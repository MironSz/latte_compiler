package assembly.memory;

import java.util.LinkedList;
import java.util.List;

public class Producer {
    public static List<String> instructions = new LinkedList<>();
    public void emmitAssemblyInstruction(String instruction){
        instructions.add(instruction);
    }
}
