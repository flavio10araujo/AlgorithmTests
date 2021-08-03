package General.DynamicProgramming.ZeroOneKnapsackProblem;

import java.util.Scanner;

/**
 * Given 2 strings determine how many distinct subsequences of s equal t. 
 * A subsequence of s is a the original sequence s with some characters deleted. 
 * The answer is guaranteed to be less than 2^31-1.
 * 
 * Constraints: 1 <= s.length, t.length <= 100001
 * 
 * The strings s and t will only contain lowercase english letters
 * 
 * Example 1
 * Input: s = "rabbbit", t = "rabbit"
 * Output: 3
 * Explanation: You can remove any of the 3 middle b values from s to make the t value
 * 
 * Solution:
 * 
 * The solution is to use dynamic programming to compute the answer.
 * 
 * Rephrasing the question
 * Let us rephrase the question as opposed to removing letters from s an equivalent way of thinking about 
 * this is how many ways can t be generated using letters from s. 
 * From this we can make an array proportionate to the size of t and check where the string t has matching letters with s. 
 * This essentially changes the question to more of a knapsack dp type question where instead we are looking for total number of combinations. 
 * If you recall knapsack is a technique for choosing objects to put into a knapsack of sorts. 
 * We can think of this question as the number of ways that we can choose characters from s from which to create the string t.
 * 
 * State transition
 * Let us define i as a particular index in string s and j as a particular index in t. 
 * Let dp[j] denote the number of ways one can make the substring of t from indices 0 to j using the first i letters from s. 
 * Our current dp state and transition is dp[i][j] = dp[i - 1][j] (+ dp[i - 1][j - 1] if s[i] == t[j]). 
 * This means
 * - dp[i][j] = dp[i - 1][j] means if we ignore whether the i-th characters[i] is the same as t[j], 
 * 	 we still have at least the number of way we can get t[j] using the first i - 1 characters in s.
 * - if s[i] == t[j], then we can add one way for each way we can make s[i - 1] == s[j -- 1].
 */
public class SequenceCheck {

	public static int sequenceCheck(String s, String t) {
        // Initialize dp array.
        int [] dp = new int [t.length() + 1];
        dp[0] = 1;
        
        // Check every character in s.
        for (int i = 0; i < s.length(); i++) {
            // Loop backwards to make sure we don't reuse characters, similar to knapsack dp.
            for (int j = t.length(); j >= 1; j--) {
                // Check if characters match, if so we add to combination.
                if (s.charAt(i) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        
        return dp[t.length()];
    }

	/*
	 rabbbit
	 rabbit
	 Output: 3
	 */
	/*
	 aaaaaaaaaaaaaaaa
	 aaa
	 Output: 560
	 */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String t = scanner.nextLine();
        scanner.close();
        int res = sequenceCheck(s, t);
        System.out.println(res);
    }
}