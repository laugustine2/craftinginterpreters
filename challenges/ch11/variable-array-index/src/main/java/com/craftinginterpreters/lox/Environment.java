package com.craftinginterpreters.lox;

import java.util.ArrayList;
import java.util.List;

public class Environment {

  final Environment enclosing;
  private final List<Object> values = new ArrayList<>();

  public Environment() {
    enclosing = null;
  }

  public Environment(Environment enclosing) {
    this.enclosing = enclosing;
  }

  void define(Object value) {
    values.add(value);
  }

  Object getAt(Integer distance, Integer slot) {
    return ancestor(distance).values.get(slot);
  }

  void assignAt(Integer distance, Integer slot, Object value) {
    ancestor(distance).values.set(slot, value);
  }

  Environment ancestor(int distance) {
    Environment environment = this;
    for (int i = 0; i < distance; i++) {
      assert environment != null;
      environment = environment.enclosing;
    }
    return environment;
  }
}
