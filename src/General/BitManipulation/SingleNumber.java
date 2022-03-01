package General.BitManipulation;

/**
 * https://leetcode.com/problems/single-number/
 * 
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 * 
 * Example 1:
 * Input: nums = [2,1,2]
 * Output: 1
 * 
 * Example 2:
 * Input: nums = [1,4,2,1,2]
 * Output: 4
 * 
 * Example 3:
 * Input: nums = [1]
 * Output: 1
 * 
 * Constraints:
 * 1 <= nums.length <= 3 * 10 ^ 4
 * -3 * 10 ^ 4 <= nums[i] <= 3 * 10 ^ 4
 * Each element in the array appears twice except for one element which appears only once.
 */
public class SingleNumber {

	public static void main(String[] args) {
		int[] nums = {2,1,2};
 		System.out.println(singleNumber(nums));
	}
	
	public static int singleNumber(int[] nums) {
		int ans = 0;

	    for (final int num : nums)
	      ans ^= num;

	    return ans;
    }
}