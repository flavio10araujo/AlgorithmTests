package General.BinarySearch.Applications;

import java.math.BigInteger;

/**
 * Leetcode 1201.
 * 
 * Write a program to find the n-th ugly number.
 * Ugly numbers are positive numbers that are divisible by either a, b, or c.
 * Since the number can be too large, return the actual answer modulo 10^9 + 7.
 * 
 * Input:n = 10, a = 2, b = 3, c = 5
 * Output: 14
 * Explanation: 2, 3, 4, 5, 6, 8, 9, 10, 12, 14 is the sequence of the first 10 ugly numbers.
 * Constraints
 * 1 <= n, a, b, c <= 10^9
 * 1 <= a * b * c <= 10^18
 * 
 * Solution:
 * 
 * For this question, we can go through linearly and determine the value of the next ugly number.
 * The time complexity is O(n).
 * That is not enough, though, as n can reach 10^9. Can we do better?
 * 
 * Consider a given integer k, we can calculate the number of ugly numbers less than or equal to it by checking how many numbers are divisible by either a, b, or c.
 * We can find the number of numbers divisible by x by k / x, e.g. there are two numbers less than or equal to 10 that are divisible by 5 - 5 and 10. 
 * 
 * 10 / 2 = 5 {2, 4, 6, 8, 10)
 * 9 / 3 = 3 {3, 6, 9}
 * 10 / 5 = 2 {5, 10)
 * 
 * Total = ( (k / a) + (k / b) + (k / c) ) - ( (k / ab) - (k / bc) - (k / ac) ) + k / abc
 */
public class UglyNumberIII {
	
	/**
	 * Time complexity: O(log(N)).
	 * @param n
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public static int nthUglyNumber(int n, int a, int b, int c) {
		BigInteger nBig = BigInteger.valueOf(n);
        BigInteger aBig = BigInteger.valueOf(a);
        BigInteger bBig = BigInteger.valueOf(b);
        BigInteger cBig = BigInteger.valueOf(c);
        
        System.out.println("aBig="+aBig+" bBig="+bBig+" aBig.gcd(bBig)="+aBig.gcd(bBig));
        BigInteger ab = aBig.multiply(bBig).divide(aBig.gcd(bBig));
        System.out.println("ab="+ab);
        
        System.out.println("bBig="+bBig+" cBig="+cBig+" bBig.gcd(cBig)="+bBig.gcd(cBig));
        BigInteger bc = bBig.multiply(cBig).divide(bBig.gcd(cBig));
        System.out.println("bc="+bc);
        
        System.out.println("aBig="+aBig+" cBig="+cBig+" aBig.gcd(cBig)="+aBig.gcd(cBig));
        BigInteger ac = aBig.multiply(cBig).divide(aBig.gcd(cBig));
        System.out.println("ac="+ac);
        
        System.out.println("ab="+ab+" cBig="+cBig+" ab.gcd(cBig)="+ab.gcd(cBig));
        BigInteger abc = ab.multiply(cBig).divide(ab.gcd(cBig));
        System.out.println("abc="+abc);
        
        BigInteger minPtr = nBig;
        BigInteger maxPtr = nBig.multiply(aBig.min(bBig).min(cBig));
        BigInteger boundaryIndex = maxPtr;
        
        while (minPtr.compareTo(maxPtr) <= 0) {
            BigInteger midpoint = minPtr.add(maxPtr).divide(BigInteger.valueOf(2));
            BigInteger uglyNumberCount = midpoint.divide(aBig).add(midpoint.divide(bBig))
                .add(midpoint.divide(cBig)).subtract(midpoint.divide(ab))
                .subtract(midpoint.divide(bc)).subtract(midpoint.divide(ac))
                .add(midpoint.divide(abc));
            
            if (uglyNumberCount.compareTo(nBig) < 0) {
                minPtr = midpoint.add(BigInteger.ONE);
            } else {
                boundaryIndex = midpoint;
                maxPtr = midpoint.subtract(BigInteger.ONE);
            }
        }
        
        return boundaryIndex.remainder(BigInteger.valueOf(1000000007)).intValue();
    }

    public static void main(String[] args) {
        int n = Integer.parseInt("10");
        int a = Integer.parseInt("2");
        int b = Integer.parseInt("3");
        int c = Integer.parseInt("5");
        int res = nthUglyNumber(n, a, b, c);
        System.out.println(res);
    }
}