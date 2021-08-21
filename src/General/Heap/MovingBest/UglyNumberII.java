package General.Heap.MovingBest;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Write a program to find the n-th ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * 
 * Example 1:
 * Input: n = 10
 * Output: 12
 * Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * 
 * Note:
 * 1 is typically treated as an ugly number.
 * n does not exceed 1690.
 * 
 * Solution
 * For this question, it is impossible to sort all ugly numbers, considering that there are infinite amount of them. 
 * Therefore, we need something else to help us find the kth ugly number.
 * Recall that a min heap always maintain the smallest item. 
 * In that case, what we can do is starting from 1, in each cycle, we pop the top of the heap and insert back that 
 * number multiplied by 2, 3, 5 into the heap (if that number wasn't in the heap already). 
 * We can find the kth ugly number this way because in each cycle, we pop the next smallest ugly number and inserting new, larger ones into the heap.
 * 
 * Time Complexity: O(ans)
 * Let ans denote the final n-th ugly number. We keep track of each number checked and we never check a number twice. Worst case we check every number from 1 to ans resulting in a time complexity of O(ans).
 */
public class UglyNumberII {

	public static final int[] allowedPrime = {2, 3, 5};

    public static int nthUglyNumber(int n) {
        Queue<Integer> ansHeap = new PriorityQueue<>();
        Set<Integer> usedNums = new HashSet<>();
        
        ansHeap.offer(1);
        usedNums.add(1);
        
        for (int i = 0; i < n - 1; i++) {
            int res = ansHeap.poll();
            
            for (int multiplier : allowedPrime) {
                if (!usedNums.contains(res * multiplier)) {
                    ansHeap.offer(res * multiplier);
                    usedNums.add(res * multiplier);
                    
                    System.out.println(ansHeap);
                    
                }
            }
        }
        
        return ansHeap.poll();
    }
    
    public static void main(String[] args) {
        int n = Integer.parseInt("10");
        int res = nthUglyNumber(n);
        System.out.println(res);
    }
}