Chapter 14 Challenges
=====================

# 1. Devise an encoding that compresses the line information for a series of instructions on the same line.

* [Implementation](./encoded-line-info)

# 2. Define a second `OP_CONSTANT_LONG` instruction that stores the operand as a 24-bit number. What sacrifices does multiple instructions that perform the same operation but with operands of different sizes force on us?

* [Implementation](./constant-long-opcode)

# 3. Find a couple of open source implementations of `malloc`/`realloc`/`free` and explain how they work. Implement `reallocate()` without calling `realloc()`, `malloc()`, or `free()`. 

