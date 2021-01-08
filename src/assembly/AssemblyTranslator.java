package assembly;

import assembly.memory.MemoryLocation;
import assembly.memory.MemoryManager;
import assembly.memory.Producer;
import assembly.memory.locations.ConstantLocation;
import assembly.memory.locations.LitPseudoLocation;
import assembly.memory.locations.Register;
import assembly.memory.locations.StackLocation;
import frontend.DeclarationContext;
import latte.Absyn.*;
import quadCode.syntax.Block;
import quadCode.syntax.instructions.*;
import quadCode.syntax.instructions.arguments.LitArgument;
import quadCode.syntax.instructions.arguments.VarArgument;
import quadCode.syntax.instructions.arguments.VoidArgument;
import quadCode.syntax.jumps.*;
import quadCode.translator.TranslationContext;

import java.util.*;

import static assembly.AssemblyInstructions.*;

//Wynik w eaxie
public class AssemblyTranslator extends Producer {


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

    private List<LitArgument> allStringConstantsInCode(List<Block> allBlocks, MemoryManager memoryManager){
        allBlocks.stream().map(Block::getInstructions).flatMap(Collection::stream).forEach(instruction -> {
            if(instruction instanceof LitInstruction){
                if(((LitInstruction) instruction).getLitExpr() instanceof EString){
                    String name = TranslationContext.newConstName();
                    String declaration = constantInstruction(((EString) ((LitInstruction) instruction).getLitExpr()).string_,name);

                    LitPseudoLocation litPseudoLocation = new LitPseudoLocation(name);
                    emmitAssemblyInstruction(declaration);
                    memoryManager.addConstant(litPseudoLocation,new LitArgument(((LitInstruction) instruction).getLitExpr()));
                }
            }
        });

    }
    private void allVarsInFunction(Block block, List<String> acc, List<String> allLiterals, Set<Block> visited) {
        if (visited.contains(block))
            return;

        for (Instruction instruction : block.getInstructions()) {
            for (String varName : instruction.allVarsInInstruction()) {
                if (!acc.contains(varName))
                    acc.add(varName);
            }
            if (instruction instanceof LitInstruction) {
                if (!allLiterals.contains(((LitInstruction) instruction).getLit()))
                    allLiterals.add(((LitInstruction) instruction).getLit());
            }
        }
        for (Block nextBlock : block.getNextBlock().allNextBlocks())
            allVarsInFunction(nextBlock, acc, allLiterals, visited);
    }

    private void codePrefix() {
        emmitAssemblyInstruction("    global    _start");
        emmitAssemblyInstruction("    section   .text");
        emmitAssemblyInstruction("_start:");
        emmitAssemblyInstruction(callInstruction("main"));
        emmitAssemblyInstruction(movInstruction(new Register("eax"), new Register("edi")));
        emmitAssemblyInstruction(movInstruction(new Register("rax"), new LitPseudoLocation("60")));
        emmitAssemblyInstruction("    syscall");
        emmitAssemblyInstruction("");
    }

    public void translate(List<Block> blocks, MemoryManager memoryManager) {
        codePrefix();
        blocks.forEach(block -> {
            if (DeclarationContext.allFunctions().contains(block.getLabel())) {
                List<String> varsInFunction = DeclarationContext.paramsInFunction(block.getLabel());
                List<String> allLiterals = new LinkedList<>();
                allVarsInFunction(block, varsInFunction, allLiterals, new HashSet<>());

                int numberOfParamsInFunction = DeclarationContext.numberOfParamsInFunction(block.getLabel());

                memoryManager.initManagerForFunction(block.getLabel(), varsInFunction, allLiterals, numberOfParamsInFunction);

                translateSingleBlock(block, memoryManager);
            }
        });
    }

    private void translateLabel(String label) {
        emmitAssemblyInstruction(label + ":");
    }

    private void translate(BlockJump blockJump, MemoryManager memoryManager) {
        memoryManager.dumpAllDataToMem();
        if (blockJump instanceof SimpleJump) {
            emmitAssemblyInstruction(jmpInstruction(blockJump.allNextBlocks().get(0).getLabel()));
        } else if (blockJump instanceof RetJump) {
            ((RetJump) blockJump).getReturnInstruction().translate(this, memoryManager);
        } else if (blockJump instanceof NoJump) {
        } else if (blockJump instanceof CondJump) {
            emmitAssemblyInstruction("    " + ((CondJump) blockJump).getCondition() + " " + ((CondJump) blockJump).getTrueBlock().getLabel());
            emmitAssemblyInstruction("    jmp" + " " + ((CondJump) blockJump).getFalseBlock().getLabel());
        }
    }


    public void translate(BinaryInstruction instruction, MemoryManager memoryManager) {
        if (DeclarationContext.getType(instruction.getExpr()) instanceof Int) {
            if (instruction.getExpr() instanceof EAdd ||
                    (instruction.getExpr() instanceof EMul && ((EMul) instruction.getExpr()).mulop_ instanceof Times)) {

                Register resultRegister = memoryManager.getRegisterContaining(instruction.getLeftVar(), true);
                MemoryLocation rightVarLocation = memoryManager.getLocation(instruction.getRightVar());

                memoryManager.removeVarFromLocation(instruction.getLeftVar(), resultRegister);

                String assemblyInstruction;

                if (instruction.getExpr() instanceof EAdd)
                    assemblyInstruction = addInstruction(resultRegister,
                            rightVarLocation,
                            ((EAdd) instruction.getExpr()).addop_ instanceof Plus);
                else
                    assemblyInstruction = mulInstruction(resultRegister, rightVarLocation);

                emmitAssemblyInstruction(assemblyInstruction);
                memoryManager.removeVarFromOtherLocations(new VarArgument(instruction.getResultVar()), resultRegister);
                memoryManager.addVarToSpecificLocation(resultRegister, new VarArgument(instruction.getResultVar()));
                return;
            }
        } else if (instruction.getExpr() instanceof EMul) {
            Register rax = memoryManager.getSpecificRegisterWithVar("rax",instruction.getLeftVar(),false);
            memoryManager.lockRegister(rax);
            Register rdx = memoryManager.getSpecificRegister("rdx");
            memoryManager.lockRegister(rdx);
            memoryManager.freeRegister(rdx);

            emmitAssemblyInstruction(resetRegister(rdx));

            MemoryLocation leftVarLocation = memoryManager.getLocation(instruction.getRightVar());

            emmitAssemblyInstruction(divInstruction(leftVarLocation));

            if (((EMul) instruction.getExpr()).mulop_ instanceof Div ){
                memoryManager.addVarToSpecificLocation(rax, new VarArgument(instruction.getResultVar()));
            } else{
                memoryManager.addVarToSpecificLocation(rdx, new VarArgument(instruction.getResultVar()));
            }
            memoryManager.unlockRegister(rdx);
            memoryManager.unlockRegister(rax);
            return;
        } else if (instruction.getExpr() instanceof ERel) {
            if (instruction.intCompare) {
                String writeTrueLabel = TranslationContext.newLabel("WriteTrue");
                String finalLabel = TranslationContext.newLabel("FinalLabel");
                String condition = translate(((ERel) instruction.getExpr()).relop_);

                StackLocation resultLocation = memoryManager.getDefaultStackLocation(instruction.getResultVar());
                Register leftRegister = memoryManager.getRegisterContaining(instruction.getLeftVar(), true);
                MemoryLocation rightVarLocation = memoryManager.getLocation(instruction.getRightVar());

                emmitAssemblyInstruction(cmpInstruction(leftRegister, rightVarLocation));
                emmitAssemblyInstruction("    " + condition + " " + writeTrueLabel);
                emmitAssemblyInstruction(movInstruction("0", resultLocation.assemblyCode()));
                emmitAssemblyInstruction("    jmp " + finalLabel);
                emmitAssemblyInstruction(writeTrueLabel);
                emmitAssemblyInstruction(movInstruction("1", resultLocation.assemblyCode()));
                emmitAssemblyInstruction(finalLabel);

                memoryManager.addVarToSpecificLocation(resultLocation, new VarArgument(instruction.getResultVar()));
                memoryManager.removeVarFromOtherLocations(new VarArgument(instruction.getResultVar()), resultLocation);
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
        if (instruction.getResultVar() != null && !instruction.getResultVar().equals("")) {
            memoryManager.addVarToSpecificLocation(
                    memoryManager.getSpecificRegister("rax"),
                    new VarArgument(instruction.getResultVar())
            );
        }
        memoryManager.restoreAllData(DeclarationContext.numberOfParamsInFunction(instruction.getFunction()));
    }

    public void translate(LitInstruction instruction, MemoryManager memoryManager) {
        MemoryLocation location = new LitPseudoLocation(instruction.getLit());
        memoryManager.addVarToSpecificLocation(location, new VarArgument(instruction.getResultVar()));
    }

    public void translate(ParamInnstruction instruction, MemoryManager memoryManager) {
        String push = pushInstruction(memoryManager.getLocation(instruction.getParam()));
        memoryManager.incrementParamsOnStackCounter();
        emmitAssemblyInstruction(push);
    }

    public void translate(ReturnInstruction instruction, MemoryManager memoryManager) {
        if (instruction.getResultVariable() instanceof VoidArgument) {
            emmitAssemblyInstruction("    ret");
        } else {
            memoryManager.getSpecificRegisterWithVar("eax", instruction.getResultVariable(), false);
            emmitAssemblyInstruction("    ret");
        }
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
