#ifndef TEST_FLOOR_H
#define TEST_FLOOR_H
#include "global.h"
#include "passenger.h"
//#include "elevator.h"
#include <vector>
#include <iostream>
class Floor {
    int whichFloor;
    int up =0;
    int down =0;
    int upButton[NUM_ELEVATORS]= {0,};
    std::vector<Passenger *> buttonOfPassenger[NUM_ELEVATORS];//装载对应乘客的数组
    int upPassengerId[NUM_ELEVATORS]= {0,};//TODO 实现电梯内部按钮; buttonOfPassenger实现为优先队列
    int downButton[NUM_ELEVATORS]= {0,};
    int downPassengerId[NUM_ELEVATORS]= {0,};
//    Elevator elevator;
    std::vector<Passenger *> waitPassengers;


public:
    Floor() = default;
    Floor(int floor): whichFloor(floor){}

    void UpPassengerOK(int eleId){
        up--;
        upButton[eleId] = 0;
        buttonOfPassenger[eleId] = static_cast<const std::vector<Passenger *, std::allocator<Passenger *>>>(NULL);
    }
    void downPassengerOK(int eleId){
        down--;
        downButton[eleId] = 0;
        downPassengerId[eleId] = 0;
    }


    int getUpPassengerDest(int eleId){
        return upButton[eleId];
    }
    int getUpPassengerId(int eleId){
        return upPassengerId[eleId];
    }
    int getDownPassengerDest(int eleId){
        return downButton[eleId];
    }
    int getDownPassengerId(int eleId){
        return downPassengerId[eleId];
    }

    void updateWaitQueue(Passenger *passenger){
        waitPassengers.push_back(passenger);
    }
    int updateButton(int eleId){
//        int time = timer;
        int flag =0;
        for (auto p : waitPassengers) {
//            std::cout<<p->getCurrentFloor()<<" "<<p->getNextFloor();
            if (p->getArriveTime() == timer){
                if (p->getCurrentFloor() < p->getNextFloor()){
                    up++;
                    upButton[eleId] = p->getNextFloor();
                    upPassengerId[eleId] = p->getId();

                } else{
                    down++;
                    downButton[eleId] = p->getNextFloor();
                    downPassengerId[eleId] = p->getId();

                }
            }

        }
        if (up == 0 && down != 0){
            flag = 1;
//            std::cout<<"change to down"<<std::endl;
        }
        if (down == 0 && up != 0){
            flag = -1;
//            std::cout<<"change to up"<<std::endl;
        }

        return flag;
    }
};


#endif //TEST_FLOOR_H
