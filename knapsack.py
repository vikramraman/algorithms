# Author: Vikram Raman
# Date: 08-23-2015

import time

#knapsack problem

# given max weight W and weights w1....wn and values v1...vn, maximize the items in a bag


def knapsack(input, maxWeight):
    k=[0]

    # for w = 1 to W
    # k(w) = max(k(W-wi) + vi))

    for w in range(1, maxWeight+1):
        maxVal = 0
        for i in range(0, len(input)):
            wi,vi = input[i]
            if wi <= w:
                maxVal = max(maxVal, k[w-wi] + vi)
                #temp = k[w-wi] + vi
                #if temp > max:
                #    max = temp
        k.append(maxVal)
    print k
    return k[maxWeight]

def knapsackworep(input, maxWeight):
    k = [[0 for i in range(0,len(input)+1)]]

    for i in range(1,maxWeight+1):
        k.append([0])
    
    # k(w,j) = max ( k(w-wj, j-1) + vj, k(w,j-1) )
    for w in range(1, maxWeight+1):
        maxVal = 0
        for j in range(1, len(input)+1):
            wj, vj = input[j-1]

            if wj > w:
                maxVal = k[w][j-1]
            else:    
                temp1 = k[w-wj][j-1] + vj
                temp2 = k[w][j-1]
                maxVal = max(temp1, temp2, maxVal)
            k[w].append(maxVal)
    print "norep=",k
    return k[maxWeight][len(input)]

input=[(6,30), (3,14), (4,16), (2,9)]
print "input=",input
start_time = time.clock()
print knapsack(input, 10)
print knapsack(input, 6)
print knapsackworep(input, 10)
print knapsackworep(input, 6)
print("--- %s seconds ---" % (time.clock() - start_time))
