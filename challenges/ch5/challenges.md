Chapter 5 Challenges
====================

# 1. Produce a grammar that matches the same language but does not use any of that notational sugar. (Bonus: What kind of expression does this bit of grammar encode?)

Syntatic sugar:
```
expr → expr ( "(" ( expr ( "," expr )* )? ")" | "." IDENTIFIER )+
     | IDENTIFIER
     | NUMBER
```

No sugar:
```
expr → expr foo
expr → IDENTIFIER
expr → NUMBER

foo → "(" bar ")"
foo → "." IDENTIFIER
foo → foo foo

bar → expr baz
bar → ""

baz → "," expr
baz → ""
baz → baz baz
```

This grammar represents field accessor and method calls. e.g.: foo.bar, foo(1), foo(1, 2), foo.bar(), etc.

# 2. The Visitor pattern lets you emulate the functional style in an object-oriented language. Devise a complementary pattern for a functional language. It should let you bundle all of the operations on one type together and let you define new types easily.

# 3. Define a visitor class for our syntax tree classes that takes an expression, converts it to RPN, and returns the resulting string.

* [Implementation](./reverse-polish-notation)
