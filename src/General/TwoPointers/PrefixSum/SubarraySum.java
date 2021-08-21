package General.TwoPointers.PrefixSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given an array of integers and an integer target, find a subarray that sums to target and return the start and end indices of the subarray. 
 * It's guaranteed to have a unique solution.
 * 
 * Input:
 * 1 -20 -3 30 5 4
 * 7
 * Output: 1 4
 * Explanation: -20 - 3 + 30 = 7. The indices for subarray [-20,-3,30] is 1 and 4 (right exclusive).
 * 
 * Explanation:
 * 
 * Intuition
 * The brute force way is to find the sum of each subarray and compare it with the target. Let N be the number of elements in the array. 
 * There are N subarrays with size 1, N-1 subarrays with size 2 .. and 1 subarray with size N. Time complexity is O(N^2).
 * A key observation is the the sum of a subarray [i, j] is equal to the sum of [0, j] minus the sum of [0, i - 1].
 * 
 * The sum of elements from index 0 to i is called the prefix sum. 
 * If we have the subarray sum for each index, we can find the sum of any subarray in constant time.
 * 
 * With the knowledge of prefix sum under our belt, the problem boils down to Two Sum. We keep a dictionary of prefix_sum: index while going through the array calculating prefix_sum. 
 * If at any point, prefix_sum - target is in the dictionary we have found our subarray.
 */
public class SubarraySum {

	public static List<Integer> subarraySum(List<Integer> arr, int target) {
        Map<Integer, Integer> prefixSums = new HashMap<>();
        
        // prefix_sum 0 happens when we have an empty array.
        prefixSums.put(0, 0);
        
        int curSum = 0;
        
        for (int i = 0; i < arr.size(); i++) {
            curSum += arr.get(i);
            System.out.println("i="+i+" curSum="+curSum);
            
            System.out.println(prefixSums);
            
            int complement = curSum - target;
            
            System.out.println(complement);
            
            if (prefixSums.containsKey(complement)) {
                return List.of(prefixSums.get(complement), i + 1);
            }
            
            prefixSums.put(curSum, i + 1);
        }
        
        return null;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        List<Integer> arr = splitWords("1 3 -3 8 5 7").stream().map(Integer::parseInt).collect(Collectors.toList());
        int target = Integer.parseInt("5");
        List<Integer> res = subarraySum(arr, target);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}