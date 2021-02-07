package frontend;

import javafx.util.Pair;
import latte.Absyn.ClassType;
import latte.Absyn.Type;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FunctionSignature {
    private String name;
    private List<Pair<String, Type>> arguments;
    private List<Pair<String, Type>> parameters;
    private static Map<String, FunctionSignature> signatures = new HashMap<>();

    public FunctionSignature(String name) {
        this.name = name;
        arguments = new LinkedList<>();
        parameters = new LinkedList<>();
        signatures.put(name, this);
    }

    public static FunctionSignature getFunctionSignature(String name) {
        return signatures.getOrDefault(name, null);
    }

    public void addSelf(String className) {
        arguments.add(0, new Pair<>("self", new ClassType(className)));
    }

    public String getName() {
        return name;
    }

    public void addArgument(String name, Type type) {
        arguments.add(new Pair<>(name, type));
    }

    public void addParameter(String name, Type type) {
        parameters.add(new Pair<>(name, type));
    }

    public List<Pair<String, Type>> getArguments() {
        return new LinkedList<>(arguments);
    }

    public List<Pair<String, Type>> getParameters() {
        return new LinkedList<>(parameters);
    }

    public static Map<String, FunctionSignature> allSignatures() {
        return signatures;
    }

    public static List<String> allVars(String name) {
        FunctionSignature functionSignature = getFunctionSignature(name);
        List<String> result = new LinkedList<>();
        result.addAll(functionSignature.getArguments().stream().map(Pair::getKey).collect(Collectors.toList()));
        result.addAll(functionSignature.getParameters().stream().map(Pair::getKey).collect(Collectors.toList()));
        return result;
    }
}
