package latte.Absyn; // Java Package generated by the BNF Converter.

public class Mod extends MulOp {
    public Mod() {
    }

    public <R, A> R accept(latte.Absyn.MulOp.Visitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof Mod;
    }

    public int hashCode() {
        return 37;
    }


}
