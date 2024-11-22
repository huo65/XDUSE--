
#ifndef TEST_GLOBAL_H
#define TEST_GLOBAL_H
const int NUM_FLOORS = 40;             // 建筑楼层数
const int NUM_ELEVATORS = 10;          // 电梯数量
const int CAPACITY_PER_ELEVATOR = 10; // 每部电梯的载客量
const int NUM_PEOPLE = 20;

const int S_ELE = 1; // 电梯运行速度
const int T_PEO = 1; // 每人上下电梯速度
static int timer;
static int MINUTE = 5; // 有N位乘客(0<N<1000)在M分钟(0<MINUTE<10)内随机地到达该国际会展中心的1层，开始乘梯活动。

enum PeoStatus
{
    TAKE,     // 乘坐
    LEAVE,    // 离开
    WAIT,     // 等待
    OVER      // 停止乘梯活动
};
#endif //TEST_GLOBAL_H
