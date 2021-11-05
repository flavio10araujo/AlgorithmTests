package General;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * 
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * You must write an algorithm that runs in O(n) time.
 * 
 * Example 1:
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * 
 * Example 2:
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 * 
 * Constraints:
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class LongestConsecutiveSequence {

	public static void main(String[] args) {
		//int[] nums = {100,4,200,1,3,2}; // 4
		//int[] nums = {0,3,7,2,5,8,4,6,0,1}; // 9
		//int[] nums = {1,2,3,2,1,0,-1,-2,-3,-2,-1}; // 7
		//int[] nums = {-339,711,-497,-940,867,-703,654,-852,116,963,15,822,588,925,-501,-431,-412,191,77,-581,781,421,387,-458,-961,-886,915,-29,283,19,-967,-982,-270,-391,-923,-330,-95,803,-109,-905,839,978,916,428,903,-512,-930,-435,678,146,56,533,-359,-556,-925,-321,-443,-937,649,840,154,755,857,883,-636,933,-373,268,736,590,362,575,641,351,76,618,838,146,-817,517,-635,238,123,5,681,197,683,-688,259,406,-848,-513,234,-991,-630,597,-985,69,-62,823,-268,-626,471,-626,724,-379,991,636,839}; // 3
		int[] nums = {-339,711,-497,-940,867,-703,654,-852,116,963,15,822,588,925,-501,-431,-412,191,77,-581,781,421,387,-458,-961,-886,915,-29,283,19,-967,-982,-270,-391,-923,-330,-95,803,-109,-905,978,916,428,903,-512,-930,-435,678,146,56,533,-359,-556,-925,-321,-443,-937,649,154,755,857,883,-636,933,-373,268,736,590,362,575,641,351,76,618,146,-817,517,-635,238,123,5,681,197,683,-688,259,406,-848,-513,234,-991,-630,597,-985,69,-62,823,-268,-626,471,-626,724,-379,991,636,838,839,839,840}; // 3
		//int[] nums = {838,839,839,840}; // 3
		
		System.out.println(solution01(nums));
	}
	
	/**
	 * Approach: heap.
	 * Time complexity: O(log n) + O(n).
	 * @param nums
	 * @return
	 */
	public static int solution01(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        
        Queue<Integer> heap = new PriorityQueue<>();
        
        for (int i = 0; i < nums.length; i++) {
            heap.add(nums[i]);
        }
        
        int maxSeq = 1;
        int countSeq = 1;
        Integer before = heap.poll();
        
        while (!heap.isEmpty()) {
        	
        	Integer current = heap.peek(); 
        	
        	if (current == before + 1) {
                countSeq++;
            } else if (current.intValue() != before.intValue()) {
                countSeq = 1;
            }
            
            maxSeq = Math.max(maxSeq, countSeq);
            
            before = heap.poll();
        }
        
        return maxSeq;
    }
	
	/**
	 * Time complexity: O(n).
	 * @param nums
	 * @return
	 */
	public static int solution02(int[] nums) {
		Set<Integer> num_set = new HashSet<Integer>();
        
		for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num-1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
	}
}