package General.DynamicProgramming.Sequence;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * You are a robber planning to rob houses along a street.
 * Each house has a certain amount of treasure stashed, 
 * the only constraint stopping you from robbing every one of them is that adjacent houses have security system connected 
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given a list of integers representing the amount of money of each house, 
 * determine the maximum amount of money you can rob tonight without alerting the police.
 * 
 * Solution:
 * 
 * The keyword "maximal" and the array in the problem statement is a good sign of dynamic programming.
 * For a particular house i, we can choose to rob it or not rob it.
 * 	If we rob it, we take its loot but it also means we could not have robbed the house before it, house i-1
 * 	If we don't rob it, we don't take its loot but we can take loot from house before it, house i-1
 * 
 * In addition, whether to rob house i-1 also decides whether we could have robbed its previous house, i-2.
 * Therefore we have our recurrence relation:
 * Let dp[i] represent the maximum amount of loot one can get using all houses from the first one up to and including house i.
 * 
 * dp[i] = max(dp[i - 1], nums[i] + dp[i - 2])
 * 
 * dp[i - 1] means we don't rob the current house and therefore get max loot from previous i-1 houses.
 * nums[i] + dp[i - 2] means we rob the current house, skip the previous house and add max loot from previous i-2 houses.
 */
public class HouseRobber {
	
	public static int rob(List<Integer> nums) {
		if (nums.size() == 0) {
	        return 0;
	    }
		
	    if (nums.size() < 2) {
	        return Math.max(nums.get(0), nums.get(1));
	    }

	    int n = nums.size();
	    int[] dp = new int[n];
	    
	    dp[0] = nums.get(0);
	    dp[1] = Math.max(nums.get(0), nums.get(1));
	    
	    for (int i = 2; i < n; i++) {
	        dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums.get(i));
	    }
	    
	    return dp[dp.length - 1];
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
    	//String str = "2 7 9 3 1"; // 12
    	//String str = "2 7 9 7 1"; // 14
    	String str = "100 7 9 120 1"; // 220
        List<Integer> nums = splitWords(str).stream().map(Integer::parseInt).collect(Collectors.toList());
        int res = rob(nums);
        System.out.println(res);
    }
}