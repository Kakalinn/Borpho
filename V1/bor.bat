@ECHO OFF

java Borpho %1
java -jar Morpho.jar -c %1.masm
java -jar Morpho.jar %1
