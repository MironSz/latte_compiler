package frontend;

import latte.Absyn.*;
import latte.FoldVisitor;

public class RemoveDuplicatesVisitor extends FoldVisitor<RemoveDuplicatesContext, RemoveDuplicatesContext> {
    @Override
    public RemoveDuplicatesContext leaf(RemoveDuplicatesContext arg) {
        return arg;
    }

    @Override
    public RemoveDuplicatesContext combine(RemoveDuplicatesContext x, RemoveDuplicatesContext y, RemoveDuplicatesContext arg) {
        return null;
    }

    @Override
    public RemoveDuplicatesContext visit(ArgCode p, RemoveDuplicatesContext arg) {
        arg.createNewVar(p.ident_);
        p.ident_=arg.getName(p.ident_);
        return arg;
    }

    @Override
    public RemoveDuplicatesContext visit(Init p, RemoveDuplicatesContext arg) {
        p.expr_.accept(this, arg);
        arg.createNewVar(p.ident_);
        p.ident_ = arg.getName(p.ident_);

        return arg;
    }

    @Override
    public RemoveDuplicatesContext visit(NoInit p, RemoveDuplicatesContext arg) {
        arg.createNewVar(p.ident_);
        p.ident_ = arg.getName(p.ident_);

        return arg;
    }

    @Override
    public RemoveDuplicatesContext visit(Cond p, RemoveDuplicatesContext arg) {
        arg = arg.newScope();
        p.expr_.accept(this, arg);
        p.stmt_.accept(this, arg);

        return arg;
    }

    @Override
    public RemoveDuplicatesContext visit(While p, RemoveDuplicatesContext arg) {
        arg = arg.newScope();
        p.expr_.accept(this, arg);
        p.stmt_.accept(this, arg);

        return arg;
    }

    @Override
    public RemoveDuplicatesContext visit(Ass p, RemoveDuplicatesContext arg) {
        p.ident_ = arg.getName(p.ident_);
        p.expr_.accept(this, arg);
        return arg;
    }

    @Override
    public RemoveDuplicatesContext visit(FnDef p, RemoveDuplicatesContext arg) {
        arg=arg.newScope();
        return super.visit(p, arg);
    }

    @Override
    public RemoveDuplicatesContext visit(BlockCode p, RemoveDuplicatesContext arg) {
        arg=arg.newScope();
        return super.visit(p, arg);
    }



    @Override
    public RemoveDuplicatesContext visit(Incr p, RemoveDuplicatesContext arg) {
        p.ident_=arg.getName(p.ident_);
        return super.visit(p, arg);
    }

    @Override
    public RemoveDuplicatesContext visit(Decr p, RemoveDuplicatesContext arg) {
        p.ident_=arg.getName(p.ident_);
        return super.visit(p, arg);
    }

    @Override
    public RemoveDuplicatesContext visit(CondElse p, RemoveDuplicatesContext arg) {
        p.expr_.accept(this, arg);
        p.stmt_1.accept(this, arg.newScope());
        p.stmt_2.accept(this, arg.newScope());
        return arg;
    }

    @Override
    public RemoveDuplicatesContext visit(EVar p, RemoveDuplicatesContext arg) {
        p.ident_=arg.getName(p.ident_);
        return super.visit(p, arg);
    }
}
