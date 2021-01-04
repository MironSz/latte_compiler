package assembly.memory.locations;

public class ParamsOnStackCounter {
    private Integer paramsOnStack;

    public ParamsOnStackCounter(Integer paramsOnStack) {
        this.paramsOnStack = paramsOnStack;
    }

    public Integer getParamsOnStack() {
        return paramsOnStack;
    }

    public void setParamsOnStack(Integer paramsOnStack) {
        this.paramsOnStack = paramsOnStack;
    }

    public void incrementParamsOnStack() {
        paramsOnStack = paramsOnStack + 1;
    }
}
