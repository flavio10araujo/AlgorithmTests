package General;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Group Anagrams
 * 
 * Given a list of strings, return a list of string lists that groups all anagrams together. 
 * Two strings are anagrams if rearranging one string results in another. 
 * For the purpose of this question, a string is an anagram of itself.
 * The output order does not matter as long as they are grouped correctly.
 * 
 * Input
 * strings: a list of strings.
 * Output: A list of string lists representing the grouped anagrams.
 * 
 * Example 1:
 * Input:
 * strings = ["eat" ,"tea", "tan", "ate", "nat", "bat"]
 * Output: [["ate", "eat", "tea"], ["bat"], ["nat", "tan"]]
 * 
 * Constraints
 * 1 <= len(strings) <= 10^4
 * 1 <= len(strings[i]) <= 100
 * Each word in the input consists of lowercase English letters, and they might not be unique.
 * 
 * Solution:
 * 
 * For this problem, consider what happens when two strings are anagrams: if we sort them both by character, then they will result in the same string. 
 * When two strings are not anagrams, then this value will always be different.
 * In that case, we can use a hashmap to store the information, with the key being the "anagram ID" (the sorted value of the string), 
 * and the entry being a list of strings with the same anagram IDs. 
 * Everything under the same ID must be anagrams, while everything outside of it are not.
 * It has a time complexity of O(n * mlog(m)), where n is the number of strings and m is the max size of each string.
 */
public class GroupAnagrams {

	/**
	 * Time complexity: O(n * m log m),
	 * where n is the number of strings and m is the max size of each string.
	 * 
	 * @param strings
	 * @return
	 */
	public static List<List<String>> groupAnagrams(List<String> strings) {
        Map<String, List<String>> anagramMap = new HashMap<>();
        
        for (String entry : strings) {
            
            char[] charArray = entry.toCharArray();
            
            Arrays.sort(charArray);
            
            String anagramId = new String(charArray);
            
            if (!anagramMap.containsKey(anagramId)) {
                anagramMap.put(anagramId, new ArrayList<>());
            }
            
            anagramMap.get(anagramId).add(entry);
        }
        
        return new ArrayList<>(anagramMap.values());
    }
	
	/**
	 * 
	 * @param strings
	 * @return
	 */
	public static List<List<String>> groupAnagrams02(List<String> strings) {

		List<List<String>> res = new ArrayList<>();

		if (strings == null || strings.size() == 0) {
			return res;
		}

		int n = strings.size();

		for (int i = 0; i < n; i++) {
			String s1 = strings.get(i);

			if (s1 == null) {
				continue;
			}

			List<String> l1 = new ArrayList<>();
			l1.add(s1);

			for (int j = i + 1; j < n; j++) {
				String s2 = strings.get(j); 

				if (isAnagram(s1, s2)) {
					l1.add(s2);
					strings.set(j, null);
				}
			}

			res.add(l1);
		}

		return res;
	}

	public static boolean isAnagram(String s1, String s2) {

		if (s1 == null || s2 == null) {
			return false;
		}

		if (s1.length() != s2.length()) {
			return false;
		}

		char[] temp = s2.toCharArray(); 

		for (int i = 0; i < s1.length(); i++) {

			boolean found = false;

			for (int j = 0; j < temp.length; j++) {

				if (temp[j] == s1.charAt(i)) {
					temp[j] = '*';
					found = true;
					break;
				}
			}

			if (!found) {
				return false;
			} 
		}

		return true;
	}

	public static List<String> splitWords(String s) {
		return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
	}

	// eat tea tan ate nat bat
	// output: ate eat tea; bat; nat tan
	
	// abba baba baab abba bbba abab aaab aaba abaa aaaa bbab bbaa abbb
	// output: aaaa; aaab aaba abaa; abab abba abba baab baba bbaa; abbb bbab bbba
	public static void main(String[] args) {
		// Marcar o tempo de execução de um algoritmo:
		long startTime = System.nanoTime();
		
		Scanner scanner = new Scanner(System.in);
		List<String> strings = splitWords(scanner.nextLine());
		scanner.close();
		List<List<String>> res = groupAnagrams02(strings);
		for (List<String> row : res) {
			Collections.sort(row);
		}
		Collections.sort(res, (a, b) -> a.get(0).compareTo(b.get(0)));
		for (List<String> row : res) {
			System.out.println(String.join(" ", row));
		}
		
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	}
}
