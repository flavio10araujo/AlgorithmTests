package General.TwoPointers.OppositeDirection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an array of integers sorted in ascending order, find two numbers that add up to a given target.
 * Return the indices of the two numbers in ascending order.
 * You can assume elements in the array are unique and there is only one solution.
 * Do this in O(n) time and with constant auxiliary space.
 * 
 * Input: [2 3 5 8 11 15], 5
 * Output: 0 1
 */
public class TwoSumSorted {
	public static List<Integer> twoSumSorted(List<Integer> arr, int target) {
        int L = 0;
		int R = findRightSide(arr, target);
        
		while (L <= R) {
			if (arr.get(L) + arr.get(R) == target) {
				return List.of(L, R);
			}
			
			if (arr.get(L) + arr.get(R) > target) {
				R--;
			} else {
				L++;
			}
		}
		
		return null;
    }
	
	/**
	 * O(log n)
	 * 
	 * @param arr
	 * @param target
	 * @return
	 */
	public static int findRightSide(List<Integer> arr, int target) {
		int L = 0, R = arr.size() - 1;
		int M = -1;
		
		while (L <= R) {
			M = (L + R) / 2;
			
			if (arr.get(M) == target) {
				return M;
			}
			
			if (arr.get(M) > target) {
				R = M - 1;
			} else {
				L = M + 1;
			}
		}
		
		return M;
	}

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        List<Integer> arr = splitWords("2 3 5 8 11 15").stream().map(Integer::parseInt).collect(Collectors.toList());
        int target = Integer.parseInt("5");
        List<Integer> res = twoSumSorted(arr, target);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}