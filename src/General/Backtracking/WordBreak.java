package General.Backtracking;

import java.util.Arrays;
import java.util.List;

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
	
	private static boolean dfs(int i, Boolean[] memo, String s, List<String> words) {
        if (i == s.length()) return true;

        if (memo[i] != null) return memo[i];

        boolean ok = false;
        for (String word : words) {
            if (s.substring(i).startsWith(word)) {
                ok = ok || dfs(i + word.length(), memo, s, words);
            }
        }
        memo[i] = ok;
        return ok;
    }

    public static boolean wordBreak(String s, List<String> words) {
        return dfs(0, new Boolean[s.length()], s, words);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }
    
    public static void main(String[] args) {
        String s = "algomonster";
        List<String> words = splitWords("algo monster");
        boolean res = wordBreak(s, words);
        System.out.println(res);
    }
}