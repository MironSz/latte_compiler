package assembly;

import frontend.ClassSignature;
import frontend.FunctionSignature;
import javafx.util.Pair;
import latte.Absyn.Expr;
import latte.Absyn.Type;
import quadCode.syntax.Block;
import quadCode.syntax.instructions.Instruction;
import quadCode.syntax.instructions.arguments.InstructionArgument;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class QuadCode {
    public Map<Instruction, Set<InstructionArgument>> alliveBefore;
    public Map<Instruction, Set<InstructionArgument>> alliveAfter;
    List<Block> blocks;
    private HashMap<Object, Type> types = new HashMap<>();
    private Map<String, FunctionSignature> functionSignatures;
    private Map<String, ClassSignature> classSignatures;
    Integer newVarCounter = 0;

    public Integer numberOfParamsInFunction(String function) {
        return functionSignatures.get(function).getParameters().size();
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


    public Integer getNewVarCounter() {
        return newVarCounter;
    }

    public QuadCode(List<Block> blocks, HashMap<Object, Type> types, Map<String, FunctionSignature> functionSignatures, Map<String, ClassSignature> classSignatures, Integer newVarCounter) {
        this.blocks = blocks;
        this.types = types;
        this.newVarCounter = newVarCounter;
        this.functionSignatures = functionSignatures;
        this.classSignatures = classSignatures;
        blocks.forEach(Block::removeClassFields);
    }

    public Set<String> allFunctions() {
        return functionSignatures.keySet();
    }

    public Type getType(Expr exp) {
        return types.get(exp);
    }


    public List<String> paramsInFunction(String function) {
        return functionSignatures.get(function).getParameters().stream().map(Pair::getKey).collect(Collectors.toList());
    }
}
