# coding:utf8
from __future__ import division
from __future__ import print_function
import numpy as np
import csv

class BeamEntry:
    "information about one single beam at specific time-step"

    def __init__(self):
        self.prTotal = 0  # blank and non-blank
        self.prNonBlank = 0  # non-blank
        self.prBlank = 0  # blank
        self.prText = 1  # LM score
        self.lmApplied = False  # flag if LM was already applied to this beam
        self.labeling = ()  # beam-labeling

class CSVReader:
    def __init__(self):
        self.path="/home/kirill/Загрузки/data/test2/probs3.csv"
    def CSVworker(self):
        a=[]
        handle=open(self.path,'r')
        fieldnames = ('1','2','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23',
                      '24','25','26','27','28','29','30','31','32','33')
        reader = csv.reader(handle, fieldnames, delimiter=',')
        for row in reader:
            a.append([float(item) for item in row])
        return a

class TxtReader:
    def __init__(self):
        self.path = "/home/kirill/Загрузки/data/test2/alphabet.txt"
        self.data=u''
    def worker(self):
        handle = open(self.path, 'r')
        self.data = handle.readline()
        return self.data


class BeamState:
    "information about the beams at specific time-step"

    def __init__(self):
        self.entries = {}

    def sort(self):
        "return beam-labelings, sorted by probability"
        beams = [v for (_, v) in self.entries.items()]
        sortedBeams = sorted(beams, reverse=True, key=lambda x: x.prTotal * x.prText)
        return [x.labeling for x in sortedBeams]



class Decoder:
    def addBeam(self,beamState, labeling):
        "add beam if it does not yet exist"
        if labeling not in beamState.entries:
            beamState.entries[labeling] = BeamEntry()

    def ctcBeamSearch(self,mat, classes, beamWidth=25):
        "beam search as described by the paper of Hwang et al. and the paper of Graves et al."

        blankIdx = len(classes)
        maxT, maxC = mat.shape

        # initialise beam state
        last = BeamState()
        labeling = ()
        last.entries[labeling] = BeamEntry()
        last.entries[labeling].prBlank = 1
        last.entries[labeling].prTotal = 1

        # go over all time-steps
        for t in range(maxT):
            curr = BeamState()

            # get beam-labelings of best beams
            bestLabelings = last.sort()[0:beamWidth]

            # go over best beams
            for labeling in bestLabelings:

                # probability of paths ending with a non-blank
                prNonBlank = 0
                # in case of non-empty beam
                if labeling:
                    # probability of paths with repeated last char at the end
                    prNonBlank = last.entries[labeling].prNonBlank * mat[t, labeling[-1]]

                # probability of paths ending with a blank
                prBlank = (last.entries[labeling].prTotal) * mat[t, blankIdx]

                # add beam at current time-step if needed
                self.addBeam(curr, labeling)

                # fill in data
                curr.entries[labeling].labeling = labeling
                curr.entries[labeling].prNonBlank += prNonBlank
                curr.entries[labeling].prBlank += prBlank
                curr.entries[labeling].prTotal += prBlank + prNonBlank
                curr.entries[labeling].prText = last.entries[
                    labeling].prText  # beam-labeling not changed, therefore also LM score unchanged from
                curr.entries[
                    labeling].lmApplied = True  # LM already applied at previous time-step for this beam-labeling

                # extend current beam-labeling
                for c in range(maxC - 1):
                    # add new char to current beam-labeling
                    newLabeling = labeling + (c,)

                    # if new labeling contains duplicate char at the end, only consider paths ending with a blank
                    if labeling and labeling[-1] == c:
                        prNonBlank = mat[t, c] * last.entries[labeling].prBlank
                    else:
                        prNonBlank = mat[t, c] * last.entries[labeling].prTotal

                    # add beam at current time-step if needed
                    self.addBeam(curr, newLabeling)

                    # fill in data
                    curr.entries[newLabeling].labeling = newLabeling
                    curr.entries[newLabeling].prNonBlank += prNonBlank
                    curr.entries[newLabeling].prTotal += prNonBlank

            # set new beam state
            last = curr

        # sort by probability
        bestLabeling = last.sort()[0]  # get most probable labeling
        # map labels to chars
        res = ''
        for l in bestLabeling:
            res += classes[l]

        return res


def testBeamSearch():
    "test decoder"
    kek=CSVReader()
    kek1=TxtReader()
    kek2=Decoder()
    classes = kek1.worker()
    mat = np.array(kek.CSVworker())
    print('Test beam search')
    actual = kek2.ctcBeamSearch(mat, classes)
    print('Actual: "' + actual + '"')



if __name__ == '__main__':
    testBeamSearch()