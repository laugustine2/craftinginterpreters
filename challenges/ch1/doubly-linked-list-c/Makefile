# Compile to executable
build: list.h list.c main.c
	gcc list.h list.c main.c
# Run executable
run: a.out
	./a.out
# Delete executable
clean: a.out
	rm a.out list.h.gch
# Mark all targets as phony
.PHONY: build run clean
