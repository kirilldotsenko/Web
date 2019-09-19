
#include <sys/socket.h>
#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <fstream>

#include <fcntl.h>


#include <opencv2/core.hpp>     // Basic OpenCV structures (cv::Mat, Scalar)

#include <opencv2/highgui.hpp>  // OpenCV window I/O

using namespace std;
using namespace cv;


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


int main()
{

    int sock, listener;
    struct sockaddr_in addr;
    char buf[1024];
    int bytes_read;

    listener = socket(AF_INET, SOCK_STREAM, 0);
    if(listener < 0)
    {
        perror("socket");
        exit(1);
    }

    addr.sin_family = AF_INET;
    addr.sin_port = htons(3425);
    addr.sin_addr.s_addr = htonl(INADDR_ANY);
    if(bind(listener, (struct sockaddr *)&addr, sizeof(addr)) < 0)
    {
        perror("bind");
        exit(2);
    }

    listen(listener, 1);
    while(1) {
        sock = accept(listener, NULL, NULL);
        if (sock < 0) {
            perror("accept");
            exit(3);
        }
        ofstream file("/home/kirill/CLionProjects/main/1.xml");
        while(true) {
            int res;
            res = recv(sock, buf, sizeof(buf), 0);
            if (res == -1) {
                if (errno == EAGAIN || errno == EWOULDBLOCK) {
                    break;
                } else {
                    break;
                }
            }
            if (res == 0) {
                break;
            } else {
                file.write(buf, res);

            }
        }
        file.close();
        const char* WIN_RF = "Reference";
        FileStorage fs("/home/kirill/CLionProjects/main/1.xml", FileStorage::READ);
        Mat frameRef;
        fs["MyData"]>>frameRef;
        imshow(WIN_RF, frameRef);



        char c = (char)waitKey(30);
        if (c == 10) break;
        frameRef.release();
    }

    return 0;
}