package quadCode.syntax.instructions.arguments;

import javafx.util.Pair;
import quadCode.syntax.instructions.Instruction;

import java.util.LinkedList;
import java.util.List;

public class InstructionArgument {
    public String assemblyName() {
        return "";
    }

    public Pair<List<Instruction>, InstructionArgument> accessField() {
        return new Pair<>(new LinkedList<>(), this);
    }

    public Pair<List<Instruction>, InstructionArgument> storeField(InstructionArgument argument) {
        return new Pair<>(new LinkedList<>(), argument);
    }


}
