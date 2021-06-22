package General;

import java.util.Arrays;
import java.util.Collections;

/**
 * How to reverse an array.
 *
 */
public class ReverseArray {

	public static void main(String[] args) {
		int[] a = {101, 102, 103, 104, 105};
		ReverseArray.solution01(a);
		ReverseArray.solution02(a);
		ReverseArray.solution03(a);
	}

	/**
	 * Using a second array to receive the elements in reverse order.
	 */
	public static void solution01(int[] a) {
		System.out.println("### Solution 01 ###");
		
		System.out.println(Arrays.toString(a));
		
		int[] aReverse = new int[a.length];
		int count = 0;
		
		for (int i = (a.length - 1); i >= 0; i--) {
			aReverse[count] = a[i];
			count++;
		}
		
		a = aReverse;
		
		System.out.println(Arrays.toString(a));
	}
	
	/**
	 * Transforming the int[] into Integer[] and using Collections.reverse.
	 */
	public static void solution02(int[] a) {
		System.out.println("### Solution 02 ###");
		
		System.out.println(Arrays.toString(a));
		
		Integer[] aI = Arrays.stream(a).boxed().toArray(Integer[]::new);
		Collections.reverse(Arrays.asList(aI));
		
		for(int i = 0; i < a.length; i++) {
			a[i] = aI[i];
		}
		
		System.out.println(Arrays.toString(a));
	}
	
	/**
	 * In-place reversal of array.
	 * Time complexity: O(n / 2).
	 */
	public static void solution03(int[] a) {
		System.out.println("### Solution 03 ###");
		
		System.out.println(Arrays.toString(a));
		
		int middle = (a.length) / 2;
		int aux = 0;
		
		for (int i = 0; i < middle; i++) {
			aux = a[i];
			a[i] = a[(a.length - 1) - i];
			a[(a.length - 1) - i] = aux;
		}
		
		System.out.println(Arrays.toString(a));
	}
}