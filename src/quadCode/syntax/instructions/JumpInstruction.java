package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import quadCode.syntax.instructions.arguments.InstructionArgument;

import java.util.List;
//Porównuje z jedynką
public class JumpInstruction extends Instruction {
    String destinationLabel;
    String jumpCommand;
    InstructionArgument argument;

    public JumpInstruction(String destinationLabel, String jumpCommand, InstructionArgument argument) {
        this.destinationLabel = destinationLabel;
        this.jumpCommand = jumpCommand;
        this.argument = argument;
    }
    public JumpInstruction(String destinationLabel){
        this(destinationLabel,null,null);
    }

    @Override
    public String getResultVar() {
        return null;
    }

    @Override
    public void setResultVar(String resultVar) {

    }

    @Override
    public void translate(AssemblyTranslator assemblyTranslator, MemoryManager memoryManagement) {
        assemblyTranslator.translate(this,memoryManagement);

    }

    @Override
    public List<String> allVarsInInstruction() {
        return null;
    }

    public String getDestinationLabel() {
        return destinationLabel;
    }

    public String getJumpCommand() {
        return jumpCommand;
    }

    public InstructionArgument getArgument() {
        return argument;
    }
}
