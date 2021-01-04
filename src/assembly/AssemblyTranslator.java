package assembly;

import assembly.memory.MemoryLocation;
import assembly.memory.MemoryManager;
import assembly.memory.Producer;
import assembly.memory.locations.LitPseudoLocation;
import assembly.memory.locations.Register;
import frontend.DeclarationContext;
import latte.Absyn.EAdd;
import latte.Absyn.Int;
import latte.Absyn.Plus;
import quadCode.syntax.Block;
import quadCode.syntax.instructions.*;
import quadCode.syntax.jumps.BlockJump;
import quadCode.syntax.jumps.SimpleJump;
import quadCode.translator.TranslationContext;

import java.util.List;

import static assembly.AssemblyInstructions.*;

//Wynik w eaxie
public class AssemblyTranslator extends Producer {

    private void translateSingleBlock(Block block, MemoryManager memoryManager) {
        translateLabel(block.getLabel());
        block.getInstructions().forEach(instruction -> instruction.translate(this, memoryManager));
        translate(block.getNextBlock(), memoryManager);
        block.getNextBlock().allNextBlocks().forEach(block1 -> translateSingleBlock(block1, memoryManager));
    }

    private void codePrefix() {
        emmitAssemblyInstruction("    global    _start");
        emmitAssemblyInstruction("    section   .text");
        emmitAssemblyInstruction("_start:");
        emmitAssemblyInstruction(callInstruction("main"));
        emmitAssemblyInstruction(movInstruction(new Register("eax"), new Register("edi")));
//        emmitAssemblyInstruction(movInstruction());
        emmitAssemblyInstruction(movInstruction(new Register("rax"), new LitPseudoLocation("60")));
        emmitAssemblyInstruction("    syscall");
        emmitAssemblyInstruction("");
        emmitAssemblyInstruction("    section .data");
    }

    public void translate(List<Block> blocks, MemoryManager memoryManager) {
        codePrefix();
        blocks.forEach(block -> {
            if (TranslationContext.allFunctions().contains(block.getLabel())) {
                memoryManager.initManagerForFunction(block.getLabel(),
                        TranslationContext.varsInFunction(block.getLabel()),
                        DeclarationContext.numberOfParamsInFunction(block.getLabel()));
                translateSingleBlock(block, memoryManager);
            }
        });
    }

    private void translateLabel(String label) {
        emmitAssemblyInstruction(label + ":");
    }

    private void translate(BlockJump blockJump, MemoryManager memoryManager) {
        if (blockJump instanceof SimpleJump) {
            emmitAssemblyInstruction(jmpInstruction( blockJump.allNextBlocks().get(0).getLabel()));
        } else {
            emmitAssemblyInstruction("TODO " + blockJump);
        }
    }


    public void translate(BinaryInstruction instruction, MemoryManager memoryManager) {
        if (DeclarationContext.getType(instruction.getExpr()) instanceof Int) {
            if (instruction.getExpr() instanceof EAdd) {
                Register resultRegister = memoryManager.getRegisterContaining(instruction.getLeftVar(), true, false);
                MemoryLocation rightVarLocation = memoryManager.getLocation(instruction.getRightVar());

                String addInstruction = addInstruction(resultRegister,
                        rightVarLocation,
                        ((EAdd) instruction.getExpr()).addop_ instanceof Plus);

                emmitAssemblyInstruction(addInstruction);
                memoryManager.removeVarFromOtherLocations(new VarArgument(instruction.getResultVar()), resultRegister);
                memoryManager.addVarToSpecificLocation(resultRegister, new VarArgument(instruction.getResultVar()));
            }
        }
    }

    //    x=y; (z założenia x!=y)
    public void translate(AssignmentInstruction instruction, MemoryManager memoryManager) {
        memoryManager.equate(new VarArgument(instruction.getResultVar()), instruction.getRightVar());
    }

    public void translate(CallInstruction instruction, MemoryManager memoryManager) {
        memoryManager.dumpAllDataToMem();
        emmitAssemblyInstruction(callInstruction(instruction.getFunction()));
        if (instruction.getResultVar() != null && !instruction.getResultVar().equals("")) {
            memoryManager.addVarToSpecificLocation(
                    memoryManager.getSpecificRegister("eax"),
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
        Register returnRegister = memoryManager.getSpecificRegister("eax");
        memoryManager.addVarToSpecificLocation(returnRegister, new VarArgument(instruction.getResultVar()));
    }
}
