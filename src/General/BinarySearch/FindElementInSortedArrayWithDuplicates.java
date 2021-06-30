package General.BinarySearch;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a sorted array of integers and a target integer, find the first occurrence of the target and return its index.
 * Return -1 if the target is not in the array.
 * Input:
 *   arr = [1, 3, 3, 3, 3, 6, 10, 10, 10, 100]
 *   target = 3
 * Output:
 *   1
 * Explanation: First occurrence of 3 is at index 1.
 */
public class FindElementInSortedArrayWithDuplicates {
	public static int findFirstOccurrence(List<Integer> arr, int target) {
        
		int L = 0;
		int M = -1;
		int R = arr.size() - 1;
		int first = -1;
		
		while (L <= R) {
			M = (L + R) / 2;
			
			System.out.println("L="+L+" M="+M+" R="+R);
			
			if (arr.get(M) == target) {
				first = M;
				R = M - 1;
			} else if (arr.get(M) > target) {
				R = M - 1;
			} else {
				L = M + 1;
			}
		}
		
        return first;
    }

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList("1 3 3 3 3 6 10 10 10 100".split(" ")).stream().map(Integer::parseInt).collect(Collectors.toList());
        int target = 3;
        int res = findFirstOccurrence(arr, target);
        System.out.println(res);
    }
}