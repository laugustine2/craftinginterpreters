package com.craftinginterpreters.lox;

import java.util.Map;

public class LoxTrait {

  final String name;
  private final Map<String, LoxFunction> methods;

  LoxTrait(String name, Map<String, LoxFunction> methods) {
    this.name = name;
    this.methods = methods;
  }

  LoxFunction findMethod(String name) {
    return methods.get(name);
  }
}
