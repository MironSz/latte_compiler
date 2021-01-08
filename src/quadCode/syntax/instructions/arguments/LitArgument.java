package quadCode.syntax.instructions.arguments;

import latte.Absyn.ELitFalse;
import latte.Absyn.ELitInt;
import latte.Absyn.ELitTrue;
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
        if (litExpr instanceof ELitInt)
            return String.valueOf(((ELitInt) litExpr).integer_);
        if (litExpr instanceof ELitTrue)
            return "1";
        if (litExpr instanceof ELitFalse)
            return "0";
        return super.toString();
    }

    public LitArgument(Expr litExpr) {
        this.litExpr = litExpr;
    }
}
