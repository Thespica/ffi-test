cmake_minimum_required(VERSION 3.1)
project(garffitest)

SET(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -O3 -g -std=c++17 -Wall")

file(GLOB SOURCES "target/generated-sources/annotations/*.cc" "target/generated-test-sources/test-annotations/*.cc")

set(LIBNAME "garffitest")

set(THREADS_PREFER_PTHREAD_FLAG ON)
set(JAVA_AWT_LIBRARY NotNeeded)
set(JAVA_AWT_INCLUDE_PATH NotNeeded)
find_package(JNI REQUIRED)
include_directories(SYSTEM ${JAVA_INCLUDE_PATH})
include_directories(SYSTEM ${JAVA_INCLUDE_PATH2})
include_directories("src/main/native")
include_directories("src/test/native")

find_package(gar REQUIRED)
include_directories(SYSTEM ${GAR_INCLUDE_DIR})

#set(GAR_SRC_DIR "graphar-src")
# Add FlatBuffers directly to our build. This defines the `flatbuffers` target.
#add_subdirectory(${GAR_SRC_DIR}
#                 ${CMAKE_CURRENT_BINARY_DIR}/build
#                 EXCLUDE_FROM_ALL)

# Now simply link against flatbuffers as needed to your already declared target.
# The flatbuffers target carry header search path automatically if CMake > 2.8.11.
#target_link_libraries(own_project_target PRIVATE flatbuffers)

add_library(${LIBNAME} SHARED ${SOURCES})
target_link_libraries(${LIBNAME} ${CMAKE_JNI_LINKER_FLAGS} gar)
find_library(GAR_LIBRARY gar)
target_link_libraries(garffitest ${GAR_LIBRARY})

set_target_properties(${LIBNAME} PROPERTIES LINKER_LANGUAGE CXX)

add_custom_command(TARGET ${LIBNAME}
                   POST_BUILD
                   COMMAND ${CMAKE_COMMAND} -E copy $<TARGET_FILE:${LIBNAME}> "${CMAKE_CURRENT_SOURCE_DIR}/target/classes/")

add_custom_command(TARGET ${LIBNAME}
                   POST_BUILD
                   COMMAND ${CMAKE_COMMAND} -E make_directory ${CMAKE_CURRENT_SOURCE_DIR}/target/native/bitcode
                   COMMAND ${CMAKE_COMMAND} -E copy_if_different $<TARGET_OBJECTS:${LIBNAME}> ${CMAKE_CURRENT_SOURCE_DIR}/target/native/bitcode COMMAND_EXPAND_LISTS)
