package quadCode.syntax.jumps;

import quadCode.syntax.Block;
import quadCode.syntax.instructions.ReturnInstruction;

import java.util.LinkedList;
import java.util.List;

public class RetJump extends BlockJump {
    ReturnInstruction returnInstruction;

    public RetJump(ReturnInstruction returnInstruction) {
        this.returnInstruction = returnInstruction;
    }

    public ReturnInstruction getReturnInstruction() {
        return returnInstruction;
    }

    public void setReturnInstruction(ReturnInstruction returnInstruction) {
        this.returnInstruction = returnInstruction;
    }

    @Override
    public List<Block> allNextBlocks() {
        return new LinkedList<>();
    }
}
