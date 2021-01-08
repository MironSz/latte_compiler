package quadCode.translator;

import quadCode.syntax.Block;
import quadCode.syntax.instructions.Instruction;
import quadCode.syntax.jumps.BlockJump;

public class TranslationContext {
    static Integer newVarCounter = 0;
    private Block currentBlock;
    private ReturnType returnType;

    public void openNewBlock(String name) {
        currentBlock = new Block(name);
    }


    public void closeCurrentBlock(BlockJump blockJump) {
        currentBlock.setNextBlock(blockJump);
        currentBlock = null;
    }

    public void addInstruction(Instruction instruction){
        if(currentBlock != null)
            currentBlock.addInstruction(instruction);
    }
    public Block getCurrentBlock(){
        return currentBlock;
    }
    public static String newConstName(){
        newVarCounter++;
        return "const_"+newVarCounter;
    }
    public static String newLabel(String label) {
        newVarCounter++;
        return "#" + label + "_" + newVarCounter;
    }

    public String getNewResultVar() {
        newVarCounter++;
        return "#tmp_var_" + newVarCounter;
    }

    public ReturnType getReturnType() {
        return returnType;
    }

    public void setReturnType(ReturnType returnType) {
        this.returnType = returnType;
    }
}
