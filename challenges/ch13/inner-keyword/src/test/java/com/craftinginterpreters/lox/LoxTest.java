package com.craftinginterpreters.lox;

import org.junit.jupiter.api.Test;

class LoxTest {

  @Test
  void testInner() throws Exception {
    var file = LoxTest.class.getResource("/superclass-inner.lox");
    assert file != null;
    Lox.runFile(file.getPath());
  }
}