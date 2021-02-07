package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import quadCode.syntax.instructions.arguments.InstructionArgument;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NewObjectInstruction extends Instruction {
    private InstructionArgument resultVar;
    private String objectClass;

    public NewObjectInstruction(InstructionArgument resultVar, String objectClass) {
        this.resultVar = resultVar;
        this.objectClass = objectClass;
    }

    @Override
    public InstructionArgument getResultVar() {
        return resultVar;
    }

    @Override
    public void translate(AssemblyTranslator assemblyTranslator, MemoryManager memoryManagement) {

    }

    @Override
    public List<String> allVarNamesInInstruction() {
        return Arrays.asList(resultVar.toString());
    }

    @Override
    public List<InstructionArgument> allArgsInInstruction() {
        return new LinkedList<>();
    }

    @Override
    public void changeArgument(InstructionArgument from, InstructionArgument to) {

    }

    public String getObjectClass() {
        return objectClass;
    }
}
