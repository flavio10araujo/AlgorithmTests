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
		
        // If we have only 1 number (or zero) in our list, it is impossible to find two subsets of numbers.
		if (nums.size() < 2) {
            return false;
        }
        
        int sum = 0;
        
        for (int i : nums) {
            sum += i;
        }

        // If the sum of all numbers in the list are an odd number, it means that we can not have two subsets with the same value.
        if (sum % 2 == 1) {
            return false;
        }
        
        sum = sum / 2;

        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        // Magic code ..,;:*
        // Impossible to understand.
        for (int i = 0; i < nums.size(); i++) {
            for (int j = sum; j >= nums.get(i); j--) {
                
            	System.out.println("i="+i+" j="+j+" nums.get("+i+")="+nums.get(i)+" dp["+j+"]="+dp[j]);
            	
            	dp[j] = dp[j] || dp[j - nums.get(i)];
            	
            	System.out.println("Après: dp["+j+"]="+dp[j]);
            }
        }
        
        return dp[sum];
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }
    
    public static void main(String[] args) {
        List<Integer> nums = splitWords("7 3 4").stream().map(Integer::parseInt).collect(Collectors.toList());
        boolean res = canPartition(nums);
        System.out.println(res);
    }
}