package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import quadCode.syntax.instructions.arguments.FieldArgument;
import quadCode.syntax.instructions.arguments.InstructionArgument;

import java.util.Arrays;
import java.util.List;

public class PutFieldInstruction extends Instruction {
    private FieldArgument fieldArgument;
    private InstructionArgument resultVar;

    @Override
    public InstructionArgument getResultVar() {
        return fieldArgument.getObjectVar();
    }

    public PutFieldInstruction(FieldArgument to, InstructionArgument from) {
        this.fieldArgument = to;
        this.resultVar = from;
    }

    @Override
    public void translate(AssemblyTranslator assemblyTranslator, MemoryManager memoryManagement) {

    }

    @Override
    public List<String> allVarNamesInInstruction() {
        return Arrays.asList(fieldArgument.getObjectVar().assemblyName(), resultVar.assemblyName());
    }

    @Override
    public List<InstructionArgument> allArgsInInstruction() {
        return Arrays.asList(fieldArgument.getObjectVar());
    }

    @Override
    public void changeArgument(InstructionArgument from, InstructionArgument to) {
        throw new RuntimeException("Wrong usage of method");
    }
}
