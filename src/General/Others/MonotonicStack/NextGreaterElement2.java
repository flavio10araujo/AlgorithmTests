package General.Others.MonotonicStack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Given a circular array (the next element of the last element is the first element of the array), 
 * print the Next Greater Number for every element. 
 * The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, 
 * which means you could search circularly to find its next greater number. 
 * If it doesn't exist, output -1 for this number.
 * 
 * Example 1:
 * Input:  [1,  2, 1]
 * Output: [2, -1, 2]
 * Explanation: 
 * The first 1's next greater number is 2; 
 * The number 2 can't find next greater number; 
 * The second 1's next greater number needs to search circularly, which is also 2.
 * 
 * Note: The length of given array won't exceed 10000.
 * 
 * Solution:
 * 
 * This problem is very similar to Daily Temperatures, except the array is circular. 
 * For this problem, the approach is similar, except after the first pass, 
 * we do a second pass to find a larger number for the remaining items on the stack without pushing new items in 
 * (since they are pushed at least once already in the first pass). 
 * This is the solution: Time Complexity: O(n).
 */
public class NextGreaterElement2 {

	public static class Element{
        int val, ind;
        
        public Element(int val, int ind) {
            this.val = val;
            this.ind = ind;
        }
    }
	
    public static List<Integer> nextGreaterElements(List<Integer> nums) {
        Deque<Element> deque = new ArrayDeque<Element>();
        List<Integer> result = new ArrayList<Integer>();
        
        for (int i = 0; i < nums.size(); i++) {
            result.add(-1);
        }
        
        for (int i = 0; i < nums.size(); i++) {
            int cur = nums.get(i);
            
            while (!deque.isEmpty() && cur > deque.peekLast().val) {
                Element element = deque.pollLast();
                result.set(element.ind, cur);
            }
            
            deque.add(new Element(cur, i));
        }
        
        for (int cur : nums) {
            while (cur > deque.peekLast().val) {
                Element element = deque.pollLast();
                result.set(element.ind, cur);
            }
        }
        
        return result;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 1 2 1
    // output: 2 -1 2
    
    // 1 2 1 1 1 2 4 1
    // output: 2 4 2 2 2 4 -1 2
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        scanner.close();
        List<Integer> res = nextGreaterElements(nums);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}