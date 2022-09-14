# Generate AST classes
generate-ast:
	javac src/main/java/com/craftinginterpreters/lox/tool/GenerateAst.java
	java -cp src/main/java com.craftinginterpreters.lox.tool.GenerateAst "src/main/java/com/craftinginterpreters/lox/"
	rm src/main/java/com/craftinginterpreters/lox/tool/GenerateAst.class
# Compile
build: src/main/java/com/craftinginterpreters/lox/Expr.java
	mvn compile
# Print AST
run: target
	mvn exec:java -Dexec.mainClass=com.craftinginterpreters.lox.Lox
# Cleanup
clean:
	mvn clean
# Removed generated AST classes
clean-generated: src/main/java/com/craftinginterpreters/lox/Expr.java
	rm src/main/java/com/craftinginterpreters/lox/Expr.java
# Non-file targets
.PHONY: generate-ast build test-ast clean clean-generated
