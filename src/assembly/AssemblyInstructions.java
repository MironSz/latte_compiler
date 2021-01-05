package assembly;

import assembly.memory.MemoryLocation;

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

    public static String label(String labelName){
        return labelName+":";
    }

    public static String jmpInstruction(String destination){
        return "    jmp "+destination;
    }

}
