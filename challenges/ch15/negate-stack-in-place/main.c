#include "chunk.h"
#include "common.h"
#include "debug.h"
#include "vm.h"

int main(int argc, const char *argv[]) {
  initVM();

  Chunk chunk;
  initChunk(&chunk);

  int constant = addConstant(&chunk, 1.2);
  writeChunk(&chunk, OP_CONSTANT, 123);
  writeChunk(&chunk, constant, 123);
  for (int i = 0; i < 1000000; i++) {
    writeChunk(&chunk, OP_NEGATE, 123);
  }
  writeChunk(&chunk, OP_RETURN, 123);

  interpret(&chunk);
  freeVM();
  freeChunk(&chunk);
  return 0;
}
