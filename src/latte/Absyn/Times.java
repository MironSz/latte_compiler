package latte.Absyn; // Java Package generated by the BNF Converter.

public class Times extends MulOp {
    public Times() {
    }

    public <R, A> R accept(latte.Absyn.MulOp.Visitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof Times;
    }

    public int hashCode() {
        return 37;
    }


}
