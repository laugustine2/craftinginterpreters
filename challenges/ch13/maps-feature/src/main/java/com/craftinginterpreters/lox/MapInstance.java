package com.craftinginterpreters.lox;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapInstance {

  private final Map<Object, Object> entries;

  public MapInstance() {
    entries = new HashMap<>();
  }

  Object get(Object key) {
    return entries.get(key);
  }

  void put(Object key, Object value) {
    entries.put(key, value);
  }

  @Override
  public String toString() {
    if (entries.isEmpty()) {
      return "[:]";
    }
    return entries.entrySet().stream()
        .map(e -> "%s:%s".formatted(e.getKey(), e.getValue()))
        .collect(Collectors.joining(", ", "[", "]"));
  }
}
