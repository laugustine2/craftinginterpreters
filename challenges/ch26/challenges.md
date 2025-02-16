Chapter 26 Challenges
=====================

# 1. How much memory does the Obj header struct take up? Can you come up with something more compact? Is there a runtime cost of doing so?

* `sizeof(Obj)` is 16 bytes
* the `next` pointer is 8 bytes and space for the `type` enum along with the `isMarked` boolean is aligned to 8 bytes
* to be more compact than 16 bytes requires packing the `type` and `isMarked` in with the 8 bytes required for the `next` pointer (is this possible?)
* the above approach would require refactoring the code that accesses the struct fields to use utility methods to unpack the field

# 2. When the sweep phase traverses a live object, it clears the isMarked field to prepare it for the next collection cycle. Can you come up with a more efficient approach?

* instead of resetting each value, flip the meaning of the `true`/`false` value after each iteration

# 3. Explore garbage collection algorithms by replacing or augmenting the current collector with another one (e.g.: reference counting, Cheney’s algorithm, or the Lisp 2 mark-compact algorithm)
