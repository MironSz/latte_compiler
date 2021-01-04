package quadCode.syntax.instructions;

import latte.Absyn.ELitInt;

public class VarArgument extends InstructionArgument{
    String varName;

    public String getVarName() {
        return varName;
    }
    @Override
    public String assemblyName() {
        return varName;
    }
    public void setVarName(String varName) {
        this.varName = varName;
    }

    public VarArgument(String varName) {
        this.varName = varName;
    }
}
