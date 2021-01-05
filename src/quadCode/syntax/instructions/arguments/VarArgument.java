package quadCode.syntax.instructions.arguments;

import java.util.Objects;

public class VarArgument extends InstructionArgument {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VarArgument that = (VarArgument) o;
        return Objects.equals(varName, that.varName);
    }

    @Override
    public String toString() {
        return varName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(varName);
    }
}
