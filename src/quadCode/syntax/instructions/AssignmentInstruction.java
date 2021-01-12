package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import quadCode.syntax.instructions.arguments.InstructionArgument;
import quadCode.syntax.instructions.arguments.VarArgument;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AssignmentInstruction extends Instruction {
    String leftVar;
    InstructionArgument rightVar;

    public AssignmentInstruction(String leftVar, InstructionArgument rightVar) {
        this.leftVar = leftVar;
        this.rightVar = rightVar;
    }

    public String getLeftVar() {
        return leftVar;
    }

    public InstructionArgument getRightVar() {
        return rightVar;
    }

    @Override
    public void setResultVar(String resultVar) {

    }

    @Override
    public String getResultVar() {
        return leftVar;
    }

    @Override
    public void translate(AssemblyTranslator assemblyTranslator, MemoryManager memoryManagement) {
        assemblyTranslator.translate(this,memoryManagement);
    }

    @Override
    public List<String> allVarNamesInInstruction() {
        if(rightVar instanceof VarArgument)
            return Arrays.asList(leftVar,rightVar.assemblyName());
        return Collections.singletonList(leftVar);
    }

    @Override
    public List<InstructionArgument> allArgsInInstruction() {
        return Collections.singletonList(rightVar);
    }
}
