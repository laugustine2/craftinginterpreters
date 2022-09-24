Chapter 9 Challenges
====================

# 1. Show how conditional execution can be implemented in terms of first-class functions and dynamic dispatch.

```
class True {
  fun ifThen(thenBranch) {
    thenBranch();
  }

  func ifThenElse(thenBranch, elseBranch) {
    thenBranch();
  }
}
class False {
  fun ifThen(thenBranch) {
  }

  func ifThenElse(thenBranch, elseBranch) {
    elseBranch();
  }
}
```

# 2. What optimization is necessary to implement looping in terms of first-class functions and dynamic dispath.

* Tail-call optimization is needed to avoid stack overflows

# 3.Add support for break statements. 

* [Implementation](./break-statements)
