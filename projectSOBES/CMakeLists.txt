cmake_minimum_required(VERSION 3.13.4)
project(projectSOBES)

set(CMAKE_CXX_STANDARD 11)

add_executable(projectSOBES main.cpp)

find_package(OpenCV REQUIRED)
include_directories(${OpenCV_INCLUDE_DIRS})
include_directories(serverSOBES)
add_subdirectory(serverSOBES)
include(serverSOBES/cmake_install.cmake)

target_link_libraries(projectSOBES ${OpenCV_LIBS})

find_library(PTHREAD_LIBRARY pthread)

target_link_libraries(projectSOBES ${PTHREAD_LIBRARY})


