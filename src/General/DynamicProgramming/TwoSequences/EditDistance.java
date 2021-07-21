package General.DynamicProgramming.TwoSequences;

/**
 * There are two words: word1 and word2.
 * You have to find the minimum number of operations required to convert word1 to word2.
 * You are allowed to use the following 3 operations on a word:
 * - Insert a character
 * - Delete a character
 * - Replace a character
 * 
 * Example 1:
 * Input:
 * word1 = "almost"
 * word2 = "algomonster"
 * Output: 5
 * Explanation:
 * almost    ->  algmost    (insert 'g')
 * algmost   ->  algomost   (insert 'o')
 * algomost  ->  algomonst   (insert 'n')
 * algomonst ->  algomoste  (insert 'e')
 * algomoste ->  algomoster (insert 'r')
 * 
 * Example 2:
 * Input:
 * word1 = "intention"
 * word2 = "execution"
 * Output: 5
 * Explanation:
 * intention  ->  inention   (remove 't')
 * inention   ->  enention   (replace 'i' with 'e')
 * enention   ->  exention   (replace 'n' with 'x')
 * exention   ->  exection   (replace 'n' with 'c')
 * exection   ->  execution  (insert 'u')
 * 
 * Solution
 * 
 * This is the classic "Two Sequence DP".
 * Let dp[i][j] be the minimum edit distance to change from substring of word1 ending at index i to substring of word2 ending at index j.
 * If word1[i] == word2[j], then edit distance d[i][j] = dp[i - 1][j - 1] since we don't have to pay any cost if the current character is the same.
 * To get dp[i][j], we can do three things:
 * 1 - match word1[i] to word2[j] and pay the replacement cost
 * 2 - match word1[i] to blank (delete word2[j])
 * 3 - match word2[j] to blank (add word1[i])
 * 
 * Time Complexity: O(n*m)
 */
public class EditDistance {

	public static int minimum(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public static int dfs(String word1, int m, String word2, int n) {
        if (m == 0) {
            return n;
        }

        if (n == 0) {
            return m;
        }

        int cost = (word1.charAt(m - 1) == word2.charAt(n - 1)) ? 0 : 1;

        return minimum(dfs(word1, m - 1, word2, n) + 1, // remove one
                dfs(word1, m, word2, n - 1) + 1, // insertion one
                dfs(word1, m - 1, word2, n - 1) + cost); // replace one of them
    }

    public static int minDistance(String word1, String word2) {
        return dfs(word1, word1.length(), word2, word2.length());
    }
    
    public static void main(String[] args) {
        String word1 = "flavio";
        String word2 = "araujo";
        int res = minDistance(word1, word2);
        System.out.println(res);
    }
}