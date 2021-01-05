package quadCode.translator;

import quadCode.syntax.instructions.arguments.InstructionArgument;

public class ReturnType {
    private InstructionArgument resultVar;
//    List<Instruction> instructions;

    public ReturnType(InstructionArgument resultVar) {
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

    public InstructionArgument getResultVar() {
        return resultVar;
    }

    public void setResultVar(InstructionArgument name) {
        resultVar = name;
//        instructions.get(instructions.size() - 1).setResultVar(name);
    }
}
