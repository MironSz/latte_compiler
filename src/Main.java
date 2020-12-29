import frontend.*;
import latte.Yylex;
import quadCode.syntax.Block;
import quadCode.translator.CalculateExpressionVisitor;
import quadCode.translator.ReturnType;
import quadCode.translator.TranslationContext;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;


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
            program.accept(new RemoveDuplicatesVisitor(),new RemoveDuplicatesContext());
            program.accept(new DeclarationVisitor(), new DeclarationContext());

            returnType = program.accept(new CalculateExpressionVisitor(), new TranslationContext());
            for(Block block: Block.allBlocks){
                System.out.println(block.toString()+"\n\n");
            }
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