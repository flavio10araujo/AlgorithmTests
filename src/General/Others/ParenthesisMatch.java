package General.Others;

import java.util.Stack;

/**
 * Given a string of parenthesis ‘(‘ and ‘)’, write a function which returns true if there are matching pairs and false if there are not. 
 * A matching pair means, there should be a closing parenthesis for an opening one, in correct order.
 * 
 * For example : '((()))', function should return TRUE, but ')(())' will return FALSE.
 * 
 * Cases:
 * 1. ((())) : True
 * 2. ())) : False
 * 3. )((( : False
 * 4. ( : False
 * 5 Empty string : True
 * 6. NULL pointer : False
 * 
 * Solution:
 * 
 * For each character of input string:
 * - If character is opening parenthesis '(', put it on stack.
 * - If character is closing parenthesis ')'
 * 		- Check top of stack, if it is '(' , pop and move to next character.
 * 		- If it is not '(', return false
 * - After scanning the entire string, check if stack is empty. If stack is empty, return true else return false.
 * 
 * Time complexity: O(N).
 * Space complexity: O(N) extra space for stack.
 */
public class ParenthesisMatch {
	
	public static void main(String[] args) {
        String s = "((()()))";
        System.out.println(isMatchingParenthesis(s));
    }

	public static boolean isMatchingParenthesis(String s) {
        Stack<Character> stack = new Stack<>();
 
        if (s == null) {
        	return true;
        }
 
        int len = s.length();
        
        for (int i = 0; i < len; i++) {
        	
            switch (s.charAt(i)) {
                case '(' :
                    stack.push(s.charAt(i));
                    break;
                case ')':
                    // If stack is empty, then there is an extra closing parenthesis.
                    if (stack.isEmpty()) {
                    	return false;
                    }
                    
                    stack.pop();
                    break;
                default:
                    return false;
            }
        }
        
        // If stack is empty that means it's a matching parenthesis.
        return stack.empty();
    }
}