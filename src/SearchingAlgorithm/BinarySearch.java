package SearchingAlgorithm;

import java.util.Arrays;

/**
 * 
 * 
 *
 */
public class BinarySearch {

	public static void main(String[] args) {
		// The array must be ordered.
		int[] a = {1, 4, 12, 13, 18, 32, 41, 42, 43, 64, 68, 79, 84, 85, 87, 91, 120, 1234, 1235, 2016, 2017, 2018, 4991};
		int searchingFor = 91;
		
		BinarySearch.solution01(a, searchingFor);
		BinarySearch.solution02(a, searchingFor);
		BinarySearch.solution03(a, searchingFor);
	}
	
	/**
	 * Linear search (naive solution).
	 * Time complexity: O(n).
	 */
	public static void solution01(int[] a, int searchingFor) {
		System.out.println("### Solution 01 - linear search ###");
		
		int position = -1;
		int iterations = 0;
		
		for (int i = 0; i < a.length; i++) {
			iterations++;
			
			if (a[i] == searchingFor) {
				position = i;
				i = a.length;
			}
		}
		
		
		System.out.printf("Number of iterations: %d %n", iterations);
		
		if (position >= 0) {
			System.out.println("The element " + searchingFor + " is in the position " + position + " in the array " + Arrays.toString(a) + ".");
		} else {
			System.out.println("The element " + searchingFor + " is not in the array " + Arrays.toString(a) + ".");
		}
	}

	/**
	 * Binary search.
	 * Time complexity: O(log n).
	 * 
	 * Step 1: It breaks the array in the middle
	 * Step 2 : Verify if the searchingFor is bigger or smaller than the middle.
	 * Step 2.1 : If it is bigger: left = (left + right) / 2; right = right - 1;
	 * Step 2.2 : If is is smaller: right = left; left = left / 2;
	 * Repeat until find searchingFor or until left equals right.
	 */
	public static void solution02(int[] a, int searchingFor) {
		System.out.println("### Solution 02 - binary search ###");
		
		int left = (a.length) / 2;
		int right = a.length - 1;
		int position = -1;
		int iterations = 0;
		
		for (int i = 0; i < a.length; i++) {
			
			iterations++;
			//System.out.println("left = " + left + " right = " + right);
			
			if (a[left] == searchingFor) {
				position = left;
				i = a.length;
			} else if (a[right] == searchingFor) {
				position = right;
				i = a.length;
			}
			
			if (left == right) {
				i = a.length;
				continue;
			}
			
			if (searchingFor < a[left]) {
				right = left;
				left = left / 2;
			} else {
				left = (left + right) / 2;
				right = right - 1;
			}
		}
		
		System.out.printf("Number of iterations: %d %n", iterations);
		
		if (position >= 0) {
			System.out.println("The element " + searchingFor + " is in the position " + position + " in the array " + Arrays.toString(a) + ".");
		} else {
			System.out.println("The element " + searchingFor + " is not in the array " + Arrays.toString(a) + ".");
		}
	}
	
	/**
	 * Recursive binary search.
	 * Time complexity: O(log n).
	 */
	public static void solution03(int[] a, int searchingFor) {
		System.out.println("### Solution 03 - recursive binary search ###");
		
		int position = solution03BinarySearch(a, 0, a.length - 1, searchingFor);
        
        if (position >= 0) {
			System.out.println("The element " + searchingFor + " is in the position " + position + " in the array " + Arrays.toString(a) + ".");
		} else {
			System.out.println("The element " + searchingFor + " is not in the array " + Arrays.toString(a) + ".");
		}
	}
	
	/**
	 * Working with the solution03.
	 */
	public static int solution03BinarySearch(int a[], int left, int right, int searchingFor) {
        
		System.out.println("Iteration - inside the recursive function");
		
		if (right >= left) {
            int mid = left + (right - left) / 2;
  
            // If the element is present at the middle itself.
            if (a[mid] == searchingFor) {
                return mid;
            } else if (a[left] == searchingFor) {
            	return left;
            } else if (a[right] == searchingFor) {
            	return right;
            }
  
            // If element is smaller than mid, then it can only be present in left subarray.
            if (a[mid] > searchingFor) {
                return solution03BinarySearch(a, left, mid - 1, searchingFor);
            }
  
            // Else the element can only be present in right subarray.
            return solution03BinarySearch(a, mid + 1, right, searchingFor);
        }
  
        // We reach here when element is not present in array.
        return -1;
    }
}