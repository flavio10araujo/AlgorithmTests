package General.DynamicProgramming.Sequence;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * You are given coins of different denominations and a total amount of money amount. 
 * Write a function to compute the fewest number of coins that you need to make up that amount. 
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * 
 * Example 1:
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation:
 * 11 = 5 + 5 + 1, 3 total coins
 * 
 * Example 2:
 * Input: coins = [3], amount = 1
 * Output: -1
 * 
 * Solution
 * 
 * To make up for a target amount, we can pick a coin with coin_value and then we are left to make up the remaining amount - coin_value amount 
 * which we can find from previous subproblem solutions.
 * Let dp[i] represent the minimum amount of coins needed to make up amount i,
 * dp[i] = min(1 + dp[i - coin]) for coin in coins
 * 1 + because we are picking one coin. 
 * dp[i - coin] means we have to make up the remaining value.
 * 
 * Time Complexity: O(coins.length * amount)
 */
public class CoinChange {

	public static int contador = 0;
	
	public static int coinChange(List<Integer> coins, int amount) {
        if (amount == 0) {
        	return 0;
        }
        
        long[] dp = new long[amount + 1];
        
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            
            for (int coin : coins) {
                dp[i] = Math.min(dp[i], i >= coin ? dp[i - coin] + 1 : Integer.MAX_VALUE);
                System.out.println(++contador);
            }
        }
        
        return dp[amount] == Integer.MAX_VALUE ? -1 : (int) dp[amount];
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }
    
    public static void main(String[] args) {
        List<Integer> coins = splitWords("1 2 5").stream().map(Integer::parseInt).collect(Collectors.toList());
        int amount = Integer.parseInt("11");
        int res = coinChange(coins, amount);
        System.out.println(res);
    }
}