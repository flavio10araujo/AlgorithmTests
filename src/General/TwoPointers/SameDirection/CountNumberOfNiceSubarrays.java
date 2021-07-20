package General.TwoPointers.SameDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * We define a "nice" array as an array that contains exactly k odd numbers, where k is a user-defined number.
 * In that case, given an array arr, find the number of "nice" continuous subarrays of that array (the array itself is its own subarray).
 * Duplicate subarrays are counted as different subarrays as long as their index range is different.
 * 
 * Parameters
 * k: An integer required for the definition of a "nice" array.
 * arr: A list of integers representing the array.
 * Result
 * An integer representing the number of nice subarrays of arr.
 * 
 * Example 1
 * Input: k = 3, arr = [1, 1, 2, 1, 1]
 * Output: 2
 * Explanation: The nice subarrays are [1, 1, 2, 1] and [1, 2, 1, 1].
 * 
 * Example 2
 * Input: k = 1, arr = [2, 4, 6, 8, 10]
 * Output: 0
 * Explanation: There are no odd integer in arr, so there are no odd subarrays.
 * 
 * Constraints
 * 1 <= k <= len(arr) <= 50000
 * 0 <= arr[i] <= 10^5
 */
public class CountNumberOfNiceSubarrays {
	
	public static int countNiceSubarrays(int k, List<Integer> arr) {
        ArrayList<Integer> oddIndices = new ArrayList<>();
        
        // For the sake of easier calculation, let the zeroth odd index be -1.
        // This way, we can easily calculate the number of even numbers before the first
        // odd number using the same way as between two odd numbers.
        oddIndices.add(-1);
        
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) % 2 == 1) {
                oddIndices.add(i);
            }
        }
        
        if (oddIndices.size() <= k) {
            return 0;
        }
        
        int ans = 0;
        // For the same reason as above, the final index is n, where n is the size of arr.
        oddIndices.add(arr.size());
        
        for (int i = 0; i < oddIndices.size() - k - 1; i++) {
            int startDiff = oddIndices.get(i + 1) - oddIndices.get(i);
            int endDiff = oddIndices.get(i + k + 1) - oddIndices.get(i + k);
            ans += startDiff * endDiff;
        }
        
        return ans;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
    	long startTime = System.nanoTime();
    	int k = Integer.parseInt("3");
        List<Integer> arr = splitWords("2 4 5 7 8 10 11 12 14 15 18 20").stream().map(Integer::parseInt).collect(Collectors.toList());
        int res = countNiceSubarrays(k, arr);
        System.out.println(res);
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }
}