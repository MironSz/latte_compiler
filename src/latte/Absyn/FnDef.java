package latte.Absyn; // Java Package generated by the BNF Converter.

public class FnDef  extends TopDef {
  public Type type_;
  public String ident_;
  public ListArg listarg_;
  public Block block_;
  public int line_num, col_num, offset;
  public FnDef(Type p1, String p2, ListArg p3, Block p4) { type_ = p1; ident_ = p2; listarg_ = p3; block_ = p4; }

  public <R,A> R accept(Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof FnDef) {
      FnDef x = (FnDef)o;
      return this.type_.equals(x.type_) && this.ident_.equals(x.ident_) && this.listarg_.equals(x.listarg_) && this.block_.equals(x.block_);
    }
    return false;
  }

  public int hashCode() {
    return 37*(37*(37*(this.type_.hashCode())+this.ident_.hashCode())+this.listarg_.hashCode())+this.block_.hashCode();
  }


}
