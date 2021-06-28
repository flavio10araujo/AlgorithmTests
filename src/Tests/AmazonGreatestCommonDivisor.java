package Tests;

/**
 * The greatest common divisor (GCD), also called highest common factor (HCF) of N numbers 
 * is the largest positive integer that divides all numbers without giving a remainder.
 * 
 * Write an algorithm to determine the GCD of N positive integers.
 * 
 * Input: the input to the function/method consists of two arguments:
 *   num, an integer representing the number of positive integers (N).
 *   arr, a list of positive integers.
 *   
 * Output: return an integer representing the GCD of the given positive integers.
 * 
 * Example:
 *   Input: 
 *     num = 5
 *     arr = [2, 4, 6, 8, 10]
 *   Output:
 *     2
 *   Explanation:
 *     The largest positive integer that divides all the positive integers 2, 4, 6, 8, 10 without a remainder is 2.
 */
public class AmazonGreatestCommonDivisor {

	public static void main(String[] args) {
		int num = 5;
		int[] arr = {4, 28, 8, 16};
		
		System.out.println(solution01(num, arr));
		System.out.println(solution02(num, arr));
		System.out.println(solution03(num, arr));
		System.out.println(solution04(num, arr));
	}
	
	/**
	 * We know that: gcd(a, b, c) = gcd(gcd(a, b), c)
	 * 
	 * So for our first approach, we get the list in pairs and 
	 * we iterate from 1 to the smallest number given and check whether the given integers are divisible by the index.
	 * The largest index which divides the given numbers is the GCD of the given numbers.
	 * 
	 * After find the GCD for the two numbers, we find the GCD of the result and the next number of the list.
	 * 
	 * Time complexity: O(min(n1, n2)).
	 * 
	 * @param num
	 * @param arr
	 * @return
	 */
	public static int solution01(int num, int[] arr) {
		System.out.println("### Solution 01 - gcdByBruteForce ###");
		
		int gcd = gcdByBruteForce(arr[0], arr[1]);
		
		for (int i = 2; i < arr.length; i++) {
			gcd = gcdByBruteForce(gcd, arr[i]);
		}
	    
	    return gcd;
	}
	
	public static int gcdByBruteForce(int n1, int n2) {
		int gcd = 1;
		
		for (int i = 1; (i <= n1 && i <= n2); i++) {
	        if (n1 % i == 0 && n2 % i == 0) {
	            gcd = i;
	        }
	    }
	    
	    return gcd;
	}
	
	/**
	 * Second approach: We can use Euclid's algorithm to find the GCD.
	 * The Euclidean algorithm is based on the following key observation: if d divides a and d divides b, then d also divides a - b. 
	 * 
	 * Time complexity: O(log min(n1, n2)).
	 * 
	 * @param num
	 * @param arr
	 * @return
	 */
	public static int solution02(int num, int[] arr) {
		System.out.println("### Solution 02 - gcdByEuclidsAlgorithm ###");
		
		int gcd = gcdByEuclidsAlgorithm(arr[0], arr[1]);
		
		for (int i = 2; i < arr.length; i++) {
			gcd = gcdByEuclidsAlgorithm(gcd, arr[i]);
		}
	    
	    return gcd;
	}
	
	/**
	 * Step 1: If n1 = n2, stop -- the GCD of n1 and n1 is, of course, n1. Otherwise, go to step 2.
	 * Step 2: If n1 > n2, replace n1 with n1 - n2, and go back to step 1.
	 * Step 3: If n2 > n1, replace n2 with n2 - n1, and go back to step 1.
	 * 
	 * @param n1
	 * @param n2
	 * @return
	 */
	public static int gcdByEuclidsAlgorithm(int n1, int n2) {
		if (n1 == n2) {
			return n1;
		} else if (n1 > n2) {
			return gcdByEuclidsAlgorithm((n1 - n2), n2);
		} else {
			// n2 > n1
			return gcdByEuclidsAlgorithm(n1, (n2 - n1));
		}
	}
	
	/**
	 * Third approach: small modification of Euclid's algorithm.
	 * In this implementation we'll use modulo instead of subtraction since it's basically many subtractions at a time.
	 * 
	 * Time complexity: O(log min(n1, n2)).
	 * 
	 * @param num
	 * @param arr
	 * @return
	 */
	public static int solution03(int num, int[] arr) {
		System.out.println("### Solution 03 - gcdByEuclidsAlgorithmWithModulo ###");
		
		int gcd = gcdByEuclidsAlgorithmWithModulo(arr[0], arr[1]);
		
		for (int i = 2; i < arr.length; i++) {
			gcd = gcdByEuclidsAlgorithmWithModulo(gcd, arr[i]);
		}
	    
	    return gcd;
	}
	
	public static int gcdByEuclidsAlgorithmWithModulo(int n1, int n2) {
	    if (n2 == 0) {
	        return n1;
	    }
	    return gcdByEuclidsAlgorithmWithModulo(n2, n1 % n2);
	}
	
	/**
	 * Fourth approach: We can use Stein's algorithm to find the GCD.
	 * 
	 * Time complexity: When n1 > n2 is O((log2 n1) ^ 2); When n1 < n2, it is O((log2 n2) ^ 2).
	 * 
	 * @param num
	 * @param arr
	 * @return
	 */
	public static int solution04(int num, int[] arr) {
		System.out.println("### Solution 04 - gcdBySteinsAlgorithm ###");
		
		int gcd = gcdBySteinsAlgorithm(arr[0], arr[1]);
		
		for (int i = 2; i < arr.length; i++) {
			gcd = gcdBySteinsAlgorithm(gcd, arr[i]);
		}
	    
	    return gcd;
	}
	
	/**
	 * Step 1: If a = b, stop -- the GCD of a and a is, of course, a. Otherwise, go to step 2.
	 * Step 2: If a and b are both even, replace a with a/2, b with b/2, and increment a counter.
	 * Step 3: If a is even and b is odd, replace a with a/2.
	 * Step 4: If a is odd and b is even, replace b with b/2.
	 * Step 5: If a and b are both odd, replace a with a - b.
	 * 
	 * @param n1
	 * @param n2
	 * @return
	 */
	public static int gcdBySteinsAlgorithm(int n1, int n2) {
	    if (n1 == 0) {
	        return n2;
	    }

	    if (n2 == 0) {
	        return n1;
	    }

	    int n;
	    for (n = 0; ((n1 | n2) & 1) == 0; n++) {
	        n1 >>= 1;
	        n2 >>= 1;
	    }

	    while ((n1 & 1) == 0) {
	        n1 >>= 1;
	    }

	    do {
	        while ((n2 & 1) == 0) {
	            n2 >>= 1;
	        }

	        if (n1 > n2) {
	            int temp = n1;
	            n1 = n2;
	            n2 = temp;
	        }
	        
	        n2 = (n2 - n1);
	    } while (n2 != 0);
	    
	    return n1 << n;
	}
}