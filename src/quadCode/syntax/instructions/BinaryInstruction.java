package quadCode.syntax.instructions;

import latte.Absyn.Expr;

public class BinaryInstruction extends Instruction {
    String leftVar, rightVar;
    String resultVar;
    Expr expr; // add,sub,mul, div, mod, or, and ...


    public BinaryInstruction(String leftVar, String rightVar, String resultVar, Expr expr) {
        this.leftVar = leftVar;
        this.rightVar = rightVar;
        this.resultVar = resultVar;
        this.expr = expr;
    }

    @Override
    public String getResultVar() {
        return resultVar;
    }

    @Override
    public void setResultVar(String resultVar) {
        this.resultVar = resultVar;
    }

    @Override
    public String toString() {
        return resultVar+" = "+leftVar+ " "+expr.getClass().toString()+" "+rightVar;
    }
}
