package General.Backtracking.Memoization;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a string and a list of words, determine if the string can be constructed from concatenating words from the list of words.
 * A word can be used multiple times.
 * 
 * Input:
 *   s = "algomonster"
 *   words = ["algo", "monster"]
 * Output: true
 * 
 * Input:
 *   s = "aab"
 *   words = ["a", "c"]
 * Output: false
 */
public class WordBreak {

	public static void main(String[] args) {
		//String s = "algomonster";
		//List<String> words = splitWords("algo monster");
		
		//String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
		//List<String> words = splitWords("a aa aaa aaaa aaaaa aaaaaa aaaaaaa aaaaaaaa aaaaaaaaa aaaaaaaaaa");

		//String s = "aaac";
		//List<String> words = splitWords("a aa b");

		//String s = "leetcode";
		//List<String> words = splitWords("code leet");

		//String s = "catsandog";
		//List<String> words = splitWords("cats dog sand and cat catsandog");

		//String s = "applepenapple";
		//List<String> words = splitWords("apple pen");
		
		String s = "abcd";
		List<String> words = splitWords("a abc b cd");

		long startTime, endTime, timeElapsed;
		
		startTime = System.nanoTime();
		System.out.println(solution01(s, words));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);

		/*startTime = System.nanoTime();
		System.out.println(solution02(s, words));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);*/

		/*startTime = System.nanoTime();
		System.out.println(solution03(s, words));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);*/
		
		/*startTime = System.nanoTime();
		System.out.println(solution04(s, words));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);*/
	}

	/**
	 * Approach: DFS + DP Top-down with index.
	 * Time complexity: O(n ^ 3)
	 * @param s
	 * @param words
	 * @return
	 */
	public static boolean solution01(String s, List<String> words) {
		return solution01DFS(s, 0, words, new Boolean[s.length()]);
	}

	private static boolean solution01DFS(String s, int i, List<String> words, Boolean[] memo) {
		if (i == s.length()) {
			return true;
		}

		if (memo[i] != null) {
			return memo[i];
		}

		boolean ok = false;

		for (String word : words) {
			if (s.substring(i).startsWith(word)) {
				ok = ok || solution01DFS(s, i + word.length(), words, memo);
			}
			
			if (ok) {
				break;
			}
		}

		memo[i] = ok;
		return ok;
	}

	/**
	 * 
	 * @param s
	 * @param words
	 * @return
	 */
	public static boolean solution02(String s, List<String> words) {
		return solution02DFS(s, words, new StringBuilder(), new HashSet<String>());
	}

	public static boolean solution02DFS(String s, List<String> words, StringBuilder path, Set<String> memo) {

		if (path.length() >= s.length()) {
			if (s.equals(path.toString())) return true;
			else return false;
		}

		for (int i = 0; i < words.size(); i++) {
			path = new StringBuilder(path.append(words.get(i)));

			if (s.startsWith(path.toString()) && !memo.contains(path.toString())) {
				memo.add(path.toString());
				if (solution02DFS(s, words, path, memo)) return true;
			}

			path = new StringBuilder(path.substring(0, path.length() - words.get(i).length()));
		}

		return false;
	}

	/**
	 * Approach: DFS + DP Top-down with raw string.
	 * Time complexity: O(n ^ 3).
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public static boolean solution03(String s, List<String> wordDict) {
		Set<String> wordSet = new HashSet<>(wordDict);
		Map<String, Boolean> memo = new HashMap<>();
		return solution03(s, wordSet, memo);
	}

	private static boolean solution03(final String s, Set<String> wordSet, Map<String, Boolean> memo) {
		if (memo.containsKey(s))
			return memo.get(s);

		if (wordSet.contains(s)) {
			memo.put(s, true);
			return true;
		}

		// 1 <= prefix.length() < s.length()
		for (int i = 1; i < s.length(); ++i) {
			final String prefix = s.substring(0, i);
			final String suffix = s.substring(i);

			if (wordSet.contains(prefix) && solution03(suffix, wordSet, memo)) {
				memo.put(s, true);
				return true;
			}
		}

		memo.put(s, false);
		return false;
	}

	/**
	 * Approach: DP Bottom-up.
	 * Time complexity: O(n ^ 3).
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public static boolean solution04(String s, List<String> wordDict) {
		final int n = s.length();

		Set<String> wordSet = new HashSet<>(wordDict);
		boolean[] dp = new boolean[n + 1]; // dp[i] := true if s[0..i) can be segmented
		dp[0] = true;

		for (int i = 1; i <= n; ++i)
			for (int j = 0; j < i; ++j)
				// s[0..j) can be segmented and s[j..i) in wordSet
				// so s[0..i) can be segmented
				if (dp[j] && wordSet.contains(s.substring(j, i))) {
					dp[i] = true;
					break;
				}

		return dp[n];
	}

	public static List<String> splitWords(String s) {
		return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
	}
}