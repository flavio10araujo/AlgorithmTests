package Tests;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A cafeteria table consists of a row of NN seats, numbered from 11 to NN from left to right. 
 * Social distancing guidelines require that every diner be seated such that KK seats to their left and KK seats to their right 
 * (or all the remaining seats to that side if there are fewer than KK) remain empty.
 * There are currently MM diners seated at the table, the iith of whom is in seat S_iSi.
 * No two diners are sitting in the same seat, and the social distancing guidelines are satisfied.
 * Determine the maximum number of additional diners who can potentially sit at the table without social 
 * distancing guidelines being violated for any new or existing diners, assuming that the existing diners 
 * cannot move and that the additional diners will cooperate to maximize how many of them can sit down.
 * Please take care to write a solution which runs within the time limit.
 * 
 * Constraints
 * 1 <= N <= 10 ^ 15
 * 1 <= K <= N
 * 1 <= M <= 500000
 * 1 <= S_i <= N
 * 
 * Example 01:
 * N = 10; K = 1; M = 2; S = [2, 6]
 * Output: 3.
 * 
 * Example 02:
 * N = 15; K = 2; M = 3; S = [11, 6, 14];
 * Output: 1.
 */
public class FacebookCafeterie {

	public static void main(String[] args) {
		//long N = 10, K = 1; int M = 2; long[] S = {2L, 6L}; // output: 3

		long N = 15, K = 2; int M = 3; long[] S = {11L, 6L, 14L}; // output: 1

		long startTime = System.nanoTime();
		System.out.println(solution01(N, K, M, S));
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);

		startTime = System.nanoTime();
		System.out.println(solution02(N, K, M, S));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	}

	/**
	 * Time complexity: O(n).
	 * Space complexity: O(n).
	 * 
	 * @param N
	 * @param K
	 * @param M
	 * @param S
	 * @return
	 */
	public static long solution01(long N, long K, int M, long[] S) {
		Set<Long> sSet = new HashSet<>();
		long count = 0L;

		for (int i = 0; i < M; i++) {
			sSet.add(S[i]);
		}

		long i = 1L;
		while (i <= N) {
			if (sSet.contains(i)) {
				i = i + K + 1L;
			} else {
				boolean exist = false;

				for (long j = i + 1L; j <= (i + K) && j <= N; j++) {
					if (sSet.contains(j)) {
						exist = true;
						i = j + K + 1L;
						j = N + 1L;
					}
				}

				if (!exist) {
					count++;
					i = i + K + 1L;
				}
			}
		}

		return count;
	}

	/**
	 * Time complexity: O(n).
	 * Space complexity: O(1).
	 * @param N
	 * @param K
	 * @param M
	 * @param S
	 * @return
	 */
	public static long solution02(long N, long K, int M, long[] S) {
		Arrays.sort(S);

		long guests = 0;
		long start = 1;
		double range = 0;

		for (long seatedDiner : S) {
			//System.out.println("seatedDiner="+seatedDiner);
			
			range = seatedDiner - start;
			//System.out.println("RANGE="+range);
			
			guests += Math.floor(range / (K + 1));
			//System.out.println("GUESTS="+guests);
			
			start = seatedDiner + K + 1;
			//System.out.println("START="+start);
			
			//System.out.println("---");
		}

		range = N - start + 1;
		//System.out.println("RANGE="+range);
		guests += Math.ceil(range / (K + 1));
		//System.out.println("GUESTS="+guests);

		return guests;
	}
}