/**
	JFlex lesgreinir fyrir einfaldar formúlur.
	Höfundur: Snorri Agnarsson
 */

%%

%public
%class ExprLexer
%byaccj

%unicode

%{

public ExprParser yyparser;

public ExprLexer( java.io.Reader r, ExprParser yyparser )
{
	this(r);
	this.yyparser = yyparser;
}

%}

  /* Reglulegar skilgreiningar */

DIGIT=[0-9]
FLOAT={DIGIT}+(\.{DIGIT}+([eE][+-]?{DIGIT}+)?)?

%%

{FLOAT} {
	yyparser.yylval = new ExprParserVal(yytext());
	return ExprParser.NUM;
}

"+" | "-" | "*" | "/" | "(" | ")" {
	return (int) yycharat(0);
}

. {
	return ExprParser.YYERRCODE;
}
