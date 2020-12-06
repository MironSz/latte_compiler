package latte.Absyn; // Java Package generated by the BNF Converter.

public class Int extends Type {
    public Int() {
    }

    public <R, A> R accept(latte.Absyn.Type.Visitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof Int;
    }

    public int hashCode() {
        return 37;
    }


}
