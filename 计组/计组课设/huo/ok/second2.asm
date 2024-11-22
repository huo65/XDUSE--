data segment
    tip db "Press a string to count(enter q or Q to quit):$"
    counttext db "count:$"
    result db 0
data ends

stack segment
    dw 64 dup(0)
stack ends

code segment
start:
    mov ax, data
    mov ds, ax
    mov es, ax

    mov ah,9
    lea dx,tip
    int 21h

    mov ah,2
    mov dl,0Ah
    int 21h
    mov dl,0Dh
    int 21h

    mov cx,0
read:
    mov ah,1
    int 21h

    cmp al,'q'
    je quit
    cmp al,'Q'
    je quit
    cmp al,' '
    je count
    cmp al,'0'
    jb nonNumber
    cmp al,'9'
    ja nonNumber
    inc cx
nonNumber:
    jmp read

count:
    mov ah,2
    mov dl,0Ah
    int 21h
    mov dl,0Dh
    int 21h

    xor bx,bx
pushstack:
    mov ax,cx
    mov dl,0Ah
    div dl
    xor ch,ch
    mov cl,al
    add ah,'0'
    push ax
    inc bx
    cmp cx,0
    jne pushstack

    mov ah,9
    lea dx,counttext
    int 21h

    mov ah,2
    mov cx,bx
output:
    pop dx
    mov dl,dh
    int 21h
    loop output

    xor cx,cx
    mov dl,0Ah
    int 21h
    mov dl,0Dh
    int 21h
    jmp read

quit:
    mov ax, 4c00h
    int 21h
code ends
end start