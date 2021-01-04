package assembly.memory;

import assembly.memory.locations.LitPseudoLocation;
import assembly.memory.locations.ParamsOnStackCounter;
import assembly.memory.locations.Register;
import assembly.memory.locations.StackLocation;
import quadCode.syntax.instructions.InstructionArgument;
import quadCode.syntax.instructions.LitArgument;
import quadCode.syntax.instructions.VarArgument;

import java.util.*;

import static assembly.AssemblyInstructions.addInstruction;
import static assembly.AssemblyInstructions.movInstruction;

public class MemoryManager extends Producer {
    List<Register> allRegisters;

    Map<InstructionArgument, StackLocation> allocatedStackLocations;
    Map<InstructionArgument, List<Register>> varToRegister;
    Map<InstructionArgument, List<StackLocation>> varToStack;
    Map<InstructionArgument, List<LitPseudoLocation>> varToLit;
    Map<MemoryLocation, List<InstructionArgument>> locationContents;

    ParamsOnStackCounter paramsOnStackCounter;
    List<String> varsInFunction;
    String functionName;
    int numberOfParams;

    public MemoryManager(List<Register> allRegisters) {
        this.allRegisters = allRegisters;
    }

    public StackLocation getDefaultStackLocation(InstructionArgument instructionArgument) {
        return allocatedStackLocations.get(instructionArgument);
    }

    public void initManagerForFunction(String functionName, List<String> varsInFunction, int numberOfParams) {
        this.functionName = functionName;
        this.varsInFunction = varsInFunction;
        this.numberOfParams = numberOfParams;

        locationContents = new HashMap<>();
        varToRegister = new HashMap<>();
        varToLit = new HashMap<>();
        varToStack = new HashMap<>();
        allocatedStackLocations = new HashMap<>();
        paramsOnStackCounter = new ParamsOnStackCounter(0);

        String incStackAssemblyInstruction = addInstruction(
                new Register("esp"),
                new LitPseudoLocation(String.valueOf(varsInFunction.size() - numberOfParams)),
                false);

        emmitAssemblyInstruction(incStackAssemblyInstruction);

        for (String var : varsInFunction) {
            varToRegister.put(new VarArgument(var), new LinkedList<>());
            varToLit.put(new VarArgument(var), new LinkedList<>());
        }

        for (String var : varsInFunction) {
            int varCount = varsInFunction.indexOf(var);
            StackLocation stackLocation = new StackLocation(paramsOnStackCounter, varsInFunction.size());
            varToStack.put(new VarArgument(var), Collections.singletonList(stackLocation));
            locationContents.put(stackLocation, Collections.singletonList(new VarArgument(var)));
            allocatedStackLocations.put(new VarArgument(var), stackLocation);
        }

        for (Register register: allRegisters){
            locationContents.put(register,new LinkedList<>());
        }

    }

    public List<InstructionArgument> getArgumentsInLocation(MemoryLocation location) {
        return locationContents.getOrDefault(location, new LinkedList<>());
    }

    public List<MemoryLocation> getLocations(InstructionArgument argument) {
        List<MemoryLocation> result = new LinkedList<>();
        result.addAll(varToRegister.getOrDefault(argument, new LinkedList<>()));
        result.addAll(varToStack.getOrDefault(argument, new LinkedList<>()));
        result.addAll(varToLit.getOrDefault(argument, new LinkedList<>()));

        return result;
    }

    public MemoryLocation getLocation(InstructionArgument argument) {
        if(argument instanceof LitArgument)
            return new LitPseudoLocation(argument.assemblyName());
        return getLocations(argument).get(0);

    }

    public Register getSpecificRegister(String name) {
        for (Register register : allRegisters) {
            if (register.getRegisterName().equals(name)) {
                return register;
            }
        }
        throw new RuntimeException("Register does not exist");
    }

    public void equate(InstructionArgument x, InstructionArgument y) {
        if (x.equals(y))
            return;
//        x=y;
        List<MemoryLocation> yLocations = getLocations(y);
        removeVarFromOtherLocations(x, null);
        yLocations.forEach(memoryLocation -> addVarToSpecificLocation(memoryLocation, x));
    }

    public void removeVarFromLocation(InstructionArgument argument, MemoryLocation location) {
        if (!locationContents.get(location).contains(argument))
            return;

        List<MemoryLocation> locations = getLocations(argument);

        varToRegister.get(argument).remove(location);
        varToStack.get(argument).remove(location);
        varToLit.get(argument).remove(location);

        if (locations.size() == 1 && location.equals(locations.get(0))) {
            String assemblyInstruction = movInstruction(location, getDefaultStackLocation(argument));
            emmitAssemblyInstruction(assemblyInstruction);
            addVarToSpecificLocation(getDefaultStackLocation(argument), argument);
        }
    }

    public void removeVarFromOtherLocations(InstructionArgument argument, MemoryLocation memoryLocation) {
        List<MemoryLocation> locationsToRemove = new LinkedList<>();
        locationsToRemove.addAll(varToLit.get(argument));
        locationsToRemove.addAll(varToStack.get(argument));
        locationsToRemove.addAll(varToRegister.get(argument));

        locationsToRemove.forEach(locationToRemove -> {
            if (!locationToRemove.equals(memoryLocation)) {
                removeVarFromLocation(argument, locationToRemove);
            }
        });
    }
/*
    Advanced setter
 */
    public void addVarToSpecificLocation(MemoryLocation location, InstructionArgument var) {
        locationContents.get(location).add(var);

        if (location instanceof LitPseudoLocation) {
            varToLit.get(var).add((LitPseudoLocation) location);
        }
        if (location instanceof Register) {
            varToRegister.get(var).add((Register) location);
        }
        if (location instanceof StackLocation) {
            varToStack.get(var).add((StackLocation) location);
        }
    }

    public void incrementParamsOnStackCounter() {
        paramsOnStackCounter.incrementParamsOnStack();
    }

    public void freeRegister(Register register, boolean tryToMoveToRegister) {
        List<InstructionArgument> valuesToRemoveFromResultRegister = new LinkedList<>();
        valuesToRemoveFromResultRegister.addAll(locationContents.get(register));
        valuesToRemoveFromResultRegister.forEach(arg -> {
            removeVarFromLocation(arg, register);
        });
    }

    public Register getSpecificRegisterWithVar(String registerName, InstructionArgument argument, boolean removeFromOtherRegisters) {
        Register resultRegister = allRegisters
                .stream()
                .filter(register -> register.getRegisterName().equals(registerName))
                .findFirst()
                .get();

        if (!locationContents.get(resultRegister).contains(argument)) {
            freeRegister(resultRegister, true);

            MemoryLocation locationOfSpecifiedVar = getLocation(argument);
            String assemblyInstruction = movInstruction(locationOfSpecifiedVar, resultRegister);
            emmitAssemblyInstruction(assemblyInstruction);
        }

        if (removeFromOtherRegisters)
            removeVarFromOtherLocations(argument, resultRegister);

        return resultRegister;
    }

    public Register pickRegisterToFree() {
        return allRegisters.get(2);
    }

    public Register getFreeRegister() {
        for (Register register : allRegisters) {
            if (locationContents.get(register).isEmpty()) {
                return register;
            }
        }
        Register register = pickRegisterToFree();
        freeRegister(register, true);
        return register;
    }

    public Register getRegisterContaining(InstructionArgument argument, boolean dumpData, boolean removeFromOtherRegisters) {
        Register resultRegister;
        if (!varToRegister.get(argument).isEmpty()) {
            resultRegister = varToRegister.get(argument).get(0);
        } else {
            resultRegister = getFreeRegister();

            String assemblyInstruction = movInstruction(getLocation(argument), resultRegister);
            emmitAssemblyInstruction(assemblyInstruction);

            addVarToSpecificLocation(resultRegister, argument);
        }

        if (removeFromOtherRegisters)
            removeVarFromOtherLocations(argument, resultRegister);

        return resultRegister;

    }

    public void dumpAllDataToMem() {
        for (Register register : allRegisters) {
            freeRegister(register, false);
        }
    }

    public void restoreAllData(Integer consumedParams) {
        paramsOnStackCounter.setParamsOnStack(paramsOnStackCounter.getParamsOnStack() - consumedParams);
    }


}
