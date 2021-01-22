package assembly;

import latte.Absyn.Expr;
import latte.Absyn.Type;
import quadCode.syntax.Block;
import quadCode.syntax.instructions.Instruction;
import quadCode.syntax.instructions.arguments.InstructionArgument;

import java.util.*;

public class QuadCode {
    public Map<Instruction, Set<InstructionArgument>> alliveBefore;
    public Map<Instruction, Set<InstructionArgument>> alliveAfter;
    List<Block> blocks;
    private HashMap<Object, Type> types = new HashMap<>();
    private Map<String, List<String>> paramsInFunction = new HashMap<>();
    Integer newVarCounter = 0;

    public Integer numberOfParamsInFunction(String function) {
        return paramsInFunction.getOrDefault(function, new LinkedList<>()).size();
    }

    public Map<Instruction, Set<InstructionArgument>> getAlliveBefore() {
        return alliveBefore;
    }

    public Map<Instruction, Set<InstructionArgument>> getAlliveAfter() {
        return alliveAfter;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public HashMap<Object, Type> getTypes() {
        return types;
    }

    public Map<String, List<String>> getParamsInFunction() {
        return paramsInFunction;
    }

    public Integer getNewVarCounter() {
        return newVarCounter;
    }

    public QuadCode(List<Block> blocks, HashMap<Object, Type> types, Map<String, List<String>> paramsInFunction, Integer newVarCounter) {
        this.blocks = blocks;
        this.types = types;
        this.paramsInFunction = paramsInFunction;
        this.newVarCounter = newVarCounter;
    }

    public Set<String> allFunctions() {
        return paramsInFunction.keySet();
    }

    public Type getType(Expr exp) {
        return types.get(exp);
    }

    public void addParamToFunction(String param, String currentFunction) {
        if (!paramsInFunction.containsKey(currentFunction))
            paramsInFunction.put(currentFunction, new LinkedList<>());
        paramsInFunction.get(currentFunction).add(param);
    }

    public List<String> paramsInFunction(String function) {
        return new LinkedList<>(paramsInFunction.getOrDefault(function, new LinkedList<>()));
    }
}
