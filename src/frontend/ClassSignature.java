package frontend;

import javafx.util.Pair;
import latte.Absyn.Type;

import java.util.*;

public class ClassSignature {
    private static HashMap<String, ClassSignature> signatures = new HashMap<>();
    private static HashMap<String, String> superClasses = new HashMap<>();

    private String name;
    private List<Pair<String, Type>> fields;

    private List<FunctionSignature> methodSignatures;


    public static void checkHierarchyGraph() {
        for (String className : superClasses.keySet()) {
            Set<String> visited = new HashSet<>();
            visited.add(className);
            while (!(superClasses.get(className) == null) && !visited.contains(superClasses.get(className))) {
                className = superClasses.get(className);
                visited.add(className);
            }
            if (!(superClasses.get(className) == null)) {
                throw new RuntimeException("Cycle in class hierarchy graph");
            }
        }
    }

    public static ClassSignature getSignature(String className) {
        return signatures.get(className);
    }

    public ClassSignature(String name) {
        this.name = name;
        ClassSignature.signatures.put(name, this);
        superClasses.put(name, superClasses.getOrDefault(name, "null"));
        fields = new LinkedList<>();
        methodSignatures = new LinkedList<>();
    }

    public void addField(String name, Type type) {
        fields.add(new Pair<>(name, type));
    }


    public void addMethodSignature(FunctionSignature functionSignature) {
        functionSignature.addSelf(name);
        methodSignatures.add(functionSignature);
    }

    public ClassSignature getSuperSignature() {
        String superName = superClasses.get(name);
        if (name == null) {
            return new ClassSignature("null");
        }
        ClassSignature superSignature = signatures.get(superName);
        return superSignature;
    }

    public List<Pair<String, Type>> getFields() {
        if (name.equals("null")) {
            return new LinkedList<>();
        }
        List<Pair<String, Type>> superFields = getSuperSignature().getFields();
        superFields.addAll(fields);
        return superFields;
    }

    public List<FunctionSignature> getMethods() {
        if (name.equals("null")) {
            return new LinkedList<>();
        }
        List<FunctionSignature> superMethods = getSuperSignature().getMethods();

        for (FunctionSignature functionSignature : methodSignatures) {
            boolean overloaded = false;
            for (FunctionSignature superFunctionDignature : superMethods) {
                if (superFunctionDignature.getName().equals(functionSignature.getName())) {
                    int index = superMethods.indexOf(functionSignature);
                    superMethods.set(index, functionSignature);
                    overloaded = true;
                    break;
                }
            }
            if (!overloaded) {
                superMethods.add(functionSignature);
            }
        }
        return superMethods;
    }

    public static void setSuperClass(String subclass, String superclass) {
        superClasses.put(subclass, superclass);
    }

    public static boolean isSuperClass(String subclass, String superclass) {
        if (superClasses.get(subclass).equals("null"))
            return false;
        if (superClasses.get(subclass).equals(superclass))
            return true;
        return isSuperClass(superClasses.get(subclass), superclass);
    }
    public static Map<String,ClassSignature> allSignatures(){
        return signatures;
    }
}
