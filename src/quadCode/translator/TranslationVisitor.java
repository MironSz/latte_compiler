package quadCode.translator;

import latte.Absyn.Ass;
import latte.Absyn.FnDef;
import latte.FoldVisitor;
import quadCode.syntax.Block;

public class TranslationVisitor extends FoldVisitor<String, TranslationContext> {
    @Override
    public String leaf(TranslationContext arg) {
        return null;
    }

    @Override
    public String combine(String x, String y, TranslationContext arg) {
        return y;
    }

    @Override
    public String visit(FnDef p, TranslationContext arg) {
        String label = "F_"+p.ident_;
        Block block = new Block(label);
        arg.currentBlock=block;
        arg.prevBlock=null;
        return super.visit(p, arg);
    }

    @Override
    public String visit(Ass p, TranslationContext arg) {
        return super.visit(p, arg);
    }
}
