Chapter 10 Challenges
====================

# 1. How does SmallTalk avoid runtime arity cheks?

* Method names in SmallTalk specifies the number of parameters implicitly

# 2. Add anonymous function syntax to Lox.

* [Implementation](./anonymous-function)

# 3. Is this program valid? Are a function’s parameters in the same scope as its local variables? What about other languages you are familiar with? What do you think a language should do?

```
fun scope(a) {
  var a = "local";
}
```

* Yes, function parameters are in the same scope as it's local variables and lox allows re-declaring a variable
* Go/Java disallows re-declaring a variable in the same scope
* Python/Ruby/Crystal only uses assignment operator and re-assigning variables is allowed
* Re-assigning an exisitng variable should be allowed, re-declaring should be disallowed
