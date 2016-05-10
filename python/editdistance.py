# Author: Vikram Raman
# Date: 09-12-2015

import time

# edit distance between two strings
# e(i,j) = min (1 + e(i-1,j) | 1 + e(i,j-1) | diff(i,j) + e(i-1,j-1))

def editdistance(s1, s2):
    m = 0 if s1 is None else len(s1)
    n = 0 if s2 is None else len(s2)

    if m == 0:
        return n
    elif n == 0:
        return m

    l = [[i for i in range(0,n+1)]]

    for i in range(1,m+1):
        l.append([i])

    for i in range(1,m+1):
        for j in range(1,n+1):
            minimum = min(1 + l[i-1][j], 1 + l[i][j-1], diff(s1,s2,i,j) + l[i-1][j-1])
            l[i].append(minimum)
    return l[m][n]        

def diff (s1, s2, i, j):
    return s1[i-1] != s2[j-1]

s1 = "exponential"
s2 = "polynomial"
print "s1=%s, s2=%s" % (s1,s2)
start_time = time.clock()
distance=editdistance(s1, s2)
print "distance=%d" % (distance)
print("--- %s seconds ---" % (time.clock() - start_time))
print editdistance("foo", "bar")
