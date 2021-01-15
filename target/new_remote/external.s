extern c_print_int
extern c_read_int
extern c_print_str
extern c_read_str
extern c_add_two_strings
extern c_exit


global _printString
global _printInt
global _error
global _addTwoStrings
global _readInt
global _readString

segment .text


_error:
    mov rdi,[rsp+8]
    mov rbp, rsp
    and rsp, -16

    call c_exit

    mov rsp, rbp
    ret

_addTwoStrings:
    mov rdi,[rsp+16]
    mov rsi,[rsp+8]

    mov rbp, rsp
    and rsp, -16

    call c_add_two_strings

    mov rsp, rbp
    ret


_printInt:
    mov rdi, [rsp+8]
    push rbp
    mov rbp, rsp
    and rsp, -16
    call c_print_int
    mov rsp, rbp
    pop rbp
    ret

_readInt:
    mov rbp, rsp
    and rsp, -16
    call c_read_int
    mov rsp, rbp
    ret

_printString:
    mov rdi, [rsp+8]

    mov rbp, rsp
    and rsp, -16
    call c_print_str
    mov rsp, rbp

    ret

_readString:
    mov rbp, rsp
    and rsp, -16
    call c_read_str
    mov rsp, rbp
    ret







