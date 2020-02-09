import pickle
import socket
BUFFER_SIZE = 4096


class Decoder:
    def __init__(self):
        self.dict = {'prTotal': 0,
                    'prNonBlank': 0,
                    'prBlank': 0,
                    'prText': 1,
                    'lmApplied': False,
                    'labeling': ()
                    }

    def addBeam(self,beamState, labeling):
        "add beam if it does not yet exist"
        if labeling not in beamState:
            beamState[labeling] = self.dict.copy()

    def sort(self,kek):
        "return beam-labelings, sorted by probability"
        beams = [v for (_, v) in kek.items()]
        sortedBeams = sorted(beams, reverse=True, key=lambda x: x['prTotal'] * x['prText'])
        return [x['labeling'] for x in sortedBeams]

    def ctcBeamSearch(self,mat, classes, beamWidth=25):
        "beam search as described by the paper of Hwang et al. and the paper of Graves et al."

        blankIdx = len(classes)
        maxT, maxC = mat.shape

        # initialise beam state


        last={}
        labeling = ()
        last[labeling] = self.dict.copy()
        last[labeling]['prBlank'] = 1
        last[labeling]['prTotal'] = 1
        # go over all time-steps
        for t in range(maxT):
            curr = {}
            # get beam-labelings of best beams
            bestLabelings = self.sort(last)[0:beamWidth]
            # go over best beams
            for labeling in bestLabelings:
                # probability of paths ending with a non-blank
                prNonBlank = 0
                # in case of non-empty beam
                if labeling:
                    # probability of paths with repeated last char at the end
                    prNonBlank = last[labeling]['prNonBlank'] * mat[t, labeling[-1]]
                # probability of paths ending with a blank
                prBlank = (last[labeling]['prTotal']) * mat[t, blankIdx]
                # add beam at current time-step if needed
                self.addBeam(curr, labeling)
                # fill in data
                curr[labeling]['labeling'] = labeling
                curr[labeling]['prNonBlank'] += prNonBlank
                curr[labeling]['prBlank'] += prBlank
                curr[labeling]['prTotal'] += prBlank + prNonBlank
                curr[labeling]['prText'] = last[labeling]['prText']  # beam-labeling not changed, therefore also LM score unchanged from
                curr[labeling]['lmApplied'] = True  # LM already applied at previous time-step for this beam-labeling

                # extend current beam-labeling
                for c in range(maxC - 1):
                    # add new char to current beam-labeling
                    newLabeling = labeling + (c,)
                    # if new labeling contains duplicate char at the end, only consider paths ending with a blank
                    if labeling and labeling[-1] == c:
                        prNonBlank = mat[t, c] * last[labeling]['prBlank']
                    else:
                        prNonBlank = mat[t, c] * last[labeling]['prTotal']

                    # add beam at current time-step if needed
                    self.addBeam(curr, newLabeling)

                    # fill in data
                    curr[newLabeling]['labeling'] = newLabeling
                    curr[newLabeling]['prNonBlank'] += prNonBlank
                    curr[newLabeling]['prTotal'] += prNonBlank


            # set new beam state
            last = curr

        # sort by probability
        bestLabeling = self.sort(last)[0]  # get most probable labeling
        # map labels to chars
        res = ''
        for l in bestLabeling:
            res += classes[l]

        return res


with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind(('127.0.0.1', 9090))
    s.listen(1)
    while True:
        conn, addr = s.accept()
        with conn:
            print('Connected by', addr)
            all_data = bytearray()
            while True:
                data = conn.recv(1024)
                all_data += data
                if not data:
                    break
            obj = pickle.loads(all_data)
            kek = Decoder()
            res = kek.ctcBeamSearch(obj[1], obj[0])
            conn, addr = s.accept()
            data = conn.recv(1024)
            conn.sendall(pickle.dumps(res))

