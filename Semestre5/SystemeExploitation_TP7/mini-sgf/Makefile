
CSRC1 := sgf-disk.c sgf-fat.c sgf-dir.c sgf-io.c
CSRC2 := $(CSRC1) sgf-impl.c

CFLAGS=-ansi -Wall -Werror
OBJ1=$(CSRC1:.c=.o)
OBJ=$(CSRC2:.c=.o)
HDR=$(wildcard sgf*.h)
EXE=sgf
MAKE2=$(shell ls Makefile2 2>/dev/null)

ifeq "$(MAKE2)" "Makefile2"
	include Makefile2
endif

all : $(EXE)

clean:
	@rm -vf $(OBJ1) main.o $(EXE)

$(EXE): $(OBJ) main.o
	@echo "===== Assemblage de $(EXE)"
	$(CC) -o $(EXE) main.o $(OBJ)

%.o: %.c $(HDR) Makefile
	$(CC) $(CFLAGS) -c -o $@ $<

format-disk:
	./format
