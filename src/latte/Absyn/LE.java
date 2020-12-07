package latte.Absyn; // Java Package generated by the BNF Converter.

public class LE  extends RelOp {
  public int line_num, col_num, offset;
  public LE() { }

  public <R,A> R accept(Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof LE) {
      return true;
    }
    return false;
  }

  public int hashCode() {
    return 37;
  }


}
