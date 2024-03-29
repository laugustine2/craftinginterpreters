Chapter 12 Challenges
=====================

# 1. Add support for "static” methods that can be called directly on the class object itself.

* [Implementation](./static-methods)

# 2. Extend Lox to support getter methods. These are declared without a parameter list. The body of the getter is executed when a property with that name is accessed.

* [Implementation](./getter-methods)

# 3. What are the trade-offs between approaches to accessing an object’s fields from outside of its own methods and why might a language prefer one or the other?

Disallowing access to an object's fields reduces access to the internal state of the object to that of it's API (public methods). On the other hand, allowing access to the internal state is simpler with less boiler plate.
