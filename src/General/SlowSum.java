package General;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class SlowSum {

	public static void main(String[] args) {
		int[] arr = {4, 2, 1, 3}; // 26
		System.out.println(getTotalTime(arr));
	}

	public static int getTotalTime(int[] arr) {
	    // Write your code here
	    Queue<Integer> maxheap = new PriorityQueue<>(Collections.reverseOrder());
	    int ans = 0;
	    
	    for (Integer i : arr) {
	      maxheap.add(i);
	    }
	    
	    for (int i = 0; i < arr.length - 1; i++) {
	      int sum = maxheap.poll() + maxheap.poll();
	      ans += sum;
	      
	      maxheap.add(sum);
	    }
	    
	    return ans;
	  }
}