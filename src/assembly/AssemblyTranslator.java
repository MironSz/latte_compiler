package assembly;

import assembly.memory.MemoryLocation;
import assembly.memory.MemoryManager;
import assembly.memory.Producer;
import assembly.memory.locations.LitPseudoLocation;
import assembly.memory.locations.Register;
import frontend.DeclarationContext;
import latte.Absyn.*;
import quadCode.syntax.Block;
import quadCode.syntax.instructions.*;
import quadCode.syntax.instructions.arguments.LitArgument;
import quadCode.syntax.instructions.arguments.VarArgument;
import quadCode.syntax.instructions.arguments.VoidArgument;
import quadCode.syntax.jumps.BlockJump;
import quadCode.syntax.jumps.CondJump;
import quadCode.syntax.jumps.RetJump;
import quadCode.syntax.jumps.SimpleJump;
import quadCode.translator.TranslationContext;

import java.util.*;

import static assembly.AssemblyInstructions.*;

//Wynik w eaxie
public class AssemblyTranslator extends Producer {
    HashMap<String, Integer> numberOfVarsInFunction = new HashMap<String, Integer>();
    String currentFunction;

    private void translateFunction(Block block, MemoryManager memoryManager) {
        translateLabel(block.getLabel());

//        Stad się biorą paramsy
        List<String> varsInFunction = DeclarationContext.paramsInFunction(block.getLabel());
        int numberOfParams = varsInFunction.size();
        List<String> allLiterals = new LinkedList<>();
        currentFunction = block.getLabel();

        allVarsInFunction(block, varsInFunction, allLiterals, new HashSet<>());
        numberOfVarsInFunction.put(block.getLabel(), varsInFunction.size() - numberOfParams);


        int numberOfParamsInFunction = DeclarationContext.numberOfParamsInFunction(block.getLabel());

        memoryManager.initManagerForFunction(block.getLabel(), varsInFunction, allLiterals, numberOfParamsInFunction);


        block.isAlreadyTranslated = true;
        block.getInstructions().forEach(instruction -> instruction.translate(this, memoryManager));
        translate(block.getNextBlock(), memoryManager);
        block.getNextBlock().allNextBlocks().forEach(block1 -> {
            if (!block1.isAlreadyTranslated) {
                memoryManager.resetManager();
                translateSingleBlock(block1, memoryManager);
            }
        });
    }

    private void translateSingleBlock(Block block, MemoryManager memoryManager) {
        block.isAlreadyTranslated = true;
        translateLabel(block.getLabel());
        block.getInstructions().forEach(instruction -> instruction.translate(this, memoryManager));
        translate(block.getNextBlock(), memoryManager);
        block.getNextBlock().allNextBlocks().forEach(block1 -> {
            if (!block1.isAlreadyTranslated) {
                memoryManager.resetManager();
                translateSingleBlock(block1, memoryManager);
            }
        });
    }

    private void addAllStringConstantsInCode(List<Block> allBlocks, MemoryManager memoryManager) {
        allBlocks.stream()
                .map(Block::getInstructions)
                .flatMap(Collection::stream)
                .map(Instruction::allArgsInInstruction)
                .flatMap(Collection::stream)
                .forEach(argument -> {
                    if (argument instanceof LitArgument) {
                        if (((LitArgument) argument).getLitExpr() instanceof EString) {
                            String name = TranslationContext.newConstName();
                            String declaration = constantInstruction(((EString) ((LitArgument) argument).getLitExpr()).string_, name);
                            ((LitArgument) argument).setConstName(name);
                            LitPseudoLocation litPseudoLocation = new LitPseudoLocation(name, true);
                            emmitAssemblyInstruction(declaration);

                            memoryManager.addConstant(litPseudoLocation, (LitArgument) argument);
                        }
                    }
                });
    }

    private void allVarsInFunction(Block block, List<String> acc, List<String> allLiterals, Set<Block> visited) {
        if (visited.contains(block))
            return;
        visited.add(block);

        for (Instruction instruction : block.getInstructions()) {
            for (String varName : instruction.allVarNamesInInstruction()) {
                if (!acc.contains(varName))
                    acc.add(varName);
            }
        }
        for (Block nextBlock : block.getNextBlock().allNextBlocks())
            allVarsInFunction(nextBlock, acc, allLiterals, visited);
    }

    private void codePrefix(List<Block> allBlocks, MemoryManager memoryManager) {
        emmitAssemblyInstruction("global    main");
        emmitAssemblyInstruction("segment   .data");

        addAllStringConstantsInCode(allBlocks, memoryManager);

        emmitAssemblyInstruction("segment   .text");
        emmitAssemblyInstruction("extern   _printInt");
        emmitAssemblyInstruction("extern   _printString");
        emmitAssemblyInstruction("extern   _readInt");
        emmitAssemblyInstruction("extern   _readString");
        emmitAssemblyInstruction("extern   _error");
        emmitAssemblyInstruction("extern   _addTwoStrings");

        emmitAssemblyInstruction("main:");
        memoryManager.initManagerForFunction("_main", new LinkedList<>(), new LinkedList<>(), 0);
        saveAllRegisters(memoryManager);

        new CallInstruction(null, "main").translate(this, memoryManager);
        restoreAllRegisters(memoryManager);
        emmitAssemblyInstruction("    ret");

//        emmitAssemblyInstruction(movInstruction(new Register("eax"), new Register("edi")));
//        emmitAssemblyInstruction(movInstruction(new LitPseudoLocation("60"), new Register("rax")));
//        emmitAssemblyInstruction("    syscall");
//        emmitAssemblyInstruction("");
    }

    public void saveAllRegisters(MemoryManager memoryManager) {
        for (Register register : memoryManager.getPreservedRegisters())
            emmitAssemblyInstruction(pushInstruction(register));

    }

    public void restoreAllRegisters(MemoryManager memoryManager) {
        Collections.reverse(memoryManager.getPreservedRegisters());
        for (Register register : memoryManager.getPreservedRegisters())
            emmitAssemblyInstruction(popInstruction(register));
    }

    public void translate(List<Block> blocks, MemoryManager memoryManager) {
        codePrefix(blocks, memoryManager);
        blocks.forEach(block -> {
            if (DeclarationContext.allFunctions().contains(block.getLabel())) {
                translateFunction(block, memoryManager);
            }
        });
    }

    private void translateLabel(String label) {
        emmitAssemblyInstruction("_" + label + ":");
    }

    private void translate(BlockJump blockJump, MemoryManager memoryManager) {
        memoryManager.dumpAllDataToMem();
        if (blockJump instanceof SimpleJump) {
            emmitAssemblyInstruction(jmpInstruction(blockJump.allNextBlocks().get(0).getLabel()));
        } else if (blockJump instanceof RetJump) {
            ((RetJump) blockJump).getReturnInstruction().translate(this, memoryManager);

        } else if (blockJump instanceof CondJump) {
            emmitAssemblyInstruction("    " + ((CondJump) blockJump).getCondition() + " _" + ((CondJump) blockJump).getTrueBlock().getLabel());
            emmitAssemblyInstruction("    jmp" + " _" + ((CondJump) blockJump).getFalseBlock().getLabel());
        }
    }


    public void translate(BinaryInstruction instruction, MemoryManager memoryManager) {
        Type operationType = DeclarationContext.getType(instruction.getExpr());
        if (operationType instanceof Int) {
            if (instruction.getExpr() instanceof EAdd ||
                    (instruction.getExpr() instanceof EMul && ((EMul) instruction.getExpr()).mulop_ instanceof Times)) {

                BinaryInstructionTranslator.translateIntAddTimes(instruction, memoryManager, this);
            } else if (instruction.getExpr() instanceof EMul) {
                BinaryInstructionTranslator.translateDiv(instruction, memoryManager, this);
            }
        } else if (DeclarationContext.getType(instruction.getExpr()) instanceof Str) {
            if (instruction.getExpr() instanceof EAdd) {
                BinaryInstructionTranslator.translateAddStr(instruction, memoryManager, this);
            }
        } else if (instruction.getExpr() instanceof ERel) {
            if (instruction.intCompare) {
                BinaryInstructionTranslator.translateIntCmp(instruction, memoryManager, this);
            }
        }
    }

    //    x=y; (z założenia x!=y)
    public void translate(AssignmentInstruction instruction, MemoryManager memoryManager) {
        if (!instruction.getResultVar().equals(instruction.getRightVar().assemblyName()))
            memoryManager.equate(new VarArgument(instruction.getResultVar()), instruction.getRightVar());
    }

    public void translate(CallInstruction instruction, MemoryManager memoryManager) {
        memoryManager.dumpAllDataToMem();
        emmitAssemblyInstruction(callInstruction(instruction.getFunction()));

        if (instruction.getResultVar() != null && !instruction.getResultVar().equals("")){
            memoryManager.removeVarFromOtherLocations(new VarArgument((instruction.getResultVar())),null);
            memoryManager.addVarToSpecificLocation(
                    memoryManager.getSpecificRegister("rax"),
                    new VarArgument(instruction.getResultVar())
            );
        }


        memoryManager.restoreAllData(DeclarationContext.numberOfParamsInFunction(instruction.getFunction()));
        Register rax = memoryManager.getSpecificRegister("rax");
        memoryManager.addVarToSpecificLocation(rax, new VarArgument(instruction.getResultVar()));
        memoryManager.removeVarFromOtherLocations(new VarArgument(instruction.getResultVar()),rax);

    }


    public void translate(ParamInnstruction instruction, MemoryManager memoryManager) {
        String push = pushInstruction(memoryManager.getLocation(instruction.getParam()));
        memoryManager.incrementParamsOnStackCounter();
        emmitAssemblyInstruction(push);
    }

    public void translate(ReturnInstruction instruction, MemoryManager memoryManager) {
        int numberOfVarsToRemove = numberOfVarsInFunction.get(currentFunction);

        if (!(instruction.getResultVariable() instanceof VoidArgument))
            memoryManager.getSpecificRegisterWithVar("rax", instruction.getResultVariable(), false);

        emmitAssemblyInstruction(addInstruction(
                new Register("rsp"),
                new LitPseudoLocation(String.valueOf(numberOfVarsToRemove * 8)),
                true));

        emmitAssemblyInstruction("    ret");
    }


    public void translate(CompareInstruction instruction, MemoryManager memoryManager) {
        Register leftRegister = memoryManager.getRegisterContaining(instruction.getLeft(), true);
        MemoryLocation rightVarLocation = memoryManager.getLocation(instruction.getRight());
        emmitAssemblyInstruction(cmpInstruction(leftRegister, rightVarLocation));
    }

    public String translate(RelOp relOp) {
        if (relOp instanceof LTH) {
            return "jl";
        } else if (relOp instanceof LE) {
            return "jle";
        } else if (relOp instanceof GTH) {
            return "jg";
        } else if (relOp instanceof GE) {
            return "jge";
        } else if (relOp instanceof EQU) {
            return "je";
        } else if (relOp instanceof NE) {
            return "jne";
        } else {
            throw new RuntimeException("Unknown comparison");
        }
    }

}
