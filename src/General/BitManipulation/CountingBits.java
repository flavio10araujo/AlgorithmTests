package General.BitManipulation;

/**
 * https://leetcode.com/problems/counting-bits/
 *
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), 
 * ans[i] is the number of 1's in the binary representation of i.
 * 
 * Input: n = 5
 * Output: [0,1,1,2,1,2]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 */
public class CountingBits {

	public static void main(String[] args) {
		int n = 5;
		
		for (int i : countingBits(n)) {
			System.out.println(i);
		}
	}
	
	public static int[] countingBits(int n) {
		int[] ans = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            ans[i] = hammingWeight(i);
        }
        
        return ans;
	}
	
	public static int hammingWeight(int n) {
        int count = 0, mask = 1;
        
        for (int i = 0; i < 32; i++) {
        	if ((n & mask) != 0) {
        		count++;
        	}
        	
        	mask = mask << 1;
        }
        
        return count;
    }
}