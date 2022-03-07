package General;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/word-pattern/
 * 
 * Given a pattern and a string s, find if s follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
 * 
 * Example 1:
 * Input: pattern = "abba", s = "dog cat cat dog"
 * Output: true
 * 
 * Example 2:
 * Input: pattern = "abba", s = "dog cat cat fish"
 * Output: false
 * 
 * Example 3:
 * Input: pattern = "aaaa", s = "dog cat cat dog"
 * Output: false
 * 
 * Constraints:
 * 1 <= pattern.length <= 300
 * pattern contains only lower-case English letters.
 * 1 <= s.length <= 3000
 * s contains only lowercase English letters and spaces ' '.
 * s does not contain any leading or trailing spaces.
 * All the words in s are separated by a single space.
 *
 */
public class WordPatternI {

	public static void main(String[] args) {
		//String pattern = "abba";
		//String s = "dog cat cat dog"; // true

		String pattern = "abba";
		String s = "dog dog dog dog"; // false

		System.out.println(solution01(pattern, s));
		System.out.println(solution02(pattern, s));
	}

	public static boolean solution01(String pattern, String s) {
		String[] sArr = s.split(" ");

		if (sArr.length != pattern.length()) {
			return false;
		}

		Map<Character, String> mymap = new HashMap<>();

		for (int i = 0; i < sArr.length; i++) {
			Character c = pattern.charAt(i);

			// If the character is not in the map, add it.
			if (!mymap.containsKey(c)) {
				// If the value for the new letter is already registered for other letter.
				if (mymap.containsValue(sArr[i])) {
					return false;
				} else {
					mymap.put(c, sArr[i]);
				}
			}
			// Verify if the value is the same than sArr[i].
			else if (mymap.get(c).equals(sArr[i])) {
				mymap.put(c, sArr[i]);
			}
			else {
				return false;    
			}
		}

		return true;
	}

	public static boolean solution02(String pattern, String str) {
		String[] words = str.split(" ");
		if (words.length != pattern.length())
			return false;

		Map<Character, Integer> charToIndex = new HashMap<>();
		Map<String, Integer> stringToIndex = new HashMap<>();

		for (Integer i = 0; i < pattern.length(); ++i)
			if (charToIndex.put(pattern.charAt(i), i) != stringToIndex.put(words[i], i))
				return false;

		return true;
	}
}
