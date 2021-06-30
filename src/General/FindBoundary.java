package General;

import java.util.Scanner;

/**
 * An array of boolean values is divided into two sections; 
 * the left section consists of all false 
 * and the right section consists of all true. 
 * Find the boundary of the right section, 
 * i.e. the index of the first true element. 
 * If there is no true element, return -1.
 * 
 * Input: arr = [false, false, true, true, true]
 * Output: 2
 * Explanation: first true's index is 2.
 *
 * Binary Search.
 */
public class FindBoundary {

	public static void main(String[] args) {
		/*Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        scanner.close();
        boolean[] arr = new boolean[input.length];
        
        for (int i = 0; i < input.length; i++) {
            arr[i] = input[i].equals("true");
        }
        
        System.out.println(solution01(arr));*/
		
		System.out.println(solution01(new boolean[]{true, true, true, true, true})); // 0
        System.out.println(solution01(new boolean[]{false, true, true, true, true})); // 1
        System.out.println(solution01(new boolean[]{false, false, true, true, true})); // 2
        System.out.println(solution01(new boolean[]{false, false, false, true, true})); // 3
        System.out.println(solution01(new boolean[]{false, false, false, false, true})); // 4
        System.out.println(solution01(new boolean[]{false, false, false, false, false})); // -1
        
        System.out.println(solution02(new boolean[]{true, true, true, true, true})); // 0
        System.out.println(solution02(new boolean[]{false, true, true, true, true})); // 1
        System.out.println(solution02(new boolean[]{false, false, true, true, true})); // 2
        System.out.println(solution02(new boolean[]{false, false, false, true, true})); // 3
        System.out.println(solution02(new boolean[]{false, false, false, false, true})); // 4
        System.out.println(solution02(new boolean[]{false, false, false, false, false})); // -1
	}
	
	public static int solution01(boolean[] arr) {
		int L = 0;
		int R = arr.length - 1;
		int M = (L + R) / 2;
		
		return binarySearch(arr, L, M, R);
    }
	
	/**
	 * Binary Search with recursion.
	 * 
	 * @param arr
	 * @param L
	 * @param M
	 * @param R
	 * @return
	 */
	public static int binarySearch(boolean[] arr, int L, int M, int R) {
		
		//System.out.println("L="+L+" M="+M+" R="+R);
		
		if (arr[M]) {
			if (M == 0) {
				return 0;
			} else if (!arr[M - 1]) {
				return M;
			}
			
			R = M;
			M = (L + R) / 2;
			
			return binarySearch(arr, L, M, R);
		} else {
			if (M == arr.length - 1) {
				return -1;
			} else if (arr[M + 1]) {
				return M + 1;
			} else if (!arr[M + 1] && (M + 1 == arr.length - 1)) {
				return -1;
			}
			
			L = M;
			M = (L + R) / 2;
			
			return binarySearch(arr, L, M, R);
		}
	}
	
	/**
	 * Binary Search without recursion.
	 * 
	 * @param arr
	 * @return
	 */
	public static int solution02(boolean[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int boundaryIndex = -1;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (arr[mid]) {
                boundaryIndex = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return boundaryIndex;
    }
}