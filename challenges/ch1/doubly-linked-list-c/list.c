#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"

List* createList() {
	// create list
	List *list = malloc(sizeof(List));
	// intialize size, head, tail
	list->size = 0;
	list->head = NULL;
	list->tail = NULL;
	// return created list
	return list;
}

Node* createNode(char *data) {
	// create node
	Node *node = malloc(sizeof(Node));
	// copy data to create node
	node->data = malloc(strlen(data) + 1);
	node->data = strcpy(node->data, data);
	// initialize next, prev to NULL
	node->next = NULL;
	node->prev = NULL;
	// return created node
	return node;
}

void append(List *list, char *data) {
	// create node with data
	Node *node = createNode(data);
	// append to list
	if (list->head == NULL) {
		// list is empty
		// 1. set head and tail to node
		// 2. set size to 1
		list->head = node;
		list->tail = node;
		list->size = 1;
	} else {
		// list is not empty
		// 1. set node prev to tail
		// 2. set tail next to node
		// 3. set tail to node
		// 4. increment list size
		node->prev = list->tail;
		list->tail->next = node;
		list->tail = node;
		list->size++;
	}
}

void prepend(List *list, char *data) {
	// create node with data
	Node *node = createNode(data);
	// append to list
	if (list->head == NULL) {
		// list is empty
		// 1. set head and tail to node
		// 2. set size to 1
		list->head = node;
		list->tail = node;
		list->size = 1;
	} else {
		// list is not empty
		// 1. set node next to head
		// 2. set head prev to node
		// 3. set head to node
		// 4. increment list size
		node->next = list->head;
		list->head->prev = node;
		list->head = node;
		list->size++;
	}
}

Node* find(List *list, char *data) {
	// start at head and proceed forward until node is found with matching data
	Node *node = list->head;
	while (node != NULL && strcmp(data, node->data) != 0) {
		node = node->next;
	}
	return node;
}

void insertBefore(List *list, Node *next, char *data) {
	if (next == NULL) {
		// no node, prepend to list
		prepend(list, data);
	} else {
		// 1. create node with data
		// 2. set node next to next
		// 3. set node prev to next prev
		// 4. set next prev next to node
		// 5. set next prev to node
		Node *node = createNode(data);
		node->next = next;
		node->prev = next->prev;
		next->prev->next = node;
		next->prev = node;
	}
}

int delete(List *list, char *data) {
	// find node
	Node *node = find(list, data);
	if (node == NULL) {
		return 0;
	}
	// 1. set node prev next to node next
	// 2. set node next prev to node prev
	// 3. free data
	// 4. free node
	if (node->prev != NULL) {
		node->prev->next = node->next;
	}
	if (node->next != NULL) {
		node->next->prev = node->prev;
	}
	free(node->data);
	free(node);
	return 1;
}

void printList(List *list) {
	printf("[");
	Node *node = list->head;
	while (node != NULL) {
		if (node == list->head) {
			printf("%s", node->data);
		} else {
			printf(", %s", node->data);
		}
		node = node->next;
	}
	printf("]\n");
}

void printNode(Node *node) {
	printf("%s\n", node->data);
}
