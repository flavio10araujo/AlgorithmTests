package General.Others.MonotonicStack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
 * 
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray 
 * such that the absolute difference between any two elements of this subarray is less than or equal to limit.
 * 
 * Example 1:
 * Input: nums = [8,2,4,7], limit = 4
 * Output: 2
 * 
 * Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4.
 * [8,2] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
 * [2] with maximum absolute diff |2-2| = 0 <= 4.
 * [2,4] with maximum absolute diff |2-4| = 2 <= 4.
 * [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
 * [4] with maximum absolute diff |4-4| = 0 <= 4.
 * [4,7] with maximum absolute diff |4-7| = 3 <= 4.
 * [7] with maximum absolute diff |7-7| = 0 <= 4.
 * Therefore, the size of the longest subarray is 2.
 * 
 * Example 2:
 * Input: nums = [10,1,2,4,7,2], limit = 5
 * Output: 4
 * Explanation: The subarray 2,4,7,2 is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
 * 
 * Example 3:
 * Input: nums = [4,2,2,2,4,4,2,2], limit = 0
 * Output: 3
 * 
 * Constraints
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= limit <= 10^9
 * 
 * Solution:
 * 
 * We define the absolute diff of an array the amplitude of that array.
 * For this question, note that from we can simply go through as much as possible until we reach a number to cause the amplitude to exceed limit. 
 * However, a simple double loop will have a time complexity of O(n^2), which is not enough for this problem. 
 * Can we reduce this to hopefully O(n)?
 * For this problem, we can use two monotonic deques and utilize their monotonic properties. 
 * Basically, we have two pointers, a pointer that points to the start and a pointer that points to the end of an interval. 
 * We move the end pointer each time, and insert that value to the deque. 
 * If, at any time, the amplitude exceeds limit, we move the start pointer, popping items from the left of the queue if necessary, until the amplitude falls within the range. 
 * Then, we just record the max size of the array until we finish moving the end pointer. 
 * The resulting time complexity can be shrunk down to O(n).
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

	public static int longestSubarray(List<Integer> nums, int limit) {
        
		int best = 0;
        
		// A strictly increasing deque consisting of members from [left, right]. 
		// The min item in this subarray is min_deque[0].
        Deque<Integer> minDeque = new ArrayDeque<Integer>();
        
        // A strictly decreasing deque consisting of members from [left, right]. 
        // The max item in this subarray is max_deque[0]
        Deque<Integer> maxDeque = new ArrayDeque<Integer>();
        
        int left = 0;
        
        for (int right = 0; right < nums.size(); right++) {
            
        	while (!minDeque.isEmpty() && nums.get(right) < nums.get(minDeque.peekLast())) { 
        		minDeque.pollLast(); 
        	}
        	
            minDeque.add(right);
            
            while (!maxDeque.isEmpty() && nums.get(right) > nums.get(maxDeque.peekLast())) { 
            	maxDeque.pollLast(); 
            }
            
            maxDeque.add(right);
            
            // While the amplitude exceeds limit, increasing left and popping items if needed.
            while (nums.get(maxDeque.peekFirst()) - nums.get(minDeque.peekFirst()) > limit) {
                if (maxDeque.peekFirst() == left) {
                    maxDeque.pollFirst();
                }
                
                if (minDeque.peekFirst() == left) {
                    minDeque.pollFirst();
                }
                
                left += 1;
            }
            
            // Stores the max subarray size up to this point.
            best = Math.max(best, right - left + 1);
        }
        
        return best;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 8 2 4 7
    // 4
    // output: 2
    
    // 10 1 2 4 7 2
    // 5
    // output: 4
    
    // 4 2 2 2 4 4 2 2
    // 0
    // output: 3
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        int limit = Integer.parseInt(scanner.nextLine());
        scanner.close();
        int res = longestSubarray(nums, limit);
        System.out.println(res);
    }
}