#include "passenger.h"
#include <iostream>

void Passenger:: printPassenger()
{
    // cout << "passenger " << getId() << " arrive at " << readyTime << " wants to go to floor " << destFloor << ", waiting for elevator " << elevatorId + 1 << "." << endl;
    std::cout << "|  passenger " << getId() << " now is at floor " << getCurrentFloor()<<" his dest floor is "<< destFloor.back()<<"                                   |"<< std::endl;
}