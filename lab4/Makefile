# Austin Yen
# CMPS 12B
# 5/2/15
# Makefile
# Wraps charType.c in an executable file


FLAGS = -std=c99 -Wall
SOURCES = charType.c Makefile README
IN = charType.c
OUT = out.txt
OBJECTS = charType.o
EXEBIN = charType
SUBMIT = submit cmps012b-pt.s15 lab4

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm $(EXEBIN) $(OBJECTS)

check :
	valgrind --leak-check=full $(EXEBIN) $(IN) $(OUT) 

submit : $(SOURCES)
	    $(SUBMIT) $(SOURCES)
	
