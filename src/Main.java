import assembly.AssemblyTranslator;
import assembly.memory.MemoryManager;
import assembly.memory.Producer;
import assembly.memory.locations.Register;
import frontend.*;
import latte.Yylex;
import quadCode.syntax.Block;
import quadCode.translator.TranslatorVisitor;
import quadCode.translator.ReturnType;
import quadCode.translator.TranslationContext;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.LinkedList;


public class Main {
    Yylex l;
    latte.parser p;

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
        p = new latte.parser(l, l.getSymbolFactory());
    }

    public static void main(String args[]) throws Exception {
        Main t = new Main(args);
        latte.Absyn.Program program = null;
        ReturnType returnType;
        try {
            try {
                program = t.parse();
            } catch (Throwable e) {
                System.err.println("At line " + String.valueOf(t.l.line_num()) + ", near \"" + t.l.buff() + "\" :");
            }
            program.accept(new SimplifyLiteralSyntaxVisitor(), null);
            program.accept(new ReturnVisitor(), false);
            program.accept(new DeclarationVisitor(), new DeclarationContext());

            DeclarationContext.clearTypesMap();
            program.accept(new RemoveDuplicatesVisitor(),new RemoveDuplicatesContext());
            program.accept(new RemoveBinaryOperationsVisitor(), null);
            program.accept(new DeclarationVisitor(), new DeclarationContext());

            returnType = program.accept(new TranslatorVisitor(), new TranslationContext());
            for(Block block: Block.allBlocks){
                System.out.println(block.toString()+"\n\n");
            }

            AssemblyTranslator assemblyTranslator = new AssemblyTranslator();
            assemblyTranslator.translate(new LinkedList<>(Block.allBlocks),
                    new MemoryManager(
                            Arrays.asList(
                                    new Register("eax"),
//                                    new Register("r2"),
                                    new Register("r1"),
                                    new Register("r3"),
                                    new Register("r4"))));
            Producer.instructions.forEach(System.out::println);

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
