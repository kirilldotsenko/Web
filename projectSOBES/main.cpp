#include <iostream> // for standard I/O
#include <string>   // for strings
#include <iomanip>  // for controlling float print precision
#include <fstream>

#include <stdio.h>
#include <unistd.h>

#include <opencv2/core.hpp>     // Basic OpenCV structures (cv::Mat, Scalar)
#include <opencv2/videoio.hpp>
#include <opencv2/highgui.hpp>  // OpenCV window I/O

#include <mutex>
#include <thread>

#include <sys/socket.h>
#include <netinet/in.h>

using namespace std;
using namespace cv;
mutex mtx;

class MyData
{
public:
    MyData() : A(0), X(0), id() {}
public:   // Data Members
    int A;
    double X;
    string id;

    void write(FileStorage& fs) const                        //Write serialization for this class
    {
        fs << "{" << "A" << A << "X" << X << "id" << id << "}";
    }
    void read(const FileNode& node)                          //Read serialization for this class
    {
        A = (int)node["A"];
        X = (double)node["X"];
        id = (string)node["id"];
    }
};

void write(FileStorage& fs, const std::string&, const MyData& x)
{
    x.write(fs);
}
void read(const FileNode& node, MyData& x, const MyData& default_value = MyData())
{
    if(node.empty())
        x = default_value;
    else
        x.read(node);
}

void func(Mat x){
    std::lock_guard<std::mutex> lock(mtx);
    int sock;
    struct sockaddr_in addr;

    sock = socket(AF_INET, SOCK_STREAM, 0);
    if(sock < 0)
    {
        perror("socket");
        exit(1);
    }

    addr.sin_family = AF_INET;
    addr.sin_port = htons(3425); // или любой другой порт...
    addr.sin_addr.s_addr = htonl(INADDR_LOOPBACK);
    if(connect(sock, (struct sockaddr *)&addr, sizeof(addr)) < 0)
    {
        perror("connect");
        exit(2);
    }
    size_t length = 0;
    char *buff = 0;
    FileStorage fs("/home/kirill/CLionProjects/projectSOBES/kek.xml", FileStorage::WRITE);
    fs<<"MyData"<<x;
    fs.release();
    ifstream file("/home/kirill/CLionProjects/projectSOBES/kek.xml",
                ios::out | ios::binary);
    if (file.is_open()) {
        file.seekg(0, ios::end);
        length = file.tellg();
        file.seekg(0, ios::beg);
        buff = new char[1 + length];
        file.read(buff, length);
        buff[length] = 0;
    }
    send(sock, buff, length, 0);
    close(sock);
    file.close();
}

int main()
{
    const string sourceReference ="/home/kirill/CLionProjects/projectSOBES/_-COwlqqErDbY.mkv";

    int frameNum = -1;          // Frame counter
    VideoCapture captRefrnc(sourceReference);
    if (!captRefrnc.isOpened())
    {
        cout  << "Could not open reference " << sourceReference << endl;
        return -1;
    }


    const char* WIN_RF = "Reference";
    

    Mat frameReference;

    for(;;) //Show the image captured in the window and repeat
    {
        captRefrnc >> frameReference;

        if (frameReference.empty())
        {
            cout << " < < <  Game over!  > > > ";
            break;
        }
        ++frameNum;
        if(frameNum==0 || frameNum%2==0){
            thread thr(func,frameReference);
            thr.join();
        }
        if(frameNum%2!=0){
            thread thr(func,frameReference);
            thr.join();
        }
    }
    return 0;
}

