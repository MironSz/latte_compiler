package latte;

import compiler.DeclarationContext;
import compiler.DeclarationVisitor;
import compiler.SimplifyLiteralSyntaxVisitor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Test {
    public static void main(String[] args) throws Exception {
        Yylex l = null;
        parser p;
        try {
            if (args.length == 0) l = new Yylex(new InputStreamReader(System.in));
            else l = new Yylex(new FileReader(args[0]));
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found: " + args[0]);
            System.exit(1);
        }
        p = new parser(l);
        /* The default parser is the first-defined entry point. */
        /* You may want to change this. Other options are: */
        /*  */
        try {
            latte.Absyn.Program parse_tree = p.pProgram();
            System.out.println();
            System.out.println("Parse Succesful!");
            System.out.println();
            System.out.println("[Abstract Syntax]");
            System.out.println();
            System.out.println(PrettyPrinter.show(parse_tree));
            System.out.println();
            System.out.println("[Linearized Tree]");
            System.out.println();
            System.out.println(PrettyPrinter.print(parse_tree));

            SimplifyLiteralSyntaxVisitor simplifySyntaxVisitor = new SimplifyLiteralSyntaxVisitor();
            parse_tree.accept(simplifySyntaxVisitor,null);

            DeclarationVisitor declarationVisitor = new DeclarationVisitor();
            parse_tree.accept(declarationVisitor,new DeclarationContext());


        } catch (Throwable e) {
            System.err.println("At line " + l.line_num() + ", near \"" + l.buff() + "\" :");
            System.err.println("     " + e.getMessage());
            System.exit(1);
        }
    }
}