#include <bits/stdc++.h>
#include "building.h"
using namespace std;

int main(int argc, char *argv[])
{
    timer = 0;
    Building *build = new Building();
    cout<<"start"<<endl;
    while (1){
//        cout<<"timer: "<<timer<<endl;
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
 * 根据对电梯仿真运行的统计分析结果，要求推测电梯运行的高峰期，给在此阶段内有乘梯需求的乘客提前预警或推荐电梯较为空闲的时间段。
提示：在随机产生乘客所去的楼层时，加上时间因素，在不同时刻随机产生的乘客数量及目的地楼层体现出较大差别，这样比较容易做出预测。
高峰期预警和推荐功能，两个选择一个实现即可。
 */
/**
 normal_distribution 是 c++ 的随机数分布函数之一，它用于生成指定均值和标准差的正态（高斯）分布随机数。
 在使用 normal_distribution 时，需要指定均值和标准差两个参数。
 */

