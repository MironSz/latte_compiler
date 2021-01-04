package quadCode.translator;

import quadCode.syntax.Block;
import quadCode.syntax.instructions.Instruction;
import quadCode.syntax.jumps.BlockJump;

import java.util.*;

public class TranslationContext {
    static Integer newVarCounter = 0;
    static String currentFunction;
    private static Map<String, List<String>> functionToVars = new HashMap<>();

    public static List<String> varsInFunction(String function) {
        if (!functionToVars.containsKey(function))
            functionToVars.put(function, new LinkedList<>());
        return functionToVars.get(function);
    }

    public static void addVarToFunction(String varname){
        if (!functionToVars.containsKey(currentFunction))
            functionToVars.put(currentFunction, new LinkedList<>());
        functionToVars.get(currentFunction).add(varname);
    }

    public static void setCurrentFunction(String currentFunction) {
        TranslationContext.currentFunction = currentFunction;
    }

    public static Set<String> allFunctions(){
        return functionToVars.keySet();
    }
    private ReturnType returnType;
    Block currentBlock;

    public void openNewBlock(String name) {
        currentBlock = new Block(name);
    }

    public void closeCurrentBlock(BlockJump blockJump) {
        currentBlock.setNextBlock(blockJump);
        currentBlock=null;
    }


    public String getNewResultVar() {
        newVarCounter++;
        return "tmp_var_" + newVarCounter;
    }

    public ReturnType getReturnType() {
        return returnType;
    }

    public void setReturnType(ReturnType returnType) {
        this.returnType = returnType;
    }
}
