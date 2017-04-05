//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "nanomorpho.byacc"
	import java.io.*;
	import java.util.*;
//#line 20 "Borpho.java"




public class Borpho
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class BorphoVal is defined in BorphoVal.java


String   yytext;//user variable to return contextual strings
BorphoVal yyval; //used to return semantic vals from action routines
BorphoVal yylval;//the 'lval' (result) I got from yylex()
BorphoVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new BorphoVal[YYSTACKSIZE];
  yyval=new BorphoVal();
  yylval=new BorphoVal();
  valptr=-1;
}
void val_push(BorphoVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
BorphoVal val_pop()
{
  if (valptr<0)
    return new BorphoVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
BorphoVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new BorphoVal();
  return valstk[ptr];
}
final BorphoVal dup_yyval(BorphoVal val)
{
  BorphoVal dup = new BorphoVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IF=257;
public final static short ELSE=258;
public final static short ELSEIF=259;
public final static short VAR=260;
public final static short WHILE=261;
public final static short NAME=262;
public final static short RETURN=263;
public final static short FUN=264;
public final static short OP1=265;
public final static short OP2=266;
public final static short OP3=267;
public final static short OP4=268;
public final static short OP5=269;
public final static short LITERAL=270;
public final static short ASSIGNMENT=271;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,   12,   12,   14,   16,   17,   19,   20,   22,   23,
   13,   15,   15,   18,   18,   25,   24,   24,   26,   21,
   21,   10,   10,   10,    5,    5,    6,    6,    7,    7,
    8,    8,    9,    9,   11,   11,   11,   11,   11,   11,
   11,   11,   11,   11,   11,    2,    2,    4,    4,    3,
    3,    1,    1,    1,
};
final static short yylen[] = {                            2,
    1,    1,    2,    0,    0,    0,    0,    0,    0,    0,
   16,    1,    0,    3,    0,    2,    1,    3,    0,    4,
    0,    3,    2,    1,    3,    1,    3,    1,    3,    1,
    3,    1,    3,    1,    1,    4,    2,    2,    2,    2,
    2,    7,    8,    1,    3,    2,    0,    3,    0,    3,
    0,    8,    4,    0,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    2,    4,    3,    0,    5,    0,   17,
    6,    0,    0,    0,    0,   18,    7,   15,    0,    0,
    9,    0,    0,    0,   14,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   44,    0,    0,    0,    0,    0,
    0,   19,   34,   10,    0,    0,    0,    0,   23,    0,
   41,   40,   39,   38,   37,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   22,    0,    0,   45,    0,
    0,    0,    0,   33,    0,   11,    0,    0,   36,    0,
   46,   20,    0,    0,    0,    0,    0,    0,   48,    0,
    0,   42,    0,    0,   43,   50,    0,    0,    0,    0,
   53,    0,    0,    0,    0,   52,
};
final static short yydgoto[] = {                          2,
   95,   67,   86,   81,   37,   38,   39,   40,   41,   87,
   43,    3,    4,    7,   11,    9,   13,   19,   18,   21,
   44,   24,   63,   12,   22,   62,
};
final static short yysindex[] = {                      -256,
 -250,    0, -256,    0,    0,    0,  -26,    0, -245,    0,
    0,  -25,  -23, -242, -100,    0,    0,    0, -235, -245,
    0,  -32,  -25,  -19,    0,   -4,   -3,  -40,  -19,   -5,
   -5,   -5,   -5,   -5,    0,  -19, -231, -229, -227, -225,
 -222,    0,    0,    0,  -19,  -19,  -19,  -19,    0,    9,
    0,    0,    0,    0,    0,    5,   -5,   -5,   -5,   -5,
   -5,   -7,  -72,   15,   16,    0,   26,   24,    0, -229,
 -227, -225, -222,    0,  -19,    0,  -53,  -52,    0,  -19,
    0,    0,  -19,  -19,   24,  -51,   13,  -49,    0, -248,
  -19,    0,  -45,   42,    0,    0,  -19,  -19,  -42,   43,
    0,  -38,  -19,  -39, -248,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,   88,    0,    0,    0,    0,    0,   48,    0,
    0,   49,    0,    0,    0,    0,    0,    0,  -33,    0,
    0,    0,   32,  -31,    0,    0,    0,    1,    0,    0,
    0,    0,    0,    0,    0,    0,  -35,  -11,  -15,  -28,
   10,    0,    0,    0,    0,    0,    0,   52,    0,    1,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   54,    0,   22,
   20,   18,   14,    0,  -31,    0,    0,    0,    0,    0,
    0,    0,  -24,  -24,   54,    0,    0,    0,    0,    6,
  -24,    0,    0,    0,    0,    0,  -24,    0,    0,    0,
    0,    0,  -24,    0,    6,    0,
};
final static short yygindex[] = {                         0,
   -2,    0,  -69,   17,    0,   47,   50,   41,   45,   51,
  -29,    0,  103,    0,    0,    0,    0,    0,    0,    0,
   34,    0,    0,   87,    0,    0,
};
final static int YYTABLESIZE=291;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         48,
   51,   52,   53,   54,   55,   24,    8,    1,   24,   93,
   94,    5,   30,    8,   88,   30,   10,   15,   14,   16,
   36,   96,   17,   24,   20,   28,   25,   99,   28,   26,
   30,   74,   26,  104,   36,   45,   46,   57,   58,   59,
   60,   35,   61,   28,   35,   69,   54,   26,   48,   54,
   32,   75,   76,   32,   31,   77,   78,   31,   29,   35,
   27,   29,   25,   27,   54,   25,   79,   80,   32,   83,
   84,   91,   31,   90,   42,   92,   29,   97,   27,   49,
   25,   98,  101,  102,  103,  105,   56,    1,   13,   12,
   16,    8,   47,   21,   49,   64,   65,   66,   68,   72,
   51,   89,  106,   70,   73,    6,   23,   71,   82,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   42,    0,    0,    0,    0,
   85,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  100,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    8,    0,    0,    0,    8,    8,    8,
   47,    8,    8,    8,    8,    8,    8,   26,   30,   30,
   30,   27,   28,   29,    0,   30,   31,   32,   33,   34,
   35,   26,   28,   28,    0,   27,   50,   26,    0,   30,
   31,   32,   33,   34,   35,   35,   35,   35,   35,   35,
   54,   54,   54,   54,   54,   32,   32,   32,   32,   31,
   31,   31,   31,    0,   29,   29,   29,   27,   27,    0,
   25,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   30,   31,   32,   33,   34,   41,   40,  264,   44,  258,
  259,  262,   41,   40,   84,   44,  262,   41,   44,  262,
   40,   91,  123,   59,  260,   41,   59,   97,   44,   41,
   59,   61,   44,  103,   40,   40,   40,  269,  268,  267,
  266,   41,  265,   59,   44,   41,   41,   59,   40,   44,
   41,   59,  125,   44,   41,   41,   41,   44,   41,   59,
   41,   44,   41,   44,   59,   44,   41,   44,   59,  123,
  123,   59,   59,  125,   24,  125,   59,  123,   59,   29,
   59,   40,  125,   41,  123,  125,   36,    0,   41,   41,
   59,  125,   41,  125,   41,   45,   46,   47,   48,   59,
  125,   85,  105,   57,   60,    3,   20,   58,   75,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   75,   -1,   -1,   -1,   -1,
   80,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   98,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,   -1,   -1,   -1,  261,  262,  263,
  271,  265,  266,  267,  268,  269,  270,  257,  267,  268,
  269,  261,  262,  263,   -1,  265,  266,  267,  268,  269,
  270,  257,  268,  269,   -1,  261,  262,  269,   -1,  265,
  266,  267,  268,  269,  270,  265,  266,  267,  268,  269,
  265,  266,  267,  268,  269,  266,  267,  268,  269,  266,
  267,  268,  269,   -1,  267,  268,  269,  268,  269,   -1,
  269,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=271;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'",null,null,"','",
null,null,null,null,null,null,null,null,null,null,null,null,null,null,"';'",
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"IF","ELSE","ELSEIF","VAR","WHILE","NAME",
"RETURN","FUN","OP1","OP2","OP3","OP4","OP5","LITERAL","ASSIGNMENT",
};
final static String yyrule[] = {
"$accept : start",
"start : program",
"program : func",
"program : program func",
"$$1 :",
"$$2 :",
"$$3 :",
"$$4 :",
"$$5 :",
"$$6 :",
"$$7 :",
"func : FUN NAME $$1 '(' $$2 optnames $$3 ')' '{' $$4 decls $$5 $$6 exprs $$7 '}'",
"optnames : names",
"optnames :",
"decls : decls decl ';'",
"decls :",
"decl : VAR names",
"names : NAME",
"names : names ',' NAME",
"$$8 :",
"exprs : expr $$8 ';' exprs",
"exprs :",
"expr : NAME ASSIGNMENT expr",
"expr : RETURN expr",
"expr : binopexpr",
"binopexpr : binopexpr OP5 optmp1",
"binopexpr : optmp1",
"optmp1 : optmp1 OP4 optmp2",
"optmp1 : optmp2",
"optmp2 : optmp2 OP3 optmp3",
"optmp2 : optmp3",
"optmp3 : optmp3 OP2 optmp4",
"optmp3 : optmp4",
"optmp4 : optmp4 OP1 smallexpr",
"optmp4 : smallexpr",
"smallexpr : NAME",
"smallexpr : NAME '(' optexprs ')'",
"smallexpr : OP5 smallexpr",
"smallexpr : OP4 smallexpr",
"smallexpr : OP3 smallexpr",
"smallexpr : OP2 smallexpr",
"smallexpr : OP1 smallexpr",
"smallexpr : WHILE '(' expr ')' '{' optexprsm '}'",
"smallexpr : IF '(' expr ')' '{' optexprsm '}' iftail",
"smallexpr : LITERAL",
"smallexpr : '(' expr ')'",
"optexprs : expr moreexprs",
"optexprs :",
"moreexprs : ',' expr moreexprs",
"moreexprs :",
"optexprsm : expr ';' optexprsm",
"optexprsm :",
"iftail : ELSEIF '(' expr ')' '{' optexprsm '}' iftail",
"iftail : ELSE '{' optexprsm '}'",
"iftail :",
};

//#line 286 "nanomorpho.byacc"

	private NanomorphoLexer lexer;

	private int yylex()
	{
		int yyl_return = -1;
		try
		{
			yylval = new BorphoVal(0);
			yyl_return = lexer.yylex();
		}
		catch (IOException e)
		{
			System.err.println("IO error: " + e);
		}
//		System.out.println("Debug " + yylval.sval + "(" + yyl_return + ")");

		return yyl_return;
	}

	public void yyerror(String error)
	{
		System.err.println("Error: " + error);
	}

	public Borpho(Reader r)
	{
		lexer = new NanomorphoLexer(r,this);
	}

	public static void thula()
	{
		int i = 0;
		int n = millithula.size();
		while (i < n)
		{
			variable_map.clear();
			var_counter = 0;
			masm.print("#\"");
			masm.print(millithula.get(i++));
			masm.print("[f" + ((ArrayList)millithula.get(i)).size());
			masm.println("]\" = ");
			masm.println("[");
			
			thula_args((ArrayList)millithula.get(i++));
			
			thula_decl((ArrayList)millithula.get(i++));

			int m = ((ArrayList)millithula.get(i)).size();
			for (int j = 0; j < m; j++)
				thula_expr((ArrayList)((ArrayList)millithula.get(i)).get(j));

			i++;

			masm.println("(Return)");
			masm.println("];");
			masm.println();
		}
	}

	public static void thula_args(ArrayList ls)
	{
		int n = ls.size();
		for (int i = 0; i < n; i++)
		{
			if (variable_map.get((String)ls.get(i)) != null) throw_error("Variable \"" + (String)ls.get(i) + "\" is already declared.");
			variable_map.put((String)ls.get(i), (Integer)var_counter);
			var_counter++;
		}
	}

	public static void thula_decl(ArrayList ls)
	{
		int n = ls.size();
		masm.println("(MakeVal null)");
		for (int i = 0; i < n; i++)
		{
			if (variable_map.get((String)ls.get(i)) != null) throw_error("Variable \"" + (String)ls.get(i) + "\" is already declared.");
			variable_map.put((String)ls.get(i), (Integer)var_counter);
			masm.println("(Push)");
			var_counter++;
		}
	}

	public static void thula_expr(ArrayList expr)
	{
		if (expr.size() == 0) return;
		if (expr.size() == 1)
		{
			if (expr.get(0) instanceof ArrayList)
			{
				thula_expr((ArrayList)expr.get(0));
				return;
			}
			if (is_literal((String)expr.get(0)))
			{
				masm.println("(MakeVal " + expr.get(0) + ")");
				return;
			}
			else
			{
				Integer loc = variable_map.get((String)expr.get(0));
				if (loc == null) throw_error("Unknown variable \"" + expr.get(0) + "\"");
				masm.println("(Fetch " + loc + ")");
				return;
			}
		}

		String head = (String)expr.get(0);


		if (head.equals("if"))
		{
			int bottom_label = name_counter++;
			int next_label   = name_counter++;
			thula_expr((ArrayList)expr.get(1));
			masm.println("(GoFalse _L" + next_label + ")");

			int n = ((ArrayList)expr.get(2)).size();
			for (int i = 0; i < n; i++)
				thula_expr((ArrayList)((ArrayList)expr.get(2)).get(i));

			masm.println("(Go _L" + bottom_label + ")");
			masm.println("_L" + next_label + ":");

			thula_iftl((ArrayList)expr.get(3), bottom_label);
			return;
		}

		if (head.equals("while"))
		{
			int top_label = name_counter++;
			int bot_label = name_counter++;
			masm.println("_L" + top_label + ":");
			thula_expr((ArrayList)expr.get(1));
			masm.println("(GoFalse _L" + bot_label + ")");

			int n = ((ArrayList)expr.get(2)).size();
			for (int i = 0; i < n; i++)
				thula_expr((ArrayList)((ArrayList)expr.get(2)).get(i));

			masm.println("(Go _L" + top_label + ")");
			masm.println("_L" + bot_label + ":");
			return;
		}

		if (head.equals("="))
		{
			Integer loc = variable_map.get((String)expr.get(1));
			if (loc == null) throw_error("Variable \"" + head + "\" not found");

			thula_expr((ArrayList)expr.get(2));

			masm.println("(Store " + loc + ")");
			return;
		}

		if (head.equals("return"))
		{
			thula_expr((ArrayList)expr.get(1));
			masm.println("(Return)");
		}
		
		else
		{
			int n = ((ArrayList)expr.get(1)).size();
			for (int i = 0; i < n; i++)
			{
				if (i != 0) masm.println("(Push)");
				thula_expr((ArrayList)((ArrayList)expr.get(1)).get(n - i - 1));
			}

			masm.println("(Call #\"" + head + "[f" + n + "]\" " + n + ")");
			return;
		}
	}

	public static void thula_iftl(ArrayList ls, int bottom_label)
	{
		if (ls.size() == 0) 
		{
			masm.println("_L" + bottom_label + ":");
			return;
		}
		if (((String)ls.get(0)).equals("elseif"))
		{
			int next_label = name_counter++;
			thula_expr((ArrayList)ls.get(1));
			masm.println("(GoFalse _L" + next_label + ")");

			int n = ((ArrayList)ls.get(2)).size();
			for (int i = 0; i < n; i++)
				thula_expr((ArrayList)((ArrayList)ls.get(2)).get(i));

			masm.println("(Go _L" + bottom_label + ")");
			masm.println("_L" + next_label + ":");

			thula_iftl((ArrayList)ls.get(3), bottom_label);
		}
		else
		{
			int n = ((ArrayList)ls.get(1)).size();
			for (int i = 0; i < n; i++)
				thula_expr((ArrayList)((ArrayList)ls.get(1)).get(i));

			masm.println("_L" + bottom_label + ":");
			return;
		}
	}


	public static boolean is_literal(String str)
	{
		return is_double(str) || is_string(str) || is_boolean(str) || is_null(str);
	}
	public static boolean is_string(String str)
	{
		return str.charAt(0) == '\"';
	}
	public static boolean is_boolean(String str)
	{
		return str.equals("true") || str.equals("false");
	}
	public static boolean is_null(String str)
	{
		return str.equals("null");
	}
	public static boolean is_double(String str)
	{
		try
		{
			double d = Double.parseDouble(str);
			return true;
		}
		catch (NumberFormatException ex)
		{
			return false;
		}
	}
		
		




	public static HashMap<String, Integer> variable_map;
	public static ArrayList<Object> millithula;
	public static ArrayList<Object> tmp_arraylist;
	public static PrintStream masm;

	public static int name_counter;
	public static int var_counter;

	public static void main(String[] args)
		throws FileNotFoundException
	{
		Borpho yyparser = new Borpho(new FileReader(args[0]));
		if (args.length == 0)
		{
			System.err.println("Fatal error. No input file.");
			System.exit(1);
		}

		variable_map  = new HashMap<String, Integer>();
		millithula    = new ArrayList<Object>();
		tmp_arraylist = new ArrayList<Object>();
		masm          = new PrintStream(new File(args[0] + ".masm"));
		name_counter  = 0;
		var_counter   = 0;

		System.out.println("Parsing!");
		yyparser.yyparse();
		System.out.println("Parser finsished!");

		System.out.println();

		masm.println("\"" + args[0] + ".mexe\" = main in\n!\n{{");
		thula();
		masm.println("}}\n*\nBASIS\n;");

		System.out.println("Comilation complete!");
	}

	public static void throw_error(String error)
	{
		System.out.println(error);
		System.exit(4);
	}
//#line 612 "Borpho.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 4:
//#line 20 "nanomorpho.byacc"
{millithula.add(val_peek(0).sval);}
break;
case 5:
//#line 21 "nanomorpho.byacc"
{tmp_arraylist.clear();}
break;
case 6:
//#line 21 "nanomorpho.byacc"
{millithula.add(new ArrayList<Object>(tmp_arraylist));}
break;
case 7:
//#line 22 "nanomorpho.byacc"
{tmp_arraylist.clear();}
break;
case 8:
//#line 22 "nanomorpho.byacc"
{millithula.add(new ArrayList<Object>(tmp_arraylist));}
break;
case 9:
//#line 23 "nanomorpho.byacc"
{tmp_arraylist.clear();}
break;
case 10:
//#line 23 "nanomorpho.byacc"
{millithula.add(new ArrayList<Object>(tmp_arraylist));}
break;
case 17:
//#line 41 "nanomorpho.byacc"
{tmp_arraylist.add(val_peek(0).sval);}
break;
case 18:
//#line 42 "nanomorpho.byacc"
{tmp_arraylist.add(val_peek(0).sval);}
break;
case 19:
//#line 46 "nanomorpho.byacc"
{tmp_arraylist.add(val_peek(0).obj);}
break;
case 22:
//#line 52 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add("=");
		ret.add(val_peek(2).sval);
		ret.add(val_peek(0).obj);
		yyval.obj = ret;
	}
break;
case 23:
//#line 60 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add("return");
		ret.add(val_peek(0).obj);
		yyval.obj = ret;
	}
break;
case 25:
//#line 71 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add(val_peek(1).sval);
		ret.add(new ArrayList<Object>());
		((ArrayList)ret.get(1)).add(val_peek(0).obj);
		((ArrayList)ret.get(1)).add(val_peek(2).obj);
		yyval.obj = ret;
	}
break;
case 27:
//#line 84 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add(val_peek(1).sval);
		ret.add(new ArrayList<Object>());
		((ArrayList)ret.get(1)).add(val_peek(0).obj);
		((ArrayList)ret.get(1)).add(val_peek(2).obj);
		yyval.obj = ret;
	}
break;
case 29:
//#line 97 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add(val_peek(1).sval);
		ret.add(new ArrayList<Object>());
		((ArrayList)ret.get(1)).add(val_peek(0).obj);
		((ArrayList)ret.get(1)).add(val_peek(2).obj);
		yyval.obj = ret;
	}
break;
case 31:
//#line 110 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add(val_peek(1).sval);
		ret.add(new ArrayList<Object>());
		((ArrayList)ret.get(1)).add(val_peek(0).obj);
		((ArrayList)ret.get(1)).add(val_peek(2).obj);
		yyval.obj = ret;
	}
break;
case 33:
//#line 123 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add(val_peek(1).sval);
		ret.add(new ArrayList<Object>());
		((ArrayList)ret.get(1)).add(val_peek(0).obj);
		((ArrayList)ret.get(1)).add(val_peek(2).obj);
		yyval.obj = ret;
	}
break;
case 35:
//#line 136 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add(val_peek(0).sval);
		yyval.obj = ret;
	}
break;
case 36:
//#line 142 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add(val_peek(3).sval);
		ret.add(val_peek(1).obj);
		yyval.obj = ret;
	}
break;
case 37:
//#line 149 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ArrayList<Object> tmp1 = new ArrayList<Object>();
		ArrayList<Object> tmp2 = new ArrayList<Object>();
		ret.add(val_peek(1).sval);
		tmp2.add(val_peek(0).obj);
		tmp1.add(tmp2);
		ret.add(tmp1);
		yyval.obj = ret;
	}
break;
case 38:
//#line 160 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ArrayList<Object> tmp1 = new ArrayList<Object>();
		ArrayList<Object> tmp2 = new ArrayList<Object>();
		ret.add(val_peek(1).sval);
		tmp2.add(val_peek(0).obj);
		tmp1.add(tmp2);
		ret.add(tmp1);
		yyval.obj = ret;
	}
break;
case 39:
//#line 171 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ArrayList<Object> tmp1 = new ArrayList<Object>();
		ArrayList<Object> tmp2 = new ArrayList<Object>();
		ret.add(val_peek(1).sval);
		tmp2.add(val_peek(0).obj);
		tmp1.add(tmp2);
		ret.add(tmp1);
		yyval.obj = ret;
	}
break;
case 40:
//#line 182 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ArrayList<Object> tmp1 = new ArrayList<Object>();
		ArrayList<Object> tmp2 = new ArrayList<Object>();
		ret.add(val_peek(1).sval);
		tmp2.add(val_peek(0).obj);
		tmp1.add(tmp2);
		ret.add(tmp1);
		yyval.obj = ret;
	}
break;
case 41:
//#line 193 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ArrayList<Object> tmp1 = new ArrayList<Object>();
		ArrayList<Object> tmp2 = new ArrayList<Object>();
		ret.add(val_peek(1).sval);
		tmp2.add(val_peek(0).obj);
		tmp1.add(tmp2);
		ret.add(tmp1);
		yyval.obj = ret;
	}
break;
case 42:
//#line 204 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add("while");
		ret.add(val_peek(4).obj);
		ret.add(val_peek(1).obj);
		yyval.obj = ret;
	}
break;
case 43:
//#line 212 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add("if");
		ret.add(val_peek(5).obj);
		ret.add(val_peek(2).obj);
		ret.add(val_peek(0).obj);
		yyval.obj = ret;
	}
break;
case 44:
//#line 221 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add(val_peek(0).sval);
		yyval.obj = ret;
	}
break;
case 45:
//#line 227 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add(val_peek(1).obj);
		yyval.obj = ret;
	}
break;
case 46:
//#line 236 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>((ArrayList)val_peek(0).obj);
		ret.add(val_peek(1).obj);
		yyval.obj = ret;
	}
break;
case 47:
//#line 241 "nanomorpho.byacc"
{yyval.obj = new ArrayList<Object>();}
break;
case 48:
//#line 246 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>((ArrayList)val_peek(0).obj);
		ret.add(val_peek(1).obj);
		yyval.obj = ret;
	}
break;
case 49:
//#line 251 "nanomorpho.byacc"
{yyval.obj = new ArrayList<Object>();}
break;
case 50:
//#line 256 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add(val_peek(2).obj);
		ret.addAll((ArrayList)val_peek(0).obj);
		yyval.obj = ret;
	}
break;
case 51:
//#line 262 "nanomorpho.byacc"
{yyval.obj = new ArrayList<Object>();}
break;
case 52:
//#line 267 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add("elseif");
		ret.add(val_peek(5).obj);
		ret.add(val_peek(2).obj);
		ret.add(val_peek(0).obj);
		yyval.obj = ret;
	}
break;
case 53:
//#line 276 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add("else");
		ret.add(val_peek(1).obj);
		yyval.obj = ret;
	}
break;
case 54:
//#line 282 "nanomorpho.byacc"
{yyval.obj = new ArrayList<Object>();}
break;
//#line 1055 "Borpho.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Borpho()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Borpho(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
