package assembly;

import assembly.memory.MemoryLocation;
import assembly.memory.MemoryManager;
import assembly.memory.locations.LitPseudoLocation;
import assembly.memory.locations.Register;
import assembly.memory.locations.StackLocation;
import latte.Absyn.*;
import quadCode.syntax.instructions.BinaryInstruction;
import quadCode.syntax.instructions.arguments.LitArgument;
import quadCode.syntax.instructions.arguments.VarArgument;
import quadCode.translator.TranslationContext;

import static assembly.AssemblyInstructions.*;

public class BinaryInstructionTranslator {

    static public void translateIntCmp(BinaryInstruction instruction, MemoryManager memoryManager, AssemblyTranslator assemblyTranslator) {
        String writeTrueLabel = TranslationContext.newLabel("WriteTrue");
        String finalLabel = TranslationContext.newLabel("FinalLabel");
        String condition = assemblyTranslator.translate(((ERel) instruction.getExpr()).relop_);

        StackLocation resultLocation = memoryManager.getDefaultStackLocation(instruction.getResultVar());
        Register leftRegister = memoryManager.getRegisterContaining(instruction.getLeftVar(), true);
        MemoryLocation rightVarLocation = memoryManager.getLocation(instruction.getRightVar());

        assemblyTranslator.emmitAssemblyInstruction(cmpInstruction(leftRegister, rightVarLocation));
        assemblyTranslator.emmitAssemblyInstruction("    " + condition + " " + writeTrueLabel);
        assemblyTranslator.emmitAssemblyInstruction(movInstruction("0", resultLocation.assemblyCode()));
        assemblyTranslator.emmitAssemblyInstruction("    jmp " + finalLabel);
        assemblyTranslator.emmitAssemblyInstruction(writeTrueLabel + ":");
        assemblyTranslator.emmitAssemblyInstruction(movInstruction("1", resultLocation.assemblyCode()));
        assemblyTranslator.emmitAssemblyInstruction(finalLabel + ":");

        memoryManager.addVarToSpecificLocation(resultLocation, new VarArgument(instruction.getResultVar()));
        memoryManager.removeVarFromOtherLocations(new VarArgument(instruction.getResultVar()), resultLocation);
    }

    static public void translateAddStr(BinaryInstruction instruction, MemoryManager memoryManager, AssemblyTranslator assemblyTranslator) {
        MemoryLocation leftLocation = memoryManager.getLocation(instruction.getLeftVar());
        MemoryLocation rightLocation = memoryManager.getLocation(instruction.getRightVar());
        memoryManager.dumpAllDataToMem();

        assemblyTranslator.emmitAssemblyInstruction(pushInstruction(leftLocation));
        memoryManager.incrementParamsOnStackCounter();

        assemblyTranslator.emmitAssemblyInstruction(pushInstruction(rightLocation));
        memoryManager.incrementParamsOnStackCounter();

        assemblyTranslator.emmitAssemblyInstruction("    call _addTwoStrings");

        memoryManager.restoreAllData(2);

        Register rax = memoryManager.getSpecificRegister("rax");
        memoryManager.addVarToSpecificLocation(rax, new VarArgument(instruction.getResultVar()));
        memoryManager.removeVarFromOtherLocations(new VarArgument(instruction.getResultVar()), rax);
    }

    static public void translateDiv(BinaryInstruction instruction, MemoryManager memoryManager, AssemblyTranslator assemblyTranslator) {
        VarArgument resultArgument = new VarArgument(instruction.getResultVar());
        Register rax = memoryManager.getSpecificRegisterWithVar("rax", instruction.getLeftVar(), false);
        memoryManager.lockRegister(rax);
        Register rdx = memoryManager.getSpecificRegisterWithVar("rdx", new LitArgument(new ELitInt(0)),false);
        memoryManager.lockRegister(rdx);
        memoryManager.freeRegister(rdx);

        assemblyTranslator.emmitAssemblyInstruction(resetRegister(rdx));

        MemoryLocation leftVarLocation = memoryManager.getLocation(instruction.getRightVar());
        if(leftVarLocation instanceof LitPseudoLocation){
            leftVarLocation = memoryManager.getRegisterContaining(instruction.getRightVar(),false);
        }

        assemblyTranslator.emmitAssemblyInstruction(divInstruction(leftVarLocation));

        memoryManager.removeVarFromOtherLocations(resultArgument,null);
        if (((EMul) instruction.getExpr()).mulop_ instanceof Div) {
            memoryManager.addVarToSpecificLocation(rax, resultArgument);
        } else {
            memoryManager.addVarToSpecificLocation(rdx, resultArgument);
        }
        memoryManager.unlockRegister(rdx);
        memoryManager.unlockRegister(rax);
    }

    static public void translateIntAddTimes(BinaryInstruction instruction, MemoryManager memoryManager, AssemblyTranslator assemblyTranslator) {

        Register resultRegister = memoryManager.getRegisterContaining(instruction.getLeftVar(), true);
        MemoryLocation rightVarLocation = memoryManager.getLocation(instruction.getRightVar());

        memoryManager.removeVarFromLocation(instruction.getLeftVar(), resultRegister);

        String assemblyInstruction;

        if (instruction.getExpr() instanceof EAdd)
            assemblyInstruction = addInstruction(resultRegister,
                    rightVarLocation,
                    ((EAdd) instruction.getExpr()).addop_ instanceof Plus);
        else
            assemblyInstruction = mulInstruction(resultRegister, rightVarLocation);

        assemblyTranslator.emmitAssemblyInstruction(assemblyInstruction);
        memoryManager.removeVarFromOtherLocations(new VarArgument(instruction.getResultVar()), resultRegister);
        memoryManager.addVarToSpecificLocation(resultRegister, new VarArgument(instruction.getResultVar()));
    }
}
