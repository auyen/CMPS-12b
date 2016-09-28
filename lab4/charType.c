/*
*  Austin Yen
*  5/2/15
*  charType.c
*  Reads input file, sorts alphabetic, numeric, punctuation, and whitespace
*  characters. Then prints how many of each are in each line of the input file
*  in an output file.
*/


#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>

#define MAX_STRING_LENGTH 100

// function prototype 
void extract_chars(char* s, char* a, char* d, char* p, char* w);

// function main which takes command line arguments 
int main(int argc, char* argv[]){
   FILE* in;						// handle for input file                  
   FILE* out;						// handle for output file                 
   char* line;						// string holding input line              
   char* alpha;						// string holding all alphabetical chars 
   char* numeric;					// string holding all numeric chars
   char* punct;						// string holding all punctuation chars
   char* whitesp;					// string holding all whitespace chars

   // check command line for correct number of arguments 
   if( argc != 3 ){
      printf("Usage: %s input-file output-file\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   // open input file for reading 
   if( (in=fopen(argv[1], "r"))==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   // open output file for writing 
   if( (out=fopen(argv[2], "w"))==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   // allocate strings line and each string on the heap 
   line = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   alpha = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   numeric = calloc(MAX_STRING_LENGTH + 1, sizeof(char));
   punct = calloc(MAX_STRING_LENGTH + 1, sizeof(char));
   whitesp = calloc(MAX_STRING_LENGTH + 1, sizeof(char));
   assert(line != NULL && alpha != NULL && numeric != NULL && punct != NULL && whitesp != NULL);

   // read each line in input file, extract the different types of characters
   int linecount = 1;
   while( fgets(line, MAX_STRING_LENGTH, in) != NULL ){
	   
	  extract_chars(line, alpha, numeric, punct, whitesp);
	  fprintf(out,"Line %d contains: \n", linecount);
	  if (strlen(alpha) == 1){
		  fprintf(out, "%d alphabetic character: %s \n", 1, alpha);
	  }
	  else{
		  fprintf(out, "%d alphabetic characters: %s \n", strlen(alpha), alpha);
	  }
	  
	  if (strlen(numeric) == 1){
		  fprintf(out, "%d numeric character: %s \n", 1, numeric);
	  }
	  else{
		  fprintf(out, "%d numeric characters: %s \n", strlen(numeric), numeric);
	  }

	  if (strlen(punct) == 1){
		  fprintf(out, "%d punctuation character: %s \n", 1, punct);
	  }
	  else{
		  fprintf(out, "%d punctuation characters: %s \n", strlen(punct), punct);
	  }

	  if (strlen(whitesp) == 1){
		  fprintf(out, "%d whitespace character: %s\n", 1, whitesp);
	  }
	  else{
		  fprintf(out, "%d whitespace characters: %s\n", strlen(whitesp), whitesp);
	  }
	  linecount++;
   }

   // free heap memory 
   free(line);
   free(alpha);
   free(numeric);
   free(punct);
   free(whitesp);
   line = NULL;
   alpha = NULL;
   numeric = NULL;
   punct = NULL;
   whitesp = NULL;

   // close input and output files 
   fclose(in);
   fclose(out);

   return EXIT_SUCCESS;
}

// function definition 
void extract_chars(char* s, char* a, char* d, char* p, char* w){
   int i=0, j=0, k=0, l=0, m=0;
   while(s[i]!='\0' && i<MAX_STRING_LENGTH){
	   if (isalpha(s[i]) != 0){
		   a[j++] = s[i];
		   
	   }
	   if (isdigit((int)s[i]) != 0){
		   d[k++] = s[i];
		   
	   }
	   if (ispunct(s[i]) != 0){
		   p[l++] = s[i];
		   
	   }
	   if (isspace((int)s[i]) != 0){
		   w[m++] = s[i];
		   
	   }
		i++;

   }
   a[j] = '\0';
   d[k] = '\0';
   p[l] = '\0';
   w[m] = '\0';
}