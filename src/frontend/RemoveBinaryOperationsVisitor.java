package frontend;

import latte.Absyn.*;
import latte.FoldVisitor;

import java.util.stream.Collectors;

public class RemoveBinaryOperationsVisitor extends FoldVisitor<Object,Object> {
    @Override
    public Object leaf(Object arg) {
        return null;
    }

    @Override
    public Object combine(Object x, Object y, Object arg) {
        return null;
    }

    private Expr simplify(Expr expr){
        if (expr instanceof Not){
            return new ERel(new ELitFalse(),new EQU(),((Not )expr).expr_);
        } else if(expr instanceof Neg){
            return new EAdd(new ELitInt(0), new Minus(), ((Neg) expr).expr_);
        } else{
            return  expr;
        }
    }
    @Override
    public Object visit(SExp p, Object arg) {
        p.expr_=simplify(p.expr_);
        return super.visit(p, arg);
    }

    @Override
    public Object visit(EApp p, Object arg) {
        ListExpr listExpr = new ListExpr();
        p.listexpr_.stream().map(this::simplify).forEach(listExpr::add);
        p.listexpr_= listExpr;
        return super.visit(p, arg);
    }

    @Override
    public Object visit(Ret p, Object arg) {
        p.expr_=simplify(p.expr_);
        return super.visit(p, arg);
    }

    @Override
    public Object visit(Cond p, Object arg) {
        p.expr_=simplify(p.expr_);

        return super.visit(p, arg);
    }

    @Override
    public Object visit(CondElse p, Object arg) {
        p.expr_=simplify(p.expr_);

        return super.visit(p, arg);
    }

    @Override
    public Object visit(While p, Object arg) {
        p.expr_=simplify(p.expr_);

        return super.visit(p, arg);
    }

    @Override
    public Object visit(Init p, Object arg) {
        p.expr_=simplify(p.expr_);

        return super.visit(p, arg);
    }

    @Override
    public Object visit(EMul p, Object arg) {
        p.expr_1=simplify(p.expr_1);
        p.expr_2=simplify(p.expr_2);

        return super.visit(p, arg);
    }

    @Override
    public Object visit(EAdd p, Object arg) {
        p.expr_1=simplify(p.expr_1);
        p.expr_2=simplify(p.expr_2);
        return super.visit(p, arg);
    }

    @Override
    public Object visit(ERel p, Object arg) {
        p.expr_1=simplify(p.expr_1);
        p.expr_2=simplify(p.expr_2);
        return super.visit(p, arg);
    }

    @Override
    public Object visit(EAnd p, Object arg) {
        p.expr_1=simplify(p.expr_1);
        p.expr_2=simplify(p.expr_2);
        return super.visit(p, arg);
    }
}
