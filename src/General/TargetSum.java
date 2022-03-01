package General;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/target-sum/
 * 
 * You are given an integer array nums and an integer target.
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
 * 
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
 * Return the number of different expressions that you can build, which evaluates to target.
 * 
 * Example 1:
 * Input: nums = [1,1,1,1,1], target = 3
 * Output: 5
 * Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * 
 * Example 2:
 * Input: nums = [1], target = 1
 * Output: 1
 * 
 * Constraints:
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 */
public class TargetSum {

	public static void main(String[] args) {
		int[] nums = {1,1,1,1,1};
		int target = 3;

		//int[] nums = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
		//int target = 1;

		//int[] nums = {100};
		//int target = -200;

		//int[] nums = {1,2,3};
		//int target = 6;
		
		System.out.println(solution02(nums, target));
	}

	/**
	 * Time complexity: O(2 ^ n).
	 * Space complexity:
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int solution01(int[] nums, int target) {
		return dfs(nums, target, 0, 0, 0);
	}

	private static int dfs(int[] nums, int target, int start, int count, int sum) {
		if (count == nums.length) {
			if (sum == target) {
				return 1;
			} else {
				return 0;
			}
		}

		int totalCount = 0;

		for (int i = start; i < nums.length; i++) {

			if (count == 0 && i > 0) {
				return totalCount;
			}

			totalCount += dfs(nums, target, i + 1, count + 1, sum + nums[i]);
			totalCount += dfs(nums, target, i + 1, count + 1, sum - nums[i]);
		}

		return totalCount;
	}

	/**
	 * Time complexity: .
	 * Space complexity: .
	 * @param nums
	 * @param S
	 * @return
	 */
	static Map<String, Long> mem = new HashMap<>();
	
	public static int solution02(int[] nums, int target) {
		int n = nums.length;
        
		if (n == 0) return 0;
        
        return (int) (ways(nums, target, n, 1, nums[0]) + ways(nums, target, n, 1, -nums[0]));
	}
	
	public static long ways(int[] nums, int target, int n, int pos, int sum) {
        if (n == pos) return sum == target ? 1 : 0;
        
        String key = Integer.toString(pos) + "*" + Integer.toString(sum);
        
        if (mem.containsKey(key)) {
            return mem.get(key);
        }
            
        long val = ways(nums, target, n, pos + 1, sum + nums[pos]) + ways(nums, target, n, pos + 1, sum - nums[pos]);
        
        mem.put(key, val);
        
        return val;
    }
}