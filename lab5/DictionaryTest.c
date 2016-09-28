//-----------------------------------------------------------------------------
// Austin Yen
// 5/9/15
// Testing functions for the Dictionary ADT
// DictionaryTest.c
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
   Dictionary D = newDictionary();

   // Check if isEmpty() returns true
   // printf("empty? %s\n", (isEmpty(D) ? "true" : "false"));
   // It returned true

   // Check if size() returns 0
   // printf("size = %d\n", size(D));
   // It returned 0

   // Try inserting some values
   insert(D,"1", "a");
   insert(D, "2", "b");
   insert(D, "3", "c");
   insert(D, "4", "d");
   insert(D, "5", "e");
   insert(D, "6", "f");

   // try the print function
   // printDictionary(stdout, D);

   // see if size() returns the proper number
   //printf("size = %d\n", size(D)); // printed "size = 6"

   // check delete function using size and print functions
   // delete(D, "2");
   // delete(D, "5");
   delete(D, "1");
   printf("size = %d\n", size(D)); // printed "size = 3"
   printDictionary(stdout, D); // printed correctly

   // check if lookup finds a value by key
   printf("%s\n", lookup(D, "3")); // printed "c"

   // Check if isEmpty() returns false
   printf("empty? %s\n", (isEmpty(D) ? "true" : "false")); // returned false

   // check makeEmpty()
   // use isEmpty() to check
   printf("make empty\n");
   makeEmpty(D);
   printf("empty? %s\n", (isEmpty(D) ? "true" : "false")); // returned true

   // try adding something to check again
   // should print out the key and value of only what was added
   // insert(D,"1", "a"); 
   // printDictionary(stdout, D); // printed "1 a"

   freeDictionary(&D);
   return(EXIT_SUCCESS);
}
