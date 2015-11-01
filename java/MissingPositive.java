package com.vikramr.algorithms;

/**************************************************************************/
/**
 * @author Vikram Raman
 */
public class Solution {

	public int firstMissingPositive(ArrayList<Integer> a) {
	    if (a == null || a.isEmpty())
	    {
	        return 1;
	    }
	    
	    int start = 0;
	    int end = a.size();
	    
	    while (start < end)
	    {
	        int num = a.get(start);
	        
	        if (num < 1 || num > end || num == start + 1)
	        {
	            start++;
	            continue;
	        }
	        
	        if (num >= 1 && num <= end)
	        {
	            int temp = a.get(num-1);
	            if (temp == num)
	            {
	                start++;
	                continue;
	            }
	            a.set(num-1, num);
	            a.set(start, temp);
	        }
	    }
	    
	    int num = 1;
	    for (int i=0; i < end; i++)
	    {
	        if (a.get(i) == num)
	        {
	            num++;
	        }
	        else
	        {
	            break;
	        }
	    }
	    return num;
	}
}
