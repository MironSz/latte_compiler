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
    sub rsp,40
    mov rax,0
    sub rax,36
    push 45
    mov QWORD [rsp + 32],rax
    mov QWORD [rsp + 8],0
    mov QWORD [rsp + 16],0
    mov r15,QWORD [rsp + 32]
    mov QWORD [rsp + 24],r15
    mov QWORD [rsp + 40],45
    call _printInt
    add rsp,8
    push QWORD [rsp + 16]
    mov QWORD [rsp + 16],rax
    call _printInt
    add rsp,8
    mov QWORD [rsp + 0],rax
    mov rax,0
    add rsp,40
    ret
