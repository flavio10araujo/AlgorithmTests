package General;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/valid-parentheses/
 * 
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', 
 * determine if the input string is valid.
 * 
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * 
 * Example 1:
 * Input: s = "()"
 * Output: true
 * 
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 * 
 * Example 3:
 * Input: s = "(]"
 * Output: false
 * 
 * Example 4:
 * Input: s = "([)]"
 * Output: false
 * 
 * Example 5:
 * Input: s = "{[]}"
 * Output: true
 */
public class ValidParentheses {

	public static void main(String[] args) {
		String s = "([)]";
		System.out.println(solution02(s));
	}

	public static boolean solution01(String s) {

		if (s == null) {
			return false;
		}

		if ("".equals(s)) {
			return true;
		}

		Deque<Character> stack = new ArrayDeque<>();

		for (int i = 0; i < s.length(); i++) {

			char c = s.charAt(i);

			if (c == '(' || c == '{' || c == '[') {
				stack.push(c);
			} else {
				if (stack.isEmpty()) {
					return false;
				} else {
					char top = stack.pop();

					if (c == ')' && top != '(') {
						return false;
					}

					if (c == '}' && top != '{') {
						return false;
					}

					if (c == ']' && top != '[') {
						return false;
					}
				}
			}
		}

		if (!stack.isEmpty()) {
			return false;
		}

		return true;
	}

	public static boolean solution02(String s) {
		Deque<Character> stack = new ArrayDeque<>();

		for (char c : s.toCharArray()) {
			if (c == '(') {
				stack.push(')');
			} else if (c == '[') {
				stack.push(']');
			} else if (c == '{') {
				stack.push('}');
			} else if (stack.isEmpty() || stack.pop() != c) {
				return false;
			}
		}

		return stack.isEmpty();
	}
}