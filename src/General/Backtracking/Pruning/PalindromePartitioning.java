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
        while (l<r) {
            if (s.charAt(l) != s.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }

    public static void dfs(List<List<String>> ans, ArrayList<String> part, String s, int start) {
        if (start == s.length()) {
            List<String> li = new ArrayList<>(part);
            ans.add(li);
        }
        
        for (int i = start; i<s.length(); i++) {
            if (isPalindrome(s.substring(start, i + 1))) {
                part.add(s.substring(start, i + 1));
                dfs(ans, part, s, i + 1);
                part.remove(part.size() - 1);
            }
        }
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        dfs(ans, new ArrayList<String>(), s, 0);
        return ans;
    }
    
    public static void main(String[] args) {
        String s = "abcba";
        List<List<String>> res = partition(s);
        for (List<String> row : res) {
            System.out.println(String.join(" ", row));
        }
    }
}