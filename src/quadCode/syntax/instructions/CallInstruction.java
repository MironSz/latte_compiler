package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import latte.Absyn.FnDef;
import quadCode.syntax.instructions.arguments.InstructionArgument;
import quadCode.translator.TranslationContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CallInstruction extends Instruction{
    String resultVar;
    String function;

    public CallInstruction(String resultVar, String function) {

        this.resultVar = resultVar;
        this.function = function;
    }

    @Override
    public void setResultVar(String resultVar) {
        resultVar = resultVar;
    }

    @Override
    public String getResultVar() {
        return resultVar;
    }

    @Override
    public void translate(AssemblyTranslator assemblyTranslator, MemoryManager memoryManagement) {
        assemblyTranslator.translate(this,memoryManagement);
    }

    @Override
    public List<String> allVarNamesInInstruction() {
        if( resultVar==null || resultVar.equals(""))
            return new LinkedList<>();
        return Collections.singletonList(resultVar);
    }

    @Override
    public List<InstructionArgument> allArgsInInstruction() {
        return new LinkedList<>();
    }

    public String getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return resultVar+" = "+"Call "+function;
    }
}
