package General.Backtracking.Memoization;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * We have a message to decode. 
 * Letters are encoded to digits by its position in the alphabet
 * A -> 1; B -> 2; C -> 3; ... Y -> 25; Z -> 26
 * Given a non-empty string of digits, how many ways are there to decode it?
 * 
 * Input: "18"
 * Output: 2
 * Explanation: "18" can be decoded as "AH" or "R"
 * 
 * Input: "123"
 * Output: 3
 * Explanation: "123" can be decoded as "ABC", "LC", "AW"
 */
public class DecodeWays {
	
	private static final List<String> LETTERS = IntStream.range(1, 27).mapToObj(Integer::toString).collect(Collectors.toList());

    private static int dfs(int i, int[] memo, String digits) {
        if (i == digits.length()) {
        	return 1;
        }

        if (memo[i] == -1) {
            int ways = 0;
            String remaining = digits.substring(i);
            
            System.out.println("remaining = " + remaining);
            
            for (String prefix : LETTERS) {
                
            	System.out.println("prefix = " + prefix);
            	
            	if (remaining.startsWith(prefix)) {
                    ways += dfs(i + prefix.length(), memo, digits);
                    
                    System.out.println("ways = " + ways);
                }
            }
            
            System.out.println("memo["+i+"] = " + ways);
            
            memo[i] = ways;
        }

        return memo[i];
    }

    public static int decodeWays(String digits) {
        // Use numbers 1 to 26 to represent all alphabet letters.
        int[] memo = new int[digits.length()];
        Arrays.fill(memo, -1);
        return dfs(0, memo, digits);
    }
    
    public static void main(String[] args) {
    	long startTime = System.nanoTime();
    	String digits = "18";
        int res = decodeWays(digits);
        System.out.println(res);
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }
}