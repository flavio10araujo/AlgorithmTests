package General;

/**
 * https://leetcode.com/problems/valid-palindrome-ii/
 * 
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 * 
 * Example 1:
 * Input: s = "aba"
 * Output: true
 * 
 * Example 2:
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 * 
 * Example 3:
 * Input: s = "abc"
 * Output: false
 * 
 * Constraints:
 * 1 <= s.length <= 10 ^ 5
 * s consists of lowercase English letters.
 */
public class ValidPalindromeII {

	public static void main(String[] args) {
		System.out.println(solution01("aba")); // true
		System.out.println(solution01("abca")); // true
		System.out.println(solution01("cuaaucu")); // true
		System.out.println(solution01("abc")); // false
	}

	/**
	 * Time complexity: O(n).
	 * Space complexity: O(1).
	 * @param s
	 * @return
	 */
	public static boolean solution01(String s) {
		int L = 0, R = s.length() - 1;

		while(L < R) {
			if (s.charAt(L) == s.charAt(R)) {
				L++;
				R--;
				continue;
			}

			return isPalindrome(s, L + 1, R) || isPalindrome(s, L, R - 1);
		}

		return true;
	}
	
	private static boolean isPalindrome(String s, int L, int R) {
        while(L < R) {
            if (s.charAt(L) != s.charAt(R)) {
                return false;
            }
            
            L++;
            R--;
        }
        
        return true;
    }
}