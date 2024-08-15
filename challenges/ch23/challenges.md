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
