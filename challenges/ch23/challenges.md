Chapter 23 Challenges
=====================

# 1. Add `switch` statement to `clox`.

```
switchStmt     → "switch" "(" expression ")"
                 "{" switchCase* defaultCase? "}" ;
switchCase     → "case" expression ":" statement* ;
defaultCase    → "default" ":" statement* ;
```

* [Implementation](./switch-statement/)

# 2. Add `continue` statement to `clox`.

```
continueStmt   → "continue" ";" ;
```

* [Implementation](./continue-statement/)

# 3. Invent a useful novel control flow feature for Lox.

* `while`/`do until` loop  with a initializer

```
givenWhile      → ( "given" varDecl varDecl* )? "while" "(" expression ")" statement ;
givenDoUntil    → ( "given" varDecl varDecl* )? "do" statement "until" "(" expression ")" ;
```

```
given
  var i = 0;
  var j = 10;
while (i > j) {
  i = i + 1;
  j = j - 1;
  print(i + ", " + j);
}
```

```
given
  var i = 0;
  var j = 10
do {
  i = i + 1;
  j = j - 1;
  print(i + ", " + j);
}
until (i > j)
```
