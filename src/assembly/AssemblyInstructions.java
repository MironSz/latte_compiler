package assembly;

import assembly.memory.MemoryLocation;

public class AssemblyInstructions {
    public static String movInstruction(MemoryLocation from, MemoryLocation to) {
        return "mov " + from.assemblyCode() + "," + to.assemblyCode();
    }

    public static String movInstruction(String from, String to) {
        return "mov " + from.toString() + "," + to.toString();
    }

    public static String pushInstruction(MemoryLocation x) {
        return "push " + x.toString();
    }

    // x+=y
    public static String addInstruction(MemoryLocation x, MemoryLocation y, boolean plus) {
        if (plus)
            return "add " + x.assemblyCode() + "," + y.toString();
        else
            return "sub " + x.toString() + "," + y.toString();

    }
    public static String callInstruction(String functionName) {
        return "call " + functionName;
    }

}
