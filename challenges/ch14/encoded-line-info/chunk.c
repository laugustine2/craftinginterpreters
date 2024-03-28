#include <stdlib.h>

#include "chunk.h"
#include "memory.h"

void initLineInfo(LineInfoArray* array) {
  array->count = 0;
  array->capacity = 0;
  array->lines = NULL;
  array->lineCounts = NULL;
}

void initChunk(Chunk* chunk) {
  chunk->count = 0;
  chunk->capacity = 0;
  chunk->code = NULL;
  initLineInfo(&chunk->lineInfo);
  initValueArray(&chunk->constants);
}

void freeLineInfo(LineInfoArray* array) {
  FREE_ARRAY(int, array->lines, array->capacity);
  FREE_ARRAY(int, array->lineCounts, array->capacity);
  initLineInfo(array);
}

void freeChunk(Chunk* chunk) {
  FREE_ARRAY(uint8_t, chunk->code, chunk->capacity);
  freeLineInfo(&chunk->lineInfo);
  freeValueArray(&chunk->constants);
  initChunk(chunk);
}

void addLine(LineInfoArray* array, int line) {
  if (array->count > 0 && array->lines[array->count - 1] == line) {
    array->lineCounts[array->count - 1]++;
    return;
  }
  if (array->capacity < array->count + 1) {
    int oldCapacity = array->capacity;
    array->capacity = GROW_CAPACITY(oldCapacity);
    array->lines = GROW_ARRAY(int, array->lines, oldCapacity, array->capacity);
    array->lineCounts = GROW_ARRAY(int, array->lineCounts, oldCapacity, array->capacity);
  }
  array->lines[array->count] = line;
  array->lineCounts[array->count] = 1;
  array->count++;
}

void writeChunk(Chunk* chunk, uint8_t byte, int line) {
  if (chunk->capacity < chunk->count + 1) {
    int oldCapacity = chunk->capacity;
    chunk->capacity = GROW_CAPACITY(oldCapacity);
    chunk->code = GROW_ARRAY(uint8_t, chunk->code, oldCapacity, chunk->capacity);
  }
  chunk->code[chunk->count] = byte;
  addLine(&chunk->lineInfo, line);
  chunk->count++;
}

int addConstant(Chunk* chunk, Value value) {
  writeValueArray(&chunk->constants, value);
  return chunk->constants.count - 1;
}

int getLine(Chunk* chunk, int offset) {
  int curIndex = 0;
  for (int i = 0;i < chunk->lineInfo.count;i++) {
    if (offset >= curIndex && offset < curIndex + chunk->lineInfo.lineCounts[i]) {
      return chunk->lineInfo.lines[i];
    }
    curIndex += chunk->lineInfo.lineCounts[i];
  }
  return -1;
}
