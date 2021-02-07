package frontend;

import latte.Absyn.Class;
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

    private FunDefCode topDefToFunDefCode(TopDef topDef) {
        return (FunDefCode) ((FnDef) topDef).fundef_;
    }

    private FunctionSignature functionSignatureFromFunDef(FunDefCode funDefCode) {
        FunctionSignature functionSignature = new FunctionSignature(funDefCode.ident_);
        for (Arg arg : funDefCode.listarg_) {
            functionSignature.addArgument(((ArgCode) arg).ident_, ((ArgCode) arg).type_);
        }
        return functionSignature;
    }

    //previsit tworzy i zapisuje pełną sygnaturę klasy i metod (poza zmiennymi lokalnymi)
    public Type previsit(Class c, DeclarationContext declarationContext) {
        ListClassStmt classStmts;
        String className;
        ClassSignature classSignature;

        if (c instanceof ClassCode) {
            classStmts = ((ClassCode) c).listclassstmt_;
            className = ((ClassCode) c).ident_;

        } else if (c instanceof ClassExt) {
            ClassSignature.setSuperClass(((ClassExt) c).ident_1, ((ClassExt) c).ident_2);

            classStmts = ((ClassExt) c).listclassstmt_;
            className = ((ClassExt) c).ident_1;
        } else {
            throw new RuntimeException("Unsuported Class subclass");
        }
        classSignature = new ClassSignature(className);

        for (ClassStmt classStmt : classStmts) {
            if (classStmt instanceof Fields) {
                for (String ident : ((Fields) classStmt).listident_) {
                    classSignature.addField(ident, ((Fields) classStmt).type_);
                }
            } else if (classStmt instanceof Method) {
                FunDefCode funDefCode = (FunDefCode) (((Method) classStmt).fundef_);
                FunctionSignature methodSignature = functionSignatureFromFunDef(funDefCode);
                classSignature.addMethodSignature(methodSignature);
            }
        }
        return new ClassType(className);

    }

    @Override
    public Type visit(ProgramCode p, DeclarationContext arg) {
//Before analyizing each function/class we must add all other functions/class to scope
        for (TopDef x : p.listtopdef_) {
            if (x instanceof FnDef) {
                FunctionSignature functionSignature = functionSignatureFromFunDef(topDefToFunDefCode(x));
                arg = arg.declareNewVar(topDefToFunDefCode(x).ident_, arg.functionToFunctionType(topDefToFunDefCode(x)), p.line_num, p.col_num);
            } else if (x instanceof ClassDef) {
                previsit(((ClassDef) x).class_, arg);
            }
        }
        for (TopDef x : p.listtopdef_) {
            x.accept(this, arg);
        }
        return null;
    }

    @Override
    public Type visit(FunDefCode p, DeclarationContext arg) {
        arg = arg.newScope(p.type_);
        if (p.ident_.equals("main")) {
            if (!p.listarg_.isEmpty()) {
                throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Function main cannot take arguments"));
            }
            if (!(p.type_ instanceof Int)) {
                throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Function main must be of type \"Int\""));
            }
        }

        arg.setCurrentFunction(p.ident_, p.line_num);
        p.block_.accept(this, arg);
        return null;
    }


    @Override
    public Type visit(ClassCode p, DeclarationContext arg) {
        arg = arg.setCurrentClass(p.ident_);
        super.visit(p, arg);
        return null;
    }

    @Override
    public Type visit(ClassExt p, DeclarationContext arg) {
        arg = arg.setCurrentClass(p.ident_1);
        super.visit(p, arg);
        return null;
    }

    @Override
    public Type visit(Fields p, DeclarationContext arg) {
        for (String ident : p.listident_) {
            arg.declareNewVar(ident, p.type_, p.line_num, p.col_num);
        }
        return null;
    }

    @Override
    public Type visit(FieldT p, DeclarationContext arg) {
        Type classType = p.expr_.accept(this, arg);
        if (!(classType instanceof ClassType)) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Target is not an object"));
        }
        Type returnType = arg.getTypeOfField(((ClassType) classType).ident_, p.ident_, p.line_num, p.col_num);
        DeclarationContext.saveType(p, returnType);
        return returnType;
    }

    @Override
    public Type visit(ECast p, DeclarationContext arg) {
        Type exprType = p.accept(this, arg);
        if (!(exprType instanceof ClassType)) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Target is not an object"));
        }

        if (!arg.isSubclass(p.ident_, ((ClassType) exprType).ident_)) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, p.ident_ + " is not superclass odf " + ((ClassType) exprType).ident_));
        }
        ClassType classType = new ClassType(p.ident_);
        DeclarationContext.saveType(p, classType);
        return classType;
    }

    @Override
    public Type visit(ENewObj p, DeclarationContext arg) {
        ClassType type = new ClassType(p.ident_);
        arg.saveType(p, type);
        return type;
    }


    @Override
    public Type visit(ArgCode p, DeclarationContext arg) {
        if (p.type_ instanceof Void) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Function argument of type \"Void\" is forbidden"));
        }
        arg.addParamToFunction(p.ident_);
        arg = arg.declareNewVar(p.ident_, p.type_, p.line_num, p.col_num);
        return super.visit(p, arg);
    }

    @Override
    public Type visit(BlockCode p, DeclarationContext arg) {
        arg = arg.newScope();
        return super.visit(p, arg);
    }


    @Override
    public Type visit(Decl p, DeclarationContext arg) {
        if (p.type_ instanceof Void) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Cannot declare variable of type void"));

        }

        for (Item item : p.listitem_) {
            if (item instanceof Init) {
                Type lType = p.type_;
                Type rType = item.accept(this, arg);
                if (!lType.equals(rType)) {
                    throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num,
                            "Cannot assign " + rType.getClass().toString() + " to " + lType.getClass().toString()));
                }
            }
            arg = arg.declareNewVar(item.ident_, p.type_, p.line_num, p.col_num);
        }
        return super.visit(p, arg);
    }

    @Override
    public Type visit(Ass p, DeclarationContext arg) {
        Type rType = p.expr_.accept(this, arg);
        Type lType = p.target_.accept(this, arg);
        if (!rType.equals(lType)) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num,
                    "Cannot assign " + rType.getClass().toString() + " to " + lType.getClass().toString()));

        }
        return null;
    }

    @Override
    public Type visit(Incr p, DeclarationContext arg) {
        Type lType = p.target_.accept(this, arg);

        if (!lType.equals(new Int())) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Wrong type or undeclared variable"));
        }
        return null;
    }

    @Override
    public Type visit(Decr p, DeclarationContext arg) {
        Type lType = p.target_.accept(this, arg);

        if (!lType.equals(new Int())) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Wrong type or undeclared variable"));
        }
        return null;
    }

    @Override
    public Type visit(Ret p, DeclarationContext arg) {
        Type type = p.expr_.accept(this, arg);
        if (type instanceof Void) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Cannot return result of type void"));
        }
        if (!arg.checkResultType(type)) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Wrong result type"));
        }

        return type;
    }

    @Override
    public Type visit(Variable p, DeclarationContext arg) {
        return arg.getTypeOfVar(p.ident_, p.line_num, p.col_num);
    }


    @Override
    public Type visit(VRet p, DeclarationContext arg) {
        Type type = new Void();
        if (!arg.checkResultType(type)) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Wrong result type"));
        }
        return type;
    }

    @Override
    public Type visit(Cond p, DeclarationContext arg) {
        if (!p.expr_.accept(this, arg).equals(new Bool())) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Wrong type or undeclared variable (Cond)"));
        }
        return p.stmt_.accept(this, arg.newScope());
    }

    @Override
    public Type visit(CondElse p, DeclarationContext arg) {
        if (!p.expr_.accept(this, arg).equals(new Bool())) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Wrong type or undeclared variable (CondElse)"));
        }
        p.stmt_1.accept(this, arg.newScope());
        p.stmt_2.accept(this, arg.newScope());
        return null;
    }

    @Override
    public Type visit(While p, DeclarationContext arg) {
        if (!p.expr_.accept(this, arg).equals(new Bool())) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Wrong type or undeclared variable (While)"));
        }
        return p.stmt_.accept(this, arg.newScope());
    }


    @Override
    public Type visit(BStmt p, DeclarationContext arg) {
        arg = arg.newScope();
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
        Type type = p.target_.accept(this, arg);
        arg.saveType(p, type);
        return type;
    }

    //TODO I think no action is needed.
    @Override
    public Type visit(NoInit p, DeclarationContext arg) {
        return super.visit(p, arg);
    }

    @Override
    public Type visit(Init p, DeclarationContext arg) {
        Type rType = p.expr_.accept(this, arg);
        return rType;
    }


    @Override
    public Type visit(ELitInt p, DeclarationContext arg) {
        arg.saveType(p, new Int());
        return new Int();
    }

    @Override
    public Type visit(ELitTrue p, DeclarationContext arg) {
        arg.saveType(p, new Bool());
        return new Bool();
    }

    @Override
    public Type visit(ELitFalse p, DeclarationContext arg) {
        arg.saveType(p, new Bool());
        return new Bool();
    }

    @Override
    public Type visit(EApp p, DeclarationContext arg) {
        Type type = p.target_.accept(this, arg);
//        Type type = arg.getTypeOfVar(p.ident_, p.line_num, p.col_num);
        if (!(type instanceof Fun))
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, p.toString() + " is not a function"));
        Fun functionType = (Fun) type;
        List<Type> argumentsTypes = p.listexpr_.stream().map(exp -> exp.accept(this, arg)).collect(Collectors.toCollection(LinkedList::new));
        List<Type> expectedTypes = functionType.listtype_;

        if (!argumentsTypes.equals(expectedTypes)) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Arguments mismatch in function call"));
        }
        arg.saveType(p, functionType.type_);

        return functionType.type_;
    }

    @Override
    public Type visit(EString p, DeclarationContext arg) {
        arg.saveType(p, new Str());

        return new Str();
    }

    @Override
    public Type visit(Neg p, DeclarationContext arg) {
        if (super.visit(p, arg) instanceof Int) {
            arg.saveType(p, new Int());
            return new Int();
        } else {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Wrong type"));
        }
    }

    @Override
    public Type visit(Not p, DeclarationContext arg) {
        if (super.visit(p, arg) instanceof Bool) {
            arg.saveType(p, new Bool());
            return new Bool();
        } else {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Wrong type"));
        }
    }

    private Type visitTrinaryOperation(Expr expr, Type expectedTypes, String errorMessage, DeclarationContext arg, Expr ex1, Expr ex2) {
        Type t1 = ex1.accept(this, arg);
        Type t2 = ex2.accept(this, arg);
        if (!(t1.equals(expectedTypes) && t2.equals(expectedTypes)))
            throw new RuntimeException(errorMessage);
        arg.saveType(expr, expectedTypes);

        return expectedTypes;
    }

    @Override
    public Type visit(EMul p, DeclarationContext arg) {
        return visitTrinaryOperation(p, new Int(), SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Wrong type in mul"), arg, p.expr_1, p.expr_2);
    }

    @Override
    public Type visit(EAdd p, DeclarationContext arg) {
        if (p.addop_ instanceof Minus)
            return visitTrinaryOperation(p, new Int(), SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Wrong type in add"), arg, p.expr_1, p.expr_2);
        else {
            try {
                return visitTrinaryOperation(p, new Int(), SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Wrong type in add"), arg, p.expr_1, p.expr_2);
            } catch (Exception exception) {
                return visitTrinaryOperation(p, new Str(), SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Wrong type in add"), arg, p.expr_1, p.expr_2);

            }
        }
    }

    @Override
    public Type visit(ERel p, DeclarationContext arg) {
        if (p.relop_ instanceof EQU || p.relop_ instanceof NE) {
            Type t1 = p.expr_1.accept(this, arg);
            Type t2 = p.expr_2.accept(this, arg);
            if (!t1.equals(t2)) {
                throw new RuntimeException(SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Wrong type in comparison"));
            }
            return new Bool();
        }
        visitTrinaryOperation(p, new Int(), SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Wrong type in comparison"), arg, p.expr_1, p.expr_2);
        arg.saveType(p, new Bool());

        return new Bool();

    }

    @Override
    public Type visit(EAnd p, DeclarationContext arg) {
        return visitTrinaryOperation(p, new Bool(), SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Wrong type in and"), arg, p.expr_1, p.expr_2);
    }

    @Override
    public Type visit(EOr p, DeclarationContext arg) {
        return visitTrinaryOperation(p, new Bool(), SemanticErrorMessage.buildMessage(p.line_num, p.col_num, "Wrong type in or"), arg, p.expr_1, p.expr_2);
    }


    @Override
    public Type combine(Type x, Type y, DeclarationContext arg) {
        return x;
    }
}
