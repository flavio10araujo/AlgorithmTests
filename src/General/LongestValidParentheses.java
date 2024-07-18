package General;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/
 * <p>
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * <p>
 * Example 1:
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * <p>
 * Example 2:
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * <p>
 * Example 3:
 * Input: s = ""
 * Output: 0
 * <p>
 * Constraints:
 * 0 <= s.length <= 3 * 10 ^ 4
 * s[i] is '(', or ')'.
 */
public class LongestValidParentheses {

    public static void main(String[] args) {
        //String s = "(()"; // 2
        //String s = ")()())"; // 4
        //String s = ")("; // 0
        //String s = "()(())"; // 6
        //String s = "()(()"; // 2
        //String s = ")()())()()("; // 4
        //String s = ")))()"; // 2
        String s = ")()(()()("; // 4

        System.out.println(longestValidParenthesesNaiveSolution(s));
        System.out.println(solution01(s));
        System.out.println(solution02(s));
        System.out.println(solution03(s));
    }

    /**
     * Approach: Brute force
     * Time complexity: O(n ^ 3)
     *
     * @param s
     * @return
     */
    public static int longestValidParenthesesNaiveSolution(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) {
            return 0;
        }

        int longest = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                for (int j = s.length() - 1; j > i; j--) {
                    if (s.charAt(j) == ')') {
                        if (isMatchingParenthesis(s.substring(i, j + 1))) {
                            longest = Math.max(longest, s.substring(i, j + 1).length());
                        }
                    }
                }
            }
        }

        return longest;
    }

    public static boolean isMatchingParenthesis(String s) {
        Stack<Character> stack = new Stack<>();

        if (s == null) {
            return true;
        }

        int len = s.length();

        for (int i = 0; i < len; i++) {

            switch (s.charAt(i)) {
            case '(':
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

        return stack.empty();
    }

    /**
     * Approach: stack
     * Time complexity: O(n).
     * Space complexity: O(n).
     *
     * @param s
     * @return
     */
    public static int solution01(String s) {
        Stack<Integer> st = new Stack<>();
        st.push(-1);

        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                st.push(i);
            } else {
                st.pop();

                if (st.isEmpty()) {
                    st.push(i);
                } else {
                    int len = i - st.peek();
                    max = Math.max(max, len);
                }
            }
        }

        return max;
    }

    /**
     * Approach: DP
     * Time complexity: O(n).
     * Space complexity: O(n).
     *
     * @param s
     * @return
     */
    public static int solution02(String s) {
        final String s2 = ")" + s;

        // dp[i] := length of longest valid parentheses substring of s2[1..i]
        int[] dp = new int[s2.length()];

        for (int i = 1; i < s2.length(); ++i)
            if (s2.charAt(i) == ')' && s2.charAt(i - dp[i - 1] - 1) == '(')
                dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2;

        return Arrays.stream(dp).max().getAsInt();
    }

    /**
     * Approach: magic.
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param s
     * @return
     */
    public static int solution03(String s) {
        int open = 0, close = 0;
        int max = 0;

        // 0 -- n
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                open++;
            } else {
                close++;
            }

            if (open == close) {
                int len = open + close;
                max = Math.max(max, len);
            } else if (close > open) {
                open = close = 0;
            }
        }

        open = close = 0;

        // n -- 0
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (c == '(') {
                open++;
            } else {
                close++;
            }

            if (open == close) {
                int len = open + close;
                max = Math.max(max, len);
            } else if (open > close) {
                open = close = 0;
            }
        }

        return max;
    }
}
