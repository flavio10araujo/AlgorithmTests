package General.TwoPointers.SlidingWindow;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * You are given two sorted arrays of distinct integers, arr1, and arr2.
 * Your goal is to start from the beginning of one array, and arrive to the end of one array.
 * For each step, you can either move forward a step on an array, or move to somewhere the element in the target is the same as the element in your current square. 
 * Your total score is defined as the sum of all unique numbers that you have been on.
 * Find the maximum score that you can get given the above rules, modded by 10^9 + 7.
 * 
 * Parameters
 * arr1: A list of ordered, distinct integers.
 * arr2: Another list of ordered, distinct integers.
 * Result
 * The maximum score possible, modded by 10^9 + 7.
 * 
 * Example
 * Input: arr1 = [2, 4, 5, 8, 10], arr2 = [4, 6, 8, 9]
 * Output: 30
 * 
 * Constraints
 * 1 <= len(arr1), len(arr2) <= 50000
 * 1 <= arr1[i], arr2[i] <= 10^7
 * arr1[i] < arr1[j] for all i < j. Same goes for arr2.
 * 
 * Solution:
 * For simplicity, we call numbers that appears multiple times "teleporters". 
 * Because both arrays are ordered and distinct, teleporter number must appear in both array exactly once, and each teleporter in both arrays 
 * must be ordered in the same way.
 * Consider the interval between two teleporters. 
 * If there are no number between these two numbers that appear in both array, then in those steps you can only go forward. 
 * Therefore, the score in that section would be the sum of the subarray in that section.
 * Furthermore, regardless where you start, it does not affect the choice whether to go top or go bottom in that section. 
 * If you start from the top array, you can go left to continue the top array, and teleport to the bottom array. 
 * The same goes for if you start from the bottom array. In that case, to maximize the score, we only need to maximize the score of each interval between 
 * two teleporters.
 * For the same implementation, we use two pointers, one for each array. 
 * We always move the smaller pointer forward and keep track of the sum of the subsection until both pointers are the same. 
 * Then, we find the maximum sum between these two subsections and add them to the result. 
 * Repeat this until we finished iterating through both arrays. The time complexity is O(n+m), where n and m are the size of the arrays, respectively.
 */
public class GetTheMaximumScore {

	public static final int MODULO_AMT = 1000000007;

    public static int maximumScore(List<Integer> arr1, List<Integer> arr2) {
        int ptr1 = 0, ptr2 = 0;
        int n1 = arr1.size(), n2 = arr2.size();
        // We use long for these attributes because they can exceed integer limit.
        // The max score, summed up and modded.
        long result = 0;
        // The sum of the subarray between the previous teleporter and the pointer for each array.
        long sectionSum1 = 0, sectionSum2 = 0;
        
        // As long as the two arrays are not both at the end, we advance the pointers.
        while (ptr1 < n1 || ptr2 < n2) {
            
        	// If they match, we sum up the max score of that section and the score of the current position, then shrink result by using modulo.
            // Reset the sums and move the pointers afterwards.
            if (ptr1 < n1 && ptr2 < n2 && arr1.get(ptr1).equals(arr2.get(ptr2)) ) {
                result += Math.max(sectionSum1, sectionSum2) + arr1.get(ptr1);
                result %= MODULO_AMT;
                sectionSum1 = 0;
                sectionSum2 = 0;
                ptr1++;
                ptr2++;
                continue;
            }
            
            // If either "ptr1" reaches the end, or "ptr2" is smaller than "ptr1" we move "ptr2" and keep track of the sum.
            if (ptr1 == n1 || (ptr2 != n2 && arr1.get(ptr1) > arr2.get(ptr2))) {
                sectionSum2 += arr2.get(ptr2);
                ptr2++;
            } else {
                // Otherwise, we move "ptr1" and keep track of that sum.
                sectionSum1 += arr1.get(ptr1);
                ptr1++;
            }
        }
        
        // Add the remaining max section sum to the result, then return the result modulo.
        result += Math.max(sectionSum1, sectionSum2);
        
        return (int)(result % MODULO_AMT);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }
    
    public static void main(String[] args) {
        List<Integer> arr1 = splitWords("2 4 5 8 10").stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> arr2 = splitWords("4 6 8 9").stream().map(Integer::parseInt).collect(Collectors.toList());
        int res = maximumScore(arr1, arr2);
        System.out.println(res);
    }
}