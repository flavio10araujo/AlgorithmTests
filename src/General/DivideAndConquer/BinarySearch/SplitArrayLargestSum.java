package General.DivideAndConquer.BinarySearch;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. 
 * Write an algorithm to minimize the largest sum among these m subarrays.
 * 
 * Example:
 * Input: [7,2,5,10,8], 2
 * Output: 18
 * 
 * Intuition and Explanation
 * 
 * In order to tackle a question like this, we should first start thinking about the brute force approach of solving this problem. 
 * Since we want to minimize the largest sum among the subarrays, one approach we could use is to try every single sum that is possible.
 * How do we do that? Well, first we need to find the range of possible sums that we could try out. 
 * As the question suggested, the array only consists of non-negative integers, thus the lower bound for possible sum should be 0. 
 * Since the largest sum cannot exceed the sum of the original array, thus the upper bound for the possible sum is sum of the entire array.
 * Now that we know the possible sum's lower and upper bound, how do we test whether each possible sum works? 
 * Well, we could iterate through the array and keep a sum for the current subarray sum 
 * and keep a counter for the number of subarrays we have already seen. 
 * Each time we go through an element, we add the element to our current sum. 
 * If our current sum exceeds the possible sum that we are currently testing, we simply increment the number of subarrays seen counter. 
 * After the loop terminates, we will return true (meaning that the current possible sum we are testing is indeed valid) 
 * if the number of subarrays seen is less than the limit.
 * This way, we can iterate through all the possible sums from the smallest to the largest and test each one individually. 
 * If we find one possible sum that satisfy the conditions, we will return that sum.
 * This is the brute force solution. 
 * However, we can see that the solution itself can be quite slow. 
 * The complexity is not only dependent on the size of the array N but also the sum of the array. 
 * This will approximately give us a complexity of O(N^2). 
 * How can we simply this?
 * Observe that the process of finding the lowest possible sum is a searching problem in a sorted array. 
 * We can therefore think of the problem as "find the index of last 0 in a sorted array consisting of only 0s and 1s". 
 * We can apply binary search in this case on finding the lowest possible sum itself.
 * This will allow us to reduce the complexity of the problem from O(N^2) to O(N Log N).
 */
public class SplitArrayLargestSum {

	public static int target = 0;
	
    public static boolean canSatisfy(List<Integer> nums, int m) {
        int val = 0;
        
        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i);
            val += num;
            
            if (num > target) {
                return false;
            }
            
            if (val > target) {
                val = num;
                m -= 1;
            } else if (i < nums.size() - 1 && val == target) {
                val = 0;
                m -= 1;
            }
        }
        
        return m > 0 && val <= target;
    }
    
    public static int splitArrayLargestSum(List<Integer> nums, int m) {
        int minVal = 0;
        int maxVal = Integer.MAX_VALUE;
        int successTarget = 0;
        
        while (minVal <= maxVal) {
            target = (minVal + maxVal) / 2;
            
            System.out.println("target="+target);
            
            if (canSatisfy(nums, m)) {
                maxVal = target - 1;
                successTarget = target;
            } else {
                minVal = target + 1;
            }
        }
        
        return successTarget;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 7 2 5 10 8
    // 2
    // output: 18
    
    // 1 1 1 1 1 3 2
    // 5
    // output: 3
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        int m = Integer.parseInt(scanner.nextLine());
        scanner.close();
        int res = splitArrayLargestSum(nums, m);
        System.out.println(res);
    }
}