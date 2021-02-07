package latte.Absyn; // Java Package generated by the BNF Converter.

public abstract class ClassStmt implements java.io.Serializable {
  public abstract <R,A> R accept(ClassStmt.Visitor<R,A> v, A arg);
  public interface Visitor <R,A> {
    public R visit(latte.Absyn.Fields p, A arg);
    public R visit(latte.Absyn.Method p, A arg);

  }

}
