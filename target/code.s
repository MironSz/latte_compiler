
global main
segment   .data
    const_2 dq 3
    const_2_body db `elo`,0xA

segment   .text

extern   printInt
extern   printStr
extern   readInt
extern   readStr
extern   error
extern   addTwoStrings


function_main:
    push const_2
    lea r15, [const_2]
    mov QWORD [rsp + 16],r15
    mov QWORD [rsp + 8],0
    call printStr
    mov rax,1
    ret


_main:
    call function_main
    ret
