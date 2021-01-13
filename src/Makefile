JAVAC=javac
JAVAC_FLAGS=-sourcepath . -cp "./java_cup.jar:../lib/java-cup-11b-runtime.jar"
JAVA=java
JAVA_FLAGS=-cp "./java_cup.jar:../lib/java-cup-11b-runtime.jar"
PARSER=${JAVA} ${JAVA_FLAGS} java_cup.Main
PARSER_FLAGS=-locations -expect 100
LEXER=${JAVA} ${JAVA_FLAGS} JLex.Main
SOURCEDIR = ./
LEXER_FLAGS=

all : main
	./buildJar.sh

SOURCES := $(shell find $(SOURCEDIR) -name '*.java')
CLASS := $(SOURCES:%.java=%.class)

$(CLASS): $(SOURCES)
		${JAVAC} ${JAVAC_FLAGS} $^

main :  $(CLASS)







.PHONY : absyn

%.class : %.java
	${JAVAC} ${JAVAC_FLAGS} $^



latte/Yylex.java : latte/Yylex
	${LEXER} ${LEXER_FLAGS} latte/Yylex

latte/parser.java latte/sym.java : latte/_cup.cup
	${PARSER} ${PARSER_FLAGS} latte/_cup.cup
	mv parser.java sym.java latte/

latte/Yylex.class : latte/Yylex.java latte/parser.java latte/sym.java

latte/sym.class : latte/sym.java

latte/parser.class : latte/parser.java latte/sym.java

latte/PrettyPrinter.class : latte/PrettyPrinter.java

clean :
	rm -f $(CLASS)

distclean : vclean
