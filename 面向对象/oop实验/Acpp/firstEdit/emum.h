#ifndef TEST_EMUM_H
#define TEST_EMUM_H
enum ElevatorStatus
{
    IDLE = 0,
    WORK = 1,
};
enum PeopleStatus
{
    WAIT = 0,
    MOVE = 1,
    LEAVE = -1,
};
enum Direction
{
    UP = 1,
    DOWN = -1
};
#endif //TEST_EMUM_H
