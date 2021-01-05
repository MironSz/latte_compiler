package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import quadCode.syntax.instructions.arguments.InstructionArgument;

import java.util.LinkedList;
import java.util.List;

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
    public void translate(AssemblyTranslator assemblyTranslator, MemoryManager memoryManagement) {
        assemblyTranslator.translate(this,memoryManagement);
    }

    @Override
    public List<String> allVarsInInstruction() {
        return new LinkedList<>();
    }

    @Override
    public String toString() {
        return "Param "+param;
    }

    public InstructionArgument getParam() {
        return param;
    }
}
