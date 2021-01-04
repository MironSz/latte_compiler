package assembly;

import assembly.memory.MemoryLocation;
import assembly.memory.MemoryManagement;
import assembly.memory.MemoryManager2;
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

import java.util.List;

import static assembly.AssemblyInstructions.*;

//Wynik w eaxie
public class AssemblyTranslator2 extends Producer {

    private void translateSingleBlock(Block block) {

    }

    private void codePrefix() {
        emmitAssemblyInstruction("call main");
//        emmitAssemblyInstruction("mov ");
        emmitAssemblyInstruction(movInstruction());
        emmitAssemblyInstruction("int 0x80");
    }

    public void translate(List<Block> blocks) {
        blocks.forEach(this::translateSingleBlock);
    }

    private void translateLabal(String label) {
        emmitAssemblyInstruction(label + ":");
    }

    private void translate(BlockJump blockJump) {
        if (blockJump instanceof SimpleJump) {
            emmitAssemblyInstruction("jmp " + blockJump.allNextBlocks().get(0).getLabel());
        } else {
            emmitAssemblyInstruction("TODO " + blockJump);
        }
    }


    public void translate(BinaryInstruction instruction, MemoryManager2 memoryManager) {
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
    public void translate(AssignmentInstruction instruction, MemoryManager2 memoryManager) {
        memoryManager.equate(new VarArgument(instruction.getResultVar()), instruction.getRightVar());
    }

    public void translate(CallInstruction instruction, MemoryManager2 memoryManager) {
        memoryManager.dumpAllDataToMem();
        emmitAssemblyInstruction(callInstruction(instruction.getFunction()));
        if (instruction.getResultVar() != null && !instruction.getResultVar().equals("")) {
            memoryManager.addVarToSpecificLocation(memoryManager.getSpecificRegister("eax"), new VarArgument(instruction.getResultVar()));
        }
        memoryManager.restoreAllData();
    }

    public void translate(LitInstruction instruction, MemoryManager2 memoryManager) {
        MemoryLocation location = new LitPseudoLocation(instruction.getLit());
        memoryManager.addVarToSpecificLocation(location, new VarArgument(instruction.getResultVar()));
    }

    public void translate(ParamInnstruction instruction, MemoryManager2 memoryManager) {
        String push = pushInstruction(memoryManager.getLocation(instruction.getParam()));
        emmitAssemblyInstruction(push);
    }

    public void translate(ReturnInstruction instruction, MemoryManagement memoryManagement) {
        MemoryLocation resultLocation = memoryManagement.getMemoryLocation(instruction.getResultVar());
        Register returnRegister = memoryManagement.getSpecificRegister("eax");
        String returnAssemblyInstruction = movInstruction(resultLocation, returnRegister);
        emmitAssemblyInstruction(returnAssemblyInstruction);
    }
}
