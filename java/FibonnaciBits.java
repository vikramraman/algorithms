import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/******************************************************************************/
/**
 * The program for counting the numbers with Fibonnaci number of bits
 * between two given 64 bit positive numbers.
 * 
 * @author Vikram Raman
 */
public class FibonnaciBits {
	
	/** the static list of Fibonnaci numbers less than 64 (bits). */
	private static List<Integer> FIB_NUMS = 
		Arrays.asList(1, 2, 3, 5, 8, 13, 21, 34, 55);
	
	/** 64 bit representation of one. */
	private static long ONE = 1;
	
	/**************************************************************************/
	/**
	 * Helper class representing a pair of values.
	 */
	private class Pair<T, U> {
		public T first;
		public U second;
		
		public Pair(T first, U second) {
			this.first = first;
			this.second = second;
		}
	}
	
	/**************************************************************************/
	/**
	 * Gets the count of numbers with Fibonnaci number of bits between two
	 * given 64 bit positive numbers.
	 * 
	 * @param a the first 64 bit number
	 * @param b the second 64 bit number
	 * @return the count of numbers with Fibonnaci number of set bits
	 *         between a (inclusive) and b (exclusive).
	 */
	public long getFibBits(long a, long b) {
		if (a >= b) {
			return 0;
		}
		
		if (a <= 0 || b <= 0) {
			return 0;
		}
		long count = getFibCount(b) - getFibCount(a);
		System.out.println("Total count=" + count);
		return count;
	}
	
	/**************************************************************************/
	/**
	 * Gets the total count of numbers between 1 and the given number with 
	 * Fibonnaci count of bits set.
	 * 
	 * @param a the number to check for
	 * @return the total count of numbers between 1 and a with Fibonnaci
	 *         count of bits set
	 */
	private long getFibCount(long a) {
		long count = 0;
		List<Pair<Long, Long>> parts = partition(1, a);
		for (Pair<Long, Long> pair : parts) {
			
			// first bit position where first & second differ
		    int leftmost = getLeftmostBit(pair.first ^ pair.second);
		    
		    // number of 1's already set to the left of leftmost
		    int setBits = getLeftSetBits(pair.first, leftmost);
		    
			System.out.println("first=" + pair.first + " second=" +
			pair.second + " leftmost=" + leftmost + " count=" + setBits);
		    
		    for (Integer numBits : FIB_NUMS) {
		    	int n = leftmost + 1;
		    	int r = numBits - setBits;
				//System.out.println("numBits=" + numBits + " diff=" + r +
				//" leftmost=" + leftmost);
		    	if (r > 0 && n >= r) {
		    		long combo = getCombinations(n, r); // nCr
		    		count += combo;
		    		//System.out.println("n=" + n + " r=" + r + " combo=" + combo);
		    	}
		    }
		}
		//System.out.println("count=" + count);
		return count;
	}
	
	/**************************************************************************/
	/**
	 * Gets nCr.
	 * 
	 * @param n the total number of bits
	 * @param r the number of bits to set
	 * @return nCr
	 */
	private long getCombinations(int n, int r) {
		long out = 1;
		for (int i=0; i < r; i++) {
			out = out * (n-i) / (i+1);
		}
		return out;
	}
	
	/**************************************************************************/
	/**
	 * Gets the number of bits set to the left of the given bit position.
	 * 
	 * @param n the number to check for
	 * @param position check to the left of this position
	 * @return the number of bits set to the left of the given position
	 */
	private int getLeftSetBits(long n, int position) {
		int count = 0;
		int leftmost = getLeftmostBit(n);
		while (leftmost > position) {
			count++;
			// clear previous leftmost bit
			n ^= (ONE << leftmost); 
			leftmost = getLeftmostBit(n);
		}
		return count;
	}
	
	/**************************************************************************/
	/**
	 * Generates a list of pairs lying between the two numbers by repeatedly
	 * taking the leftmost bit of the larger number and generating a range of
	 * numbers lower than that.
	 * 
	 * For example, given 1 (0001) and 10 (1010):
	 * 0001 - 0111 (1 to 7)
	 * 1000 - 1001 (8 to 9)
	 * 
	 * @param a the smaller number
	 * @param b the larger number
	 * @return list of pairs less between the two numbers
	 */
	private List<Pair<Long, Long>> partition(long a, long b) {
		List<Pair<Long, Long>> parts = new ArrayList<>();
		long temp = b;
		int left = getLeftmostBit(b);
		long start = a;
		long end = (ONE << left) - 1;
		
		while (start <= end) {
			parts.add(new Pair<>(start, end));
			start = invertZeros(end, left);
			if (start == b) {
				break;
			}
			
			// clear prev leftmost bit
			temp ^= (ONE << left);
			left = getLeftmostBit(temp);
			end = invertOnes(b, left);
		}
		return parts;
	}
	
	/**************************************************************************/
	/**
	 * Sets leftmost bit to 1 and clears all bits to its right.
	 * 
	 * @param n the number to invert
	 * @param leftmost the leftmost bit position to manipulate
	 * @return the resulting number after inversion
	 */
	private long invertZeros(long n, int leftmost) {
		long mask = (ONE << leftmost);
		n &= ~(mask-1); // clear all 1's to the right of leftmost
		n |= mask; // set leftmost to 1
		return n;
	}
	
	/**************************************************************************/
	/**
	 * Clears leftmost bit and sets all bits to its right to 1.
	 * 
	 * @param n the number to invert
	 * @param leftmost the leftmost bit position to manipulate
	 * @return the resulting number after inversion
	 */
	private long invertOnes(long n, int leftmost) {
		long mask = (ONE << leftmost);
		n ^= mask; // clear left most bit
		n |= mask - 1; // set all bits to the right to 1
		return n;
	}
	
	/**************************************************************************/
	/**
	 * Gets the leftmost set bit position for the given number.
	 * 
	 * @param n the number to check for leftmost set bit
	 * @return the leftmost set bit
	 */
	private int getLeftmostBit(long n)
	{
	   int left = 0;
	   while (n > 1)
	   {
	      n = n >> 1;
	      left++;
	   }
	   return left;
	}

	/**************************************************************************/
	/**
	 * Main method.
	 * 
	 * @param args the list of arguments
	 */
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("Enter the two numbers:");
			//long a = in.nextLong();
			//long b = in.nextLong();
			
			FibonnaciBits obj = new FibonnaciBits();
			//obj.getFibBits(a, b);
			
			obj.getFibBits(1, (long) (Math.pow(2, 64) - 1));
			// obj.getFibBits(100, (long) (Math.pow(2, 64) - 1));
		} catch (Exception ex) {
			System.out.println("Error handling input=" + ex.getMessage());
		}
	}
}
