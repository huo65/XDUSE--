#include "output.h"
#include "global.h"
#include <iostream>
void ShowInfo(Elevator *elevators, Passenger *passengers)
{
    std::cout << "--------------------------------------INFO------------------------------------" << std::endl;
    for (int i = 0; i < NUM_ELEVATORS; ++i)
    {
        elevators[i].printElevator();
    }
    for (size_t i = 0; i < NUM_PEOPLE; i++)
    {
        passengers[i].printPassenger();
//        std::cout<<"check arr:"<<passengers[i].getArriveTime()<<std::endl;
    }
    std::cout << "-----------------------------------------------------------------------------" << std::endl;
}