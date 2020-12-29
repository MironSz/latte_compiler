package frontend;

import latte.Absyn.Void;
import latte.Absyn.*;
import latte.FoldVisitor;

public class ReturnVisitor extends FoldVisitor<Boolean, Boolean> {
    @Override
    public Boolean leaf(Boolean arg) {
        return false;
    }

    @Override
    public Boolean visit(ProgramCode p, Boolean arg) {
        long mainCount = p.listtopdef_.stream()
                .filter(topDef -> topDef instanceof FnDef)
                .map(topDef -> (FnDef) topDef)
                .filter(topDef -> topDef.ident_.equals("main"))
                .count();
        if (mainCount == 0)
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Main definition not found"));
        if (mainCount > 1)
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Multiple  definition of main"));

        return super.visit(p, arg);
    }

    @Override
    public Boolean visit(FnDef p, Boolean arg) {
        if (!(p.type_ instanceof Void) && !super.visit(p, arg))
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num,
                    "Function " + p.ident_ + " doesn't end with \"return\" statement"));
        return true;

    }

    @Override
    public Boolean combine(Boolean x, Boolean y, Boolean arg) {
        return x || y;
    }

    @Override
    public Boolean visit(Ret p, Boolean arg) {
        return true;
    }

    @Override
    public Boolean visit(VRet p, Boolean arg) {
        return true;
    }

    @Override
    public Boolean visit(Cond p, Boolean arg) {
        if (p.expr_ instanceof ELitTrue)
            return p.stmt_.accept(this, arg);
        return false;
    }

    @Override
    public Boolean visit(CondElse p, Boolean arg) {
        if (p.expr_ instanceof ELitTrue)
            return p.stmt_1.accept(this, arg);

        if (p.expr_ instanceof ELitFalse)
            return p.stmt_2.accept(this, arg);

        return p.stmt_1.accept(this, arg) && p.stmt_2.accept(this, arg);
    }

    @Override
    public Boolean visit(While p, Boolean arg) {
        if (p.expr_ instanceof ELitTrue)
            return p.stmt_.accept(this, arg);

        return false;
    }
}
