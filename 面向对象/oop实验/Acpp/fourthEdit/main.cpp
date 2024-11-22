#include <bits/stdc++.h>
#include "building.h"
using namespace std;
int main(int argc, char *argv[])
{
    timer = 0;
    Building *build = new Building();
    cout<<"start"<<endl;
    while (1){
        cout<<"timer: "<<timer<<endl;
        myTimerProc(build, timer);
        timer++;

        if (timer>M*100){
            if (build->isTermination()) {
                build->Statistical();
                cout<<"over"<<endl;
                return 0;
            }
        }
        if(timer>M*999){
            cout<<"time out!!!!"<<endl;
            return 1;
        }
    }


}
/**
1、联动模式：当业主按下等待键时，两台电梯中离业主所在楼层最近的那一台会响应并停至业主所在楼层。
2、非联动模式：当业主按下等待键时，两台电梯都会响应并停至业主所在楼层。
 */
/**
 1. 统计各电梯的运行和空闲时间
 2. 统计各乘客发出要求后的等待时间
 */
