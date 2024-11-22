data segment
    tip db "Press a string to count:$"
    counttext db "Count: $"
    result db 0
data ends

code segment
start:
    mov ax, data
    mov ds, ax
    mov es, ax

    mov ah, 9
    lea dx, tip
    int 21h

    mov ah, 2
    mov dl, 0Ah
    int 21h
    mov dl, 0Dh
    int 21h

    mov cx, 0
read:
    mov ah, 1
    int 21h

    cmp al, 'q'
    je quit
    cmp al, 'Q'
    je quit
    cmp al, ' '
    je count
    cmp al, '0'
    jb nonNumber
    cmp al, '9'
    ja nonNumber
    inc cx
nonNumber:
    jmp read

count:
    mov ah, 2
    mov dl, 0Ah
    int 21h
    mov dl, 0Dh
    int 21h

    mov ah, 9
    lea dx, counttext
    int 21h

    mov ah, 2
    mov dl, cl
    add dl, '0'
    int 21h

    xor cx, cx ; 清零cx寄存器
    mov dl, 0Ah
    int 21h
    mov dl, 0Dh
    int 21h

    jmp read

quit:
    mov ax, 4C00h
    int 21h
code ends
end start