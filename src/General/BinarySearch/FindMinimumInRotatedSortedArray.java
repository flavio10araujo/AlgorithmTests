package General.BinarySearch;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A sorted array was rotated at an unknown pivot. 
 * For example, [10, 20, 30, 40, 50] becomes [30, 40, 50, 10, 20]. 
 * Find the index of the minimum element in this array.
 * 
 * Input: [30, 40, 50, 10, 20]
 * Output: 3
 * Explanation: the smallest element is 10 and its index is 3.
 * 
 * Binary Search.
 */
public class FindMinimumInRotatedSortedArray {

	public static int findMinRotated(List<Integer> arr) {
		int left = 0;
        int right = arr.size() - 1;
        int boundaryIndex = -1;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            System.out.println("L="+left+" M="+mid+" R="+right);
            
            // if <= last element, then belongs to lower half
            if (arr.get(mid) <= arr.get(arr.size() - 1)) {
                boundaryIndex = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return boundaryIndex;
    }
	
	public static void main(String[] args) {
		List<Integer> arr = Arrays.asList("30 40 50 10 20 21 22".split(" ")).stream().map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(findMinRotated(arr));
	}
}