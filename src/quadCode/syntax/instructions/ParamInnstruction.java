package quadCode.syntax.instructions;

public class ParamInnstruction extends Instruction{
    String param;

    public ParamInnstruction(String param) {
        this.param = param;
    }

    @Override
    public void setResultVar(String resultVar) {

    }

    @Override
    public String getResultVar() {
        return null;
    }
}
