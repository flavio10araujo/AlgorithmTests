package General;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/maximum-population-year/
 * 
 * You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death years of the ith person.
 * The population of some year x is the number of people alive during that year. 
 * The ith person is counted in year x's population if x is in the inclusive range [birthi, deathi - 1]. 
 * Note that the person is not counted in the year that they die.
 * Return the earliest year with the maximum population.
 * 
 * Example 1:
 * Input: logs = [[1993,1999],[2000,2010]]
 * Output: 1993
 * Explanation: The maximum population is 1, and 1993 is the earliest year with this population.
 * 
 * Example 2:
 * Input: logs = [[1950,1961],[1960,1971],[1970,1981]]
 * Output: 1960
 * Explanation:
 * The maximum population is 2, and it had happened in years 1960 and 1970
 * The earlier year between them is 1960.
 * 
 * Constraints:
 * 1 <= logs.length <= 100
 * 1950 <= birthi < deathi <= 2050
 */
public class MaximumPopulationYear {

	public static void main(String[] args) {
		int[][] logs = {{1987,2047},{1952,2006},{2021,2042},{2047,2049},{2036,2040},{1994,2009}};
		
		long startTime = System.nanoTime();
		System.out.println(solution01(logs));
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
		
		startTime = System.nanoTime();
		System.out.println(solution02(logs));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	}

	/**
	 * Approach: Using heap.
	 * Time complexity: O(n log n).
	 * Space complexity: O(n).
	 * @param logs
	 * @return
	 */
	public static int solution01(int[][] logs) {
        Arrays.sort(logs, (a, b) -> Integer.compare(a[0], b[0]));
		
        Queue<Integer> heap = new PriorityQueue<>();
        
        for (int i = 0; i < logs.length; i++) {
            heap.add(logs[i][1]);
        }
        
        int population = 0;
        int maxPopulation = 0;
        int yearMaxPopulation = 0;
        
        for (int i = 0; i < logs.length; i++) {
            if (logs[i][0] < heap.peek()) {
                population++;
                
                if (population > maxPopulation) {
                    maxPopulation = population;
                    yearMaxPopulation = logs[i][0];
                }
            } else {
                heap.poll();
            }
        }
        
        return yearMaxPopulation;
        
    }
	
	public static int solution02(int[][] logs) {
		int[] arr = new int[101];
		
		for (int i = 0; i < logs.length; i++) {
			arr[logs[i][0] - 1950]++;
			arr[logs[i][1] - 1950]--;
		}
		
		for (int i = 1; i < 101; i++) {
			arr[i] += arr[i -1];
		}
		
		int maxVal = 0;
		int maxYear = 1950;
		
		for (int i = 0; i < 101; i++) {
			if (maxVal < arr[i]) {
				maxVal = arr[i];
				maxYear = i + 1950;
			}
		}
		
		return maxYear;
	}
}