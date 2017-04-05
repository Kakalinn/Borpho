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
    0,   10,    8,   11,    8,   12,   14,   17,    9,   13,
   13,   15,   15,   19,   18,   18,    6,    6,    1,    1,
    2,    2,    3,    3,    4,    4,    5,    5,    7,    7,
    7,    7,    7,    7,    7,    7,    7,   22,   20,   20,
   23,   21,   21,   25,   27,   24,   28,   29,   30,   26,
   26,   32,   31,   31,   33,   16,   34,   35,   16,   36,
   16,   37,   38,   16,   16,
};
final static short yylen[] = {                            2,
    1,    0,    2,    0,    3,    0,    0,    0,   12,    1,
    0,    3,    0,    2,    1,    3,    3,    1,    3,    1,
    3,    1,    3,    1,    3,    1,    3,    1,    1,    4,
    2,    2,    2,    2,    2,    1,    3,    0,    3,    0,
    0,    4,    0,    0,    0,    9,    0,    0,    0,   11,
    1,    0,    5,    0,    0,    4,    0,    0,   10,    0,
    4,    0,    0,    6,    0,
};
final static short yydefred[] = {                         2,
    0,    0,    0,    0,    0,    3,    5,    6,    0,    0,
   15,    0,    0,    7,    0,    0,   16,   13,    0,    0,
    0,    0,    0,   60,    0,    0,   62,    0,    0,    0,
    0,    0,   36,    0,    0,    0,    0,    0,    0,    0,
   28,    0,   12,    0,    0,    0,    0,    0,    0,   35,
   34,   33,   32,   31,    0,    0,    0,    0,    0,    0,
   55,    9,    0,    0,    0,   17,   38,    0,    0,   37,
    0,    0,    0,    0,   27,    0,   44,   61,   57,    0,
   30,   63,   56,    0,    0,    0,   39,    0,    0,    0,
   41,   64,    0,    0,    0,    0,   58,   42,   45,    0,
    0,   59,   52,   47,   46,   51,    0,    0,    0,    0,
    0,    0,   53,   48,    0,    0,    0,   49,    0,   50,
};
final static short yydgoto[] = {                          1,
   35,   36,   37,   38,   39,   40,   41,    2,    6,    3,
    4,    9,   12,   16,   19,   42,   21,   13,   22,   68,
   87,   80,   95,   64,   84,  105,  101,  108,  115,  119,
  106,  107,   76,   85,  100,   44,   48,   88,
};
final static short yysindex[] = {                         0,
    0,    0, -256, -256, -247,    0,    0,    0,  -22, -243,
    0,  -27,  -21,    0, -240,  -99,    0,    0, -235, -243,
    5,  -16,  -21,    0,   -3,  -40,    0,  -10,  -10,  -10,
  -10,  -10,    0,   14, -237, -230, -221, -219, -217,   -9,
    0,  -76,    0,   12,   14,   14,   14,   14,   13,    0,
    0,    0,    0,    0,   15,  -10,  -10,  -10,  -10,  -10,
    0,    0,   14,    5,   17,    0,    0,   18,   -4,    0,
 -230, -221, -219, -217,    0,    5,    0,    0,    0,   16,
    0,    0,    0,   21,  -60,   14,    0,    5,  -57,    5,
    0,    0,    5,  -58,   16,  -55,    0,    0,    0,    5,
 -248,    0,    0,    0,    0,    0,  -52,   37,    5,   14,
  -44,   41,    0,    0,  -39,    5,  -37,    0, -248,    0,
};
final static short yyrindex[] = {                         0,
    0,    1,    0,    0,    0,    0,    0,    0,    0,   44,
    0,    0,   48,    0,    0,    0,    0,    0,  -33,    0,
  -35,    0,   35,    0,    0,   20,    0,    0,    0,    0,
    0,    0,    0,    0,  -32,  -24,  -15,  -28,   24,    0,
    0,    0,    0,    0,    0,    0,   54,    0,   20,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -35,    0,    0,    0,    0,    0,    0,
   -8,   34,   32,   28,    0,  -35,    0,    0,    0,   55,
    0,    0,    0,    0,    0,    0,    0,  -35,    0,  -35,
    0,    0,  -35,    0,   55,    0,    0,    0,    0,  -35,
  -19,    0,    0,    0,    0,    0,    0,    0,  -35,    0,
    0,    0,    0,    0,    0,  -35,    0,    0,  -19,    0,
};
final static short yygindex[] = {                         0,
    0,   43,   40,   47,   42,   -6,  -26,    0,   98,    0,
    0,    0,    0,    0,    0,   10,    0,   87,    0,    0,
   19,    0,    0,    0,    0,  -11,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=303;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         47,
    1,   50,   51,   52,   53,   54,    8,    5,   18,  103,
  104,   18,   24,   14,    8,   24,   20,   10,   11,   20,
   54,   17,   15,   18,   20,   22,   18,   55,   22,   34,
   24,   56,   19,   75,   20,   19,   45,   57,   65,   66,
   67,   69,   43,   22,   34,   58,   59,   60,   62,   61,
   19,   63,   47,   34,   82,   70,   77,   79,   81,   86,
   29,   89,   90,   29,   26,   93,   97,   26,   25,   99,
  109,   25,   23,   78,   21,   23,  110,   21,   29,   91,
  113,  114,   26,  116,   11,   83,   25,  118,   10,   65,
   23,    8,   21,   14,   40,   43,   72,   92,   71,   94,
   74,    7,   96,  112,   73,   54,   23,  120,    0,  102,
    0,    0,    0,   98,    0,    0,    0,    0,  111,    0,
    0,    0,    0,    0,    0,  117,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    8,    0,    0,    0,    8,    8,    8,
   46,    8,    8,    8,    8,    8,    8,   54,   24,   24,
   24,   54,   54,   54,   20,   54,   54,   54,   54,   54,
   54,   49,   22,   22,   28,   29,   30,   31,   32,   33,
   19,   24,    0,    0,    4,   25,   26,   27,    0,   28,
   29,   30,   31,   32,   33,   26,    0,    0,   28,   29,
   30,   31,   32,   33,   29,   29,   29,   29,   29,   26,
   26,   26,   26,   25,   25,   25,   25,    0,   23,   23,
   23,   21,   21,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   28,   29,   30,   31,   32,   40,  264,   41,  258,
  259,   44,   41,   41,  262,   44,   41,   40,  262,   44,
   40,  262,   44,  123,  260,   41,   59,   34,   44,   40,
   59,  269,   41,   60,   59,   44,   40,  268,   45,   46,
   47,   48,   59,   59,   40,  267,  266,  265,  125,   59,
   59,   40,   40,   40,   59,   41,   63,   41,   41,   44,
   41,   41,  123,   44,   41,  123,  125,   44,   41,  125,
  123,   44,   41,   64,   41,   44,   40,   44,   59,   86,
  125,   41,   59,  123,   41,   76,   59,  125,   41,  125,
   59,  125,   59,   59,   41,   41,   57,   88,   56,   90,
   59,    4,   93,  110,   58,  125,   20,  119,   -1,  100,
   -1,   -1,   -1,   95,   -1,   -1,   -1,   -1,  109,   -1,
   -1,   -1,   -1,   -1,   -1,  116,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,   -1,   -1,   -1,  261,  262,  263,
  271,  265,  266,  267,  268,  269,  270,  257,  267,  268,
  269,  261,  262,  263,  269,  265,  266,  267,  268,  269,
  270,  262,  268,  269,  265,  266,  267,  268,  269,  270,
  269,  257,   -1,   -1,  264,  261,  262,  263,   -1,  265,
  266,  267,  268,  269,  270,  262,   -1,   -1,  265,  266,
  267,  268,  269,  270,  265,  266,  267,  268,  269,  266,
  267,  268,  269,  266,  267,  268,  269,   -1,  267,  268,
  269,  268,  269,
};
}
final static short YYFINAL=1;
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
"$$1 :",
"program : $$1 func",
"$$2 :",
"program : program $$2 func",
"$$3 :",
"$$4 :",
"$$5 :",
"func : FUN NAME $$3 '(' optnames ')' $$4 '{' decls $$5 body '}'",
"optnames : names",
"optnames :",
"decls : decls decl ';'",
"decls :",
"decl : VAR names",
"names : NAME",
"names : names ',' NAME",
"expr : NAME ASSIGNMENT expr",
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
"smallexpr : LITERAL",
"smallexpr : '(' expr ')'",
"$$6 :",
"optexprs : expr $$6 moreexpr",
"optexprs :",
"$$7 :",
"moreexpr : ',' expr $$7 moreexpr",
"moreexpr :",
"$$8 :",
"$$9 :",
"ifexpr : '(' expr $$8 ')' '{' body '}' $$9 elseifexpr",
"$$10 :",
"$$11 :",
"$$12 :",
"elseifexpr : ELSEIF $$10 '(' expr ')' $$11 '{' body '}' $$12 elseifexpr",
"elseifexpr : elseexpr",
"$$13 :",
"elseexpr : ELSE $$13 '{' body '}'",
"elseexpr :",
"$$14 :",
"body : expr ';' $$14 body",
"$$15 :",
"$$16 :",
"body : WHILE '(' expr ')' $$15 '{' body '}' $$16 body",
"$$17 :",
"body : IF $$17 ifexpr body",
"$$18 :",
"$$19 :",
"body : RETURN $$18 expr ';' $$19 body",
"body :",
};

//#line 215 "nanomorpho.byacc"

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

	public static void thula(ArrayList<Object> millithula)
	{
		int i = 0;
		while (true)
		{
			String millithulu_takn = (String)millithula.get(i);

			if (millithulu_takn.equals("~FUN"))
			{
				var_counter = 0;
				variable_map.clear();

				i++;
				masm.print("#\"");
				masm.print(millithula.get(i));
				masm.print("[f");

				i++;
				millithulu_takn = (String)millithula.get(i);
				String tmp_str = "";
				while (((String)millithula.get(i)).charAt(0) != '~')
				{
					if (variable_map.get(millithulu_takn) != null) throw_error("Variable \"" + millithulu_takn + "\" is already declared.");
					variable_map.put(millithulu_takn, (Integer)var_counter);
					var_counter++;
					millithulu_takn = (String)millithula.get(++i);
				}
				masm.print(((String)millithula.get(i++)).substring(1, ((String)millithula.get(i - 1)).length()));
				masm.println("]\" = ");
				masm.println("[");
				masm.println("(MakeVal null)");
				millithulu_takn = (String)millithula.get(i);
				{
					masm.println(tmp_str);
					millithulu_takn = (String)millithula.get(i++);
					while (!millithulu_takn.equals("~ENDDECL"))
					{
						if (variable_map.get(millithulu_takn) != null) throw_error("Variable \"" + millithulu_takn + "\" is already declared.");
						variable_map.put(millithulu_takn, (Integer)var_counter);
						masm.println("(Push)");
						var_counter++;
						millithulu_takn = (String)millithula.get(i++);
					}
					i = thula_body(i, "~ENDFUN");
				}
				masm.println("(Return)");
				masm.println("];\n");
			}
			else if(millithulu_takn.equals("~END"))
			{
				return;
			}
			else
			{
				throw_error("Expected a function declaration.");
			}
		}
	}

	public static int thula_body(int i, String exit)
	{
		while (true)
		{
			Object check = millithula.get(i);
			if (check instanceof ArrayList)
			{
				thula_call((ArrayList)millithula.get(i));
				i++;
				continue;
			}
			else if (check instanceof String)
			{
				String millithulu_takn = (String)millithula.get(i++);
				if (millithulu_takn.equals("~ASSIGN"))
				{
					String name = (String)millithula.get(i++);
					thula_expr(millithula.get(i));
					i++;
					Integer var_loc = variable_map.get(name);
					if (var_loc == null) throw_error("Can't assign unknown variable \"" + name + "\"");
					masm.println("(Store " + var_loc + ")");
					continue;
				}
				if (millithulu_takn.equals("~IF"))
				{
					int bottom_label = name_counter++;
					thula_expr(millithula.get(i));
					i++;
					masm.println("(GoFalse _L" + (name_counter) + ")");
					i = thula_body(i, "~ENDIF");
					masm.println("(Go _L" + bottom_label + ")");
					masm.println("_L" + (name_counter++) + ":");
					if (millithula.get(i) instanceof String)
					while (millithula.get(i).equals("~ELSEIF"))
					{
						int label = name_counter;

						i++;
						thula_expr(millithula.get(i));
						i++;
						masm.println("(GoFalse _L" + label + ")");
						i = thula_body(i, "~ENDELSEIF");
						masm.println("(Go _L" + bottom_label + ")");
						masm.println("_L" + label + ":");
						name_counter++;
					}
					if (millithula.get(i) instanceof String)
					if (millithula.get(i).equals("~ELSE"))
					{
						i++;
						i = thula_body(i, "~ENDELSE");
					}
					masm.println("_L" + bottom_label + ":");

					continue;
				}
				if (millithulu_takn.equals("~WHILE"))
				{
					int label1 = name_counter;
					int label2 = name_counter + 1;
					name_counter += 2;

					masm.println("_L" + label1 + ":");
					thula_expr(millithula.get(i));
					masm.println("(GoFalse _L" + (label2) + ")");
					i = thula_body(i, "~ENDWHILE");
					masm.println("(MakeVal 1)");
					masm.println("(Go _L" + label1 + ")");
					masm.println("_L" + label2 + ":");

					continue;
				}
				if (millithulu_takn.equals("~RETURN"))
				{
					thula_expr(millithula.get(i));
					i++;
					masm.println("(Return)");
					continue;
				}
				if (millithulu_takn.equals(exit))
				{
					return i;
				}
				else
				{
					continue;
				}
			}
			else
			{
				throw_error("Weird error :(");
			}
			return i;
		}
	}

	public static void thula_expr(Object expr)
	{
		if (!(expr instanceof String))
		{
			thula_call((ArrayList<Object>)expr);
		}
		else
		{
			if (is_literal((String)expr))
			{
				masm.println("(MakeVal " + expr + ")");
			}
			else
			{
				Integer var_loc = variable_map.get(expr);
				if (var_loc == null) throw_error("Undefined variable: " + expr);

				masm.println("(Fetch " + var_loc + ")");
			}
		}
	}

	public static void thula_call(ArrayList call)
	{
		int i = 1;
		String name = (String)call.get(1);

		if (call.size() != 3)
		{
			while (true)
			{
				i++;
				if ((call.get(i + 1) instanceof String) && ((String)call.get(i + 1)).equals("~ENDCALL"))
				{
					thula_expr(call.get(i));
					break;
				}
				else
				{
					thula_expr(call.get(i));
					masm.println("(Push)");
				}
			}
			masm.println("(Call #\"" + name + "[f" + (i - 1) + "]\" " + (i - 1) + ")");
		}
		else
		{
			masm.println("(Call #\"" + name + "[f0]\" 0)");
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

	public static void clean_millithula()
	{
		ArrayList<Object> tmp = new ArrayList<Object>();
		int n = millithula.size();
		for (int i = 0; i < n; i++)
			if (millithula.get(i) != null) tmp.add(millithula.get(i));

		millithula = tmp;
	}




	public static HashMap<String, Integer> function_map;
	public static HashMap<String, Integer> variable_map;
	public static ArrayList<Object> millithula;
	public static ArrayList<Object> expr_tmp;
	public static PrintStream masm;

	public static int args_counter;
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

		function_map = new HashMap<String, Integer>();
		variable_map = new HashMap<String, Integer>();
		millithula   = new ArrayList<Object>();
		expr_tmp 	 = new ArrayList<Object>();
		masm         = new PrintStream(new File(args[0] + ".masm"));
		name_counter = 0;
		var_counter  = 1;

		System.out.println("Parsing!");
		yyparser.yyparse();
		millithula.add("~END");
		System.out.println("Parser finsished!");

		clean_millithula();

		System.out.println();

		masm.println("\"" + args[0] + ".mexe\" = main in\n!\n{{");
		thula(millithula);
		masm.println("}}\n*\nBASIS\n;");

		System.out.println("Comilation complete!");
	}

	public static void print_millithula()
	{
		int n = millithula.size();

		System.out.println("Printing the milliþula:");
		for (int i = 0; i < n; i++)
		{
			System.out.println(millithula.get(i));
		}
	}

	public static void throw_error(String error)
	{
		System.out.println(error);
		System.exit(4);
	}
//#line 681 "Borpho.java"
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
case 2:
//#line 15 "nanomorpho.byacc"
{millithula.add("~FUN");}
break;
case 4:
//#line 16 "nanomorpho.byacc"
{millithula.add("~FUN");}
break;
case 6:
//#line 20 "nanomorpho.byacc"
{millithula.add(val_peek(0).sval); args_counter = 0;}
break;
case 7:
//#line 20 "nanomorpho.byacc"
{function_map.put(val_peek(4).sval, args_counter); millithula.add("~" + args_counter);}
break;
case 8:
//#line 20 "nanomorpho.byacc"
{millithula.add("~ENDDECL");}
break;
case 9:
//#line 20 "nanomorpho.byacc"
{millithula.add("~ENDFUN");}
break;
case 15:
//#line 38 "nanomorpho.byacc"
{millithula.add(val_peek(0).sval); args_counter++;}
break;
case 16:
//#line 39 "nanomorpho.byacc"
{millithula.add(val_peek(0).sval); args_counter++;}
break;
case 17:
//#line 43 "nanomorpho.byacc"
{millithula.add("~ASSIGN"); millithula.add(val_peek(2).sval); millithula.add(val_peek(0).obj);}
break;
case 18:
//#line 44 "nanomorpho.byacc"
{yyval.obj = val_peek(0).obj;}
break;
case 19:
//#line 49 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add("~CALL");
		ret.add(val_peek(1).sval);
		ret.add(val_peek(2).obj);
		ret.add(val_peek(0).obj);
		ret.add("~ENDCALL");
		yyval.obj = ret;
	}
break;
case 21:
//#line 63 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add("~CALL");
		ret.add(val_peek(1).sval);
		ret.add(val_peek(2).obj);
		ret.add(val_peek(0).obj);
		ret.add("~ENDCALL");
		yyval.obj = ret;
	}
break;
case 23:
//#line 77 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add("~CALL");
		ret.add(val_peek(1).sval);
		ret.add(val_peek(2).obj);
		ret.add(val_peek(0).obj);
		ret.add("~ENDCALL");
		yyval.obj = ret;
	}
break;
case 25:
//#line 91 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add("~CALL");
		ret.add(val_peek(1).sval);
		ret.add(val_peek(2).obj);
		ret.add(val_peek(0).obj);
		ret.add("~ENDCALL");
		yyval.obj = ret;
	}
break;
case 27:
//#line 105 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add("~CALL");
		ret.add(val_peek(1).sval);
		ret.add(val_peek(2).obj);
		ret.add(val_peek(0).obj);
		ret.add("~ENDCALL");
		yyval.obj = ret;
	}
break;
case 29:
//#line 118 "nanomorpho.byacc"
{yyval.obj = val_peek(0).sval;}
break;
case 30:
//#line 120 "nanomorpho.byacc"
{
		expr_tmp.add("~ENDCALL");
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add("~CALL");
		ret.add(val_peek(3).sval);
		int n = expr_tmp.size();
		for (int i = 0; i < n; i++)
		{
			ret.add(expr_tmp.get(i));
		}
		expr_tmp.clear();
		yyval.obj = ret;
	}
break;
case 31:
//#line 134 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add("~CALL");
		ret.add(val_peek(1).sval);
		ret.add(val_peek(0).obj);
		ret.add("~ENDCALL");
		yyval.obj = ret;
	}
break;
case 32:
//#line 143 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add("~CALL");
		ret.add(val_peek(1).sval);
		ret.add(val_peek(0).obj);
		ret.add("~ENDCALL");
		yyval.obj = ret;
	}
break;
case 33:
//#line 152 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add("~CALL");
		ret.add(val_peek(1).sval);
		ret.add(val_peek(0).obj);
		ret.add("~ENDCALL");
		yyval.obj = ret;
	}
break;
case 34:
//#line 161 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add("~CALL");
		ret.add(val_peek(1).sval);
		ret.add(val_peek(0).obj);
		ret.add("~ENDCALL");
		yyval.obj = ret;
	}
break;
case 35:
//#line 170 "nanomorpho.byacc"
{
		ArrayList<Object> ret = new ArrayList<Object>();
		ret.add("~CALL");
		ret.add(val_peek(1).sval);
		ret.add(val_peek(0).obj);
		ret.add("~ENDCALL");
		yyval.obj = ret;
	}
break;
case 36:
//#line 178 "nanomorpho.byacc"
{yyval.obj = val_peek(0).sval;}
break;
case 37:
//#line 179 "nanomorpho.byacc"
{yyval.obj = val_peek(1).obj;}
break;
case 38:
//#line 183 "nanomorpho.byacc"
{expr_tmp.add(val_peek(0).obj);}
break;
case 41:
//#line 188 "nanomorpho.byacc"
{expr_tmp.add(val_peek(0).obj);}
break;
case 44:
//#line 193 "nanomorpho.byacc"
{millithula.add(val_peek(0).obj);}
break;
case 45:
//#line 193 "nanomorpho.byacc"
{millithula.add("~ENDIF");}
break;
case 47:
//#line 197 "nanomorpho.byacc"
{millithula.add("~ELSEIF");}
break;
case 48:
//#line 197 "nanomorpho.byacc"
{millithula.add(val_peek(1).obj);}
break;
case 49:
//#line 197 "nanomorpho.byacc"
{millithula.add("~ENDELSEIF");}
break;
case 52:
//#line 202 "nanomorpho.byacc"
{millithula.add("~ELSE");}
break;
case 53:
//#line 202 "nanomorpho.byacc"
{millithula.add("~ENDELSE");}
break;
case 55:
//#line 207 "nanomorpho.byacc"
{millithula.add(val_peek(1).obj);}
break;
case 57:
//#line 208 "nanomorpho.byacc"
{millithula.add("~WHILE"); millithula.add(val_peek(1).obj);}
break;
case 58:
//#line 208 "nanomorpho.byacc"
{millithula.add("~ENDWHILE");}
break;
case 60:
//#line 209 "nanomorpho.byacc"
{millithula.add("~IF");}
break;
case 62:
//#line 210 "nanomorpho.byacc"
{millithula.add("~RETURN");}
break;
case 63:
//#line 210 "nanomorpho.byacc"
{millithula.add(val_peek(1).obj);}
break;
//#line 1073 "Borpho.java"
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
