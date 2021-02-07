global    main
segment   .data
segment   .text
extern   _printInt
extern   _printString
extern   _readInt
extern   _readString
extern   _error
extern   _addTwoStrings
main:
    sub rsp,0
    push rbx
    push rsp
    push rbp
    push r11
    push r12
    push r13
    push r14
    call _main
    add rsp,0
    mov edi,eax
    mov rax,60
    syscall

_main:
    sub rsp,16
    push 0
    mov QWORD [rsp + 8],0
    mov QWORD [rsp + 16],0
    call _printInt
    add rsp,8
    mov QWORD [rsp + 0],rax
    mov rax,0
    add rsp,16
    ret
