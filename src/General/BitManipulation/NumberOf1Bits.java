package General.BitManipulation;

/**
 * https://leetcode.com/problems/number-of-1-bits/
 * 
 * Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).
 * 
 * Note:
 * In Java, there is no unsigned integer type. 
 * In this case, the input will be given as a signed integer type. 
 * It should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation. 
 * Therefore, in Example 3, the input represents the signed integer. -3.
 * 
 * Example 1:
 * Input: n = 00000000000000000000000000001011
 * Output: 3
 * Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.
 * 
 * Example 2:
 * Input: n = 00000000000000000000000010000000
 * Output: 1
 * Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.
 * 
 * Example 3:
 * Input: n = 11111111111111111111111111111101
 * Output: 31
 * Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.
 * 
 * Constraints: The input must be a binary string of length 32.
 */
public class NumberOf1Bits {

	public static void main(String[] args) {
		int n = 00000000000000000000000000001011; // 3
		//int n = 00000000000000000000000010000000; // 1
		//int n = 11111111111111111111111111111101; // 31
		
		System.out.println(hammingWeightSolution01(n));
		System.out.println(hammingWeightSolution02(n));
		System.out.println(hammingWeightSolution03(n));
	}
	
	/**
	 * Time complexity: O(32) = O(1).
	 * Space complexity: O(1).
	 * @param n
	 * @return
	 */
	public static int hammingWeightSolution01(int n) {
        int count = 0, mask = 1;
        
        for (int i = 0; i < 32; i++) {
        	if ((n & mask) != 0) {
        		count++;
        	}
        	
        	mask = mask << 1;
        }
        
        return count;
    }
	
	/**
	 * Time complexity: O(setBits) = O(1).
	 * Space complexity: O(1).
	 * @param n
	 * @return
	 */
	public static int hammingWeightSolution02(int n) {
        int count = 0;
        
        while(n != 0) {
        	n = n & (n - 1);
        	count++;
        }
        
        return count;
    }
	
	/**
	 * Time complexity: O(setBits) = O(1).
	 * Space complexity: O(1).
	 * @param n
	 * @return
	 */
	public static int hammingWeightSolution03(int n) {
        if (n == 0) {
        	return 0;
        }
        
        if (n == 1) {
        	return 1;
        }
        
        return hammingWeightSolution03(n & (n - 1)) + 1;
    }
}