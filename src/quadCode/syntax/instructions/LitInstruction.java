package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import latte.Absyn.ELitInt;
import latte.Absyn.Expr;
import quadCode.translator.TranslationContext;

public class LitInstruction extends Instruction {
    String varName;
    Expr litExpr;// TODO determine which subclass

    public LitInstruction(String varName, Expr litExpr) {
        TranslationContext.addVarToFunction(varName);

        this.varName = varName;
        this.litExpr = litExpr;
    }

    @Override
    public void setResultVar(String resultVar) {
        varName=resultVar;
    }

    @Override
    public String getResultVar() {
        return varName;
    }

    @Override
    public void translate(AssemblyTranslator assemblyTranslator, MemoryManager memoryManagement) {
        assemblyTranslator.translate(this,memoryManagement);
    }

    public String getLit(){
        if (litExpr instanceof ELitInt)
            return String.valueOf(((ELitInt) litExpr).integer_);
        return  "UNSUPORTED LIT";
    }

    @Override
    public String toString() {
        return varName+" = constant";

    }
}
