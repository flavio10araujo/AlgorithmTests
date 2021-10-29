package General;

/**
 * https://leetcode.com/problems/palindromic-substrings/
 * 
 * Given a string s, return the number of palindromic substrings in it.
 * A string is a palindrome when it reads the same backward as forward.
 * A substring is a contiguous sequence of characters within the string.
 * 
 * Example 1:
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * 
 * Example 2:
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 * 
 * Constraints:
 * 1 <= s.length <= 1000
 * s consists of lowercase English letters.
 */
public class PalindromicSubstrings {

	public static void main(String[] args) {
		// String s = "AAA"; // 6
		String s = "ABC"; // 3
		
		System.out.println(countSubstrings(s));
	}
	
	public static int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int count = s.length();
        
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (i >= j) { 
                    dp[i][j] = true;
                } else {
                    dp[i][j] = false;
                }
            }
        }
        
        printMatrix(dp);
        
        for (int i = s.length() - 2; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                
            	System.out.println("i="+i+" j="+j);
            	
            	if (s.charAt(i) == s.charAt(j)) {
                    
                	if (
                        ((i + 1) < s.length()) && 
                        ((j - 1) >= 0) &&
                        (dp[i + 1][j - 1])
                    ) {
                		dp[i][j] = true;
                        count++;
                        
                        System.out.println("ENTROU i="+i+" j="+j);
                    }
                }
            }         
        }
        
        printMatrix(dp);
        
        return count;
    }
	
	public static void printMatrix(boolean[][] matrix) {
		System.out.println("Matrix: ");
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print("matrix["+i+"]["+j+"] = "+ matrix[i][j] + "; ");
			}
			System.out.println(" ");
		}
	}
}