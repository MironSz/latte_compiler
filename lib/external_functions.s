

extern _c_print_int
extern _c_read_int
extern _c_print_str
extern _c_read_str
extern _c_add_two_strings

segment   .text

error:
     mov eax, 60  ; sys_exit syscall number: 60
     mov edi, 1 ; set exit status to 0 (`xor edi, edi` is equal to `mov edi, 0` )
     syscall      ; call it

addTwoStrings:
    mov rdi, [rsp+16]
    mov rsi, [rsp+8]
    mov rbp, rsp
    and rsp, -16
    call _c_add_two_strings
    mov rsp, rbp
    ret


printInt:
    mov rdi, [rsp+8]
    mov rbp, rsp
    and rsp, -16

    call _c_print_int

    mov rsp, rbp
    ret

readInt:
    mov rbp, rsp
    and rsp, -16
    call _c_read_int
    mov rsp, rbp
    ret

printString:
    mov rdi, [rsp+8]
    mov rbp, rsp
    and rsp, -16
    call _c_print_str
    mov rsp, rbp
    ret

readStr:
    mov rbp, rsp
    and rsp, -16
    call _c_read_str
    mov rsp, rbp
    ret






