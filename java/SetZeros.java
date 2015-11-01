package com.vikramr.algorithms;

/**************************************************************************/
/**
 * @author Vikram Raman
 */
public class Solution {
	public void setZeroes(ArrayList<ArrayList<Integer>> a) {
	    if (a == null || a.isEmpty())
	    {
	        return;
	    }
	    
	    int rows = a.size();
	    int cols = a.get(0).size();

	    int row = 0;	    
	    int col = 0;
	    
	    Set<Integer> colSet = new HashSet<>();
	    
	    while (row < rows)
	    {
	        boolean foundZero = false;
	        List<Integer> rowList = a.get(row);
	        
	        while (col < cols)
	        {
	            int num = rowList.get(col);
	            if (num == 0)
	            {
	                colSet.add(col);
	                if (!foundZero)
	                {
	                    foundZero = true;
	                    setZeros(rowList, col);
	                }
	            }
	            else
	            {
	                if (foundZero)
	                {
	                    rowList.set(col, 0);
	                }
	            }
	            col++;
	        }
	        row++;
	        col = 0;
	    }
	    
	    // TODO: this iteration can be avoided by flipping zeros to -1
	    // and then letting the next row scan flip the upper -1's to zero
	    for (int i=0; i < rows; i++)
	    {
	        List<Integer> rowList = a.get(i);
	        for (Integer j : colSet)
	        {
	            rowList.set(j, 0);
	        }
	    }
	}
	
	private void setZeros(List<Integer> l, int numZeros)
	{
	    for (int i=0; i < numZeros; i++)
	    {
	        l.set(i, 0);
	    }
	}
}
