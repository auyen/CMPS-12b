# Austin Yen
# CMPS 12B
# 4/19/15
# Makefile
# Can compile Search.java, create an executable jar, remove files, or submit files
JAVASRC    = Search.java
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = Search
CLASSES    = Search.java
JARFILE    = Search
SUBMIT     = submit cmps012b-pt.s15 pa2

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)