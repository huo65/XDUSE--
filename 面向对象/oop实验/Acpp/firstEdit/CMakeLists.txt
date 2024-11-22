cmake_minimum_required(VERSION 3.17)
project(test)

set(CMAKE_CXX_STANDARD 14)

add_executable(test main.cpp emum.h passenger.h passenger.cpp elevator.h elevator.cpp global.h output.cpp output.h floor.cpp floor.h)