package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;

public class ReturnInstruction extends Instruction {
    InstructionArgument resultVariable;

    public ReturnInstruction(InstructionArgument resultVariable) {
        this.resultVariable = resultVariable;
    }

    @Override
    public String getResultVar() {
        return "Wrong usage";
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
}
