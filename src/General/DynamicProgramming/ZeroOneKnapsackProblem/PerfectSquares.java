package General.DynamicProgramming.ZeroOneKnapsackProblem;

import java.util.Arrays;

/**
 * Given a number that is less than 10^5 determine the smallest amount of perfect squares needed to sum to a particular number. 
 * The same number can be used multiple times.
 * 
 * Example 1:
 * Input: 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4
 * 
 * Example 2:
 * Input: 13
 * Output: 2
 * Explanation: 13 = 4 + 9
 * 
 * Solution
 * 
 * The answer is to use dynamic programming either bottom-up or top-down and to maintain the least number of numbers to get a certain sum. 
 * Alternatively you can use a graph theory algorithm such as bfs to solve this question as well using a queue. 
 * The solution displayed uses a bottom-up dynamic programming approach.
 * Time Complexity: O(n*sqrt(n))
 */
public class PerfectSquares {

	public static int perfectSquares(int n) {
        int[] dp = new int [n + 1];
        
        // Set to arbitrarily high value, 0x3f3f3f3f was chosen here which is approximately 10^9, any sufficiently large value can work here.
        Arrays.fill(dp, 0x3f3f3f3f);
        
        dp[0] = 0;

        // We only need to loop up to the square root of the number so we don't exceed it.
        for (int i = 1; i * i <= n; i++) {
            
        	int cur = i * i;
            
        	System.out.println("i="+i+" cur="+cur);
        	
        	// Loop forwards so we can count reusing a number multiple times.
            for (int j = cur; j <= n; j++) {
                
            	System.out.println("j="+j+" dp["+j+"]="+dp[j]);
            	
            	dp[j] = Math.min(dp[j], dp[j - cur] + 1);
            	
            	System.out.println("j="+j+" dp["+j+"]="+dp[j]);
            }
        }
        
        return dp[n];
    }
	
	public static void main(String[] args) {
        int n = Integer.parseInt("13");
        int res = perfectSquares(n);
        System.out.println(res);
    }
}