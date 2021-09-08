package General;

import java.util.Arrays;

/**
 * Given a list of numbers, find if there exists a pythagorean triplet in that list. 
 * A pythagorean triplet is 3 variables a, b, c where a2 + b2 = c2
 * 
 * Example:
 * Input: [3, 5, 12, 5, 13]
 * Output: true
 * Explanation: 5 ^ 2 + 12 ^ 2 = 13 ^ 2.
 * 
 * Input: [3, 1, 4, 6, 5]
 * Output: true
 * Explanation: 3 ^ 2 + 4 ^ 2 = 5 ^ 2.
 * 
 * Input: [10, 4, 6, 12, 5]
 * Output: false
 */
public class FindTripletsPythagorean {
	
	static int count = 0;

	public static void main(String[] args) {
		//int[] arr = {3, 5, 12, 5, 13};
		//int[] arr = {3, 1, 4, 6, 5};
		int[] arr = {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 5, 12, 13};
		//int[] arr = {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
		
		// Marcar o tempo de execução de um algoritmo:
		/*long startTime = System.nanoTime();
		System.out.println(solution01(arr));
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);*/
		
		long startTime = System.nanoTime();
		System.out.println(solution02(arr));
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
		
		System.out.println("Count: " + count);
	}
	
	/**
	 * Naive solution.
	 * Time complexity: O(n ^ 3).
	 * @param arr
	 * @return
	 */
	public static boolean solution01(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				for (int m = j + 1; m < arr.length; m++) {
					
					count++;
					
					if ((arr[i] * arr[i]) + (arr[j] * arr[j]) == (arr[m] * arr[m])) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Time Complexity: O(n ^ 2).
	 * @param arr
	 * @return
	 */
	public static boolean solution02(int[] arr) {
		
		// Square array elements.
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i] * arr[i];
		}
		
		// Sorting the array.
		Arrays.sort(arr);

		int target = arr.length - 1;
		int L = 0;
		int R = target - 1;
		
		while (L < R && R < target) {
			
			count++;
			
			if (arr[target] == (arr[L] + arr[R])) {
				System.out.println("Numbers: " + arr[L] + " and " + arr[R] + ". Target: " + arr[target]);
				return true;
			}
			
			if (arr[target] > (arr[L] + arr[R])) {
				L++;
			} else {
				R--;
			}
			
			if (L >= R) {
				target--;
				L = 0;
				R = target;
			}
		}
		
		return false;
	}
}