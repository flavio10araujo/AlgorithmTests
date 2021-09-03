package General;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/count-primes/
 * 
 * Count the number of prime numbers less than a non-negative number, n.
 * 
 * Example 1:
 * Input: n = 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * 
 * Example 2:
 * Input: n = 0
 * Output: 0
 * 
 * Example 3:
 * Input: n = 1
 * Output: 0
 */
public class CountPrimeNumbers {

	public static void main(String args[]) {
        int n = 10000;
        
        // Marcar o tempo de execução de um algoritmo:
        long startTime = System.nanoTime();
        System.out.println(countPrimes(n)); ////
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
        
        // Marcar o tempo de execução de um algoritmo:
        startTime = System.nanoTime();
        System.out.println(solution02(n));
        endTime = System.nanoTime();
        timeElapsed = endTime - startTime;
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }

	public static int countPrimes(int n) {
		return sieveOfEratosthenes(n - 1);
    }
	
	// https://www.geeksforgeeks.org/sieve-of-eratosthenes/
	public static int sieveOfEratosthenes(int n) {
        
		int countPrimeNumbers = 0;
		
		// Create a boolean array "prime[0..n]" and initialize all entries it as true. 
		// A value in prime[i] will finally be false if i is Not a prime, else true.
        boolean prime[] = new boolean[n + 1];
        
        Arrays.fill(prime, true);
        
        for (int p = 2; p * p <= n; p++) {
            // If prime[p] is not changed, then it is a prime.
            if (prime[p] == true) {
                // Update all multiples of p.
                for (int i = p * p; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
 
        // Count all prime numbers.
        // Print all prime numbers.
        for (int i = 2; i <= n; i++) {
            if (prime[i] == true) {
                //System.out.print(i + " ");
                countPrimeNumbers++;
            }
        }
        
        return countPrimeNumbers;
    }
	
	/**
	 * Naive solution.
	 * 
	 * @param n
	 * @return
	 */
	public static int solution02(int n) {
		
		n--;
		
		if (n < 2) {
			return 0;
		}
		
		int countNotPrimes = 1;
		
		for (int a = 2; a <= n; a++) {
			
			for (int i = a / 2; i > 1; i--) {
				if (a % i == 0) {
					//System.out.println("a="+a);
					countNotPrimes++;
					i = 0;
				}
			}
		}
		
		//System.out.println("countNotPrimes="+countNotPrimes);
		
		return n - countNotPrimes;
		
    }
}