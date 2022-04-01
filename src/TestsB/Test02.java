package TestsB;

import java.util.Arrays;
import java.util.Random;

public class Test02 {

	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12};
		System.out.println(Arrays.toString(shuffle(arr)));
	}
	
	private static int[] shuffle(int[] arr) {
		int n = arr.length;
		Random r = new Random();
		
		for (int i = n - 1; i > 0; i--) {
			int j = r.nextInt(i + 1);
			
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
		
		return arr;
	}
}