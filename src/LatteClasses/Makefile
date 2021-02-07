JAVAC=javac
JAVAC_FLAGS=-sourcepath . -cp "./java_cup.jar:../../lib/java-cup-11b-runtime.jar"
JAVA=java
JAVA_FLAGS=-cp "./java_cup.jar:../../lib/java-cup-11b-runtime.jar"
PARSER=${JAVA} ${JAVA_FLAGS} java_cup.Main
PARSER_FLAGS=-locations -expect 100
LEXER=${JAVA} ${JAVA_FLAGS}  JLex.Main
LEXER_FLAGS=
all : test
#java -cp ./java_cup.jar:../../lib/java-cup-11b.jar java_cup.Main -locations -expect 100 latte/_cup.cup
test : absyn ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte

.PHONY : absyn


%.class : %.java
	${JAVAC} ${JAVAC_FLAGS} $^

absyn : ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte ../latte
	${JAVAC} ${JAVAC_FLAGS} $^

latte/Yylex.java : ../latte
	${LEXER} ${LEXER_FLAGS} latte/Yylex

latte/parser.java latte/sym.java : ../latte
	${PARSER} ${PARSER_FLAGS} latte/_cup.cup
	mv parser.java sym.java latte/

latte/Yylex.class : ../latte ../latte ../latte

latte/sym.class : ../latte

latte/parser.class : ../latte ../latte

latte/PrettyPrinter.class : ../latte

clean :
	rm -f latte/Absyn/*.class latte/*.class

distclean : vclean

vclean :
	 rm -f latte/Absyn/Program.java latte/Absyn/ProgramCode.java latte/Absyn/TopDef.java latte/Absyn/FnDef.java latte/Absyn/ClassDef.java latte/Absyn/ListTopDef.java latte/Absyn/FunDef.java latte/Absyn/FunDefCode.java latte/Absyn/Arg.java latte/Absyn/ArgCode.java latte/Absyn/ListArg.java latte/Absyn/Class.java latte/Absyn/ClassCode.java latte/Absyn/ClassExt.java latte/Absyn/ClassStmt.java latte/Absyn/Fields.java latte/Absyn/Method.java latte/Absyn/ListIdent.java latte/Absyn/ListClassStmt.java latte/Absyn/Target.java latte/Absyn/Variable.java latte/Absyn/FieldT.java latte/Absyn/Block.java latte/Absyn/BlockCode.java latte/Absyn/ListStmt.java latte/Absyn/Stmt.java latte/Absyn/Empty.java latte/Absyn/BStmt.java latte/Absyn/Decl.java latte/Absyn/Ass.java latte/Absyn/Incr.java latte/Absyn/Decr.java latte/Absyn/Ret.java latte/Absyn/VRet.java latte/Absyn/Cond.java latte/Absyn/CondElse.java latte/Absyn/While.java latte/Absyn/SExp.java latte/Absyn/Item.java latte/Absyn/NoInit.java latte/Absyn/Init.java latte/Absyn/ListItem.java latte/Absyn/Type.java latte/Absyn/Int.java latte/Absyn/Str.java latte/Absyn/Bool.java latte/Absyn/Void.java latte/Absyn/ClassType.java latte/Absyn/Fun.java latte/Absyn/ListType.java latte/Absyn/Expr.java latte/Absyn/EVar.java latte/Absyn/ECast.java latte/Absyn/ENewObj.java latte/Absyn/ELitInt.java latte/Absyn/ELitTrue.java latte/Absyn/ELitFalse.java latte/Absyn/EApp.java latte/Absyn/EString.java latte/Absyn/ENull.java latte/Absyn/Neg.java latte/Absyn/Not.java latte/Absyn/EMul.java latte/Absyn/EAdd.java latte/Absyn/ERel.java latte/Absyn/EAnd.java latte/Absyn/EOr.java latte/Absyn/ListExpr.java latte/Absyn/AddOp.java latte/Absyn/Plus.java latte/Absyn/Minus.java latte/Absyn/MulOp.java latte/Absyn/Times.java latte/Absyn/Div.java latte/Absyn/Mod.java latte/Absyn/RelOp.java latte/Absyn/LTH.java latte/Absyn/LE.java latte/Absyn/GTH.java latte/Absyn/GE.java latte/Absyn/EQU.java latte/Absyn/NE.java latte/Absyn/Program.class latte/Absyn/ProgramCode.class latte/Absyn/TopDef.class latte/Absyn/FnDef.class latte/Absyn/ClassDef.class latte/Absyn/ListTopDef.class latte/Absyn/FunDef.class latte/Absyn/FunDefCode.class latte/Absyn/Arg.class latte/Absyn/ArgCode.class latte/Absyn/ListArg.class latte/Absyn/Class.class latte/Absyn/ClassCode.class latte/Absyn/ClassExt.class latte/Absyn/ClassStmt.class latte/Absyn/Fields.class latte/Absyn/Method.class latte/Absyn/ListIdent.class latte/Absyn/ListClassStmt.class latte/Absyn/Target.class latte/Absyn/Variable.class latte/Absyn/FieldT.class latte/Absyn/Block.class latte/Absyn/BlockCode.class latte/Absyn/ListStmt.class latte/Absyn/Stmt.class latte/Absyn/Empty.class latte/Absyn/BStmt.class latte/Absyn/Decl.class latte/Absyn/Ass.class latte/Absyn/Incr.class latte/Absyn/Decr.class latte/Absyn/Ret.class latte/Absyn/VRet.class latte/Absyn/Cond.class latte/Absyn/CondElse.class latte/Absyn/While.class latte/Absyn/SExp.class latte/Absyn/Item.class latte/Absyn/NoInit.class latte/Absyn/Init.class latte/Absyn/ListItem.class latte/Absyn/Type.class latte/Absyn/Int.class latte/Absyn/Str.class latte/Absyn/Bool.class latte/Absyn/Void.class latte/Absyn/ClassType.class latte/Absyn/Fun.class latte/Absyn/ListType.class latte/Absyn/Expr.class latte/Absyn/EVar.class latte/Absyn/ECast.class latte/Absyn/ENewObj.class latte/Absyn/ELitInt.class latte/Absyn/ELitTrue.class latte/Absyn/ELitFalse.class latte/Absyn/EApp.class latte/Absyn/EString.class latte/Absyn/ENull.class latte/Absyn/Neg.class latte/Absyn/Not.class latte/Absyn/EMul.class latte/Absyn/EAdd.class latte/Absyn/ERel.class latte/Absyn/EAnd.class latte/Absyn/EOr.class latte/Absyn/ListExpr.class latte/Absyn/AddOp.class latte/Absyn/Plus.class latte/Absyn/Minus.class latte/Absyn/MulOp.class latte/Absyn/Times.class latte/Absyn/Div.class latte/Absyn/Mod.class latte/Absyn/RelOp.class latte/Absyn/LTH.class latte/Absyn/LE.class latte/Absyn/GTH.class latte/Absyn/GE.class latte/Absyn/EQU.class latte/Absyn/NE.class
	 rm -f latte/Absyn/*.class
	 rmdir latte/Absyn/
	 rm -f latte/Yylex latte/_cup.cup latte/Yylex.java latte/VisitSkel.java latte/ComposVisitor.java latte/AbstractVisitor.java latte/FoldVisitor.java latte/AllVisitor.java latte/PrettyPrinter.java latte/Skeleton.java latte/Test.java latte/parser.java latte/sym.java latte/*.class
	 rm -f Makefile
	 rmdir -p latte/
