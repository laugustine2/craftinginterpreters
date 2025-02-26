Chapter 28 Challenges
=====================

# 1. Implement a faster lookup to find a class's `init()` method. Write a benchmark to measure the performance difference.

* [Implementation](./fast-init-lookup/)
* Benchmark ran using `hyperfine` (`hyperfine './a.out test.clox' --warmup=10`)
```
(before)
  Time (mean ± σ):     624.2 ms ±  30.9 ms    [User: 614.5 ms, System: 2.5 ms]
  Range (min … max):   583.1 ms … 681.3 ms    10 runs

(after)
  Time (mean ± σ):     574.5 ms ±  33.3 ms    [User: 563.4 ms, System: 3.5 ms]
  Range (min … max):   537.8 ms … 639.2 ms    10 runs
```

# 2. Most of the time a callsite ends up calling the exact same method on exact same class. How do advanced language implementation optimize based on this observation?

Caching?

# 3. When interpreting an `OP_INVOKE` instruction, the VM first lookup fields that could shadow method before it looks for a method. This is because fields and methods are accessed using the same syntax and fields shadow methods. Was this the right choice?

I think it's the right choice since it follows the precedent of many other languages.  
The optimization at the end of this chapter showed a significant performance gains. I'd have to measure the improvement to determine if a different syntax would be worth it.
