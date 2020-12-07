package latte.Absyn; // Java Package generated by the BNF Converter.

public class Decr  extends Stmt {
  public String ident_;
  public int line_num, col_num, offset;
  public Decr(String p1) { ident_ = p1; }

  public <R,A> R accept(Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof Decr) {
      Decr x = (Decr)o;
      return this.ident_.equals(x.ident_);
    }
    return false;
  }

  public int hashCode() {
    return this.ident_.hashCode();
  }


}
