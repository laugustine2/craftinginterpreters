package com.craftinginterpreters.lox;

import com.craftinginterpreters.lox.Expr.Assign;
import com.craftinginterpreters.lox.Expr.Binary;
import com.craftinginterpreters.lox.Expr.Call;
import com.craftinginterpreters.lox.Expr.Get;
import com.craftinginterpreters.lox.Expr.Grouping;
import com.craftinginterpreters.lox.Expr.Literal;
import com.craftinginterpreters.lox.Expr.Logical;
import com.craftinginterpreters.lox.Expr.Set;
import com.craftinginterpreters.lox.Expr.Super;
import com.craftinginterpreters.lox.Expr.This;
import com.craftinginterpreters.lox.Expr.Unary;
import com.craftinginterpreters.lox.Expr.Variable;
import com.craftinginterpreters.lox.Stmt.Block;
import com.craftinginterpreters.lox.Stmt.Class;
import com.craftinginterpreters.lox.Stmt.Expression;
import com.craftinginterpreters.lox.Stmt.Function;
import com.craftinginterpreters.lox.Stmt.If;
import com.craftinginterpreters.lox.Stmt.Print;
import com.craftinginterpreters.lox.Stmt.Trait;
import com.craftinginterpreters.lox.Stmt.Var;
import com.craftinginterpreters.lox.Stmt.While;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class TraitsTest {

  @Test
  void testScanner() {
    var source = "trait Foo { foo() { print \"foo\"; } } class Bar has Foo {} Bar().foo();";
    // tokenize
    var scanner = new Scanner(source);
    var tokens = scanner.scanTokens();
    System.out.println(tokens);
    // parse
    var parser = new Parser(tokens);
    var ast = parser.parse();
    ast.stream()
        .map(stmt -> stmt.accept(new ASTPrinter()))
        .forEach(System.out::println);
    // resolve
    var interpreter = new Interpreter();
    var resolver = new Resolver(interpreter);
    resolver.resolve(ast);
    // interpret
    interpreter.interpret(ast);
  }

  static class ASTPrinter implements Expr.Visitor<String>, Stmt.Visitor<String> {

    @Override
    public String visitBinaryExpr(Binary expr) {
      return String.format("(%s %s %s)",
          expr.operator.lexeme,
          expr.left.accept(this),
          expr.right.accept(this));
    }

    @Override
    public String visitCallExpr(Call expr) {
      if (expr.arguments.isEmpty()) {
        return String.format("(call %s)", expr.callee.accept(this));
      } else {
        return String.format("(call %s %s)",
            expr.callee.accept(this),
            expr.arguments.stream()
                .map(e -> e.accept(this))
                .collect(Collectors.joining(" ")));
      }
    }

    @Override
    public String visitGetExpr(Get expr) {
      return String.format("(get %s %s)",
          expr.object.accept(this),
          expr.name.lexeme);
    }

    @Override
    public String visitSetExpr(Set expr) {
      return String.format("(set %s %s)",
          expr.object.accept(this),
          expr.name.lexeme);
    }

    @Override
    public String visitSuperExpr(Super expr) {
      return String.format("(super %s)", expr.method.lexeme);
    }

    @Override
    public String visitThisExpr(This expr) {
      return "this";
    }

    @Override
    public String visitLogicalExpr(Logical expr) {
      return String.format("(%s %s %s)",
          expr.operator.lexeme,
          expr.left.accept(this),
          expr.right.accept(this));
    }

    @Override
    public String visitGroupingExpr(Grouping expr) {
      return String.format("(%s)", expr.expression.accept(this));
    }

    @Override
    public String visitLiteralExpr(Literal expr) {
      return expr.value instanceof String ? String.format("'%s'", expr.value)
          : expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Unary expr) {
      return String.format("(%s %s)",
          expr.operator.lexeme,
          expr.right.accept(this));
    }

    @Override
    public String visitVariableExpr(Variable expr) {
      return expr.name.lexeme;
    }

    @Override
    public String visitAssignExpr(Assign expr) {
      return String.format("(= %s %s)",
          expr.name.lexeme,
          expr.value.accept(this));
    }

    @Override
    public String visitExpressionStmt(Expression stmt) {
      return stmt.expression.accept(this);
    }

    @Override
    public String visitFunctionStmt(Function stmt) {
      return String.format("(func %s(%s)\n%s)",
          stmt.name.lexeme,
          stmt.params.stream()
              .map(s -> s.lexeme)
              .collect(Collectors.joining(" ")),
          stmt.body.stream()
              .map(s -> s.accept(this))
              .map(s -> "\t" + s)
              .collect(Collectors.joining("\n")));
    }

    @Override
    public String visitIfStmt(If stmt) {
      if (stmt.elseBranch == null) {
        return String.format("(if (%s)\n\t%s)",
            stmt.condition.accept(this),
            stmt.thenBranch.accept(this));
      } else {
        return String.format("(if (%s)\n\t%s\n\t%s)",
            stmt.condition.accept(this),
            stmt.thenBranch.accept(this),
            stmt.elseBranch.accept(this));
      }
    }

    @Override
    public String visitPrintStmt(Print stmt) {
      return String.format("(print %s)", stmt.expression.accept(this));
    }

    @Override
    public String visitReturnStmt(Stmt.Return stmt) {
      return String.format("(return %s)", stmt.value.accept(this));
    }

    @Override
    public String visitVarStmt(Var stmt) {
      if (stmt.initializer == null) {
        return String.format("(var %s)", stmt.name.lexeme);
      } else {
        return String.format("(var %s %s)",
            stmt.name.lexeme,
            stmt.initializer.accept(this));
      }
    }

    @Override
    public String visitWhileStmt(While stmt) {
      return String.format("(while (%s)\n%s)",
          stmt.condition.accept(this),
          stmt.body.accept(this));
    }

    @Override
    public String visitBlockStmt(Block stmt) {
      return stmt.statements.stream()
          .map(s -> s.accept(this))
          .map(s -> "\t" + s)
          .collect(Collectors.joining("\n"));
    }

    @Override
    public String visitClassStmt(Class stmt) {
      if (stmt.superclass == null && stmt.traits.isEmpty()) {
        return String.format("(class %s\n%s)",
            stmt.name.lexeme,
            stmt.methods.stream()
                .map(s -> s.accept(this))
                .map(s -> "\t" + s)
                .collect(Collectors.joining("\n")));
      } else {
        return String.format("(class %s(%s)\n%s)",
            stmt.name.lexeme,
            (stmt.superclass == null ? stmt.traits.stream()
                : Stream.concat(Stream.of(stmt.superclass), stmt.traits.stream()))
                .map(e -> e.accept(this))
                .collect(Collectors.joining(" ")),
            stmt.methods.stream()
                .map(s -> s.accept(this))
                .map(s -> "\t" + s)
                .collect(Collectors.joining("\n")));
      }
    }

    @Override
    public String visitTraitStmt(Trait stmt) {
      return String.format("(trait %s\n%s",
          stmt.name.lexeme,
          stmt.methods.stream()
              .map(s -> s.accept(this))
              .map(s -> "\t" + s)
              .collect(Collectors.joining("\n")));
    }
  }
}
