Chapter 13 Challenges
=====================

# 1. How would you support reusing/sharing capabilities across classes? (e.g. mixins, traits, multiple inheritance, virtual inheritance, extension methods, etc.)

Traits in the following form

```
trait Foo {
  bar() {
  }
}

class Baz has Foo {
}

Baz().bar()
```

* [Implementation](./multiple-inheritance)

# 2. Replace 'super' keyword and mechanism with opposite 'inner' keyword and mechanism.

* [Implementation](./inner-keyword)

# 3. Implement a feature that is missing from Lox.

* [Support for maps](./maps-feature)

```
var map = [:];
print map; // [:]
map.a = 1;
map.b = 2;
map.c = 3;
print map; // [a:1, b:2, c:3]
```
