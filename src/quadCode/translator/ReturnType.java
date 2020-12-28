package quadCode.translator;

import quadCode.syntax.instructions.Instruction;

import java.util.List;

 class ReturnType{
    String resultVar;
    List<Instruction> instructions;

     public ReturnType(String resultVar, List<Instruction> instructions) {
         this.resultVar = resultVar;
         this.instructions = instructions;
     }

     public void setResultVar(String name){
         instructions.get(instructions.size()-1).setResultVar(name);
     }
     public void addInstruction(Instruction instruction){
//         TODO
     }
 }
