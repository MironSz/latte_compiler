package assembly.memory;

import assembly.memory.locations.LitPseudoLocation;
import assembly.memory.locations.ParamsOnStackCounter;
import assembly.memory.locations.Register;
import assembly.memory.locations.StackLocation;
import quadCode.syntax.instructions.arguments.InstructionArgument;
import quadCode.syntax.instructions.arguments.LitArgument;
import quadCode.syntax.instructions.arguments.VarArgument;

import java.util.*;

import static assembly.AssemblyInstructions.addInstruction;
import static assembly.AssemblyInstructions.movInstruction;

public class MemoryManager extends Producer {
    List<Register> allRegisters;
    Map<String, LitPseudoLocation> constantLocations = new HashMap<>();

    Map<InstructionArgument, StackLocation> allocatedStackLocations;
    Map<InstructionArgument, List<Register>> varToRegister;
    Map<InstructionArgument, List<StackLocation>> varToStack;
    Map<InstructionArgument, List<LitPseudoLocation>> varToLit;
    Map<MemoryLocation, List<InstructionArgument>> locationContents;

    List<Register> lockedRegisters = new LinkedList<>();


    ParamsOnStackCounter paramsOnStackCounter;
    List<String> varsInFunction;
    String functionName;
    int numberOfParams;

    public MemoryManager(List<Register> allRegisters) {
        this.allRegisters = allRegisters;
    }


    public StackLocation getDefaultStackLocation(String varName) {
        return getDefaultStackLocation(new VarArgument(varName));

    }

    public StackLocation getDefaultStackLocation(InstructionArgument instructionArgument) {
        return allocatedStackLocations.get(instructionArgument);
    }

    public void initManagerForFunction(String functionName, List<String> varsInFunction, List<String> allLiterals, int numberOfParams) {
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
        }

        for (String literal : allLiterals) {
            LitPseudoLocation litPseudoLocation = constantLocations.getOrDefault(literal, new LitPseudoLocation(literal));
            locationContents.put(litPseudoLocation, new LinkedList<>());
        }

        for (String var : varsInFunction) {
            int varCount = varsInFunction.indexOf(var);
            StackLocation stackLocation = new StackLocation(paramsOnStackCounter, varsInFunction.size() - varCount);
            locationContents.put(stackLocation, new LinkedList<>());

            if (varCount < numberOfParams) {
                varToStack.put(new VarArgument(var), new LinkedList<>(Collections.singletonList(stackLocation)));
                varToLit.put(new VarArgument(var), new LinkedList<>());

                locationContents.get(stackLocation).add(new VarArgument(var));
            } else {
                varToStack.put(new VarArgument(var), new LinkedList<>());
                varToLit.put(new VarArgument(var), new LinkedList<>(Collections.singletonList(new LitPseudoLocation("0"))));

                locationContents.get(new LitPseudoLocation("0")).add(new VarArgument(var));
            }

            allocatedStackLocations.put(new VarArgument(var), stackLocation);
        }

        for (Register register : allRegisters) {
            locationContents.put(register, new LinkedList<>());
        }

    }

    public List<InstructionArgument> getArgumentsInLocation(MemoryLocation location) {
        return locationContents.getOrDefault(location, new LinkedList<>());
    }

    public List<MemoryLocation> getLocations(InstructionArgument argument) {
        if (argument instanceof LitArgument) {
            LitPseudoLocation location = new LitPseudoLocation(argument.assemblyName());
            locationContents.put(location, locationContents.getOrDefault(location, new LinkedList<>()));
            locationContents.get(location).add(argument);
            varToLit.put(argument, varToLit.getOrDefault(argument, new LinkedList<>()));
            varToLit.get(argument).add(location);
        }
        List<MemoryLocation> result = new LinkedList<>();
        result.addAll(varToRegister.getOrDefault(argument, new LinkedList<>()));
        result.addAll(varToStack.getOrDefault(argument, new LinkedList<>()));
        result.addAll(varToLit.getOrDefault(argument, new LinkedList<>()));

        return result;
    }

    public MemoryLocation getLocation(InstructionArgument argument) {
        if (argument instanceof LitArgument)
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
        List<MemoryLocation> yLocations = getLocations(y);
        removeVarFromOtherLocations(x, null);
        yLocations.forEach(memoryLocation -> addVarToSpecificLocation(memoryLocation, x));
    }

    public void removeVarFromLocation(InstructionArgument argument, MemoryLocation location) {
        if (!locationContents.get(location).contains(argument))
            return;

        varToRegister.get(argument).remove(location);
        varToStack.get(argument).remove(location);
        varToLit.get(argument).remove(location);

        locationContents.get(location).remove(argument);
    }

    public void freeRegister(Register register) {
        List<InstructionArgument> varsInCell = locationContents.get(register);
        if (varsInCell.isEmpty()) {
            return;
        }
// Jesli te informacje już są w innym miejscu to można je bezrpoblemowo wyrzucić z rejestru
        if (getLocations(varsInCell.get(0)).size() > 1) {
            for (InstructionArgument movedArgument : varsInCell) {
                varToRegister.get(movedArgument).remove(register);
                locationContents.get(register).remove(movedArgument);
            }
            return;
        }

        StackLocation locationToMove = null;

        for (InstructionArgument argumentInCell : varsInCell) {
            StackLocation correspondingCell = allocatedStackLocations.get(argumentInCell);
            if (locationContents.get(correspondingCell).isEmpty()) {
                locationToMove = correspondingCell;
                break;
            }
            locationToMove = correspondingCell;
        }
        varsInCell = null;
        freeStackLocation(locationToMove);
        varsInCell = new LinkedList<>(locationContents.get(register));

        emmitAssemblyInstruction(movInstruction(register, locationToMove));

        for (InstructionArgument movedArgument : varsInCell) {
            varToRegister.get(movedArgument).remove(register);
            varToStack.get(movedArgument).add(locationToMove);

            locationContents.get(register).remove(movedArgument);
            locationContents.get(locationToMove).add(movedArgument);
        }
    }

    public void freeStackLocation(StackLocation stackLocation) {
        List<InstructionArgument> varsInCell = locationContents.get(stackLocation);
        if (varsInCell.isEmpty()) {
            return;
        }

        if (getLocations(varsInCell.get(0)).size() > 1) {
            for (InstructionArgument movedArgument : varsInCell) {
                varToStack.get(movedArgument).remove(stackLocation);
                locationContents.get(stackLocation).remove(movedArgument);
            }
            return;
        }

        StackLocation locationToMove = null;
        for (InstructionArgument argumentInCell : varsInCell) {
            StackLocation correspondingCell = allocatedStackLocations.get(argumentInCell);
            if (locationContents.get(correspondingCell).isEmpty()) {
                locationToMove = correspondingCell;
                break;
            }
            locationToMove = correspondingCell;
        }
        freeStackLocation(locationToMove);
        varsInCell = new LinkedList<>(locationContents.get(stackLocation));

        for (InstructionArgument movedArgument : varsInCell) {
            varToStack.get(movedArgument).remove(stackLocation);
            varToStack.get(movedArgument).add(locationToMove);

            locationContents.get(stackLocation).remove(movedArgument);
            locationContents.get(locationToMove).add(movedArgument);
        }
        emmitAssemblyInstruction(movInstruction(stackLocation, locationToMove));
    }

    public void removeVarFromOtherLocations(InstructionArgument argument, MemoryLocation memoryLocation) {
        List<MemoryLocation> locationsToRemove = getLocations(argument);


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
            if (!varToLit.containsKey(var)) {
                varToLit.put(var, new LinkedList<>());
            }
            varToLit.get(var).add((LitPseudoLocation) location);
        }
        if (location instanceof Register) {
            if (!varToRegister.containsKey(var)) {
                varToRegister.put(var, new LinkedList<>());
            }
            varToRegister.get(var).add((Register) location);
        }
        if (location instanceof StackLocation) {
            if (!varToStack.containsKey(var)) {
                varToStack.put(var, new LinkedList<>());
            }
            varToStack.get(var).add((StackLocation) location);
        }
    }

    public void incrementParamsOnStackCounter() {
        paramsOnStackCounter.incrementParamsOnStack();
    }


    public Register getSpecificRegisterWithVar(String registerName, InstructionArgument argument, boolean removeFromOtherRegisters) {
        Register resultRegister = getSpecificRegister(registerName);

        if (!locationContents.get(resultRegister).contains(argument)) {
            freeRegister(resultRegister);

            MemoryLocation locationOfSpecifiedVar = getLocation(argument);
            String assemblyInstruction = movInstruction(locationOfSpecifiedVar, resultRegister);
            emmitAssemblyInstruction(assemblyInstruction);
        }

        if (removeFromOtherRegisters)
            removeVarFromOtherLocations(argument, resultRegister);

        return resultRegister;
    }

    public void lockRegister(Register register) {
        lockedRegisters.add(register);

    }

    public void unlockRegister(Register register) {
        lockedRegisters.remove(register);

    }

    public Register pickRegisterToFree() {
        for (Register register : allRegisters) {
            if (!lockedRegisters.contains(register)) {
                return register;
            }
        }
        throw new RuntimeException("All registers are locked");
    }

    public Register getFreeRegister() {
        for (Register register : allRegisters) {
            if (locationContents.get(register).isEmpty()) {
                return register;
            }
        }
        Register register = pickRegisterToFree();
        freeRegister(register);
        return register;
    }

    private boolean isVarAlive(InstructionArgument argument) {
        return true;
    }

    public Register getRegisterContaining(InstructionArgument argument, boolean duplicateIfOnlyValue) {
        Register resultRegister;
        if (!varToRegister.getOrDefault(argument, new LinkedList<>()).isEmpty()) {
            resultRegister = varToRegister.get(argument).get(0);
        } else {
            resultRegister = getFreeRegister();

            String assemblyInstruction = movInstruction(getLocation(argument), resultRegister);
            emmitAssemblyInstruction(assemblyInstruction);

            addVarToSpecificLocation(resultRegister, argument);
        }
        if (duplicateIfOnlyValue)
            if (isVarAlive(argument))
                if (getLocations(argument).size() == 1)
                    freeRegister(resultRegister);

        return resultRegister;

    }

    public void dumpAllDataToMem() {
        for (Register register : allRegisters) {
            freeRegister(register);
        }
        for (InstructionArgument argument : allocatedStackLocations.keySet()) {
            if (!getLocations(argument).contains(getDefaultStackLocation(argument))) {
                MemoryLocation currentLocation = getLocation(argument);
                emmitAssemblyInstruction(movInstruction(currentLocation, getDefaultStackLocation(argument)));
            }
        }
    }

    public void restoreAllData(Integer consumedParams) {
        paramsOnStackCounter.setParamsOnStack(paramsOnStackCounter.getParamsOnStack() - consumedParams);
        resetManager();
    }


    public void resetManager() {
        for (InstructionArgument argument : allocatedStackLocations.keySet()) {
            StackLocation stackLocation = getDefaultStackLocation(argument);
            addVarToSpecificLocation(stackLocation, argument);
            removeVarFromOtherLocations(argument, stackLocation);
        }
    }

//    public MemoryLocation getLengthOfString(InstructionArgument argument) {
//
//    }


    public void addConstant(LitPseudoLocation constantLocation, LitArgument litArgument) {
        constantLocations.put()
    }
}
