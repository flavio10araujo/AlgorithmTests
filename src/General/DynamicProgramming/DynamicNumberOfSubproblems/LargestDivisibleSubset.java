package General.DynamicProgramming.DynamicNumberOfSubproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * You are given a set of numbers nums consisting of distinct numbers. 
 * Find the size of the largest subset that satisfies the following condition: 
 * for each two number pairings in the set, one number is divisible by the other.
 * 
 * Input: nums: a list integers representing the set.
 * Output: An integer representing the size of the largest subset that satisfy the condition.
 * 
 * Example 1:
 * Input: nums = [1, 2, 3]
 * Output: 2
 * Explanation: Either [1, 2] or [1, 3] satisfy the condition, because both 2 and 3 are both divisible by 1. Either way, the largest set has a size of 2.
 * 
 * Example 2:
 * Input: nums = [1, 2, 4, 8]
 * Output: 4
 * Explanation: In this set, for each pair of numbers, at least one is divisible by the other 
 * 				because they are all powers of 2. As such, the max subset has a size of 4, the size of the original set.
 * 
 * Constraints
 * 1 <= len(nums) <= 1000
 * 1 <= nums[i] <= 10^9
 * Each number in nums is unique.
 * 
 * Solution:
 * 
 * Note that divisibility is transitive. 
 * That is, if "a" is divisible by "b" and "b" is divisible by "c", then "a" is divisible by "c". 
 * Furthermore, if "a" is divisible by "b", then "a" must be larger than it (or equal, but that is not relevant in this question as the numbers are unique).
 * Therefore, if a set satisfies the condition, starting from the lowest number, the next smallest number must be divisible by the current number. 
 * This means that we can expand on the set by finding a number that is divisible by the largest element in the existing set.
 * In that case, for each number in nums, consider the size of the largest set that satisfy the condition and whose largest element is equal to that number. 
 * It is equal to the size of the largest subset whose largest number can divide the current number (not including the current number) plus 1. 
 * If no such set exists, then the largest size is 1 because the set simply consist of the number itself (and the condition always holds true for sets with one element).
 * Knowing this, we have built up a logic for a bottom-up DP: 
 * starting from the smallest number, 
 * we calculate the size of the largest set that satisfy the condition 
 * and whose largest number is that number using the methods above. 
 * Then, the size of the largest set that satisfies the condition is the max size from the numbers calculated above.
 */
public class LargestDivisibleSubset {

	public static int findLargestSubset(List<Integer> nums) {
		
        Collections.sort(nums);
        
        List<Integer> maxSubsets = new ArrayList<>();
        
        for (int i = 0; i < nums.size(); i++) {
            int maxSubset = 0;
            
            for (int j = 0; j < i; j++) {
                
            	if (nums.get(i) % nums.get(j) == 0) {
                    maxSubset = Math.max(maxSubset, maxSubsets.get(j));
                }
            }
            
            maxSubsets.add(maxSubset + 1);
        }
        
        return Collections.max(maxSubsets);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 1 2 3
    // output: 3
    
    // 1 2 4 8
    // output: 4
    
    // 8 9 4 2 12 1 3
    // output : 4
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        scanner.close();
        int res = findLargestSubset(nums);
        System.out.println(res);
    }
}