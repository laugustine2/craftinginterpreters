#include "object.h"
#include "table.h"
#include "value.h"
#include <stdio.h>

int main(int argc, char **argv) {
  Table table;
  initTable(&table);

  for (int i = 0; i < 1000000; i++) {
    char s[8];
    int length = i > 99999  ? 6
                 : i > 9999 ? 5
                 : i > 999  ? 4
                 : i > 99   ? 3
                 : i > 9    ? 2
                            : 1;
    snprintf(s, 8, "%d", i);
    ObjString *key = copyString(s, length);
    tableSet(&table, key, NUMBER_VAL(i));
  }

//   ObjString *key = copyString("1000", 4);
//   Value value;
//   tableGet(&table, key, &value);
//   printValue(value);
//   printf("\ncount: %d capacity: %d\n", table.count, table.capacity);

  freeTable(&table);
  return 0;
}
