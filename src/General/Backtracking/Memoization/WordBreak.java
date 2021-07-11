package General.Backtracking.Memoization;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
	
	public static boolean wordBreak(String s, List<String> words) {
        return dfs(s, words, new StringBuilder(), new HashSet<String>());
    }
    
    public static boolean dfs(String s, List<String> words, StringBuilder path, Set<String> memo) {
        
        if (path.length() >= s.length()) {
            if (s.equals(path.toString())) return true;
            else return false;
        }
        
        for (int i = 0; i < words.size(); i++) {
        	path = new StringBuilder(path.append(words.get(i)));
        	
        	if (!memo.contains(path.toString()) && s.startsWith(path.toString())) {
        		memo.add(path.toString());
        		if (dfs(s, words, path, memo)) return true;
            }
            
        	path = new StringBuilder(path.substring(0, path.length() - words.get(i).length()));
        }
        
        return false;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }
    
    public static void main(String[] args) {
    	long startTime = System.nanoTime();
    	String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        List<String> words = splitWords("a aa aaa aaaa aaaaa aaaaaa aaaaaaa aaaaaaaa aaaaaaaaa aaaaaaaaaa");
        boolean res = wordBreak(s, words);
        System.out.println(res);
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }
}