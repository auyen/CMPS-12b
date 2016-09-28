# Austin Yen
# CMPS 12B
# 4/26/15
# Makefile
# Wraps FileReverse.c in an executable file
FLAGS = -std=c99 -Wall
SOURCES = FileReverse.c Makefile README
OBJECTS = FileReverse.o
EXEBIN = FileReverse
SUBMIT = submit cmps012b-pt.s15 lab3

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)
$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)
clean :
	rm -f $(EXEBIN) $(OBJECTS))
submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)