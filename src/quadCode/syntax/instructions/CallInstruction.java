package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import latte.Absyn.FnDef;
import quadCode.syntax.instructions.arguments.InstructionArgument;
import quadCode.translator.TranslationContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CallInstruction extends Instruction{
    InstructionArgument resultVar;
    InstructionArgument function;

    public CallInstruction(InstructionArgument resultVar, InstructionArgument function) {

        this.resultVar = resultVar;
        this.function = function;
    }

    @Override
    public void changeArgument(InstructionArgument from, InstructionArgument to) {
        if (resultVar == from)
            resultVar = to;
        else if (function == from)
            function = to;
    }
    @Override
    public InstructionArgument getResultVar() {
        return resultVar;
    }

    @Override
    public void translate(AssemblyTranslator assemblyTranslator, MemoryManager memoryManagement) {
        assemblyTranslator.translate(this,memoryManagement);
    }

    @Override
    public List<String> allVarNamesInInstruction() {
        if( resultVar==null || resultVar.equals(""))
            return new LinkedList<>();
        return Collections.singletonList(resultVar.assemblyName());
    }

    @Override
    public List<InstructionArgument> allArgsInInstruction() {
        return new LinkedList<>();
    }

    public InstructionArgument getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return resultVar+" = "+"Call "+function;
    }
}
