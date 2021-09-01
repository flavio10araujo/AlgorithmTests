package General;

// Fibonacci solved with three different approaches.
// We have the list: 0 1 1 2 3 5 8 13.
// We want to know the next number in the list.
// Answer: 21.
class Fibonacci {
	
	private static int counter = 0;
	
	public static void main(String args[]) {
		int n = 6;
		//System.out.println(fibSolution01(n)); // Solution with recursion.
		//System.out.println(fibSolution02(n)); // Solution with dynamic programming and memoization.
		System.out.println(fibSolution03(n)); // Solution with dynamic programming and tabulation.
		
		System.out.println("Counter = " + counter);
	}

	/**
	 * Solution with recursion.
	 * Time complexity - exponential: O(2 ^ n).
	 */
	@SuppressWarnings("unused")
	private static int fibSolution01(int n) {
		counter++;
		System.out.println("n = " + n);

		if (n <= 1) {
			return n;
		}

		return fibSolution01(n - 1) + fibSolution01(n - 2);
	}

	/**
	 * Solution with dynamic programming and memoization.
	 * This solution is better than the 01 because it avoids making the same calculation more than once.
	 * Time complexity - linear: O(n).
	 */
	@SuppressWarnings("unused")
	private static int fibSolution02(int n) {
		int memoize[] = new int[n + 1];
		return calculateFibonacciRecursive(memoize, n);
	}

	private static int calculateFibonacciRecursive(int[] memoize, int n) {
		counter++;
		System.out.println("n = " + n);

		if (n <= 1) {
			return n;
		}

		// If we have already solved this subproblem, simply return the result from the cache.
		if (memoize[n] != 0) {
			return memoize[n];
		}

		memoize[n] = calculateFibonacciRecursive(memoize, n - 1) + calculateFibonacciRecursive(memoize, n - 2);
		
		return memoize[n];
	}

	/**
	 * Solution with dynamic programming and tabulation.
	 * Time complexity - linear: O(n).
	 */
	private static int fibSolution03(int n) {
		int dp[] = new int[n + 1];
		// base cases
		dp[0] = 0;
		dp[1] = 1;

		for (int i = 2; i <= n; i++) {
			counter++;
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		return dp[n];
	}
}