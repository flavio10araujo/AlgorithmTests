package General.DynamicProgramming.Sequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	public static int minQtd = Integer.MAX_VALUE;

	public static void main(String[] args) {
		//int[] coins = {1,3,4,5}; int amount = 7;
		//int[] coins = {1,2,5}; int amount = 11;
		//int[] coins = {1,2,5}; int amount = 100;
		int[] coins = {2}; int amount = 3;

		System.out.println(solution01(coins, amount));

		System.out.println(solution03(coins, amount));
	}

	/**
	 * Approach: Dynamic Programming
	 * Time complexity: O(coins * amount)
	 * Space complexity: O(amount)
	 * @param coins
	 * @param amount
	 * @return
	 */
	public static int solution01(int[] coins, int amount) {
		if (amount == 0) {
			return 0;
		}

		long[] dp = new long[amount + 1];

		for (int i = 1; i <= amount; i++) {
			dp[i] = Integer.MAX_VALUE;

			for (int coin : coins) {
				dp[i] = Math.min(dp[i], i >= coin ? dp[i - coin] + 1 : Integer.MAX_VALUE);
				//System.out.println(++contador);
			}
		}

		return dp[amount] == Integer.MAX_VALUE ? -1 : (int) dp[amount];
	}
	
	/**
	 * Approach: DFS.
	 * @param coins
	 * @param amount
	 * @return
	 */
	public static int solution03(int[] coins, int amount) {
		if (amount == 0) {
			return 0;
		}

		Arrays.sort(coins);

		for (int i = 0; i < coins.length; i++) {
			List<Integer> path = new ArrayList<>();
			path.add(coins[i]);
			DFS(coins, amount, path, coins[i]);
		}

		if (minQtd == Integer.MAX_VALUE) {
			return -1;
		}

		return minQtd;
	}

	public static boolean DFS(int[] coins, int amount, List<Integer> path, int sumPath) {
		if (sumPath == amount) {
			minQtd = Math.min(minQtd, path.size());
			return true;
		} else if (sumPath > amount) {
			return false;
		}

		for (int i = 0; i < coins.length; i++) {
			boolean jump = false;
			path.add(coins[i]);
			sumPath += coins[i];

			if (sumPath <= amount && path.size() < minQtd) {
				DFS(coins, amount, path, sumPath);
			}

			if (sumPath > amount || path.size() > minQtd) {
				jump = true;
			}

			sumPath -= coins[i];
			path.remove(path.size() - 1);

			if (jump) {
				i = coins.length;
			}
		}

		return true;
	}
}