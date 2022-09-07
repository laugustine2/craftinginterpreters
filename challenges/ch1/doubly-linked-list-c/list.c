#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"

List* createList() {
	List *list = malloc(sizeof(List));
	list->size = 0;
	list->head = NULL;
	list->tail = NULL;
	return list;
}

Node* createNode(char *data) {
	Node *node = malloc(sizeof(Node));
	node->data = data;
	node->next = NULL;
	node->prev = NULL;
	return node;
}

void append(List *list, char *data) {
	Node *node = createNode(data);
	if (list->head == NULL) {
		list->head = node;
		list->tail = node;
		list->size = 1;
	} else {
		node->prev = list->tail;
		list->tail->next = node;
		list->tail = node;
		list->size++;
	}
}

void prepend(List *list, char *data) {
	Node *node = malloc(sizeof(Node));
	node->data = data;
	if (list->head == NULL) {
		list->head = node;
		list->tail = node;
		list->size = 1;
	} else {
		node->next = list->head;
		list->head->prev = node;
		list->head = node;
		list->size++;
	}
}

Node* find(List *list, char *data) {
	Node *node = list->head;
	while (node != NULL && strcmp(data, node->data) != 0) {
		node = node->next;
	}
	return node;
}

int delete(List *list, char *data) {
	Node *node = find(list, data);
	if (node == NULL) {
		return 0;
	}
	node->prev->next = node->next;
	node->next->prev = node->prev;
	free(node->data);
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
