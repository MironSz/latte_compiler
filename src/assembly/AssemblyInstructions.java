package assembly;

import assembly.memory.MemoryLocation;
import assembly.memory.locations.Register;

public class AssemblyInstructions {
    public static String movInstruction(MemoryLocation from, MemoryLocation to) {
        return "    mov " + to.assemblyCode() + "," + from.assemblyCode();
    }

    public static String movInstruction(String from, String to) {
        return "    mov " + to.toString() + "," + from.toString();
    }

    public static String pushInstruction(MemoryLocation x) {
        return "    push " + x.assemblyCode();
    }

    public static String mulInstruction(MemoryLocation x, MemoryLocation y) {
        return "    imul " + x.assemblyCode() + "," + y.assemblyCode();
    }

    // x+=y
    public static String addInstruction(MemoryLocation x, MemoryLocation y, boolean plus) {
        if (plus)
            return "    add " + x.assemblyCode() + "," + y.assemblyCode();
        else
            return "    sub " + x.assemblyCode() + "," + y.assemblyCode();

    }

    public static String callInstruction(String functionName) {
        return "    call " + functionName;
    }

    public static String label(String labelName) {
        return labelName + ":";
    }

    public static String jmpInstruction(String destination) {
        return "    jmp " + destination;
    }

    public static String cmpInstruction(MemoryLocation x, MemoryLocation y) {
        return "    cmp " + x.assemblyCode() + ", " + y.assemblyCode();
    }

    public static String resetRegister(Register register) {
        return "    xor " + register.assemblyCode() + "," + register.assemblyCode();
    }

    public static String divInstruction(MemoryLocation memoryLocation) {
        return "    div " + memoryLocation.assemblyCode();
    }

    public static String constantInstruction(String constant, String name) {
        return "    " + name + " dq " + constant.length() + "\n" + "    " + name + "_body" + " db `" + constant + "`,0xA";
    }

}
