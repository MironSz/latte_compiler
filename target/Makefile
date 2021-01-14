all: lib.o external.o code.o
	gcc -no-pie -o out code.o external.o lib.o

lib.o:
	gcc -no-pie -c lib.c -o lib.o
external.o:
	nasm external.s -f elf64 -o external.o
code.o:
	nasm code.s -f elf64 -o code.o

clean:
	rm *.o
	rm out
	rm code.s



