package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import javafx.util.Pair;
import quadCode.syntax.instructions.arguments.InstructionArgument;

import java.util.LinkedList;
import java.util.List;

public abstract class Instruction {
    private Instruction nextInstruction = null;

    public abstract InstructionArgument getResultVar();

    public abstract void translate(AssemblyTranslator assemblyTranslator, MemoryManager memoryManagement);

    public abstract List<String> allVarNamesInInstruction();

    public abstract List<InstructionArgument> allArgsInInstruction();

    public Instruction getNextInstruction() {
        return nextInstruction;
    }

    public void addNextInstruction(Instruction instruction) {

    }

    public abstract void changeArgument(InstructionArgument from, InstructionArgument to);


    public List<Instruction> removeFields() {
        List<Instruction> instructions = new LinkedList<>();
        List<InstructionArgument> arguments = this.allArgsInInstruction();
        InstructionArgument resultVariable = getResultVar();
// Accesing fields
        for (InstructionArgument argument : arguments) {
            Pair<List<Instruction>, InstructionArgument> accessField = argument.accessField();
            instructions.addAll(accessField.getKey());
            changeArgument(argument, accessField.getValue());
        }
//        Original instruction with swapped fields
        instructions.add(this);

//        Add storing result in field
        if (resultVariable != null) {
            Pair<List<Instruction>, InstructionArgument> accessField = resultVariable.storeField(resultVariable);
            instructions.addAll(accessField.getKey());
        }


        return instructions;
    }

    public void setNextInstruction(Instruction nextInstruction) {
        this.nextInstruction = nextInstruction;
    }
}
