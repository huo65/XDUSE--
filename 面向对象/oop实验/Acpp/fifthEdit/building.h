#ifndef LIFT_H
#define LIFT_H
#include "Elevator.h"
#include <Windows.h>
#include <WinUser.h>
#include <iostream>
#include "Passenger.h"
#include "global.h"


class Building{
public:
    Building();
    int isTermination();
    void Statistical();
    void randomTime(int* arr);
    //设定全局变量
    Elevator  *E[NUM_ELEVATORS + 1];                //电梯指针
    Passenger     *P[NUM_PEOPLE+1];             //乘客指针


    int Nowtime;
    int K = CAPACITY_PER_ELEVATOR;                   //电梯的最大载客数量         10<K<18
    int N = NUM_PEOPLE;                 //乘客数量                   0<N<1000
    int M = 0;                  //初始等待时间    （分钟）   0<M<10
    int L = 8;                   //乘客的乘梯次数             1<L<10
    int S = S_ELE;                   //电梯速度    单位 :秒/层    1<s<5
    int T = T_PEO;                   //乘客上下电梯的时间         2<T<10



};

void myTimerProc(Building *l, int time);

#endif // LIFT_H
