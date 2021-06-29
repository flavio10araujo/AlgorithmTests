package SortingAlgorithm;

public class QuickSort {

	/*
	 * This function takes last element as pivot, 
	 * places the pivot element at its correct position in sorted array, 
	 * and places all smaller (smaller than pivot) to left of pivot 
	 * and all greater elements to right of pivot.
	 */
	int partition(int arr[], int low, int high) {
		int pivot = arr[high];
		int i = (low - 1); // Index of smaller element.
		
		for (int j = low; j < high; j++) {
			// If current element is smaller than the pivot.
			if (arr[j] < pivot) {
				i++;
				// Swap arr[i] and arr[j].
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		// Swap arr[i + 1] and arr[high] (or pivot).
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;

		return i + 1;
	}

	void sort(int arr[], int low, int high) {	
		if (low < high) {
			
			printArray(arr);
			
			// pi is partitioning index, arr[pi] is now at right place.
			int pi = partition(arr, low, high);

			// Recursively sort elements before partition and after partition.
			sort(arr, low, pi - 1);
			sort(arr, pi + 1, high);
		}
	}

	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	public static void main(String args[]) {
		QuickSort ob = new QuickSort();
		int arr[] = { 64, 34, 25, 12, 22, 11, 1 };
		System.out.println("Quick Sort:");
		ob.sort(arr, 0, arr.length - 1);
		printArray(arr);
	}
}