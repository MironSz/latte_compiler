package latte;

import latte.Absyn.*;
import latte.Absyn.Void;

/** BNFC-Generated Fold Visitor */
public abstract class FoldVisitor<R,A> implements AllVisitor<R,A> {
    public abstract R leaf(A arg);
    public abstract R combine(R x, R y, A arg);

/* Program */
    public R visit(ProgramCode p, A arg) {
      R r = leaf(arg);
      for (TopDef x : p.listtopdef_)
      {
        r = combine(x.accept(this, arg), r, arg);
      }
      return r;
    }

/* TopDef */
    public R visit(FnDef p, A arg) {
      R r = leaf(arg);
      r = combine(p.type_.accept(this, arg), r, arg);
      for (Arg x : p.listarg_)
      {
        r = combine(x.accept(this, arg), r, arg);
      }
      r = combine(p.block_.accept(this, arg), r, arg);
      return r;
    }

/* Arg */
    public R visit(ArgCode p, A arg) {
      R r = leaf(arg);
      r = combine(p.type_.accept(this, arg), r, arg);
      return r;
    }

/* Block */
    public R visit(BlockCode p, A arg) {
      R r = leaf(arg);
      for (Stmt x : p.liststmt_)
      {
        r = combine(x.accept(this, arg), r, arg);
      }
      return r;
    }

/* Stmt */
    public R visit(Empty p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(BStmt p, A arg) {
      R r = leaf(arg);
      r = combine(p.block_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(Decl p, A arg) {
      R r = leaf(arg);
      r = combine(p.type_.accept(this, arg), r, arg);
      for (Item x : p.listitem_)
      {
        r = combine(x.accept(this, arg), r, arg);
      }
      return r;
    }
    public R visit(Ass p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(Incr p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(Decr p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(Ret p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(VRet p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(Cond p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_.accept(this, arg), r, arg);
      r = combine(p.stmt_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(CondElse p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_.accept(this, arg), r, arg);
      r = combine(p.stmt_1.accept(this, arg), r, arg);
      r = combine(p.stmt_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(While p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_.accept(this, arg), r, arg);
      r = combine(p.stmt_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(SExp p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_.accept(this, arg), r, arg);
      return r;
    }

/* Item */
    public R visit(NoInit p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(Init p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_.accept(this, arg), r, arg);
      return r;
    }

/* Type */
    public R visit(Int p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(Str p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(Bool p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(Void p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(Fun p, A arg) {
      R r = leaf(arg);
      r = combine(p.type_.accept(this, arg), r, arg);
      for (Type x : p.listtype_)
      {
        r = combine(x.accept(this, arg), r, arg);
      }
      return r;
    }

/* Expr */
    public R visit(EVar p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(ELitInt p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(ELitTrue p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(ELitFalse p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(EApp p, A arg) {
      R r = leaf(arg);
      for (Expr x : p.listexpr_)
      {
        r = combine(x.accept(this, arg), r, arg);
      }
      return r;
    }
    public R visit(EString p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(Neg p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(Not p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(EMul p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_1.accept(this, arg), r, arg);
      r = combine(p.mulop_.accept(this, arg), r, arg);
      r = combine(p.expr_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(EAdd p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_1.accept(this, arg), r, arg);
      r = combine(p.addop_.accept(this, arg), r, arg);
      r = combine(p.expr_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(ERel p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_1.accept(this, arg), r, arg);
      r = combine(p.relop_.accept(this, arg), r, arg);
      r = combine(p.expr_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(EAnd p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_1.accept(this, arg), r, arg);
      r = combine(p.expr_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(EOr p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_1.accept(this, arg), r, arg);
      r = combine(p.expr_2.accept(this, arg), r, arg);
      return r;
    }

/* AddOp */
    public R visit(Plus p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(Minus p, A arg) {
      R r = leaf(arg);
      return r;
    }

/* MulOp */
    public R visit(Times p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(Div p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(Mod p, A arg) {
      R r = leaf(arg);
      return r;
    }

/* RelOp */
    public R visit(LTH p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(LE p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(GTH p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(GE p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(EQU p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(NE p, A arg) {
      R r = leaf(arg);
      return r;
    }


}
