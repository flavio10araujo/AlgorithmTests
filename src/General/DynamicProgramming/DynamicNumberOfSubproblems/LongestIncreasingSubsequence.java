package General.DynamicProgramming.DynamicNumberOfSubproblems;

import java.util.Arrays;
import java.util.List;

/**
 * Longest Increasing Subsequence (LIS).
 * Find the length of the longest subsequence of a given sequence such that all elements of the subsequence are sorted in increasing order.

 * For example, the length of LIS for [50, 3, 10, 7, 40, 80] is 4 and LIS is [3, 7, 40, 80].
 * 
 * The keywords "longest" and "sequence" are good indicators of dynamic programming.
 * 
 * Solution:
 * 
 * To find the longer subsequence ending at a number at index i, what we want to do is to see if we can extend a previous subsequence by appending nums[i]. 
 * This is only possible if nums[i] is larger than the last element of the previous subsequence.
 * Let dp[i] be the longest increasing subsequence ending at index i. 
 * Given that we know all the previous dp[j]s for j < i, we can find dp[i] by checking each number before nums[i]. 
 * If a number before current number is smaller than current number nums[j] < nums[i], then we can update dp[i] to be max(d[i], dp[j] + 1). 
 * dp[j] + 1 because the new subsequence is whatever the subsequence ending in j plus the current element.
 */
public class LongestIncreasingSubsequence {
	
	public static int longestSubLen(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        
        int best = 0;
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            
            best = Math.max(best, dp[i]);
        }
        
        return best;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
    	int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
    	System.out.println(longestSubLen(nums));
    }
}