Chapter 15 Challenges
=====================

# 1. What bytecode instruction sequences would you generate for the following expression

  * `1 * 2 + 3`
  ```py
  0000 123 OP_CONSTANT 0 '1'
  0002   | OP_CONSTANT 1 '2'
  0004   | OP_MULTIPLY
  0005   | OP_CONSTANT 1 '3'
  0007   | OP_ADD
  ```
  * `1 + 2 * 3`
  ```py
  0000 123 OP_CONSTANT 0 '1'
  0002   | OP_CONSTANT 1 '2'
  0004   | OP_CONSTANT 2 '3'
  0006   | OP_MULTIPLY
  0007   | OP_ADD
  ```
  * `3 - 2 - 1`
  ```py
  0000 123 OP_CONSTANT 0 '3'
  0002   | OP_CONSTANT 1 '2'
  0004   | OP_SUBTRACT
  0005   | OP_CONSTANT 1 '1'
  0007   | OP_SUBTRACT
  ```
  * `1 + 2 * 3 - 4 / -5`
  ```py
  0000 123 OP_CONSTANT 0 '1'
  0002   | OP_CONSTANT 1 '2'
  0004   | OP_CONSTANT 2 '3'
  0006   | OP_MULTIPLY
  0007   | OP_ADD
  0008   | OP_CONSTANT 1 '4'
  0010   | OP_CONSTANT 2 '5'
  0012   | OP_NEGATE
  0013   | OP_DIVIDE
  0014   | OP_SUBTRACT
  ```

# 2. First without using `OP_NEGATE` then without using `OP_SUBTRACT`, show the bytecode instruction sequence you would generated for `4 - 3 * -2`.

  * without `OP_NEGATE`
  ```py
  0000 123 OP_CONSTANT 0 '4'
  0002   | OP_CONSTANT 1 '3'
  0004   | OP_CONSTANT 2 '0'
  0006   | OP_CONSTANT 3 '2'
  0008   | OP_SUBTRACT
  0009   | OP_MULTIPLY
  0010   | OP_SUBTRACT
  ```
  * without `OP_SUBTRACT`
  ```py
  0000 123 OP_CONSTANT 0 '4'
  0002   | OP_CONSTANT 1 '3'
  0004   | OP_CONSTANT 2 '2'
  0006   | OP_NEGATE
  0007   | OP_MULTIPLY
  0008   | OP_NEGATE
  0009   | OP_ADD
  ```

  Both cases increases the size of the bytecode, moreso in the case without `OP_NEGATE` which additionally increase the max stack depth. The additional overhead of supporting these instructions seem minor in comparison.

# 3. Dynamically grow the VM's stack as needed instead of using a fixed sized. What are the costs and benefits of doing so?

  * [Implementation](./dynamic-growth-stack)

    A dynamically growing stack allows arbitrary stack depth which supports a wider range of behaviors. The downside is an added conditional check when pushing values to the stack.

# 4. For `OP_NEGATE`, negate the value in place on the stack, leaving `stackTop` alone, and measure any performance difference. Are there other instructions with similar optimization?

  * [Implementation](./negate-stack-in-place)

    The hand-generated bytecode was updated to negate 1 million times and `hyperfine` was used to benchmark (the benchmark includes time spent generating the bytecode).
    ```
    Benchmark 1: ./a.out
    Time (mean ± σ):      12.3 ms ±   0.4 ms    [User: 10.0 ms, System: 2.4 ms]
    Range (min … max):    11.7 ms …  14.5 ms    186 runs
    ```
    ```
    Benchmark 1: ./a.out
    Time (mean ± σ):       7.6 ms ±   0.4 ms    [User: 6.4 ms, System: 1.8 ms]
    Range (min … max):     7.0 ms …  10.4 ms    288 runs
    ```
    The performance improvement measured above appears to be significant. A similar approach may be utilized, in part, for the bytecode instruction sequences of binary operators.