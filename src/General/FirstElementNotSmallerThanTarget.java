package General;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of integers sorted in increasing order and a target, 
 * find the index of the first element in the array that is larger than or equal to the target. 
 * Assume that it is guaranteed to find a satisfying number.
 * Input: 
 *   arr = [1, 3, 3, 5, 8, 8, 10]
 *   target = 2
 * Output:
 *   1
 * Explanation: the first element larger than 2 is 3 which has index 1.
 * 
 * Binary Search.
 */
public class FirstElementNotSmallerThanTarget {
	public static int firstNotSmaller(List<Integer> arr, int target) {
        
		int L = 0;
		int M = -1;
		int R = arr.size() - 1;
		int first = -1;
		
		while(L <= R) {
			M = (L + R) / 2;
			
			System.out.println("L="+L+" M="+M+" R="+R);
			
			if (arr.get(M) >= target) {
				first = M;
				R = M - 1;
			} else {
				L = M + 1;
			}
		}
		
        return first;
    }

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<Integer>();
        
        arr.add(1);
        arr.add(3);
        arr.add(3);
        arr.add(5);
        arr.add(8);
        arr.add(8);
        arr.add(10);
        
        int target = 11;
        int res = firstNotSmaller(arr, target);
        System.out.println(res);
    }
}