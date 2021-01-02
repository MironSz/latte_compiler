package assembly.memory;

public class Register extends MemoryLocation{
    String registerName;

    public Register(String registerName) {
        this.registerName = registerName;
    }
}
