package latte.Absyn; // Java Package generated by the BNF Converter.

public class ProgramCode  extends Program {
  public ListTopDef listtopdef_;
  public int line_num, col_num, offset;
  public ProgramCode(ListTopDef p1) { listtopdef_ = p1; }

  public <R,A> R accept(latte.Absyn.Program.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(java.lang.Object o) {
    if (this == o) return true;
    if (o instanceof latte.Absyn.ProgramCode) {
      latte.Absyn.ProgramCode x = (latte.Absyn.ProgramCode)o;
      return this.listtopdef_.equals(x.listtopdef_);
    }
    return false;
  }

  public int hashCode() {
    return this.listtopdef_.hashCode();
  }


}
