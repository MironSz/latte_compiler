package quadCode.syntax.instructions.arguments;

import latte.Absyn.ELitInt;
import latte.Absyn.Expr;

public class LitArgument extends InstructionArgument {
    Expr litExpr;// TODO determine which subclass

    public Expr getLitExpr() {
        return litExpr;
    }

    public void setLitExpr(Expr litExpr) {
        this.litExpr = litExpr;
    }

    @Override
    public String assemblyName() {
        if(litExpr instanceof ELitInt)
            return String.valueOf(((ELitInt) litExpr).integer_);
        return super.toString();
    }

    public LitArgument(Expr litExpr) {
        this.litExpr = litExpr;
    }
}
