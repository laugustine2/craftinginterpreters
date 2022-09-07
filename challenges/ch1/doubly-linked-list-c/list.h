typedef struct Node Node;
typedef struct List List;

struct List {
	int size;
	Node *head;
	Node *tail;
};

struct Node {
	char *data;
	Node *prev;
	Node *next;
};

List* createList();
void append(List *list, char *data);
void prepend(List *list, char *data);
Node* find(List *list, char *data);
void insertBefore(List *list, Node *next, char *data);
int delete(List *list, char *data);
void printList(List *list);
void printNode(Node *node);
