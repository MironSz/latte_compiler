package quadCode.translator;

import latte.Absyn.*;
import latte.FoldVisitor;
import quadCode.syntax.Block;
import quadCode.syntax.instructions.*;
import quadCode.syntax.instructions.arguments.InstructionArgument;
import quadCode.syntax.instructions.arguments.LitArgument;
import quadCode.syntax.instructions.arguments.VarArgument;
import quadCode.syntax.instructions.arguments.VoidArgument;
import quadCode.syntax.jumps.CondJump;
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

    ReturnType visitTrinaryExpression(Expr expr, Expr lExpr, Expr rExpr, TranslationContext arg) {
        ReturnType rLeft = lExpr.accept(this, arg);
        ReturnType rRight = rExpr.accept(this, arg);

        String resultVar = arg.getNewResultVar();
        Instruction instruction = new BinaryInstruction(rLeft.getResultVar(), rRight.getResultVar(), resultVar, expr);

        ReturnType result = new ReturnType(new VarArgument(resultVar));
        arg.currentBlock.addInstruction(instruction);

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

        String condition = "Condition_" + Block.allBlocks.size();
        arg.openNewBlock(condition);
        jumpToCondition.setNextBlock(arg.currentBlock);
        ReturnType returnType = p.expr_.accept(this, arg);

        condJump.setCondition(((VarArgument) returnType.getResultVar()).getVarName());
        arg.closeCurrentBlock(condJump);

        String body = "Body_" + Block.allBlocks.size();
        arg.openNewBlock(body);
        condJump.setTrueBlock(arg.currentBlock);
        p.stmt_.accept(this, arg);
        arg.closeCurrentBlock(jumpToCondition);

        String finalBlock = "FinalBlock_" + Block.allBlocks.size();
        arg.openNewBlock(finalBlock);


        condJump.setFalseBlock(arg.currentBlock);


        return new ReturnType(new VoidArgument());
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
        return new ReturnType(new VarArgument(resultVar));
    }

    @Override
    public ReturnType visit(Cond p, TranslationContext arg) {
        ReturnType conditionReturn = p.expr_.accept(this, arg);

        CondJump condJump = new CondJump();
        condJump.setCondition(((VarArgument) conditionReturn.getResultVar()).getVarName());
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


        return new ReturnType(new VoidArgument());
    }

    @Override
    public ReturnType visit(CondElse p, TranslationContext arg) {
        ReturnType conditionReturn = p.expr_.accept(this, arg);

        CondJump condJump = new CondJump();
        condJump.setCondition(((VarArgument) conditionReturn.getResultVar()).getVarName());
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

        return new ReturnType(new VoidArgument());
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
        Instruction instruction = new AssignmentInstruction(p.ident_,result.getResultVar());
        arg.currentBlock.addInstruction(instruction);
        return result;
    }

    @Override
    public ReturnType visit(EVar p, TranslationContext arg) {
        return new ReturnType(new VarArgument(p.ident_));
    }

    @Override
    public ReturnType visit(Ret p, TranslationContext arg) {
        ReturnType result = p.expr_.accept(this, arg);
        arg.currentBlock.addInstruction(new ReturnInstruction(result.getResultVar()));
        return new ReturnType(new VoidArgument());
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

//    @Override
//    public ReturnType visit(EAnd p, TranslationContext arg) {
//        return visitTrinaryExpression(p, p.expr_1, p.expr_2, arg);
//    }


    @Override
    public ReturnType visit(EOr p, TranslationContext arg) {
//        return visitTrinaryExpression(p, p.expr_1, p.expr_2, arg);

        String writeTrueLabel = arg.newLabel("WRITE_TRUE");
        String writeFalseLabel = arg.newLabel("WRITE_FALSE");
        String finalLabel = arg.newLabel("FINAL");

        String finalArgument = arg.getNewResultVar();
        Instruction writeTrue = new AssignmentInstruction(finalArgument,new LitArgument(new ELitTrue()));
        Instruction writeFalse = new AssignmentInstruction(finalArgument,new LitArgument(new ELitFalse()));
        Instruction finalJumpInstruction = new JumpInstruction(finalLabel);

        ReturnType rLeft = p.expr_1.accept(this, arg);
        Instruction jumpToTrue  = new JumpInstruction(writeTrueLabel,"je",rLeft.getResultVar()) ;
        arg.currentBlock.addInstruction(jumpToTrue);

        ReturnType rRight = p.expr_2.accept(this, arg);
        Instruction jumpToFalse  = new JumpInstruction(writeFalseLabel,"je",rRight.getResultVar()) ;
        arg.currentBlock.addInstruction(jumpToTrue);

        arg.currentBlock.addInstruction(writeFalse);
        arg.currentBlock.addInstruction(finalJumpInstruction);
        arg.currentBlock.addInstruction(new LabelInstruction(writeTrueLabel));
        arg.currentBlock.addInstruction(writeTrue);
        arg.currentBlock.addInstruction(new LabelInstruction(finalLabel));

        String resultVar = arg.getNewResultVar();
        ReturnType result = new ReturnType(new VarArgument(resultVar));
        return result;
    }

    @Override
    public ReturnType visit(EAnd p, TranslationContext arg) {

        String writeTrueLabel = arg.newLabel("WRITE_TRUE");
        String writeFalseLabel = arg.newLabel("WRITE_FALSE");
        String finalLabel = arg.newLabel("FINAL");

        String finalArgument = arg.getNewResultVar();
        Instruction writeTrue = new AssignmentInstruction(finalArgument,new LitArgument(new ELitTrue()));
        Instruction writeFalse = new AssignmentInstruction(finalArgument,new LitArgument(new ELitFalse()));
        Instruction finalJumpInstruction = new JumpInstruction(finalLabel);

        ReturnType rLeft = p.expr_1.accept(this, arg);
        Instruction jumpFalse  = new JumpInstruction(writeFalseLabel,"jne",rLeft.getResultVar()) ;
        arg.currentBlock.addInstruction(jumpFalse);

        ReturnType rRight = p.expr_2.accept(this, arg);
        Instruction jumpTrue  = new JumpInstruction(writeTrueLabel,"jne",rRight.getResultVar()) ;
        arg.currentBlock.addInstruction(jumpFalse);

        arg.currentBlock.addInstruction(writeTrue);
        arg.currentBlock.addInstruction(finalJumpInstruction);
        arg.currentBlock.addInstruction(new LabelInstruction(writeFalseLabel));
        arg.currentBlock.addInstruction(writeFalse);
        arg.currentBlock.addInstruction(new LabelInstruction(finalLabel));

        String resultVar = arg.getNewResultVar();
        ReturnType result = new ReturnType(new VarArgument(resultVar));
        return result;
    }

    @Override
    public ReturnType visit(Init p, TranslationContext arg) {
        ReturnType returnType = p.expr_.accept(this,arg);
        arg.currentBlock.addInstruction(new AssignmentInstruction(p.ident_,returnType.getResultVar()));
        return returnType;
    }



}
