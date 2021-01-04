package assembly;

import assembly.memory.MemoryLocation;
import assembly.memory.MemoryManagement;
import assembly.memory.Producer;
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

//Wynik w eaxie
public class AssemblyTranslator extends Producer {

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


    private String movInstruction(MemoryLocation from, MemoryLocation to) {
        return "mov " + from.toString() + "," + to.toString();
    }

    private String movInstruction(String from, String to) {
        return "mov " + from.toString() + "," + to.toString();
    }

    private String pushInstruction(MemoryLocation x){
        return "push "+x.toString();
    }
    // x+=y
    private String addInstruction(MemoryLocation x, MemoryLocation y, boolean plus) {
        if (plus)
            return "add " + x.toString() + "," + y.toString();
        else
            return "sub " + x.toString() + "," + y.toString();

    }

    private String callInstruction(String functionName) {
        return "call " + functionName;
    }

    public Register moveArgumentToRegister(InstructionArgument argument, MemoryManagement memoryManagement) {
        if (argument instanceof VarArgument && memoryManagement.getRegistersForVar(argument).size() > 0) {
            return memoryManagement.getRegistersForVar(argument).get(0);
        }

        Register resultRegister = memoryManagement.getFreeRegister();
        String assemblyInstruction;
        assemblyInstruction = movInstruction(memoryManagement.getMemoryLocation(argument), resultRegister);
        emmitAssemblyInstruction(assemblyInstruction);
        return resultRegister;
    }


    public void translate(BinaryInstruction instruction, MemoryManagement memoryManagement) {
        if (DeclarationContext.getType(instruction.getExpr()) instanceof Int) {
            if (instruction.getExpr() instanceof EAdd) {
                Register resultRegister = moveArgumentToRegister(instruction.getLeftVar(), memoryManagement);


                String addInstruction = addInstruction(resultRegister,
                        memoryManagement.getMemoryLocation(instruction.getRightVar()),
                        ((EAdd) instruction.getExpr()).addop_ instanceof Plus);

                emmitAssemblyInstruction(addInstruction);
                memoryManagement.removeVarFromRegisters(instruction.getResultVar());
                memoryManagement.addVarToRegister(instruction.getResultVar(), resultRegister);
            }
        }
    }

    //    x=y; (z założenia x!=y)
    public void translate(AssignmentInstruction instruction, MemoryManagement memoryManagement) {
        memoryManagement.removeVarFromRegisters(instruction.getLeftVar());
        List<Register> registers = memoryManagement.getRegistersForVar(instruction.getRightVar());
        if (registers.isEmpty()) {
            Register resultRegister = moveArgumentToRegister(instruction.getRightVar(), memoryManagement);
            resultRegister.addVar(instruction.getLeftVar());
            registers.add(resultRegister);
        }
        registers.forEach(register -> memoryManagement.addVarToRegister(instruction.getLeftVar(), register));
    }

    public void translate(CallInstruction instruction, MemoryManagement memoryManagement) {
        memoryManagement.saveState();
        emmitAssemblyInstruction(callInstruction(instruction.getFunction()));
        if (instruction.getResultVar() != null && !instruction.getResultVar().equals("")) {
            memoryManagement.addVarToRegister(instruction.getResultVar(), memoryManagement.getSpecificRegister("eax"));
        }
        memoryManagement.restoreState();
    }

    public void translate(LitInstruction instruction, MemoryManagement memoryManagement) {

    }

    public void translate(ParamInnstruction instruction, MemoryManagement memoryManagement) {
        String push = pushInstruction(memoryManagement.getMemoryLocation(instruction.getParam()));
        emmitAssemblyInstruction(push);
    }

    public void translate(ReturnInstruction instruction, MemoryManagement memoryManagement) {
        MemoryLocation resultLocation = memoryManagement.getMemoryLocation(instruction.getResultVar());
        Register returnRegister = memoryManagement.getSpecificRegister("eax");
        String returnAssemblyInstruction = movInstruction(resultLocation, returnRegister);
        emmitAssemblyInstruction(returnAssemblyInstruction);
    }
}
