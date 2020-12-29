package quadCode.syntax.instructions;

import latte.Absyn.Expr;

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

    @Override
    public String getResultVar() {
        return varName;
    }

    @Override
    public String toString() {
        return varName+" = constant";

    }
}
