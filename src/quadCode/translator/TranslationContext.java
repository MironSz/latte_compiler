package quadCode.translator;

import quadCode.syntax.Block;
import quadCode.syntax.jumps.BlockJump;

public class TranslationContext {
    static Integer newVarCounter = 0;
    Block currentBlock;
    private ReturnType returnType;

    public void openNewBlock(String name) {
        currentBlock = new Block(name);
    }

    public void closeCurrentBlock(BlockJump blockJump) {
        currentBlock.setNextBlock(blockJump);
        currentBlock = null;
    }

    public String newLabel(String label) {
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
