package SortingAlgorithm;

import java.util.Arrays;

public class SortingWithJava {

	public static void main(String[] args) {
		int[] array = { 45, 12, 85, 32, 89, 39, 69, 44, 42, 1, 6, 8 };
		
		printArray(array);
		
		Arrays.sort(array);
		
		printArray(array);
	}
	
	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
}