package General.Others.MonotonicStack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Given a list of daily temperatures T, return a list such that, for each day in the input, 
 * tells you how many days you would have to wait until a warmer temperature. 
 * If there is no future day for which this is possible, put 0 instead.
 * 
 * Input: [73, 74, 75, 71, 69, 72, 76, 73]
 * Output: [1, 1, 4, 2, 1, 1, 0, 0]
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 * 
 * Solution:
 * 
 * This is a classic monotonic stack problem. 
 * Since we don't need to pop anything from the start, we don't even need to use a deque.
 * Time Complexity: O(n)
 * We loop through the array once and stack is an O(1) data structure for inserting and popping.
 */
public class DailyTemperatures {

	public static List<Integer> dailyTemperatures (List<Integer> t) {
        
		// Stores a decreasing list of temperature by index.
        Deque<Integer> deque = new ArrayDeque<Integer>();
        
        List<Integer> ans = new ArrayList<Integer>();
        int n = t.size();
        
        // For this implementation, we start from the reverse.
        for (int i = t.size() - 1; i >= 0; i--) {
            
        	int element = t.get(i);
            
        	// Special case when `i == n - 1`, which is the last day.
            if (i == n - 1) {
                deque.offerLast(i);
                ans.add(0);
                continue;
            }
            
            while (!deque.isEmpty() && element >= t.get(deque.peekLast())) {
                deque.pollLast();
            }
            
            // If deque is nonempty, there is a next big temperature, so we store it in ans. Otherwise, there are no bigger temperature, so we record `0`
            if (!deque.isEmpty()) {
                ans.add(deque.peekLast() - i);
            }
            else {
                ans.add(0);
            }
            
            deque.offerLast(i);
        }
        
        // The answer is appended in reverse order, so we need to reverse the list first.
        Collections.reverse(ans);
        
        return ans;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 73 74 75 71 69 72 76 73
    // output: 1 1 4 2 1 1 0 0
    
    // 75 73 71 70 70 72 74 76 76 75
    // output: 7 5 3 2 1 1 1 0 0 0
    
    // 89 5 57 14 80 64 28 41 8 78 74 7 80 43 65 48
    // output: 0 1 2 1 0 4 1 2 1 3 2 1 0 1 0 0
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> t = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        scanner.close();
        List<Integer> res = dailyTemperatures(t);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}