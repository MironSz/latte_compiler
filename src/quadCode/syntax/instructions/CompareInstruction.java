package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import quadCode.syntax.instructions.arguments.InstructionArgument;
import quadCode.syntax.instructions.arguments.VarArgument;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CompareInstruction extends Instruction {
    InstructionArgument leftVar, rightVar;

    public CompareInstruction(InstructionArgument leftVar, InstructionArgument rightVar) {
        this.leftVar = leftVar;
        this.rightVar = rightVar;
    }

    public InstructionArgument getLeftVar() {
        return leftVar;
    }

    public void setLeftVar(InstructionArgument leftVar) {
        this.leftVar = leftVar;
    }

    public InstructionArgument getRightVar() {
        return rightVar;
    }

    public void setRightVar(InstructionArgument rightVar) {
        this.rightVar = rightVar;
    }

    @Override
    public void changeArgument(InstructionArgument from, InstructionArgument to) {
        if (leftVar == from)
            leftVar = to;
        else if (rightVar == from)
            rightVar = to;
    }

    @Override
    public InstructionArgument getResultVar() {
        throw new RuntimeException("This shouldn't happen");
    }

    @Override
    public void translate(AssemblyTranslator assemblyTranslator, MemoryManager memoryManagement) {
        assemblyTranslator.translate(this, memoryManagement);
    }

    @Override
    public List<String> allVarNamesInInstruction() {
        List<String> result = new LinkedList<>();
        if (leftVar instanceof VarArgument) {
            result.add(((VarArgument) leftVar).getVarName());
        }
        if (rightVar instanceof VarArgument) {
            result.add(((VarArgument) rightVar).getVarName());
        }
        return result;
    }

    @Override
    public List<InstructionArgument> allArgsInInstruction() {
        return Arrays.asList(leftVar, rightVar);
    }

}
