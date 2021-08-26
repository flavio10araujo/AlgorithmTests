package General.DivideAndConquer.Generalized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Count of Smaller Numbers after Self | Number of Swaps to Sort | Algorithm Swap
 * 
 * You are given an integer array nums and you have to return a new counts array. 
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 * 
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * For the number 5, there are 2 numbers smaller than it after it. (2 and 1)
 * For the number 2, there is 1 number smaller than it after it. (1)
 * For the number 6, there is also 1 number smaller than it after it. (1)
 * For the number 1, there are no numbers smaller than it after it.
 * Hence, we have [2, 1, 1, 0].
 * 
 * Number of swaps to sort
 * 
 * Another way to phrase the question is:
 * If we sort the array by finding the smallest pair i, j where i < j and a[i] > a[j] how many swaps are needed?
 * To answer that question we just have to sum up the numbers in the above output array: 2 + 1 + 1 = 5 swaps.
 * 
 * Explanation
 * 
 * Intuition
 * 
 * The brute force way to solve this question is really easy and intuitive, we simply go through the list of elements. 
 * For each of the element, we go through the elements after it and count how many numbers are smaller than it. 
 * This would result in a O(N^2) runtime. 
 * However, this approach is not the optimal solution.
 * Observe that if we need to reduce our solution's complexity, we will need to count multiple numbers' smaller count in one go. 
 * This can only be done using some kind of sorted order.
 * But sorting destroys the origin order of the array, what can we do about that?
 * Recall from introduction of divide and conquer questions, 
 * the common approach of tackling a divide and conquer question is dividing the data given into two components, 
 * assuming each components is solved and then try to merge the result.
 * What if we divide the numbers into two components by index and then sort them separate?
 * Since we divided the original array by index, after the two components are both sorted, 
 * all the elements in the left components still have smaller index than any element in the right components in the original array.
 * We can utilize this fact when we combine the two arrays together.
 * Thus, to solve this problem, we first split the data given into two components, the left and the right components. 
 * And then we assume that both components' sub-problem are already solved -- 
 * that is we know the count of number smaller than itself for each number for both components. 
 * Now all we need to know is for each number in the left component, how many elements are smaller than it in the right component.
 * This will allow us to know for each number in the left components, how many elements is smaller than it in the right component.
 * Thus, we have successfully solved the problem.
 * So, what is the run time of our improved solution? 
 * We split the problem into two components each recursion and go through each of the components, 
 * and each recursion takes O(N) time for the merge process. 
 * Thus we have T(N) = 2T(N/2) + O(N)
 * This recurrence will yield a total run time of O(N log N).
 */
public class NumberOfSwapsToSort {

	public static class Element {
        int val, ind;
        
        public Element(int val, int ind) {
            this.val = val;
            this.ind = ind;
        }
    }
	
    public static List<Integer> smallerArr = new ArrayList<Integer>();
    
    public static List<Element> mergeSort(List<Element> nums) {
        if (nums.size() <= 1) {
            return nums;
        }
        
        List<Element> splitLeft = new ArrayList<Element>();
        List<Element> splitRight = new ArrayList<Element>();
        
        for (int i = 0; i < nums.size(); i++) {
            if (i < nums.size() / 2) {
            	splitLeft.add(nums.get(i));
            } else {
            	splitRight.add(nums.get(i));
            }
        }
        
        List<Element> left = mergeSort(splitLeft);
        List<Element> right = mergeSort(splitRight);
        
        return merge(left, right);
    }
    
    public static List<Element> merge(List<Element> left, List<Element> right) {
        List<Element> result = new ArrayList<Element>();
        int l = 0, r = 0;
        
        while (l < left.size() || r < right.size()) {
            if (r >= right.size() || (l < left.size() && left.get(l).val <= right.get(r).val)) {
                result.add(left.get(l));
                smallerArr.set(left.get(l).ind, smallerArr.get(left.get(l).ind) + r);
                l += 1;
            } else {
                result.add(right.get(r));
                r += 1;
            }
        }
        
        return result;
    }
    
    public static List<Integer> countSmaller(List<Integer> nums) {
        for (int i = 0; i < nums.size(); i++) {
        	smallerArr.add(0);
        }
        
        List<Element> temp = new ArrayList<Element>();
        
        for (int i = 0; i < nums.size(); i++) {
        	temp.add(new Element(nums.get(i), i));
        }
        
        mergeSort(temp);
        
        return smallerArr;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 1 9 7 8 5
    // output: 0 3 1 1 0
    
    // -1 -1
    // output: 0 0
    
    // 6 3 7 1 5 8 2 4
    // output: 5 2 4 0 2 2 0 0
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        scanner.close();
        List<Integer> res = countSmaller(nums);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}