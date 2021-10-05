package General;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * https://leetcode.com/problems/valid-anagram/
 * 
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * 
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * 
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 */
public class ValidAnagram {

	public static void main(String[] args) {
		String s = "baaaaaaaaaaaaaaadddaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbccccccccccccccccccccccaaaaaaaaaaaaaaadddaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbccccccccccccccccccccccaaaaaaaaaaaaaaadddaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbccccccccccccccccccccccaaaaaaaaaaaaaaadddaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbccccccccccccccccccccccaaaaaaaaaaaaaaadddaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbccccccccccccccccccccccaaaaaaaaaaaaaaadddaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbcccccccccccccccccccccc";
		String t = "uaaaaaaaaaaaaaaadddaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbccccccccccccccccccccccaaaaaaaaaaaaaaadddaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbccccccccccccccccccccccaaaaaaaaaaaaaaadddaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbccccccccccccccccccccccaaaaaaaaaaaaaaadddaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbccccccccccccccccccccccaaaaaaaaaaaaaaadddaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbccccccccccccccccccccccaaaaaaaaaaaaaaadddaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbcccccccccccccccccccccc";
		
		// Marcar o tempo de execução de um algoritmo:
		long startTime = System.nanoTime();
		System.out.println(solution01(s, t));
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
		
		startTime = System.nanoTime();
		System.out.println(solution02(s, t));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	}
	
	/**
	 * O(n log n).
	 * @param s
	 * @param t
	 * @return
	 */
	public static boolean solution01(String s, String t) {
		if (s.length() != t.length()) {
            return false;
        }
        
        char[] charsA = s.toCharArray();
        char[] charsB = t.toCharArray();
        
        Arrays.sort(charsA);
        Arrays.sort(charsB);
        
        for (int i = 0; i < s.length(); i++) {
            if (charsA[i] != charsB[i]) {
                return false;
            }
        }
        
        return true;
	}
	
	public static boolean solution02(String s, String t) {
		if (s.length() != t.length()) {
            return false;
        }
		
		Map<Character, Integer> mymapS = new HashMap<>();
		Map<Character, Integer> mymapT = new HashMap<>();
		
		for (int i = 0; i < s.length(); i++) {
            if (mymapS.containsKey(s.charAt(i))) {
            	mymapS.put(s.charAt(i), mymapS.get(s.charAt(i)) + 1);
            } else {
            	mymapS.put(s.charAt(i), 1);
            }
            
            if (mymapT.containsKey(t.charAt(i))) {
            	mymapT.put(t.charAt(i), mymapT.get(t.charAt(i)) + 1);
            } else {
            	mymapT.put(t.charAt(i), 1);
            }
        }
        
        for (Entry<Character, Integer> entry : mymapS.entrySet()) {
        	int qtdS = entry.getValue();
        	
        	if (mymapT.get(entry.getKey()) == null) {
        		return false;
        	}
        	
        	int qtdT = mymapT.get(entry.getKey());
        	
        	if (qtdS != qtdT) {
        		return false;
        	}
        }
         
        return true;
	}
}