package General;

/**
 * https://leetcode.com/problems/maximum-subarray/
 * 
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * A subarray is a contiguous part of an array.
 * 
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * 
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 * 
 * Example 3:
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 */
public class MaximumSubarray {

	public static void main(String[] args) {
		int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
		//int[] nums = {5,4,-1,7,8};
		
		System.out.println(solution01(nums));
		
		System.out.println(solution02(nums));
	}

	/**
	 * O(n ^ 2).
	 * @param nums
	 * @return
	 */
	public static int solution01(int[] nums) {
		if (nums.length == 0) {
            return 0;
        }
        
        if (nums.length == 1) {
            return nums[0];
        }
        
        int sum = 0;
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            
            sum = nums[i];
            
            if (sum > max) {
                max = sum;
            }
            
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                
                if (sum > max) {
                    max = sum;
                }
            }
        }
        
        return max;
	}
	
	/**
	 * O(n).
	 * Kadane's Algorithm.
	 * @param nums
	 * @return
	 */
	public static int solution02(int[] nums) {
		int maxSum = nums[0];
		int currentSum = maxSum;
		
		for (int i = 1; i < nums.length; i++) {
			currentSum = Math.max(nums[i] + currentSum, nums[i]);
			maxSum = Math.max(currentSum, maxSum);
		}
		
		return maxSum;
	}
}