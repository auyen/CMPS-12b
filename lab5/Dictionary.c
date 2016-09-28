//-----------------------------------------------------------------------------
// Austin Yen
// 5/9/15
// Implementation file for Dictionary ADT
// Dictionary.c
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

#define MAX_STRING_LENGTH 100

// private types --------------------------------------------------------------

// NodeObj
typedef struct NodeObj{
	char* key;
	char* value;
   struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* x,char* y) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->value = y;
   N->key = x;
   N->next = NULL;
   return(N);
}


// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
	if (pN != NULL && *pN != NULL){
		free(*pN);
		*pN = NULL;
	}
}


// DictionaryObj
typedef struct DictionaryObj{
   Node head;
   Node tail;
   int numItems;
} DictionaryObj;

// private helper function -------------------------------------------------

// findKey()
// returns a reference to the Node at position with the same key
Node findKey(Dictionary D,char* key){
	Node N = D->head;
	if (D->numItems == 1){
		if (!strcmp(N->key,key)==0){
			return NULL;
		}
	}
	else{
		for (int i = 1; i <= D->numItems; i++){

			if (strcmp(N->key,key)==0){
				break;
			}
			N = N->next;
			if (i == D->numItems){
				return NULL;
			}
		}
	}
	return N;
}

// public functions -----------------------------------------------------------

// Dictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D!=NULL);
   D->head = NULL;
   D->tail = NULL;
   D->numItems = 0;
   return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
	if (pD != NULL && *pD != NULL){
		if (!isEmpty(*pD)) makeEmpty(*pD);
		free(*pD);
		*pD = NULL;
	}
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
              "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return(D->numItems==0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
	return D->numItems;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
	if (findKey(D,k) == NULL){
		return NULL;
	}
	else{
		return findKey(D,k)->value;
	}
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
	if (lookup(D, k) == NULL){
		Node N;
		if (D->numItems == 0){ // if there are no items in the list
			N = newNode(k, v);
			N->next = D->head;
			D->head = N;
			D->tail = D->head;
		}
		else{ // if there are items in the list, new items are added to the end
			N = D->tail;
			Node O = newNode(k, v);
			N->next = O;
			D->tail = O;
		}
		D->numItems++;
	}
	else{
		fprintf(stderr,
			"Dictionary Error: Cannot insert duplicate key\n");
		exit(EXIT_FAILURE);
	}
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
	if (lookup(D,k) == NULL){
		fprintf(stderr,
			"Dictionary Error: cannot delete non-existent key\n");
		exit(EXIT_FAILURE);
	}
	else{
		if (D->head == findKey(D,k)){
			Node N = D->head;
			D->head = D->head->next;
			freeNode(&N);
		}
		else{
			Node N = D->head;
			int x = 1;
			while (x == 1){
				if (N->next == findKey(D,k)){
					Node P = N;
					N = P->next;
					P->next = N->next;
					freeNode(&N);
					break;
				}
				N = N->next;
			}

		}
		D->numItems--;
	}
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
	if (D == NULL){
		fprintf(stderr,
			"Dictionary Error: calling makeEmpty() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	while (D->numItems>0){
		delete(D, D->head->key);
	}
	
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
	
	for (Node N = D->head; N != NULL; N = N->next){
		fprintf(out, "%s %s\n", N->key, N->value);
	}
	

}

