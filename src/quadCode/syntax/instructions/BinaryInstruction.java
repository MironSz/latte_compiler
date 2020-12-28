package quadCode.syntax.instructions;

import latte.Absyn.Expr;
import quadCode.syntax.LValue;
import quadCode.syntax.Operation;
import quadCode.syntax.RValue;

public class BinaryInstruction extends Instruction {
    String a, b;
    String x;
    Expr expr; // add,sub,mul, div, mod, or, and ...


    public BinaryInstruction(String a, String b, String x, Expr expr) {
        this.a = a;
        this.b = b;
        this.x = x;
        this.expr = expr;
    }

    @Override
    public void setResultVar(String resultVar) {

    }
}
