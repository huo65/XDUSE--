#include "elevator.h"
#include "global.h"
#include "output.h"
#include <iostream>

void Elevator:: printElevator()
{
    std::cout<<"@@@ elevator "<<id<<" now is at floor: "<<currentFloor<<" direction: "<<direction<<" have peo: ";
    // 输出电梯信息
    for(auto p:havePassengers){
        std::cout<<"passenger: "<<p->getId()<<" dest floor: "<<p->getNextFloor()<<" ";
    }
    std::cout<<std::endl;
//    std::cout << "|  Elevator " << id << " is now at floor:" << currentFloor << " ElevatorStatus:" << status << " Direction:" << direction << "number of people:" << getNumPassengers() <<". |"<< std::endl;
}
