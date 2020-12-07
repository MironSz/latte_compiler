package latte.Absyn; // Java Package generated by the BNF Converter.

public class ArgCode  extends Arg {
  public Type type_;
  public String ident_;
  public int line_num, col_num, offset;
  public ArgCode(Type p1, String p2) { type_ = p1; ident_ = p2; }

  public <R,A> R accept(Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof ArgCode) {
      ArgCode x = (ArgCode)o;
      return this.type_.equals(x.type_) && this.ident_.equals(x.ident_);
    }
    return false;
  }

  public int hashCode() {
    return 37*(this.type_.hashCode())+this.ident_.hashCode();
  }


}
