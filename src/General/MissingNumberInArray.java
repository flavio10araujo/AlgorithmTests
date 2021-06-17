package General;

import java.util.BitSet;

import javax.swing.plaf.synth.SynthSeparatorUI;

/**
 * 
 * Program to find missing elements in an Integer array containing numbers from 1 to 100.
 * Ex.: array = {1, 2, 3, 4, 6}; qtd = 6 (it represents the numbers of items that should be in the array).
 * Answer: 5, because it is the number that is missing in the array.
 *
 */
public class MissingNumberInArray {
	public static void main(String args[]) {

		// First approach (it works only for one missing number).
		// Only one missing number in the array.
		getMissingNumber(new int[]{1, 4, 3, 2, 6}, 6);

		// Second approach (it works for no matter the quantity of missing numbers).
		// One missing number.
		//printMissingNumbers(new int[]{1, 2, 3, 4, 6}, 6);

		// Two missing numbers.
		// As you can see, the order of the values inside the array doesn't matter.
		//printMissingNumbers(new int[]{9, 2, 3, 7, 6, 4, 1, 8, 10}, 10);

		// Three missing numbers.
		printMissingNumbers(new int[]{2, 1, 9, 4, 6, 3, 8}, 10);
		
		// Third approach.
		// The same than the second approach, but using an BitSet.
		// The BitSet uses less memory than an array of booleans.
		printMissingNumbersBitSet(new int[]{2, 1, 9, 4, 6, 3, 8}, 10);
	}

	/**
	 * First approach - it works only for one missing number.
	 * 
	 * @param a
	 * @param qtd
	 */
	private static void getMissingNumber(int[] a, int qtd) {
		System.out.println("*** First approach ***");
		
		int totalCountExpected = qtd, totalCountArray = 0;

		if (totalCountExpected % 2 == 0) {
			totalCountExpected = (totalCountExpected * ((totalCountExpected + 1) / 2)) + (totalCountExpected / 2);
		} else {
			totalCountExpected = totalCountExpected * ((totalCountExpected + 1) / 2);
		}

		for (int i = 0; i < a.length; i++) {
			totalCountArray = totalCountArray + a[i];
		}

		System.out.println(totalCountExpected - totalCountArray);
	}

	/**
	 * Second approach - it works for no matter the quantity of missing numbers.
	 * 
	 * A general method to find missing values from an integer array in Java.
	 * This method will work even if array has more than one missing element.
	 */
	private static void printMissingNumbers(int[] numbers, int count) {
		System.out.println("*** Second approach - using boolean[] ***");
		
		boolean[] values = new boolean[count];
		
		for (int number : numbers) {
			values[number - 1] = true;
		}
		
		for (int i = 0; i < count; i++) {
			if (!values[i]) {
				System.out.println(i + 1);
			}
		}
	}
	
	/**
	 * Third approach - it works for no matter the quantity of missing numbers.
	 * This is the best approach because it uses a BitSet, which uses less memory than an array of booleans.
	 * 
	 */
	private static void printMissingNumbersBitSet(int[] numbers, int count) {
		System.out.println("*** Third approach - using BitSet ***");
		
		int missingCount = count - numbers.length; // The number of missing values.
		BitSet bitSet = new BitSet(count);
		
		for (int number : numbers) {
			bitSet.set(number - 1);
		}
		
		int lastMissingIndex = 0;

		for (int i = 0; i < missingCount; i++) {
			lastMissingIndex = bitSet.nextClearBit(lastMissingIndex);
			System.out.println(++lastMissingIndex);
		}
	}
}