package latte.Absyn; // Java Package generated by the BNF Converter.

public class ENewObj  extends Expr {
  public String ident_;
  public int line_num, col_num, offset;
  public ENewObj(String p1) { ident_ = p1; }

  public <R,A> R accept(latte.Absyn.Expr.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(java.lang.Object o) {
    if (this == o) return true;
    if (o instanceof latte.Absyn.ENewObj) {
      latte.Absyn.ENewObj x = (latte.Absyn.ENewObj)o;
      return this.ident_.equals(x.ident_);
    }
    return false;
  }

  public int hashCode() {
    return this.ident_.hashCode();
  }


}