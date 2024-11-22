#include "Passenger.h"
#include "global.h"

#include <iostream>
Passenger::Passenger()
{
}

Passenger::Passenger(int M, int L, Elevator  *E[], int id,int arrtime)
{
    Id = id;
    NextFloor = (rand() % (NUM_FLOORS - 1)) + 1;            //随机生成目标楼层
    status = LEAVE;                                     //状态为离开状态
    NowFloor = 1;                                       //初始楼层为1
    Total = 0;                                          //总计等待时间
    times = (rand() % (L - 1)) + 5;                                     //总乘梯次数
    nowtime = timer;
    group = 0;
    NextTime = arrtime;                  //设定下一次乘梯的时间 TODO 设置乘坐电梯时间
    for (int i = 0; i < NUM_ELEVATORS; i++)
    {
        e[i] = E[i];
    }
    Select(group, which);
}


Passenger::~Passenger()
{
}
void Passenger::printPassenger() {
    std::cout<<"Peo: "<<Id<<" now "<<nowtime<<" at floor: "<<GetNowFloor()<<" to floor: "<<GetNextFloor()<<" status: "<<GetStatus()
    <<" currentEle:  "<< e[which]->Id
    <<" nextTime: "<<NextTime <<" at group: "<<group<<std::endl;
}
void Passenger::JudgeWarning(){// TODO 预警判断
    if (abs(nowtime - PEAK)<variance){
        std::cout<<"Warning Peak, Pay attention to avoiding"<<std::endl;
    }
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
                        JudgeWarning();
                        status = TAKE;

                        flag ++;
                        if (flag == 1){
                            printPassenger();
                        }
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
                if (NowFloor==1)
                status = OVER;
                else{
                    int temp = ((rand() % NUM_FLOORS-1)+1);
                    while (temp == NowFloor)    //如果取出的数是当前楼层，则重新取随机数，直到不同
                    {
                        temp = ((rand() % NUM_FLOORS-1)+1);
                    }
                    NextFloor = temp;
                }
            }

            if (nowtime - NextTime > 120){
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

            break;
        case  TAKE:

            NowFloor = e[which]->NowFloor;        //更新当前乘坐电梯的楼层
            if (NextFloor <= 0) NextFloor =1;
            //当前状态为乘坐，则查看是否到达目的楼层 ，将状态置为 “离开”或“停止”
            if (NowFloor == NextFloor)
            {
                times--;
                switch (times)
                {
                    case 0:
                        if (NowFloor==1) {
                            status = OVER;
                        }else{
                            times++;
                            NextFloor = 1;
                            NextTime = (nowtime +((rand() % 111) + 15));
                            e[which]->people++;
                        }
                        break;
                    case 1:
                        status = LEAVE;
                        NextFloor = 1;
                        NextTime = (nowtime +((rand() % 111) + 15));
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
                Select(group, which);
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

int Passenger::GetWhich()
{
    return which + 1;
}

int Passenger::GetTimes()
{
    return times;
}
int Passenger::GetGroup(){
    return group;
}


int Passenger::GetTotal()
{
    return Total;
}

void  Passenger::Select(int & group, int & which)
{
    int select[10];                  //设定决策权重数组
    for (int i = 0; i < 10; i++)      //初始化
    {
        select[i] = 0;
    }
    //group:0
    select[0] = 1;
    select[1] = 1;
    //group:1
    //如果当前楼层与目标楼层为1,25-40
    if (((25 <= NowFloor && NowFloor <= 40) || NowFloor == 1) && ((25 <= NextFloor && NextFloor <= 40) || NextFloor == 1))
    {
        select[2] += 1;
        select[3] = 1;
    }
    //group:2
    //如果当前楼层与目标楼层为1-25
    if ((1 <= NowFloor && NowFloor <= 25) && (1 <= NextFloor && NextFloor <= 25))
    {
        select[4] += 1;
        select[5] = 1;
    }
    //group:3
    //如果当前楼层与目标楼层为偶数
    if ((NowFloor % 2 == 0 || NowFloor == 1) && (NextFloor % 2 == 0 || NextFloor == 1))
    {
        select[6] += 1;
        select[7] = 1;
    }
    //如果当前楼层与目标楼层为奇数
    if ((NowFloor % 2 == 1) && (NextFloor % 2 == 1))
    {
        select[8] += 1;
        select[9] = 1;
    }


    // 进一步选择
    int direction = NextFloor - NowFloor;  //设定乘梯方向  >0 向上 <0向下
    for (int i = 0; i < 10; i++)
    {
        //在满足条件的电梯组中选择
        if (select[i])
        {
            //如果乘客要去上面
            if (direction > 0)
            {
                //如果电梯停
                if (e[i]->NextFloor - e[i]->NowFloor == 0)
                {
                    select[i] = select[i] + 40 - abs((NowFloor - e[i]->NowFloor));   //距离越近，权重越大
                }
                    //如果电梯向上
                else if (e[i]->NextFloor - e[i]->NowFloor > 0)
                {
                    //并且电梯位置低于乘客位置
                    if (e[i]->NowFloor <= NowFloor)
                    {
                        select[i] = select[i] + 40 - abs((NowFloor - e[i]->NowFloor));   //距离越近，权重越大
                    }
                    else   //电梯位置高于乘客
                    {
                        select[i] = select[i] - abs((NowFloor - e[i]->NowFloor));
                    }
                }
                    //如果电梯向下
                else
                {
                    //并且电梯位置高于乘客位置
                    if (e[i]->NowFloor >= NowFloor)
                    {
                        select[i] = select[i] - abs((NowFloor - e[i]->NowFloor));   //距离越近，权重越大
                    }
                    else   //电梯位置低于乘客
                    {
                        select[i] = select[i] + abs((NowFloor - e[i]->NowFloor));
                    }
                }
            }
            else   //如果乘客要去下面
            {
                //如果电梯停
                if (e[i]->NextFloor - e[i]->NowFloor == 0)
                {
                    select[i] = select[i] + 40 - abs((NowFloor - e[i]->NowFloor));   //距离越近，权重越大
                }
                    //如果电梯向上
                else if (e[i]->NextFloor - e[i]->NowFloor > 0)
                {
                    //并且电梯位置低于乘客位置
                    if (e[i]->NowFloor <= NowFloor)
                    {
                        select[i] = select[i] - abs((NowFloor - e[i]->NowFloor));   //距离越近，权重越大
                    }
                    else   //电梯位置高于乘客
                    {
                        select[i] = select[i] + abs((NowFloor - e[i]->NowFloor));
                    }
                }
                    //如果电梯向下
                else
                {
                    //并且电梯位置高于乘客位置
                    if (e[i]->NowFloor >= NowFloor)
                    {
                        select[i] = select[i] + 40 - abs((NowFloor - e[i]->NowFloor));   //距离越近，权重越大
                    }
                    else   //电梯位置低于乘客
                    {
                        select[i] = select[i] - abs((NowFloor - e[i]->NowFloor));
                    }
                }

            }
            if (e[i]->GetStatu() == Elevator::STOP){
                select[i] +=20;
            }
        }
    }

    //在select数组中取最大值的下标，即为最优选择电梯组
    int max = 9;
    for (int i = 9; i >= 0; i--)
    {
        if (select[i] != 0 && (select[max] < select[i]))
        {
            max = i;
        }
    }
    which = max;
    group = (max / 2);


}


