mystack segment stack
mystack ends

data segment
    str       db '123456$'
    string    db 'huozhijie21009201175$'
    tip       db 'Input character (for q to exit:$'
    InputChar db 'InputChar:$'
    asc       db 'ascii:$'
data ends

code segment
                          assume cs:code, ds:data
    start:                
                          mov    ax, data
                          mov    ds, ax
                          mov    es, ax

    ; 输出姓名学号
                          lea    dx, string
                          mov    ah, 9
                          int    21h

                          mov    dl, 0Dh
                          mov    ah, 2
                          int    21h

                          mov    dl, 0Ah
                          mov    ah, 2
                          int    21h

                          lea    dx, str
                          mov    ah, 9
                          int    21h

                          mov    si, 0                    ; 初始化字符串索引为0
    convert_str_to_ascii: 
                          mov    al,   str[si]            ; 获取字符串中的字符
                          cmp    al, '$'                  ; 判断是否到达字符串结尾
                          je     display_str_end
                          push   ax
                          call   display_ascii
                          pop    ax
                          inc    si                       ; 增加字符串索引
                          jmp    convert_str_to_ascii

    display_str_end:      
                          mov    dl, 0Dh
                          mov    ah, 2
                          int    21h

                          mov    dl, 0Ah
                          mov    ah, 2
                          int    21h


    ; 输入
                          lea    dx, tip
                          mov    ah, 9
                          int    21h

    convert_char_to_ascii:
    ; 换行
                          mov    dl, 0Dh
                          mov    ah, 2
                          int    21h

                          mov    dl, 0Ah
                          mov    ah, 2
                          int    21h

    ; 获取输入的字符
                          mov    ah, 1
                          int    21h

                          cmp    al, 'q'
                          je     ending
                          cmp    al, 'Q'
                          je     ending

    ; ASC 转换
                          push   ax
                          call   display_ascii
                          pop    ax

                          jmp    convert_char_to_ascii

    display_ascii:        
                          push   ax
                          push   bx
                          push   cx
                          push   dx

                          mov    bl, al
                          mov    bh, al

    ; 回车+换行
                          mov    dl, 0Dh
                          mov    ah, 2
                          int    21h

                          mov    dl, 0Ah
                          mov    ah, 2
                          int    21h

                          lea    dx, InputChar
                          mov    ah, 9
                          int    21h

                          mov    dl, bh
                          mov    ah, 2
                          int    21h

                          mov    dl, 9
                          mov    ah, 2
                          int    21h

                          lea    dx, asc
                          mov    ah, 9
                          int    21h

                          and    bl, 0F0h
                          mov    cl, 4
                          shr    bl, cl

                          cmp    bl, 9
                          ja     display_ascii_alph1
    display_ascii_numb1:  
                          add    bl, '0'
                          mov    dl, bl
                          mov    ah, 2
                          int    21h
                          jmp    display_ascii_low4

    display_ascii_alph1:  
                          add    bl, 37h
                          mov    dl, bl
                          mov    ah, 2
                          int    21h

    display_ascii_low4:   
                          and    bh, 0Fh
                          cmp    bh, 9
                          ja     display_ascii_alph2
    display_ascii_numb2:  
                          add    bh, '0'
                          mov    dl, bh
                          mov    ah, 2
                          int    21h
                          jmp    display_ascii_end

    display_ascii_alph2:  
                          add    bh, 37h
                          mov    dl, bh
                          mov    ah, 2
                          int    21h

    display_ascii_end:    
                          pop    dx
                          pop    cx
                          pop    bx
                          pop    ax

                          ret

    ending:               
    ; 回车+换行
                          mov    dl, 0Dh
                          mov    ah, 2
                          int    21h

                          mov    dl, 0Ah
                          mov    ah, 2
                          int    21h

    ; 终止程序
                          mov    ax, 4c00h
                          int    21h

code ends
end start
