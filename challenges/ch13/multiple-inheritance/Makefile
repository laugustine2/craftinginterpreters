# Generate AST classes
generate-ast:
	javac src/main/java/com/craftinginterpreters/lox/tool/GenerateAst.java
	java -cp src/main/java com.craftinginterpreters.lox.tool.GenerateAst "src/main/java/com/craftinginterpreters/lox/"
	rm src/main/java/com/craftinginterpreters/lox/tool/GenerateAst.class
# Compile
build: src/main/java/com/craftinginterpreters/lox/Expr.java
	mvn compile
# Run interpreter
run: target
	mvn exec:java -Dexec.mainClass=com.craftinginterpreters.lox.Lox
# Tests
test-superclass:
	mvn exec:java -Dexec.mainClass=com.craftinginterpreters.lox.Lox -Dexec.args="src/main/resources/superclass.lox"
test-superclass-method:
	mvn exec:java -Dexec.mainClass=com.craftinginterpreters.lox.Lox -Dexec.args="src/main/resources/superclass-method.lox"
# Cleanup
clean:
	mvn clean
# Removed generated AST classes
clean-generated: src/main/java/com/craftinginterpreters/lox/Expr.java
	rm src/main/java/com/craftinginterpreters/lox/Expr.java
# Non-file targets
.PHONY: generate-ast build test-fib test-counter clean clean-generated
