package General.Others.MonotonicStack;

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

	
}