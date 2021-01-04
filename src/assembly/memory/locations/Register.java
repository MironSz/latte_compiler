package assembly.memory.locations;

import assembly.memory.MemoryLocation;

import java.util.Objects;

public class Register extends MemoryLocation {
    String registerName;

    public Register(String registerName) {
        this.registerName = registerName;
    }

    public String getRegisterName() {
        return registerName;
    }

    @Override
    public String assemblyCode() {
        return registerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Register register = (Register) o;
        return Objects.equals(registerName, register.registerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registerName);
    }
}
