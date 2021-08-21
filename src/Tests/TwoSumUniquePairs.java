package Tests;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Write a function that takes a list of numbers and a target number, and then returns the number of unique pairs that add up to the target number.
 * [X, Y] and [Y, X] are considered the same pair, and not unique.
 * 
 * Example 1:
 * Input: [1, 1, 2, 45, 46, 46], target = 47
 * Output: 2
 * Explanation:
 * 1 + 46 = 47
 * 2 + 45 = 47
 * 
 * Example 2:
 * Input: [1, 1], target = 2
 * Output: 1
 * Explanation:
 * 1 + 1 = 2
 * 
 * Example 3:
 * Input: [1, 5, 1, 5], target = 6
 * Output: 1
 * Explanation:
 * [1, 5] and [5, 1] are considered the same, therefore there is only one unique pair that adds up to 6.
 */
public class TwoSumUniquePairs {

	public static int twoSumUniquePairs(List<Integer> nums, int target) {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> complement = new HashSet<>();
        
        int ans = 0;
        
        for (Integer num : nums) {
        	if (complement.contains(target - num) && !seen.contains(num)) {
        		// (num, target - num) is a pair that sums to the target
        		ans++;
        		// mark num and target - num as seen so that when we see (target - num, num) it won't be counted again
        		seen.add(num);
        		seen.add(target - num);
        	}

        	complement.add(num);
        }

        return ans;
    }
	
	public static int twoSumUniquePairs02(List<Integer> nums, int target) {
        
        if (nums == null || nums.size() < 2) {
            return 0;
        }
        
        int L = 0;
        int R = nums.size() - 1;
        int res = 0;
        
        Set<Integer> ns = new HashSet<Integer>();
        
        Collections.sort(nums);
        
        while(L < R) {
            
            if (ns.contains(nums.get(L))) {
                while(L < R && ns.contains(nums.get(L))) {
                    L++;
                }
            }
            
            if (ns.contains(nums.get(R))) {
                while(L < R && ns.contains(nums.get(R))) {
                    R--;
                }
            }
            
            if (nums.get(L) + nums.get(R) == target) {
                res++;
                ns.add(nums.get(L));
                ns.add(nums.get(R));
                
                L++;
                R--;
            }
        }
        
        return res;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 1 1 2 45 46 46
    // 47
    
    // 1 1
    // 2
    
    // 1 5 1 5
    // 6
    
    // 3 1 5 3 3
    // 6
    public static void main(String[] args) {
    	// Marcar o tempo de execução de um algoritmo:
    	long startTime = System.nanoTime();
    	
    	Scanner scanner = new Scanner(System.in);
        List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        int target = Integer.parseInt(scanner.nextLine());
        scanner.close();
        int res = twoSumUniquePairs02(nums, target);
        System.out.println(res);
        
        long endTime = System.nanoTime();
    	long timeElapsed = endTime - startTime;
    	System.out.println("Execution time in nanoseconds: " + timeElapsed);
    	System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }
}