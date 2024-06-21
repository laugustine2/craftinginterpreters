Chapter 20 Challenges
=====================

# 1. Add support for hash tables with keys of the other primitive types (numbers, booleans, and nil). What kind of complexity would supporting instances of user-defined classes add?

* [Implementation](./hash-key-primitive)
* Supporting user-defined classes requires a way to compute the hash code for instances of arbitrary types. One approach would be to require a `hashCode` method to be provided classes intended to be used as hash keys (similar to `Java`).

# 2. Look up a few hash table implementations, research the choices they made and why they did things that way.

# 3. Write a handful of different benchmark program to validate our hash table implementation. How does the performance vary between them? Why did you choose the specific test cases you chose?
