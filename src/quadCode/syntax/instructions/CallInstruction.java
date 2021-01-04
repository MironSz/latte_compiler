package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import latte.Absyn.FnDef;
import quadCode.translator.TranslationContext;

public class CallInstruction extends Instruction{
    String resultVar;
    String function;

    public CallInstruction(String resultVar, String function) {
        TranslationContext.addVarToFunction(resultVar);

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

    public String getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return resultVar+" = "+"Call "+function;
    }
}
