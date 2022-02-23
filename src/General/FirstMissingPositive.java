package General;

/**
 * https://leetcode.com/problems/first-missing-positive/
 * 
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 * You must implement an algorithm that runs in O(n) time and uses constant extra space.
 * 
 * Example 1:
 * Input: nums = [1,2,0]
 * Output: 3
 * 
 * Example 2:
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * 
 * Example 3:
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 * 
 * Constraints:
 * 1 <= nums.length <= 5 * 10 ^ 5
 * -2 ^ 31 <= nums[i] <= 2 ^ 31 - 1
 */
public class FirstMissingPositive {

	public static void main(String[] args) {
		//int[] nums = {1,2,0}; // 3
		//int[] nums = {3,4,-1,1}; // 2
		//int[] nums = {7,8,9,11,12}; // 1
		int[] nums = {1}; // 2
		
		System.out.println(solution01(nums));
	}
	
	public static int solution01(int[] nums) {
		int index = 0;
		
		while(index < nums.length) {
			if (nums[index] == index + 1) {
				index++;
				continue;
			}
			
			if (nums[index] <= 0 || nums[index] > nums.length) {
				nums[index] = -1;
				index++;
				continue;
			}
			
			int temp = nums[nums[index] - 1];
			nums[nums[index] - 1] = nums[index];
			
			if (temp <= 0 || temp > nums.length || temp == nums[index]) {
				nums[index] = -1;
				index++;
			} else {
				nums[index] = temp;
			}
		}
		
		for (int i = 0; i < nums.length; i++) {
        	if (nums[i] == -1)
        		return i + 1;
        }
		
		return nums.length + 1;
	}

	/**
	 * Time complexity: O(n)
	 * Space complexity: O(n)
	 *  
	 * @param nums
	 * @return
	 */
	public static int solution02(int[] nums) {
        boolean[] checks = new boolean[nums.length]; 
		
        for (int i : nums) {
        	if (i <= 0)
        		continue;
        	
        	if (i - 1 < nums.length)
        		checks[i - 1] = true;
        }
        
        for (int i = 0; i < checks.length; i++) {
        	if (!checks[i])
        		return i + 1;
        }
        
		return 0;
    }
}