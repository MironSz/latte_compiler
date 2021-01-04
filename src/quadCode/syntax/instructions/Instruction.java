package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import quadCode.translator.TranslatorVisitor;

public abstract class Instruction {
    public abstract void setResultVar(String resultVar);
    public abstract String getResultVar();
    public abstract void translate(AssemblyTranslator assemblyTranslator, MemoryManager memoryManagement);

}
