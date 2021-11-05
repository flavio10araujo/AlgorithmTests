package General.Heap.TopK;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 * 
 * Given an integer array nums and an integer k, return the k most frequent elements. 
 * You may return the answer in any order.
 * 
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * 
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 * 
 * Constraints:
 * 1 <= nums.length <= 105
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 */
public class TopKFrequentElements {

	public static void main(String[] args) {
		int k = 2;
		int[] nums = {1,2,1,4,4,4,1,3,2,4};
		
		int[] ans = topKFrequent(nums, k);
		for (int i = 0; i < ans.length; i++) {
			System.out.print(ans[i] + " ");
		}
	}
	
	public static int[] topKFrequent(int[] nums, int k) {
        int[] ans = new int[k];
        
        Map<Integer, Integer> count = new HashMap<>();
        Queue<Item> heap = new PriorityQueue<>((a, b) -> a.freq - b.freq);
        
        for (int i = 0; i < nums.length; i++) {
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
        }
        
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            heap.add(new Item(entry.getKey(), entry.getValue()));
            
            if (heap.size() > k) {
                heap.poll();
            }
        }
        
        for (int i = 0; i < k; i++) {
            ans[i] = heap.poll().num;
        }
        
        return ans;
    }
    
    static class Item {
        public int num;
        public int freq;
        
        public Item(int num, int freq) {
            this.num = num;
            this.freq = freq;
        }
    }
}