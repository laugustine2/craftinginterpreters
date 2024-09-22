Chapter 26 Challenges
=====================

# 1. How much memory does the Obj header struct take up? Can you come up with something more compact? Is there a runtime cost of doing so?

# 2. When the sweep phase traverses a live object, it clears the isMarked field to prepare it for the next collection cycle. Can you come up with a more efficient approach?

# 3. Explore garbage collection algorithms by replacing or augmenting the current collector with another one (e.g.: reference counting, Cheney’s algorithm, or the Lisp 2 mark-compact algorithm)
