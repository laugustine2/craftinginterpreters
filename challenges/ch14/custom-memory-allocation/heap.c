#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "heap.h"

const int MAX_HEAP_SIZE_BYTES = 1 << 20;

// Header for each heap chunk
typedef struct header {
  uint32_t size; // size of chunk (excluding header)
  struct header *next;
} Header;

// head of the heap
static Header base;
// start of free list
static Header *freeList = NULL;

bool isAdjacent(Header *first, Header *second) {
  return ((char *)first + sizeof(Header) + first->size) == (char *)second;
}

void initializeHeap() {
  base.size = 0;
  base.next = malloc(sizeof(char) * MAX_HEAP_SIZE_BYTES);
  base.next->size = MAX_HEAP_SIZE_BYTES - sizeof(Header);
  base.next->next = &base;
  freeList = &base;
}

void showAvailableHeap() {
  if (freeList == NULL) {
    initializeHeap();
  }
  Header *current = &base;
  do {
    printf("{start: %p size: %d} -> ", current, current->size);
    current = current->next;
  } while (current != &base);
  printf("NULL\n");
}

void *customMalloc(uint32_t size) {
  uint32_t requiredSize = size + sizeof(Header);
  if (freeList == NULL) {
    initializeHeap();
  }
  Header *previous = freeList;
  for (Header *current = previous->next;;
       previous = current, current = current->next) {
    if (current->size >= size) {          // big enough
      if (current->size < requiredSize) { // "exact" size
        previous->next = current->next;
      } else { // allocate from tail
        current->size -= requiredSize;
        current = (Header *)((char *)current + sizeof(Header) + current->size);
        current->size = size;
      }
      freeList = previous;
      return current + 1;
    }
    if (current == freeList) {
      break;
    }
  }
  return NULL;
}

void *customRealloc(void *data, uint32_t newSize) {
  if (data == NULL) {
    return customMalloc(newSize);
  }
  Header *header = (Header *)data - 1;
  if (newSize == 0) { // free all
    customFree(data);
    return NULL;
  } else if (header->size == newSize) { // same size
    return data;
  } else if (header->size > newSize) { // shrink
    uint32_t remainder = header->size - newSize;
    if (remainder <= sizeof(Header)) { // too small to resize
      return data;
    } else { // resize and free excess
      Header *extra = (Header *)(data + newSize);
      extra->size = remainder - sizeof(Header);
      extra->next = header->next;
      header->size = newSize;
      header->next = extra;
      customFree(extra + 1);
      return data;
    }
  } else { // grow
    // just request a new block and copy the data
    void *newData = customMalloc(newSize);
    memcpy(newData, data, header->size);
    customFree(data);
    return newData;
  }
}

void customFree(void *data) {
  Header *header = (Header *)data - 1, *destination;
  for (destination = freeList;
       header <= destination || header >= destination->next;
       destination = destination->next) {
    if (destination >= destination->next &&
        (header > destination || header < destination->next)) {
      break;
    }
  }

  if (isAdjacent(header, destination->next)) {
    header->size += destination->next->size + sizeof(Header);
    header->next = destination->next->next;
    ;
  } else {
    header->next = destination->next;
  }

  if (isAdjacent(destination, header)) {
    destination->size += header->size + sizeof(Header);
    destination->next = header->next;
  } else {
    destination->next = header;
  }

  freeList = destination;
}

void testMalloc() {
  showAvailableHeap();

  void *a = customMalloc(349509);
  if (a == NULL) {
    printf("cannot allocate memory\n");
    exit(EXIT_FAILURE);
  }
  void *b = customMalloc(349509);
  if (b == NULL) {
    printf("cannot allocate memory\n");
    exit(EXIT_FAILURE);
  }
  showAvailableHeap();

  void *c = customMalloc(349509);
  if (c == NULL) {
    printf("cannot allocate memory\n");
    exit(EXIT_FAILURE);
  }
  showAvailableHeap();

  customFree(a);
  showAvailableHeap();

  customFree(c);
  showAvailableHeap();

  customFree(b);
  showAvailableHeap();
}

void testRealloc() {
  showAvailableHeap();
  void *a = customRealloc(NULL, 349509);
  if (a == NULL) {
    printf("cannot allocate memory\n");
    exit(EXIT_FAILURE);
  }

  void *b = customRealloc(NULL, 349509);
  if (b == NULL) {
    printf("cannot allocate memory\n");
    exit(EXIT_FAILURE);
  }
  showAvailableHeap();

  a = customRealloc(a, 8);
  if (a == NULL) {
    printf("cannot allocate memory\n");
    exit(EXIT_FAILURE);
  }
  showAvailableHeap();

  a = customRealloc(a, 349000);
  if (a == NULL) {
    printf("cannot allocate memory\n");
    exit(EXIT_FAILURE);
  }
  showAvailableHeap();

  a = customRealloc(a, 0);
  if (a != NULL) {
    printf("did not free memory\n");
    exit(EXIT_FAILURE);
  }
  showAvailableHeap();

  b = customRealloc(b, 0);
  if (b != NULL) {
    printf("did not free memory\n");
    exit(EXIT_FAILURE);
  }
  showAvailableHeap();
}

// int main(int argc, char **argv) {
//   testRealloc();
//   return 0;
// }
