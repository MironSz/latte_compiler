package latte;

import latte.Absyn.*;
import latte.Absyn.Void;


/** BNFC-Generated Abstract Visitor */

public class AbstractVisitor<R,A> implements AllVisitor<R,A> {
    /* Program */
    public R visit(ProgramCode p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(Program p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* TopDef */
    public R visit(FnDef p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(TopDef p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* Arg */
    public R visit(ArgCode p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(Arg p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* Block */
    public R visit(BlockCode p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(Block p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* Stmt */
    public R visit(Empty p, A arg) { return visitDefault(p, arg); }
    public R visit(BStmt p, A arg) { return visitDefault(p, arg); }
    public R visit(Decl p, A arg) { return visitDefault(p, arg); }
    public R visit(Ass p, A arg) { return visitDefault(p, arg); }
    public R visit(Incr p, A arg) { return visitDefault(p, arg); }
    public R visit(Decr p, A arg) { return visitDefault(p, arg); }
    public R visit(Ret p, A arg) { return visitDefault(p, arg); }
    public R visit(VRet p, A arg) { return visitDefault(p, arg); }
    public R visit(Cond p, A arg) { return visitDefault(p, arg); }
    public R visit(CondElse p, A arg) { return visitDefault(p, arg); }
    public R visit(While p, A arg) { return visitDefault(p, arg); }
    public R visit(SExp p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(Stmt p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* Item */
    public R visit(NoInit p, A arg) { return visitDefault(p, arg); }
    public R visit(Init p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(Item p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* Type */
    public R visit(Int p, A arg) { return visitDefault(p, arg); }
    public R visit(Str p, A arg) { return visitDefault(p, arg); }
    public R visit(Bool p, A arg) { return visitDefault(p, arg); }
    public R visit(Void p, A arg) { return visitDefault(p, arg); }
    public R visit(Fun p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(Type p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* Expr */
    public R visit(EVar p, A arg) { return visitDefault(p, arg); }
    public R visit(ELitInt p, A arg) { return visitDefault(p, arg); }
    public R visit(ELitTrue p, A arg) { return visitDefault(p, arg); }
    public R visit(ELitFalse p, A arg) { return visitDefault(p, arg); }
    public R visit(EApp p, A arg) { return visitDefault(p, arg); }
    public R visit(EString p, A arg) { return visitDefault(p, arg); }
    public R visit(Neg p, A arg) { return visitDefault(p, arg); }
    public R visit(Not p, A arg) { return visitDefault(p, arg); }
    public R visit(EMul p, A arg) { return visitDefault(p, arg); }
    public R visit(EAdd p, A arg) { return visitDefault(p, arg); }
    public R visit(ERel p, A arg) { return visitDefault(p, arg); }
    public R visit(EAnd p, A arg) { return visitDefault(p, arg); }
    public R visit(EOr p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(Expr p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* AddOp */
    public R visit(Plus p, A arg) { return visitDefault(p, arg); }
    public R visit(Minus p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(AddOp p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* MulOp */
    public R visit(Times p, A arg) { return visitDefault(p, arg); }
    public R visit(Div p, A arg) { return visitDefault(p, arg); }
    public R visit(Mod p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(MulOp p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* RelOp */
    public R visit(LTH p, A arg) { return visitDefault(p, arg); }
    public R visit(LE p, A arg) { return visitDefault(p, arg); }
    public R visit(GTH p, A arg) { return visitDefault(p, arg); }
    public R visit(GE p, A arg) { return visitDefault(p, arg); }
    public R visit(EQU p, A arg) { return visitDefault(p, arg); }
    public R visit(NE p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(RelOp p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }

}
