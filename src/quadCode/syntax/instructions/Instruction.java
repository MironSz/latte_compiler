package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import quadCode.syntax.instructions.arguments.InstructionArgument;
import quadCode.syntax.instructions.arguments.VarArgument;
import quadCode.translator.TranslatorVisitor;

import java.util.List;

public abstract class Instruction {
    public abstract void setResultVar(String resultVar);
    public abstract String getResultVar();
    public abstract void translate(AssemblyTranslator assemblyTranslator, MemoryManager memoryManagement);
    public abstract List<String> allVarNamesInInstruction();
    public abstract List<InstructionArgument> allArgsInInstruction();

}
