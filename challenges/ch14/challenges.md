Chapter 14 Challenges
=====================

# 1. Devise an encoding that compresses the line information for a series of instructions on the same line.

* [Implementation](./encoded-line-info)

# 2. Define a second `OP_CONSTANT_LONG` instruction that stores the operand as a 24-bit number. What sacrifices does multiple instructions that perform the same operation but with operands of different sizes force on us?

* [Implementation](./constant-long-opcode)
* Multiple instructions for the same operation means more code to implement and maintain

# 3. Find a couple of open source implementations of `malloc`/`realloc`/`free` and explain how they work. Implement `reallocate()` without calling `realloc()`, `malloc()`, or `free()`. 

* [The C Programming Language](https://www.amazon.com/Programming-Language-2nd-Brian-Kernighan/dp/0131103628) book has an example storage allocator
  * A version of the implementation can be found in [this repo](https://github.com/ohkimur/the-c-programming-language-2nd-edition-solutions/blob/main/chapter_8/exercise_8_06/calloc.c) which contains solutions to exercises from the book
  * The gist is to track a "free list" of available memory sections
  * `malloc` searches the free list for suitable block of memory, splitting if necessary
  * `free`, on the other hand, adds a block of memory to the appropriate location in the free list, merging blocks if necessary
  * A `Header` structure is used represent the memory blocks in free list; it contains the size of the block along with a pointer to the next block
* [Implementation](./custom-memory-allocation)
