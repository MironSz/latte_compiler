package assembly.memory.locations;

import assembly.memory.MemoryLocation;

public class ConstantLocation extends MemoryLocation {
    public String getConstantName() {
        return constantName;
    }

    public void setConstantName(String constantName) {
        this.constantName = constantName;
    }

    public ConstantLocation(String constantName) {
        this.constantName = constantName;
    }

    private String constantName;

    @Override
    public String assemblyCode() {
        return constantName;
    }
}
