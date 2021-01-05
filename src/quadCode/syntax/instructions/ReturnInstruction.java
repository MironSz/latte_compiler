package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import quadCode.syntax.instructions.arguments.InstructionArgument;
import quadCode.syntax.instructions.arguments.VarArgument;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ReturnInstruction extends Instruction {
    InstructionArgument resultVariable;

    public ReturnInstruction(InstructionArgument resultVariable) {
        this.resultVariable = resultVariable;
    }

    @Override
    public String getResultVar() {
        return resultVariable.assemblyName();
    }

    @Override
    public void setResultVar(String resultVar) {

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
    public List<String> allVarsInInstruction() {
        if(resultVariable instanceof VarArgument) {
            return Collections.singletonList(resultVariable.assemblyName());
        }
        return new LinkedList<>();
    }

    public InstructionArgument getResultVariable() {
        return resultVariable;
    }
}
