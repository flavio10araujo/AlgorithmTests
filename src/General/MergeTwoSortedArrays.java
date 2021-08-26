package General;

/**
 * Leetcode 88.
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, 
 * and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
 * 
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 * 
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1. 
 * To accommodate this, nums1 has a length of m + n, 
 * where the first m elements denote the elements that should be merged, 
 * and the last n elements are set to 0 and should be ignored. 
 * nums2 has a length of n.
 * 
 * Example 1:
 * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * Output: [1,2,2,3,5,6]
 * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
 * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
 * 
 * Example 2:
 * Input: nums1 = [1], m = 1, nums2 = [], n = 0
 * Output: [1]
 * Explanation: The arrays we are merging are [1] and [].
 * The result of the merge is [1].
 * 
 * Example 3:
 * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
 * Output: [1]
 * Explanation: The arrays we are merging are [] and [1].
 * The result of the merge is [1].
 * Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
 */
public class MergeTwoSortedArrays {

	public static void main(String[] args) {
		int[] arr1 = {1,2,7};
		int[] arr2 = {2,5,6};
		int[] arr3 = new int[arr1.length + arr2.length];
				
		MergeTwoSortedArrays.merge(arr1, arr2, arr3);
		
		for (int i = 0; i < arr3.length; i++) {
			System.out.print(arr3[i] + " ");
		}
		
		System.out.println("");
		
		// #######
		
		int[] nums1 = {1,2,7,0,0,0};
		int m = 3;
		int[] nums2 = {2,5,6};
		int n = nums2.length;
		
		MergeTwoSortedArrays.mergeAndReturnTheSameArray(nums1, m, nums2, n);
		
		for (int i = 0; i < nums1.length; i++) {
			System.out.print(nums1[i] + " ");
		}
	}

	/**
	 * This method is going to merge two arrays and return a new array with the sorted values.
	 * 
	 * @param arr1 = array 1
	 * @param arr2 = array 2
	 * @param arr3 = array that will be returned.
	 */
	public static void merge(int[] arr1, int[] arr2, int[] arr3) {
	
		int m = arr1.length, n = arr2.length, i = 0, j = 0, k = 0;
	     
        // Traverse both array.
        while (i < m && j < n) {
            // Check if current element of first array is smaller than current element of second array.
        	// If yes, store first array element and increment first array index. 
        	// Otherwise do same with second array.
            if (arr1[i] < arr2[j]) {
                arr3[k] = arr1[i];
                i++;
            } else {
                arr3[k] = arr2[j];
                j++;
            }
            
            k++;
        }
     
        // Store remaining elements of first array.
        while (i < m) {
            arr3[k] = arr1[i];
            i++;
            k++;
        }
     
        // Store remaining elements of second array.
        while (j < n) {
            arr3[k] = arr2[j];
            j++;
            k++;
        }
	}
	
	/**
	 * Leetcode 88 : https://leetcode.com/problems/merge-sorted-array/
	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public static void mergeAndReturnTheSameArray(int[] nums1, int m, int[] nums2, int n) {
		
		m--;
		n--;
		
		int index = nums1.length - 1;
		
		while (index >= 0) {
			if (m < 0) {
				nums1[index] = nums2[n--];
			} else if (n < 0) {
				nums1[index] = nums1[m--];
			} else {
				if (nums1[m] > nums2[n]) {
					nums1[index] = nums1[m--];
				} else {
					nums1[index] = nums2[n--];
				}
			}
			
			index--;
		}
	}
}