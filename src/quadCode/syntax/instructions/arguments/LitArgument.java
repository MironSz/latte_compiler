package quadCode.syntax.instructions.arguments;

import latte.Absyn.*;

public class LitArgument extends InstructionArgument {
    Expr litExpr;// TODO determine which subclass

    String constName;

    public Expr getLitExpr() {
        return litExpr;
    }

    public void setLitExpr(Expr litExpr) {
        this.litExpr = litExpr;
    }


    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName = constName;
    }

    @Override
    public String assemblyName() {
        if (litExpr instanceof ELitInt)
            return String.valueOf(((ELitInt) litExpr).integer_);
        if (litExpr instanceof ELitTrue)
            return "1";
        if (litExpr instanceof ELitFalse)
            return "0";
        if(litExpr instanceof EString)
            return constName;
        return super.toString();
    }

    public LitArgument(Expr litExpr) {
        this.litExpr = litExpr;
    }
}
