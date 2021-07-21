package General.DynamicProgramming.Interval;

/**
 * Number of Ways to Partition a String into a Palindromes
 * 
 * The problem is exactly the same as in Palindrome Partitioning except instead of outputting every possible palindrome, 
 * we instead ask you to simply output the number of possible partitions. 
 * The string size can be up to 2000 characters in length. 
 * The number will be guaranteed to fit into an int.
 * 
 * Example:
 * Input: aab
 * Output: 2
 * Explanation: ["a","a","b"], ["aa","b"]
 * 
 * Solution:
 * 
 * For this question we use dynamic programming to solve it. 
 * We already know from Palindrome Counting that we can preprocess the palindromes in a particular string in O(n^2) time. 
 * We realize that we can also memoize states for this particular problem as well. 
 * If we let f(i) equal the number of possible partitions using only the first i letters in the string 
 * then we realize that there are O(n) possible states and that we can have O(n) transition by looping backwards 4
 * and checking where there exists palindromes. 
 * Let us assume that a particular interval [i,j] is a palindrome then the number of palindromes 
 * that can be formed using the first j indicies is equal to the current number of palindromes 
 * that can be formed by the first j indicies plus the number of indicies formed by i.
 * Time Complexity: O(s.length()^2)
 */
public class PalindromePartitioningDP {

	public static int partition2(String s) {
		
        // This code is exactly the same as in Partition Count.
        int sLength = s.length();
        boolean [][] isPalindrome = new boolean[sLength][sLength];
        int [] partitionCount = new int[sLength];

        // Initialize the dp array so that every index in the array with i > j is set to 1.
        for (int i = 0; i < sLength; i++) {
            for (int j = 0; j <= i; j++) {
                isPalindrome[i][j] = true;
            }
        }

        // Loop through the array first iterating through the possible string lengths then the possible starting indices.
        for (int len = 1; len < sLength; len++) {
            for (int i = 0; i < sLength - len; i++) {
                int j = i + len;
                // Check for the condition as mentioned in the solution that the interval [i + 1, j - 1] is a palindrome and for matching characters.
                isPalindrome[i][j] = isPalindrome[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
            }
        }

        partitionCount[0] = 1;
        // This code is new for this question, loop backwards checking if the particular interval is a palindrome. 
        // If it is then we want to add the number of possible partitions up to the lower bound of the interval.
        for (int i = 1; i < sLength; i++) {
            for (int j = i; j >= 1; j--) {
                if (isPalindrome[j][i]) {
                    partitionCount[i] += partitionCount[j - 1];
                }
            }
            
            // Make sure to check edge case that whole interval is a palindrome itself.
            if (isPalindrome[0][i]) {
                partitionCount[i]++;
            }
        }
        
        return partitionCount[sLength-1];
    }
	
	public static void main(String[] args) {
        String s = "aab";
        int res = partition2(s);
        System.out.println(res);
    }
}