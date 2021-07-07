package General.Heap.TopK;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Find the kth largest element in an unsorted array. 
 * Note that it is the kth largest element in the sorted order, not necessarily the kth distinct element.
 * 
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * 
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * 
 * Note: You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestElementInAnArray {

	public static int solution01(List<Integer> nums, int k) {
		Queue<Integer> queue = new PriorityQueue<Integer>(nums.size());
		int res = 0;
		
		for (Integer num : nums) {
			queue.add(-num);
		}
		
		for (int i = 0; i < k; i++) {
			res = queue.poll();
		}
		
        return res * -1;
    }
	
	public static int solution02(List<Integer> nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(nums);
        
        for (int j = 0; j < nums.size() - k; j++)
            heap.poll();
        
        return heap.poll();
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        int k = Integer.parseInt(scanner.nextLine());
        scanner.close();
        int res = solution01(nums, k);
        System.out.println(res);
    }
}