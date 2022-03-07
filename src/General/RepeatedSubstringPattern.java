package General;

/**
 * https://leetcode.com/problems/repeated-substring-pattern/
 * 
 * Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
 * 
 * Example 1:
 * Input: s = "abab"
 * Output: true
 * Explanation: It is the substring "ab" twice.
 * 
 * Example 2:
 * Input: s = "aba"
 * Output: false
 * 
 * Example 3:
 * Input: s = "abcabcabcabc"
 * Output: true
 * Explanation: It is the substring "abc" four times or the substring "abcabc" twice.
 * 
 * Constraints:
 * 1 <= s.length <= 10 ^ 4
 * s consists of lowercase English letters.
 */
public class RepeatedSubstringPattern {

	public static void main(String[] args) {
		//String s = "abab";
		//String s = "aba";
		//String s = "ababab";
		
		//System.out.println(solution01(s));
		
		System.out.println(solution01("abab"));
		System.out.println(solution01("aba"));
		System.out.println(solution01("ababab"));
		
		System.out.println(solution02("abab"));
		System.out.println(solution02("aba"));
		System.out.println(solution02("ababab"));
	}
	
	/**
	 * Time complexity: O(n ^ 2), where n = s.length()
	 * Space complexity: O(n).
	 * @param s
	 * @return
	 */
	public static boolean solution01(String s) {
		final String ss = s + s;
	    return ss.substring(1, ss.length() - 1).contains(s);
	}
	
	public static boolean solution02(String s) {
		for (int i = s.length() / 2; i >= 1; i--) {
			if (s.length() % i == 0) {
				String substring = s.substring(0, i);
				StringBuilder sb = new StringBuilder();
				
				for (int j = 0; j < (s.length() / i); j++) {
					sb.append(substring);
				}
				
				if (sb.toString().equals(s)) {
					return true;
				}
			}
		}
		
		return false;
	}
}