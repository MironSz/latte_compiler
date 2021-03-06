package latte;

/** BNFC-Generated Fold Visitor */
public abstract class FoldVisitor<R,A> implements AllVisitor<R,A> {
    public abstract R leaf(A arg);
    public abstract R combine(R x, R y, A arg);

/* Program */
    public R visit(latte.Absyn.ProgramCode p, A arg) {
      R r = leaf(arg);
      for (latte.Absyn.TopDef x : p.listtopdef_)
      {
        r = combine(x.accept(this, arg), r, arg);
      }
      return r;
    }

/* TopDef */
    public R visit(latte.Absyn.FnDef p, A arg) {
      R r = leaf(arg);
      r = combine(p.fundef_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(latte.Absyn.ClassDef p, A arg) {
      R r = leaf(arg);
      r = combine(p.class_.accept(this, arg), r, arg);
      return r;
    }

/* FunDef */
    public R visit(latte.Absyn.FunDefCode p, A arg) {
      R r = leaf(arg);
      r = combine(p.type_.accept(this, arg), r, arg);
      for (latte.Absyn.Arg x : p.listarg_)
      {
        r = combine(x.accept(this, arg), r, arg);
      }
      r = combine(p.block_.accept(this, arg), r, arg);
      return r;
    }

/* Arg */
    public R visit(latte.Absyn.ArgCode p, A arg) {
      R r = leaf(arg);
      r = combine(p.type_.accept(this, arg), r, arg);
      return r;
    }

/* Class */
    public R visit(latte.Absyn.ClassCode p, A arg) {
      R r = leaf(arg);
      for (latte.Absyn.ClassStmt x : p.listclassstmt_)
      {
        r = combine(x.accept(this, arg), r, arg);
      }
      return r;
    }
    public R visit(latte.Absyn.ClassExt p, A arg) {
      R r = leaf(arg);
      for (latte.Absyn.ClassStmt x : p.listclassstmt_)
      {
        r = combine(x.accept(this, arg), r, arg);
      }
      return r;
    }

/* ClassStmt */
    public R visit(latte.Absyn.Fields p, A arg) {
      R r = leaf(arg);
      r = combine(p.type_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(latte.Absyn.Method p, A arg) {
      R r = leaf(arg);
      r = combine(p.fundef_.accept(this, arg), r, arg);
      return r;
    }

/* Target */
    public R visit(latte.Absyn.Variable p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.FieldT p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_.accept(this, arg), r, arg);
      return r;
    }

/* Block */
    public R visit(latte.Absyn.BlockCode p, A arg) {
      R r = leaf(arg);
      for (latte.Absyn.Stmt x : p.liststmt_)
      {
        r = combine(x.accept(this, arg), r, arg);
      }
      return r;
    }

/* Stmt */
    public R visit(latte.Absyn.Empty p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.BStmt p, A arg) {
      R r = leaf(arg);
      r = combine(p.block_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(latte.Absyn.Decl p, A arg) {
      R r = leaf(arg);
      r = combine(p.type_.accept(this, arg), r, arg);
      for (latte.Absyn.Item x : p.listitem_)
      {
        r = combine(x.accept(this, arg), r, arg);
      }
      return r;
    }
    public R visit(latte.Absyn.Ass p, A arg) {
      R r = leaf(arg);
      r = combine(p.target_.accept(this, arg), r, arg);
      r = combine(p.expr_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(latte.Absyn.Incr p, A arg) {
      R r = leaf(arg);
      r = combine(p.target_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(latte.Absyn.Decr p, A arg) {
      R r = leaf(arg);
      r = combine(p.target_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(latte.Absyn.Ret p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(latte.Absyn.VRet p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.Cond p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_.accept(this, arg), r, arg);
      r = combine(p.stmt_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(latte.Absyn.CondElse p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_.accept(this, arg), r, arg);
      r = combine(p.stmt_1.accept(this, arg), r, arg);
      r = combine(p.stmt_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(latte.Absyn.While p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_.accept(this, arg), r, arg);
      r = combine(p.stmt_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(latte.Absyn.SExp p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_.accept(this, arg), r, arg);
      return r;
    }

/* Item */
    public R visit(latte.Absyn.NoInit p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.Init p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_.accept(this, arg), r, arg);
      return r;
    }

/* Type */
    public R visit(latte.Absyn.Int p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.Str p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.Bool p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.Void p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.ClassType p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.Fun p, A arg) {
      R r = leaf(arg);
      r = combine(p.type_.accept(this, arg), r, arg);
      for (latte.Absyn.Type x : p.listtype_)
      {
        r = combine(x.accept(this, arg), r, arg);
      }
      return r;
    }

/* Expr */
    public R visit(latte.Absyn.EVar p, A arg) {
      R r = leaf(arg);
      r = combine(p.target_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(latte.Absyn.ECast p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(latte.Absyn.ENewObj p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.ELitInt p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.ELitTrue p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.ELitFalse p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.EApp p, A arg) {
      R r = leaf(arg);
      r = combine(p.target_.accept(this, arg), r, arg);
      for (latte.Absyn.Expr x : p.listexpr_)
      {
        r = combine(x.accept(this, arg), r, arg);
      }
      return r;
    }
    public R visit(latte.Absyn.EString p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.ENull p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.Neg p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(latte.Absyn.Not p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_.accept(this, arg), r, arg);
      return r;
    }
    public R visit(latte.Absyn.EMul p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_1.accept(this, arg), r, arg);
      r = combine(p.mulop_.accept(this, arg), r, arg);
      r = combine(p.expr_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(latte.Absyn.EAdd p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_1.accept(this, arg), r, arg);
      r = combine(p.addop_.accept(this, arg), r, arg);
      r = combine(p.expr_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(latte.Absyn.ERel p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_1.accept(this, arg), r, arg);
      r = combine(p.relop_.accept(this, arg), r, arg);
      r = combine(p.expr_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(latte.Absyn.EAnd p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_1.accept(this, arg), r, arg);
      r = combine(p.expr_2.accept(this, arg), r, arg);
      return r;
    }
    public R visit(latte.Absyn.EOr p, A arg) {
      R r = leaf(arg);
      r = combine(p.expr_1.accept(this, arg), r, arg);
      r = combine(p.expr_2.accept(this, arg), r, arg);
      return r;
    }

/* AddOp */
    public R visit(latte.Absyn.Plus p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.Minus p, A arg) {
      R r = leaf(arg);
      return r;
    }

/* MulOp */
    public R visit(latte.Absyn.Times p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.Div p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.Mod p, A arg) {
      R r = leaf(arg);
      return r;
    }

/* RelOp */
    public R visit(latte.Absyn.LTH p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.LE p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.GTH p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.GE p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.EQU p, A arg) {
      R r = leaf(arg);
      return r;
    }
    public R visit(latte.Absyn.NE p, A arg) {
      R r = leaf(arg);
      return r;
    }


}
