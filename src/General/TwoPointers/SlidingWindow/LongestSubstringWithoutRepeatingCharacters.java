package General.TwoPointers.SlidingWindow;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Find the length of the longest substring of a given string without repeating characters.
 * Input: abccabcabcc
 * Output: 3
 * Explanation: longest substrings are abc, cab, both of length 3.
 * 
 * Input: aaaabaaa
 * Output: 2
 * Explanation: ab is the longest substring, length 2
 */
public class LongestSubstringWithoutRepeatingCharacters {
	
	public static int longestSubstringWithoutRepeatingCharacters(String s) {    
		int n = s.length();
        int longest = 0, l = 0, r = 0;
        Set<Character> window = new HashSet<>();
        
        while (r < n) {
            if (!window.contains(s.charAt(r))) {
                window.add(s.charAt(r));
                r++;
            } else {
                window.remove(s.charAt(l));
                l++;
            }
            
            longest = Math.max(longest, r - l);
        }
        
        return longest;
    }
	
	public static int longestSubstringWithoutRepeatingCharacters02(String s) {
        
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int total = 1;
        int n = 1;
        Deque<Character> stack = new ArrayDeque<>();
        stack.add(s.charAt(0));
        
        for (int i = 1; i < s.length(); i++) {
            n++;
            
            if (stack.contains(s.charAt(i))) {
                stack.add(s.charAt(i));
                
                while(true) {
                    n--;
                    Character c = stack.pop();
                    
                    if (c == s.charAt(i)) {
                        break;
                    }
                }
            } else {
                stack.add(s.charAt(i));
            }
            
            if (n > total) {
                total = n;
            }
        }
        
        return total;
    }

    public static void main(String[] args) {
    	long startTime = System.nanoTime();
    	
    	String s = "abcdbea";
        int res = longestSubstringWithoutRepeatingCharacters02(s);
        System.out.println(res);
        
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }
}