package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManagement;

public class ReturnInstruction extends Instruction {
    String resultVariable;

    public ReturnInstruction(String resultVariable) {
        this.resultVariable = resultVariable;
    }

    @Override
    public void setResultVar(String resultVar) {

    }

    @Override
    public String getResultVar() {
        return "Wrong usage";
    }

    @Override
    public void translate(AssemblyTranslator assemblyTranslator, MemoryManagement memoryManagement) {
        assemblyTranslator.translate(this,memoryManagement);
    }

    @Override
    public String toString() {
        return "return "+resultVariable;
    }
}
