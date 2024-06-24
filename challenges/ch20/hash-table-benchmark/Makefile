bench-set:
	gcc bench1.c chunk.c compiler.c debug.c memory.c object.c scanner.c table.c value.c vm.c
	hyperfine ./a.out --warmup=10
# Cleanup
clean: a.out
	rm a.out
# Non-file targets
.PHONY: bench-set clean
