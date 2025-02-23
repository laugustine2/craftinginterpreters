Chapter 28 Challenges
=====================

# 1. Implement a faster lookup to find a class's `init()` method. Write a benchmark to measure the performance difference.

# 2. Most of the time a callsite ends up calling the exact same method on exact same class. How do advanced language implementation optimize based on this observation?

# 3. When interpreting an `OP_INVOKE` instruction, the VM first lookup fields that could shadow method before it looks for a method. This is because fields and methods are accessed using the same syntax and fields shadow methods. Was this the right choice?
