package General.DynamicProgramming.ZeroOneKnapsackProblem;

import java.util.Arrays;

/**
 * This problem is similar to Perfect Squares, except a number can only be used once. 
 * Again we ask you to output the minimum number of numbers in order to sum to the desired number. 
 * Output -1 if the number can't be reached.
 * 
 * Example 1:
 * Input: 12
 * Output: -1
 * Explanation: You cannot make 12 with any combination of 1, 4, 9.
 * 
 * Example 2:
 * Input: 13
 * Output: 2
 * Explanation: 13 = 4 + 9
 * 
 * Solution
 * 
 * The idea is to use a knapsack just like before except instead simply loop backwards to prevent using the same number twice.
 * Time Complexity: O(n*sqrt(n))
 */
public class PerfectSquares2 {

	public static int perfectSquares2(int n) {
        int [] dp = new int [n + 1];
        
        // Set to arbitrarily high value, 0x3f3f3f3f was chosen here which is approximately 10^9, any sufficiently large value can work here.
        Arrays.fill(dp, 0x3f3f3f3f);
        
        dp[0] = 0;

        // We only need to loop up to the square root of the number so we don't exceed it.
        for (int i = 1; i * i <= n; i++) {
            
        	int cur = i * i;
            
        	// Loop forwards so we can count reusing a number multiple times.
            for (int j = n; j >= cur; j--) {
                dp[j] = Math.min(dp[j], dp[j - cur] + 1);
            }
        }
        
        return dp[n] == 0x3f3f3f3f ? -1 : dp[n];
    }

    public static void main(String[] args) {
        int n = Integer.parseInt("12");
        int res = perfectSquares2(n);
        System.out.println(res);
    }
}