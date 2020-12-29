package quadCode.syntax.instructions;

public class ReturnInstruction extends Instruction {
    String resultVariable;

    public ReturnInstruction(String resultVariable) {
        this.resultVariable = resultVariable;
    }

    @Override
    public void setResultVar(String resultVar) {

    }

    @Override
    public String getResultVar() {
        return "Wrong usage";
    }

    @Override
    public String toString() {
        return "return "+resultVariable;
    }
}
