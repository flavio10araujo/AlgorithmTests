package General.Backtracking;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Given a pattern and a string s, find if s follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in s.
 * 
 * Input: pattern = "abab", s = "redblueredblue"
 * Output: true
 * 
 * Input: pattern = "aaaa", s = "asdasdasdasd"
 * Output: true
 * 
 * Input: pattern = "aabb", s = "xyzabcxzyabc"
 * Output: false
 */
public class WordPatternII {
	public static boolean helper(String pattern, 
			String s, 
			int i, 
			int j, 
			HashMap<Character, String> map, 
			HashSet<String> set) {
		
		if (i == pattern.length() && j == s.length()) {
			return true;
		}
		
		if (i >= pattern.length() || j >= s.length()) {
			return false;
		}

		char c = pattern.charAt(i);
		
		for (int k = j + 1; k <= s.length(); k++) {
			String sub = s.substring(j, k);
			
			if (!map.containsKey(c) && !set.contains(sub)) {
				map.put(c, sub);
				set.add(sub);
				
				if (helper(pattern, s, i + 1, k, map, set))
					return true;
				
				map.remove(c);
				set.remove(sub);
				
			} else if (map.containsKey(c) && map.get(c).equals(sub)) {
				if (helper(pattern, s, i + 1, k, map, set))
					return true;
			}
		}
		
		return false;
	}

	public static boolean wordPatternMatch(String pattern, String s) {
		if (pattern.length() == 0 && s.length() == 0)
			return true;
		
		if (pattern.length() == 0)
			return false;

		HashMap<Character, String> map = new HashMap<Character, String>();
		HashSet<String> set = new HashSet<String>();
		return helper(pattern, s, 0, 0, map, set);
	}

	public static void main(String[] args) {
		long startTime = System.nanoTime();
    	String pattern = "abab";
        String s = "redblueredblue";
        boolean res = wordPatternMatch(pattern, s);
        System.out.println(res);
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	}
}