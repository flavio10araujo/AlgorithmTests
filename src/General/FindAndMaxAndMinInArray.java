package General;

import java.util.Arrays;

/**
 * Program to find largest and smallest number from an array.
 * You cannot use any library method both from Java and third-party library.
 */
public class FindAndMaxAndMinInArray {

	public static void main(String[] args) {
		largestAndSmallest(new int[]{-20, 34, 21, -87, 92, Integer.MAX_VALUE});
		largestAndSmallest(new int[]{10, Integer.MIN_VALUE, -2});
		largestAndSmallest(new int[]{Integer.MAX_VALUE, 40, Integer.MAX_VALUE});
		largestAndSmallest(new int[]{1, -1, 0});

		//largestAndSmallest(new int[]{25, 28, 19, 96, 24, 53, 10, 88});
	}

	/**
	 * Using java lib Arrays.
	 * Time complexity: O(n log(n))
	 */
	/*public static void largestAndSmallest(int[] a) {
		Arrays.sort(a);
		System.out.printf("Largest: %d Smallest: %d %n", a[a.length - 1], a[0]);
	}*/
	
	/**
	 * Time complexity: O(n)
	 * @param a
	 */
	public static void largestAndSmallest(int[] a) {
		if (a == null || a.length == 0) {
			return;
		}
		
		int largest = a[0], smallest = a[0];

		for (int i = 0; i < a.length; i++) {
			if (a[i] > largest) {
				largest = a[i];
			}

			if (a[i] < smallest) {
				smallest = a[i];
			}
		}
		
		System.out.printf("Largest: %d Smallest: %d %n", largest, smallest);
	}
}