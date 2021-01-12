package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import quadCode.syntax.instructions.arguments.InstructionArgument;
import quadCode.syntax.instructions.arguments.VarArgument;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CompareInstruction extends Instruction {
    InstructionArgument left, right;

    public CompareInstruction(InstructionArgument left, InstructionArgument right) {
        this.left = left;
        this.right = right;
    }

    public InstructionArgument getLeft() {
        return left;
    }

    public void setLeft(InstructionArgument left) {
        this.left = left;
    }

    public InstructionArgument getRight() {
        return right;
    }

    public void setRight(InstructionArgument right) {
        this.right = right;
    }

    @Override
    public void setResultVar(String resultVar) {

    }

    @Override
    public String getResultVar() {
        return null;
    }

    @Override
    public void translate(AssemblyTranslator assemblyTranslator, MemoryManager memoryManagement) {
        assemblyTranslator.translate(this, memoryManagement);
    }

    @Override
    public List<String> allVarNamesInInstruction() {
        List<String> result = new LinkedList<>();
        if (left instanceof VarArgument) {
            result.add(((VarArgument) left).getVarName());
        }
        if (right instanceof VarArgument) {
            result.add(((VarArgument) right).getVarName());
        }
        return result;
    }

    @Override
    public List<InstructionArgument> allArgsInInstruction() {
        return Arrays.asList(left,right);
    }

}
