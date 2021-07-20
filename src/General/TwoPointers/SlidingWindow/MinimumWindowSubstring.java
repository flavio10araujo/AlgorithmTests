package General.TwoPointers.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings, original and check, return the minimum substring of original such that each character in check, including duplicates, 
 * are included in this substring. 
 * 
 * By "minimum", I mean the shortest substring. 
 * 
 * If two substrings that satisfy the condition has the same length, the one that comes lexicographically first are smaller.
 * 
 * Parameters
 * original: The original string.
 * check: The string to check if a window contains it.
 * Result
 * The smallest substring of original that satisfy the condition.
 * 
 * Example
 * Input: original = "cdbaebaecd", check = "abc"
 * Output: baec
 * Explanation: baec is the shortest substring of original that contains all of a, b, and c.
 * 
 * Constraints
 * 1 <= len(check), len(original) <= 10^5
 * original and check both contains only upper case and lower case characters in English. The characters are case sensitive.
 */
public class MinimumWindowSubstring {

	// Change the number of "char" inside the window by "delta"
    // Automatically increase or decrease "satisfyCount" to reflect the current value.
    // Returns the new "satisfyCount".
    public static int deltaChar(char ch, int delta, int satisfyCount, Map<Character, Integer> checkCount, Map<Character, Integer> windowCount) {
        
    	if (windowCount.getOrDefault(ch, 0) >= checkCount.getOrDefault(ch, 0)) {
            satisfyCount--;
        }
    	
        windowCount.merge(ch, delta, Integer::sum);
        
        if (windowCount.getOrDefault(ch, 0) >= checkCount.getOrDefault(ch, 0)) {
            satisfyCount++;
        }
        
        return satisfyCount;
    }

    public static String getMinimumWindow(String original, String check) {
    	
        // Counts the number of each character of "check".
        Map<Character, Integer> checkCount = new HashMap<>();
        
        for (char ch : check.toCharArray()) {
            checkCount.merge(ch, 1, Integer::sum);
        }
        
        // Counts the number of each character in the sliding window.
        Map<Character, Integer> windowCount = new HashMap<>();
        
        // Count the number of entries in "checkCount" that is smaller than or equal to that in "windowCount".
        // If "satisfyCount" is equal to the number of entries in "checkCount", that window contains "check". We then just need to check if its the minimum.
        int satisfyCount = 0;
        int originalLen = original.length();
        // Two pointers pointing to the window (inclusive start, exclusive end).
        int startPtr = 0, endPtr = 0;
        // The number of entries in "checkCount". Used to check if "windowCount" contains "checkCount".
        int matchReq = checkCount.size();
        // The smallest recorded string that satisfies the conditions.
        String smallestStr = null;
        
        while (endPtr < originalLen) {
            
        	// Moves the end pointer until it contains "check" or it reaches the end.
            while (endPtr < originalLen && satisfyCount < matchReq) {
                satisfyCount = deltaChar(original.charAt(endPtr), 1, satisfyCount, checkCount, windowCount);
                endPtr++;
            }
            
            // If the window reaches the end and does not contain "check", break loop.
            if (endPtr == originalLen && satisfyCount < matchReq) {
                break;
            }
            
            // Otherwise, the window contains "check", so we move the start pointer
            // until it no longer does. Then, the one before failing the check is the local
            // minimal substring.
            while (satisfyCount >= matchReq) {
                satisfyCount = deltaChar(original.charAt(startPtr), -1, satisfyCount, checkCount, windowCount);
                startPtr++;
            }
            
            String validWindow = original.substring(startPtr - 1, endPtr);
            // Compare the local minimum to the stored smallest string.
            // If there is nothing stored, or the condition outlined is true, we store the string.
            
            if (smallestStr == null || (smallestStr.length() > validWindow.length())) {
                smallestStr = validWindow;
            } else if (smallestStr.length() == validWindow.length() && smallestStr.compareTo(validWindow) > 0) {
                smallestStr = validWindow;
            }
        }
        
        return smallestStr == null ? "" : smallestStr;
    }

    public static void main(String[] args) {
        String original = "cdbaebaecd";
        String check = "abc";
        String res = getMinimumWindow(original, check);
        System.out.println(res);
    }
}