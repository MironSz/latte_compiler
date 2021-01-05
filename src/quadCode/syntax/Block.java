package quadCode.syntax;

import quadCode.syntax.instructions.Instruction;
import quadCode.syntax.jumps.BlockJump;
import quadCode.syntax.jumps.NoJump;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Block {
    public static Set<Block> allBlocks = new HashSet<>();
    Set<Block> prevBlocks;
    String label;

    List<Instruction> instructions;
    BlockJump nextBlock = new NoJump();


    public Block(String label) {
        allBlocks.add(this);
        this.instructions = new LinkedList<>();
        this.label = label;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(label + ":\n");
        for (Instruction instruction : instructions) {
            result.append("    ").append(instruction.toString()).append("\n");
        }
        result.append(nextBlock);
        return result.toString();
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public void addInstructions(List<Instruction> instructions) {
        this.instructions.addAll(instructions);
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void addInstruction(Instruction instruction) {
        this.instructions.add(instruction);
    }

    public void setResultVar(String name){
        instructions.get(instructions.size()-1).setResultVar(name);
    }

    public String getLabel() {
        return label;
    }

    public BlockJump getNextBlock() {
        return nextBlock;
    }

    public void setNextBlock(BlockJump nextBlock) {
        this.nextBlock = nextBlock;
    }
}
