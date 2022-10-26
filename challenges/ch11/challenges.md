Chapter 11 Challenges
=====================

# 1. Why is it safe to eagerly define the variable bound to a function’s name when other variables must wait until after they are initialized before they can be used?

Only the part of the function declaration that assigns a name and delcares it's parameters are need to reference a function. This allows recursive calls within the function's block.

# 2. How do other languages you know handle local variables that refer to the same name in their initializer?

This is not allowed in Go or Python unless a variable with the same name exists in an outer block.  
This is also not allowed in Java but variable shadowing is also not permitted.  
C initializes the declared variable to some value before the initializer.

# 3. Extend the resolver to report an error if a local variable is never used.

* [Implementation](./unused-variable-error)

# 4. Extend the resolver to associate a unique index for each local variable declared in a scope and use that to quickly access a variable by its index instead of using a map.

* [Implementation](./variable-array-index)
