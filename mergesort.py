# Author: Vikram Raman
# Date: 09-05-2014

import time

def mergeSort(l):
    if l is None:
        return []
    if len(l) < 2:
        return l
    
    mid = len(l)/2
    return merge(mergeSort(l[:mid]), mergeSort(l[mid:]))

def merge(l1, l2):
    len1 = len(l1)
    len2 = len(l2)

    if l1 is None or len1 == 0:
        return l2
    if l2 is None or len2 == 0:
        return l1

    l =[]
    i=j=0
    while (i < len1 and j < len2):
        if l1[i] > l2[j]:
            l.append(l2[j])
            j += 1
        else:
            l.append(l1[i])
            i += 1

    if i < len1:
        l.extend(l1[i:])
    if j < len2:
        l.extend(l2[j:])
    return l    

l = [5, 1, 4, 2, 8, 20, 3, 25, 6, 30, 7]
start_time=time.clock()
print mergeSort(l)
print("--- mergeSort: %s seconds ---" % (time.clock() - start_time))
