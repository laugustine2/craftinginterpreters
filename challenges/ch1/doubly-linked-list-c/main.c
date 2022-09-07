#include "list.h"

int main(char **argv, int argc) {
	List *list = createList();
	printList(list);
	append(list, "1");
	append(list, "2");
	append(list, "3");
	prepend(list, "a");
	printList(list);
	printNode(find(list, "2"));
	return 0;
}
