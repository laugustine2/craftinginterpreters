package com.craftinginterpreters.lox;

import com.craftinginterpreters.lox.Expr.Binary;
import com.craftinginterpreters.lox.Expr.Grouping;
import com.craftinginterpreters.lox.Expr.Literal;
import com.craftinginterpreters.lox.Expr.Unary;
import com.craftinginterpreters.lox.Expr.Visitor;

public class AstPrinter implements Visitor<String> {

  String print(Expr expr) {
    return expr.accept(this);
  }

  @Override
  public String visitBinaryExpr(Binary expr) {
    return expr.left.accept(this)
        + " "
        + expr.right.accept(this)
        + " "
        + expr.operator.lexeme;
  }

  @Override
  public String visitGroupingExpr(Grouping expr) {
    return expr.expression.accept(this);
  }

  @Override
  public String visitLiteralExpr(Literal expr) {
    if (expr.value == null) {
      return "nil";
    }
    return expr.value.toString();
  }

  @Override
  public String visitUnaryExpr(Unary expr) {
    return expr.right.accept(this) + " " + expr.operator.lexeme;
  }

  private String parenthesize(String name, Expr... exprs) {
    StringBuilder builder = new StringBuilder();
    builder.append("(").append(name);
    for (Expr expr : exprs) {
      builder.append(" ");
      builder.append(expr.accept(this));
    }
    builder.append(")");

    return builder.toString();
  }

  public static void main(String[] args) {
    Expr expression = new Binary(
        new Unary(new Token(TokenType.MINUS, "-", null, 1), new Literal(123)),
        new Token(TokenType.STAR, "*", null, 1),
        new Grouping(new Literal((45.67)))
    );
    System.out.println(new AstPrinter().print(expression));

    expression = new Binary(
        new Binary(
            new Binary(
                new Literal("x"),
                new Token(TokenType.MINUS, "^", null, 1),
                new Literal("y")
            ),
            new Token(TokenType.SLASH, "/", null, 1),
            new Binary(
                new Literal(5),
                new Token(TokenType.STAR, "*", null, 1),
                new Literal("z")
            )
        ),
        new Token(TokenType.PLUS, "+", null, 1),
        new Unary(new Token(TokenType.MINUS, "-", null, 1), new Literal(10))
    );
    System.out.println(new AstPrinter().print(expression));
  }
}
