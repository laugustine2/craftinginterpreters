Chapter 25 Challenges
=====================

# 1. Change `clox` to only wrap functions in ObjClosures that ned upvalues. How does the code complexity and performance compare to always wrapping functions?

* [Implementation](./upvalue-only-closure/)
* [No closures](./upvalue-only-closure/fib.clox)
```
(before)
Benchmark 1: ./a.out fib.clox
  Time (mean ± σ):     283.0 ms ±   6.1 ms    [User: 282.0 ms, System: 0.9 ms]
  Range (min … max):   273.4 ms … 290.9 ms    10 runs
(after)
Benchmark 1: ./a.out fib.clox
  Time (mean ± σ):     279.3 ms ±   5.7 ms    [User: 278.1 ms, System: 0.9 ms]
  Range (min … max):   271.9 ms … 287.4 ms    10 runs
```
* [With closures](./upvalue-only-closure/closure.clox)
```
(before)
Benchmark 1: ./a.out closure.clox
  Time (mean ± σ):     249.1 ms ±   2.2 ms    [User: 233.9 ms, System: 15.0 ms]
  Range (min … max):   245.7 ms … 252.5 ms    11 runs
(after)
Benchmark 1: ./a.out closure.clox
  Time (mean ± σ):     169.7 ms ±   4.1 ms    [User: 169.5 ms, System: 0.5 ms]
  Range (min … max):   163.4 ms … 175.4 ms    17 runs
```
* Benchmark showed a slight improvement without closures (~1.5%) and a significant improvement with closures (~32%). Both benchmarks saw an improvement and the added code complexity was not significant.

# 2. Change the implementation to create a new variable for each loop iteration.

* [Implementation](./loop-iteration-variable/)

# 3. Using closures, write a `Lox` program that model two-dimensional vector "objects". It should (1) define a "constructor" to create a new vector with the given x and y coordinates, (2) provide "methods" to access the x and y coordinates of values returned from that constructor, (3) define and addition "method" that adds two vectors and produces a third.

