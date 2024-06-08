Chapter 19 Challenges
=====================

# 1. Use [flexible array members](https://en.wikipedia.org/wiki/Flexible_array_member) to store the `ObjString` and its character array in a single contiguous allocation.

* [Implementation](./objstring-flexible-array)

# 2. Add support "constant strings" which are `ObjSting` that do not own their character array and instead just point back to the original source string.

* [Implementation](./constant-strings)

# 3. What would you have Lox do when a user tries to use `+` with one string operand and the other some other type?

* I'd coerce the other type to a string value before concatenating the two. Lox is a dynamic language and this follows the example set in `groovy`/`js`/`ts`. Static languages such as `Java`/`C#` also supports this behavior.
