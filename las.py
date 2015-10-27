# Author: Vikram Raman
# Date: 08-15-2015

# Longest Arithmetic Sequence

import time

def las(l):
    d=[]
    d.append([l[0]])

    for i in range(1, len(l)):
        maxVal = []
        for j in range(0, i):
            if (l[j] < l[i]) and (len(d[j]) >= len(maxVal)):
                maxVal = list(d[j])
        maxVal.append(l[i])
        d.append(maxVal)
        print i, d
    print d

l = [1, 6, 3, 5, 9, 7]

print l
start_time = time.clock()
las(l)
print("--- %s seconds ---" % (time.clock() - start_time))
