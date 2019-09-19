cmake_minimum_required(VERSION 3.13.4)
project(serverSOBES)

set(CMAKE_CXX_STANDARD 11)
get_directory_property(IS_SUBPROJECT PARENT_DIRECTORY)
add_executable(serverSOBES main.cpp)

find_library(PTHREAD_LIBRARY pthread)
target_link_libraries(serverSOBES ${PTHREAD_LIBRARY})

find_package(OpenCV REQUIRED)

include_directories(${OpenCV_INCLUDE_DIRS})

target_link_libraries(serverSOBES ${OpenCV_LIBS})
