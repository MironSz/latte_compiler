package latte.Absyn; // Java Package generated by the BNF Converter.

public class EString  extends Expr {
  public String string_;
  public int line_num, col_num, offset;
  public EString(String p1) { string_ = p1; }

  public <R,A> R accept(Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof EString) {
      EString x = (EString)o;
      return this.string_.equals(x.string_);
    }
    return false;
  }

  public int hashCode() {
    return this.string_.hashCode();
  }


}
