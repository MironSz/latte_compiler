import assembly.AssemblyTranslator;
import assembly.Optimizations.LivenessOptimization;
import assembly.QuadCode;
import assembly.memory.MemoryManager;
import assembly.memory.Producer;
import assembly.memory.locations.Register;
import frontend.*;
import latte.Absyn.Int;
import latte.Yylex;
import latte.parser;
import quadCode.syntax.Block;
import quadCode.translator.TranslationContext;
import quadCode.translator.TranslatorVisitor;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;


public class Main {
    Yylex l;
    parser p;

    public Main(String[] args) {
        try {
            Reader input;
            if (args.length == 0) input = new InputStreamReader(System.in);
            else input = new FileReader(args[0]);
            l = new Yylex(input);
        } catch (IOException e) {
            System.err.println("Error: File not found: " + args[0]);
            System.exit(1);
        }
        p = new parser(l, l.getSymbolFactory());
    }

    public static void main(String args[]) throws Exception {
        Main t = new Main(args);
        String pathToLatteProgram = args[0];
        String pathToTarget = "./target/";
        String pathToOutput = pathToLatteProgram.replace(".lat", ".s");
        String pathToOutputExe = pathToLatteProgram.replace(".lat", "");
        latte.Absyn.Program program;
        try {
            try {
                program = t.parse();
            } catch (Throwable e) {
                System.err.println("At line " + String.valueOf(t.l.line_num()) + ", near \"" + t.l.buff() + "\" :");
                return;
            }
            FunctionSignature functionSignature;

            functionSignature= new FunctionSignature("printInt");
            functionSignature.addArgument("x", new Int());
            functionSignature= new FunctionSignature("readInt");
            functionSignature.addArgument("x", new Int());





            program.accept(new SimplifyLiteralSyntaxVisitor(), null);
            program.accept(new ReturnVisitor(), false);
            program.accept(new DeclarationVisitor(), new DeclarationContext());

            DeclarationContext.clearTypesMap();
            program.accept(new RemoveDuplicatesVisitor(), new RemoveDuplicatesContext());
            program.accept(new RemoveBinaryOperationsVisitor(), null);
            program.accept(new DeclarationVisitor(), new DeclarationContext());

            program.accept(new TranslatorVisitor(), new TranslationContext());
            QuadCode rawQuadCode = new QuadCode(
                    new LinkedList<>(Block.allBlocks),
                    DeclarationContext.getTypes(),
                    FunctionSignature.allSignatures(),
                    ClassSignature.allSignatures(),
                    new Integer(1000000));

            MemoryManager memoryManager = new MemoryManager(
                    Arrays.asList(
                            new Register("rax"),
                            new Register("rbx"),
                            new Register("rcx"),
                            new Register("rdx"),
                            new Register("rsi"),
                            new Register("r8"),
                            new Register("r9"),
                            new Register("r10"),
                            new Register("r11"),
                            new Register("r12"),
                            new Register("r13"),
                            new Register("r14")
//                                    new Register("r2"),
//                                    new Register("r1"),
//                                    new Register("r3"),
//                                    new Register("r4")
                    ), rawQuadCode);

            memoryManager.setPreservedRegisters(Arrays.asList(new Register("rbx"),
                    new Register("rsp"),
                    new Register("rbp"),
                    new Register("r11"),
                    new Register("r12"),
                    new Register("r13"),
                    new Register("r14")));

            new LivenessOptimization().optimize(rawQuadCode);
            AssemblyTranslator assemblyTranslator = new AssemblyTranslator(rawQuadCode);

            assemblyTranslator.translate(memoryManager);

            File assemblyFile = new File(pathToOutput);
            assemblyFile.delete();

            FileWriter fileWriter = new FileWriter(pathToOutput);
            Producer.instructions.forEach(instruction -> {
                try {
                    fileWriter.write(instruction);
                    fileWriter.write("\n");
                } catch (Throwable e) {
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                    System.exit(1);
                }
            });
            fileWriter.close();
            Thread.sleep(100);

            new ProcessBuilder("make", "clean").directory(new File(pathToTarget)).start();
            Thread.sleep(100);

            assemblyFile = new File(pathToTarget + "code.s");

            FileWriter fileWriter2 = new FileWriter(pathToTarget + "code.s");
            Producer.instructions.forEach(instruction -> {
                try {
                    fileWriter2.write(instruction);
                    fileWriter2.write("\n");
                } catch (Throwable e) {
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                    System.exit(1);
                }
            });
            fileWriter2.close();


            Runtime rt = Runtime.getRuntime();
            new ProcessBuilder("make").directory(new File(pathToTarget)).start();
            Thread.sleep(100);
            rt.exec("cp ./target/out " + pathToOutputExe);
            Thread.sleep(100);


        } catch (Throwable e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }


    }


    public latte.Absyn.Program parse() throws Exception {
        latte.Absyn.Program ast = p.pProgram();
        return ast;
    }
}
