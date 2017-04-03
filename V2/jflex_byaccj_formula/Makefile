all: ExprLexer.class ExprParser.class ExprParserVal.class

test: all
	java ExprParser "2+3*(4+5)"

ExprLexer.class ExprParser.class ExprParserVal.class: ExprLexer.java ExprParser.java ExprParserVal.java
	javac ExprLexer.java ExprParser.java ExprParserVal.java

ExprLexer.java: formula.jflex
	java -jar jflex-1.6.1.jar formula.jflex

ExprParser.java ExprParserVal.java: formula.byaccj
	./byacc -Jclass=ExprParser formula.byaccj

clean:
	rm -rf *.class *~ *.java *.bak yacc.*
