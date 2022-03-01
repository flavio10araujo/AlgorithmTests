package General;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/majority-element/
 * 
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than n / 2 times. You may assume that the majority element always exists in the array.
 * 
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 * 
 * Example 2:
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 * 
 * Constraints:
 * n == nums.length
 * 1 <= n <= 5 * 10 ^ 4
 * -2 ^ 31 <= nums[i] <= 2 ^ 31 - 1
 */
public class MajorityElement {

	public static void main(String[] args) {
		//int[] nums = {2,2,1,1,1,2,2};
		int[] nums = {1,6,3,6,5,6,6};
		
		System.out.println(solution02(nums));
	}

	/**
	 * Approach: map.
	 * Time: O(n).
	 * Space: O(n).
	 * @param nums
	 * @return
	 */
	public static int solution01(int[] nums) {
		int ans = 0, max = 0;
		Map<Integer, Integer> countNums = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			int counter = countNums.getOrDefault(nums[i], 0) + 1;
			countNums.put(nums[i], counter);

			if (counter > max) {
				max = counter;
				ans = nums[i];
			}
		}

		return ans;
	}

	/**
	 * Time: O(n).
	 * Space: O(1).
	 * @param nums
	 * @return
	 */
	public static int solution02(int[] nums) {
		Integer ans = null;
		int count = 0;

		/*for (final int num : nums) {
			if (count == 0)
				ans = num;
			count += num == ans ? 1 : -1;
		}*/
		
		for (final int num : nums) {
			if (count == 0) {
				ans = num;
			}
			
			count = count + (num == ans ? 1 : -1);
		}

		return ans;
	}
}