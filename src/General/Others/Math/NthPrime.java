package General.Others.Math;

import java.util.Arrays;
import java.util.Scanner;

/**
 * For this question we ask you to compute the n-th prime number.
 * 
 * Input
 * n: Integer representing the n-th number that should be printed
 * Output
 * n-th prime number
 * 
 * Example 1:
 * Input: n = 3
 * Output: 5
 * Explanation: We want the third prime number which is 5 given that 2, 3 and 5 are the first 3 prime numbers.
 * 
 * Example 2:
 * Input: n = 5
 * Output: 11
 * Explanation: 11 is the 5th prime number.
 * 
 * Constraints: The resulting prime number if guarenteed to be less than 100000.
 * 
 * Solution:
 * 
 * Pretty straightforward, we simply employ the sieve to calculate prime numbers then find the n-th one in our boolean array.
 */
public class NthPrime {

	public static int nthPrime(int n) {
        // We know the number will not exceed 100001 from the constraints.
        boolean [] primes = new boolean [100001];
        
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        int ans = 0, cnt = 0;
        
        for (int i = 2; i <= 100000; i++) {
            if (primes[i]) {
                cnt++;
                
                if (cnt == n) {
                    ans = i;
                    break;
                }
                
                for (int j = i + i; j <= 100000; j += i) {
                    primes[j] = false;
                }
            }
        }
        
        return ans;
    }

	// input: 3. output: 5.
	// input: 5. output: 11. 
	// input: 100. output: 541. 
	// input: 153. output: 761.
	// input: 2021. output: 17579. 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        scanner.close();
        int res = nthPrime(n);
        System.out.println(res);
    }
}