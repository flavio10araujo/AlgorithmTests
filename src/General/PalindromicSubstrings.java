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
		// String s = "ABC"; // 3
		String s = "AABA";
		
		long startTime = System.nanoTime();
		System.out.println(solution01(s));
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
		
		startTime = System.nanoTime();
		System.out.println(solution02(s));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	}
	
	/**
	 * Time complexity: O(n ^ 2).
	 * Space complexity: O(n ^ 2).
	 * @param s
	 * @return
	 */
	public static int solution01(String s) {
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
	
	/**
	 * Time complexity: O(n ^ 2)
	 * Space complexity: O(1)
	 * @param s
	 * @return
	 */
	public static int solution02(String s) {
	    int ans = 0;

	    for (int i = 0; i < s.length(); ++i) {
	      ans += extendPalindromes(s, i, i);
	      ans += extendPalindromes(s, i, i + 1);
	    }

	    return ans;
	  }

	  private static int extendPalindromes(final String s, int l, int r) {
	    int count = 0;

	    while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
	      ++count;
	      --l;
	      ++r;
	    }

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