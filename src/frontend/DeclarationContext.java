package frontend;

import latte.Absyn.Void;
import latte.Absyn.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.stream.Collectors;

public class DeclarationContext {
    private static HashMap<Serializable, DeclarationContext> scopeToDeclaration;
    private final HashMap<String, Type> nameToType;
    private Type expectedResultType;
    private DeclarationContext parent;
//    private final InstrOrderContext instrOrderContext;

    public DeclarationContext() {
//        this.instrOrderContext = instrOrderContext;
        nameToType = new HashMap<>();

        ListType printStringTypes = new ListType();
        printStringTypes.add(new Str());

        ListType printIntTypes = new ListType();
        printIntTypes.add(new Int());


        nameToType.put("printString", new Fun(new Void(), printStringTypes));
        nameToType.put("printInt", new Fun(new Void(), printIntTypes));
        nameToType.put("error", new Fun(new Void(), new ListType()));
        nameToType.put("readInt", new Fun(new Int(), new ListType()));
        nameToType.put("readString", new Fun(new Str(), new ListType()));

        expectedResultType = null;
    }

    private DeclarationContext(DeclarationContext declarationContext) {
        this();
        this.parent = declarationContext;
    }

    public Fun functionToFunctionType(FnDef fnDef) {
        return new Fun(fnDef.type_, fnDef.listarg_.stream().map(arg -> ((ArgCode) arg).type_).collect(Collectors.toCollection(ListType::new)));
    }


    public Type getTypeOfVar(String name, int line,int col) {
        for (DeclarationContext currentContext = this; currentContext != null; currentContext = currentContext.parent) {
            if (currentContext.nameToType.containsKey(name)) {
                return currentContext.nameToType.get(name);
            }
        }
        throw new RuntimeException(SemanticErrorMessage.buildMessage(line,col,"Variable "+name+" undeclared"));
    }

    public DeclarationContext newScope() {
        return new DeclarationContext(this);
    }

    public DeclarationContext newScope(Type expectedResultType) {
        DeclarationContext result = this.newScope();
        result.expectedResultType = expectedResultType;
        return result;
    }

    public boolean checkResultType(Type type) {
        for (DeclarationContext currentContext = this; currentContext != null; currentContext = currentContext.parent) {
            if (currentContext.expectedResultType != null)
                return type.equals(currentContext.expectedResultType);
        }
        return false;
    }


    public DeclarationContext declareNewVar(String name, Type type, int line, int col) {
//        DeclarationContext result = this.newScope();
        DeclarationContext result = this;
        if (result.nameToType.containsKey(name)) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(line,col,"Variable "+name+" previously declared"));
        }
        result.nameToType.put(name, type);
        return result;
    }

    public boolean isDeclared(String name, Type type, int line, int col) {
        Type type2 = this.getTypeOfVar(name,line,col);
        return type.equals(type2);
    }
}
