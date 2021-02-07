package frontend;

import javafx.util.Pair;
import latte.Absyn.Void;
import latte.Absyn.*;

import java.util.*;
import java.util.stream.Collectors;

public class DeclarationContext {
    private static HashMap<Object, Type> types = new HashMap<>();
    private static Map<String, List<String>> paramsInFunction = new HashMap<>();
    private final HashMap<String, Type> nameToType;
    private Type expectedResultType;
    private DeclarationContext parent;

    private ClassSignature currentClass;
    private FunctionSignature currentFunction;


    public DeclarationContext setCurrentClass(String name) {
        DeclarationContext declarationContext = new DeclarationContext(this);
        declarationContext.currentClass = new ClassSignature(name);

        return declarationContext;
    }


    public static HashMap<Object, Type> getTypes() {
        return types;
    }

    public static Map<String, List<String>> getParamsInFunction() {
        return paramsInFunction;
    }


    public DeclarationContext() {
        nameToType = new HashMap<>();

        ListType printStringTypes = new ListType();
        printStringTypes.add(new Str());

        ListType printIntTypes = new ListType();
        printIntTypes.add(new Int());

        paramsInFunction.put("printString", Collections.singletonList("s"));
        paramsInFunction.put("printInt", Collections.singletonList("i"));
        paramsInFunction.put("addTwoStrings", Arrays.asList("s1", "s2"));

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
        this.currentFunction = declarationContext.currentFunction;
        this.currentClass = declarationContext.currentClass;
    }


    public void addParamToFunction(String param) {
        currentFunction.addParameter(param, nameToType.get(param));
    }


    public static void clearTypesMap() {
        types.clear();
        paramsInFunction.clear();
    }

    public static void saveType(Object object, Type type) {
        types.put(object, type);
    }

    public Fun functionToFunctionType(FunDefCode fnDef) {
        return new Fun(fnDef.type_, fnDef.listarg_.stream().map(arg -> ((ArgCode) arg).type_).collect(Collectors.toCollection(ListType::new)));
    }

    public void setCurrentFunction(String currentFunctionName, int line) {
        this.currentFunction = FunctionSignature.getFunctionSignature(currentFunctionName);
        if (currentFunction == null) {
            throw new RuntimeException(SemanticErrorMessage.buildMessage(line, 0, "Function " + currentFunctionName + " is not defined"));
        }

        for (Pair<String, Type> stringTypePair : currentFunction.getArguments()) {
            declareNewVar(stringTypePair.getKey(), stringTypePair.getValue(), line, 0);
        }
    }

    public FunctionSignature getCurrentFunction() {
        return currentFunction;
    }

    public Type getTypeOfVar(String name, int line, int col) {
        for (DeclarationContext currentContext = this; currentContext != null; currentContext = currentContext.parent) {
            if (currentContext.nameToType.containsKey(name)) {
                return currentContext.nameToType.get(name);
            }
        }
        throw new RuntimeException(SemanticErrorMessage.buildMessage(line, col, "Variable " + name + " undeclared"));
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
            throw new RuntimeException(SemanticErrorMessage.buildMessage(line, col, "Variable " + name + " previously declared"));
        }
        if(currentFunction!=null)
            currentFunction.addParameter(name, type);
        result.nameToType.put(name, type);
        return result;
    }

    public boolean isDeclared(String name, Type type, int line, int col) {
        Type type2 = this.getTypeOfVar(name, line, col);
        return type.equals(type2);
    }

    public Type getTypeOfField(String className, String fieldName, int line_num, int col_num) {
        Optional<Pair<String, Type>> match = ClassSignature.getSignature(className).getFields()
                .stream()
                .filter(pair -> pair.getKey().equals(fieldName))
                .findFirst();
        if (!match.isPresent())
            throw new RuntimeException(SemanticErrorMessage.buildMessage(line_num, col_num, "Field " + fieldName + " is not present in class " + className));
        return match.get().getValue();
    }

    public boolean isSubclass(String subclass, String superclass) {
        return ClassSignature.isSuperClass(subclass, superclass);
    }
}
