package SortingAlgorithm;

public class BubbleSort {
	
	void sort(int arr[]) {
		int n = arr.length;
		boolean swapped = false;
		
		for (int i = 0; i < n - 1; i++) {
			
			swapped = false;
			
			//for (int j = 0; j < n - 1; j++) { // option 1 = more iterations
			for (int j = 0; j < n - i - 1; j++) { // option 2 = less iterations
				if (arr[j] > arr[j + 1]) {
					// swap arr[j+1] and arr[i]
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					
					swapped = true; // with this boolean we can have even less iterations
				}
				
				printArray(arr);
			}
			
			if (!swapped) {
				break;
			}
		}
	}

	void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	public static void main(String args[]) {
		BubbleSort ob = new BubbleSort();
		int arr[] = { 64, 34, 25, 12, 22, 11, 1 };
		System.out.println("Bubble Sort:");
		ob.printArray(arr);
		ob.sort(arr);
	}
}