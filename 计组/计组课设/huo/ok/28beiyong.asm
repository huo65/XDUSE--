;By 笑风生
;http://blog.csdn.net/xiaofengsheng
SAVEREG macro
	push ax
	push bx
	push cx
	push dx
	push si
	push di
endm

RESAVEREG macro
	pop di
	pop si
	pop dx
	pop cx
	pop bx
	pop ax	
endm

;************************
data segment
	msg db 'Please input your decimal number:',13,10,'$'
	input_string db 200, ?, 200 dup(0)
	data_ten dw 10
data ends
;************************
code segment
;------------------------
main proc far
	assume cs:code, ds:data
start:
	push ds
	sub ax, ax
	push ax

	mov ax, data
	mov ds, ax
	;--------------
	;main process
	;--------------
	lea dx, msg
	mov ah, 09h
	int 21h

	;input the string
	lea dx, input_string
	mov ah, 0ah
	int 21h

	;enter line
	mov dx, 0ah
	mov ah, 02h
	int 21h

	mov si, 1
	mov cl, input_string[si]
	mov ch, 0
	inc si
	
	mov ax, 0
	mov dx, 0
GET_DECIMAL_LOOP:
	mul data_ten
	mov bl, input_string[si]
	mov bh, 0
	sub bx, 30h
	add ax, bx
	inc si
	loop GET_DECIMAL_LOOP

	call get_oct

	ret

;---------------------
;get_oct function
get_oct proc near
	SAVEREG
	mov cx, 0
	mov bx, 8
GET_OCT_LOOP:
	div bx
	push dx
	mov dx, 0
	inc cx
	cmp ax, 0
	jne GET_OCT_LOOP
	
PRINT:
	pop dx
	add dx, 30h
	mov ah, 02h
	int 21h
	loop PRINT

	;enter and change line
	mov dl, 0dh
	mov ah, 02h
	int 21h
	mov dl, 0ah
	mov ah, 02h
	int 21h

	RESAVEREG
	ret

get_oct endp

main endp
;-------------------------
code ends
;*************************
	end start