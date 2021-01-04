package assembly.memory;

import assembly.memory.locations.LitPseudoLocation;
import assembly.memory.locations.Register;
import assembly.memory.locations.StackLocation;
import quadCode.syntax.instructions.InstructionArgument;
import quadCode.syntax.instructions.LitArgument;
import quadCode.syntax.instructions.VarArgument;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MemoryManagement {
    List<Register> registers;
    List<StackLocation> stackLocations;
    Map<String, StackLocation> adreses;
    Map<String, List<Register>> varToRegisters;
    Map<Register, List<String>> registerToVars;

    public MemoryManagement(List<Register> registers, List<StackLocation> stackLocations) {
        this.registers = registers;
        this.stackLocations = stackLocations;
    }

    public MemoryLocation getMemoryLocation(InstructionArgument arg) {
        if (arg instanceof LitArgument)
            return new LitPseudoLocation(arg.assemblyName());
        else
            return null;
    }

    public MemoryLocation getMemoryLocation(String varName) {
        return null;
    }

    protected Register pickRegister() {
        return registers.get(0);
    }

    public List<Register> getRegistersForVar(InstructionArgument argument) {
        if (argument instanceof VarArgument)
            return varToRegisters.getOrDefault(((VarArgument) argument).getVarName(), new LinkedList<>());
        else
            return new LinkedList<>();
    }

    public void addVarToRegister(String varName, Register register) {
        register.addVar(varName);
    }

    public void removeVarFromRegisters(String varName) {

    }

    public void freeRegister(Register register) {

    }

    public Register getSpecificRegister(String name) {
        return null;
    }

    public Register getFreeRegister() {
        for (Register register : registers) {
            if (register.isFree())
                return register;
        }

        Register register = pickRegister();
        register.dumpData();
        return register;
    }

    public Register getRegisterContainingVar(String name) {
        return null;
    }

    public void saveState() {

    }

    public void restoreState() {

    }


}
