package com.vikramr.algorithms;

/**************************************************************************/
/**
 * @author Vikram Raman
 */
 public class Solution {
	// DO NOT MODIFY THE LIST
	public String largestNumber(final List<Integer> a) {
	    if (a == null || a.isEmpty())
	    {
	        return "0";
	    }

        List<List<Integer>> count = new ArrayList<>();
	    for (int i=0; i < 10; i++)
	    {
	        count.add(new ArrayList<Integer>());
	    }
	    
	    // find first digit of the number
	    for (Integer num : a)
	    {
	        int temp = num;
	        while (num >= 10)
	        {
	             num = num/10;
	        }
	        List<Integer> numList = count.get(num);
	        numList.add(temp);
	    }
	    
	    for (int i=0; i < 10; i++)
	    {
	        List<Integer> numList = count.get(i);
	        if (!numList.isEmpty())
	        {
	            Collections.sort(numList, new Comparator<Integer>() {
	                public int compare(Integer n1, Integer n2)
	                {
	                    if (n1.equals(n2))
	                    {
	                        return 0;
	                    }

	                    int n1Digits = findDigits(n1);
	                    int n2Digits = findDigits(n2);
	                    
	                    long num1 = merge(n1, n2);
	                    long num2 = merge(n2, n1);
	                    
	                    if (num1 > num2)
	                    {
	                        return -1;
	                    }
	                    else if (num1 < num2)
	                    {
	                        return 1;
	                    }
	                    return 0;
	                }
	                
	                public boolean equals(Integer n1, Integer n2)
	                {
	                    return n1.equals(n2);
	                }
	            });
	        }
	    }
	    
	    StringBuffer result = new StringBuffer();
	    
	    boolean foundNonZero = false;
	    for (int i=9; i >=0; i--)
	    {
	        List<Integer> numList = count.get(i);
	        for (Integer num : numList)
	        {
	            if (num > 0)
	            {
	                foundNonZero = true;
	            }
	            result.append(num.toString());
	        }
	    }
	    String output = result.toString();
	    if (!foundNonZero)
	    {
	        return "0";
	    }
	    return output;
	}
	
	private long merge(Integer n1, Integer n2)
	{
	    String num = n1.toString() + n2.toString();
	    return Long.parseLong(num);
	}
	
	private int findDigits(int num)
	{
	    if (num == 0)
	    {
	        return 1;
	    }
	    
	    int digits = 0;
	    while (num > 0)
	    {
	        num = num/10;
	        digits++;
	    }
	    return digits;
	}
}
