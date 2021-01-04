package assembly.memory;

import assembly.memory.locations.LitPseudoLocation;
import assembly.memory.locations.Register;
import assembly.memory.locations.StackLocation;
import quadCode.syntax.instructions.InstructionArgument;

import java.util.List;
import java.util.Map;

public class MemoryManager2 extends Producer {
    List<Register> allRegisters;
    Map<InstructionArgument, List<Register>> varToRegister;
    Map<InstructionArgument, List<StackLocation>> varToStack;
    Map<InstructionArgument, List<LitPseudoLocation>> varToLit;
    Integer paramsOnStackCounter;

    public List<MemoryLocation> getLocations(InstructionArgument argument) {

    }
    public MemoryLocation getLocation(InstructionArgument argument){

    }
    public Register getSpecificRegister(String name){

    }

    public void equate(InstructionArgument x, InstructionArgument y) {

    }

    public void removeVarFromOtherLocations(InstructionArgument argument, MemoryLocation memoryLocation) {

    }
    public void addVarToSpecificLocation(MemoryLocation location, InstructionArgument var){

    }
    public void incrementParamsOnStackCounter(){
        paramsOnStackCounter++;
    }

    public Register getSpecificRegisterWithVar(String registerName, String varName, boolean removeFromOtherRegisters ){

    }
    public Register getRegisterContaining(InstructionArgument argument, boolean dumpData, boolean removeFromOtherRegisters) {

    }

    public void dumpAllDataToMem() {

    }

    public void restoreAllData() {
    }


}
