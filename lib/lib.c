#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define LL long long
void c_print_int(LL a){
    printf("%lld\n" , a);
}


LL c_read_int() {
    LL a;
    scanf("%lld" , &a);
    return a;
}

void c_print_str(void * s){
    printf("%s\n" , (char *) (s+sizeof(LL )));
}

void * c_read_str(){
    void * buffor_ptr = NULL;
    LL length = getline((char **) &buffor_ptr, NULL, stdin);
    void * result_ptr = malloc(sizeof(LL )+sizeof(char)*(length+1));
    *((LL *) result_ptr) = length;

    memcpy(result_ptr+sizeof(LL ), buffor_ptr, length+1);

    free(buffor_ptr);
    return result_ptr;
}

void *  c_add_two_strings(void * s1, void * s2){
    LL length1  = *( LL *) s1;
    LL length2  = *( LL *) s2;
    LL result_length = length1+length2;

    void * result_ptr = malloc((result_length+1)*sizeof(char)+sizeof(LL ));

    *((LL *) result_ptr) = result_length;

    memcpy(result_ptr+sizeof(LL ), s1, length1);
    memcpy(result_ptr+sizeof(LL )+(length1)*sizeof(char), s2, length2+1);

    return result_ptr;
}