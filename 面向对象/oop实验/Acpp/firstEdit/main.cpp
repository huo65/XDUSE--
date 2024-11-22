#include <iostream>
#include <ctime>

#include "emum.h"
#include "passenger.h"
#include "elevator.h"
#include "output.h"
#include "global.h"
using namespace std;
int main() {
    srand(time(NULL)); // 设置随机数种子
     Elevator elevators[NUM_ELEVATORS+1];
     Passenger passengers[NUM_PEOPLE+1];
     Floor floors [NUM_FLOORS+1];
    // 创建电梯对象
    cout << "~~~Init elevators~~~" << endl;
    for (int id = 1; id <= NUM_ELEVATORS; ++id)
    {
        elevators[id] = Elevator(id);
//        elevators[id].printElevator();
    }
    cout << "~~~Init passengers~~~" << endl;
    // 创建乘客对象
    for (int id = 1; id <= NUM_PEOPLE; id++)
    {
        // 随机生成一个乘客目标楼层和电梯编号以及到达时间
        int L = 1;

        std::vector<int> dest;
        for (int j = 0; j < L; ++j) {
            dest.push_back((rand() % NUM_FLOORS) + 1);
        }
        int elevatorId = rand() % NUM_ELEVATORS+1;
        int arriveTime = rand() % M;
        Passenger passenger = Passenger(id,L, dest, elevatorId, arriveTime*60);

        passengers[id] = passenger;
        // 输出乘客信息
        cout << "|  passenger " << (passenger).getId() << " arrive at " << arriveTime  << " waiting for elevator " << elevatorId + 1 << ".    |" << endl;
        cout << " wants to go to floors: " <<endl;
        for (int j = L-1; j >= 0 ; --j) {
            cout<<dest[j]<<" ";
        }
        cout<<endl;
    }
    // 初始化floor类
    for (int f = 1; f <= NUM_FLOORS; ++f) {
        floors[f] = Floor(f);
    }

    cout << "~~~~~~~~~~~~~~~~~~start simulation~~~~~~~~~~~~~~~~~~" << endl;
    // 仿真过程
    timer = 0;
    int passengerId = 0;
    int elevatorId = 0;
    int currentFloor = 0;
    int fault =0;
        while (1)
        {
            for (int i = 0; i < NUM_PEOPLE; i++)
            {

                if (timer == passengers[i+1].getArriveTime())
                {
                    fault =1;
                    passengerId = i+1;
                    elevatorId = passengers[passengerId].getElevatorId();
                    cout << "!!! AT TIMER:" << timer ;
                    Passenger &passenger = passengers[passengerId];
                    currentFloor = passenger.getCurrentFloor();
                    cout<<" passenger: "<<passengerId<<" is at floor "<< currentFloor<<"waiting ele:"<<elevatorId<< endl;
                    floors[currentFloor].updateWaitQueue(&passenger);//TODO bug记录
                    Elevator elevator = elevators[elevatorId];
                    int change = floors[currentFloor].updateButton(elevatorId);
                    if (change != 0)elevators[elevatorId].change(change);


//                    for (int j = 0; j < NUM_PEOPLE; ++j) {
//                        passengers[j+1].printPassenger();
//                    }
                }
            }
            if (fault)
                elevators[elevatorId].dispatch(floors, passengers);
            fault =0;

            timer++;


            if (timer>M*60){

                for (int i = 0; i < NUM_PEOPLE; ++i) {
                    if (passengers[i+1].getStatus() != LEAVE)
                        break;
                    if (i == NUM_PEOPLE -1)
                        return 0;
                }

            }
//            cout<<"time:"<<timer<<endl;
        }
    cout << "~~~~~~~~~~~~~~~~~~~simulation over~~~~~~~~~~~~~~~~~~~" << endl;
    return 0;
}
