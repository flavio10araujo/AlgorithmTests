package General.DynamicProgramming;

import java.util.Arrays;

public class Examples {

	public static void main(String[] args) {
		int target = 4;
		int[] nums = {2,1,1,2};

		//System.out.println(climbStairs(target, nums) + " formas de subir a escada.");

		//System.out.println(combinationSum(target, nums) + " formas de somar.");

		//System.out.println("Posso somar o valor " + target + " com as moedas " + Arrays.toString(nums) + " de quantas formas? R: "+ coinChange01(target, nums) + " ");

		//System.out.println("Essas formas são: R: "+ coinChange02(target, nums) + " ");

		//System.out.println("A forma que usa menos moedas, usa a seguinte quantidade de moedas: R: " + coinChange03(target, nums));

		//System.out.println(rob(nums));

		//System.out.println(rob2(nums));
		
		System.out.println(canJump(new int[]{3,2,1,0,4}));
	}

	/**
	 * De quantas formas diferentes eu consigo subir uma escada com n degraus?
	 * Considerando que tenho x tipos de movimentos.
	 * 
	 * n = target
	 * x = nums
	 * 
	 * Quando nums = {1, 2} quer dizer que posso subir de 1 em 1 degrau ou de 2 em 2 degraus.
	 * 
	 * Exemplo:
	 * n = 3
	 * nums = {1, 2}
	 * Output: 3 
	 * Ou seja, posso subir essa escada de 3 formas diferentes.
	 * 
	 * @param target
	 * @param nums
	 * @return
	 */
	public static int climbStairs(int target, int[] nums) {
		if (target == 0) {
			return target;
		}

		int[] dp = new int[target + 1];

		dp[0] = 1;

		for (int i = 0; i <= target; ++i)
			for (final int num : nums)
				if (i >= num)
					dp[i] += dp[i - num];

		return dp[target];
	}

	/**
	 * De quantas formas diferentes eu consigo somar determinado o valor n?
	 * Considerando que tenho x sub-valores para fazer essa conta.
	 * 
	 * n = target
	 * x = nums
	 * 
	 * Quando nums = {1, 2} quer dizer que devo usar esses valores para chegar no valor target.
	 * 
	 * Exemplo:
	 * n = 3
	 * nums = {1, 2}
	 * Output: 3
	 * Ou seja, posso somar: 1,1,1; 1,2; 2,1.
	 * 
	 * @param target
	 * @param nums
	 * @return
	 */
	public static int combinationSum(int target, int[] nums) {
		if (target == 0) {
			return target;
		}

		int[] dp = new int[target + 1];

		dp[0] = 1;

		for (int i = 0; i <= target; ++i)
			for (final int num : nums)
				if (i >= num)
					dp[i] += dp[i - num];

		return dp[target];
	}

	/**
	 * De quantas formas consigo somar as moedas (nums) e totalizar target?
	 * 
	 * Exemplo:
	 * target = 3
	 * nums = {1, 2}
	 * Output: 3
	 * Ou seja, posso somar de 3 formas: 1,1,1; 1,2; 2,1.
	 * 
	 * @param target
	 * @param nums
	 * @return
	 */
	public static int coinChange01(int target, int[] nums) {
		if (target == 0) {
			return target;
		}

		int[] dp = new int[target + 1];

		dp[0] = 1;

		for (int i = 0; i <= target; ++i)
			for (final int num : nums)
				if (i >= num)
					dp[i] += dp[i - num];

		return dp[target];
	}

	/**
	 * 
	 * @param target
	 * @param nums
	 * @return
	 */
	public static int coinChange02(int target, int[] nums) {
		return 0;
	}

	/**
	 * Dentre as formas que tenho de somar os valores de nums somando target,
	 * qual a forma que usa menos nums?
	 * 
	 * Exemplo:
	 * n = 3
	 * nums = {1, 2}
	 * Output: 2
	 * Ou seja, posso somar 1,1,1; 1,2; 2,1.
	 * A forma com menos nums possui apenas 2 números.
	 * 
	 * @param target
	 * @param nums
	 * @return
	 */
	public static int coinChange03(int target, int[] nums) {
		if (target == 0) {
			return target;
		}

		long[] dp = new long[target + 1];

		for (int i = 1; i <= target; i++) {
			dp[i] = Integer.MAX_VALUE;

			for (int coin : nums) {
				dp[i] = Math.min(dp[i], i >= coin ? dp[i - coin] + 1 : Integer.MAX_VALUE);
			}
		}

		return dp[target] == Integer.MAX_VALUE ? -1 : (int) dp[target];
	}

	/**
	 * House Robber.
	 * Se temos n casas (n = nums.length) e em cada casa tem um valor (nums[i]).
	 * Sabendo que não podemos pegar os valores de duas casas vizinhas.
	 * Qual o valor máximo que podemos pegar de valores dessas casas?
	 * 
	 * Exemplo:
	 * nums = {1, 2}
	 * Output: 2
	 * Ou seja, posso pegar o valor 2.
	 * 
	 * Exemplo:
	 * nums = {10, 2, 3, 10}
	 * Output: 20.
	 * Ou seja, dentre todas as combinações de roubo, a melhor nos dá um valor de 20.
	 * 
	 * @param nums
	 * @return
	 */
	public static int rob(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		} else if (nums.length == 2) {
			return Math.max(nums[0], nums[1]);
		}

		int[] dp = new int[nums.length];

		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);

		for (int i = 2; i < dp.length; i++) {
			dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
		}

		return dp[dp.length - 1];
	}

	/**
	 * House Robber 2.
	 * 
	 * Mesma ideia do House Robber, mas agora as casas formam um círculo.
	 * Ou seja, a última casa é vizinha da primeira casa. 
	 *  
	 * @param nums
	 * @return
	 */
	public static int rob2(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		} else if (nums.length == 2) {
			return Math.max(nums[0], nums[1]);
		}

		int[] dpWithFirst = new int[nums.length];

		dpWithFirst[0] = nums[0];
		dpWithFirst[1] = Math.max(nums[0], nums[1]);

		for (int i = 2; i < dpWithFirst.length - 1; i++) {
			dpWithFirst[i] = Math.max(dpWithFirst[i - 1], nums[i] + dpWithFirst[i - 2]);
		}

		int[] dpWithLast = new int[nums.length];

		dpWithLast[0] = 0;
		dpWithLast[1] = nums[1];

		for (int i = 2; i < dpWithLast.length; i++) {
			dpWithLast[i] = Math.max(dpWithLast[i - 1], nums[i] + dpWithLast[i - 2]);
		}


		return Math.max(dpWithFirst[dpWithFirst.length - 2], dpWithLast[dpWithLast.length - 1]);
	}

	/**
	 * Decode Ways.
	 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
	 * 'A' -> "1"
	 * 'B' -> "2
	 * ...
	 * 'Z' -> "26"
	 * 
	 * Example 1:
	 * Input: s = "12"
	 * Output: 2
	 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
	 * 
	 * @param s
	 * @return
	 */
	public static int numDecodings(String s) {
		final int n = s.length();

		int[] dp = new int[n + 1];
		dp[n] = 1;
		dp[n - 1] = isValid(s.charAt(n - 1)) ? 1 : 0;

		for (int i = n - 2; i >= 0; --i) {
			if (isValid(s.charAt(i)))
				dp[i] += dp[i + 1];
			if (isValid(s.charAt(i), s.charAt(i + 1)))
				dp[i] += dp[i + 2];
		}

		return dp[0];
	}

	private static boolean isValid(char c) {
		return '1' <= c && c <= '9';
	}

	private static boolean isValid(char c1, char c2) {
		return c1 == '1' || (c1 == '2' && c2 <= '6');
	}
	
	/**
	 * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
	 * Return true if you can reach the last index, or false otherwise.
	 * 
	 * Example 1:
	 * Input: nums = [2,3,1,1,4]
	 * Output: true
	 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
	 * 
	 * Example 2:
	 * Input: nums = [3,2,1,0,4]
	 * Output: false
	 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
	 * 
	 * @param nums
	 * @return
	 */
	public static boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        
        int jump = -1;
        
        for (int i = 0; i < nums.length - 1; i++) {
            jump = Math.max(jump - 1, nums[i]);
        }
        
        if (jump > 0) {
            return true;
        }
        
        return false;
    }
}