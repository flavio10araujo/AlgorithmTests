package General;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 * 
 * Given a string s, return the longest palindromic substring in s.
 * 
 * Example 1:
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * 
 * Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 * 
 * Example 3:
 * Input: s = "a"
 * Output: "a"
 * 
 * Example 4:
 * Input: s = "ac"
 * Output: "a"
 */
public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		String s = "babba"; // abba
		//String s = "cbbd"; // bb
		//String s = "aaaaa"; // aaaaa
		//String s = "abcd"; // a

		long startTime = System.nanoTime();
		System.out.println(solution01(s));
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);

		startTime = System.nanoTime();
		System.out.println(solution02(s));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);

		startTime = System.nanoTime();
		System.out.println(solution03(s));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);

		startTime = System.nanoTime();
		System.out.println(solution04(s));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	}

	/**
	 * Naive solution.
	 * Time complexity: O(n ^ 3).
	 * Space complexity: O(1).
	 * @param s
	 * @return
	 */
	public static String solution01(String s) {

		if (s == null || "".equals(s)) {
			return "";
		} else if (s.length() == 1) {
			return s;
		}

		String sMax = "";

		for (int i = 0; i < s.length(); i++) {

			for (int j = i + 1; j <= s.length(); j++) {

				String subs = s.substring(i, j);

				if (subs.length() <= sMax.length()) {
					continue;
				}

				if (isPalindrome(subs)) {
					sMax = subs;
				}

			}
		}

		return sMax;
	}

	public static boolean isPalindrome(String s) {
		int L = 0;
		int R = s.length() - 1;

		while (L <= R) {
			if (s.charAt(L) != s.charAt(R)) {
				return false;
			} else {
				L++;
				R--;
			}
		}

		return true;
	}

	/**
	 * Using Dynamic Programming.
	 * Time complexity: O(n ^ 2).
	 * Space complexity: O(n ^ 2).
	 * @param s
	 * @return
	 */
	public static String solution02(String s) {

		if (s == null || "".equals(s)) {
			return "";
		} else if (s.length() == 1) {
			return s;
		} else if (s.length() == 2) {
			if (s.charAt(0) == s.charAt(1)) {
				return s;
			} else {
				return "" + s.charAt(0); 
			}
		}

		boolean[][] matrix = new boolean[s.length()][s.length()];

		for (int i = 0; i < s.length(); i++) {
			matrix[i][i] = true;
		}

		//printMatrix(matrix);

		String longest = s.charAt(0) + "";

		for (int i = 1; i < s.length(); i++) {
			if ((s.charAt(i - 1) == s.charAt(i))) {
				matrix[i - 1][i] = true;

				if (longest.length() == 1) {
					longest = s.substring(i - 1, i + 1);
				}
			} else {
				matrix[i - 1][i] = false;
			}
		}

		//printMatrix(matrix);

		for (int i = 2; i < s.length(); i++) {
			for (int j = 0; j < i - 1; j++) {

				if (s.charAt(i) == s.charAt(j)) {

					//System.out.println(s.substring(j, i + 1));
					//System.out.println("i="+i+" j="+j+ " matrix["+(i - 1)+"]["+(j + 1)+"]"+matrix[i - 1][j + 1]);

					if (matrix[j + 1][i - 1]) {
						matrix[j][i] = true;

						//System.out.println(s.substring(j, i + 1));

						if (((i + 1) - j) > longest.length()) {
							longest = s.substring(j, i + 1);
						}
					} else {
						//System.out.println("else");
						matrix[j][i] = false;
					}
				} else {
					matrix[j][i] = false;
				}
			}
		}

		return longest;
	}

	/**
	 * Time complexity: O(n ^ 2).
	 * Space complexity: O(n).
	 * @param s
	 * @return
	 */
	public static String solution03(String s) {
		if (s.isEmpty()) {
			return "";
		}

		// [start, end] indices of the longest palindrome in s
		int[] indices = {0, 0};

		for (int i = 0; i < s.length(); ++i) {
			int[] indices1 = extend(s, i, i);

			if (indices1[1] - indices1[0] > indices[1] - indices[0]) {
				indices = indices1;
			}

			if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
				int[] indices2 = extend(s, i, i + 1);

				if (indices2[1] - indices2[0] > indices[1] - indices[0]) {
					indices = indices2;
				}
			}
		}

		return s.substring(indices[0], indices[1] + 1);
	}

	// return [start, end] indices of the longest palindrome extended from s[i..j]
	private static int[] extend(final String s, int i, int j) {
		for (; i >= 0 && j < s.length(); --i, ++j)
			if (s.charAt(i) != s.charAt(j))
				break;

		return new int[] {i + 1, j - 1};
	}

	/**
	 * Time complexity: O(n).
	 * Space complexity: O(n).
	 * Manacher's algorithm.
	 * @param s
	 * @return
	 */
	public static String solution04(String s) {
		final String t = join('@' + s + '$', '#');
		final int n = t.length();

		// t[i - maxExtends[i]..i) ==
		// t[i + 1..i + maxExtends[i]]
		int[] maxExtends = new int[n];
		int center = 0;

		for (int i = 1; i < n - 1; ++i) {
			final int rightBoundary = center + maxExtends[center];
			final int mirrorIndex = center - (i - center);
			maxExtends[i] = rightBoundary > i && Math.min(rightBoundary - i, maxExtends[mirrorIndex]) > 0 ? 1 : 0;

			// Attempt to expand palindrome centered at i
			while (t.charAt(i + 1 + maxExtends[i]) == t.charAt(i - 1 - maxExtends[i]))
				++maxExtends[i];

			// If palindrome centered at i expand past rightBoundary,
			// adjust center based on expanded palindrome.
			if (i + maxExtends[i] > rightBoundary)
				center = i;
		}

		// Find the maxExtend and bestCenter
		int maxExtend = 0;
		int bestCenter = -1;

		for (int i = 0; i < n; ++i)
			if (maxExtends[i] > maxExtend) {
				maxExtend = maxExtends[i];
				bestCenter = i;
			}

		return s.substring((bestCenter - maxExtend) / 2, (bestCenter + maxExtend) / 2);
	}

	private static String join(final String s, char c) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); ++i) {
			sb.append(s.charAt(i));
			if (i != s.length() - 1)
				sb.append(c);
		}
		return sb.toString();
	}

	public static void printMatrix(boolean[][] matrix) {
		System.out.println("Matrix: ");
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print("matrix["+i+"]["+j+"] = "+ matrix[i][j] + "; ");
			}
			System.out.println(" ");
		}
	}
}