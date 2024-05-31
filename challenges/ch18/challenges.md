Chapter 18 Challenges
=====================

# 1. Which other instructions can be eliminated and how would the compiler cope with their absence?

* `OP_SUBTRACT` - negate the 2nd argument, then add
* `OP_LESS`/`OP_GREATER` - call `OP_GREATER`/`OP_LESS`, then logical negation of result

# 2. What instructions would you define to speed up the kind of user code we added support for in this chapter?

* `OP_ADD_ONE`/`OP_MINUS_ONE` - increment/decrement a number by 1
