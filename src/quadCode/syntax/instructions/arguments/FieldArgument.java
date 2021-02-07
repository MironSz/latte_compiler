package quadCode.syntax.instructions.arguments;

import javafx.util.Pair;
import latte.Absyn.ClassType;
import quadCode.syntax.instructions.GetFieldInstruction;
import quadCode.syntax.instructions.Instruction;
import quadCode.syntax.instructions.PutFieldInstruction;

import java.util.LinkedList;
import java.util.List;

public class FieldArgument extends InstructionArgument {
    private ClassType classType;
    private InstructionArgument objectVar;
    private String fieldName;
    private static Integer tmpVarCounter = 0;

    public static String newTmpVar() {
        tmpVarCounter++;
        return "fieldTmp_" + tmpVarCounter;
    }

    public FieldArgument(ClassType classType, String fieldName, InstructionArgument objectVar) {
        this.classType = classType;
        this.fieldName = fieldName;
        this.objectVar = objectVar;
    }


    @Override
    public Pair<List<Instruction>, InstructionArgument> accessField() {
        Pair<List<Instruction>, InstructionArgument> recursiveAccess = objectVar.accessField();
//Z obiektu wskazywanego przez recursiveAccess._2 muszę doczytać odpowiednie pole

        List<Instruction> resultList = new LinkedList<>(recursiveAccess.getKey());
        GetFieldInstruction getFieldInstruction;

        VarArgument resultVariable = new VarArgument(FieldArgument.newTmpVar());
        FieldArgument tmpFieldArgument = new FieldArgument(classType, fieldName, recursiveAccess.getValue());

        getFieldInstruction = new GetFieldInstruction(tmpFieldArgument, resultVariable);

        resultList.add(getFieldInstruction);
        return new Pair<>(resultList, resultVariable);
    }

    @Override
    public Pair<List<Instruction>, InstructionArgument> storeField(InstructionArgument argument) {
        Pair<List<Instruction>, InstructionArgument> recursiveAccess = objectVar.accessField();
//        W obiekcie wskazywanym przez recursiveAccess._2 muszę na pole fieldName zapisać
        List<Instruction> resultList = new LinkedList<>(recursiveAccess.getKey());

        FieldArgument tmpFieldArgument = new FieldArgument(classType, fieldName, recursiveAccess.getValue());
        PutFieldInstruction putFieldInstruction = new PutFieldInstruction(tmpFieldArgument, argument);
        resultList.add(putFieldInstruction);

        return new Pair<>(resultList, tmpFieldArgument);
    }

    public String getFieldName() {
        return fieldName;
    }

    public InstructionArgument getObjectVar() {
        return objectVar;
    }

    public void setObjectVar(InstructionArgument objectVar) {
        this.objectVar = objectVar;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }
}
