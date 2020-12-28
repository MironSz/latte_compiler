package quadCode.syntax;

import quadCode.syntax.instructions.Instruction;
import quadCode.syntax.jumps.BlockJump;
import quadCode.syntax.jumps.SimpleJump;

import java.util.List;
import java.util.Set;

public class Block {
    static Set<Block> allBlocks;
    Set<Block> prevBlocks;
    String label;

    List<Instruction> instructions;
    BlockJump nextBlock;


    public Block(String label) {
        this.label = label;
    }


    private void doMerge() {
        allBlocks.remove(this.nextBlock.allNextBlocks().get(0));

        for (Block block : nextBlock.allNextBlocks().get(0).nextBlock.allNextBlocks()) {
            block.prevBlocks.remove(nextBlock.allNextBlocks().get(0));
            block.prevBlocks.add(this);
        }
        this.instructions.addAll(nextBlock.allNextBlocks().get(0).instructions);
        this.nextBlock = nextBlock.allNextBlocks().get(0).nextBlock;
    }

    public void mergeBlocks() {
        nextBlock.allNextBlocks().forEach(Block::mergeBlocks);
        if (nextBlock instanceof SimpleJump && nextBlock.allNextBlocks().get(0).prevBlocks.size() == 1) {
            doMerge();
        }

    }
}
