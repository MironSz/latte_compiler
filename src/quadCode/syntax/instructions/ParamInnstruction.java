package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManagement;

public class ParamInnstruction extends Instruction{
    InstructionArgument param;

    public ParamInnstruction(InstructionArgument param) {
        this.param = param;
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

    @Override
    public String toString() {
        return "Param "+param;
    }

    public InstructionArgument getParam() {
        return param;
    }
}
