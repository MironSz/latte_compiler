package quadCode.translator;

import frontend.DeclarationContext;
import latte.Absyn.*;
import latte.FoldVisitor;
import quadCode.syntax.Block;
import quadCode.syntax.instructions.*;
import quadCode.syntax.instructions.arguments.LitArgument;
import quadCode.syntax.instructions.arguments.VarArgument;
import quadCode.syntax.instructions.arguments.VoidArgument;
import quadCode.syntax.jumps.CondJump;
import quadCode.syntax.jumps.RetJump;
import quadCode.syntax.jumps.SimpleJump;

public class TranslatorVisitor extends FoldVisitor<ReturnType, TranslationContext> {
    @Override
    public ReturnType leaf(TranslationContext arg) {
        return new ReturnType(new VoidArgument());
    }

    @Override
    public ReturnType combine(ReturnType x, ReturnType y, TranslationContext arg) {
        return y;
    }

    ReturnType visitTrinaryExpression(Expr expr, Expr lExpr, Expr rExpr, TranslationContext arg, boolean intCompare) {
        ReturnType rLeft = lExpr.accept(this, arg);
        ReturnType rRight = rExpr.accept(this, arg);

        String resultVar = arg.getNewResultVar();
        Instruction instruction = new BinaryInstruction(rLeft.getResultVar(), rRight.getResultVar(), resultVar, expr,intCompare,false);

        ReturnType result = new ReturnType(new VarArgument(resultVar));
        arg.addInstruction(instruction);

        return result;
    }

    @Override
    public ReturnType visit(FnDef p, TranslationContext arg) {
        arg.openNewBlock(p.ident_);
        return super.visit(p, arg);
    }

    ReturnType visitLiteralExpression(Expr p, TranslationContext arg) {
        return new ReturnType(new LitArgument(p));
    }

    @Override
    public ReturnType visit(While p, TranslationContext arg) {
        SimpleJump jumpToCondition = new SimpleJump();
        CondJump condJump = new CondJump();
        arg.closeCurrentBlock(jumpToCondition);

        String condition = "WhileCondition_" + Block.allBlocks.size();
        arg.openNewBlock(condition);
        jumpToCondition.setNextBlock(arg.getCurrentBlock());
        ReturnType returnType = p.expr_.accept(this, arg);
        CompareInstruction compareInstruction = new CompareInstruction(returnType.getResultVar(),new LitArgument(new ELitTrue()));
        arg.addInstruction(compareInstruction);
        condJump.setCondition("je");
        arg.closeCurrentBlock(condJump);

        String body = "WhileBody_" + Block.allBlocks.size();
        arg.openNewBlock(body);
        condJump.setTrueBlock(arg.getCurrentBlock());
        p.stmt_.accept(this, arg);
        arg.closeCurrentBlock(jumpToCondition);

        String finalBlock = "WhileFinalBlock_" + Block.allBlocks.size();
        arg.openNewBlock(finalBlock);


        condJump.setFalseBlock(arg.getCurrentBlock());


        return new ReturnType(new VoidArgument());
    }

    @Override
    public ReturnType visit(EApp p, TranslationContext arg) {
        for (Expr expr : p.listexpr_) {
            ReturnType returnType = expr.accept(this, arg);
            arg.addInstruction(new ParamInnstruction(returnType.getResultVar()));
        }
        String resultVar = arg.getNewResultVar();
        Instruction instruction = new CallInstruction(resultVar, p.ident_);
        arg.addInstruction(instruction);
        return new ReturnType(new VarArgument(resultVar));
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
        ReturnType result = p.expr_.accept(this, arg);
        Instruction instruction = new AssignmentInstruction(p.ident_, result.getResultVar());
        arg.addInstruction(instruction);
        return result;
    }

    @Override
    public ReturnType visit(EVar p, TranslationContext arg) {
        return new ReturnType(new VarArgument(p.ident_));
    }

    @Override
    public ReturnType visit(Ret p, TranslationContext arg) {
        ReturnType result = p.expr_.accept(this, arg);
        ReturnInstruction returnInstruction = new ReturnInstruction(result.getResultVar());
        arg.closeCurrentBlock(new RetJump(returnInstruction));
        return new ReturnType(new VoidArgument());
    }

    @Override
    public ReturnType visit(EMul p, TranslationContext arg) {
        return visitTrinaryExpression(p, p.expr_1, p.expr_2, arg,false);
    }

    @Override
    public ReturnType visit(EAdd p, TranslationContext arg) {
        return visitTrinaryExpression(p, p.expr_1, p.expr_2, arg,false);
    }

    @Override
    public ReturnType visit(ERel p, TranslationContext arg) {
        return visitTrinaryExpression(p, p.expr_1, p.expr_2, arg,true);
    }


    @Override
    public ReturnType visit(Cond p, TranslationContext arg) {
        ReturnType conditionReturn = p.expr_.accept(this, arg);

        CondJump condJump = new CondJump();
        arg.addInstruction(new CompareInstruction(conditionReturn.getResultVar(), new LitArgument(new ELitTrue())));
        condJump.setCondition("je");
        arg.closeCurrentBlock(condJump);
        SimpleJump finalJump = new SimpleJump();

        String label1 = "IfTrueBlock_" + Block.allBlocks.size();
        arg.openNewBlock(label1);
        condJump.setTrueBlock(arg.getCurrentBlock());
        p.stmt_.accept(this, arg);
        arg.closeCurrentBlock(finalJump);


        String label3 = "IfFinalBlock_" + Block.allBlocks.size();
        arg.openNewBlock(label3);
        finalJump.setNextBlock(arg.getCurrentBlock());
        condJump.setFalseBlock(arg.getCurrentBlock());


        return new ReturnType(new VoidArgument());
    }

    @Override
    public ReturnType visit(CondElse p, TranslationContext arg) {
        ReturnType conditionReturn = p.expr_.accept(this, arg);

        CondJump condJump = new CondJump();
        arg.addInstruction(new CompareInstruction(conditionReturn.getResultVar(), new LitArgument(new ELitTrue())));
        condJump.setCondition("je");
        arg.closeCurrentBlock(condJump);
        SimpleJump finalJump = new SimpleJump();

        String trueLabel = "IfElseTrueBlock_" + Block.allBlocks.size();
        arg.openNewBlock(trueLabel);
        condJump.setTrueBlock(arg.getCurrentBlock());
        p.stmt_1.accept(this, arg);
        arg.closeCurrentBlock(finalJump);


        String falseLabel = "IfElseFalseBlock_" + Block.allBlocks.size();
        arg.openNewBlock(falseLabel);
        condJump.setFalseBlock(arg.getCurrentBlock());
        p.stmt_2.accept(this, arg);
        arg.closeCurrentBlock(finalJump);

        String finalLabel = "IfElseFinalBlock_" + Block.allBlocks.size();
        arg.openNewBlock(finalLabel);
        finalJump.setNextBlock(arg.getCurrentBlock());


        return new ReturnType(new VoidArgument());
    }


    @Override
    public ReturnType visit(EOr p, TranslationContext arg) {
        String writeTrueLabel = arg.newLabel("WRITE_TRUE");
        String writeFalseLabel = arg.newLabel("WRITE_FALSE");
        String finalLabel = arg.newLabel("FINAL");
        String rightExpression = arg.newLabel("RIGHT_EXPR");
        String resultVar = arg.getNewResultVar();

        CondJump trueOrRight = new CondJump();
        CondJump trueOrFalse = new CondJump();
        SimpleJump jumpToFinal = new SimpleJump();
        trueOrRight.setCondition("je");
        trueOrFalse.setCondition("je");

        ReturnType rLeft = p.expr_1.accept(this, arg);
        arg.addInstruction(new CompareInstruction(rLeft.getResultVar(), new LitArgument(new ELitTrue())));
        arg.closeCurrentBlock(trueOrRight);

        arg.openNewBlock(rightExpression);
        trueOrRight.setFalseBlock(arg.getCurrentBlock());
        ReturnType rRight = p.expr_2.accept(this, arg);
        arg.addInstruction(new CompareInstruction(rRight.getResultVar(), new LitArgument(new ELitTrue())));
        arg.closeCurrentBlock(trueOrFalse);

        arg.openNewBlock(writeTrueLabel);
        trueOrRight.setTrueBlock(arg.getCurrentBlock());
        trueOrFalse.setTrueBlock(arg.getCurrentBlock());
        arg.addInstruction(new AssignmentInstruction(resultVar, new LitArgument(new ELitTrue())));
        arg.closeCurrentBlock(jumpToFinal);

        arg.openNewBlock(writeFalseLabel);
        trueOrRight.setFalseBlock(arg.getCurrentBlock());
        trueOrFalse.setFalseBlock(arg.getCurrentBlock());
        arg.addInstruction(new AssignmentInstruction(resultVar, new LitArgument(new ELitFalse())));
        arg.closeCurrentBlock(jumpToFinal);

        arg.openNewBlock(finalLabel);
        jumpToFinal.setNextBlock(arg.getCurrentBlock());

        ReturnType result = new ReturnType(new VarArgument(resultVar));
        return result;
    }


    @Override
    public ReturnType visit(EAnd p, TranslationContext arg) {
        throw new RuntimeException("unimplemented shit");
    }

    @Override
    public ReturnType visit(Init p, TranslationContext arg) {
        ReturnType returnType = p.expr_.accept(this, arg);
        arg.addInstruction(new AssignmentInstruction(p.ident_, returnType.getResultVar()));
        return returnType;
    }


}
