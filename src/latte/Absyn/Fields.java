package latte.Absyn; // Java Package generated by the BNF Converter.

public class Fields  extends ClassStmt {
  public Type type_;
  public ListIdent listident_;
  public int line_num, col_num, offset;
  public Fields(Type p1, ListIdent p2) { type_ = p1; listident_ = p2; }

  public <R,A> R accept(latte.Absyn.ClassStmt.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(java.lang.Object o) {
    if (this == o) return true;
    if (o instanceof latte.Absyn.Fields) {
      latte.Absyn.Fields x = (latte.Absyn.Fields)o;
      return this.type_.equals(x.type_) && this.listident_.equals(x.listident_);
    }
    return false;
  }

  public int hashCode() {
    return 37*(this.type_.hashCode())+this.listident_.hashCode();
  }


}
