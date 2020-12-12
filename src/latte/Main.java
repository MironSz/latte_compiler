package latte;

import compiler.DeclarationContext;
import compiler.DeclarationVisitor;
import compiler.ReturnVisitor;
import compiler.SimplifyLiteralSyntaxVisitor;

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
        try {
            try {
                program = t.parse();
            } catch (Throwable e) {
                System.err.println("At line " + String.valueOf(t.l.line_num()) + ", near \"" + t.l.buff() + "\" :");
            }
            program.accept(new SimplifyLiteralSyntaxVisitor(), null);
            program.accept(new ReturnVisitor(), false);
            program.accept(new DeclarationVisitor(), new DeclarationContext());
        } catch (Throwable e) {
            System.err.println(e.getMessage());
//            e.printStackTrace();
            System.exit(1);
        }


    }

    public latte.Absyn.Program parse() throws Exception {
        latte.Absyn.Program ast = p.pProgram();
        return ast;
    }
}
