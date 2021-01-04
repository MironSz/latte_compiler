package assembly.memory.locations;

import assembly.memory.MemoryLocation;

public class StackLocation extends MemoryLocation {
    Integer paramsOnStackCounter;

    final Integer shiftToStackPointer;

    public StackLocation(Integer paramsOnStackCounter, Integer shiftToStackPointer) {
        this.paramsOnStackCounter = paramsOnStackCounter;
        this.shiftToStackPointer = shiftToStackPointer;
    }

    @Override
    public String assemblyCode() {
        return "[rsp - "+(shiftToStackPointer+paramsOnStackCounter)+"]";
    }
}
