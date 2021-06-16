package General;

import java.util.HashSet;

/**
 * The challenge is to find all the pairs of two integers in an unsorted array that sum up to a given S.
 * Ex.: if the array is [3, 5, 2, -4, 8, 11] and the sum is 7, your program should return [[11, -4], [2, 5]] because 11 + -4 = 7 and 2 + 5 = 7.
 *
 */
public class TwoSum {
	public static void main(String[] args) {
		//int[] a = { 1 };
		int[] a = { 3, 5, 2, -4, 8, 11, 7 };
		int S = 7;
		//TwoSum.solution01(a, S);
		TwoSum.solution02(a, S);
	}
	
	/**
	 * Time complexity linearithmic: O(n log n).
	 * 
	 * @param a
	 * @param S
	 */
	private static void solution01(int[] a, int S){
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] + a[j] == S) {
					System.out.println(a[i] + ", " + a[j]);
				}
			}
		}
	}
	
	/**
	 * Time complexity linear: O(n).
	 * 
	 * The HashSet offers constant time performance for the basic operations: add, remove, contains and size.
	 * 
	 * @param a
	 * @param S
	 */
	private static void solution02(int[] a, int S) {
		HashSet<Integer> nums = new HashSet<Integer>();
		
		for (int i = 0; i < a.length; i++) {
			nums.add(a[i]);
			
			if (nums.contains(S - a[i])) {
				System.out.println(a[i] + ", " + (S - a[i]));
			}
		}
	}
}