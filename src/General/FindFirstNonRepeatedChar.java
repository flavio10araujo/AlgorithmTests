package General;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Program to find the first non repeated char in a word.
 * 
 * Ex.:
 * 	 java. Answer: j	
 *   swiss. Answer: w
 * 	 jojoco. Answer: c
 */
public class FindFirstNonRepeatedChar {

	public static void main(String[] args) {
		String s = "jojoco";
		FindFirstNonRepeatedChar.Solution01(s);
		FindFirstNonRepeatedChar.Solution02(s);
		FindFirstNonRepeatedChar.Solution03(s);
		FindFirstNonRepeatedChar.Solution04(s);
	}

	/**
	 * Naive solution.
	 * Time complexity: O(n ^ 2).
	 */
	public static void Solution01(String s) {
		System.out.println("### Solution 01 ###");
		
		String[] sArr = s.split("");

		String[] resultArr = new String[sArr.length];
		int[] result2Arr = new int[sArr.length];

		String first = "";

		for (int i = 0; i < sArr.length; i++) {

			resultArr[i] = sArr[i];
			result2Arr[i] = 1;

			for (int j = 0; j < sArr.length; j++) {
				if (i != j) {
					if (sArr[i].equals(sArr[j])) {
						result2Arr[i] = result2Arr[i] + 1;
					}
				}
			}
		}

		System.out.println(Arrays.toString(resultArr));
		System.out.println(Arrays.toString(result2Arr));

		for (int j = 0; j < sArr.length; j++) {
			if (result2Arr[j] == 1) {
				first = resultArr[j];
				j = sArr.length;
			}
		}

		System.out.println(first);
	}

	/**
	 * Solution with a LinkedHashMap.
	 * Time complexity: O(2n) or O(n).
	 * 
	 *  Step 1: get character array and loop through it to build a hash table with char and their count.
	 *  Step 2: loop through LinkedHashMap to find an entry with value 1, that's your first non-repeated character, as LinkedHashMap maintains insertion order.
     */
	public static void Solution02(String s) {
		System.out.println("### Solution 02 ###");
		
		if (s == null || "".equals(s.trim())) {
			System.out.println("String not valid.");
		} else {
			s = s.toLowerCase();
		}

		String[] sArr = s.split("");
		Map<String, Integer> myMap = new LinkedHashMap<String, Integer>();

		for (int i = 0; i < sArr.length; i++) {
			if (!myMap.containsKey(sArr[i])) {
				myMap.put(sArr[i], 1);
			} else {
				myMap.put(sArr[i], myMap.get(sArr[i]) + 1);
			}
		}

		System.out.println(myMap.toString());
		
		label : {
			for (Entry<String, Integer> entry : myMap.entrySet()) {
				if (entry.getValue() == 1) {
					System.out.println(entry.getKey());
					break label;
				}
			}
		}
	}
	
	/**
	 * Using one Set and one List.
	 * Time complexity: O(n).
	 * Space complexity: it uses two storages.
	 * 
     * Finds first non repeated character in a String in just one loop.
     * It uses two storage to cut down one iteration, standard space vs time trade-off.
     * Since we store repeated and non-repeated character separately, at the end of iteration, first element from List is our first non repeated character from String.
     */
    public static void Solution03(String s) {
    	System.out.println("### Solution 03 ###");
    	
    	Set<Character> repeating = new HashSet<>();
        List<Character> nonRepeating = new ArrayList<>();
        
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            
            if (repeating.contains(letter)) {
                continue;
            }
            
            if (nonRepeating.contains(letter)) {
                nonRepeating.remove((Character) letter);
                repeating.add(letter);
            } else {
                nonRepeating.add(letter);
            }
        }
        
        System.out.println(nonRepeating);
        System.out.println(nonRepeating.get(0));
    }

    /**
     * Using HashMap.
     * Time complexity: O(2n)
     * 
     * The same than the Solution02, but with a HashMap instead of a LinkedHashMap.
     */
    public static void Solution04(String word) {
    	System.out.println("### Solution 04 ###");
    	
        HashMap<Character, Integer> scoreboard = new HashMap<>();
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            
            if (scoreboard.containsKey(c)) {
                scoreboard.put(c, scoreboard.get(c) + 1);
            } else {
                scoreboard.put(c, 1);
            }
        }
        
        // Since HashMap doesn't maintain order, going through string again.
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            
            if (scoreboard.get(c) == 1) {
                System.out.println(c);
                i = word.length();
            }
        }
    }
}