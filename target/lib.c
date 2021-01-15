#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define LL long long


extern void c_exit(LL a){
    exit((int) a);
}
extern  void c_print_int(LL a){
    printf("%d\n" , (int)a);
}


extern  LL c_read_int() {
    LL a;
    scanf("%lld" , &a);
    return a;
}

extern  void c_print_str(void * s){
    LL length = * (LL *) (s);
    ((char *)(s+sizeof(LL)))[*((LL *) s)]='\n';

    printf("%.*s\n" , *((LL *) s), (char *) (s+sizeof(LL )));
}

extern  void * c_read_str(){
    void * buffor_ptr = NULL;
    LL length=0;
    length=getline( &buffor_ptr, &length, stdin);
    length--;

    void * result_ptr = malloc(sizeof(LL )+sizeof(char)*(length+1));
    *((LL *) result_ptr) = length;

    memcpy(result_ptr+sizeof(LL ), buffor_ptr, length+1);

    free(buffor_ptr);
    return result_ptr;
}

extern  void *  c_add_two_strings(void * s2, void * s1){
    LL length1  = *( LL *) s1;
    LL length2  = *( LL *) s2;
    LL result_length = length1+length2;

    void * result_ptr = malloc((result_length+1)*sizeof(char)+sizeof(LL ));

    *((LL *) result_ptr) = result_length;

    memcpy(result_ptr+sizeof(LL ), s1+sizeof(LL), length1);

    memcpy(result_ptr+sizeof(LL )+(length1)*sizeof(char), s2+sizeof(LL), length2+1);
    return result_ptr;
}
