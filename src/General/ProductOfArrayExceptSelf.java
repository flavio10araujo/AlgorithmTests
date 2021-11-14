package General;

/**
 * https://leetcode.com/problems/product-of-array-except-self/
 * 
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 * 
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * 
 * Example 2:
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 * 
 * Constraints:
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * Follow up: Can you solve the problem in O(1) extra space complexity? 
 * (The output array does not count as extra space for space complexity analysis.)
 */
public class ProductOfArrayExceptSelf {

	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4};
		
		long startTime = System.nanoTime();
		System.out.println("Solution 01:");
		printArr(solution01(nums));
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
		
		startTime = System.nanoTime();
		System.out.println("Solution 02:");
		printArr(solution02(nums));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	}

	public static void printArr(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	} 

	/**
	 * Time complexity: O(n).
	 * Space complexity: O(n).
	 * @param nums
	 * @return
	 */
	public static int[] solution01(int[] nums) {

		int size = nums.length;
		int[] prodFront = new int[size];
		int[] prodBack = new int[size];
		int[] res = new int[size];

		prodFront[0] = 1;
		prodBack[size - 1] = 1;

		for (int i = 0; i < size - 1; i++) {
			prodFront[i + 1] = prodFront[i] * nums[i];
		}

		for (int i = size - 1; i > 0; i--) {
			prodBack[i - 1] = prodBack[i] * nums[i];
		}

		for (int i = 0; i < size; i++) {
			res[i] = prodFront[i] * prodBack[i];
		}

		return res;
	}

	/**
	 * Time complexity: O(n).
	 * Space complexity: O(1).
	 * @param nums
	 * @return
	 */
	public static int[] solution02(int[] nums) {
		final int n = nums.length;

		int[] ans = new int[n];
		ans[0] = 1;

		// use ans as the prefix product array
		for (int i = 1; i < n; ++i) {
			ans[i] = ans[i - 1] * nums[i - 1];
		}

		int suffix = 1; // suffix product
		
		for (int i = n - 1; i >= 0; --i) {
			ans[i] *= suffix;
			suffix *= nums[i];
		}

		return ans;
	}
}