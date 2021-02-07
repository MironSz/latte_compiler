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
    pop r14
    pop r13
    pop r12
    pop r11
    pop rbp
    pop rsp
    pop rbx
    ret
_main:
    sub rsp,112
    mov rax,1
    add rax,1
    add rax,1
    add rax,1
    add rax,1
    add rax,1
    mov rbx,1
    add rbx,1
    add rbx,1
    add rbx,1
    add rbx,1
    add rbx,1
    xor rdx,rdx
    cqo
    idiv rbx
    push rax
    mov QWORD [rsp + 128],rax
    mov QWORD [rsp + 136],rbx
    call _printInt
    add rsp,8
    push QWORD [rsp + 128]
    call _printInt
    add rsp,8
    push QWORD [rsp + 120]
    call _printInt
    add rsp,8
    mov rax,0
    add rsp,120
    ret
