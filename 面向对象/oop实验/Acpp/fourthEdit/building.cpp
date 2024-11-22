#include <ctime>
#include "building.h"

using namespace std;

void myTimerProc(Building *l, int time)
{
    l->Nowtime = l->Nowtime + 1;
    //电梯行动
    for (int i = 0; i < NUM_ELEVATORS; i++)
    {
        l->E[i]->nowtime = time;
        l->E[i]->action();
        l->E[i]->printEle();

    }
    // 乘客活动
    for (int i = 0; i < NUM_PEOPLE; i++)
    {
        l->P[i]->nowtime = time;
        l->P[i]->action();
        l->P[i]->printPassenger();
    }
}

/*初始化电梯系统*/
Building::Building()
{

    cout << "~~~Init elevators~~~" << endl;
    for (int i = 0; i < NUM_ELEVATORS; ++i)
    {
        E[i] =  new Elevator(S, K, T, i + 1);
//        cout<<"HIHI"<<E[i]->Id<<endl;
    }

    cout << "~~~Init passengers~~~" << endl;
    for (int i = 0; i < NUM_PEOPLE; i++)
    {
        P[i] =  new Passenger(M, L, E,i+1);//初始化乘客
    }

}
    int Building::isTermination(){

            for (int i = 0; i < NUM_PEOPLE; ++i) {
                if (P[i]->getstatus() != PeoStatus::OVER)
                    break;
                if (i == NUM_PEOPLE -1) {
                    cout << "win" << endl;
                    return 1;
                }
            }
        return 0;
    }

    void Building::Statistical() {
        cout<<"Statistical results: "<<endl;
        cout<<"Elevator work time: "<<endl;
        for (int i = 0; i < NUM_ELEVATORS; ++i)
        {
            cout<<"Ele"<<i+1<<" total "<<E[i]->Getbusy()<<" ";
        }
        cout<<endl;
        cout<<"Elevator idle time: "<<endl;
        for (int i = 0; i < NUM_ELEVATORS; ++i) {
            cout<<"Ele: "<<i+1<<" total "<<E[i]->Getvacant()<<" ";
        }
        cout<<endl;
        cout<<"People wait time: "<<endl;
        for (int i = 0; i < NUM_PEOPLE; ++i) {
            cout<<"Peo: "<<i+1<<" total "<<P[i]->GetTotal()<<" ";
            if (i == 9) cout<<endl;
        }
        cout<<endl;
    }
