package latte;

import latte.Absyn.*;


/** BNFC-Generated All Visitor */

public interface AllVisitor<R,A> extends
  Program.Visitor<R,A>,
  TopDef.Visitor<R,A>,
  Arg.Visitor<R,A>,
  Block.Visitor<R,A>,
  Stmt.Visitor<R,A>,
  Item.Visitor<R,A>,
  Type.Visitor<R,A>,
  Expr.Visitor<R,A>,
  AddOp.Visitor<R,A>,
  MulOp.Visitor<R,A>,
  RelOp.Visitor<R,A>
{}
