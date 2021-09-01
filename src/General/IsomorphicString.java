package General;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Isomorphic String
 * 
 * Given two strings, determine if they are isomorphic. 
 * Two strings are "isomorphic" if each unique character from the first string can be replaced to match the second string, 
 * without changing the ordering of the characters.
 * No two characters can map to the same character, but a character can map to itself.
 * 
 * Input
 * str_1: the first string.
 * str_2: the second string.
 * Output: Whether if these two strings are isomorphic.
 * 
 * Example 1:
 * Input:
 * str_1 = "egg"
 * str_2 = "all"
 * Output: true
 * Explanation: "E" can be replaced with "A" and "G" can be replaced with "L". Therefore, the strings are isomorphic.
 * 
 * Example 2:
 * Input:
 * str_1 = "wow"
 * str_2 = "aaa"
 * Output: false
 * Explanation: "W" and "O" both maps to "A", which makes them not isomorphic.
 * 
 * Constraints
 * 1 <= len(str_1), len(str_2) <= 5 * 10^4
 * The strings are case sensitive and consists of valid ASCII characters.
 * 
 * Solution:
 * 
 * This problem is meant demonstrate solving a problem using multiple data structures - a map for storing mappings and a set to record existence.
 * There are two conditions to meet:
 * 1. Each character from the first string only matches one character from the second string. 
 * 	  For example, 'aba' and 'cde' are not isomorphic because 'a' from the first string matches 'c' in position 0 of the second string 
 * 	  but also matches 'e' in position 2. This is relatively easy to check, we can traverse both strings and construct a mapping of character 
 * 	  from the first string to the second one. If we run into a character from the first string that we have seen before, 
 * 	  we check in the mapping and see if the character in the second string is the same. 
 * 	  If not, we have found an inconsistency and return false.
 * 2. No two characters from the first string maps to the same character in the second string. 
 * 	  Example 2 in the description is one such example. To check this, we need to know which characters from the second string 
 * 	  have already been matched as we traverse the strings. We can use a set to record it.
 * 
 * The time complexity is O(n), where n is the size of the strings.
 */
public class IsomorphicString {

	public static boolean isIsomorphic(String str1, String str2) {
		
		Map<Character, Character> characterMap = new HashMap<>();
		Set<Character> used = new HashSet<>();
		
		if (str1.length() != str2.length()) {
			return false;
		}
		
		int n = str1.length();
		
		for (int i = 0; i < n; i++) {
			
			char aChar = str1.charAt(i);
			char bChar = str2.charAt(i);
			
			if (characterMap.containsKey(aChar)) {
				if (characterMap.get(aChar) != bChar) {
					return false;
				}
			} else {
				if (used.contains(bChar)) {
					return false;
				}
				
				characterMap.put(aChar, bChar);
				used.add(bChar);
			}
		}
		
		return true;
	}

	public static boolean isIsomorphic02(String str1, String str2) {

		/*if (str1 == null || str2 == null) {
			return false;
		}*/

		if (str1.length() != str2.length()) {
			return false;
		}

		Map<Character, Character> myMap = new HashMap<>();

		char[] arr1 = str1.toCharArray();
		char[] arr2 = str2.toCharArray();

		for (int i = 0; i < arr1.length; i++) {
			if (myMap.containsValue(arr2[i]) || myMap.containsKey(arr1[i])) {
				Character entry = myMap.get(arr1[i]);

				if (entry == null) {
					return false;
				} else if (entry != arr2[i]) {
					return false;
				}
			} else {
				myMap.put(arr1[i], arr2[i]);
			}
		}

		return true;
	}

	// egg
	// all
	// output: true

	// wow
	// aaa
	// output: false

	// isomorphism
	// esatopriest
	// output: false

	// Suspect
	// sUsPeCt
	// output: false
	public static void main(String[] args) {
		// Marcar o tempo de execução de um algoritmo:
		long startTime = System.nanoTime();

		Scanner scanner = new Scanner(System.in);
		String str1 = scanner.nextLine();
		String str2 = scanner.nextLine();
		scanner.close();
		boolean res = isIsomorphic02(str1, str2);
		System.out.println(res);

		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	}
}