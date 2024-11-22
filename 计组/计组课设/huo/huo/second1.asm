assume ds:data, cs:code
data segment
    tip db 'Please input a number:$'
    errorinfo db 'The input is not a number!$'
    decimal dw 0
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

read:
    mov ah, 1
    int 21h

    cmp al, 'q'
    je quit
    cmp al, 'Q'
    je quit
    cmp al, 0Dh
    je output
    cmp al, '0'
    jb error
    cmp al, '9'
    ja error

; x10
    sub al, '0'
    mov bl, al
    mov ax, 0Ah
    mul decimal
    mov decimal, ax
    xor bh, bh
    add decimal, bx
    jmp read

output:
    mov bx, decimal

    mov ah, 2
    mov dl, 0Ah
    int 21h
    mov dl, 0Dh
    int 21h
;循环次数
    mov cx, 10h

bit:
    test bx, 8000h
    jz set0

set1:
    mov dl, '1'
    jmp print

set0:
    mov dl, '0'

print:
    mov ah, 2
    int 21h
; 将bx寄存器左移一位，相当于将二进制数向左移动一位。
    shl bx, 1
    loop bit

    mov ah, 2
    mov dl, 'b'
    int 21h

    mov decimal, 0

    mov dl, 0Ah
    int 21h
    mov dl, 0Dh
    int 21h

    jmp read

error:
    mov ah, 2
    mov dl, 0Ah
    int 21h
    mov dl, 0Dh
    int 21h

    mov ah, 9
    lea dx, errorinfo
    int 21h

    mov ah, 2
    mov dl, 0Ah
    int 21h
    mov dl, 0Dh
    int 21h

    mov decimal, 0
    jmp read

quit:
    mov ax, 4c00h
    int 21h
code ends

end start