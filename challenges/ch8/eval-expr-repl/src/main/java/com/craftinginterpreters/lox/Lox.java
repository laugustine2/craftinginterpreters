package com.craftinginterpreters.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Lox {

  private static final Interpreter interpreter = new Interpreter();
  private static boolean hadError = false;
  private static boolean hadRuntimeError = false;

  public static void main(String[] args) throws IOException {
    if (args.length > 1) {
      System.out.println("Usage jlox [script]");
      System.exit(64);
    } else if (args.length == 1) {
      runFile(args[0]);
    } else {
      runPrompt();
    }
  }

  private static void runFile(String path) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    run(new String(bytes, Charset.defaultCharset()), false);

    // Indicate an error in the exit code.
    if (hadError) {
      System.exit(65);
    }
    if (hadRuntimeError) {
      System.exit(70);
    }
  }

  private static void runPrompt() throws IOException {
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);
    for (; ; ) {
      System.out.print("> ");
      String line = reader.readLine();
      if (line == null) {
        break;
      }
      run(line, true);
      hadError = false;
    }
  }

  private static void run(String source, boolean repl) {
    Scanner scanner = new Scanner(source);
    List<Token> tokens = scanner.scanTokens();
    // wrap expression with print <expr> ;
    if (repl && tokens.size() > 1 && tokens.get(tokens.size() - 2).type != TokenType.SEMICOLON) {
      tokens = new ArrayList<>(tokens);
      tokens.add(0, new Token(TokenType.PRINT, "", null, 1));
      tokens.add(tokens.size() - 1, new Token(TokenType.SEMICOLON, ":", null, 1));
    }
    Parser parser = new Parser(tokens);
    List<Stmt> statements = parser.parse();

    // Stop if there was a syntax error
    if (hadError) {
      return;
    }

    interpreter.interpret(statements);
  }

  static void error(int line, String message) {
    report(line, "", message);
  }

  static void report(int line, String where, String message) {
    System.err.println("[line " + line + "] Error" + where + ": " + message);
    hadError = true;
  }

  static void error(Token token, String message) {
    if (token.type == TokenType.EOF) {
      report(token.line, " at end", message);
    } else {
      report(token.line, " at '" + token.lexeme + "'", message);
    }
  }

  static void runtimeError(RuntimeError error) {
    System.err.println(error.getMessage() + "\n[line " + error.token.line + "]");
    hadRuntimeError = true;
  }
}
