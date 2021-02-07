package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import quadCode.syntax.instructions.arguments.InstructionArgument;
import quadCode.syntax.instructions.arguments.VarArgument;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AssignmentInstruction extends Instruction {
    InstructionArgument leftVar;
    InstructionArgument rightVar;

    public AssignmentInstruction(InstructionArgument leftVar, InstructionArgument rightVar) {
        this.leftVar = leftVar;
        this.rightVar = rightVar;
    }


    public InstructionArgument getRightVar() {
        return rightVar;
    }


    @Override
    public InstructionArgument getResultVar() {
        return leftVar;
    }

    @Override
    public void translate(AssemblyTranslator assemblyTranslator, MemoryManager memoryManagement) {
        assemblyTranslator.translate(this, memoryManagement);
    }

    @Override
    public List<String> allVarNamesInInstruction() {
        if (rightVar instanceof VarArgument)
            return Arrays.asList(leftVar.assemblyName(), rightVar.assemblyName());
        return Collections.singletonList(leftVar.assemblyName());
    }

    @Override
    public List<InstructionArgument> allArgsInInstruction() {
        return Collections.singletonList(rightVar);
    }

    @Override
    public void changeArgument(InstructionArgument from, InstructionArgument to) {
        rightVar = to;
    }
}
