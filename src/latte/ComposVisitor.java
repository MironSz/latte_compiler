package latte;

import latte.Absyn.*;
import latte.Absyn.Void;


/** BNFC-Generated Composition Visitor
*/

public class ComposVisitor<A> implements
  Program.Visitor<Program,A>,
  TopDef.Visitor<TopDef,A>,
  Arg.Visitor<Arg,A>,
  Block.Visitor<Block,A>,
  Stmt.Visitor<Stmt,A>,
  Item.Visitor<Item,A>,
  Type.Visitor<Type,A>,
  Expr.Visitor<Expr,A>,
  AddOp.Visitor<AddOp,A>,
  MulOp.Visitor<MulOp,A>,
  RelOp.Visitor<RelOp,A>
{
    /* Program */
    public Program visit(ProgramCode p, A arg)
    {
      ListTopDef listtopdef_ = new ListTopDef();
      for (TopDef x : p.listtopdef_)
      {
        listtopdef_.add(x.accept(this,arg));
      }
      return new ProgramCode(listtopdef_);
    }

    /* TopDef */
    public TopDef visit(FnDef p, A arg)
    {
      Type type_ = p.type_.accept(this, arg);
      String ident_ = p.ident_;
      ListArg listarg_ = new ListArg();
      for (Arg x : p.listarg_)
      {
        listarg_.add(x.accept(this,arg));
      }
      Block block_ = p.block_.accept(this, arg);
      return new FnDef(type_, ident_, listarg_, block_);
    }

    /* Arg */
    public Arg visit(ArgCode p, A arg)
    {
      Type type_ = p.type_.accept(this, arg);
      String ident_ = p.ident_;
      return new ArgCode(type_, ident_);
    }

    /* Block */
    public Block visit(BlockCode p, A arg)
    {
      ListStmt liststmt_ = new ListStmt();
      for (Stmt x : p.liststmt_)
      {
        liststmt_.add(x.accept(this,arg));
      }
      return new BlockCode(liststmt_);
    }

    /* Stmt */
    public Stmt visit(Empty p, A arg)
    {
      return new Empty();
    }
    public Stmt visit(BStmt p, A arg)
    {
      Block block_ = p.block_.accept(this, arg);
      return new BStmt(block_);
    }
    public Stmt visit(Decl p, A arg)
    {
      Type type_ = p.type_.accept(this, arg);
      ListItem listitem_ = new ListItem();
      for (Item x : p.listitem_)
      {
        listitem_.add(x.accept(this,arg));
      }
      return new Decl(type_, listitem_);
    }
    public Stmt visit(Ass p, A arg)
    {
      String ident_ = p.ident_;
      Expr expr_ = p.expr_.accept(this, arg);
      return new Ass(ident_, expr_);
    }
    public Stmt visit(Incr p, A arg)
    {
      String ident_ = p.ident_;
      return new Incr(ident_);
    }
    public Stmt visit(Decr p, A arg)
    {
      String ident_ = p.ident_;
      return new Decr(ident_);
    }
    public Stmt visit(Ret p, A arg)
    {
      Expr expr_ = p.expr_.accept(this, arg);
      return new Ret(expr_);
    }
    public Stmt visit(VRet p, A arg)
    {
      return new VRet();
    }
    public Stmt visit(Cond p, A arg)
    {
      Expr expr_ = p.expr_.accept(this, arg);
      Stmt stmt_ = p.stmt_.accept(this, arg);
      return new Cond(expr_, stmt_);
    }
    public Stmt visit(CondElse p, A arg)
    {
      Expr expr_ = p.expr_.accept(this, arg);
      Stmt stmt_1 = p.stmt_1.accept(this, arg);
      Stmt stmt_2 = p.stmt_2.accept(this, arg);
      return new CondElse(expr_, stmt_1, stmt_2);
    }
    public Stmt visit(While p, A arg)
    {
      Expr expr_ = p.expr_.accept(this, arg);
      Stmt stmt_ = p.stmt_.accept(this, arg);
      return new While(expr_, stmt_);
    }
    public Stmt visit(SExp p, A arg)
    {
      Expr expr_ = p.expr_.accept(this, arg);
      return new SExp(expr_);
    }

    /* Item */
    public Item visit(NoInit p, A arg)
    {
      String ident_ = p.ident_;
      return new NoInit(ident_);
    }
    public Item visit(Init p, A arg)
    {
      String ident_ = p.ident_;
      Expr expr_ = p.expr_.accept(this, arg);
      return new Init(ident_, expr_);
    }

    /* Type */
    public Type visit(Int p, A arg)
    {
      return new Int();
    }
    public Type visit(Str p, A arg)
    {
      return new Str();
    }
    public Type visit(Bool p, A arg)
    {
      return new Bool();
    }
    public Type visit(Void p, A arg)
    {
      return new Void();
    }
    public Type visit(Fun p, A arg)
    {
      Type type_ = p.type_.accept(this, arg);
      ListType listtype_ = new ListType();
      for (Type x : p.listtype_)
      {
        listtype_.add(x.accept(this,arg));
      }
      return new Fun(type_, listtype_);
    }

    /* Expr */
    public Expr visit(EVar p, A arg)
    {
      String ident_ = p.ident_;
      return new EVar(ident_);
    }
    public Expr visit(ELitInt p, A arg)
    {
      Integer integer_ = p.integer_;
      return new ELitInt(integer_);
    }
    public Expr visit(ELitTrue p, A arg)
    {
      return new ELitTrue();
    }
    public Expr visit(ELitFalse p, A arg)
    {
      return new ELitFalse();
    }
    public Expr visit(EApp p, A arg)
    {
      String ident_ = p.ident_;
      ListExpr listexpr_ = new ListExpr();
      for (Expr x : p.listexpr_)
      {
        listexpr_.add(x.accept(this,arg));
      }
      return new EApp(ident_, listexpr_);
    }
    public Expr visit(EString p, A arg)
    {
      String string_ = p.string_;
      return new EString(string_);
    }
    public Expr visit(Neg p, A arg)
    {
      Expr expr_ = p.expr_.accept(this, arg);
      return new Neg(expr_);
    }
    public Expr visit(Not p, A arg)
    {
      Expr expr_ = p.expr_.accept(this, arg);
      return new Not(expr_);
    }
    public Expr visit(EMul p, A arg)
    {
      Expr expr_1 = p.expr_1.accept(this, arg);
      MulOp mulop_ = p.mulop_.accept(this, arg);
      Expr expr_2 = p.expr_2.accept(this, arg);
      return new EMul(expr_1, mulop_, expr_2);
    }
    public Expr visit(EAdd p, A arg)
    {
      Expr expr_1 = p.expr_1.accept(this, arg);
      AddOp addop_ = p.addop_.accept(this, arg);
      Expr expr_2 = p.expr_2.accept(this, arg);
      return new EAdd(expr_1, addop_, expr_2);
    }
    public Expr visit(ERel p, A arg)
    {
      Expr expr_1 = p.expr_1.accept(this, arg);
      RelOp relop_ = p.relop_.accept(this, arg);
      Expr expr_2 = p.expr_2.accept(this, arg);
      return new ERel(expr_1, relop_, expr_2);
    }
    public Expr visit(EAnd p, A arg)
    {
      Expr expr_1 = p.expr_1.accept(this, arg);
      Expr expr_2 = p.expr_2.accept(this, arg);
      return new EAnd(expr_1, expr_2);
    }
    public Expr visit(EOr p, A arg)
    {
      Expr expr_1 = p.expr_1.accept(this, arg);
      Expr expr_2 = p.expr_2.accept(this, arg);
      return new EOr(expr_1, expr_2);
    }

    /* AddOp */
    public AddOp visit(Plus p, A arg)
    {
      return new Plus();
    }
    public AddOp visit(Minus p, A arg)
    {
      return new Minus();
    }

    /* MulOp */
    public MulOp visit(Times p, A arg)
    {
      return new Times();
    }
    public MulOp visit(Div p, A arg)
    {
      return new Div();
    }
    public MulOp visit(Mod p, A arg)
    {
      return new Mod();
    }

    /* RelOp */
    public RelOp visit(LTH p, A arg)
    {
      return new LTH();
    }
    public RelOp visit(LE p, A arg)
    {
      return new LE();
    }
    public RelOp visit(GTH p, A arg)
    {
      return new GTH();
    }
    public RelOp visit(GE p, A arg)
    {
      return new GE();
    }
    public RelOp visit(EQU p, A arg)
    {
      return new EQU();
    }
    public RelOp visit(NE p, A arg)
    {
      return new NE();
    }
}
