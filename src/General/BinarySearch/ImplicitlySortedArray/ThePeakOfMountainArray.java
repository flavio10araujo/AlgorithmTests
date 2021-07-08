package General.BinarySearch.ImplicitlySortedArray;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A mountain array is defined as an array that has at least 3 elements let's call the element with the largest value the "peak", with index k. 
 * The array elements monotonically increase from the first element to A[k], 
 * and then monotonically decreases from A[k + 1] to the last element of the array. 
 * Thus creating a "mountain" of numbers.
 * Find the index of the peak element. Assume there is no duplicate.
 * 
 * Input: 0 1 2 3 2 1 0
 * Output: 3
 * Explanation: the largest element is 3 and its index is 3.
 * 
 * Binary Search
 */
public class ThePeakOfMountainArray {

	public static int peakOfMountainArray(List<Integer> arr) {
		int left = 0;
        int right = arr.size() - 1;
        int peak = -1;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (arr.get(mid) < (arr.get(mid + 1))) {
            	left = mid + 1;
            } else {
            	peak = mid;
            	right = mid - 1;
            }
        }
        
        return peak;
    }
	
	public static void main(String[] args) {
		List<Integer> arr = Arrays.asList("0 1 2 3 2 1 0".split(" ")).stream().map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(peakOfMountainArray(arr));
	}
}