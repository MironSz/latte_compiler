package quadCode.syntax.jumps;

import quadCode.syntax.Block;

import java.util.Arrays;
import java.util.List;

public class SimpleJump extends BlockJump {
    Block nextBlock;
    @Override
    public List<Block> allNextBlocks() {
        return Arrays.asList(nextBlock);
    }

    public Block getNextBlock() {
        return nextBlock;
    }

    public void setNextBlock(Block nextBlock) {
        this.nextBlock = nextBlock;
    }

    @Override
    public String toString() {
        return "    goto "+nextBlock.getLabel();
    }
}
