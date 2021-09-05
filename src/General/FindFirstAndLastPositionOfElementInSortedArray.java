package General;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * 
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * If target is not found in the array, return [-1, -1].
 * You must write an algorithm with O(log n) runtime complexity.
 * 
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * 
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * 
 * Example 3:
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 * 
 * Constraints:
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums is a non-decreasing array.
 * -109 <= target <= 109
 *
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

	public static void main(String[] args) {
		int[] nums = {5,7,7,8,8,10};
		int target = 8;
		
		int[] res = searchRange(nums, target);
		
		System.out.println(Arrays.toString(res));
	}

	public static int[] searchRange(int[] nums, int target) {
        
        int[] res = new int[2];
        
        res[0] = -1;
        res[1] = -1;
        
        if (nums.length == 0) {
            return res;
        }
        
        int L = 0; // left pointer.
        int R = nums.length - 1; // right pointer.
        
        // Looking for the first occurrence of target.
        while(L <= R) {
            int M = (L + R) / 2; // middle pointer;
            
            if (nums[M] < target) {
                L = M + 1;
            } else {
                R = M - 1;
                
                if (nums[M] == target) {
                    res[0] = M;
                }
            }
        }
        
        // If there is no target in the array.
        if (res[0] == -1) {
            return res;
        }
        
        L = 0;
        R = nums.length - 1;
        
        while(L <= R) {
            int M = (L + R) / 2;
            
            if (nums[M] <= target) {
                L = M + 1;
                
                if (nums[M] == target) {
                    res[1] = M;
                }
            } else {
                R = M - 1;
            }
        }
        
        return res;
    }
}