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
	public static int solution01(int n, int a, int b, int c) {
		BigInteger nBig = BigInteger.valueOf(n);
        
		BigInteger aBig = BigInteger.valueOf(a);
        BigInteger bBig = BigInteger.valueOf(b);
        BigInteger cBig = BigInteger.valueOf(c);
        
        BigInteger ab = aBig.multiply(bBig).divide(aBig.gcd(bBig));
        BigInteger bc = bBig.multiply(cBig).divide(bBig.gcd(cBig));
        BigInteger ac = aBig.multiply(cBig).divide(aBig.gcd(cBig));
        
        BigInteger abc = ab.multiply(cBig).divide(ab.gcd(cBig));
        
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
	
	/**
	 * 
	 * @param nn
	 * @param aa
	 * @param bb
	 * @param cc
	 * @return
	 */
	public static int solution02(int nn, int aa, int bb, int cc) {
        long a = aa;
        long b = bb;
        long c = cc;
        long n = nn;
        long L = 1;
        long R = Integer.MAX_VALUE;
 
        while (L < R) {
            long M = (L + R) / 2;
            
            if (calcul(M, a, b, c) < n) {
                L = M + 1;
            } else {
                R = M;
            }
        }
        
        return (int) L;
    }
	
	static long gcd(long a, long b) {
        if (a > b) {
            return gcd(b, a);
        }
        
        if (a == 0) {
            return b;
        } else {
            return gcd(a, b % a);
        }
    }
 
    static long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
    
    public static int calcul(long M, long a, long b, long c) {
    	//return (int) ((M / a) + (M / b) + (M / c) - (M / lcm(a, b)) - (M / lcm(a, c)) - (M / lcm(b, c)) + (M / lcm(a, lcm(b, c))));
    	
    	int qtd = (int) ((M / a) + (M / b) + (M / c));
    	
    	int lcmAB = (int) lcm(a, b);
    	int qtdAB = (int) (M / lcmAB);
    	int lcmAC = (int) lcm(a, c);
    	int qtdAC = (int) (M / lcmAC);
    	int lcmBC = (int) lcm(b, c);
    	int qtdBC = (int) (M / lcmBC);
    	
    	int lcmLast = (int) lcm(a, lcmBC);
    	int qtdLast = (int) (M / lcmLast);
    	
    	qtd = qtd - qtdAB - qtdAC - qtdBC + qtdLast;
    	
    	return qtd;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt("10");
        int a = Integer.parseInt("2");
        int b = Integer.parseInt("3");
        int c = Integer.parseInt("5");
        
        System.out.println(solution01(n, a, b, c));
        
        System.out.println(solution02(n, a, b, c));
    }
}