package assembly.memory.locations;

import assembly.memory.MemoryLocation;

import java.util.Objects;

public class StackLocation extends MemoryLocation {
    ParamsOnStackCounter paramsOnStackCounter;

    final Integer shiftToStackPointer;

    public StackLocation(ParamsOnStackCounter paramsOnStackCounter, Integer shiftToStackPointer) {
        this.paramsOnStackCounter = paramsOnStackCounter;
        this.shiftToStackPointer = shiftToStackPointer;
    }

    @Override
    public String assemblyCode() {
        return "[rsp - "+(shiftToStackPointer+paramsOnStackCounter.getParamsOnStack())+"]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StackLocation that = (StackLocation) o;
        return Objects.equals(paramsOnStackCounter, that.paramsOnStackCounter) && Objects.equals(shiftToStackPointer, that.shiftToStackPointer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paramsOnStackCounter, shiftToStackPointer);
    }
}
