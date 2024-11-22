cmake_minimum_required(VERSION 3.17)
project(secondEdit)

set(CMAKE_CXX_STANDARD 14)

add_executable(secondEdit main.cpp Elevator.cpp Elevator.h Passenger.cpp Passenger.h building.cpp building.h global.h)