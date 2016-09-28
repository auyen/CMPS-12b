//-----------------------------------------------------------------------------
// Austin Yen
// 6/2/15
// Implementation file for Dictionary ADT
// Dictionary.c
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"
const int tableSize = 101;

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
Node newNode(char* x, char* y) {
	Node N = malloc(sizeof(NodeObj));
	assert(N != NULL);
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
	int numItems;
	Node *table;
} DictionaryObj;

// private helper function -------------------------------------------------

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
	int sizeInBits = 8 * sizeof(unsigned int);
	shift = shift & (sizeInBits - 1);
	if (shift == 0)
		return value;
	return (value << shift) | (value >> (sizeInBits - shift));
}
// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
	unsigned int result = 0xBAE86554;
	while (*input) {
		result ^= *input++;
		result = rotate_left(result, 5);
	}
	return result;
}
// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
	return (pre_hash(key) % tableSize);
}



// findKey()
// returns a reference to the Node at position with the same key
Node findKey(Dictionary D, char* key){
	Node N = NULL;
	if (D->numItems == 0){
		return NULL;
	}
	int i;
	for (i = 0; i < tableSize; i++){
		if (D->table[i] != NULL){
			N = D->table[i];
			break;
		}
	}
	if (strcmp(N->key, key) == 0){
		return N;
	}
	while (N->next != NULL){
		if (strcmp(N->key, key) == 0){
			return N;
		}
		N = N->next;
	}
	
	for (int j = i+1; j < tableSize; j++){
		if (D->table[j] == NULL){
			continue;
		}
		else{
			N = D->table[j];
			if (strcmp(N->key, key) == 0){
				return N;
			}
			while (N->next != NULL){
				if (strcmp(N->key, key) == 0){
					return N;
				}
				N = N->next;
			}
		}
	}
	
	return NULL;
}



// public functions -----------------------------------------------------------





// Dictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
	Dictionary D = malloc(sizeof(DictionaryObj));
	assert(D != NULL);
	D->numItems = 0;
	D->table = calloc(tableSize, sizeof(NodeObj));
	assert(D->table != NULL);
	for (int i = 0; i < tableSize; i++) D->table[i] = NULL;
	return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
	if (pD != NULL && *pD != NULL){
		
		free((*pD)->table);
		if (!isEmpty(*pD)) makeEmpty(*pD);
		free(*pD);
		*pD = NULL;
		
	}
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
	if (D == NULL){
		fprintf(stderr,
			"Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	return(D->numItems == 0);
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
	if (findKey(D, k) == NULL){
		return NULL;
	}
	else{
		return findKey(D, k)->value;
	}
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
	int index = hash(k);
	if (lookup(D, k) == NULL){
		Node N;
		if(D->table[index] == NULL){ // if there are no items in that index, one is added
			N = newNode(k, v);
			D->table[index] = N;

		}

		else{ // if there are items in the index, a new node is placed at the end of the linked list
			N = D->table[index];
			
			Node O = newNode(k, v);
			O->next = N;
			D->table[index] = O;

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
	if (lookup(D, k) == NULL){
		fprintf(stderr,
			"Dictionary Error: cannot delete non-existent key\n");
		exit(EXIT_FAILURE);
	}
	else{

		if (D->table[hash(k)] == findKey(D, k)){
			Node N = D->table[hash(k)];
			D->table[hash(k)] = D->table[hash(k)]->next;
			freeNode(&N);
		}
		else{
			Node N = D->table[hash(k)];
			int x = 1;
			while (x == 1){
				if (N->next == findKey(D, k)){
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
		for (int i = 0; i < tableSize; i++){
			while (D->table[i] != NULL){
				delete(D, D->table[i]->key);
			}
		}
		
	}

}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
	for (int i = 0; i < tableSize; i++){
		if (D->table[i] != NULL){
			for (Node N = D->table[i]; N != NULL; N = N->next){
				fprintf(out, "%s %s\n", N->key, N->value);
			}
		}
	}


}

