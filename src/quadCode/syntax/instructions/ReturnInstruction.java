package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import quadCode.syntax.instructions.arguments.InstructionArgument;
import quadCode.syntax.instructions.arguments.VarArgument;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ReturnInstruction extends Instruction {
    InstructionArgument resultVariable;

    public ReturnInstruction(InstructionArgument resultVariable) {
        this.resultVariable = resultVariable;
    }

    @Override
    public InstructionArgument getResultVar() {
        return resultVariable;
    }


    @Override
    public void translate(AssemblyTranslator assemblyTranslator, MemoryManager memoryManagement) {
        assemblyTranslator.translate(this, memoryManagement);
    }

    @Override
    public String toString() {
        return "return " + resultVariable;
    }

    @Override
    public List<String> allVarNamesInInstruction() {
        if (resultVariable instanceof VarArgument) {
            return Collections.singletonList(resultVariable.assemblyName());
        }
        return new LinkedList<>();
    }

    @Override
    public List<InstructionArgument> allArgsInInstruction() {
        return Collections.singletonList(resultVariable);
    }

    @Override
    public void changeArgument(InstructionArgument from, InstructionArgument to) {
        resultVariable = to;
    }

    public InstructionArgument getResultVariable() {
        return resultVariable;
    }


}
