package com.craftinginterpreters.lox;

import java.util.List;
import java.util.Map;

public class LoxClass implements LoxCallable {

  final String name;
  final LoxClass superclass;
  private final Map<String, LoxFunction> methods;

  LoxClass(String name, LoxClass superclass, Map<String, LoxFunction> methods) {
    this.name = name;
    this.superclass = superclass;
    this.methods = methods;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public int arity() {
    LoxFunction initializer = findMethod(null, "init");
    if (initializer == null) {
      return 0;
    }
    return initializer.arity();
  }

  @Override
  public Object call(Interpreter interpreter, List<Object> arguments) {
    LoxInstance instance = new LoxInstance(this);
    LoxFunction initializer = findMethod(instance, "init");
    if (initializer != null) {
      initializer.call(interpreter, arguments);
    }
    return instance;
  }

  /**
   * @return method implementation from top of hierarchy
   */
  LoxFunction findMethod(LoxInstance instance, String name) {
    // method from current class
    var method = methods.get(name);
    LoxFunction inner = null;
    // search class hierarchy to find method from top most class
    var callee = this;
    while (callee.superclass != null) {
      var parentMethod = callee.superclass.methods.get(name);
      if (parentMethod != null) {
        inner = method;
        method = parentMethod.bind(instance, inner);
      }
      callee = callee.superclass;
    }
    if (method != null) {
      return method.bind(instance, inner);
    }
    return null;
  }
}
