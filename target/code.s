global    main
segment   .data
    const_41 dq 0
    const_41_body db ``,0xA
    const_42 dq 1
    const_42_body db `=`,0xA
    const_43 dq 8
    const_43_body db `hello */`,0xA
    const_44 dq 8
    const_44_body db `/* world`,0xA
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

_ifac2f:
    sub rsp,64
    mov rax,0
    cmp rax, 1
    mov QWORD [rsp + 48],0
    mov QWORD [rsp + 40],0
    mov QWORD [rsp + 56],0
    mov QWORD [rsp + 32],0
    mov QWORD [rsp + 8],0
    mov QWORD [rsp + 0],0
    mov QWORD [rsp + 24],0
    mov QWORD [rsp + 16],0
    je _IfTrueBlock_22
    jmp _IfFinalBlock_23
_IfTrueBlock_22:
    mov rax,QWORD [rsp + 80]
    add rsp,64
    ret
_IfFinalBlock_23:
    mov rax,QWORD [rsp + 80]
    cmp rax, QWORD [rsp + 72]
    jg __WriteTrue_45
    mov QWORD [rsp + 56],0
    jmp __FinalLabel_46
__WriteTrue_45:
    mov QWORD [rsp + 56],1
__FinalLabel_46:
    mov rbx,QWORD [rsp + 56]
    cmp rbx, 1
    je _IfTrueBlock_24
    jmp _IfFinalBlock_25
_IfTrueBlock_24:
    mov rax,1
    add rsp,64
    ret
_IfFinalBlock_25:
    mov rax,QWORD [rsp + 80]
    add rax,QWORD [rsp + 72]
    mov rdx,0
    xor rdx,rdx
    mov rbx,2
    idiv rbx
    push QWORD [rsp + 80]
    push rax
    mov QWORD [rsp + 64],rax
    mov r15,QWORD [rsp + 64]
    mov QWORD [rsp + 56],r15
    mov r15,QWORD [rsp + 64]
    mov QWORD [rsp + 48],r15
    call _ifac2f
    add rsp,16
    mov rbx,QWORD [rsp + 32]
    add rbx,1
    push rbx
    push QWORD [rsp + 80]
    mov QWORD [rsp + 40],rax
    mov QWORD [rsp + 32],rbx
    call _ifac2f
    add rsp,16
    mov rbx,QWORD [rsp + 24]
    imul rbx,rax
    mov QWORD [rsp + 8],rax
    mov QWORD [rsp + 0],rbx
    mov rax,QWORD [rsp + 0]
    add rsp,64
    ret
_ifac:
    sub rsp,8
    push 1
    push QWORD [rsp + 24]
    mov QWORD [rsp + 16],0
    call _ifac2f
    add rsp,16
    mov QWORD [rsp + 0],rax
    mov rax,QWORD [rsp + 0]
    add rsp,8
    ret
_repStr:
    sub rsp,32
    mov QWORD [rsp + 8],0
    lea r15, [const_41]
    mov QWORD [rsp + 24],r15
    mov QWORD [rsp + 0],0
    mov QWORD [rsp + 16],0
    jmp _WhileCondition_27
_WhileCondition_27:
    mov rax,QWORD [rsp + 16]
    cmp rax, QWORD [rsp + 40]
    jl __WriteTrue_47
    mov QWORD [rsp + 8],0
    jmp __FinalLabel_48
__WriteTrue_47:
    mov QWORD [rsp + 8],1
__FinalLabel_48:
    mov rbx,QWORD [rsp + 8]
    cmp rbx, 1
    je _WhileBody_28
    jmp _WhileFinalBlock_29
_WhileBody_28:
    push QWORD [rsp + 24]
    push QWORD [rsp + 56]
    call _addTwoStrings
    add rsp,16
    mov QWORD [rsp + 0],rax
    mov r15,QWORD [rsp + 0]
    mov QWORD [rsp + 24],r15
    jmp _WhileCondition_27
_WhileFinalBlock_29:
    mov rax,QWORD [rsp + 24]
    add rsp,32
    ret
_main:
    sub rsp,136
    push 10
    mov QWORD [rsp + 120],0
    mov QWORD [rsp + 128],0
    mov QWORD [rsp + 136],0
    mov QWORD [rsp + 64],0
    mov QWORD [rsp + 40],0
    mov QWORD [rsp + 32],0
    mov QWORD [rsp + 48],0
    mov QWORD [rsp + 8],0
    mov QWORD [rsp + 24],0
    mov QWORD [rsp + 16],0
    mov QWORD [rsp + 56],0
    mov QWORD [rsp + 80],0
    mov QWORD [rsp + 88],0
    mov QWORD [rsp + 96],0
    mov QWORD [rsp + 104],0
    mov QWORD [rsp + 112],0
    mov QWORD [rsp + 72],0
    call _fac
    add rsp,8
    push rax
    mov QWORD [rsp + 136],rax
    call _printInt
    add rsp,8
    push 10
    mov QWORD [rsp + 128],rax
    call _rfac
    add rsp,8
    push rax
    mov QWORD [rsp + 120],rax
    call _printInt
    add rsp,8
    push 10
    mov QWORD [rsp + 112],rax
    call _mfac
    add rsp,8
    push rax
    mov QWORD [rsp + 104],rax
    call _printInt
    add rsp,8
    push 10
    mov QWORD [rsp + 96],rax
    call _ifac
    add rsp,8
    push rax
    mov QWORD [rsp + 88],rax
    call _printInt
    add rsp,8
    mov QWORD [rsp + 72],rax
    mov QWORD [rsp + 56],1
    mov QWORD [rsp + 64],10
    jmp _WhileCondition_1
_WhileCondition_1:
    mov rax,QWORD [rsp + 64]
    cmp rax, 0
    jg __WriteTrue_49
    mov QWORD [rsp + 48],0
    jmp __FinalLabel_50
__WriteTrue_49:
    mov QWORD [rsp + 48],1
__FinalLabel_50:
    mov rbx,QWORD [rsp + 48]
    cmp rbx, 1
    je _WhileBody_2
    jmp _WhileFinalBlock_3
_WhileBody_2:
    mov rax,QWORD [rsp + 56]
    imul rax,QWORD [rsp + 64]
    mov QWORD [rsp + 40],rax
    mov r15,QWORD [rsp + 40]
    mov QWORD [rsp + 56],r15
    jmp _WhileCondition_1
_WhileFinalBlock_3:
    push QWORD [rsp + 56]
    call _printInt
    add rsp,8
    push const_42
    push 60
    mov QWORD [rsp + 48],rax
    call _repStr
    add rsp,16
    push rax
    mov QWORD [rsp + 32],rax
    call _printString
    add rsp,8
    push const_43
    mov QWORD [rsp + 24],rax
    call _printString
    add rsp,8
    push const_44
    mov QWORD [rsp + 16],rax
    call _printString
    add rsp,8
    mov QWORD [rsp + 0],rax
    mov rax,0
    add rsp,136
    ret
_nfac:
    sub rsp,32
    mov rax,QWORD [rsp + 40]
    cmp rax, 0
    jne __WriteTrue_51
    mov QWORD [rsp + 24],0
    jmp __FinalLabel_52
__WriteTrue_51:
    mov QWORD [rsp + 24],1
__FinalLabel_52:
    mov rbx,QWORD [rsp + 24]
    cmp rbx, 1
    mov QWORD [rsp + 16],0
    mov QWORD [rsp + 8],0
    mov QWORD [rsp + 0],0
    je _IfElseTrueBlock_17
    jmp _IfElseFalseBlock_18
_IfElseTrueBlock_17:
    mov rax,QWORD [rsp + 40]
    sub rax,1
    push rax
    mov QWORD [rsp + 24],rax
    call _mfac
    add rsp,8
    mov QWORD [rsp + 8],rax
    imul rax,QWORD [rsp + 40]
    mov QWORD [rsp + 0],rax
    mov rax,QWORD [rsp + 0]
    add rsp,32
    ret
_IfElseFalseBlock_18:
    mov rax,1
    add rsp,32
    ret
_rfac:
    sub rsp,32
    mov rax,QWORD [rsp + 40]
    cmp rax, 0
    je __WriteTrue_53
    mov QWORD [rsp + 24],0
    jmp __FinalLabel_54
__WriteTrue_53:
    mov QWORD [rsp + 24],1
__FinalLabel_54:
    mov rbx,QWORD [rsp + 24]
    cmp rbx, 1
    mov QWORD [rsp + 0],0
    mov QWORD [rsp + 16],0
    mov QWORD [rsp + 8],0
    je _IfElseTrueBlock_9
    jmp _IfElseFalseBlock_10
_IfElseTrueBlock_9:
    mov rax,1
    add rsp,32
    ret
_IfElseFalseBlock_10:
    mov rax,QWORD [rsp + 40]
    sub rax,1
    push rax
    mov QWORD [rsp + 24],rax
    call _rfac
    add rsp,8
    mov rbx,QWORD [rsp + 40]
    imul rbx,rax
    mov QWORD [rsp + 8],rax
    mov QWORD [rsp + 0],rbx
    mov rax,QWORD [rsp + 0]
    add rsp,32
    ret
_fac:
    sub rsp,40
    mov QWORD [rsp + 8],0
    mov QWORD [rsp + 0],0
    mov QWORD [rsp + 32],1
    mov QWORD [rsp + 16],0
    mov r15,QWORD [rsp + 48]
    mov QWORD [rsp + 24],r15
    jmp _WhileCondition_5
_WhileCondition_5:
    mov rax,QWORD [rsp + 24]
    cmp rax, 0
    jg __WriteTrue_55
    mov QWORD [rsp + 16],0
    jmp __FinalLabel_56
__WriteTrue_55:
    mov QWORD [rsp + 16],1
__FinalLabel_56:
    mov rbx,QWORD [rsp + 16]
    cmp rbx, 1
    je _WhileBody_6
    jmp _WhileFinalBlock_7
_WhileBody_6:
    mov rax,QWORD [rsp + 32]
    imul rax,QWORD [rsp + 24]
    mov rbx,QWORD [rsp + 24]
    sub rbx,1
    mov QWORD [rsp + 8],rax
    mov QWORD [rsp + 0],rbx
    mov r15,QWORD [rsp + 8]
    mov QWORD [rsp + 32],r15
    mov r15,QWORD [rsp + 0]
    mov QWORD [rsp + 24],r15
    jmp _WhileCondition_5
_WhileFinalBlock_7:
    mov rax,QWORD [rsp + 32]
    add rsp,40
    ret
_mfac:
    sub rsp,32
    mov rax,QWORD [rsp + 40]
    cmp rax, 0
    je __WriteTrue_57
    mov QWORD [rsp + 24],0
    jmp __FinalLabel_58
__WriteTrue_57:
    mov QWORD [rsp + 24],1
__FinalLabel_58:
    mov rbx,QWORD [rsp + 24]
    cmp rbx, 1
    mov QWORD [rsp + 0],0
    mov QWORD [rsp + 16],0
    mov QWORD [rsp + 8],0
    je _IfElseTrueBlock_13
    jmp _IfElseFalseBlock_14
_IfElseTrueBlock_13:
    mov rax,1
    add rsp,32
    ret
_IfElseFalseBlock_14:
    mov rax,QWORD [rsp + 40]
    sub rax,1
    push rax
    mov QWORD [rsp + 24],rax
    call _nfac
    add rsp,8
    mov rbx,QWORD [rsp + 40]
    imul rbx,rax
    mov QWORD [rsp + 8],rax
    mov QWORD [rsp + 0],rbx
    mov rax,QWORD [rsp + 0]
    add rsp,32
    ret
