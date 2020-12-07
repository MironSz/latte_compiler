package latte.Absyn; // Java Package generated by the BNF Converter.

public abstract class MulOp implements java.io.Serializable {
  public abstract <R,A> R accept(MulOp.Visitor<R,A> v, A arg);
  public interface Visitor <R,A> {
    public R visit(Times p, A arg);
    public R visit(Div p, A arg);
    public R visit(Mod p, A arg);

  }

}
