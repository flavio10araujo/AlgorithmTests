package General;

/**
 * https://leetcode.com/problems/rotate-array/
 * 
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * 
 * Example 1:
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * 
 * Example 2:
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation: 
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 * 
 * Constraints:
 * 1 <= nums.length <= 10 ^ 5
 * -2 ^ 31 <= nums[i] <= 2 ^ 31 - 1
 * 0 <= k <= 10 ^ 5
 */
public class RotateArray {

	public static void main(String[] args) {
		int[] nums = {1,2,3,4,5,6,7};
		int k = 3;

		//int[] nums = {1,2};
		//int k = 2;

		//int[] nums = {1,2,3,4,5,6};
		//int k = 4;

		solution02(nums, k);

		for (int n : nums) {
			System.out.print(n + " ");
		}
	}

	/**
	 * Time complexity: O(n).
	 * Space complexity: O(n).
	 * 
	 * @param nums
	 * @param k
	 */
	public static void solution01(int[] nums, int k) {
		int[] temp = new int[nums.length];

		if (k > nums.length) {
			k = k % nums.length;
		}

		if (k == 0) {
			return;
		}

		int index = nums.length - k;

		for (int i = 0; i < temp.length; i++) {
			temp[i] = nums[index];

			index++;

			if (index == nums.length) {
				index = 0;
			}
		}

		for (int i = 0; i < temp.length; i++) {
			nums[i] = temp[i];
		}
	}

	/**
	 * Time complexity: O(n).
	 * Space complexity: O(1).
	 * 
	 * @param nums
	 * @param k
	 */
	public static void solution02(int[] nums, int k) {
		k %= nums.length;
		reverse(nums, 0, nums.length - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, nums.length - 1);
	}

	private static void reverse(int[] nums, int l, int r) {
		while (l < r)
			swap(nums, l++, r--);
	}

	private static void swap(int[] nums, int l, int r) {
		final int temp = nums[l];
		nums[l] = nums[r];
		nums[r] = temp;
	}
}