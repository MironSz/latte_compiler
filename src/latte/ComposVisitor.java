package latte;
/** BNFC-Generated Composition Visitor
*/

public class ComposVisitor<A> implements
  latte.Absyn.Program.Visitor<latte.Absyn.Program,A>,
  latte.Absyn.TopDef.Visitor<latte.Absyn.TopDef,A>,
  latte.Absyn.Arg.Visitor<latte.Absyn.Arg,A>,
  latte.Absyn.Block.Visitor<latte.Absyn.Block,A>,
  latte.Absyn.Stmt.Visitor<latte.Absyn.Stmt,A>,
  latte.Absyn.Item.Visitor<latte.Absyn.Item,A>,
  latte.Absyn.Type.Visitor<latte.Absyn.Type,A>,
  latte.Absyn.Expr.Visitor<latte.Absyn.Expr,A>,
  latte.Absyn.AddOp.Visitor<latte.Absyn.AddOp,A>,
  latte.Absyn.MulOp.Visitor<latte.Absyn.MulOp,A>,
  latte.Absyn.RelOp.Visitor<latte.Absyn.RelOp,A>
{
    /* Program */
    public latte.Absyn.Program visit(latte.Absyn.ProgramCode p, A arg)
    {
      latte.Absyn.ListTopDef listtopdef_ = new latte.Absyn.ListTopDef();
      for (latte.Absyn.TopDef x : p.listtopdef_)
      {
        listtopdef_.add(x.accept(this,arg));
      }
      return new latte.Absyn.ProgramCode(listtopdef_);
    }

    /* TopDef */
    public latte.Absyn.TopDef visit(latte.Absyn.FnDef p, A arg)
    {
      latte.Absyn.Type type_ = p.type_.accept(this, arg);
      String ident_ = p.ident_;
      latte.Absyn.ListArg listarg_ = new latte.Absyn.ListArg();
      for (latte.Absyn.Arg x : p.listarg_)
      {
        listarg_.add(x.accept(this,arg));
      }
      latte.Absyn.Block block_ = p.block_.accept(this, arg);
      return new latte.Absyn.FnDef(type_, ident_, listarg_, block_);
    }

    /* Arg */
    public latte.Absyn.Arg visit(latte.Absyn.ArgCode p, A arg)
    {
      latte.Absyn.Type type_ = p.type_.accept(this, arg);
      String ident_ = p.ident_;
      return new latte.Absyn.ArgCode(type_, ident_);
    }

    /* Block */
    public latte.Absyn.Block visit(latte.Absyn.BlockCode p, A arg)
    {
      latte.Absyn.ListStmt liststmt_ = new latte.Absyn.ListStmt();
      for (latte.Absyn.Stmt x : p.liststmt_)
      {
        liststmt_.add(x.accept(this,arg));
      }
      return new latte.Absyn.BlockCode(liststmt_);
    }

    /* Stmt */
    public latte.Absyn.Stmt visit(latte.Absyn.Empty p, A arg)
    {
      return new latte.Absyn.Empty();
    }
    public latte.Absyn.Stmt visit(latte.Absyn.BStmt p, A arg)
    {
      latte.Absyn.Block block_ = p.block_.accept(this, arg);
      return new latte.Absyn.BStmt(block_);
    }
    public latte.Absyn.Stmt visit(latte.Absyn.Decl p, A arg)
    {
      latte.Absyn.Type type_ = p.type_.accept(this, arg);
      latte.Absyn.ListItem listitem_ = new latte.Absyn.ListItem();
      for (latte.Absyn.Item x : p.listitem_)
      {
        listitem_.add(x.accept(this,arg));
      }
      return new latte.Absyn.Decl(type_, listitem_);
    }
    public latte.Absyn.Stmt visit(latte.Absyn.Ass p, A arg)
    {
      String ident_ = p.ident_;
      latte.Absyn.Expr expr_ = p.expr_.accept(this, arg);
      return new latte.Absyn.Ass(ident_, expr_);
    }
    public latte.Absyn.Stmt visit(latte.Absyn.Incr p, A arg)
    {
      String ident_ = p.ident_;
      return new latte.Absyn.Incr(ident_);
    }
    public latte.Absyn.Stmt visit(latte.Absyn.Decr p, A arg)
    {
      String ident_ = p.ident_;
      return new latte.Absyn.Decr(ident_);
    }
    public latte.Absyn.Stmt visit(latte.Absyn.Ret p, A arg)
    {
      latte.Absyn.Expr expr_ = p.expr_.accept(this, arg);
      return new latte.Absyn.Ret(expr_);
    }
    public latte.Absyn.Stmt visit(latte.Absyn.VRet p, A arg)
    {
      return new latte.Absyn.VRet();
    }
    public latte.Absyn.Stmt visit(latte.Absyn.Cond p, A arg)
    {
      latte.Absyn.Expr expr_ = p.expr_.accept(this, arg);
      latte.Absyn.Stmt stmt_ = p.stmt_.accept(this, arg);
      return new latte.Absyn.Cond(expr_, stmt_);
    }
    public latte.Absyn.Stmt visit(latte.Absyn.CondElse p, A arg)
    {
      latte.Absyn.Expr expr_ = p.expr_.accept(this, arg);
      latte.Absyn.Stmt stmt_1 = p.stmt_1.accept(this, arg);
      latte.Absyn.Stmt stmt_2 = p.stmt_2.accept(this, arg);
      return new latte.Absyn.CondElse(expr_, stmt_1, stmt_2);
    }
    public latte.Absyn.Stmt visit(latte.Absyn.While p, A arg)
    {
      latte.Absyn.Expr expr_ = p.expr_.accept(this, arg);
      latte.Absyn.Stmt stmt_ = p.stmt_.accept(this, arg);
      return new latte.Absyn.While(expr_, stmt_);
    }
    public latte.Absyn.Stmt visit(latte.Absyn.SExp p, A arg)
    {
      latte.Absyn.Expr expr_ = p.expr_.accept(this, arg);
      return new latte.Absyn.SExp(expr_);
    }

    /* Item */
    public latte.Absyn.Item visit(latte.Absyn.NoInit p, A arg)
    {
      String ident_ = p.ident_;
      return new latte.Absyn.NoInit(ident_);
    }
    public latte.Absyn.Item visit(latte.Absyn.Init p, A arg)
    {
      String ident_ = p.ident_;
      latte.Absyn.Expr expr_ = p.expr_.accept(this, arg);
      return new latte.Absyn.Init(ident_, expr_);
    }

    /* Type */
    public latte.Absyn.Type visit(latte.Absyn.Int p, A arg)
    {
      return new latte.Absyn.Int();
    }
    public latte.Absyn.Type visit(latte.Absyn.Str p, A arg)
    {
      return new latte.Absyn.Str();
    }
    public latte.Absyn.Type visit(latte.Absyn.Bool p, A arg)
    {
      return new latte.Absyn.Bool();
    }
    public latte.Absyn.Type visit(latte.Absyn.Void p, A arg)
    {
      return new latte.Absyn.Void();
    }
    public latte.Absyn.Type visit(latte.Absyn.Fun p, A arg)
    {
      latte.Absyn.Type type_ = p.type_.accept(this, arg);
      latte.Absyn.ListType listtype_ = new latte.Absyn.ListType();
      for (latte.Absyn.Type x : p.listtype_)
      {
        listtype_.add(x.accept(this,arg));
      }
      return new latte.Absyn.Fun(type_, listtype_);
    }

    /* Expr */
    public latte.Absyn.Expr visit(latte.Absyn.EVar p, A arg)
    {
      String ident_ = p.ident_;
      return new latte.Absyn.EVar(ident_);
    }
    public latte.Absyn.Expr visit(latte.Absyn.ELitInt p, A arg)
    {
      Integer integer_ = p.integer_;
      return new latte.Absyn.ELitInt(integer_);
    }
    public latte.Absyn.Expr visit(latte.Absyn.ELitTrue p, A arg)
    {
      return new latte.Absyn.ELitTrue();
    }
    public latte.Absyn.Expr visit(latte.Absyn.ELitFalse p, A arg)
    {
      return new latte.Absyn.ELitFalse();
    }
    public latte.Absyn.Expr visit(latte.Absyn.EApp p, A arg)
    {
      String ident_ = p.ident_;
      latte.Absyn.ListExpr listexpr_ = new latte.Absyn.ListExpr();
      for (latte.Absyn.Expr x : p.listexpr_)
      {
        listexpr_.add(x.accept(this,arg));
      }
      return new latte.Absyn.EApp(ident_, listexpr_);
    }
    public latte.Absyn.Expr visit(latte.Absyn.EString p, A arg)
    {
      String string_ = p.string_;
      return new latte.Absyn.EString(string_);
    }
    public latte.Absyn.Expr visit(latte.Absyn.Neg p, A arg)
    {
      latte.Absyn.Expr expr_ = p.expr_.accept(this, arg);
      return new latte.Absyn.Neg(expr_);
    }
    public latte.Absyn.Expr visit(latte.Absyn.Not p, A arg)
    {
      latte.Absyn.Expr expr_ = p.expr_.accept(this, arg);
      return new latte.Absyn.Not(expr_);
    }
    public latte.Absyn.Expr visit(latte.Absyn.EMul p, A arg)
    {
      latte.Absyn.Expr expr_1 = p.expr_1.accept(this, arg);
      latte.Absyn.MulOp mulop_ = p.mulop_.accept(this, arg);
      latte.Absyn.Expr expr_2 = p.expr_2.accept(this, arg);
      return new latte.Absyn.EMul(expr_1, mulop_, expr_2);
    }
    public latte.Absyn.Expr visit(latte.Absyn.EAdd p, A arg)
    {
      latte.Absyn.Expr expr_1 = p.expr_1.accept(this, arg);
      latte.Absyn.AddOp addop_ = p.addop_.accept(this, arg);
      latte.Absyn.Expr expr_2 = p.expr_2.accept(this, arg);
      return new latte.Absyn.EAdd(expr_1, addop_, expr_2);
    }
    public latte.Absyn.Expr visit(latte.Absyn.ERel p, A arg)
    {
      latte.Absyn.Expr expr_1 = p.expr_1.accept(this, arg);
      latte.Absyn.RelOp relop_ = p.relop_.accept(this, arg);
      latte.Absyn.Expr expr_2 = p.expr_2.accept(this, arg);
      return new latte.Absyn.ERel(expr_1, relop_, expr_2);
    }
    public latte.Absyn.Expr visit(latte.Absyn.EAnd p, A arg)
    {
      latte.Absyn.Expr expr_1 = p.expr_1.accept(this, arg);
      latte.Absyn.Expr expr_2 = p.expr_2.accept(this, arg);
      return new latte.Absyn.EAnd(expr_1, expr_2);
    }
    public latte.Absyn.Expr visit(latte.Absyn.EOr p, A arg)
    {
      latte.Absyn.Expr expr_1 = p.expr_1.accept(this, arg);
      latte.Absyn.Expr expr_2 = p.expr_2.accept(this, arg);
      return new latte.Absyn.EOr(expr_1, expr_2);
    }

    /* AddOp */
    public latte.Absyn.AddOp visit(latte.Absyn.Plus p, A arg)
    {
      return new latte.Absyn.Plus();
    }
    public latte.Absyn.AddOp visit(latte.Absyn.Minus p, A arg)
    {
      return new latte.Absyn.Minus();
    }

    /* MulOp */
    public latte.Absyn.MulOp visit(latte.Absyn.Times p, A arg)
    {
      return new latte.Absyn.Times();
    }
    public latte.Absyn.MulOp visit(latte.Absyn.Div p, A arg)
    {
      return new latte.Absyn.Div();
    }
    public latte.Absyn.MulOp visit(latte.Absyn.Mod p, A arg)
    {
      return new latte.Absyn.Mod();
    }

    /* RelOp */
    public latte.Absyn.RelOp visit(latte.Absyn.LTH p, A arg)
    {
      return new latte.Absyn.LTH();
    }
    public latte.Absyn.RelOp visit(latte.Absyn.LE p, A arg)
    {
      return new latte.Absyn.LE();
    }
    public latte.Absyn.RelOp visit(latte.Absyn.GTH p, A arg)
    {
      return new latte.Absyn.GTH();
    }
    public latte.Absyn.RelOp visit(latte.Absyn.GE p, A arg)
    {
      return new latte.Absyn.GE();
    }
    public latte.Absyn.RelOp visit(latte.Absyn.EQU p, A arg)
    {
      return new latte.Absyn.EQU();
    }
    public latte.Absyn.RelOp visit(latte.Absyn.NE p, A arg)
    {
      return new latte.Absyn.NE();
    }
}
