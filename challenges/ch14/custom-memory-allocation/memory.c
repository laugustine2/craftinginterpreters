#include <stdlib.h>

#include "memory.h"
#include "heap.h"

void* reallocate(void *pointer, size_t oldSize, size_t newSize) {
  if (newSize == 0) {
    customFree(pointer);
    return NULL;
  }

  void* result = customRealloc(pointer, newSize);
  if (result == NULL) exit(1);
  return result;
}
