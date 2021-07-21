package General.DynamicProgramming.Partition;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Consider a non-empty array containing only positive integers, 
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * 
 * Note:
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 * 
 * Example:
 * Input:
 * [3, 4, 7]
 * Output: True
 * Explanation: The array can be partitioned as [3,4] and [7].
 * 
 * Example:
 * Input:
 * [1, 5 , 11, 5]
 * Output: True
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * 
 * Solution
 * 
 * This is a classic "Partition DP", i.e. DFS + memoization problem.
 * First off, the sum of each subset should be the sum of the entire array divided by 2, sum(nums) / 2, we call it target.
 * Since there are only two subsets, we just have to find a subset of numbers that sum to target because the rest of the numbers will also sum to target.
 * Now the problem has become whether we can find a subset of numbers that sums to target, which we can solve with DFS.
 * We use used array to record which element has been picked.
 * Time Complexity: O(n^2)
 */
public class PartitionToTwoEqualSubsetSums {

	public static boolean canPartition(List<Integer> nums) {
		
        if (nums.size() < 2) {
            return false;
        }
        
        int sum = 0;
        
        for (int i : nums) {
            sum += i;
        }

        if (sum % 2 == 1) {
            return false;
        }
        
        sum /= 2;

        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int i = 0; i < nums.size(); i++) {
            for (int j = sum; j >= nums.get(i); j--) {
                dp[j] = dp[j] || dp[j - nums.get(i)];
            }
        }
        
        return dp[sum];
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }
    
    public static void main(String[] args) {
        List<Integer> nums = splitWords("3 4 7").stream().map(Integer::parseInt).collect(Collectors.toList());
        boolean res = canPartition(nums);
        System.out.println(res);
    }
}