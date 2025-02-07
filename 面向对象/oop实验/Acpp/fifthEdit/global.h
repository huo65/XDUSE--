
#ifndef TEST_GLOBAL_H
#define TEST_GLOBAL_H
const int NUM_FLOORS = 40;             // 建筑楼层数
const int NUM_ELEVATORS = 10;          // 电梯数量
const int CAPACITY_PER_ELEVATOR = 10; // 每部电梯的载客量
const int NUM_PEOPLE = 40;

const int S_ELE = 1; // 电梯运行速度
const int T_PEO = 1; // 每人上下电梯速度
const int PEAK = 25; //TODO 指定高峰
const int variance = 6;


static int timer;
static int M = 5; // 有N位乘客(0<N<1000)在M分钟(0<M<10)内随机地到达该国际会展中心的1层，开始乘梯活动。





enum PeoStatus
{
    TAKE,     // 乘坐
    LEAVE,    // 离开
    WAIT,     // 等待
    OVER      // 停止乘梯活动
};
enum EleStatus
{
    STOP,    // 停止
    UP,      // 向上运行
    DOWN,    // 向下运行
    UPSTOP,  // 上行过程中的暂停
    DOWNSTOP // 下行过程中的暂停
};
#endif //TEST_GLOBAL_H
