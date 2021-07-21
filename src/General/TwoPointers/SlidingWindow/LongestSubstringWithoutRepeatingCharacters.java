package General.TwoPointers.SlidingWindow;

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
            System.out.println("r="+r+" l="+l);
            System.out.println(window);
        	
        	if (!window.contains(s.charAt(r))) {
                window.add(s.charAt(r));
                r++;
            } else {
                window.remove(s.charAt(l));
                l++;
            }
            
            longest = Math.max(longest, r - l);
            
            System.out.println("longest="+longest);
        }
        
        return longest;
    }

    public static void main(String[] args) {
        String s = "abcdbea";
        int res = longestSubstringWithoutRepeatingCharacters(s);
        System.out.println(res);
    }
}