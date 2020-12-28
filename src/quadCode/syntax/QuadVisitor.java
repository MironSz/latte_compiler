package quadCode.syntax;

import latte.Absyn.FnDef;
import latte.FoldVisitor;

public class QuadVisitor extends FoldVisitor<Object,TranslationContext> {
    @Override
    public Object leaf(TranslationContext arg) {
        return null;
    }

    @Override
    public Object combine(Object x, Object y, TranslationContext arg) {
        return null;
    }

    @Override
    public Object visit(FnDef p, TranslationContext arg) {

        return super.visit(p, arg);
    }
}
