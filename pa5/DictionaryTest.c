//-----------------------------------------------------------------------------
// Austin Yen
// 6/2/15
// Test file for Dictionary ADT
// DictionaryTest.c
//-----------------------------------------------------------------------------


#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180


int main(int argc, char* argv[]){
	Dictionary A = newDictionary();
//	printf("%s\n", (isEmpty(A) ? "true" : "false")); // check that it returns true
//	printf("num items: %d\n", size(A)); // check that it returns 0

	insert(A, "one", "ayy"); // try inserting something

//	printf("%s\n", (isEmpty(A) ? "true" : "false")); // check that it returns false
//	printf("num items: %d\n", size(A)); // try it again

//	printf("%s\n", lookup(A, "one")); // see if it returns "ayy"

	// insert a few more
	insert(A, "two", "byy");
	insert(A, "three", "cyy");
	insert(A, "four", "dyy"); 
	insert(A, "five", "eyy");

//	printf("num items: %d\n", size(A)); // see if it returns 5

//	printDictionary(stdout, A); // try printing the list out

	delete(A, "two"); // try deleting something

//	printf("num items: %d\n", size(A)); // see if it returns 4

//	printDictionary(stdout, A); // try printing the list out again

	makeEmpty(A); // try emptying the list
	printf("num items: %d\n", size(A)); // see if it returns 0
	printf("%s\n", (isEmpty(A) ? "true" : "false")); // check that it returns true

	freeDictionary(&A);

	return(EXIT_SUCCESS);
}