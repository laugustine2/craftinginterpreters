Chapter 21 Challenges
=====================

# 1. Add an optimization that avoids adding a global variable’s name to the constant table every time an identifier is encountered. How does this affect the performance of the compiler compared to the runtime? Is this the right trade-off?

* [Implementation](./dedup-constant-table)

# 2. Can you come up with a more efficient way to store and access global variables without changing the semantics?

# 3. Should mistakes like unused functions and accessing variables that are never defined be reported as compile errors when running from a script? What do other scripting languages you know do?
