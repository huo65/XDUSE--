COM_ADD     EQU 0273H   
PA_ADD      EQU 0270H   
PB_ADD      EQU 0271H   
PC_ADD      EQU 0272H   


STACK1      SEGMENT STACK   
        DW  127 DUP(?)  
STACK1      ENDS        


DATA1       SEGMENT WORD PUBLIC 'DATA'
LEDDATA1 DB  10111110B   
        DB  10111111B   
        DB  10111101B   
        DB  11101011B   
        DB  11111011B   
        DB  11011011B   
DATA1       ENDS        
    

CODE        SEGMENT     
START       PROC    NEAR    
        ASSUME  CS:CODE, DS:DATA1, SS:STACK1
        MOV AX, DATA1   
        MOV DS, AX
        NOP  
        MOV DX, COM_ADD
        MOV AL, 89H  
        OUT DX, AL
        MOV DX, PA_ADD  
        MOV AL, 0FFH
        OUT DX, AL
        LEA BX, LEDDATA1
        
        INPUT:  

	
	MOV DX,PC_ADD
	
   ; MOV DX, IO244
   	IN  AX, DX
    CMP AX, 0FFFFH  
    JZ Q1  
    CMP AX, 0FE00H  
    JZ Q2
    
    MOV DX,PA_ADD  
    ;MOV DX, IO273
    NOT AX  
    OUT DX, AX
    JMP INPUT
Q1:
    MOV AX, 7FFFH
    MOV DX,PA_ADD
    ;MOV DX, IO273
R2L:
    CALL Light500ms
    OUT DX, AX  
    ROL AX, 1
    CMP AX, 7FFFH
    JNE R2L  
    JMP INPUT
        
Q2:
    MOV AX, 0FFFEH
    MOV DX,PA_ADD
    ;MOV DX, IO273
L2R:
    CALL Light500ms
    OUT DX, AX
    ROR AX, 1
    CMP AX, 0FFFEH
    JNE L2R  
    JMP INPUT  


Light500ms  PROC  NEAR
        PUSH  CX  
        MOV CX, 60000
Light500ms1:  
        LOOP  Light500ms1  
        POP CX
        RET
Light500ms      ENDP


Light3s PROC  NEAR
        PUSH  CX  
        MOV CX, 6
Light3s1:  
        CALL  Light500ms  
        LOOP  Light3s1
        POP CX
        RET
        ENDP


Light5s PROC  NEAR
        PUSH  CX  
        MOV CX, 10

Light5s1:
	CALL  Light500ms
        LOOP  Light5s1
        POP CX
        RET
        ENDP

START       ENDP        
CODE        ENDS        
        END START
