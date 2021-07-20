package General.TwoPointers.PrefixSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given an array of integers and an integer K, find the number of subarrays which are divisible by K.
 * 
 * Input: [3,1,2,5,1], 3
 * Output: 6
 * Explanation: the six subarrays are[3], [3,1,2], [1,2], [5,1], [3,1,2,5,1], [1,2,5,1].
 * 
 * This problem is very similar to the previous problem subarray sum's follow-up problem. 
 * Instead of saving the prefix_sum, we save the remainder prefix_sum % k.
 * Time Complexity: O(n)
 */
public class SubarraySumDivisibleByK {

	public static int subarraySumDivisible(List<Integer> nums, int k) {
        Map<Integer, Integer> remainders = new HashMap<>();
        remainders.put(0, 1);
        
        int curSum = 0;
        int count = 0;
        
        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i);
            curSum += num;
            
            int remainder = curSum % k;
            
            int complement = (k - remainder) % k;
            
            if (remainders.containsKey(complement)) {
                count += remainders.get(complement);
            }
            
            if (remainders.containsKey(complement)) {
                remainders.replace(complement, remainders.get(complement) + 1);
            } else {
                remainders.put(complement, 1);
            }
        }
        
        return count;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }
    
    public static void main(String[] args) {
        List<Integer> nums = splitWords("3 1 2 5 1").stream().map(Integer::parseInt).collect(Collectors.toList());
        int k = Integer.parseInt("3");
        int res = subarraySumDivisible(nums, k);
        System.out.println(res);
    }
}