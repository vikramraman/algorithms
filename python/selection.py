# Author: Vikram Raman
# Date: 09-20-2015

import time
import random

def kth(l, k):

    length = len(l)
    while (length > 1):
        mid = length/2

        lmid = l[mid]

        l0 = []
        l1 = []
        l2 = []

        for i in range(0, length):
            val = l[i]
            if val < lmid:
                l0.append(val)
            elif val > lmid:
                l2.append(val)
            else:
                l1.append(val)
        
        len0 = len(l0)
        len1 = len(l1)
        len2 = len(l2)

        if k <= len0:
            l = l0
        elif k <= (len0 + len1):
            return l1[len0 + len1 - k]
        else:
            l = l2
            k = k - (len0 + len1)
        length = len(l)

    if length == 1:
        return l[0]

def sorted(l,k):
    l.sort()
    return l[k-1]

l = [int(1000*random.random()) for i in xrange(1,1000000)]
k = 5000
