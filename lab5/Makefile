#----------------------------------------------------------------
# Austin Yen
# CMPS 12B
# 5/9/15
# Makefile
# makefile for DictionaryClient.c with macros
#----------------------------------------------------------------

FLAGS   = -std=c99 -Wall
SOURCES = Dictionary.c DictionaryClient.c 
OTHER   = DictionaryTest.c README Makefile
OBJECTS = Dictionary.o DictionaryClient.o
HEADERS = Dictionary.h
EXEBIN  = DictionaryClient
SUBMIT  = submit cmps012b-pt.s15 lab5

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

check:
	valgrind --leak-check=full $(EXEBIN) 

submit: $(SOURCES)
	  $(SUBMIT) $(SOURCES) $(HEADERS) $(OTHER)
