Chapter 24 Challenges
=====================

# 1. Store the `ip` field directly in a local variable and mark it `register` to encourage the compiler to keep it in a native CPU register. Write a coule of benchmarks to measure how it affects performance. Is the extra code complexity worth it?

* [Implementation](./ip-register-variable/)
* Benchmark ran using `hyperfine` (`hyperfine './a.out test.clox' --warmup=10`).
```
(before)
  Time (mean ± σ):      3.512 s ±  0.111 s    [User: 3.507 s, System: 0.001 s]
  Range (min … max):    3.304 s …  3.609 s    10 runs

(after)
  Time (mean ± σ):      3.244 s ±  0.152 s    [User: 3.241 s, System: 0.000 s]
  Range (min … max):    3.013 s …  3.475 s    10 runs
```
* Results showed a ~7.5% increase in performance. I'd say this is significant for the small code change

# 2. Add arity checking to native function calls.

* [Implementation](./native-function-arity/)

# 3. Extend the native function system to support reporting runtime errors. How does this capability affect the performance of native calls?

* [Implementation](./native-function-runtime-error/)
* `NativeFn` was updated to return a struct with the result value and error message (should really be a union). The `callValue` function was updated to report a runtime error if the result included an error message. Running a benchmark using the same means as described in `#1` showed ~1% performance penalty which seems insignificant.

# 4. Add some more native functions to do things you find useful and write some programs using those. How do the affect the feel of the language and how practical it is?

* [Implementation](./additional-native-functions/)
* Functions added
  * `readNumber` - reads a number input
  * `readString` - read a string input
  * `random` - generates a randomd number
* Create a number guessing game using `readNumber` and `random`
