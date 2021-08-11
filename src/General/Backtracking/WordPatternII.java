package General.Backtracking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
	
	public static boolean helper(String pattern,  String s,  int i,  int j, Map<Character, String> map,  Set<String> set) {

		if (i == pattern.length() && j == s.length()) {
			return true;
		}

		if (i >= pattern.length() || j >= s.length()) {
			return false;
		}

		char c = pattern.charAt(i);
		
		for (int k = j + 1; k <= s.length(); k++) {
			
			String sub = s.substring(j, k);
			
			System.out.println("c="+c+" k="+k+" j="+j+" i="+i+" sub="+sub);
			
			if (!map.containsKey(c) && !set.contains(sub)) {
				map.put(c, sub);
				set.add(sub);
				
				System.out.println("map="+map+" set="+set);
				
				if (helper(pattern, s, i + 1, k, map, set)) {
					return true;
				}
				
				map.remove(c);
				set.remove(sub);
				
				System.out.println("map="+map+" set="+set);
				
			} else if (map.containsKey(c) && map.get(c).equals(sub)) {
				if (helper(pattern, s, i + 1, k, map, set)) {
					return true;
				}
			}
		}
		
		return false;
	}

	public static boolean wordPatternMatch(String pattern, String s) {
		if (pattern.length() == 0 && s.length() == 0) {
			return true;
		}
		
		if (pattern.length() == 0) {
			return false;
		}

		Map<Character, String> map = new HashMap<Character, String>();
		Set<String> set = new HashSet<String>();
		
		return helper(pattern, s, 0, 0, map, set);
	}

	public static void main(String[] args) {
		long startTime = System.nanoTime();
    	String pattern = "abab";
        String s = "redareda";
        boolean res = wordPatternMatch(pattern, s);
        System.out.println(res);
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	}
}