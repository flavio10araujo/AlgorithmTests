package SortingAlgorithm;

public class MergeSort {
	
	// Merges two subarrays of arr[].
	// First subarray is arr[l..m]
	// Second subarray is arr[m + 1..r]
	void merge(int arr[], int l, int m, int r) {
		
		System.out.println("MERGE : L="+l+" M="+m+" R="+r);
		
		// Find sizes of two subarrays to be merged.
		int n1 = (m - l) + 1;
		int n2 = r - m;

		// Create temp arrays.
		int L[] = new int[n1];
		int R[] = new int[n2];

		// Copy data to temp arrays.
		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		// Merge the temp arrays.

		// Initial indexes of first and second sub-arrays.
		int i = 0, j = 0;

		// Initial index of merged sub-array.
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		// Copy remaining elements of L[] if any.
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		// Copy remaining elements of R[] if any.
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	// Main function that sorts arr[l..r].
	void sort(int arr[], int l, int r) {
		
		System.out.println("L="+l+" R="+r);
		printArray(arr);
		
		if (l < r) {
			// Find the middle point.
			int m = (l + r) / 2;

			// Sort first and second halves.
			sort(arr, l, m);
			sort(arr, m + 1, r);

			// Merge the sorted halves.
			merge(arr, l, m, r);
		}
	}

	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	public static void main(String args[]) {
		MergeSort ob = new MergeSort();
		int arr[] = { 64, 34, 25, 12, 22, 11, 1 };
		System.out.println("Merge Sort:");
		ob.sort(arr, 0, arr.length - 1);
		printArray(arr);
	}
}