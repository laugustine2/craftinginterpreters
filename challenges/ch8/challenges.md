Chapter 8 Challenges
====================

# 1. Add support to the REPL to let users type in both statements and expressions. If they enter a statement, execute it. If they enter an expression, evaluate it and display the result value.

* Solution: If list of tokens doesn't end with a 'semicolon' wrap with 'print' and 'semicolon'
* [Implementation](./eval-expr-repl)

# 2. Make it a runtime error to access a variable that has not been initialized or assigned to.

* [Implementation](./no-init-error)

# 3. What does the following program do? What did you expect it to do? Is it what you think it should do? What does analogous code in other languages you are familiar with do? What do you think users will expect this to do?

```
var a = 1;
{
  var a = a + 2;
  print a;
}
```

* '3' to be printed.
* Expected '3' to be printed
* This makes sense to me.
* Other languages:
  * Go: print '3'
  * Java: fails with a compilation error
  * C: prints '2'
  * Python: fails with a runtime error
* Go has the best interpretation
