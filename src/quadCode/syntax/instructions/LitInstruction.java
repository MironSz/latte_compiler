package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import latte.Absyn.ELitInt;
import latte.Absyn.Expr;
import quadCode.translator.TranslationContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LitInstruction extends Instruction {
    String varName;
    Expr litExpr;// TODO determine which subclass

    public LitInstruction(String varName, Expr litExpr) {
        this.varName = varName;
        this.litExpr = litExpr;
    }

    @Override
    public void setResultVar(String resultVar) {
        varName=resultVar;
    }

    public Expr getLitExpr() {
        return litExpr;
    }

    @Override
    public String getResultVar() {
        return varName;
    }

    @Override
    public void translate(AssemblyTranslator assemblyTranslator, MemoryManager memoryManagement) {
        assemblyTranslator.translate(this,memoryManagement);
    }

    @Override
    public List<String> allVarsInInstruction() {
        return Collections.singletonList(varName);
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
