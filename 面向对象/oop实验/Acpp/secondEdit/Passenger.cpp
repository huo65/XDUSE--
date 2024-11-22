#include "Passenger.h"
#include "global.h"

#include <iostream>
Passenger::Passenger()
{
}

Passenger::Passenger(int M, int L, Elevator  *E[], int id)
{
    Id = id;
    NextFloor = (rand() % (NUM_FLOORS - 1)) + 1;            //随机生成目标楼层
    status = LEAVE;                                     //状态为离开状态
    NowFloor = 1;                                       //初始楼层为1
    WaitTime = (rand() % (M * 60 - 1)) + 1;               //乘客初次等待时间
    Total = 0;                                          //总计等待时间
    times = L + 1;                                      //总乘梯次数
    nowtime = timer;
    NextTime = (nowtime + WaitTime);                   //设定下一次乘梯的时间
    for (int i = 0; i < NUM_ELEVATORS; i++)
    {
        e[i] = E[i];
    }
    Select( which);
}


Passenger::~Passenger()
{
}
void Passenger::printPassenger() {
    std::cout<<"Peo: "<<Id<<" now "<<nowtime<<" at floor: "<<GetNowFloor()<<" to floor: "<<GetNextFloor()<<" status: "<<GetStatus()
    <<" currentEle:  "<< e[which]->Id
    <<" nextTime: "<<NextTime <<std::endl;
}

void Passenger::action()
{

    switch (status)
    {

        case  WAIT:
            //当前状态为等待，则查看电梯是否到达当前楼层，将状态置为 “乘坐”
            if (e[which]->people < e[which]->capacity)
            {   //如果电梯人数未满
                if (e[which]->NowFloor == NowFloor)
                {   //选定的电梯楼层与乘客所在楼层一致
                    if (((NextFloor - NowFloor) > 0 && (e[which]->NextFloor - e[which]->NowFloor) >= 0) ||
                        ((NextFloor - NowFloor) < 0 && (e[which]->NextFloor - e[which]->NowFloor) <= 0))
                    {   //并且方向一致
                        status = TAKE;
                        //统计等待
                        Total = Total + (nowtime - NextTime);
                        //设定乘坐电梯的目标楼层
                        if ((NextFloor - NowFloor) > 0)
                        {
                            e[which]->SetInsideUp(NextFloor, 1);   //设置目标楼层
                            e[which]->SetUp(NowFloor, -1);         //撤销呼叫
                            e[which]->people++;
                        }
                        else if ((NextFloor - NowFloor) < 0)
                        {
                            e[which]->SetInsideDown(NextFloor, 1);  //设置目标楼层
                            e[which]->SetDown(NowFloor, -1);        //撤销呼叫
                            e[which]->people++;
                        }
                        else
                        {

                        }
                    }
                }
            }
            if (NowFloor == NextFloor){
                status = OVER;
            }
//            if ((nowtime - NextTime) % 60 == 0)
//            {
//                //如果等待时间每超过一分钟 重新选择电梯
//                //首先撤销上次的选择记录
//                int  old_which = which;
//                if ((NextFloor - NowFloor) > 0)
//                {
//
//                    e[old_which]->SetUp(NowFloor, -1);
//                }
//                else
//                {
//                    e[old_which]->SetDown(NowFloor, -1);  ;
//                }
//                Select( which);  //如果电梯满员 重新选择电梯
//                //重新设置新的电梯记录
//                if ((NextFloor - NowFloor) > 0)
//                {
//
//                    e[which]->SetUp(NowFloor, 1);    //Up[this->NowFloor] += 1;
//                }
//                else
//                {
//                    e[which]->SetDown(NowFloor, 1);  //Down[this->NowFloor] += 1;
//                }
//            }

            break;
        case  TAKE:
            NowFloor = e[which]->NowFloor;        //更新当前乘坐电梯的楼层
            //当前状态为乘坐，则查看是否到达目的楼层 ，将状态置为 “离开”或“停止”
            if (NowFloor == NextFloor)
            {
                times--;
                switch (times)
                {
                    case 0:
                        status = OVER;
                        break;
                    case 1:
                        status = LEAVE;
                        NextFloor = 1;
                        NextTime = (nowtime + 10);
                        break;
                    default:
                        status = LEAVE;
                        //设定新的目标楼层
                        int temp = ((rand() % NUM_FLOORS-1)+1);
                        while (temp == NowFloor)    //如果取出的数是当前楼层，则重新取随机数，直到不同
                        {
                            temp = ((rand() % NUM_FLOORS-1)+1);
                        }
                        NextFloor = temp;
                        NextTime = (nowtime + ((rand() % 111) + 10));     //生成下一次乘梯时间,随机停留10-120秒
                        break;
                }
                e[which]->people--;

            }
            break;
        case LEAVE:
            //当前状态为离开，则查看是否到下次开始乘梯的时间 ，将状态置为 “等待”

            if (nowtime == NextTime)
            {
                //改变状态
                status = WAIT;
                //选择乘坐的电梯组,与电梯
                Select( which);
                //设置电梯停靠楼层
                if ((NextFloor - NowFloor) > 0)
                {

                    e[which]->SetUp(NowFloor, 1);    //Up[this->NowFloor] += 1;
                }
                else
                {
                    e[which]->SetDown(NowFloor, 1);  //Down[this->NowFloor] += 1;
                }

            }
            break;
        case OVER:
            break;
    }

}
char * Passenger::GetStatus()
{
    switch (status)
    {

        case  WAIT:
            strcpy_s(str, "WAIT");
            break;
        case  TAKE:
            strcpy_s(str, "TAKE");
            break;
        case LEAVE:
            strcpy_s(str, "LEAVE");
            break;
        case OVER:
            strcpy_s(str, "OVER");
            break;
    }
    return str;
}

int Passenger::getstatus()
{
    return status;
}

int Passenger::GetNextFloor()
{
    return NextFloor;
}

int Passenger::GetNowFloor()
{
    return NowFloor;
}

int Passenger::Getwhich()
{
    return which + 1;
}

int Passenger::Gettimes()
{
    return times;
}



int Passenger::GetTotal()
{
    return Total;
}

void  Passenger::Select (int & which)
{
    which = rand()%NUM_ELEVATORS;
//    std::cout<<"select:"<<which<<std::endl;

}

