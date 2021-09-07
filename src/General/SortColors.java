package General;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * https://leetcode.com/problems/sort-colors/
 * 
 * Other name for this problem: Sorting a list with 3 unique numbers.
 * 
 * Given an array nums with n objects colored red, white, or blue, 
 * sort them in-place so that objects of the same color are adjacent, 
 * with the colors in the order red, white, and blue.
 * 
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * 
 * You must solve this problem without using the library's sort function.
 * 
 * Example 1:
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * 
 * Example 2:
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 * 
 * Example 3:
 * Input: nums = [0]
 * Output: [0]
 * 
 * Example 4:
 * Input: nums = [1]
 * Output: [1]
 */
public class SortColors {

	static int count = 0;
	
	public static void main(String[] args) {
		int[] nums = {2,0,2,1,1,0};
		
		System.out.println(Arrays.toString(nums));
		//sortColors(nums);
		solution02(nums);
		System.out.println(Arrays.toString(nums));
		
		System.out.println(count);
	}

	/**
	 * We can just count the number of each color and recreate the array at the end.
	 * O(2 * n).
	 */
	public static void sortColors(int[] nums) {
		int color00 = 0;
		int color01 = 0;
		int color02 = 0;
		
		for (int i = 0; i < nums.length; i++) {
			count++;
			
			if (nums[i] == 0) {
				color00++;
			} else if (nums[i] == 1) {
				color01++;
			} else if (nums[i] == 2) {
				color02++;
			}
		}
		
		int i = 0;
		
		while(color00 > 0) {
			count++;
			nums[i] = 0;
			color00--;
			i++;
		}
		
		while(color01 > 0) {
			count++;
			nums[i] = 1;
			color01--;
			i++;
		}
		
		while(color02 > 0) {
			count++;
			nums[i] = 2;
			color02--;
			i++;
		}
	}
	
	/**
	 * Swapping values.
	 * O(n).
	 */
	public static void solution02(int[] nums) {
		int i = 0;
		int j = nums.length - 1;
		int k = j;
		
		// The idea is:
		// When the curr number is 0, we swap to put the 0 in the first positions.
		// When the curr number is 2, we swap to put the 2 in the last posistions.
		// When the curr number is 1, we do nothing.
		while(i <= j) {
			count++;
			int curr = nums[j];
			
			System.out.println("curr="+curr);
			
			if (curr == 0) {
				swap(nums, i, j);
				i++;
			} else if (curr == 2) {
				swap(nums, j, k);
				j--;
				k--;
			} else {
				j--;
			}
		}
	}
	
	/**
	 * Method used to swap two numbers in the array nums.
	 * biggest is the index of the biggest number.
	 * smallest is the index of the smallest number.
	 * The goal is to put the smallest number in an index before the biggest number.
	 * 
	 * @param nums
	 * @param biggest
	 * @param smallest
	 */
	public static void swap(int[] nums, int biggest, int smallest) {
		System.out.println("swap -> biggest=" + biggest + " smallest=" + smallest + " nums["+biggest+"]="+nums[biggest]+ " nums["+smallest+"]="+nums[smallest]);
		int temp = nums[biggest];
		nums[biggest] = nums[smallest];
		nums[smallest] = temp;
		
		System.out.println(Arrays.toString(nums));
	}
}