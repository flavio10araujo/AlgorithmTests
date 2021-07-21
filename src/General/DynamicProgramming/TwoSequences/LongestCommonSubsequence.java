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

    public static int longestCommonSubsequence(String word1, String word2) {
        return dfs(word1, word2, 0, 0, new Integer[word1.length()][word2.length()]);
    }
    
    public static void main(String[] args) {
        String word1 = "abcde";
        String word2 = "ace";
        int res = longestCommonSubsequence(word1, word2);
        System.out.println(res);
    }
}