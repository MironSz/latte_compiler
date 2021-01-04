package assembly.memory.locations;

import assembly.memory.MemoryLocation;
import latte.Absyn.Str;
import quadCode.syntax.instructions.InstructionArgument;

import java.util.List;

public class Register extends MemoryLocation {
    String registerName;

    public void addVar(String varName){

    }

    public String getRegisterName() {
        return registerName;
    }

    public Register(String registerName) {
        this.registerName = registerName;
    }

    @Override
    public String assemblyCode() {
        return registerName;
    }

}
