package quadCode.syntax.jumps;

import quadCode.syntax.Block;

import java.util.LinkedList;
import java.util.List;

public class NoJump extends BlockJump{
    @Override
    public List<Block> allNextBlocks() {
        return new LinkedList<>();
    }
}
