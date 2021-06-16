package General;

// Fibonacci series using recursion.
// Ex.: 0 1 1 2 3 5 8 13 = the next one will be 21.
class Fibonacci {
	public static void main(String args[]) {
		int n = 8;
		//System.out.println(fibSolution01(n));
		//System.out.println(fibSolution02(n));
		System.out.println(fibSolution03(n));
	}

	/**
	 * Solution with recursion.
	 */
	@SuppressWarnings("unused")
	private static int fibSolution01(int n) {
		System.out.println("n = " + n);

		if (n <= 1) {
			System.out.println("inside the if");
			return n;
		}

		return fibSolution01(n - 1) + fibSolution01(n - 2);
	}

	/**
	 * Solution with dynamic programming and memoization.
	 * This solution is better than the 01 because it avoids making the same calculation more than once.
	 */
	@SuppressWarnings("unused")
	private static int fibSolution02(int n) {
		int memoize[] = new int[n + 1];
		return CalculateFibonacciRecursive(memoize, n);
	}

	private static int CalculateFibonacciRecursive(int[] memoize, int n) {
		System.out.println("n = " + n);

		if (n < 2) {
			System.out.println("inside the if");
			return n;
		}

		// If we have already solved this subproblem, simply return the result from the cache.
		if (memoize[n] != 0) {
			return memoize[n];
		}

		memoize[n] = CalculateFibonacciRecursive(memoize, n - 1) + CalculateFibonacciRecursive(memoize, n - 2);
		
		return memoize[n];
	}

	/**
	 * Solution with dynamic programming and tabulation.
	 */
	private static int fibSolution03(int n) {
		int dp[] = new int[n + 1];
		// base cases
		dp[0] = 0;
		dp[1] = 1;

		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
			System.out.println("dp[i] = dp[i - 1] + dp[i - 2] => dp["+i+"]=" + dp[i] + " dp["+i+" - 1]=" + dp[i - 1] + " dp["+i+" - 2]=" + dp[i - 2]);
		}

		return dp[n];
	}
}