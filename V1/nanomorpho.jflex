%%
%public
%class NanomorphoLexer
%byaccj

%unicode

%{

public Borpho yyparser;

public NanomorphoLexer( java.io.Reader r, Borpho yyparser )
{
	this(r);
	this.yyparser = yyparser;
}

%}

NEWLINE 	=	\n
WS 			=	[ \t\r\f]
INTEGER 	=	[0-9]+
FLOAT 		=	[0-9]+\.[0-9]+([Ee][+-]?[0-9]+)?
BOOLEAN 	=	(true)|(false)
NAME 		=	[a-zA-Z_][a-zA-Z0-9_]*
OP1 		=	[*/%]
OP2			=	[+-]
OP3			=	(<)|(>)|(>=)|(<=)|(==)
OP4			=	(\|\|)|(&&)
OP5			=	[-+/*%<>=|!&]+
DELIMITERS =	[()\{\};.,]
STRING 	   =	\"([^\"\n\r\f])*\"

COMMENT	   =	;;;.*

%%

{NEWLINE}
{
//	System.out.println("Newline!");
}
{COMMENT}
{
//	System.out.println("Comment!");
}
{WS}
{
//	System.out.println("Whitespace!");
}

var
{
	yyparser.yylval = new BorphoVal(yytext());
	return yyparser.VAR;
}

if
{
	yyparser.yylval = new BorphoVal(yytext());
	return yyparser.IF;
}

else
{
	yyparser.yylval = new BorphoVal(yytext());
	return yyparser.ELSE;
}

elseif
{
	yyparser.yylval = new BorphoVal(yytext());
	return yyparser.ELSEIF;
}

while
{
	yyparser.yylval = new BorphoVal(yytext());
	return yyparser.WHILE;
}

return
{
	yyparser.yylval = new BorphoVal(yytext());
	return yyparser.RETURN;
}

fun
{
	yyparser.yylval = new BorphoVal(yytext());
	return yyparser.FUN;
}

{DELIMITERS}
{
	yyparser.yylval = new BorphoVal(yytext());
	return (int) yycharat(0);
}

=
{
	yyparser.yylval = new BorphoVal(yytext());
	return yyparser.ASSIGNMENT;
}
	
{OP1}
{
	yyparser.yylval = new BorphoVal(yytext());
	return yyparser.OP1;
}

{OP2}
{
	yyparser.yylval = new BorphoVal(yytext());
	return yyparser.OP2;
}

{OP3}
{
	yyparser.yylval = new BorphoVal(yytext());
	return yyparser.OP3;
}

{OP4}
{
	yyparser.yylval = new BorphoVal(yytext());
	return yyparser.OP4;
}

{OP5}
{
	yyparser.yylval = new BorphoVal(yytext());
	return yyparser.OP5;
}

{INTEGER}
{
	yyparser.yylval = new BorphoVal(yytext());
	return yyparser.LITERAL;
}

{FLOAT}
{
	yyparser.yylval = new BorphoVal(yytext());
	return yyparser.LITERAL;
}

{BOOLEAN}
{
	yyparser.yylval = new BorphoVal(yytext());
	return yyparser.LITERAL;
}

{STRING}
{
	yyparser.yylval = new BorphoVal(yytext());
	return yyparser.LITERAL;
}

{NAME}
{
	yyparser.yylval = new BorphoVal(yytext());
	return yyparser.NAME;
}

.
{
	System.out.println("Oh no. Token \"" + yytext() + "\" not know.");
	return Borpho.YYERRCODE;
}


