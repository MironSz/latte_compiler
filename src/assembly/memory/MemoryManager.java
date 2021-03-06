package assembly.memory;

import assembly.QuadCode;
import assembly.memory.locations.LitPseudoLocation;
import assembly.memory.locations.ParamsOnStackCounter;
import assembly.memory.locations.Register;
import assembly.memory.locations.StackLocation;
import quadCode.syntax.instructions.Instruction;
import quadCode.syntax.instructions.arguments.InstructionArgument;
import quadCode.syntax.instructions.arguments.LitArgument;
import quadCode.syntax.instructions.arguments.VarArgument;

import java.util.*;

import static assembly.AssemblyInstructions.addInstruction;
import static assembly.AssemblyInstructions.movInstruction;

public class MemoryManager extends Producer {
    QuadCode quadCode;
    List<Register> allRegisters;
    List<Register> preservedRegisters;

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

    public List<Register> getPreservedRegisters() {
        return preservedRegisters;
    }

    public void setPreservedRegisters(List<Register> preservedRegisters) {
        this.preservedRegisters = preservedRegisters;
    }

    public List<Register> getAllRegisters() {
        return allRegisters;
    }

    public MemoryManager(List<Register> allRegisters, QuadCode quadCode) {
        this.allRegisters = allRegisters;
        this.quadCode = quadCode;
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
                new Register("rsp"),
                new LitPseudoLocation(String.valueOf((varsInFunction.size() - numberOfParams) * 8)),
                false);

        emmitAssemblyInstruction(incStackAssemblyInstruction);

        for (String var : varsInFunction) {
            varToRegister.put(new VarArgument(var), new LinkedList<>());
        }
        locationContents.put(new LitPseudoLocation("0"), new LinkedList<>());

        for (String literal : allLiterals) {
            LitPseudoLocation litPseudoLocation = constantLocations.getOrDefault(literal, new LitPseudoLocation(literal));
            locationContents.put(litPseudoLocation, new LinkedList<>());
        }

        for (String var : varsInFunction) {
            int varCount = varsInFunction.indexOf(var);
            StackLocation stackLocation;

            if (varCount < numberOfParams)
                stackLocation = new StackLocation(paramsOnStackCounter, varsInFunction.size() - varCount);
            else
                stackLocation = new StackLocation(paramsOnStackCounter, varsInFunction.size() - varCount - 1);
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

    public MemoryLocation getLocation(InstructionArgument argument, Instruction instruction) {
        if (argument instanceof LitArgument)
            return new LitPseudoLocation(argument.assemblyName());
        List<MemoryLocation> memoryLocations = getLocations(argument);
        if (memoryLocations.isEmpty()) {
            throw new RuntimeException(argument.assemblyName() + " is not in any location during " + instruction);
        }
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

        varToRegister.getOrDefault(argument, new LinkedList<>()).remove(location);
        varToStack.getOrDefault(argument, new LinkedList<>()).remove(location);
        varToLit.getOrDefault(argument, new LinkedList<>()).remove(location);

        locationContents.get(location).remove(argument);
    }

    public void freeRegister(Register register, Instruction instruction) {

        List<InstructionArgument> varsInCell = new LinkedList<>(locationContents.get(register));
        for (InstructionArgument instructionArgument : varsInCell) {
            if (!isVarAlive(instructionArgument, instruction)) {
                removeVarFromOtherLocations(instructionArgument, null);
            }
        }
        varsInCell = new LinkedList<>(locationContents.get(register));

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


    public Register getSpecificRegisterWithVar(String registerName, InstructionArgument argument, boolean removeFromOtherRegisters, Instruction instruction) {
        Register resultRegister = getSpecificRegister(registerName);

        if (!locationContents.get(resultRegister).contains(argument)) {
            freeRegister(resultRegister, instruction);

            MemoryLocation locationOfSpecifiedVar = getLocation(argument, null);
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

    private int howLongUnusedVar(Instruction instruction, InstructionArgument argument) {
        if (!isVarAlive(argument, instruction))
            return Integer.MAX_VALUE;
        int result = 0;
        Instruction ni;
        for (ni = instruction.getNextInstruction();
             !ni.allArgsInInstruction().contains(argument) && ni.getNextInstruction() != null;
             ni = ni.getNextInstruction())
            result++;
        if (ni.allArgsInInstruction().contains(argument))
            return result;
        else
            return Integer.MAX_VALUE;
    }

    private int howLongUnusedRegister(Instruction instruction, Register register) {
        return locationContents
                .get(register)
                .stream()
                .map(argument -> howLongUnusedVar(instruction, argument))
                .min(Integer::compareTo)
                .orElse(Integer.MAX_VALUE);
    }

    public Register pickRegisterToFree(Instruction instruction) {
        for (Register register : allRegisters) {
            if (!lockedRegisters.contains(register)) {
                List<InstructionArgument> argsInRegister = locationContents.get(register);
                boolean registerContainsLiveVar = false;
                for (InstructionArgument argument : argsInRegister) {
                    if (isVarAlive(argument, instruction)) {
                        registerContainsLiveVar = true;
                    }
                }
                if (!registerContainsLiveVar)
                    return register;
            }
        }

        Register result = null;
        int longestWait = -1;

        for (Register register : allRegisters) {
            if (!lockedRegisters.contains(register)) {
                int wait = howLongUnusedRegister(instruction, register);
                if (wait > longestWait) {
                    longestWait = wait;
                    result = register;
                }
            }
        }
        if (longestWait != -1)
            return result;
        throw new RuntimeException("All registers are locked");
    }

    public Register getFreeRegister(Instruction instruction) {
        for (Register register : allRegisters) {
            if (locationContents.get(register).isEmpty() && !lockedRegisters.contains(register)) {
                return register;
            }
        }
        Register register = pickRegisterToFree(instruction);
        freeRegister(register, instruction);
        return register;
    }

    private boolean isVarAlive(InstructionArgument argument, Instruction instruction) {
        return quadCode.alliveAfter.getOrDefault(instruction, new HashSet<>()).contains(argument);
    }

    public Register getRegisterContaining(InstructionArgument argument, boolean duplicateIfOnlyValue, Instruction instruction) {
        Register resultRegister;
        if (!varToRegister.getOrDefault(argument, new LinkedList<>()).isEmpty()) {
            resultRegister = varToRegister.get(argument).get(0);
        } else {
            resultRegister = getFreeRegister(instruction);

            String assemblyInstruction = movInstruction(getLocation(argument, instruction), resultRegister);
            emmitAssemblyInstruction(assemblyInstruction);

            addVarToSpecificLocation(resultRegister, argument);
        }
        if (duplicateIfOnlyValue)
            if (isVarAlive(argument, instruction))
                if (getLocations(argument).size() == 1)
                    freeRegister(resultRegister, instruction);

        return resultRegister;

    }

    public void dumpAllDataToMem(Instruction instruction) {
        for (Register register : allRegisters) {
            freeRegister(register, instruction);
        }
        for (InstructionArgument argument : allocatedStackLocations.keySet()) {
            if (isVarAlive(argument, instruction)) {
                if (!getLocations(argument).contains(getDefaultStackLocation(argument))) {
                    MemoryLocation currentLocation = getLocation(argument, instruction);
                    emmitAssemblyInstruction(movInstruction(currentLocation, getDefaultStackLocation(argument)));
                }
            }
        }
    }

    public void restoreAllData(Integer consumedParams) {
        paramsOnStackCounter.setParamsOnStack(paramsOnStackCounter.getParamsOnStack() - consumedParams);
        emmitAssemblyInstruction(addInstruction(new Register("rsp"), new LitPseudoLocation(String.valueOf(consumedParams * 8)), true));
        resetManager();
    }


    public void resetManager() {
        for (InstructionArgument argument : allocatedStackLocations.keySet()) {
            StackLocation stackLocation = getDefaultStackLocation(argument);
            addVarToSpecificLocation(stackLocation, argument);
            removeVarFromOtherLocations(argument, stackLocation);
        }
    }


    public void addConstant(LitPseudoLocation constantLocation, LitArgument litArgument) {
//        constantLocations.put()
    }
}
