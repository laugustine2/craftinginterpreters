#include "list.h"

int main(char **argv, int argc) {
	List *list = createList();
	// empty list
	printList(list);
	// a, 1, 2, 3
	append(list, "1");
	append(list, "2");
	append(list, "3");
	prepend(list, "a");
	printList(list);
	// a, 1, b, 2, 3
	insertBefore(list, find(list, "2"), "b");
	printList(list);
	// a, 1, b, 2
	delete(list, "3");
	printList(list);
	return 0;
}
