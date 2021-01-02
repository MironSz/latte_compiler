package assembly.memory;

public class MemoryLocation extends Producer {
    String varName;
    public void assignVar(String varName){

    }

    public String getVarName() {
        return varName;
    }

    public void dumpData(){
    }
    public boolean isFree(){
        return varName==null;
    }
}
