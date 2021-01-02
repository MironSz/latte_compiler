package quadCode.translator;

import quadCode.syntax.instructions.Instruction;

import java.util.List;

public class ReturnType {
    private String resultVar;
//    List<Instruction> instructions;

    public ReturnType(String resultVar) {
        this.resultVar = resultVar;
//        this.instructions = instructions;
    }
//    public ReturnType(String resultVar, List<Instruction> instructions) {
//        this.resultVar = resultVar;
//        this.instructions = instructions;
//    }

//    public void addInstruction(Instruction instruction) {
//        instructions.add(instruction);
//        resultVar = instruction.getResultVar();
//    }

    public String getResultVar() {
        return resultVar;
    }

    public void setResultVar(String name) {
        resultVar = name;
//        instructions.get(instructions.size() - 1).setResultVar(name);
    }
}
