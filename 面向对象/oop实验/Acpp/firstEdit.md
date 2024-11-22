# 要求

(1)设计实现电梯类、乘客类以及你认为必要的类。
(2)设计实现显示仿真信息的界面：显示每部电梯的状态（空闲或运行中)、运行的方向（上行或者下行)、目前停留的楼层；电梯内乘客的数量，每位乘客要去往的楼层（例如1232表示标识为12的乘客要求去往32层)；当前时间。
(3)假设10部电梯各自独立运行，均可到达每一层。
(4)随机产生每位乘客所要到达的楼层（假设每位乘客只产生一次乘梯请求)，并随机选择一部电梯让其等待。
(5)实现满足该要求的电梯仿真流程，选择电梯将乘客送至其请求的楼层。当所有乘客均到达请求的楼层后，仿真结束。

# 设计说明

> 起初低估了项目的复杂性把所有的类都放在了一个文件，从第二次开始将按照标准格式分开代码

## 逻辑说明

1. 初始化电梯与乘客，随机产生乘客的到达时间以及选择的电梯。初始化后输出结果
2. 开始仿真，以timer变量作为时间单位，监控乘客到达时间接受请求（即代表乘梯行为）
3. 如果接收到请求就进入处理逻辑，整个程序利用**仿并行思想**，在处理请求前后记录时间，在此时间范围内锁住资源防止竞争
4. 每次请求处理完成后输出电梯以及乘客的状态

## 类设计

- 乘客类（记录乘客信息

```c++
 int destFloor;  // 乘客要去的楼层
    int elevatorId; // 乘客所在的电梯编号
    int Id;         // 乘客标识
    int arriveTime; // 仿真开始后的到达电梯时间
    int currentFloor = 1;//乘客所在楼梯
```

- 电梯类（除电梯的基本信息外，将乘梯行为抽象为请求设计处理具体请求的逻辑

```c++
 int id;           // 电梯编号
    int currentFloor; // 当前所在楼层
    ElevatorStatus status = IDLE;
    Direction direction = UP;
    priority_queue<Passenger *, vector<Passenger *>, CompareFloor> requests; // 请求队列（按目标楼层排序，便于处理）
```

### 核心方法

```c++
// 处理请求
    int processRequests(int timer)
    {
        int spend = 0;
        if (timer > endTime[id])
        {
            status = ElevatorStatus::IDLE;
        }
        // 处理请求队列
        if (status == IDLE)
        {
            startTime[id] = timer;
            while (!requests.empty())
            {
                Passenger *passenger = requests.top();
                int destFloor = passenger->getDestFloor();
                // 移动电梯
                cout << "MOVEING~~~" << endl;
                int delta = move(destFloor);//电梯移动方法
                spend += delta * S_ELE;
                passenger->setCurrentFloor(currentFloor);
                cout << "LOG::: passenger " << passenger->getId() << " now is on floor" << passenger->getCurrentFloor();
                cout << " his dest floor is  " << passenger->getDestFloor() << endl;

                if (currentFloor == destFloor)
                {
                    // 乘客到达目标楼层，移除请求和乘客
                    removeRequest(passenger);
                    // removePassenger(passenger);
                    delete passenger;
                    spend += T_PEO;
                }
            }
        }
        else
        {
            if((!requests.empty())){
            cout << "Elevator is busy,please wait util"<< (endTime[id]+currentFloor*S_ELE)<< endl;
            requests.top()->setArriveTime((endTime[id]+currentFloor*S_ELE)/6);
            }
        }
        return spend;
    }
```

```c++
// 移动电梯
    int move(int targetFloor)
    {
        int delta = 0;
        if (!requests.empty())
        {
            status = ElevatorStatus::WORK;
            // 如果请求队列不为空，电梯会移动到最高（小）的请求楼层
            // int targetFloor = requests.top()->getDestFloor();
            if (targetFloor > currentFloor)
            {
                delta = targetFloor - currentFloor;
                direction = Direction::UP;
            }
            else if (targetFloor < currentFloor)
            {
                delta = currentFloor - targetFloor;
                direction = Direction::DOWN;
            }
            else
                delta = 0;
            // status = ElevatorStatus::IDLE;
        }
        currentFloor += delta;
        cout << "LOG::: Elevator " << id << " is now at floor:" << currentFloor << " ElevatorStatus:" << status << " Direction:" << direction << endl;
        return delta;
    }
```





