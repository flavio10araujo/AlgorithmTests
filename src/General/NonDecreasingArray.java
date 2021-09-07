package General;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/non-decreasing-array/
 * 
 * Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most one element.
 * We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).
 * 
 * Example 1:
 * Input: nums = [4,2,3]
 * Output: true
 * Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
 * 
 * Example 2:
 * Input: nums = [4,2,1]
 * Output: false
 */
public class NonDecreasingArray {

	public static void main(String[] args) {
		int[] nums = {3,3,3,3,3,2,3,3,3,3,3,2};
		System.out.println(checkPossibility(nums));
	}

	/**
	 * Time complexity: O(n).
	 * Space complexity: O(1).
	 * @param nums
	 * @return
	 */
	public static boolean checkPossibility(int[] nums) {
		
		if (nums.length <= 2) {
			return true;
		}
		
		int pos = -1;
		
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] > nums[i + 1]) {
				if (pos != -1) {
					return false;
				}
				
				pos = i;
			}
		}
		
		return pos == -1 || pos == 0 || pos == nums.length - 2 || nums[pos - 1] <= nums[pos + 1] || nums[pos] <= nums[pos + 2]; 
	}
	
	/**
	 * Time complexity: O(n ^ 2).
	 * Space complexity: O(n).
	 * @param nums
	 * @return
	 */
	public static boolean solution02(int[] nums) {
		int count = 0;
        Set<Integer> myset = new HashSet<>();
		
        for (int i = 0; i < nums.length; i++) {
        	for (int j = i + 1; j < nums.length; j++) {
        		if (nums[i] > nums[j]) {
        			if (!myset.contains(j)) {
        				myset.add(j);
        				count++;
                        break;
        			}
        		}
        	}
        }
		
        if (count >= 2) {
        	return false;
        }
        
		return true;
    }
}