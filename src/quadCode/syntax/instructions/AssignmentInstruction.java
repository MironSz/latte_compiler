package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManagement;

public class AssignmentInstruction extends Instruction {
    String leftVar;
    InstructionArgument rightVar;

    public AssignmentInstruction(String leftVar, InstructionArgument rightVar) {
        this.leftVar = leftVar;
        this.rightVar = rightVar;
    }

    public String getLeftVar() {
        return leftVar;
    }

    public InstructionArgument getRightVar() {
        return rightVar;
    }

    @Override
    public void setResultVar(String resultVar) {

    }

    @Override
    public String getResultVar() {
        return null;
    }

    @Override
    public void translate(AssemblyTranslator assemblyTranslator, MemoryManagement memoryManagement) {
        assemblyTranslator.translate(this,memoryManagement);
    }
}
