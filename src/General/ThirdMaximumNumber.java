package General;

import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/third-maximum-number/
 * 
 * Given an integer array nums, return the third distinct maximum number in this array. 
 * If the third maximum does not exist, return the maximum number.
 * 
 * Example 1:
 * Input: nums = [3,2,1]
 * Output: 1
 * Explanation:
 * The first distinct maximum is 3.
 * The second distinct maximum is 2.
 * The third distinct maximum is 1.
 * 
 * Example 2:
 * Input: nums = [1,2]
 * Output: 2
 * Explanation:
 * The first distinct maximum is 2.
 * The second distinct maximum is 1.
 * The third distinct maximum does not exist, so the maximum (2) is returned instead.
 * 
 * Example 3:
 * Input: nums = [2,2,3,1]
 * Output: 1
 * Explanation:
 * The first distinct maximum is 3.
 * The second distinct maximum is 2 (both 2's are counted together since they have the same value).
 * The third distinct maximum is 1.
 */
public class ThirdMaximumNumber {

	public static void main(String[] args) {
		int[] nums = {3, 2, 1}; // 1
		//int[] nums = {1, 2}; // 2
		//int[] nums = {2, 2, 3, 1}; // 1
		//int[] nums = {3, 3, 3, 3}; // 3
		//int[] nums = {2, 2, 2, 3}; // 3
		//int[] nums = {2, 1, -1};  // -1
		//int[] nums = {2, 2, -1};  // 2
		//int[] nums = {-2147483648, 1, 1};  // 1
		//int[] nums = {1, 2, -2147483648}; // -2147483648
		
		System.out.println(thirdMax(nums));
	}

	public static int thirdMax(int[] nums) {
		if (nums.length == 0) {
            return -1;
        }
        
        Set<Integer> numbers = new HashSet<Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            numbers.add(nums[i]);
        }
        
        // para criar um max-heap é necessário usar o Collections.reverseOrder().
        Queue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        
        numbers.forEach(e -> heap.add(e));
        
        if (numbers.size() < 3) {
            return heap.poll();
        }
        
        for (int i = 0; i < 2; i++) {
            heap.poll();
        }
        
        return heap.poll();
	}
}