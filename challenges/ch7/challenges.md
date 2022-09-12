Chapter 6 Challenges
====================

# 1. Would you extend Lox to support comparing other types? If so, which pairs of types do you allow and how do you define their ordering?

# 2. Many languages define + such that if either operand is a string, the other is converted to a string and the results are then concatenated. For example, "scone" + 4 would yield scone4. Extend the code in visitBinaryExpr() to support that.

* [Implementation](./concat-string-any)

# 3. Change the implementation in visitBinaryExpr() to detect and report a runtime error when you divide a number by zero.

* [Implementation](./divide-by-zero)
