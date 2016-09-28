# Austin Yen
# CMPS 12B
# 4/9/15
# Makefile
# Compiles extrema.java and runs it
JAVASRC    = Extrema.java
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = Extrema
CLASSES    = Extrema.java
JARFILE    = Extrema
SUBMIT     = submit cmps012b-pt.s15 pa1

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