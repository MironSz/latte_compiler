package quadCode.translator;

import quadCode.syntax.Block;
import quadCode.syntax.instructions.Instruction;
import quadCode.syntax.jumps.BlockJump;

import java.util.List;

public class TranslationContext {
    static Integer newVarCounter = 0;
    private ReturnType returnType;
    Block currentBlock;

    public void openNewBlock(String name) {
        currentBlock = new Block(name);
    }

    public void closeCurrentBlock(BlockJump blockJump) {
        currentBlock.setNextBlock(blockJump);
        currentBlock=null;
    }

    public void closeCurrentBlock(List<Instruction> instructions) {
//        currentBlock.setInstructions(instructions);
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
