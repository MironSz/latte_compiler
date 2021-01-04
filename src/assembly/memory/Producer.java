package assembly.memory;

import java.util.LinkedList;
import java.util.List;

public class Producer {
    public static List<String> instructions = new LinkedList<>();
    protected void emmitAssemblyInstruction(String instruction){

        instructions.add(instruction);
        System.out.println(instruction);
    }
}
