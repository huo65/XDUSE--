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

        if (timer > MINUTE * 60){
            if (build->isTermination()) {
                cout<<"over"<<endl;
                return 0;
            }
        }
        if(timer > MINUTE * 999999){
            cout<<"time out!!!!"<<endl;
            return 1;
        }
    }


}
