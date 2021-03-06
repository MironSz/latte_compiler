package latte;

public class PrettyPrinter
{
  //For certain applications increasing the initial size of the buffer may improve performance.
  private static int INITIAL_BUFFER_SIZE = 128;
  private static int INDENT_WIDTH = 2;
  //You may wish to change the parentheses used in precedence.
  private static String _L_PAREN = new String("(");
  private static String _R_PAREN = new String(")");
  //You may wish to change render
  private static void render(String s)
  {
    if (s.equals("{"))
    {
       buf_.append("\n");
       indent();
       buf_.append(s);
       _n_ = _n_ + INDENT_WIDTH;
       buf_.append("\n");
       indent();
    }
    else if (s.equals("(") || s.equals("["))
       buf_.append(s);
    else if (s.equals(")") || s.equals("]"))
    {
       backup();
       buf_.append(s);
       buf_.append(" ");
    }
    else if (s.equals("}"))
    {
       int t;
       _n_ = _n_ - INDENT_WIDTH;
       for(t=0; t<INDENT_WIDTH; t++) {
         backup();
       }
       buf_.append(s);
       buf_.append("\n");
       indent();
    }
    else if (s.equals(","))
    {
       backup();
       buf_.append(s);
       buf_.append(" ");
    }
    else if (s.equals(";"))
    {
       backup();
       buf_.append(s);
       buf_.append("\n");
       indent();
    }
    else if (s.equals("")) return;
    else if (s.trim().equals(""))
    {
       backup();
       buf_.append(s);
    }
    else
    {
       buf_.append(s);
       buf_.append(" ");
    }
  }


  //  print and show methods are defined for each category.
  public static String print(latte.Absyn.Program foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.Program foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.TopDef foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.TopDef foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.ListTopDef foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.ListTopDef foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.FunDef foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.FunDef foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.Arg foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.Arg foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.ListArg foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.ListArg foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.Class foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.Class foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.ClassStmt foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.ClassStmt foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.ListIdent foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.ListIdent foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.ListClassStmt foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.ListClassStmt foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.Target foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.Target foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.Block foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.Block foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.ListStmt foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.ListStmt foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.Stmt foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.Stmt foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.Item foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.Item foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.ListItem foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.ListItem foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.Type foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.Type foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.ListType foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.ListType foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.Expr foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.Expr foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.ListExpr foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.ListExpr foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.AddOp foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.AddOp foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.MulOp foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.MulOp foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(latte.Absyn.RelOp foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(latte.Absyn.RelOp foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  /***   You shouldn't need to change anything beyond this point.   ***/

  private static void pp(latte.Absyn.Program foo, int _i_)
  {
    if (foo instanceof latte.Absyn.ProgramCode)
    {
       latte.Absyn.ProgramCode _programcode = (latte.Absyn.ProgramCode) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_programcode.listtopdef_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(latte.Absyn.TopDef foo, int _i_)
  {
    if (foo instanceof latte.Absyn.FnDef)
    {
       latte.Absyn.FnDef _fndef = (latte.Absyn.FnDef) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_fndef.fundef_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.ClassDef)
    {
       latte.Absyn.ClassDef _classdef = (latte.Absyn.ClassDef) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_classdef.class_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(latte.Absyn.ListTopDef foo, int _i_)
  {
     for (java.util.Iterator<latte.Absyn.TopDef> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), _i_);
       if (it.hasNext()) {
         render("");
       } else {
         render("");
       }
     }  }

  private static void pp(latte.Absyn.FunDef foo, int _i_)
  {
    if (foo instanceof latte.Absyn.FunDefCode)
    {
       latte.Absyn.FunDefCode _fundefcode = (latte.Absyn.FunDefCode) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_fundefcode.type_, 0);
       pp(_fundefcode.ident_, 0);
       render("(");
       pp(_fundefcode.listarg_, 0);
       render(")");
       pp(_fundefcode.block_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(latte.Absyn.Arg foo, int _i_)
  {
    if (foo instanceof latte.Absyn.ArgCode)
    {
       latte.Absyn.ArgCode _argcode = (latte.Absyn.ArgCode) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_argcode.type_, 0);
       pp(_argcode.ident_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(latte.Absyn.ListArg foo, int _i_)
  {
     for (java.util.Iterator<latte.Absyn.Arg> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), _i_);
       if (it.hasNext()) {
         render(",");
       } else {
         render("");
       }
     }  }

  private static void pp(latte.Absyn.Class foo, int _i_)
  {
    if (foo instanceof latte.Absyn.ClassCode)
    {
       latte.Absyn.ClassCode _classcode = (latte.Absyn.ClassCode) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("class");
       pp(_classcode.ident_, 0);
       render("{");
       pp(_classcode.listclassstmt_, 0);
       render("}");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.ClassExt)
    {
       latte.Absyn.ClassExt _classext = (latte.Absyn.ClassExt) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("class");
       pp(_classext.ident_1, 0);
       render("extends");
       pp(_classext.ident_2, 0);
       render("{");
       pp(_classext.listclassstmt_, 0);
       render("}");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(latte.Absyn.ClassStmt foo, int _i_)
  {
    if (foo instanceof latte.Absyn.Fields)
    {
       latte.Absyn.Fields _fields = (latte.Absyn.Fields) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_fields.type_, 0);
       pp(_fields.listident_, 0);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.Method)
    {
       latte.Absyn.Method _method = (latte.Absyn.Method) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_method.fundef_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(latte.Absyn.ListIdent foo, int _i_)
  {
     for (java.util.Iterator<String> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), _i_);
       if (it.hasNext()) {
         render(",");
       } else {
         render("");
       }
     }  }

  private static void pp(latte.Absyn.ListClassStmt foo, int _i_)
  {
     for (java.util.Iterator<latte.Absyn.ClassStmt> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), _i_);
       if (it.hasNext()) {
         render("");
       } else {
         render("");
       }
     }  }

  private static void pp(latte.Absyn.Target foo, int _i_)
  {
    if (foo instanceof latte.Absyn.Variable)
    {
       latte.Absyn.Variable _variable = (latte.Absyn.Variable) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_variable.ident_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.FieldT)
    {
       latte.Absyn.FieldT _fieldt = (latte.Absyn.FieldT) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_fieldt.expr_, 6);
       render(".");
       pp(_fieldt.ident_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(latte.Absyn.Block foo, int _i_)
  {
    if (foo instanceof latte.Absyn.BlockCode)
    {
       latte.Absyn.BlockCode _blockcode = (latte.Absyn.BlockCode) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("{");
       pp(_blockcode.liststmt_, 0);
       render("}");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(latte.Absyn.ListStmt foo, int _i_)
  {
     for (java.util.Iterator<latte.Absyn.Stmt> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), _i_);
       if (it.hasNext()) {
         render("");
       } else {
         render("");
       }
     }  }

  private static void pp(latte.Absyn.Stmt foo, int _i_)
  {
    if (foo instanceof latte.Absyn.Empty)
    {
       latte.Absyn.Empty _empty = (latte.Absyn.Empty) foo;
       if (_i_ > 0) render(_L_PAREN);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.BStmt)
    {
       latte.Absyn.BStmt _bstmt = (latte.Absyn.BStmt) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_bstmt.block_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.Decl)
    {
       latte.Absyn.Decl _decl = (latte.Absyn.Decl) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_decl.type_, 0);
       pp(_decl.listitem_, 0);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.Ass)
    {
       latte.Absyn.Ass _ass = (latte.Absyn.Ass) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_ass.target_, 0);
       render("=");
       pp(_ass.expr_, 0);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.Incr)
    {
       latte.Absyn.Incr _incr = (latte.Absyn.Incr) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_incr.target_, 0);
       render("++");
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.Decr)
    {
       latte.Absyn.Decr _decr = (latte.Absyn.Decr) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_decr.target_, 0);
       render("--");
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.Ret)
    {
       latte.Absyn.Ret _ret = (latte.Absyn.Ret) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("return");
       pp(_ret.expr_, 0);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.VRet)
    {
       latte.Absyn.VRet _vret = (latte.Absyn.VRet) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("return");
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.Cond)
    {
       latte.Absyn.Cond _cond = (latte.Absyn.Cond) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("if");
       render("(");
       pp(_cond.expr_, 0);
       render(")");
       pp(_cond.stmt_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.CondElse)
    {
       latte.Absyn.CondElse _condelse = (latte.Absyn.CondElse) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("if");
       render("(");
       pp(_condelse.expr_, 0);
       render(")");
       pp(_condelse.stmt_1, 0);
       render("else");
       pp(_condelse.stmt_2, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.While)
    {
       latte.Absyn.While _while = (latte.Absyn.While) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("while");
       render("(");
       pp(_while.expr_, 0);
       render(")");
       pp(_while.stmt_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.SExp)
    {
       latte.Absyn.SExp _sexp = (latte.Absyn.SExp) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_sexp.expr_, 0);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(latte.Absyn.Item foo, int _i_)
  {
    if (foo instanceof latte.Absyn.NoInit)
    {
       latte.Absyn.NoInit _noinit = (latte.Absyn.NoInit) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_noinit.ident_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.Init)
    {
       latte.Absyn.Init _init = (latte.Absyn.Init) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_init.ident_, 0);
       render("=");
       pp(_init.expr_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(latte.Absyn.ListItem foo, int _i_)
  {
     for (java.util.Iterator<latte.Absyn.Item> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), _i_);
       if (it.hasNext()) {
         render(",");
       } else {
         render("");
       }
     }  }

  private static void pp(latte.Absyn.Type foo, int _i_)
  {
    if (foo instanceof latte.Absyn.Int)
    {
       latte.Absyn.Int _int = (latte.Absyn.Int) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("int");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.Str)
    {
       latte.Absyn.Str _str = (latte.Absyn.Str) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("string");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.Bool)
    {
       latte.Absyn.Bool _bool = (latte.Absyn.Bool) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("boolean");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.Void)
    {
       latte.Absyn.Void _void = (latte.Absyn.Void) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("void");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.ClassType)
    {
       latte.Absyn.ClassType _classtype = (latte.Absyn.ClassType) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_classtype.ident_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.Fun)
    {
       latte.Absyn.Fun _fun = (latte.Absyn.Fun) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_fun.type_, 0);
       render("(");
       pp(_fun.listtype_, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(latte.Absyn.ListType foo, int _i_)
  {
     for (java.util.Iterator<latte.Absyn.Type> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), _i_);
       if (it.hasNext()) {
         render(",");
       } else {
         render("");
       }
     }  }

  private static void pp(latte.Absyn.Expr foo, int _i_)
  {
    if (foo instanceof latte.Absyn.EVar)
    {
       latte.Absyn.EVar _evar = (latte.Absyn.EVar) foo;
       if (_i_ > 6) render(_L_PAREN);
       pp(_evar.target_, 0);
       if (_i_ > 6) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.ECast)
    {
       latte.Absyn.ECast _ecast = (latte.Absyn.ECast) foo;
       if (_i_ > 6) render(_L_PAREN);
       render("<<");
       pp(_ecast.ident_, 0);
       render(">>");
       pp(_ecast.expr_, 0);
       if (_i_ > 6) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.ENewObj)
    {
       latte.Absyn.ENewObj _enewobj = (latte.Absyn.ENewObj) foo;
       if (_i_ > 6) render(_L_PAREN);
       render("new");
       pp(_enewobj.ident_, 0);
       if (_i_ > 6) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.ELitInt)
    {
       latte.Absyn.ELitInt _elitint = (latte.Absyn.ELitInt) foo;
       if (_i_ > 6) render(_L_PAREN);
       pp(_elitint.integer_, 0);
       if (_i_ > 6) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.ELitTrue)
    {
       latte.Absyn.ELitTrue _elittrue = (latte.Absyn.ELitTrue) foo;
       if (_i_ > 6) render(_L_PAREN);
       render("true");
       if (_i_ > 6) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.ELitFalse)
    {
       latte.Absyn.ELitFalse _elitfalse = (latte.Absyn.ELitFalse) foo;
       if (_i_ > 6) render(_L_PAREN);
       render("false");
       if (_i_ > 6) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.EApp)
    {
       latte.Absyn.EApp _eapp = (latte.Absyn.EApp) foo;
       if (_i_ > 6) render(_L_PAREN);
       pp(_eapp.target_, 0);
       render("(");
       pp(_eapp.listexpr_, 0);
       render(")");
       if (_i_ > 6) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.EString)
    {
       latte.Absyn.EString _estring = (latte.Absyn.EString) foo;
       if (_i_ > 6) render(_L_PAREN);
       printQuoted(_estring.string_);
       if (_i_ > 6) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.ENull)
    {
       latte.Absyn.ENull _enull = (latte.Absyn.ENull) foo;
       if (_i_ > 6) render(_L_PAREN);
       render("null");
       if (_i_ > 6) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.Neg)
    {
       latte.Absyn.Neg _neg = (latte.Absyn.Neg) foo;
       if (_i_ > 5) render(_L_PAREN);
       render("-");
       pp(_neg.expr_, 6);
       if (_i_ > 5) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.Not)
    {
       latte.Absyn.Not _not = (latte.Absyn.Not) foo;
       if (_i_ > 5) render(_L_PAREN);
       render("!");
       pp(_not.expr_, 6);
       if (_i_ > 5) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.EMul)
    {
       latte.Absyn.EMul _emul = (latte.Absyn.EMul) foo;
       if (_i_ > 4) render(_L_PAREN);
       pp(_emul.expr_1, 4);
       pp(_emul.mulop_, 0);
       pp(_emul.expr_2, 5);
       if (_i_ > 4) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.EAdd)
    {
       latte.Absyn.EAdd _eadd = (latte.Absyn.EAdd) foo;
       if (_i_ > 3) render(_L_PAREN);
       pp(_eadd.expr_1, 3);
       pp(_eadd.addop_, 0);
       pp(_eadd.expr_2, 4);
       if (_i_ > 3) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.ERel)
    {
       latte.Absyn.ERel _erel = (latte.Absyn.ERel) foo;
       if (_i_ > 2) render(_L_PAREN);
       pp(_erel.expr_1, 2);
       pp(_erel.relop_, 0);
       pp(_erel.expr_2, 3);
       if (_i_ > 2) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.EAnd)
    {
       latte.Absyn.EAnd _eand = (latte.Absyn.EAnd) foo;
       if (_i_ > 1) render(_L_PAREN);
       pp(_eand.expr_1, 2);
       render("&&");
       pp(_eand.expr_2, 1);
       if (_i_ > 1) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.EOr)
    {
       latte.Absyn.EOr _eor = (latte.Absyn.EOr) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_eor.expr_1, 1);
       render("||");
       pp(_eor.expr_2, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(latte.Absyn.ListExpr foo, int _i_)
  {
     for (java.util.Iterator<latte.Absyn.Expr> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), _i_);
       if (it.hasNext()) {
         render(",");
       } else {
         render("");
       }
     }  }

  private static void pp(latte.Absyn.AddOp foo, int _i_)
  {
    if (foo instanceof latte.Absyn.Plus)
    {
       latte.Absyn.Plus _plus = (latte.Absyn.Plus) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("+");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.Minus)
    {
       latte.Absyn.Minus _minus = (latte.Absyn.Minus) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("-");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(latte.Absyn.MulOp foo, int _i_)
  {
    if (foo instanceof latte.Absyn.Times)
    {
       latte.Absyn.Times _times = (latte.Absyn.Times) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("*");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.Div)
    {
       latte.Absyn.Div _div = (latte.Absyn.Div) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("/");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.Mod)
    {
       latte.Absyn.Mod _mod = (latte.Absyn.Mod) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("%");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(latte.Absyn.RelOp foo, int _i_)
  {
    if (foo instanceof latte.Absyn.LTH)
    {
       latte.Absyn.LTH _lth = (latte.Absyn.LTH) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("<");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.LE)
    {
       latte.Absyn.LE _le = (latte.Absyn.LE) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("<=");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.GTH)
    {
       latte.Absyn.GTH _gth = (latte.Absyn.GTH) foo;
       if (_i_ > 0) render(_L_PAREN);
       render(">");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.GE)
    {
       latte.Absyn.GE _ge = (latte.Absyn.GE) foo;
       if (_i_ > 0) render(_L_PAREN);
       render(">=");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.EQU)
    {
       latte.Absyn.EQU _equ = (latte.Absyn.EQU) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("==");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof latte.Absyn.NE)
    {
       latte.Absyn.NE _ne = (latte.Absyn.NE) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("!=");
       if (_i_ > 0) render(_R_PAREN);
    }
  }


  private static void sh(latte.Absyn.Program foo)
  {
    if (foo instanceof latte.Absyn.ProgramCode)
    {
       latte.Absyn.ProgramCode _programcode = (latte.Absyn.ProgramCode) foo;
       render("(");
       render("ProgramCode");
       render("[");
       sh(_programcode.listtopdef_);
       render("]");
       render(")");
    }
  }

  private static void sh(latte.Absyn.TopDef foo)
  {
    if (foo instanceof latte.Absyn.FnDef)
    {
       latte.Absyn.FnDef _fndef = (latte.Absyn.FnDef) foo;
       render("(");
       render("FnDef");
       sh(_fndef.fundef_);
       render(")");
    }
    if (foo instanceof latte.Absyn.ClassDef)
    {
       latte.Absyn.ClassDef _classdef = (latte.Absyn.ClassDef) foo;
       render("(");
       render("ClassDef");
       sh(_classdef.class_);
       render(")");
    }
  }

  private static void sh(latte.Absyn.ListTopDef foo)
  {
     for (java.util.Iterator<latte.Absyn.TopDef> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(latte.Absyn.FunDef foo)
  {
    if (foo instanceof latte.Absyn.FunDefCode)
    {
       latte.Absyn.FunDefCode _fundefcode = (latte.Absyn.FunDefCode) foo;
       render("(");
       render("FunDefCode");
       sh(_fundefcode.type_);
       sh(_fundefcode.ident_);
       render("[");
       sh(_fundefcode.listarg_);
       render("]");
       sh(_fundefcode.block_);
       render(")");
    }
  }

  private static void sh(latte.Absyn.Arg foo)
  {
    if (foo instanceof latte.Absyn.ArgCode)
    {
       latte.Absyn.ArgCode _argcode = (latte.Absyn.ArgCode) foo;
       render("(");
       render("ArgCode");
       sh(_argcode.type_);
       sh(_argcode.ident_);
       render(")");
    }
  }

  private static void sh(latte.Absyn.ListArg foo)
  {
     for (java.util.Iterator<latte.Absyn.Arg> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(latte.Absyn.Class foo)
  {
    if (foo instanceof latte.Absyn.ClassCode)
    {
       latte.Absyn.ClassCode _classcode = (latte.Absyn.ClassCode) foo;
       render("(");
       render("ClassCode");
       sh(_classcode.ident_);
       render("[");
       sh(_classcode.listclassstmt_);
       render("]");
       render(")");
    }
    if (foo instanceof latte.Absyn.ClassExt)
    {
       latte.Absyn.ClassExt _classext = (latte.Absyn.ClassExt) foo;
       render("(");
       render("ClassExt");
       sh(_classext.ident_1);
       sh(_classext.ident_2);
       render("[");
       sh(_classext.listclassstmt_);
       render("]");
       render(")");
    }
  }

  private static void sh(latte.Absyn.ClassStmt foo)
  {
    if (foo instanceof latte.Absyn.Fields)
    {
       latte.Absyn.Fields _fields = (latte.Absyn.Fields) foo;
       render("(");
       render("Fields");
       sh(_fields.type_);
       render("[");
       sh(_fields.listident_);
       render("]");
       render(")");
    }
    if (foo instanceof latte.Absyn.Method)
    {
       latte.Absyn.Method _method = (latte.Absyn.Method) foo;
       render("(");
       render("Method");
       sh(_method.fundef_);
       render(")");
    }
  }

  private static void sh(latte.Absyn.ListIdent foo)
  {
     for (java.util.Iterator<String> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(latte.Absyn.ListClassStmt foo)
  {
     for (java.util.Iterator<latte.Absyn.ClassStmt> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(latte.Absyn.Target foo)
  {
    if (foo instanceof latte.Absyn.Variable)
    {
       latte.Absyn.Variable _variable = (latte.Absyn.Variable) foo;
       render("(");
       render("Variable");
       sh(_variable.ident_);
       render(")");
    }
    if (foo instanceof latte.Absyn.FieldT)
    {
       latte.Absyn.FieldT _fieldt = (latte.Absyn.FieldT) foo;
       render("(");
       render("FieldT");
       sh(_fieldt.expr_);
       sh(_fieldt.ident_);
       render(")");
    }
  }

  private static void sh(latte.Absyn.Block foo)
  {
    if (foo instanceof latte.Absyn.BlockCode)
    {
       latte.Absyn.BlockCode _blockcode = (latte.Absyn.BlockCode) foo;
       render("(");
       render("BlockCode");
       render("[");
       sh(_blockcode.liststmt_);
       render("]");
       render(")");
    }
  }

  private static void sh(latte.Absyn.ListStmt foo)
  {
     for (java.util.Iterator<latte.Absyn.Stmt> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(latte.Absyn.Stmt foo)
  {
    if (foo instanceof latte.Absyn.Empty)
    {
       latte.Absyn.Empty _empty = (latte.Absyn.Empty) foo;
       render("Empty");
    }
    if (foo instanceof latte.Absyn.BStmt)
    {
       latte.Absyn.BStmt _bstmt = (latte.Absyn.BStmt) foo;
       render("(");
       render("BStmt");
       sh(_bstmt.block_);
       render(")");
    }
    if (foo instanceof latte.Absyn.Decl)
    {
       latte.Absyn.Decl _decl = (latte.Absyn.Decl) foo;
       render("(");
       render("Decl");
       sh(_decl.type_);
       render("[");
       sh(_decl.listitem_);
       render("]");
       render(")");
    }
    if (foo instanceof latte.Absyn.Ass)
    {
       latte.Absyn.Ass _ass = (latte.Absyn.Ass) foo;
       render("(");
       render("Ass");
       sh(_ass.target_);
       sh(_ass.expr_);
       render(")");
    }
    if (foo instanceof latte.Absyn.Incr)
    {
       latte.Absyn.Incr _incr = (latte.Absyn.Incr) foo;
       render("(");
       render("Incr");
       sh(_incr.target_);
       render(")");
    }
    if (foo instanceof latte.Absyn.Decr)
    {
       latte.Absyn.Decr _decr = (latte.Absyn.Decr) foo;
       render("(");
       render("Decr");
       sh(_decr.target_);
       render(")");
    }
    if (foo instanceof latte.Absyn.Ret)
    {
       latte.Absyn.Ret _ret = (latte.Absyn.Ret) foo;
       render("(");
       render("Ret");
       sh(_ret.expr_);
       render(")");
    }
    if (foo instanceof latte.Absyn.VRet)
    {
       latte.Absyn.VRet _vret = (latte.Absyn.VRet) foo;
       render("VRet");
    }
    if (foo instanceof latte.Absyn.Cond)
    {
       latte.Absyn.Cond _cond = (latte.Absyn.Cond) foo;
       render("(");
       render("Cond");
       sh(_cond.expr_);
       sh(_cond.stmt_);
       render(")");
    }
    if (foo instanceof latte.Absyn.CondElse)
    {
       latte.Absyn.CondElse _condelse = (latte.Absyn.CondElse) foo;
       render("(");
       render("CondElse");
       sh(_condelse.expr_);
       sh(_condelse.stmt_1);
       sh(_condelse.stmt_2);
       render(")");
    }
    if (foo instanceof latte.Absyn.While)
    {
       latte.Absyn.While _while = (latte.Absyn.While) foo;
       render("(");
       render("While");
       sh(_while.expr_);
       sh(_while.stmt_);
       render(")");
    }
    if (foo instanceof latte.Absyn.SExp)
    {
       latte.Absyn.SExp _sexp = (latte.Absyn.SExp) foo;
       render("(");
       render("SExp");
       sh(_sexp.expr_);
       render(")");
    }
  }

  private static void sh(latte.Absyn.Item foo)
  {
    if (foo instanceof latte.Absyn.NoInit)
    {
       latte.Absyn.NoInit _noinit = (latte.Absyn.NoInit) foo;
       render("(");
       render("NoInit");
       sh(_noinit.ident_);
       render(")");
    }
    if (foo instanceof latte.Absyn.Init)
    {
       latte.Absyn.Init _init = (latte.Absyn.Init) foo;
       render("(");
       render("Init");
       sh(_init.ident_);
       sh(_init.expr_);
       render(")");
    }
  }

  private static void sh(latte.Absyn.ListItem foo)
  {
     for (java.util.Iterator<latte.Absyn.Item> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(latte.Absyn.Type foo)
  {
    if (foo instanceof latte.Absyn.Int)
    {
       latte.Absyn.Int _int = (latte.Absyn.Int) foo;
       render("Int");
    }
    if (foo instanceof latte.Absyn.Str)
    {
       latte.Absyn.Str _str = (latte.Absyn.Str) foo;
       render("Str");
    }
    if (foo instanceof latte.Absyn.Bool)
    {
       latte.Absyn.Bool _bool = (latte.Absyn.Bool) foo;
       render("Bool");
    }
    if (foo instanceof latte.Absyn.Void)
    {
       latte.Absyn.Void _void = (latte.Absyn.Void) foo;
       render("Void");
    }
    if (foo instanceof latte.Absyn.ClassType)
    {
       latte.Absyn.ClassType _classtype = (latte.Absyn.ClassType) foo;
       render("(");
       render("ClassType");
       sh(_classtype.ident_);
       render(")");
    }
    if (foo instanceof latte.Absyn.Fun)
    {
       latte.Absyn.Fun _fun = (latte.Absyn.Fun) foo;
       render("(");
       render("Fun");
       sh(_fun.type_);
       render("[");
       sh(_fun.listtype_);
       render("]");
       render(")");
    }
  }

  private static void sh(latte.Absyn.ListType foo)
  {
     for (java.util.Iterator<latte.Absyn.Type> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(latte.Absyn.Expr foo)
  {
    if (foo instanceof latte.Absyn.EVar)
    {
       latte.Absyn.EVar _evar = (latte.Absyn.EVar) foo;
       render("(");
       render("EVar");
       sh(_evar.target_);
       render(")");
    }
    if (foo instanceof latte.Absyn.ECast)
    {
       latte.Absyn.ECast _ecast = (latte.Absyn.ECast) foo;
       render("(");
       render("ECast");
       sh(_ecast.ident_);
       sh(_ecast.expr_);
       render(")");
    }
    if (foo instanceof latte.Absyn.ENewObj)
    {
       latte.Absyn.ENewObj _enewobj = (latte.Absyn.ENewObj) foo;
       render("(");
       render("ENewObj");
       sh(_enewobj.ident_);
       render(")");
    }
    if (foo instanceof latte.Absyn.ELitInt)
    {
       latte.Absyn.ELitInt _elitint = (latte.Absyn.ELitInt) foo;
       render("(");
       render("ELitInt");
       sh(_elitint.integer_);
       render(")");
    }
    if (foo instanceof latte.Absyn.ELitTrue)
    {
       latte.Absyn.ELitTrue _elittrue = (latte.Absyn.ELitTrue) foo;
       render("ELitTrue");
    }
    if (foo instanceof latte.Absyn.ELitFalse)
    {
       latte.Absyn.ELitFalse _elitfalse = (latte.Absyn.ELitFalse) foo;
       render("ELitFalse");
    }
    if (foo instanceof latte.Absyn.EApp)
    {
       latte.Absyn.EApp _eapp = (latte.Absyn.EApp) foo;
       render("(");
       render("EApp");
       sh(_eapp.target_);
       render("[");
       sh(_eapp.listexpr_);
       render("]");
       render(")");
    }
    if (foo instanceof latte.Absyn.EString)
    {
       latte.Absyn.EString _estring = (latte.Absyn.EString) foo;
       render("(");
       render("EString");
       sh(_estring.string_);
       render(")");
    }
    if (foo instanceof latte.Absyn.ENull)
    {
       latte.Absyn.ENull _enull = (latte.Absyn.ENull) foo;
       render("ENull");
    }
    if (foo instanceof latte.Absyn.Neg)
    {
       latte.Absyn.Neg _neg = (latte.Absyn.Neg) foo;
       render("(");
       render("Neg");
       sh(_neg.expr_);
       render(")");
    }
    if (foo instanceof latte.Absyn.Not)
    {
       latte.Absyn.Not _not = (latte.Absyn.Not) foo;
       render("(");
       render("Not");
       sh(_not.expr_);
       render(")");
    }
    if (foo instanceof latte.Absyn.EMul)
    {
       latte.Absyn.EMul _emul = (latte.Absyn.EMul) foo;
       render("(");
       render("EMul");
       sh(_emul.expr_1);
       sh(_emul.mulop_);
       sh(_emul.expr_2);
       render(")");
    }
    if (foo instanceof latte.Absyn.EAdd)
    {
       latte.Absyn.EAdd _eadd = (latte.Absyn.EAdd) foo;
       render("(");
       render("EAdd");
       sh(_eadd.expr_1);
       sh(_eadd.addop_);
       sh(_eadd.expr_2);
       render(")");
    }
    if (foo instanceof latte.Absyn.ERel)
    {
       latte.Absyn.ERel _erel = (latte.Absyn.ERel) foo;
       render("(");
       render("ERel");
       sh(_erel.expr_1);
       sh(_erel.relop_);
       sh(_erel.expr_2);
       render(")");
    }
    if (foo instanceof latte.Absyn.EAnd)
    {
       latte.Absyn.EAnd _eand = (latte.Absyn.EAnd) foo;
       render("(");
       render("EAnd");
       sh(_eand.expr_1);
       sh(_eand.expr_2);
       render(")");
    }
    if (foo instanceof latte.Absyn.EOr)
    {
       latte.Absyn.EOr _eor = (latte.Absyn.EOr) foo;
       render("(");
       render("EOr");
       sh(_eor.expr_1);
       sh(_eor.expr_2);
       render(")");
    }
  }

  private static void sh(latte.Absyn.ListExpr foo)
  {
     for (java.util.Iterator<latte.Absyn.Expr> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(latte.Absyn.AddOp foo)
  {
    if (foo instanceof latte.Absyn.Plus)
    {
       latte.Absyn.Plus _plus = (latte.Absyn.Plus) foo;
       render("Plus");
    }
    if (foo instanceof latte.Absyn.Minus)
    {
       latte.Absyn.Minus _minus = (latte.Absyn.Minus) foo;
       render("Minus");
    }
  }

  private static void sh(latte.Absyn.MulOp foo)
  {
    if (foo instanceof latte.Absyn.Times)
    {
       latte.Absyn.Times _times = (latte.Absyn.Times) foo;
       render("Times");
    }
    if (foo instanceof latte.Absyn.Div)
    {
       latte.Absyn.Div _div = (latte.Absyn.Div) foo;
       render("Div");
    }
    if (foo instanceof latte.Absyn.Mod)
    {
       latte.Absyn.Mod _mod = (latte.Absyn.Mod) foo;
       render("Mod");
    }
  }

  private static void sh(latte.Absyn.RelOp foo)
  {
    if (foo instanceof latte.Absyn.LTH)
    {
       latte.Absyn.LTH _lth = (latte.Absyn.LTH) foo;
       render("LTH");
    }
    if (foo instanceof latte.Absyn.LE)
    {
       latte.Absyn.LE _le = (latte.Absyn.LE) foo;
       render("LE");
    }
    if (foo instanceof latte.Absyn.GTH)
    {
       latte.Absyn.GTH _gth = (latte.Absyn.GTH) foo;
       render("GTH");
    }
    if (foo instanceof latte.Absyn.GE)
    {
       latte.Absyn.GE _ge = (latte.Absyn.GE) foo;
       render("GE");
    }
    if (foo instanceof latte.Absyn.EQU)
    {
       latte.Absyn.EQU _equ = (latte.Absyn.EQU) foo;
       render("EQU");
    }
    if (foo instanceof latte.Absyn.NE)
    {
       latte.Absyn.NE _ne = (latte.Absyn.NE) foo;
       render("NE");
    }
  }


  private static void pp(Integer n, int _i_) { buf_.append(n); buf_.append(" "); }
  private static void pp(Double d, int _i_) { buf_.append(String.format(java.util.Locale.ROOT, "%.15g ", d)); }
  private static void pp(String s, int _i_) { buf_.append(s); buf_.append(" "); }
  private static void pp(Character c, int _i_) { buf_.append("'" + c.toString() + "'"); buf_.append(" "); }
  private static void sh(Integer n) { render(n.toString()); }
  private static void sh(Double d) { render(String.format(java.util.Locale.ROOT, "%.15g", d)); }
  private static void sh(Character c) { render("'" + c.toString() + "'"); }
  private static void sh(String s) { printQuoted(s); }
  private static void printQuoted(String s) { render("\"" + s + "\""); }
  private static void indent()
  {
    int n = _n_;
    while (n > 0)
    {
      buf_.append(' ');
      n--;
    }
  }
  private static void backup()
  {
    int prev = buf_.length() - 1;
    if (buf_.charAt(prev) == ' ')
      buf_.setLength(prev);
  }
  private static void trim()
  {
    // Trim initial spaces
    int end = 0;
    int len = buf_.length();
    while (end < len && buf_.charAt(end) == ' ')
      end++;
    buf_.delete(0, end);
    // Trim trailing spaces
    end = buf_.length();
    while (end > 0 && buf_.charAt(end-1) == ' ')
      end--;
    buf_.setLength(end);
  }
  private static int _n_ = 0;
  private static StringBuilder buf_ = new StringBuilder(INITIAL_BUFFER_SIZE);
}

