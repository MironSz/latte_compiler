package latte.Absyn; // Java Package generated by the BNF Converter.

public class ERel extends Expr {
    public Expr expr_1;
    public Expr expr_2;
    public final RelOp relop_;

    public ERel(Expr p1, RelOp p2, Expr p3) {
        expr_1 = p1;
        relop_ = p2;
        expr_2 = p3;
    }

    public <R, A> R accept(latte.Absyn.Expr.Visitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof latte.Absyn.ERel) {
            latte.Absyn.ERel x = (latte.Absyn.ERel) o;
            return this.expr_1.equals(x.expr_1) && this.relop_.equals(x.relop_) && this.expr_2.equals(x.expr_2);
        }
        return false;
    }

    public int hashCode() {
        return 37 * (37 * (this.expr_1.hashCode()) + this.relop_.hashCode()) + this.expr_2.hashCode();
    }


}
