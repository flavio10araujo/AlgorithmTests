package General;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/basic-calculator/
 * 
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, 
 * and return the result of the evaluation.
 * 
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 * 
 * Input: s = "1 + 1"
 * Output: 2
 * 
 * Input: s = " 2-1 + 2 "
 * Output: 3
 * 
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 */
public class BasicCalculator {

	public static void main(String[] args) {
		String s = "1 + 1";
		//String s = " 2-1 + 2 ";
		//String s = "(1+(4+5+2)-3)+(6+8)";
		System.out.println(calculate(s));
	}

	public static int calculate(String s) {
		int res = 0;
		int sign = 1;
		
		Deque<Integer> stack = new ArrayDeque<>();
		
		for (int i = 0; i < s.length(); ++i) {
			
			char c = s.charAt(i);
			
			if (c == '+') {
				sign = 1;
			} else if (c == '-') {
				sign = -1;
			} else if (Character.isDigit(c)) {
				int num = 0;
				
				for (int j = i; j < s.length() && Character.isDigit(s.charAt(j)); ++j) {
					num *= 10;
					num += (s.charAt(j) - '0');
					i = j;
				}
				
				res += sign * num;
			} else if (c == '(') {
				stack.push(res);
				stack.push(sign);
				res = 0;
				sign = 1;
			} else if (c == ')') {
				res *= stack.pop();
				res += stack.pop();
			}
		}
		
		return res;
	}
}
