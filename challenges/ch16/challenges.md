Chapter 16 Challenges
=====================

# 1. What token types would you define to implement a scanner for string interprolation? What sequence would you emit for the `"${drink} will be ready in ${steep + cool} minutes."`? What tokens would you emit for `"Nested ${"interpolation?! Are you ${"mad?!"}"}"`?

When lexing a string, I'd end the string token when `${` is encountered and create a token that indicates string should be joined. The "interpolation level" must be tracked and `}` token will end the interpolated part of the string and read the remainder, emiting another token to indicate those two strings should be join

* `"${drink} will be ready in ${steep + cool} minutes."`
```
STRING()
STR_CONCAT
IDENTIFIER(drink)
STR_CONCAT
STRING( will be read in )
STR_CONCAT
IDENTIFIER(steep)
PLUS
IDENTIFIER(cool)
STR_CONCAT
STRING( minutes.)
```
* `"Nested ${"interpolation?! Are you ${"mad?!"}"}"`
```
STRING(Nested )
STR_CONCAT
STRING(interpolation?! Are you )
STR_CONCAT
STRING(mad?!)
STR_CONCAT
STRING()
STR_CONCAT
STRING()
```

# 2. How does Java and C# avoid issue of `>>` being lexed as a single right shift token instead of two `>` tokens in the case of `vector<vector<string>> nestedVectors;`?

* ¯\\\_(ツ)\_/¯

# 3. Name a few contextual keywards from other languages, and the context where they are meaningful. What are the pros and cons of having contextual keywords? How would you implement them in your language's front end?

* Example contextual keywords in Java are `sealed`, `permits`, `non-sealed`. These are used to support classes/interfaces that can only be extended/implmented by classes/interfaces permitted to do so.
    ```java
    sealed interface Foo permits Bar, Baz {
    }
    record Bar() implements Foo {
    }
    record Baz() implements Foo {
    }
    ```
* One benefit is the language can be extended in a backwards-compatible manner if the context for which the keyword is valid did not exist in the previous version. Downside here is code the uses the reserved identifier outside of the context may confuse readers.
* Contextual keywords will be tokenize as regular identifiers and the parser will handle as a keyword when parsing the new context
