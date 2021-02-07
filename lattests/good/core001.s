global    main
segment   .data
    const_44 dq 1
    const_44_body db `=`,0xA
    const_45 dq 8
    const_45_body db `hello */`,0xA
    const_46 dq 8
    const_46_body db `/* world`,0xA
    const_47 dq 0
    const_47_body db ``,0xA
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

_repStr:
    sub rsp,40
    lea r15, [const_47]
    mov QWORD [rsp + 32],r15
    mov QWORD [rsp + 16],0
    mov QWORD [rsp + 24],0
    mov QWORD [rsp + 8],0
    mov QWORD [rsp + 0],0
    jmp _WhileCondition_27
_WhileCondition_27:
    mov rax,QWORD [rsp + 24]
    cmp rax, QWORD [rsp + 48]
    jl __WriteTrue_48
    mov QWORD [rsp + 16],0
    jmp __FinalLabel_49
__WriteTrue_48:
    mov QWORD [rsp + 16],1
__FinalLabel_49:
    mov rbx,QWORD [rsp + 16]
    cmp rbx, 1
    je _WhileBody_28
    jmp _WhileFinalBlock_29
_WhileBody_28:
    push QWORD [rsp + 32]
    push QWORD [rsp + 64]
    call _addTwoStrings
    add rsp,16
    mov rbx,QWORD [rsp + 24]
    add rbx,1
    mov QWORD [rsp + 8],rax
    mov QWORD [rsp + 0],rbx
    mov r15,QWORD [rsp + 8]
    mov QWORD [rsp + 32],r15
    mov r15,QWORD [rsp + 0]
    mov QWORD [rsp + 24],r15
    jmp _WhileCondition_27
_WhileFinalBlock_29:
    mov rax,QWORD [rsp + 32]
    add rsp,40
    ret
_nfac:
    sub rsp,32
    mov rax,QWORD [rsp + 40]
    cmp rax, 0
    jne __WriteTrue_50
    mov QWORD [rsp + 24],0
    jmp __FinalLabel_51
__WriteTrue_50:
    mov QWORD [rsp + 24],1
__FinalLabel_51:
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
_main:
    sub rsp,144
    push 10
    mov QWORD [rsp + 128],0
    mov QWORD [rsp + 136],0
    mov QWORD [rsp + 144],0
    mov QWORD [rsp + 72],0
    mov QWORD [rsp + 48],0
    mov QWORD [rsp + 40],0
    mov QWORD [rsp + 56],0
    mov QWORD [rsp + 16],0
    mov QWORD [rsp + 8],0
    mov QWORD [rsp + 32],0
    mov QWORD [rsp + 24],0
    mov QWORD [rsp + 64],0
    mov QWORD [rsp + 88],0
    mov QWORD [rsp + 96],0
    mov QWORD [rsp + 104],0
    mov QWORD [rsp + 112],0
    mov QWORD [rsp + 120],0
    mov QWORD [rsp + 80],0
    call _fac
    add rsp,8
    push rax
    mov QWORD [rsp + 144],rax
    call _printInt
    add rsp,8
    push 10
    mov QWORD [rsp + 136],rax
    call _rfac
    add rsp,8
    push rax
    mov QWORD [rsp + 128],rax
    call _printInt
    add rsp,8
    push 10
    mov QWORD [rsp + 120],rax
    call _mfac
    add rsp,8
    push rax
    mov QWORD [rsp + 112],rax
    call _printInt
    add rsp,8
    push 10
    mov QWORD [rsp + 104],rax
    call _ifac
    add rsp,8
    push rax
    mov QWORD [rsp + 96],rax
    call _printInt
    add rsp,8
    mov QWORD [rsp + 80],rax
    mov QWORD [rsp + 64],1
    mov QWORD [rsp + 72],10
    jmp _WhileCondition_1
_WhileCondition_1:
    mov rax,QWORD [rsp + 72]
    cmp rax, 0
    jg __WriteTrue_52
    mov QWORD [rsp + 56],0
    jmp __FinalLabel_53
__WriteTrue_52:
    mov QWORD [rsp + 56],1
__FinalLabel_53:
    mov rbx,QWORD [rsp + 56]
    cmp rbx, 1
    je _WhileBody_2
    jmp _WhileFinalBlock_3
_WhileBody_2:
    mov rax,QWORD [rsp + 64]
    imul rax,QWORD [rsp + 72]
    mov rbx,QWORD [rsp + 72]
    sub rbx,1
    mov QWORD [rsp + 48],rax
    mov QWORD [rsp + 40],rbx
    mov r15,QWORD [rsp + 48]
    mov QWORD [rsp + 64],r15
    mov r15,QWORD [rsp + 40]
    mov QWORD [rsp + 72],r15
    jmp _WhileCondition_1
_WhileFinalBlock_3:
    push QWORD [rsp + 64]
    call _printInt
    add rsp,8
    push const_44
    push 60
    mov QWORD [rsp + 48],rax
    call _repStr
    add rsp,16
    push rax
    mov QWORD [rsp + 32],rax
    call _printString
    add rsp,8
    push const_45
    mov QWORD [rsp + 24],rax
    call _printString
    add rsp,8
    push const_46
    mov QWORD [rsp + 16],rax
    call _printString
    add rsp,8
    mov QWORD [rsp + 0],rax
    mov rax,0
    add rsp,144
    ret
_ifac2f:
    sub rsp,72
    mov rax,QWORD [rsp + 88]
    cmp rax, QWORD [rsp + 80]
    je __WriteTrue_54
    mov QWORD [rsp + 64],0
    jmp __FinalLabel_55
__WriteTrue_54:
    mov QWORD [rsp + 64],1
__FinalLabel_55:
    mov rbx,QWORD [rsp + 64]
    cmp rbx, 1
    mov QWORD [rsp + 8],0
    mov QWORD [rsp + 0],0
    mov QWORD [rsp + 56],0
    mov QWORD [rsp + 32],0
    mov QWORD [rsp + 24],0
    mov QWORD [rsp + 16],0
    mov QWORD [rsp + 48],0
    mov QWORD [rsp + 40],0
    je _IfTrueBlock_22
    jmp _IfFinalBlock_23
_IfTrueBlock_22:
    mov rax,QWORD [rsp + 88]
    add rsp,72
    ret
_IfFinalBlock_23:
    mov rax,QWORD [rsp + 88]
    cmp rax, QWORD [rsp + 80]
    jg __WriteTrue_56
    mov QWORD [rsp + 56],0
    jmp __FinalLabel_57
__WriteTrue_56:
    mov QWORD [rsp + 56],1
__FinalLabel_57:
    mov rbx,QWORD [rsp + 56]
    cmp rbx, 1
    je _IfTrueBlock_24
    jmp _IfFinalBlock_25
_IfTrueBlock_24:
    mov rax,1
    add rsp,72
    ret
_IfFinalBlock_25:
    mov rax,QWORD [rsp + 88]
    add rax,QWORD [rsp + 80]
    xor rdx,rdx
    mov rbx,2
    cqo
    idiv rbx
    push QWORD [rsp + 88]
    push rax
    mov QWORD [rsp + 64],rax
    mov r15,QWORD [rsp + 64]
    mov QWORD [rsp + 48],r15
    mov r15,QWORD [rsp + 64]
    mov QWORD [rsp + 56],r15
    call _ifac2f
    add rsp,16
    mov rbx,QWORD [rsp + 32]
    add rbx,1
    push rbx
    push QWORD [rsp + 88]
    mov QWORD [rsp + 40],rax
    mov QWORD [rsp + 32],rbx
    call _ifac2f
    add rsp,16
    mov rbx,QWORD [rsp + 24]
    imul rbx,rax
    mov QWORD [rsp + 8],rax
    mov QWORD [rsp + 0],rbx
    mov rax,QWORD [rsp + 0]
    add rsp,72
    ret
_fac:
    sub rsp,40
    mov QWORD [rsp + 0],0
    mov QWORD [rsp + 16],0
    mov QWORD [rsp + 8],0
    mov QWORD [rsp + 32],1
    mov r15,QWORD [rsp + 48]
    mov QWORD [rsp + 24],r15
    jmp _WhileCondition_5
_WhileCondition_5:
    mov rax,QWORD [rsp + 24]
    cmp rax, 0
    jg __WriteTrue_58
    mov QWORD [rsp + 16],0
    jmp __FinalLabel_59
__WriteTrue_58:
    mov QWORD [rsp + 16],1
__FinalLabel_59:
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
_rfac:
    sub rsp,32
    mov rax,QWORD [rsp + 40]
    cmp rax, 0
    je __WriteTrue_60
    mov QWORD [rsp + 24],0
    jmp __FinalLabel_61
__WriteTrue_60:
    mov QWORD [rsp + 24],1
__FinalLabel_61:
    mov rbx,QWORD [rsp + 24]
    cmp rbx, 1
    mov QWORD [rsp + 8],0
    mov QWORD [rsp + 0],0
    mov QWORD [rsp + 16],0
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
_mfac:
    sub rsp,32
    mov rax,QWORD [rsp + 40]
    cmp rax, 0
    je __WriteTrue_62
    mov QWORD [rsp + 24],0
    jmp __FinalLabel_63
__WriteTrue_62:
    mov QWORD [rsp + 24],1
__FinalLabel_63:
    mov rbx,QWORD [rsp + 24]
    cmp rbx, 1
    mov QWORD [rsp + 8],0
    mov QWORD [rsp + 0],0
    mov QWORD [rsp + 16],0
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
