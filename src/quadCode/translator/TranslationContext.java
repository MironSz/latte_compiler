package quadCode.translator;

import quadCode.syntax.Block;
import quadCode.syntax.instructions.Instruction;
import quadCode.syntax.jumps.BlockJump;

import java.util.List;

public class TranslationContext {
    Block currentBlock;
    Block prevBlock;

    public void openNewBlock(String name){

    }

    public void closeCurrentBlock(BlockJump blockJump){

    }
    public void closeCurrentBlock(List<Instruction> instructions){

    }

    public String getNewResultVar(){
        return "TODO_resultVar";
    }


}
