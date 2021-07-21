package General.DynamicProgramming.Interval;

/**
 * Count the number of palindromes that exist in a particular string. 
 * Duplicates are allowed so long as they cover distinct intervals of the string. 
 * Empty strings do not count towards this total.
 * 
 * Example 1:
 * Input: abab
 * Output: 6
 * Explanation: The following intervals are palindromes(0-indexed), [0,0],[1,1],[2,2],[3,3],[0,2],[1,3]
 * 
 * Explanation
 * 
 * Let n denote length of our string. 
 * Our main observation is that we can actually compute palindromes in O(n^2) using Dynamic Programming as opposed to O(n^3). 
 * We have O(n^2) states which we represent with a 2-D array which we will call dp. 
 * dp[i][j] represents whether or not the substring from i to j is a palindrome or not. 
 * We realize that this interval is only a palindrome if the substring from i+1 to j-1 is a palindrome itself and the characters 
 * at index i and j are matching. 
 * Therefore, we use bottom up dp to compute all potential palindromes for substrings size 1, then size 2 and so on and so forth.
 */
public class PalindromeCounting {

	public static int palindromeCounting(String s) {
		
        int strLength = s.length();
        int [][] isPalindrome = new int[strLength][strLength];

        // Initialize the isPalindrome array so that every index in the array with i >= j is set to 1.
        for (int i = 0; i < strLength; i++) {
            for (int j = 0; j <= i; j++) {
                isPalindrome[i][j] = 1;
                //System.out.println("i="+i+" j="+j);
            }
        }

        // Loop through the array first iterating through the possible string lengths then the possible starting indices.
        for (int len = 1; len < strLength; len++) {
            for (int i = 0; i < strLength - len; i++) {
                int j = i + len;
                // Check for the condition as mentioned in the solution that the interval [i + 1, j - 1] is a palindrome and for
                // matching characters.
                if (isPalindrome[i + 1][j - 1] == 1 && s.charAt(i) == s.charAt(j)) {
                    isPalindrome[i][j] = 1;
                }
            }
        }

        // Finally add up all potential palindromes into an answer variable and return it.
        int ans = 0;
        for (int i = 0; i < strLength; i++) {
            for (int j = i; j < strLength; j++) {
                ans += isPalindrome[i][j];
            }
        }
        
        return ans;
    }
	
	public static void main(String[] args) {
        String s = "abab";
        int res = palindromeCounting(s);
        System.out.println(res);
    }
}