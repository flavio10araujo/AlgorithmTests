package General;

/**
 * https://leetcode.com/problems/maximum-product-subarray/
 * 
 * Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.
 * It is guaranteed that the answer will fit in a 32-bit integer.
 * A subarray is a contiguous subsequence of the array.
 * 
 * Example 1:
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * 
 * Example 2:
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 * 
 * Constraints:
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 */
public class MaximumProductSubarray {

	public static void main(String[] args) {
		//int[] nums = {2, 3, -2, 4};
		int[] nums = {2, 3, 0, 7, 0};
		System.out.println(maxProduct(nums));
	}
	
	/**
	 * Time complexity: O(n).
	 * Space complexity: O(1).
	 * @param nums
	 * @return
	 */
	public static int maxProduct(int[] nums) {
		if (nums.length == 1) {
            return nums[0]; 
        }
        
        int size = nums.length;
        
        int[] dpMax = new int[size];
        int[] dpMin = new int[size];
        
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        
        int max = nums[0];
        
        for (int i = 1; i < size; i++) {
            int n  = nums[i];
            int n1 = nums[i] * dpMax[i - 1];
            int n2 = nums[i] * dpMin[i - 1];
            
            dpMax[i] = Math.max(n, Math.max(n1, n2));
            dpMin[i] = Math.min(n, Math.min(n1, n2));
            
            max = Math.max(max, dpMax[i]);
        }
        
        return max;
    }
}