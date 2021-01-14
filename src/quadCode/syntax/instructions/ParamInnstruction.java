package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import quadCode.syntax.instructions.arguments.InstructionArgument;
import quadCode.syntax.instructions.arguments.VarArgument;

import java.util.Collections;
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
    public List<String> allVarNamesInInstruction() {
        if(param instanceof VarArgument)
            return Collections.singletonList(((VarArgument) param).getVarName());
        return new LinkedList<>();
    }

    @Override
    public List<InstructionArgument> allArgsInInstruction() {
        return Collections.singletonList(param);
    }

    @Override
    public String toString() {
        return "Param "+param;
    }

    public InstructionArgument getParam() {
        return param;
    }
}
