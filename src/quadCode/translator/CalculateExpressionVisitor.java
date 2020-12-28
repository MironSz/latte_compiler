package quadCode.translator;

import latte.Absyn.*;
import latte.FoldVisitor;
import quadCode.syntax.instructions.BinaryInstruction;
import quadCode.syntax.instructions.Instruction;
import quadCode.syntax.instructions.LitInstruction;
import quadCode.syntax.instructions.ReturnInstruction;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CalculateExpressionVisitor extends FoldVisitor<ReturnType, TranslationContext> {
    @Override
    public ReturnType leaf(TranslationContext arg) {
        return new ReturnType("", new LinkedList<>());
    }

    @Override
    public ReturnType combine(ReturnType x, ReturnType y, TranslationContext arg) {
        List<Instruction> result = new LinkedList<>();
        result.addAll(x.instructions);
        result.addAll(y.instructions);
        return new ReturnType(y.resultVar, result);
    }

    ReturnType visitTrinaryExpression(Expr expr, Expr lExpr, Expr rExpr, TranslationContext arg) {
        ReturnType rLeft = lExpr.accept(this, arg);
        ReturnType rRight = rExpr.accept(this, arg);

        Instruction instruction = new BinaryInstruction(rLeft.resultVar, rRight.resultVar, arg.getNewResultVar(), expr);

        ReturnType result = combine(rLeft, rRight, arg);

        result.instructions.add(instruction);

        return result;
    }

    @Override
    public ReturnType visit(FnDef p, TranslationContext arg) {
        arg.openNewBlock(p.ident_);
        return super.visit(p, arg);
    }

    ReturnType visitLiteralExpression(Expr p, TranslationContext arg){
        String varName = arg.getNewResultVar();
        Instruction instruction = new LitInstruction(varName,p);
        return new ReturnType(varName, Arrays.asList(instruction));
    }
    @Override
    public ReturnType visit(ELitInt p, TranslationContext arg) {
        return visitLiteralExpression(p,arg);
    }

    @Override
    public ReturnType visit(ELitTrue p, TranslationContext arg) {
        return visitLiteralExpression(p,arg);
    }

    @Override
    public ReturnType visit(ELitFalse p, TranslationContext arg) {
        return visitLiteralExpression(p,arg);
    }

    @Override
    public ReturnType visit(Ass p, TranslationContext arg) {
        ReturnType instructions = p.expr_.accept(this, arg);
        instructions.setResultVar(p.ident_);
        return instructions;
    }

    @Override
    public ReturnType visit(EVar p, TranslationContext arg) {
        return new ReturnType(p.ident_, new LinkedList<>());
    }

    @Override
    public ReturnType visit(Ret p, TranslationContext arg) {
        ReturnType calcInstructions = p.expr_.accept(this,arg);
        calcInstructions.instructions.add(new ReturnInstruction(calcInstructions.resultVar));
        arg.closeCurrentBlock(calcInstructions.instructions);
        return calcInstructions;
    }

    @Override
    public ReturnType visit(EMul p, TranslationContext arg) {
        return visitTrinaryExpression(p, p.expr_1, p.expr_2, arg);
    }

    @Override
    public ReturnType visit(EAdd p, TranslationContext arg) {
        return visitTrinaryExpression(p, p.expr_1, p.expr_2, arg);
    }

    @Override
    public ReturnType visit(ERel p, TranslationContext arg) {
        return visitTrinaryExpression(p, p.expr_1, p.expr_2, arg);
    }

    @Override
    public ReturnType visit(EAnd p, TranslationContext arg) {
        return visitTrinaryExpression(p, p.expr_1, p.expr_2, arg);
    }

    @Override
    public ReturnType visit(EOr p, TranslationContext arg) {
        return visitTrinaryExpression(p, p.expr_1, p.expr_2, arg);
    }

}
