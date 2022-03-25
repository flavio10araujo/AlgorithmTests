package Tests;

import java.util.ArrayList;
import java.util.List;

/**
 * Amazon Web services has n servers.
 * The cache optimization power of the ith server is power[i].
 * The cache optimization power of a group of contiguous servers [i,r] is defined as:
 * Power[l,r] = Math.min(power[l], power[r]) * Sum(power[l], power[r]).
 * 
 * Find the sum of Power[l,r] for each possible contiguous group of servers.
 * Since, the answer can be very large, return the answer modulo 1000000007 (10 ^ 9 + 7).
 * 
 * Example 01:
 * input: power={2, 3, 2, 1}
 * output: 69
 * explanation: 
 * 		power[0,0] = min(2) * sum(2) = 2 * 2 = 4
 * 		power[0,1] = min(2,3) * sum(2,3) = 2 * 5 = 10
 * 		power[0,2] = min(2,3,2) * sum(2,3,2) = 2 * 7 = 14
 * 		power[0,3] = min(2,3,2,1) * sum(2,3,2,1) = 1 * 8 = 8
 * 		power[1,1] = min(3) * sum(3) = 9
 * 		power[1,2] = min(3,2) * sum(3,2) = 2 * 5 = 10
 * 		power[1,3] = min(3,2,1) * sum(3,2,1) = 1 * 6 = 6
 * 		power[2,2] = min(2) * sum(2) = 2 * 2 = 4
 * 		power[2,3] = min(2,1) * sum(2,1) = 1 * 3 = 3
 * 		power[3,3] = min(1) * sum(1) = 1 * 1 = 1
 * 		overall = (4 + 10 + 14 + 8 + 9 + 10 + 6 + 4 + 3 + 1)  = 69
 */
public class AmazonCacheOptimizationPower {

	static int totalPower;
	static int MODULO = 1000000007;
	
	public static void main(String[] args) {
		//System.out.println(solution(List.of(2,3,2,1))); // 69
		//System.out.println(solution(List.of(2,1,3))); // 27
		//System.out.println(solution(List.of(2,4))); // 32
		System.out.println(solution(List.of(Integer.MAX_VALUE, Integer.MAX_VALUE, 3, 4))); // 762213165
	}
	
	private static int solution(List<Integer> power) {
		
		List<List<Long>> ans = new ArrayList<>();
		
		List<Long> powerLong = new ArrayList<>();
		
		for (Integer i : power) {
			powerLong.add((long) i);
		}
		
		for (int i = 0; i < power.size(); i++) {
			helper(powerLong, i, powerLong.get(i), 0, ans, new ArrayList<>());
		}
		
		System.out.println(ans);
		
		return totalPower;
	}
	
	private static boolean helper(List<Long> power, int start, long min, long sum, List<List<Long>> ans, List<Long> path) {
		if (start == power.size()) {
			return false;
		}
		
		boolean go = true;
		
		for (int i = start; i < power.size(); i++) {
			min = Math.min(min, power.get(i));
			sum = (sum + power.get(i)) % MODULO;
			totalPower += (min * sum) % MODULO;
			
			path.add(power.get(i));
			ans.add(new ArrayList<>(path));
			
			go = helper(power, i + 1, min, sum, ans, path);
			
			path.remove(path.size() - 1);
			
			if (!go) {
				break;
			}
		}
		
		return go;
	}
}