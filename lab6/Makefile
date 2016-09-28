#------------------------------------------------------------------------------
# Austin Yen
# 5/17/15
# Makefile
# Wraps the files in an executable file
#------------------------------------------------------------------------------

# Variables
SOURCES    = List.java ListInterface.java ListClient.java\
             ListIndexOutOfBoundsException.java
OTHER      = Makefile ListTest.java README
MAINCLASS  = ListClient
CLASSES    = List.class ListInterface.class ListClient.class List\$$Node.class\
             ListIndexOutOfBoundsException.class
JARFILE    = ListClient
SUBMIT     = submit cmps012b-pt.s15 lab6

# Build Targets
all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(SOURCES)
	javac -Xlint:all,-serial $(SOURCES)  

clean:
	rm -f $(CLASSES) $(JARFILE) 

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES) $(OTHER)

