package com.craftinginterpreters.lox;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LoxClass implements LoxCallable {

  final String name;
  final LoxClass superclass;
  final List<LoxTrait> traits;
  private final Map<String, LoxFunction> methods;

  LoxClass(String name, LoxClass superclass, List<LoxTrait> traits,
      Map<String, LoxFunction> methods) {
    this.name = name;
    this.superclass = superclass;
    this.traits = traits;
    this.methods = methods;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public int arity() {
    LoxFunction initializer = findMethod("init");
    if (initializer == null) {
      return 0;
    }
    return initializer.arity();
  }

  @Override
  public Object call(Interpreter interpreter, List<Object> arguments) {
    LoxInstance instance = new LoxInstance(this);
    LoxFunction initializer = findMethod("init");
    if (initializer != null) {
      initializer.bind(instance).call(interpreter, arguments);
    }
    return instance;
  }

  LoxFunction findMethod(String name) {
    if (methods.containsKey(name)) {
      return methods.get(name);
    }
    if (!traits.isEmpty()) {
      return traits.stream()
          .map(t -> t.findMethod(name))
          .filter(Objects::nonNull)
          .findFirst()
          .orElse(null);
    }
    if (superclass != null) {
      return superclass.findMethod(name);
    }
    return null;
  }
}
