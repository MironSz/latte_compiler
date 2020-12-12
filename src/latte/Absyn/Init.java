package latte.Absyn; // Java Package generated by the BNF Converter.

public class Init  extends Item {
//  public String ident_;
  public Expr expr_;
  public int line_num, col_num, offset;
  public Init(String p1, Expr p2) { ident_ = p1; expr_ = p2; }

  public <R,A> R accept(latte.Absyn.Item.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(java.lang.Object o) {
    if (this == o) return true;
    if (o instanceof latte.Absyn.Init) {
      latte.Absyn.Init x = (latte.Absyn.Init)o;
      return this.ident_.equals(x.ident_) && this.expr_.equals(x.expr_);
    }
    return false;
  }

  public int hashCode() {
    return 37*(this.ident_.hashCode())+this.expr_.hashCode();
  }


}
