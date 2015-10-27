# Author: Vikram Raman
# Date: 08-08-2015

import time

def maxGifts(l):

    length = len(l)
    len0 = len(l[0])

    m = [[]]
    for i in range(0, len0):
        if i == 0:
            m[0].append(l[0][i])
        else:
            m[0].append(l[0][i] + m[0][i-1])
    print m


    for i in range(1, length):
        m.append([ m[i-1][0] + l[i][0] ])
    print m


    for i in  range(1, len0):
        for j in range(1, length):
            m[i].append( max(m[i-1][j] + l[i][j], m[i][j-1] + l[i][j]) )
    print m

l = [[1,10,3,8], [12,2,9,6], [5,7,4,11], [3,7,16,5]]

start_time = time.clock()
max=maxGifts(l)
print("--- %s seconds ---" % (time.clock() - start_time))
