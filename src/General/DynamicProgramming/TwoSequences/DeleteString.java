package General.DynamicProgramming.TwoSequences;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Given 2 strings determine the minimum cost required to delete characters from either string to make them equal. 
 * We also assign a particular cost to each character so that in order to remove one instance of that character from either string it will incur that cost. 
 * Only lower-case English letters will be used. 
 * The answer is guaranteed to fit in a 32-bit integer.
 * 
 * Input
 * costs: An array of size 26 that contains the cost for each character in the order of a-z.
 * s1: First string
 * s2: Second string
 * Output: Minimum cost to make the strings equal.
 * 
 * Example 1:
 * Input:
 * 		costs = [1, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
 * 		s1 = abb
 * 		s2 = bba
 * Output: 2
 * Explanation: We can remove "a" from both string to make bb in both strings with only a cost of 2.
 * 
 * Solution:
 * 
 * This problem is very similar to Edit Distance. 
 * The difference is now the only operation is delete and there is a cost for it.
 * Let dp[i][j] denote the minimum total cost to make substrings s1[:i] and s2[:j] equal. 
 * To get dp[i][j], 
 * If s1[i] and s2[j] are the same character, 
 * 	then we don't have to incur any delete cost, we simply use the total cost without the last character dp[i - 1][j - 1].
 * Else, 
 * 	we can delete the last character from either s1 or s2 and add the cost. 
 * We take the minimum of the two.
 * 
 * dp[i][j] = min(dp[i - 1][j] + cost_to_remove_last_character_from_s1, dp[i][j - 1] + cost_to_remove_last_character_from_s2)
 */
public class DeleteString {

	public static int deleteString(List<Integer> costs, String s1, String s2) {
        int [][] dp = new int[1001][1001];
        int n = s1.length();
        int m = s2.length();
        for (int i = 1; i <= n; i++) dp[i][0] = dp[i - 1][0] + costs.get(s1.charAt(i - 1) - 'a');
        for (int j = 1; j <= m; j++) dp[0][j] = dp[0][j - 1] + costs.get(s2.charAt(j - 1) - 'a');
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else {
                    dp[i][j] = Math.min(dp[i][j - 1] + costs.get(s2.charAt(j - 1) - 'a'), dp[i - 1][j] + costs.get(s1.charAt(i - 1) - 'a'));
                }
            }
        }
        return dp[n][m];
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 1 2 3 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    // abb
    // bba
    // output: 2
    
    // 1 1 3 2 5 10 2 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    // abcdefgh
    // hgfedcba
    // output: 30
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> costs = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        scanner.close();
        int res = deleteString(costs, s1, s2);
        System.out.println(res);
    }
}