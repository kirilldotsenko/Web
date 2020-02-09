# coding:utf8
from __future__ import division
from __future__ import print_function

from memory_profiler import profile
import pickle
import sys
import argparse
import socket

import numpy as np
import csv
from datetime import datetime
import time

start_time = datetime.now()


class CSVReader:
    def __init__(self,i):
        self.path=i
    def CSVworker(self):
        a=[]
        handle=open(self.path,'r')
        reader = csv.reader(handle, delimiter=',')
        for row in reader:
            if "" in row:
                print("ERROR IN CSV FILE!!!")
                return "ERROR IN CSV FILE!!!"
            a.append([float(item) for item in row])
        handle.close()
        return a

class TxtReader:
    def __init__(self,alphabet):
        self.path = alphabet
        self.data=u''
    def worker(self):
        handle = open(self.path, 'r')
        self.data = handle.readline()
        if self.data=="":
            print("ERROR IN TXT FILE!!!")
            return "ERROR IN TXT FILE!!!"
        handle.close()
        return self.data

#@profile
def testBeamSearch(alphabet,path):
    "test decoder"
    kek = CSVReader(path)
    kek1 = TxtReader(alphabet)
    classes = kek1.worker()
    mat = kek.CSVworker()
    if classes=="ERROR IN TXT FILE" or mat=="ERROR IN CSV FILE!!!":
        return "ERROR"
    datas = []
    datas.append(classes)
    datas.append(np.array(mat))
    obj = pickle.dumps(datas)
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect(('localhost', 9090))
        s.sendall(obj)
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect(('localhost', 9090))
        s.sendall(b"k")
        a=s.recv(1024)
        print(pickle.loads(a))
        return pickle.loads(a)

def createParser():
    parser = argparse.ArgumentParser()
    parser.add_argument('-n', '--name', nargs='+', default=['мир'])

    return parser


if __name__ == '__main__':
    parser = createParser()
    namespace = parser.parse_args(sys.argv[1:])
    if len(namespace.name)<2:
        print("Error! Expected 2 or more variables!")
    for name in namespace.name[1:]:
        testBeamSearch(namespace.name[0],name)
    print(datetime.now() - start_time)
