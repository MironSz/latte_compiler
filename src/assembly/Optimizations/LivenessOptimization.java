package assembly.Optimizations;

import assembly.Optimization;
import assembly.QuadCode;
import quadCode.syntax.Block;
import quadCode.syntax.instructions.Instruction;
import quadCode.syntax.instructions.arguments.InstructionArgument;
import quadCode.syntax.instructions.arguments.VarArgument;
import quadCode.syntax.instructions.arguments.VoidArgument;
import quadCode.syntax.jumps.RetJump;

import java.util.*;

public class LivenessOptimization extends Optimization {

    private boolean changeDetected = false;

    @Override
    public QuadCode optimize(QuadCode quadCode) {
        if (quadCode.alliveAfter == null) {
            changeDetected = true;
            quadCode.alliveAfter = new HashMap<>();
            quadCode.alliveBefore = new HashMap<>();
            for (Block block : quadCode.getBlocks()) {
                for (Instruction instruction : block.getInstructions()) {
                    quadCode.alliveAfter.put(instruction, new HashSet<>());
                    quadCode.alliveBefore.put(instruction, new HashSet<>());
                }
            }
        }
        Map<Block, Set<InstructionArgument>> aliveBeforeBlocks = new HashMap<>();

        do {
            changeDetected = false;
            Set<Block> visitedBlocks = new HashSet<>();
            for (Block block : quadCode.getBlocks()) {
                optimize(block, quadCode, visitedBlocks, aliveBeforeBlocks);
            }
        } while (changeDetected);


        return quadCode;
    }

    private Set<InstructionArgument> inOutEquation(Set<InstructionArgument> out, Set<InstructionArgument> used, Set<InstructionArgument> killed) {
        Set<InstructionArgument> in = new HashSet<>(used);

        for (InstructionArgument argument : out) {
            if (!killed.contains(argument))
                in.add(argument);
        }

        return in;
    }

    private void optimize(Block block, QuadCode quadCode, Set<Block> visitedBlocks, Map<Block, Set<InstructionArgument>> aliveBeforeBlocks) {
        Set<InstructionArgument> alliveBeforeBlock = new HashSet<>();
// Don't visit the same block twice during one iteration
        if (visitedBlocks.contains(block))
            return;
        visitedBlocks.add(block);

        for (Block nextBlock : block.getNextBlock().allNextBlocks()) {
            optimize(nextBlock, quadCode, visitedBlocks, aliveBeforeBlocks);
            alliveBeforeBlock.addAll(aliveBeforeBlocks.getOrDefault(nextBlock, new HashSet<>()));
        }

        ListIterator instructionIterator = block.getInstructions().listIterator(block.getInstructions().size());
        Set<InstructionArgument> prevOut = alliveBeforeBlock;

        if (block.getNextBlock() instanceof RetJump) {
            if (!(((RetJump) block.getNextBlock()).getReturnInstruction().getResultVariable() instanceof VoidArgument)){
                prevOut.add(((RetJump) block.getNextBlock()).getReturnInstruction().getResultVariable());
            }
        }
        while (instructionIterator.hasPrevious()) {
            Instruction instruction = (Instruction) instructionIterator.previous();

            Set<InstructionArgument> used = new HashSet<>(instruction.allArgsInInstruction());
            Set<InstructionArgument> killed = new HashSet<>();
            if (instruction.getResultVar() != null && !instruction.getResultVar().equals("")) {
                killed.add(new VarArgument(instruction.getResultVar()));
            }

            if (!quadCode.alliveAfter.getOrDefault(instruction, new HashSet<>()).equals(prevOut)) {
                changeDetected = true;
            }

            quadCode.alliveAfter.put(instruction, prevOut);

            prevOut = inOutEquation(prevOut, used, killed);
            if (!quadCode.alliveBefore.getOrDefault(instruction, new HashSet<>()).equals(prevOut)) {
                changeDetected = true;
            }
            quadCode.alliveBefore.put(instruction, prevOut);
        }
        aliveBeforeBlocks.put(block, prevOut);
    }

    @Override
    public boolean changeDetected() {
        return changeDetected;
    }
}
