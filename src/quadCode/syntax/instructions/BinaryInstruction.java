package quadCode.syntax.instructions;

import assembly.AssemblyTranslator;
import assembly.memory.MemoryManagement;
import latte.Absyn.Expr;

public class BinaryInstruction extends Instruction {
    InstructionArgument leftVar, rightVar;
    String resultVar;
    Expr expr; // add,sub,mul, div, mod, or, and ...


    public BinaryInstruction(InstructionArgument leftVar, InstructionArgument rightVar, String resultVar, Expr expr) {
        this.leftVar = leftVar;
        this.rightVar = rightVar;
        this.resultVar = resultVar;
        this.expr = expr;
    }

    @Override
    public String getResultVar() {
        return resultVar;
    }

    @Override
    public void translate(AssemblyTranslator assemblyTranslator, MemoryManagement memoryManagement) {
        assemblyTranslator.translate(this,memoryManagement);
    }

    @Override
    public void setResultVar(String resultVar) {
        this.resultVar = resultVar;
    }

    @Override
    public String toString() {
        return resultVar+" = "+leftVar+ " "+expr.getClass().toString()+" "+rightVar;
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
