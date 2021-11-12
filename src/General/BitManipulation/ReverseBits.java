package General.BitManipulation;

/**
 * https://leetcode.com/problems/reverse-bits/
 * 
 * Reverse bits of a given 32 bits unsigned integer.
 * 
 * Input: n = 00000010100101000001111010011100
 * Output: 964176192 (00111001011110000010100101000000)
 * Explanation: The input binary string 
 * 		00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 
 * 		00111001011110000010100101000000.
 */
public class ReverseBits {

	public static void main(String[] args) {
		int n = 43261596;

		System.out.println(reverseBits(n));
	}

	// you need treat n as an unsigned value
	public static int reverseBits(int n) {
		int ans = 0;

		for (int i = 0; i < 32; i++) {
			if ( ( (n >> i) & 1) == 1) {
				ans = ans | (1 << (31 - i));
			}
		}

		return ans;
	}
}