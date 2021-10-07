package General;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/missing-number/
 * 
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 * 
 * Example 1:
 * Input: nums = [3,0,1]
 * Output: 2
 * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 
 * 2 is the missing number in the range since it does not appear in nums.
 * 
 * Example 2:
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 
 * 2 is the missing number in the range since it does not appear in nums.
 * 
 * Example 3:
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 
 * 8 is the missing number in the range since it does not appear in nums.
 * 
 * Example 4:
 * Input: nums = [0]
 * Output: 1
 * Explanation: n = 1 since there is 1 number, so all numbers are in the range [0,1]. 
 * 1 is the missing number in the range since it does not appear in nums.
 * 
 * Constraints:
 * n == nums.length
 * 1 <= n <= 104
 * 0 <= nums[i] <= n
 * All the numbers of nums are unique.
 */
public class MissingNumber {

	public static void main(String[] args) {
		//int[] nums = {3,0,1};
		//int[] nums = {0,1};
		int[] nums = {9,6,4,2,3,5,7,0,1};
		
		System.out.println(solution01(nums));
		System.out.println(solution02(nums));
		System.out.println(solution03(nums));
	}
	
	/**
	 * Time complexity: O(n).
	 * Space complexity: O(n).
	 * @param nums
	 * @return
	 */
	public static int solution01(int[] nums) {
        Set<Integer> myset = new HashSet<>();
        
        for (int i = 0; i < nums.length; i++) {
        	myset.add(nums[i]);
        }
        
        for (int i = 1; i <= nums.length; i++) {
        	if (!myset.contains(i)) {
        		return i;
        	}
        }
        
        return -1;
    }
	
	/**
	 * Time complexity: O(n).
	 * Space complexity: O(1).
	 * @param nums
	 * @return
	 */
	public static int solution02(int[] nums) {
		int sumExpected = sumNumbersLessOrEqualThanN(nums.length);
		int sumReal = sumNumberInArray(nums);
		
		return sumExpected - sumReal;
	}
	
	public static int sumNumbersLessOrEqualThanN(int n) {
		int sum = 0;
		
		for (int i = 1; i <= n; i++) {
			sum += i;
		}
		
		return sum;
	}
	
	public static int sumNumberInArray(int[] nums) {
		int sum = 0;
		
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		
		return sum;
	}
	
	/**
	 * 
	 * @param nums
	 * @return
	 */
	public static int solution03(int[] nums) {
		int ans = nums.length;

	    for (int i = 0; i < nums.length; ++i) {
	    	ans ^= i ^ nums[i];
	    }

	    return ans;
	}
}