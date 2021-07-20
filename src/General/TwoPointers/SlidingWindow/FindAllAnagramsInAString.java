package General.TwoPointers.SlidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given a string original and a string check, find the starting index of all substrings of original that is an anagram of check. 
 * The output must be sorted in ascending order.
 * 
 * Parameters
 * original: A string
 * check: A string
 * Result
 * A list of integers representing the starting indices of all anagrams of check.
 * 
 * Example 1
 * Input: original = "cbaebabacd", check = "abc"
 * Output: [0, 6]
 * Explanation: The substring from 0 to 2, "cba", is an anagram of "abc", and so is the substring from 6 to 8, "bac".
 * 
 * Example 2
 * Input: original = "abab", check = "ab"
 * Output: [0, 1, 2]
 * Explanation: All substrings with length 2 from "abab" is an anagram of "ab".
 * 
 * Constraints
 * 1 <= len(original), len(check) <= 10^5
 * Each string consists of only lowercase characters in standard English alphabet.
 */
public class FindAllAnagramsInAString {

	// Change the number of "letter" inside the window by "delta"
    // Automatically increase or decrease "match_count" to reflect the current value.
    public static int changeLetter(char letter, int delta, Map<Character, Integer> letterCount, Map<Character, Integer> windowLetterCount, int oldMatch) {
        if (!windowLetterCount.containsKey(letter)) {
            windowLetterCount.put(letter, 0);
        }
        if (letterCount.containsKey(letter) && windowLetterCount.get(letter) == letterCount.get(letter)) {
            oldMatch--;
        }
        windowLetterCount.merge(letter, delta, Integer::sum);
        if (letterCount.containsKey(letter) && windowLetterCount.get(letter) == letterCount.get(letter)) {
            oldMatch++;
        }
        return oldMatch;
    }

    public static List<Integer> findAllAnagrams(String original, String check) {
        
    	// Counts the number of each character of "check".
        Map<Character, Integer> letterCount = new HashMap<>();
        
        for (char ch : check.toCharArray()) {
            letterCount.merge(ch, 1, Integer::sum);
        }
        
        // Counts the number of each character in the sliding window.
        Map<Character, Integer> windowLetterCount = new HashMap<>();
        
        // Counts how many character count in "letterCount" matches that of "windowLetterCount".
        // If all matches and the number of characters is the same, that window is an anagram of "check".
        int matchCount = 0;
        int checkLen = check.length();
        int originalLen = original.length();
        // The value that "match_count" must equal for "letter_count" and "window_letter_count" to match.
        int matchThreshold = letterCount.size();
        // Stores the list of starting indices to be returned.
        List<Integer> returnList = new ArrayList<>();
        
        // Edge case, if this happens, there is no way for there to be an anagram substring so just return the empty list.
        if (originalLen < checkLen) {
            return returnList;
        }
        
        // Initialize the starting window with the first substring.
        for (int i = 0; i < checkLen; i++) {
            matchCount = changeLetter(original.charAt(i), 1, letterCount, windowLetterCount, matchCount);
        }
        
        if (matchCount == matchThreshold) {
            returnList.add(0);
        }
        
        // Move the sliding window each cycle and check if the window is an anagram of "check"
        for (int i = 0; i < originalLen - checkLen; i++) {
            matchCount = changeLetter(original.charAt(i + checkLen), 1, letterCount, windowLetterCount, matchCount);
            matchCount = changeLetter(original.charAt(i), -1, letterCount, windowLetterCount, matchCount);
            if (matchCount == matchThreshold) {
                returnList.add(i + 1);
            }
        }
        
        return returnList;
    }
    
    public static void main(String[] args) {
        String original = "cbaebabacd";
        String check = "abc";
        List<Integer> res = findAllAnagrams(original, check);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}