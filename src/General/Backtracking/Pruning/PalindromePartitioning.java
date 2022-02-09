package General.Backtracking.Pruning;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * 
 * Input: aab
 * Output:[ ["aa","b"], ["a","a","b"] ]
 */
public class PalindromePartitioning {
	
	public static boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        
        while (l < r) {
            if (s.charAt(l) != s.charAt(r))
                return false;
            
            l++;
            r--;
        }
        
        return true;
    }

    public static void dfs(int start, String s, List<String> path, List<List<String>> res) {
        if (start == s.length()) {
            res.add(new ArrayList<String>(path));
            return;
        }
        
        for (int i = start; i < s.length(); i++) {
        	if (isPalindrome(s.substring(start, i + 1))) {
                path.add(s.substring(start, i + 1));
                dfs(i + 1, s, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        dfs(0, s, new ArrayList<String>(), ans);
        return ans;
    }
    
    public static void main(String[] args) {
        String s = "aba";
        List<List<String>> res = partition(s);
        for (List<String> row : res) {
            System.out.println(String.join(" ", row));
        }
    }
}