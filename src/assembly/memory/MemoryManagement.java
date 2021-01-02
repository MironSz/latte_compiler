package assembly.memory;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MemoryManagement {
    List<Register> registers;
    List<StackLocation> stackLocations;
    Map<String,StackLocation> adreses;
    Map<String, List<Register>> varToRegisters;
    Map<Register,List<String>> registerToVars;

    public MemoryManagement(List<Register> registers, List<StackLocation> stackLocations) {
        this.registers = registers;
        this.stackLocations = stackLocations;
    }

    public MemoryLocation getMemoryLocation(String varName){
        return null;
    }

    protected Register pickRegister(){
        return registers.get(0);
    }

    public List<Register> getRegistersForVar(String varName){
        return null;
    }
    public void addVarToRegister(String varName, Register register){

    }
    public void removeVarFromRegisters(String varName){

    }
    public void freeRegister(Register register){

    }
    public Register getSpecificRegister(String name){
        return null;
    }

    public Register getFreeRegister(String name){
        for(Register register: registers){
            if (register.getVarName().equals(name))
                return register;
        }
        for(Register register: registers){
            if (register.isFree())
                return register;
        }

        Register register = pickRegister();
        register.dumpData();
        return register;
    }

    public Register getRegisterContainingVar(String name){
        return null;
    }

    public void saveState(){

    }
    public void restoreState(){

    }


}
