package latte;

/** BNFC-Generated Abstract Visitor */

public class AbstractVisitor<R,A> implements AllVisitor<R,A> {
    /* Program */
    public R visit(latte.Absyn.ProgramCode p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(latte.Absyn.Program p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* TopDef */
    public R visit(latte.Absyn.FnDef p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.ClassDef p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(latte.Absyn.TopDef p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* FunDef */
    public R visit(latte.Absyn.FunDefCode p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(latte.Absyn.FunDef p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* Arg */
    public R visit(latte.Absyn.ArgCode p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(latte.Absyn.Arg p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* Class */
    public R visit(latte.Absyn.ClassCode p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.ClassExt p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(latte.Absyn.Class p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* ClassStmt */
    public R visit(latte.Absyn.Fields p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.Method p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(latte.Absyn.ClassStmt p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* Target */
    public R visit(latte.Absyn.Variable p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.FieldT p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(latte.Absyn.Target p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* Block */
    public R visit(latte.Absyn.BlockCode p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(latte.Absyn.Block p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* Stmt */
    public R visit(latte.Absyn.Empty p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.BStmt p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.Decl p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.Ass p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.Incr p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.Decr p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.Ret p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.VRet p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.Cond p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.CondElse p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.While p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.SExp p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(latte.Absyn.Stmt p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* Item */
    public R visit(latte.Absyn.NoInit p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.Init p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(latte.Absyn.Item p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* Type */
    public R visit(latte.Absyn.Int p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.Str p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.Bool p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.Void p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.ClassType p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.Fun p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(latte.Absyn.Type p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* Expr */
    public R visit(latte.Absyn.EVar p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.ECast p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.ENewObj p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.ELitInt p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.ELitTrue p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.ELitFalse p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.EApp p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.EString p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.ENull p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.Neg p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.Not p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.EMul p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.EAdd p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.ERel p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.EAnd p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.EOr p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(latte.Absyn.Expr p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* AddOp */
    public R visit(latte.Absyn.Plus p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.Minus p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(latte.Absyn.AddOp p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* MulOp */
    public R visit(latte.Absyn.Times p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.Div p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.Mod p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(latte.Absyn.MulOp p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* RelOp */
    public R visit(latte.Absyn.LTH p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.LE p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.GTH p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.GE p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.EQU p, A arg) { return visitDefault(p, arg); }
    public R visit(latte.Absyn.NE p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(latte.Absyn.RelOp p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }

}
