package com.vikramr.algorithms;

/**************************************************************************/
/**
 * @author Vikram Raman
 */

private class ListNode {
    public int val;
    public ListNode next;
    ListNode(int x) { val = x; next = null; }
}
 
public class Solution {
	public ListNode insertionSortList(ListNode a) {
	    if (a == null || a.next == null)
	    {
	        return a;
	    }
	    
	    ListNode head = null;
	    
	    while (a != null)
	    {
	        if (head == null)
	        {
	            head = a;
	            a = a.next;
	            head.next = null;
	        }
	        else
	        {
	            ListNode prev = null;
	            ListNode p = head;
	            int val = a.val;
	            
	            while (p != null && val > p.val)
	            {
	                prev = p;
	                p = p.next;
	            }
	            
	            if (prev == null)
	            {
	                prev = a;
	                a = a.next;
	                prev.next = p;
	                head = prev;
	            }
	            else
	            {
	                prev.next = a;
	                a = a.next;
    	            prev.next.next = p;
	            }
	        }
	    }
	    return head;
	}
}
