package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import quadCode.syntax.instructions.arguments.FieldArgument;
import quadCode.syntax.instructions.arguments.InstructionArgument;
import quadCode.syntax.instructions.arguments.VarArgument;

import java.util.Arrays;
import java.util.List;

public class GetFieldInstruction extends Instruction {
    private FieldArgument fieldArgument;
    private VarArgument resultVar;

    public GetFieldInstruction(FieldArgument from, VarArgument to) {
        this.fieldArgument = from;
        this.resultVar = to;
    }

    @Override
    public InstructionArgument getResultVar() {
        return resultVar;
    }

    @Override
    public void translate(AssemblyTranslator assemblyTranslator, MemoryManager memoryManagement) {
        throw new RuntimeException();
    }

    @Override
    public List<String> allVarNamesInInstruction() {
        return Arrays.asList(fieldArgument.getObjectVar().assemblyName(), resultVar.assemblyName());
    }

    @Override
    public List<InstructionArgument> allArgsInInstruction() {
        return Arrays.asList(fieldArgument);
    }

    @Override
    public void changeArgument(InstructionArgument from, InstructionArgument to) {
        throw new RuntimeException("Wrong usage of method");
    }
}
