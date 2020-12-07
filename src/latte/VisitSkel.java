package latte;

import latte.Absyn.*;
import latte.Absyn.Void;


/*** BNFC-Generated Visitor Design Pattern Skeleton. ***/

/* This implements the common visitor design pattern.
   Tests show it to be slightly less efficient than the
   instanceof method, but easier to use.
   Replace the R and A parameters with the desired return
   and context types.*/

public class VisitSkel
{
  public class ProgramVisitor<R,A> implements Program.Visitor<R,A>
  {
    public R visit(ProgramCode p, A arg)
    { /* Code for ProgramCode goes here */
      for (TopDef x: p.listtopdef_) {
        x.accept(new TopDefVisitor<R,A>(), arg);
      }
      return null;
    }
  }
  public class TopDefVisitor<R,A> implements TopDef.Visitor<R,A>
  {
    public R visit(FnDef p, A arg)
    { /* Code for FnDef goes here */
      p.type_.accept(new TypeVisitor<R,A>(), arg);
      //p.ident_;
      for (Arg x: p.listarg_) {
        x.accept(new ArgVisitor<R,A>(), arg);
      }
      p.block_.accept(new BlockVisitor<R,A>(), arg);
      return null;
    }
  }
  public class ArgVisitor<R,A> implements Arg.Visitor<R,A>
  {
    public R visit(ArgCode p, A arg)
    { /* Code for ArgCode goes here */
      p.type_.accept(new TypeVisitor<R,A>(), arg);
      //p.ident_;
      return null;
    }
  }
  public class BlockVisitor<R,A> implements Block.Visitor<R,A>
  {
    public R visit(BlockCode p, A arg)
    { /* Code for BlockCode goes here */
      for (Stmt x: p.liststmt_) {
        x.accept(new StmtVisitor<R,A>(), arg);
      }
      return null;
    }
  }
  public class StmtVisitor<R,A> implements Stmt.Visitor<R,A>
  {
    public R visit(Empty p, A arg)
    { /* Code for Empty goes here */
      return null;
    }
    public R visit(BStmt p, A arg)
    { /* Code for BStmt goes here */
      p.block_.accept(new BlockVisitor<R,A>(), arg);
      return null;
    }
    public R visit(Decl p, A arg)
    { /* Code for Decl goes here */
      p.type_.accept(new TypeVisitor<R,A>(), arg);
      for (Item x: p.listitem_) {
        x.accept(new ItemVisitor<R,A>(), arg);
      }
      return null;
    }
    public R visit(Ass p, A arg)
    { /* Code for Ass goes here */
      //p.ident_;
      p.expr_.accept(new ExprVisitor<R,A>(), arg);
      return null;
    }
    public R visit(Incr p, A arg)
    { /* Code for Incr goes here */
      //p.ident_;
      return null;
    }
    public R visit(Decr p, A arg)
    { /* Code for Decr goes here */
      //p.ident_;
      return null;
    }
    public R visit(Ret p, A arg)
    { /* Code for Ret goes here */
      p.expr_.accept(new ExprVisitor<R,A>(), arg);
      return null;
    }
    public R visit(VRet p, A arg)
    { /* Code for VRet goes here */
      return null;
    }
    public R visit(Cond p, A arg)
    { /* Code for Cond goes here */
      p.expr_.accept(new ExprVisitor<R,A>(), arg);
      p.stmt_.accept(new StmtVisitor<R,A>(), arg);
      return null;
    }
    public R visit(CondElse p, A arg)
    { /* Code for CondElse goes here */
      p.expr_.accept(new ExprVisitor<R,A>(), arg);
      p.stmt_1.accept(new StmtVisitor<R,A>(), arg);
      p.stmt_2.accept(new StmtVisitor<R,A>(), arg);
      return null;
    }
    public R visit(While p, A arg)
    { /* Code for While goes here */
      p.expr_.accept(new ExprVisitor<R,A>(), arg);
      p.stmt_.accept(new StmtVisitor<R,A>(), arg);
      return null;
    }
    public R visit(SExp p, A arg)
    { /* Code for SExp goes here */
      p.expr_.accept(new ExprVisitor<R,A>(), arg);
      return null;
    }
  }
  public class ItemVisitor<R,A> implements Item.Visitor<R,A>
  {
    public R visit(NoInit p, A arg)
    { /* Code for NoInit goes here */
      //p.ident_;
      return null;
    }
    public R visit(Init p, A arg)
    { /* Code for Init goes here */
      //p.ident_;
      p.expr_.accept(new ExprVisitor<R,A>(), arg);
      return null;
    }
  }
  public class TypeVisitor<R,A> implements Type.Visitor<R,A>
  {
    public R visit(Int p, A arg)
    { /* Code for Int goes here */
      return null;
    }
    public R visit(Str p, A arg)
    { /* Code for Str goes here */
      return null;
    }
    public R visit(Bool p, A arg)
    { /* Code for Bool goes here */
      return null;
    }
    public R visit(Void p, A arg)
    { /* Code for Void goes here */
      return null;
    }
    public R visit(Fun p, A arg)
    { /* Code for Fun goes here */
      p.type_.accept(new TypeVisitor<R,A>(), arg);
      for (Type x: p.listtype_) {
        x.accept(new TypeVisitor<R,A>(), arg);
      }
      return null;
    }
  }
  public class ExprVisitor<R,A> implements Expr.Visitor<R,A>
  {
    public R visit(EVar p, A arg)
    { /* Code for EVar goes here */
      //p.ident_;
      return null;
    }
    public R visit(ELitInt p, A arg)
    { /* Code for ELitInt goes here */
      //p.integer_;
      return null;
    }
    public R visit(ELitTrue p, A arg)
    { /* Code for ELitTrue goes here */
      return null;
    }
    public R visit(ELitFalse p, A arg)
    { /* Code for ELitFalse goes here */
      return null;
    }
    public R visit(EApp p, A arg)
    { /* Code for EApp goes here */
      //p.ident_;
      for (Expr x: p.listexpr_) {
        x.accept(new ExprVisitor<R,A>(), arg);
      }
      return null;
    }
    public R visit(EString p, A arg)
    { /* Code for EString goes here */
      //p.string_;
      return null;
    }
    public R visit(Neg p, A arg)
    { /* Code for Neg goes here */
      p.expr_.accept(new ExprVisitor<R,A>(), arg);
      return null;
    }
    public R visit(Not p, A arg)
    { /* Code for Not goes here */
      p.expr_.accept(new ExprVisitor<R,A>(), arg);
      return null;
    }
    public R visit(EMul p, A arg)
    { /* Code for EMul goes here */
      p.expr_1.accept(new ExprVisitor<R,A>(), arg);
      p.mulop_.accept(new MulOpVisitor<R,A>(), arg);
      p.expr_2.accept(new ExprVisitor<R,A>(), arg);
      return null;
    }
    public R visit(EAdd p, A arg)
    { /* Code for EAdd goes here */
      p.expr_1.accept(new ExprVisitor<R,A>(), arg);
      p.addop_.accept(new AddOpVisitor<R,A>(), arg);
      p.expr_2.accept(new ExprVisitor<R,A>(), arg);
      return null;
    }
    public R visit(ERel p, A arg)
    { /* Code for ERel goes here */
      p.expr_1.accept(new ExprVisitor<R,A>(), arg);
      p.relop_.accept(new RelOpVisitor<R,A>(), arg);
      p.expr_2.accept(new ExprVisitor<R,A>(), arg);
      return null;
    }
    public R visit(EAnd p, A arg)
    { /* Code for EAnd goes here */
      p.expr_1.accept(new ExprVisitor<R,A>(), arg);
      p.expr_2.accept(new ExprVisitor<R,A>(), arg);
      return null;
    }
    public R visit(EOr p, A arg)
    { /* Code for EOr goes here */
      p.expr_1.accept(new ExprVisitor<R,A>(), arg);
      p.expr_2.accept(new ExprVisitor<R,A>(), arg);
      return null;
    }
  }
  public class AddOpVisitor<R,A> implements AddOp.Visitor<R,A>
  {
    public R visit(Plus p, A arg)
    { /* Code for Plus goes here */
      return null;
    }
    public R visit(Minus p, A arg)
    { /* Code for Minus goes here */
      return null;
    }
  }
  public class MulOpVisitor<R,A> implements MulOp.Visitor<R,A>
  {
    public R visit(Times p, A arg)
    { /* Code for Times goes here */
      return null;
    }
    public R visit(Div p, A arg)
    { /* Code for Div goes here */
      return null;
    }
    public R visit(Mod p, A arg)
    { /* Code for Mod goes here */
      return null;
    }
  }
  public class RelOpVisitor<R,A> implements RelOp.Visitor<R,A>
  {
    public R visit(LTH p, A arg)
    { /* Code for LTH goes here */
      return null;
    }
    public R visit(LE p, A arg)
    { /* Code for LE goes here */
      return null;
    }
    public R visit(GTH p, A arg)
    { /* Code for GTH goes here */
      return null;
    }
    public R visit(GE p, A arg)
    { /* Code for GE goes here */
      return null;
    }
    public R visit(EQU p, A arg)
    { /* Code for EQU goes here */
      return null;
    }
    public R visit(NE p, A arg)
    { /* Code for NE goes here */
      return null;
    }
  }
}
