package General;

/**
 * Given a string s and an integer k, find out if the given string is a K-palindrome or not.
 * A string is a K-palindrome if it can be transformed into a palindrome by removing at most k characters from it.
 * 
 * Example 1:
 * Input: s = "abcdeca", k = 2
 * Output: true
 * Explanation: Remove b and e.
 */
public class ValidPalindromeIII {

	public static void main(String[] args) {
		String s = "abcdeca";
		int k = 2;

		System.out.println(isValidPalindrome(s, k));
	}

	/**
	 * Time complexity: O(n ^ 2)
	 * Space complexity: O(n ^ 2)
	 * @param s
	 * @param k
	 * @return
	 */
	public static boolean isValidPalindrome(String s, int k) {
		return s.length() - longestPalindromeSubseq(s) <= k;
	}

	// same as 516. Longest Palindromic Subsequence
	private static int longestPalindromeSubseq(final String s) {
		final int n = s.length();
		// dp[i][j] := LPS's length in s[i..j]
		int[][] dp = new int[n][n];

		for (int i = 0; i < n; ++i)
			dp[i][i] = 1;

		for (int d = 1; d < n; ++d)
			for (int i = 0; i + d < n; ++i) {
				final int j = i + d;
				if (s.charAt(i) == s.charAt(j))
					dp[i][j] = 2 + dp[i + 1][j - 1];
				else
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
			}

		return dp[0][n - 1];
	}
}