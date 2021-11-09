package General.DynamicProgramming.TwoSequences;

/**
 * Given two strings word1 and word2, return the length of their longest common subsequence.
 * 
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted 
 * without changing the relative order of the remaining characters. 
 * (eg, "ace" is a subsequence of "abcde" while "aec" is not). 
 * A common subsequence of two strings is a subsequence that is common to both strings.
 * If there is no common subsequence, return 0.
 * 
 * Example 1:
 * Input:
 * word1 = "abcde"
 * word2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is ace and its length is 3.
 * 
 * Example 2:
 * Input:
 * word1 = "almost"
 * word2 = "algomonster"
 * Output: 6
 * Explanation: The longest common subsequence is almost and its length is 6.
 * 
 * Example 3:
 * Input:
 * word1 = "abc"
 * word2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 * 
 * Solution
 * 
 * 1 - match word1[i] to word2[j] and add 1 to the length of common subsequence
 * 2 - match word1[i] to blank (skip word2[j])
 * 3 - match word1[j] to blank (skip word2[i])
 * Time Complexity: O(word1.length*word2.length)
 */
public class LongestCommonSubsequence {

	public static void main(String[] args) {
		String word1 = "abcde";
		String word2 = "ace";

		long startTime = System.nanoTime();
		System.out.println(solution01(word1, word2));
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
		
		startTime = System.nanoTime();
		System.out.println(solution02(word1, word2));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	}

	/**
	 * Approach: DFS + memoization.
	 * @param word1
	 * @param word2
	 * @return
	 */
	public static int solution01(String word1, String word2) {
		return dfs(word1, word2, 0, 0, new Integer[word1.length()][word2.length()]);
	}

	public static int dfs(String word1, String word2, int index1, int index2, Integer[][] memo) {
		if (index1 >= word1.length() || index2 >= word2.length()) {
			return 0;
		}

		if (memo[index1][index2] != null) {
			return memo[index1][index2];
		}

		if (word1.charAt(index1) == word2.charAt(index2)) {
			return memo[index1][index2] = 1 + dfs(word1, word2, index1 + 1, index2 + 1, memo);
		} else {
			return memo[index1][index2] = Math.max(dfs(word1, word2, index1 + 1, index2, memo),
					dfs(word1, word2, index1, index2 + 1, memo));
		}
	}

	/**
	 * Approach: Dynamic Programming.
	 * Time complexity: O(m * n).
	 * Space complexity: O(m * n).
	 * @param text1
	 * @param text2
	 * @return
	 */
	public static int solution02(String text1, String text2) {
		
		final int m = text1.length();
		final int n = text2.length();

		// dp[i][j] := LCS's length of text1[0..i) and text2[0..j)
		int[][] dp = new int[m + 1][n + 1];

		for (int i = 1; i <= m; ++i) {
			for (int j = 1; j <= n; ++j) {
				if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
				
				printMatrix(dp);
			}
		}

		return dp[m][n];
	}
	
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			
			System.out.println(" ");
		}
		
		System.out.println("------");
	}
}