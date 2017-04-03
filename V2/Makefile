default:
	java -jar jflex-1.6.1.jar nanomorpho.jflex
	byacc -Jclass=Borpho nanomorpho.byacc
	javac *.java

lexer:
	java -jar jflex-1.6.1.jar nanomorpho.jflex

parser:
	byacc -Jclass=Borpho nanomorpho.byacc

comp:
	javac *.java
