Chapter 17 Challenges
=====================

# 1. Write a trace for `(-1 + 2) * 3 - -4`. Show how the parsing functions are called including the order, which calls which, and the arguments passed to them.

* parsePrecendence
  * grouping `(`
    * unary `-`
      * number `1`
    * binary `+`
      * number `2`
  * binary `*`
    * number `3`
  * binary `-`
    * unary `-`
      * number `4`

# 2. In Lox, what tokens, besides `TOKEN_MINUS`, can be used in both prefix and infix positions? What about in C or in another language?

* `Lox`
  * `TOKEN_BANG` can be a prefix operator as with logical negation or part of the infix operator for inequal comparison
* `C`
  * `+`, similar to `-`, can be both a prefix operator as well as an infix operator
  * `*` can be a pointer deference when used as a prefix operator and arithmetic multiplication when used as an infix operator

# 3. Show how you would support for the condtional/ternary operator to the compiler.

* Create a parse rule for the `?` token with an infix operator for handling conditional expressions

```c
ParseRule rules[] = {
    ...
    [TOKEN_QUESTION_MARK] = {NULL, conditional, PREC_CONDITIONAL}
    ...
}

static void conditional() {
  TokenType operator = parser.previous.type;
  expression();
  consume(TOKEN_COLON, "Expect ':' after expression.");
  expression();
  // how to evaluate only one single branch?
  emitByte(OP_CONDITIONAL);
}
```
