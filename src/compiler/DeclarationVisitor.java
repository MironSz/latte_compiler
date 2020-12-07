package compiler;

import latte.Absyn.Void;
import latte.Absyn.*;
import latte.FoldVisitor;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class DeclarationVisitor extends FoldVisitor<Type, DeclarationContext> {
    @Override
    public Type leaf(DeclarationContext arg) {
        return null;
    }

    @Override
    public Type visit(ProgramCode p, DeclarationContext arg) {

        for (TopDef x : p.listtopdef_) {
            arg = arg.declareNewVar(((FnDef) x).ident_, arg.functionToFunctionType((FnDef) x));
        }
        for (TopDef x : p.listtopdef_) {
            x.accept(this, arg);
        }
        return null;
    }

    @Override
    public Type visit(FnDef p, DeclarationContext arg) {
//        arg.declareNewVar(p.ident_,p.type_);
        arg = arg.newScope(p.type_);
        return super.visit(p, arg);
    }

    @Override
    public Type visit(ArgCode p, DeclarationContext arg) {
        arg = arg.declareNewVar(p.ident_, p.type_);
        return super.visit(p, arg);
    }

    @Override
    public Type visit(BlockCode p, DeclarationContext arg) {
        arg = arg.newScope();
        return super.visit(p, arg);
    }


    @Override
    public Type visit(Decl p, DeclarationContext arg) {
        for (Item item : p.listitem_) {
            arg = arg.declareNewVar(item.ident_, p.type_);
        }
        return super.visit(p, arg);
    }

    @Override
    public Type visit(Ass p, DeclarationContext arg) {
        if (!arg.isDeclared(p.ident_, p.expr_.accept(this, arg))) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num,p.col_num,"Wrong type or undeclared variable (Ass)"));
        }
        return null;
    }

    @Override
    public Type visit(Incr p, DeclarationContext arg) {
        if (!arg.isDeclared(p.ident_, new Int())) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num,p.col_num,"Wrong type or undeclared variable (Incr)"));
        }
        return null;
    }

    @Override
    public Type visit(Decr p, DeclarationContext arg) {
        if (!arg.isDeclared(p.ident_, new Int())) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num,p.col_num,"Wrong type or undeclared variable (Decr)"));
        }
        return null;
    }

    @Override
    public Type visit(Ret p, DeclarationContext arg) {
        Type type = p.expr_.accept(this, arg);
        if (!arg.checkResultType(type)) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num,p.col_num,"Wrong result type"));
        }
        return type;
    }

    @Override
    public Type visit(VRet p, DeclarationContext arg) {
        Type type = new Void();
        if (!arg.checkResultType(type)) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num,p.col_num,"Wrong result type"));
        }
        return type;
    }

    @Override
    public Type visit(Cond p, DeclarationContext arg) {
        if (!p.expr_.accept(this, arg).equals(new Bool())) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num,p.col_num,"Wrong type or undeclared variable (Cond)"));
        }
        return p.stmt_.accept(this, arg.newScope());
    }

    @Override
    public Type visit(CondElse p, DeclarationContext arg) {
        if (p.expr_.accept(this, arg).equals(new Bool())) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num,p.col_num,"Wrong type or undeclared variable (CondElse)"));
        }
        p.stmt_1.accept(this, arg.newScope());
        p.stmt_2.accept(this, arg.newScope());
        return null;
    }

    @Override
    public Type visit(While p, DeclarationContext arg) {
        if (!p.expr_.accept(this, arg).equals(new Bool())) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num,p.col_num,"Wrong type or undeclared variable (While)"));
        }
        return p.stmt_.accept(this, arg.newScope());
    }


    @Override
    public Type visit(BStmt p, DeclarationContext arg) {
        arg=arg.newScope();
        return super.visit(p, arg);
    }

    @Override
    public Type visit(SExp p, DeclarationContext arg) {
        return super.visit(p, arg);
    }

    @Override
    public Type visit(Int p, DeclarationContext arg) {
        return super.visit(p, arg);
    }

    @Override
    public Type visit(Str p, DeclarationContext arg) {
        return super.visit(p, arg);
    }

    @Override
    public Type visit(Bool p, DeclarationContext arg) {
        return super.visit(p, arg);
    }

    @Override
    public Type visit(Void p, DeclarationContext arg) {
        return super.visit(p, arg);
    }

    @Override
    public Type visit(Fun p, DeclarationContext arg) {
        return super.visit(p, arg);
    }

    @Override
    public Type visit(EVar p, DeclarationContext arg) {
        return super.visit(p, arg);
    }

    //TODO I think no action is needed.
    @Override
    public Type visit(NoInit p, DeclarationContext arg) {
        return super.visit(p, arg);
    }

    @Override
    public Type visit(Init p, DeclarationContext arg) {
        Type rType = p.expr_.accept(this, arg);
        if (!arg.isDeclared(p.ident_, rType)) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num,p.col_num,"Wrong type or undeclared variable (Init)"));
        }
        return null;
    }


    @Override
    public Type visit(ELitInt p, DeclarationContext arg) {
        return new Int();
    }

    @Override
    public Type visit(ELitTrue p, DeclarationContext arg) {
        return new Bool();
    }

    @Override
    public Type visit(ELitFalse p, DeclarationContext arg) {
        return new Bool();
    }

    @Override
    public Type visit(EApp p, DeclarationContext arg) {
        Type type = arg.getTypeOfVar(p.ident_);
        if (!(type instanceof Fun))
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num,p.col_num,p.ident_ + " is not a function"));
        Fun functionType = (Fun) type;
        List<Type> argumentsTypes = p.listexpr_.stream().map(exp -> exp.accept(this, arg)).collect(Collectors.toCollection(LinkedList::new));
        List<Type> expectedTypes = functionType.listtype_;

        if (!argumentsTypes.equals(expectedTypes)) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num,p.col_num,"Arguments mismatch in function call"));
        }

        return functionType.type_;
    }

    @Override
    public Type visit(EString p, DeclarationContext arg) {
        return new Str();
    }

    @Override
    public Type visit(Neg p, DeclarationContext arg) {
        if (super.visit(p, arg) instanceof Int)
            return new Int();
        else {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num,p.col_num,"Wrong type"));
        }
    }

    @Override
    public Type visit(Not p, DeclarationContext arg) {
        if (super.visit(p, arg) instanceof Bool)
            return new Bool();
        else {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num,p.col_num,"Wrong type"));
        }
    }

    private Type visitTrinaryOperation(Expr expr, Type expectedTypes, String errorMessage, DeclarationContext arg, Expr ex1, Expr ex2) {
        Type t1 = ex1.accept(this, arg);
        Type t2 = ex2.accept(this, arg);
        if (!(t1.equals(expectedTypes) && t2.equals(expectedTypes)))
            throw new RuntimeException(errorMessage);
        return expectedTypes;
    }

    @Override
    public Type visit(EMul p, DeclarationContext arg) {
        return visitTrinaryOperation(p, new Int(), SemanticErrorMessage.buildMessage(p.line_num,p.col_num,"Wrong type in mul"), arg, p.expr_1, p.expr_2);
    }

    @Override
    public Type visit(EAdd p, DeclarationContext arg) {
        return visitTrinaryOperation(p, new Int(), SemanticErrorMessage.buildMessage(p.line_num,p.col_num,"Wrong type in add"), arg, p.expr_1, p.expr_2);
    }

    @Override
    public Type visit(ERel p, DeclarationContext arg) {
        if (p.relop_ instanceof EQU || p.relop_ instanceof NE) {
            Type t1 = p.accept(this, arg);
            Type t2 = p.accept(this, arg);
            if (!t1.equals(t2)) {
                throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num,p.col_num,"Wrong type in comparison"));
            }
            return new Bool();
        }
        return visitTrinaryOperation(p, new Int(), SemanticErrorMessage.buildMessage(p.line_num,p.col_num,"Wrong type in comparison"), arg, p.expr_1, p.expr_2);

    }

    @Override
    public Type visit(EAnd p, DeclarationContext arg) {

        return visitTrinaryOperation(p, new Bool(), SemanticErrorMessage.buildMessage(p.line_num,p.col_num,"Wrong type in and"), arg, p.expr_1, p.expr_2);
    }

    @Override
    public Type visit(EOr p, DeclarationContext arg) {
        return visitTrinaryOperation(p, new Bool(), SemanticErrorMessage.buildMessage(p.line_num,p.col_num,"Wrong type in or"), arg, p.expr_1, p.expr_2);
    }


    @Override
    public Type combine(Type x, Type y, DeclarationContext arg) {
        return x;
    }
}
