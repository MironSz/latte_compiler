package quadCode.translator;

import latte.Absyn.*;
import latte.FoldVisitor;
import quadCode.syntax.Block;
import quadCode.syntax.instructions.*;
import quadCode.syntax.jumps.CondJump;
import quadCode.syntax.jumps.SimpleJump;

public class TranslatorVisitor extends FoldVisitor<ReturnType, TranslationContext> {
    @Override
    public ReturnType leaf(TranslationContext arg) {
        return new ReturnType("");
    }

    @Override
    public ReturnType combine(ReturnType x, ReturnType y, TranslationContext arg) {
//        List<Instruction> result = new LinkedList<>();
//        y.instructions.forEach(i -> result.add(i));
//        x.instructions.forEach(i -> result.add(i));
        return y;
    }

    ReturnType visitTrinaryExpression(Expr expr, Expr lExpr, Expr rExpr, TranslationContext arg) {
        ReturnType rLeft = lExpr.accept(this, arg);
        ReturnType rRight = rExpr.accept(this, arg);

        String resultVar = arg.getNewResultVar();
        Instruction instruction = new BinaryInstruction(rLeft.getResultVar(), rRight.getResultVar(), resultVar, expr);

        ReturnType result = combine(rLeft, rRight, arg);
        arg.currentBlock.addInstruction(instruction);

        return result;
    }

    @Override
    public ReturnType visit(FnDef p, TranslationContext arg) {
        arg.openNewBlock(p.ident_);
        return super.visit(p, arg);
    }

    ReturnType visitLiteralExpression(Expr p, TranslationContext arg) {
        String varName = arg.getNewResultVar();
        Instruction instruction = new LitInstruction(varName, p);
        arg.currentBlock.addInstruction(instruction);

        return new ReturnType(varName);
    }

    @Override
    public ReturnType visit(While p, TranslationContext arg) {
        SimpleJump jumpToCondition = new SimpleJump();
        CondJump condJump = new CondJump();
        arg.closeCurrentBlock(jumpToCondition);

        String condition = "Condition_" + Block.allBlocks.size();
        arg.openNewBlock(condition);
        jumpToCondition.setNextBlock(arg.currentBlock);
        ReturnType returnType = p.expr_.accept(this, arg);
        condJump.setCondition(returnType.getResultVar());
        arg.closeCurrentBlock(condJump);

        String body = "Body_" + Block.allBlocks.size();
        arg.openNewBlock(body);
        condJump.setTrueBlock(arg.currentBlock);
        p.stmt_.accept(this, arg);
        arg.closeCurrentBlock(jumpToCondition);

        String finalBlock = "FinalBlock_" + Block.allBlocks.size();
        arg.openNewBlock(finalBlock);


        condJump.setFalseBlock(arg.currentBlock);


        return new ReturnType("");
    }

    @Override
    public ReturnType visit(EApp p, TranslationContext arg) {
        for (Expr expr : p.listexpr_) {
            ReturnType returnType = expr.accept(this, arg);
            arg.currentBlock.addInstruction(new ParamInnstruction(returnType.getResultVar()));
        }
        String resultVar = arg.getNewResultVar();
        Instruction instruction = new CallInstruction(resultVar, p.ident_);
        arg.currentBlock.addInstruction(instruction);
        return new ReturnType(resultVar);
    }

    @Override
    public ReturnType visit(Cond p, TranslationContext arg) {
        ReturnType conditionReturn = p.expr_.accept(this, arg);

        CondJump condJump = new CondJump();
        condJump.setCondition(conditionReturn.getResultVar());
        arg.closeCurrentBlock(condJump);
        SimpleJump finalJump = new SimpleJump();

        String label1 = "TrueBlock_" + Block.allBlocks.size();
        arg.openNewBlock(label1);
        condJump.setTrueBlock(arg.currentBlock);
        p.stmt_.accept(this, arg);
        arg.closeCurrentBlock(finalJump);


        String label3 = "FinalBlock_" + Block.allBlocks.size();
        arg.openNewBlock(label3);
        finalJump.setNextBlock(arg.currentBlock);
        condJump.setFalseBlock(arg.currentBlock);


        return new ReturnType("");
    }

    @Override
    public ReturnType visit(CondElse p, TranslationContext arg) {
        ReturnType conditionReturn = p.expr_.accept(this, arg);

        CondJump condJump = new CondJump();
        condJump.setCondition(conditionReturn.getResultVar());
        arg.closeCurrentBlock(condJump);
        SimpleJump finalJump = new SimpleJump();

        String label1 = "TrueBlock_" + Block.allBlocks.size();
        arg.openNewBlock(label1);
        condJump.setTrueBlock(arg.currentBlock);
        p.stmt_1.accept(this, arg);
        arg.closeCurrentBlock(finalJump);

        String label2 = "FalseBlock_" + Block.allBlocks.size();
        arg.openNewBlock(label2);
        condJump.setFalseBlock(arg.currentBlock);
        p.stmt_2.accept(this, arg);
        arg.closeCurrentBlock(finalJump);

        String label3 = "FinalBlock_" + Block.allBlocks.size();
        arg.openNewBlock(label3);
        finalJump.setNextBlock(arg.currentBlock);

        return new ReturnType("");
    }

    @Override
    public ReturnType visit(ELitInt p, TranslationContext arg) {
        return visitLiteralExpression(p, arg);
    }

    @Override
    public ReturnType visit(ELitTrue p, TranslationContext arg) {
        return visitLiteralExpression(p, arg);
    }

    @Override
    public ReturnType visit(ELitFalse p, TranslationContext arg) {
        return visitLiteralExpression(p, arg);
    }

    @Override
    public ReturnType visit(EString p, TranslationContext arg) {
        return visitLiteralExpression(p, arg);
    }

    @Override
    public ReturnType visit(Ass p, TranslationContext arg) {
        ReturnType instructions = p.expr_.accept(this, arg);
        instructions.setResultVar(p.ident_);
        arg.currentBlock.setResultVar(p.ident_);
        return instructions;
    }

    @Override
    public ReturnType visit(EVar p, TranslationContext arg) {
        return new ReturnType(p.ident_);
    }

    @Override
    public ReturnType visit(Ret p, TranslationContext arg) {
        ReturnType calcInstructions = p.expr_.accept(this, arg);
//        calcInstructions.addInstruction(new ReturnInstruction(calcInstructions.getResultVar()));
        arg.currentBlock.addInstruction(new ReturnInstruction(calcInstructions.getResultVar()));
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