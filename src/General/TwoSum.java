package General;

import java.util.HashSet;

/**
 * The challenge is to find all the pairs of two integers in an unsorted array that sum up to a given S.
 * Ex.: if the array is [3, 5, 2, -4, 8, 11] and the sum is 7, your program should return [[11, -4], [2, 5]] because 11 + -4 = 7 and 2 + 5 = 7.
 *
 */
public class TwoSum {
	public static void main(String[] args) {
		TwoSum t = new TwoSum();
		//int[] a = { 1 };
		int[] a = { 3, 5, 2, -4, 8, 11, 7 };
		int s = 7;
		t.calcTwoSumSolution02(a, s);
	}
	
	private static int calcTwoSumSolution01(int[] a, int s){
		int sum = 0;
		
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] + a[j] == 7) {
					System.out.println(a[i] + " e " + a[j]);
				}
			}
		}
		
		return sum;
	}
	
	private static int calcTwoSumSolution02(int[] a, int s) {
		int sum = 0;
		
		HashSet<Integer> nums = new HashSet<Integer>();
		
		for (int i = 0; i < a.length; i++) {
			nums.add(a[i]);
			
			if (nums.contains(s - a[i])) {
				System.out.println(a[i] + " and " + (s - a[i]));
			}
		}
		
		return sum;
	}
}