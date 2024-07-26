Chapter 22 Challenges
=====================

# 1. Come up with a more efficient approach to resolving a reference to a variable than a linear array scan. Is the additional complexity worth it?

* store locals/globals in a hash table using the token as key and the offset as the value
* I don't believe the complexity cost will be worth it as the size of the array would not be significant for the majority source inputs.

# 2. How do other languages handle the following code? What would you do if it was your language? Why?

```java
var a = a;
```
* `Java` does not allow this
* I'd follow suit with `Java` and disallow this as it adds confusion.

# 3. Pick a keyword for a single-assignment variable form and add to Lox. Justify your choice, then implement it. An atttmpt to assign to a variable declared using the new keyword should cause a compile error.

* Added new keyword `val` as it seems as an appropriate complement to `var`
```
varDecl        → "var" IDENTIFIER ( "=" expression )? ";"
               | "val" IDENTIFIER "=" expression ";" ;
```
* [Implementation](./single-assignment-variable)

# 4. Extend clox to allow more than 256 local variable to be in scope at a time.

* [Implementation](./more-local-variables)
