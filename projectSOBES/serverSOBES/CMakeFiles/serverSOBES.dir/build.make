# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.13

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/kirill/CLionProjects/projectSOBES

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/kirill/CLionProjects/projectSOBES

# Include any dependencies generated for this target.
include serverSOBES/CMakeFiles/serverSOBES.dir/depend.make

# Include the progress variables for this target.
include serverSOBES/CMakeFiles/serverSOBES.dir/progress.make

# Include the compile flags for this target's objects.
include serverSOBES/CMakeFiles/serverSOBES.dir/flags.make

serverSOBES/CMakeFiles/serverSOBES.dir/main.cpp.o: serverSOBES/CMakeFiles/serverSOBES.dir/flags.make
serverSOBES/CMakeFiles/serverSOBES.dir/main.cpp.o: serverSOBES/main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/kirill/CLionProjects/projectSOBES/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object serverSOBES/CMakeFiles/serverSOBES.dir/main.cpp.o"
	cd /home/kirill/CLionProjects/projectSOBES/serverSOBES && /usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/serverSOBES.dir/main.cpp.o -c /home/kirill/CLionProjects/projectSOBES/serverSOBES/main.cpp

serverSOBES/CMakeFiles/serverSOBES.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/serverSOBES.dir/main.cpp.i"
	cd /home/kirill/CLionProjects/projectSOBES/serverSOBES && /usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/kirill/CLionProjects/projectSOBES/serverSOBES/main.cpp > CMakeFiles/serverSOBES.dir/main.cpp.i

serverSOBES/CMakeFiles/serverSOBES.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/serverSOBES.dir/main.cpp.s"
	cd /home/kirill/CLionProjects/projectSOBES/serverSOBES && /usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/kirill/CLionProjects/projectSOBES/serverSOBES/main.cpp -o CMakeFiles/serverSOBES.dir/main.cpp.s

# Object files for target serverSOBES
serverSOBES_OBJECTS = \
"CMakeFiles/serverSOBES.dir/main.cpp.o"

# External object files for target serverSOBES
serverSOBES_EXTERNAL_OBJECTS =

serverSOBES/serverSOBES: serverSOBES/CMakeFiles/serverSOBES.dir/main.cpp.o
serverSOBES/serverSOBES: serverSOBES/CMakeFiles/serverSOBES.dir/build.make
serverSOBES/serverSOBES: /usr/lib/x86_64-linux-gnu/libpthread.so
serverSOBES/serverSOBES: /usr/local/lib/libopencv_gapi.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_stitching.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_aruco.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_bgsegm.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_bioinspired.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_ccalib.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_dnn_objdetect.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_dpm.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_face.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_freetype.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_fuzzy.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_hdf.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_hfs.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_img_hash.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_line_descriptor.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_quality.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_reg.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_rgbd.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_saliency.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_stereo.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_structured_light.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_superres.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_surface_matching.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_tracking.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_videostab.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_xfeatures2d.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_xobjdetect.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_xphoto.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_shape.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_highgui.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_datasets.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_plot.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_text.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_dnn.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_ml.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_phase_unwrapping.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_optflow.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_ximgproc.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_video.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_videoio.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_imgcodecs.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_objdetect.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_calib3d.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_features2d.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_flann.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_photo.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_imgproc.so.4.1.2
serverSOBES/serverSOBES: /usr/local/lib/libopencv_core.so.4.1.2
serverSOBES/serverSOBES: serverSOBES/CMakeFiles/serverSOBES.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/kirill/CLionProjects/projectSOBES/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable serverSOBES"
	cd /home/kirill/CLionProjects/projectSOBES/serverSOBES && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/serverSOBES.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
serverSOBES/CMakeFiles/serverSOBES.dir/build: serverSOBES/serverSOBES

.PHONY : serverSOBES/CMakeFiles/serverSOBES.dir/build

serverSOBES/CMakeFiles/serverSOBES.dir/clean:
	cd /home/kirill/CLionProjects/projectSOBES/serverSOBES && $(CMAKE_COMMAND) -P CMakeFiles/serverSOBES.dir/cmake_clean.cmake
.PHONY : serverSOBES/CMakeFiles/serverSOBES.dir/clean

serverSOBES/CMakeFiles/serverSOBES.dir/depend:
	cd /home/kirill/CLionProjects/projectSOBES && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/kirill/CLionProjects/projectSOBES /home/kirill/CLionProjects/projectSOBES/serverSOBES /home/kirill/CLionProjects/projectSOBES /home/kirill/CLionProjects/projectSOBES/serverSOBES /home/kirill/CLionProjects/projectSOBES/serverSOBES/CMakeFiles/serverSOBES.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : serverSOBES/CMakeFiles/serverSOBES.dir/depend

