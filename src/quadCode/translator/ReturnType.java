package quadCode.translator;

import quadCode.syntax.instructions.arguments.InstructionArgument;

public class ReturnType {
    private InstructionArgument resultVar;

    public ReturnType(InstructionArgument resultVar) {
        this.resultVar = resultVar;
    }

    public InstructionArgument getResultVar() {
        return resultVar;
    }

    public void setResultVar(InstructionArgument name) {
        resultVar = name;
    }
}
