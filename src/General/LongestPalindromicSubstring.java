package General;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 * 
 * Given a string s, return the longest palindromic substring in s.
 * 
 * Example 1:
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * 
 * Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 * 
 * Example 3:
 * Input: s = "a"
 * Output: "a"
 * 
 * Example 4:
 * Input: s = "ac"
 * Output: "a"
 */
public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		//String s = "babba"; // abba
		//String s = "cbbd"; // bb
		//String s = "aaaaa"; // aaaaa
		String s = "abcd"; // a
		//System.out.println(longestPalindrome(s));
		System.out.println(solution02(s));
	}

	/**
	 * Naive solution.
	 * Time complexity: O(n ^ 3).
	 * 
	 * @param s
	 * @return
	 */
	public static String longestPalindrome(String s) {
		
		if (s == null || "".equals(s)) {
			return "";
		} else if (s.length() == 1) {
			return s;
		}

		String sMax = "";
		
		for (int i = 0; i < s.length(); i++) {
			
			for (int j = i + 1; j <= s.length(); j++) {
			
				String subs = s.substring(i, j);
				
				if (subs.length() < sMax.length()) {
					continue;
				}
				
				if (isPalindrome(subs)) {
					sMax = subs;
				}
			
			}
		}
		
		return sMax;
	}
	
	public static boolean isPalindrome(String s) {
		int L = 0;
		int R = s.length() - 1;
		
		while (L <= R) {
			if (s.charAt(L) != s.charAt(R)) {
				return false;
			} else {
				L++;
				R--;
			}
		}
		
		return true;
	}

	/**
	 * Using Dynamic Programming.
	 * Time Complexity: O(n ^ 2).
	 * @param s
	 * @return
	 */
	public static String solution02(String s) {
		
		if (s == null || "".equals(s)) {
			return "";
		} else if (s.length() == 1) {
			return s;
		} else if (s.length() == 2) {
			if (s.charAt(0) == s.charAt(1)) {
				return s;
			} else {
				return "" + s.charAt(0); 
			}
		}
		
		boolean[][] matrix = new boolean[s.length()][s.length()];
		
		for (int i = 0; i < s.length(); i++) {
			matrix[i][i] = true;
		}
		
		//printMatrix(matrix);
		
		String longest = s.charAt(0) + "";
		
		for (int i = 1; i < s.length(); i++) {
			if ((s.charAt(i - 1) == s.charAt(i))) {
				matrix[i - 1][i] = true;
				
				if (longest.length() == 1) {
					longest = s.substring(i - 1, i + 1);
				}
			} else {
				matrix[i - 1][i] = false;
			}
		}
		
		//printMatrix(matrix);
		
		for (int i = 2; i < s.length(); i++) {
			for (int j = 0; j < i - 1; j++) {
				
				if (s.charAt(i) == s.charAt(j)) {
					
					//System.out.println(s.substring(j, i + 1));
					//System.out.println("i="+i+" j="+j+ " matrix["+(i - 1)+"]["+(j + 1)+"]"+matrix[i - 1][j + 1]);
					
					if (matrix[j + 1][i - 1]) {
						matrix[j][i] = true;
						
						//System.out.println(s.substring(j, i + 1));
						
						if (((i + 1) - j) > longest.length()) {
							longest = s.substring(j, i + 1);
						}
					} else {
						//System.out.println("else");
						matrix[j][i] = false;
					}
				} else {
					matrix[j][i] = false;
				}
			}
		}
		
		return longest;
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