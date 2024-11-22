#ifndef TEST_ELEVATOR_H
#define TEST_ELEVATOR_H
#include <vector>
#include <queue>
#include "emum.h"
#include "passenger.h"
#include "floor.h"
// 电梯类
class Elevator
{
private:
    int id;           // 电梯编号
    int currentFloor; // 当前所在楼层
    ElevatorStatus status = IDLE;
    Direction direction = UP;
    int number=0;
    std::vector<Passenger *> havePassengers;// 乘客列表
    int nextFloor;

public:
    Elevator() = default;
    Elevator(int id) : id(id), currentFloor(1),status(IDLE),direction(UP),number(0) {}

    // 获取电梯编号
    int getId() const
    {
        return id;
    }

    // 获取当前所在楼层
    int getCurrentFloor() const
    {
        return currentFloor;
    }

    // 获取当前乘客数量
    int getNumPassengers() const
    {
        return havePassengers.size();
    }

    // 获取当前电梯状态
    ElevatorStatus getElevatorStatus() const
    {
        return status;
    }
    // 获取当前电梯方向
    Direction getDirection() const
    {
        return direction;
    }

    //    乘客上电梯
    void addPassenger(Passenger *passenger){
        if (havePassengers.size()<CAPACITY_PER_ELEVATOR) {
            havePassengers.push_back(passenger);
            passenger->ready();
        }else{
            std::cout<<"elevator is full !!!";
            passenger->updateReadyTime(timer+(currentFloor>(NUM_FLOORS-currentFloor)?currentFloor*T_PEO:(NUM_FLOORS-currentFloor)*T_PEO));
        }
//        TODO 超载逻辑
    }
    //    乘客下电梯
    void removePassenger(Passenger *passenger){
        std::vector<Passenger *> newPassenger;
        while (!havePassengers.empty())
        {
            Passenger *p = havePassengers.back();
            if (p != passenger) {
                newPassenger.push_back(p);
            }
            else {
                this->number--;
                havePassengers.pop_back();
                passenger->update(currentFloor);
            }
        }
        havePassengers = newPassenger;
    }
//    电梯调度逻辑
    void dispatch(Floor *floors,Passenger *passengers){
        if (direction == UP){
            for (int i = currentFloor; i < NUM_FLOORS; ++i) {
                //TODO 比较最近目的地和最近按钮，选择后迭代
                if (floors[i].getUpPassengerDest(id) != 0){
                    Passenger *passenger = &passengers[floors[i].getUpPassengerId(id)];
                    nextFloor = i;
                    addPassenger(passenger);
//                    std::cout<<"up:ele find peo"<<std::endl;
                    printElevator();
                    move(nextFloor);//TODO 将找人逻辑分开
                    nextFloor = floors[i].getUpPassengerDest(id);
//                    std::cout<<"move to dest"<<std::endl;
                    move(nextFloor);
                    removePassenger(passenger);//TODO 以最近按钮
                    floors[i].UpPassengerOK(id);
                }
            }
        }else if (direction == DOWN){
            for (int i = currentFloor; i > 0; --i) {

                if (floors[i].getDownPassengerDest(id) != 0){
                    Passenger *passenger = &passengers[floors[i].getDownPassengerId(id)];
                    nextFloor = i;
                    addPassenger(passenger);
//                    std::cout<<"down:ele find peo"<<std::endl;
                    printElevator();
                    move(nextFloor);
                    nextFloor = floors[i].getDownPassengerDest(id);
//                    std::cout<<"move to dest"<<std::endl;
                    move(nextFloor);
                    removePassenger(passenger);
                    floors[i].downPassengerOK(id);
                }
            }
        }

        status = IDLE;
    }
//    电梯移动逻辑,返回时间花费
    int move(int nextFloor){
        status = WORK;
        int delta = 0;
            if (nextFloor > currentFloor)
            {
                delta = nextFloor - currentFloor;
            }
            else if (nextFloor < currentFloor)
            {
                delta = nextFloor - currentFloor;
            }
            else
                delta += 0;
        currentFloor += delta;

        return delta;

    }
//TODO   电梯方向转换
    void change(int flag){
        if (flag == 1)
            direction = DOWN;
        else
            direction = UP;
    }
    //打印信息
    void printElevator();


};

#endif //TEST_ELEVATOR_H
