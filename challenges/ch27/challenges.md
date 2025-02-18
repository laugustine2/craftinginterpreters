Chapter 27 Challenges
=====================

# 1. How do other dynamic languages handle missing fields? Implement a solution for Lox.

`python` raises `AttributeError` and `groovy` throws `MissingPropertyException` which can be handled via a try block. Additionally, `python` provides `hasattr` and `groovy` provides `hasProperty` to check existence.  
Adding exception handling to `Lox` would be some effort. However, we can provide a `hasField` method.

* [Implementation](./has-field/)

# 2. Should a user program be able use a string value as a field name? Devise and implement a language feature that enables this.

Yes, user programs should be able to get/set fields using a string value

* [Implementation](./get-set-field/)

# 3. How do other languages handle removing a field from an instance? Choose and implement a strategy for Lox

`python` provides `delattr` method for removing a named attribute from an object. For `Lox`, we can provide a `deleteField` method.

* [Implementation](./delete-field/)

# 4. How do sophisticated dynamic language implementations optimize field access?
