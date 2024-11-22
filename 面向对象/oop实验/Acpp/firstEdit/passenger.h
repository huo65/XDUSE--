#ifndef TEST_PASSENGER_H
#define TEST_PASSENGER_H

#include "emum.h"
#include <vector>
#include <cstdlib>
#include "global.h"
#include <iostream>

// 乘客类，保存了每个乘客的目标楼层以及所在电梯编号
class Passenger
{
private:
    int L;  //乘梯次数
    std::vector<int> destFloor;  // 乘客要去的楼层
    int nextFloor;  //下次要去的楼层
    int elevatorId; // 乘客所在的电梯编号
    int Id;         // 乘客标识
    int readyTime; // 到达电梯时间
    int currentFloor = 1;
    PeopleStatus status = WAIT;

public:

    Passenger(int id,int L,std::vector<int> dest, int elevator, int arrTime) : Id(id),L(L) ,destFloor(dest), elevatorId(elevator), readyTime(arrTime) {}
    Passenger() = default;
    // 获取乘客的目标楼层
    std::vector<int> getDestFloor() const
    {
        return destFloor;
    }
    int getNextFloor(){
        nextFloor = destFloor.back();
//        printPassenger();
        return nextFloor;
    }

    // 获取乘客所在的电梯编号
    int getElevatorId() const
    {
        return elevatorId;
    }

    int getId() const
    {
        return Id;
    }
    int getArriveTime() const
    {
        return readyTime;
    }
    int getCurrentFloor()
    {
        return currentFloor;
    }
//    乘坐电梯前改变状态
    void  ready(){
        status = MOVE;
//        printPassenger();
    }
//    每次乘梯完成后更新状态，刷新目标
    void  update(int current){
        currentFloor = current;
        L--;
        if (L>0){
            status = WAIT;
            destFloor.pop_back();
            updateReadyTime(timer);
            std::cout<<"p: "<<Id<<" ok "<<" next at "<<getArriveTime()<< " to "<<destFloor.back()<<std::endl;
        }else{
            status = LEAVE;
            std::cout<<"%%%%run"<<std::endl;
        }
    }
    PeopleStatus getStatus(){
        return status;
    }
    int updateReadyTime(int time){
        readyTime = time+rand()%120+10;
        return readyTime;
    }
    void printPassenger();


};
#endif //TEST_PASSENGER_H
