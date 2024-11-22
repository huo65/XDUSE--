

![image-20230603232026657](C:/Users/86132/AppData/Roaming/Typora/typora-user-images/image-20230603232026657.png)







![image-20230603232015127](C:/Users/86132/AppData/Roaming/Typora/typora-user-images/image-20230603232015127.png)







| **学号**     | 21009201175  | **姓名**     | 霍志杰     |
| ------------ | ------------ | ------------ | ---------- |
| **班级**     | **oop-02班** | **任课教师** | **张淑平** |
| **实验名称** | **电梯仿真** |              |            |
| **实验日期** | **2023春**   |              |            |











# 实验题目及要求

- **第一次上机**

  (1)设计实现电梯类、乘客类以及你认为必要的类。
  (2)设计实现显示仿真信息的界面：显示每部电梯的状态（空闲或运行中)、运行的方向（上行或者下行)、目前停留的楼层；电梯内乘客的数量，每位乘客要去往的楼层（例如1232表示标识为12的乘客要求去往32层)；当前时间。
  (3)假设10部电梯各自独立运行，均可到达每一层。
  (4)随机产生每位乘客所要到达的楼层（假设每位乘客只产生一次乘梯请求)，并随机选择一部电梯让其等待。
  (5)实现满足该要求的电梯仿真流程，选择电梯将乘客送至其请求的楼层。当所有乘客均到达请求的楼层后，仿真结束。

- **第二次上机**

  在完成第一次上机内容的基础上，修改乘客乘梯请求规则：

  （1） 随机产生每位乘客初次所要到达的楼层，并随机选择一部电梯让其等待；

  （2） 每位乘客乘坐电梯达到指定楼层后，随机地停留10-120秒后，再随机地去往另一楼层，依此类推。当每位乘客乘坐过L次（每人的L值不同，在产生乘客时随机地在1-10之间确定）电梯后，第L+1次为下至底层并结束乘梯行为。到所有乘客结束乘梯行为时，本次仿真结束。

- **第三次上机**

  在完成电梯仿真系统第一、二次要求基础上，修改电梯运行规则：

  （1）E0、E1：可到达每层；

  （2）E2、E3：可到达1、25~40层。

  （3）E4、E5：可到达1~25层。

  （4）E6、E7：可到达1、2~40层中的偶数层。

  （5）E8、E9：可到达1~39层中的奇数层。

  （6）按照相同规则运行的两部电梯之间是联动的。

  （7）随机产生每位乘客初次所要到达的楼层，选择一部合适的电梯让其等待。

- **第四次上机**

  在完成电梯仿真系统前3次要求的基础上，增加电梯运行统计分析功能。

  在仿真结束后，对电梯在仿真过程中的运行情况进行统计分析：统计各梯的运行与空闲时间；统计各乘客发出乘梯要求后的等待时间；仿真结束后显示这些时间。

- **第五次上机**

  根据对电梯仿真运行的统计分析结果，推测电梯运行的高峰期，给在此阶段内有乘梯需求的乘客提前预警或推荐电梯较为空闲的时间段。高峰期预警和推荐功能，两个选择一个实现即可。

# 分析和设计说明

这个项目由以下文件组成：

- building.cpp //统一管理电梯和乘客，因为每一个时间单位都要去有处理将其对应的行为封装起来方便处理
  - building.h
- Elevator.cpp//电梯类
  - Elevator.h
- Passenger.cpp//乘客类
  - Passenger.h
- global.cpp//全局变量类，用于管理全局共享的变量
  - global.h
- main.cpp//主类，控制执行

针对每次上机的改进重点：

##### 第二次：

```cpp
 case  TAKE://乘客的乘梯状态
            NowFloor = e[which]->NowFloor;        //更新当前乘坐电梯的楼层
            //当前状态为乘坐，则查看是否到达目的楼层 ，将状态置为 “离开”或“停止”
            if (NowFloor == NextFloor)
            {
                times--;//到达一个目的地后将乘梯次数-1，并做出判断
                switch (times)
                {
                    case 0://完成所有乘梯活动
                        status = OVER;//乘客完成坐电梯的标志
                        break;
                    case 1://最后一次回到一楼
                        status = LEAVE;
                        NextFloor = 1;
                        NextTime = (nowtime +((rand() % 111) + 10));//生成下一次乘梯时间
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
                        NextTime = (nowtime + ((rand() % 111) + 10));//生成下一次乘梯时间,随机停留10-120秒
                        break;
                }
                e[which]->people--;

            }
            break;
```

##### 第三次：

```cpp
void  Passenger::Select(int & group, int & which)
{
    int select[10];                  //设定决策权重数组
    for (int i = 0; i < 10; i++)      //初始化
    {
        select[i] = 0;
    }
    //选择电梯组
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
           //根据电梯距离的远近与方向为决策数组赋权值
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
```

##### 第四次：

```cpp
 case STOP:                         //电梯状态为停止

            if (NextFloor != NowFloor)
            {
                if (NextFloor - NowFloor > 0)
                {
                    status = UP;
                    vacant += (nowtime - Over);// 统计等待时间
                    Start = nowtime;
                }
                else……
 case UPSTOP:					//电梯状态为在上升时暂停
            if (NextStart == nowtime)
            {
                    busy += (nowtime - Start);// 统计运行时间
                    Over = nowtime;
                }
            }
case  WAIT: //当前状态为等待，则查看电梯是否到达当前楼层，将状态置为 “乘坐”
            if (……) 
              status = TAKE;
              Total = Total + (nowtime - NextTime); // 统计等待
```

```cpp
 void Building::Statistical() {//统计输出
        cout<<"Statistical results: "<<endl;
        cout<<"Elevator work time: "<<endl;
        for (int i = 0; i < NUM_ELEVATORS; ++i)
        {
            cout<<"Ele"<<i+1<<" total "<<E[i]->Getbusy()<<" ";
        }
        cout<<endl;
        cout<<"Elevator idle time: "<<endl;
        for (int i = 0; i < NUM_ELEVATORS; ++i) {
            cout<<"Ele: "<<i+1<<" total "<<E[i]->Getvacant()<<" ";
        }
        cout<<endl;
        cout<<"People wait time: "<<endl;
        for (int i = 0; i < NUM_PEOPLE; ++i) {
            cout<<"Peo: "<<i+1<<" total "<<P[i]->GetTotal()<<" ";
            if (i == 9) cout<<endl;
        }
        cout<<endl;
    }
```

##### 第五次

```cpp
/**
 normal_distribution 是 c++ 的随机数分布函数之一，它用于生成指定均值和标准差的正态（高斯）分布随机数。
 在使用 normal_distribution 时，需要指定均值和标准差两个参数。
 */
void Building::randomTime(int*  timeArr){  //TODO 正态随机生成
        unsigned seed = chrono::system_clock::now().time_since_epoch().count();
        default_random_engine generator(seed);
        normal_distribution<double> dist(PEAK, variance);//指定均值和方差
        for (int i = 0; i < NUM_PEOPLE; ++i) {

            timeArr[i] = dist(generator);
            cout <<"arrTime:"<<timeArr[i] << endl;
        }
    }
void Passenger::JudgeWarning(){// 预警判断
    if (abs(nowtime - PEAK)<variance){
        std::cout<<"Warning Peak, Pay attention to avoiding"<<std::endl;
    }
```



# 测试情况

预警功能：

![image-20230605151956180](oop%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.assets/image-20230605151956180.png)

最终结果：

![image-20230605151852209](oop%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.assets/image-20230605151852209.png)

# 主要问题

整个实验最大的难点在于第二次实验，因为第一次的情况非常固定，不需要太复杂的设计就能实现出来，这就会为第二次实验埋雷。

我就是因为第一次实验写的缺少扩展性，在尝试过n次修该后决定重构整个项目。

##### 中断机制的不可行性分析

在第一次我使用了类似于操作系统中断机制的思想

```c++
      while (1)
        {
            for (int i = 0; i < NUM_PEOPLE; i++)
            {
				//遍历乘客的行动时间，只要到达相应时间代表产生中断
                if (timer == passengers[i+1].getArriveTime())
                {
                    fault =1;
                    ……
                }
            }
      if (fault)elevators[elevatorId].dispatch(floors, passengers);//然后根据有无事件发生做出处理
            fault =0;

            timer++;
      }
```

这样的方式极大的提高了程序的效率，在不需要处理事件的时候不浪费额外的资源

可惜这个思路对于第二次要求捉襟见肘，因为乘客的乘坐次数增加就会导致事件随时间随机的产生，在处理一个请求的时间内总会有意想不到的请求发生。

> 例如当电梯向上时经过一层楼的时间段内刚好有人也要上楼，在现有的架构下难以处理类似的事件

考虑过在乘客类和电梯类中间加一层中介，用类似消息队列的思想处理请求，但效果仍不理想，每次电梯调度的工作量还是很大。

所以说中断机制是不可行性，就算最终考虑并解决所有的情况，最后系统的复杂度并不亚于暴力遍历解决问题。

##### 最终方案

- 在每一个时间单位都会有意想不到的事件发生的背景下，系统只能时刻去监听并处理请求。
- 电梯于乘客都是状态驱动：即电梯或者乘客的行为是严格根据其在这一时刻的状态决定的

```cpp
while (1){
        everTimerProc(build, timer);//在每个时间单位都做处理
        timer++;
        if (timer>M*100){//在经过一定时间后检查是否结束
            if (build->isTermination()) {
                build->Statistical();
                cout<<"over"<<endl;
                return 0;
            }
        }
        if(timer>M*999){
            cout<<"time out!!!!"<<endl;
            return 1;
        }
    }

void everTimerProc(Building *l, int time)
{
    l->Nowtime = l->Nowtime + 1;
    //电梯行动
    for (int i = 0; i < NUM_ELEVATORS; i++)
    {
        l->E[i]->nowtime = time;
        l->E[i]->action();

    }
    // 乘客活动
    for (int i = 0; i < NUM_PEOPLE; i++)
    {
        l->P[i]->nowtime = time;
        l->P[i]->action();
    }
}
```



# 实验总结

本次实验的难度总体来说还是挺高的，要考虑很多的特殊情况，这就要求整个项目的设计都能适应特殊情况的处理。

相比于对面向对象编程思想，我认为通过这个程序我更多的学到了复杂项目的构建。这个项目表面看起没那么复杂，**但是要真正的去分析真实场景下的所有情况的话，**（其实忽略很多情况的话也挺容易）它随时间变化的非常灵活，越写越发现需要考虑的情况越多，就像《人月神话》中形容的”焦油坑“一样。所以在以后写代码的时候一定要多考虑代码的可扩展性，先仔细思考需求再写代码，选择合适的架构可以节约很长的试错时间。

总之这次实验有挑战也有收获。对c++的掌握以及对编程的理解都有很大进步。