package quadCode.syntax.jumps;

import quadCode.syntax.Block;

import java.util.Arrays;
import java.util.List;

public class CondJump extends BlockJump {
    String condition;
    Block trueBlock,falseBlock;
//    SimpleJump trueJump, falseJump;

    @Override
    public List<Block> allNextBlocks() {
        return Arrays.asList(trueBlock,falseBlock);
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Block getTrueBlock() {
        return trueBlock;
    }

    public void setTrueBlock(Block trueBlock) {
        this.trueBlock = trueBlock;
    }

    public Block getFalseBlock() {
        return falseBlock;
    }

    public void setFalseBlock(Block falseBlock) {
        this.falseBlock = falseBlock;
    }

    @Override
    public String toString() {
        return "    if "+condition+" goto "+trueBlock.getLabel()+" else goto "+falseBlock.getLabel();
    }
}
