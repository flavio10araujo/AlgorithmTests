package General;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * https://leetcode.com/problems/jump-game-ii/
 * 
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * You can assume that you can always reach the last index.
 * 
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * 
 * Example 2:
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 * 
 * Constraints:
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 */
public class JumpGameII {

	public static void main(String[] args) {
		//int[] nums = {2,3,1,1,4}; // 2
		int[] nums = {2,3,0,1,4}; // 2
		//int[] nums = {2,1}; // 1
		//int[] nums = {8,2,4,4,4,9,5,2,5,8,8,0,8,6,9,1,1,6,3,5,1,2,6,6,0,4,8,6,0,3,2,8,7,6,5,1,7,0,3,4,8,3,5,9,0,4,0,1,0,5,9,2,0,7,0,2,1,0,8,2,5,1,2,3,9,7,4,7,0,0,1,8,5,6,7,5,1,9,9,3,5,0,7,5}; //13 
		System.out.println(solution04(nums));
	}

	/**
	 * DFS.
	 * @param nums
	 * @return
	 */
	public static int solution01(int[] nums) {
		if (nums.length <= 1) {
			return 0;
		}

		int[] dp = new int[nums.length];

		for (int i = nums.length - 1; i >= 0; i--) {
			if ((nums.length - 1)  - i - nums[i] <= 0) {
				dp[i] = 1;
				continue;
			}

			if (nums[i] == 0) {
				dp[i] = nums.length;
				continue;
			}

			int j = nums[i];
			int min = Integer.MAX_VALUE;

			while (j > 0) {
				min = Math.min(1 + dp[i + j], min);
				j--;
			}

			dp[i] = min;
		}

		return dp[0];
	}

	/**
	 * Approach: DFS + memo.
	 * Time complexity: O(n).
	 * Space complexity: O(n).
	 * @param nums
	 * @return
	 */
	public static int solution02(int[] nums) {
		int[] memo = new int[nums.length];
		Arrays.fill(memo, -1);
		return dfs(nums, 0, memo);
	}

	public static int dfs(int[] nums, int index, int[] memo) {
		if (index == nums.length - 1) {
			return 0;
		}

		if (memo[index] >= 0) {
			return memo[index];
		}

		if (nums[index] == 0) {
			return nums.length;
		}

		int count = 1;
		int min = Integer.MAX_VALUE;

		for (int i = 1; i <= nums[index]; i++) {
			if (index + i < nums.length) {
				min = Math.min(min, dfs(nums, index + i, memo));
			}
		}

		memo[index] = count + min;

		return memo[index];
	}

	/**
	 * Approach: BFS.
	 * @param nums
	 * @return
	 */
	public static int solution03(int[] nums) {
		if (nums.length <= 1) {
			return 0;
		}

		int ans = -1;

		Deque<Integer> queue = new ArrayDeque<>();

		queue.add(0);

		while(!queue.isEmpty()) {
			ans++;
			int n = queue.size();

			for (int i = 0; i < n; i++) {
				int index = queue.pop();

				if (index == nums.length - 1) {
					return ans;
				}

				int jump = nums[index];

				while(jump > 0) {
					if (index + jump < nums.length) {
						queue.add(index + jump);
					}

					jump--;
				}
			}
		}

		return ans;
	}

	/**
	 * Time complexity: O(n).
	 * Space complexity: O(1).
	 * @param nums
	 * @return
	 */
	public static int solution04(int[] nums) {
		int ans = 0;
		int end = 0;
		int farthest = 0;

		for (int i = 0; i < nums.length - 1; ++i) {
			farthest = Math.max(farthest, i + nums[i]);
			if (farthest >= nums.length - 1) {
				++ans;
				break;
			}
			if (i == end) {
				++ans;
				end = farthest;
			}
		}

		return ans;
	}
}