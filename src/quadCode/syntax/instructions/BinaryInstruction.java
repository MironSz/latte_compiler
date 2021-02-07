package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import latte.Absyn.Expr;
import quadCode.syntax.instructions.arguments.InstructionArgument;
import quadCode.syntax.instructions.arguments.VarArgument;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BinaryInstruction extends Instruction {
    InstructionArgument leftVar, rightVar;
    InstructionArgument resultVar;
    Expr expr; // add,sub,mul, div, mod, or, and ...
    public boolean intCompare = false;
    public boolean strCompare = false;

    public BinaryInstruction(InstructionArgument leftVar, InstructionArgument rightVar, InstructionArgument resultVar, Expr expr, boolean intCompare, boolean strCompare) {
        this(leftVar, rightVar, resultVar, expr);
        this.intCompare = intCompare;
        this.strCompare = strCompare;
    }

    public BinaryInstruction(InstructionArgument leftVar, InstructionArgument rightVar, InstructionArgument resultVar, Expr expr) {

        this.leftVar = leftVar;
        this.rightVar = rightVar;
        this.resultVar = resultVar;
        this.expr = expr;
    }

    @Override
    public void changeArgument(InstructionArgument from, InstructionArgument to) {
        if (leftVar == from)
            leftVar = to;
        else if (rightVar == from)
            rightVar = to;
    }

    @Override
    public InstructionArgument getResultVar() {
        return resultVar;
    }

    @Override
    public void translate(AssemblyTranslator assemblyTranslator, MemoryManager memoryManagement) {
        assemblyTranslator.translate(this, memoryManagement);
    }

    @Override
    public List<String> allVarNamesInInstruction() {
        List<String> result = new LinkedList<>();
        if (leftVar instanceof VarArgument)
            result.add(leftVar.assemblyName());
        if (rightVar instanceof VarArgument)
            result.add(rightVar.assemblyName());
        result.add(resultVar.assemblyName());
        return result;
    }

    @Override
    public List<InstructionArgument> allArgsInInstruction() {
        return Arrays.asList(leftVar, rightVar);
    }


    @Override
    public String toString() {
        return resultVar + " = " + leftVar + " " + expr.getClass().toString() + " " + rightVar;
    }

    public InstructionArgument getLeftVar() {
        return leftVar;
    }

    public InstructionArgument getRightVar() {
        return rightVar;
    }

    public Expr getExpr() {
        return expr;
    }
}
