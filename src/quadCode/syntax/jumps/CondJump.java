package quadCode.syntax.jumps;

import quadCode.syntax.Block;

import java.util.Arrays;
import java.util.List;

public class CondJump extends BlockJump {
    String condition;
    SimpleJump trueJump, falseJump;

    @Override
    public List<Block> allNextBlocks() {
        return Arrays.asList(trueJump.nextBlock, falseJump.nextBlock);
    }
}
