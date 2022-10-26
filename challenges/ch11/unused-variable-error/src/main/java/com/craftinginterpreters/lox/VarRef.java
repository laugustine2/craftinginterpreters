package com.craftinginterpreters.lox;

class VarRef {

  final Token name;
  boolean referenced;
  boolean initialized;

  VarRef(Token name, boolean referenced, boolean initialized) {
    this.name = name;
    this.referenced = referenced;
    this.initialized = initialized;
  }
}
