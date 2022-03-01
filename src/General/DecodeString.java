package General;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/decode-string/
 * 
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. 
 * Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. 
 * For example, there will not be input like 3a or 2[4].
 * 
 * Example 1:
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * 
 * Example 2:
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * 
 * Example 3:
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 * 
 * Constraints:
 * 1 <= s.length <= 30
 * s consists of lowercase English letters, digits, and square brackets '[]'.
 * s is guaranteed to be a valid input.
 * All the integers in s are in the range [1, 300].
 */
public class DecodeString {

	public static void main(String[] args) {
		//String s = "3[a]2[bc]";
		String s = "3[a2[c]]";
		//String s = "2[abc]3[cd]ef";
		//String s = "100[leetcode]";
		System.out.println(decodeString(s));
	}
	
	public static String decodeString(String s) {
		
		StringBuilder sb = new StringBuilder("");
		Deque<Integer> stack = new ArrayDeque<>();
		int indexNumber = 0;
		
		for (int i = 0; i < s.length(); i++) {
			Character c = s.charAt(i);
			
			if (c == '[') {
				stack.push(i);
				continue;
			}
			
			if (c == ']' && stack.size() == 1) {
				int indexStart = stack.pop();
				int n = Integer.parseInt(s.substring(indexNumber, indexStart) + "");
				String subs = s.substring(indexStart + 1, i);
				
				if (subs.indexOf('[') >= 0) {
					subs = decodeString(subs);
				} 
				
				sb.append(createString(n, subs));
				
				indexNumber = i + 1;
			}
			else if (c == ']' && stack.size() > 1) {
				stack.pop();
			}
			
			if (Character.isLetter(c) && stack.size() == 0) {
				sb.append(c);
				indexNumber++;
			}
		}
		
		return sb.toString();
	}
	
	private static String createString(int n, String s) {
		StringBuilder sb = new StringBuilder("");
		
		while (n > 0) {
			sb.append(s);
			n--;
		}
		
		return sb.toString();
	}
}