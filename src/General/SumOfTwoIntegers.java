package General;

/**
 * https://leetcode.com/problems/sum-of-two-integers/
 * 
 * Given two integers a and b, return the sum of the two integers without using the operators + and -.
 * 
 * Example 1:
 * Input: a = 1, b = 2
 * Output: 3
 * 
 * Example 2:
 * Input: a = 2, b = 3
 * Output: 5
 * 
 * Constraints:-1000 <= a, b <= 1000
 */
public class SumOfTwoIntegers {

	public static void main(String[] args) {
		//int a = 1, b = 2;
		int a = 2, b = 3;

		System.out.println(getSum(a, b));
	}

	public static int getSum(int a, int b) {
		while (b != 0) { // still have carry bits
			final int carry = a & b; // record carry bits
			
			System.out.println("carry="+ carry);
			
			a = a ^ b; // ^ works like + w/o handling carry bits
			b = carry << 1;
			
			System.out.println("a="+a+" b="+b+ " carry="+ carry);
		}
		
		return a;
	}
}