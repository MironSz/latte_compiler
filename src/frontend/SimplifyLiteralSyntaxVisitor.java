package frontend;

import latte.Absyn.*;
import latte.FoldVisitor;

public class SimplifyLiteralSyntaxVisitor extends FoldVisitor<Expr, Object> {


    @Override
    public Expr leaf(Object arg) {
        return null;
    }

    @Override
    public Expr combine(Expr x, Expr y, Object arg) {
        return null;
    }

    @Override
    public Expr visit(EApp p, Object arg) {
        return p;
    }

    @Override
    public Expr visit(EAdd p, Object arg) {
        Expr e1 = p.expr_1.accept(this, arg);
        Expr e2 = p.expr_2.accept(this, arg);
        if (e1 instanceof ELitInt && e2 instanceof ELitInt) {
            if (p.addop_ instanceof Plus)
                return new ELitInt(((ELitInt) e1).integer_ + ((ELitInt) e2).integer_);
            else
                return new ELitInt(((ELitInt) e1).integer_ - ((ELitInt) e2).integer_);
        } else {
            return new EAdd(e1, p.addop_, e2);
        }

    }


    @Override
    public Expr visit(EMul p, Object arg) {
        Expr e1 = p.expr_1.accept(this, arg);
        Expr e2 = p.expr_2.accept(this, arg);
        Expr retExpr;
        if (e1 instanceof ELitInt && e2 instanceof ELitInt) {
            if (p.mulop_ instanceof Times)
                retExpr = new ELitInt(((ELitInt) e1).integer_ * ((ELitInt) e2).integer_);
            else if (p.mulop_ instanceof Div) {
                if (((ELitInt) e2).integer_.equals(0)) {
                    throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num,
                            "Division by zero"));

                }
                retExpr = new ELitInt(((ELitInt) e1).integer_ / ((ELitInt) e2).integer_);
            } else {
                if (((ELitInt) e2).integer_.equals(0)) {
                    throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num,
                            "Modulo by zero"));

                }
                retExpr = new ELitInt(((ELitInt) e1).integer_ % ((ELitInt) e2).integer_);
            }

        } else {
            retExpr = new EMul(e1, p.mulop_, e2);
        }

        return retExpr;
    }

    @Override
    public Expr visit(Cond p, Object arg) {
        p.expr_ = p.expr_.accept(this, arg);
        p.stmt_.accept(this, arg);
        return null;
    }

    @Override
    public Expr visit(CondElse p, Object arg) {
        p.expr_ = p.expr_.accept(this, arg);
        p.stmt_1.accept(this, arg);
        p.stmt_2.accept(this, arg);
        return null;
    }

    @Override
    public Expr visit(ELitInt p, Object arg) {
        return p;
    }

    @Override
    public Expr visit(ELitTrue p, Object arg) {
        return p;
    }

    @Override
    public Expr visit(ELitFalse p, Object arg) {
        return p;
    }

    @Override
    public Expr visit(While p, Object arg) {
        p.expr_ = p.expr_.accept(this, arg);
        p.stmt_.accept(this, arg);
        return null;
    }




    @Override
    public Expr visit(EOr p, Object arg) {
        Expr p1 = p.expr_1.accept(this, arg);
        Expr p2 = p.expr_2.accept(this, arg);

        if ((p1 instanceof ELitFalse || p1 instanceof ELitTrue)
                && (p2 instanceof ELitFalse || p2 instanceof ELitTrue)) {
            if ((p1 instanceof ELitTrue || p2 instanceof ELitTrue))
                return new ELitTrue();
            else
                return new ELitFalse();
        }

        return new EOr(p1, p2);
    }

    @Override
    public Expr visit(EVar p, Object arg) {
        return p;
    }

    @Override
    public Expr visit(EString p, Object arg) {
        return p;
    }

    @Override
    public Expr visit(ERel p, Object arg) {
        p.expr_1 = p.expr_1.accept(this, arg);
        p.expr_2 = p.expr_2.accept(this, arg);
        if (p.expr_2.getClass().equals(p.expr_1.getClass()))
            if (p.relop_ instanceof EQU) {
                if (p.expr_1.equals(p.expr_2)) {
                    return new ELitTrue();
                } else {
                    return p;
                }
            } else if (p.expr_1 instanceof ELitInt && p.expr_2 instanceof ELitInt) {
                if (checkOp(((ELitInt) p.expr_1).integer_, ((ELitInt) p.expr_2).integer_, p.relop_)) {
                    return new ELitTrue();
                } else {
                    return new ELitFalse();
                }
            }
        return p;
    }

    private boolean checkOp(int a, int b, RelOp op) {
        if (op instanceof LTH)
            return a < b;
        if (op instanceof LE)
            return a <= b;
        if (op instanceof GE)
            return a >= b;
        if (op instanceof GTH)
            return a > b;
        if (op instanceof NE)
            return a!=b;
        return a == b;
    }

    @Override
    public Expr visit(EAnd p, Object arg) {
        Expr p1 = p.expr_1.accept(this, arg);
        Expr p2 = p.expr_2.accept(this, arg);

        if ((p1 instanceof ELitFalse || p1 instanceof ELitTrue)
                && (p2 instanceof ELitFalse || p2 instanceof ELitTrue)) {
            if ((p1 instanceof ELitTrue && p2 instanceof ELitTrue))
                return new ELitTrue();
            else
                return new ELitFalse();
        }

        return new EAnd(p1, p2);
    }

    @Override
    public Expr visit(Neg p, Object arg) {
        Expr expr = p.expr_.accept(this, arg);
        if (expr instanceof ELitInt)
            return new ELitInt(((ELitInt) p.expr_).integer_ * (-1));
        return p;
    }

    @Override
    public Expr visit(Not p, Object arg) {
        p.expr_ = p.expr_.accept(this, arg);
        if (p.expr_ instanceof ELitTrue)
            return new ELitFalse();
        else if (p.expr_ instanceof ELitFalse)
            return new ELitTrue();
        else
            return p;
    }
}
