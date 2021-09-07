package General;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Given a list of numbers, where every number shows up twice except for one number, find that one number.
 * 
 * Example:
 * Input: [4, 3, 2, 4, 1, 3, 2]
 * Output: 1
 */
public class FindTheNonDuplicateElement {

	public static void main(String[] args) {
		int arr[] = { 4, 3, 2, 4, 1, 3, 2 };
        System.out.print(firstNonRepeating(arr));
	}

	/**
	 * Time complexity: O(n).
	 * @param arr
	 * @return
	 */
	public static int firstNonRepeating(int[] arr) {
		
		Set<Integer> nums = new HashSet<>();
		
		for (int i = 0; i < arr.length; i++) {
			if (nums.contains(arr[i])) {
				nums.remove(arr[i]);
			} else {
				nums.add(arr[i]);
			}
		}
		
		if (!nums.isEmpty()) {
			for (Integer i : nums) {
				return i;
			}
		} 
		
		return -1;
	}
}