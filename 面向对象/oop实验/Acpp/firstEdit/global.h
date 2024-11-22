
#ifndef TEST_GLOBAL_H
#define TEST_GLOBAL_H
const int NUM_FLOORS = 10;             // 建筑楼层数
const int NUM_ELEVATORS = 3;          // 电梯数量
const int CAPACITY_PER_ELEVATOR = 10; // 每部电梯的载客量
const int NUM_PEOPLE = 2;

const int S_ELE = 3; // 电梯运行速度
const int T_PEO = 5; // 每人上下电梯速度
static int timer;
static int M = 5; // 有N位乘客(0<N<1000)在M分钟(0<M<10)内随机地到达该国际会展中心的1层，开始乘梯活动。
static int startTime[4] = {0,}, endTime[4] = {0,};

#endif //TEST_GLOBAL_H
