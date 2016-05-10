# Author: Vikram Raman
# Date: 08-15-2015

import time

# Given a rope with length n, how to cut the rope into m parts with length n[0], n[1], ..., n[m-1],
# in order to get the maximal product of n[0]*n[1]* ... *n[m-1]? 
# We have to cut once at least. Additionally, the length of the whole length of the rope, 
# as well as the length of each part, are in integer value.

# For example, if the length of the rope is 8, 
# the maximal product of the part lengths is 18. 
# In order to get the maximal product, 
# the rope is cut into three parts with lengths 2, 3, and 3 respectively.

# immediate thoughts: this is a dynamic programming knapsack kind of problem

def cutrope(l):
    d = [0, 1]

    for i in range(2, l+1):
        maxVal = 0
        for j in range(1, i):
            maxVal = max(j * d[i-j], j * (i-j), maxVal)
        d.append(maxVal)
    print d

l = 8
start_time = time.clock()
cutrope(l)
print("--- %s seconds ---" % (time.clock() - start_time))
