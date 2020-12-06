package latte.Absyn; // Java Package generated by the BNF Converter.

//Todo check return type
public class FnDef extends TopDef {
    public final Type type_;
    public final String ident_;
    public final ListArg listarg_;
    public final Block block_;

    public FnDef(Type p1, String p2, ListArg p3, Block p4) {
        type_ = p1;
        ident_ = p2;
        listarg_ = p3;
        block_ = p4;
    }

    public <R, A> R accept(latte.Absyn.TopDef.Visitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof latte.Absyn.FnDef) {
            latte.Absyn.FnDef x = (latte.Absyn.FnDef) o;
            return this.type_.equals(x.type_) && this.ident_.equals(x.ident_) && this.listarg_.equals(x.listarg_) && this.block_.equals(x.block_);
        }
        return false;
    }

    public int hashCode() {
        return 37 * (37 * (37 * (this.type_.hashCode()) + this.ident_.hashCode()) + this.listarg_.hashCode()) + this.block_.hashCode();
    }


}
