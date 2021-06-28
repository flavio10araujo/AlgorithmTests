package Tests;

import java.util.Arrays;

/**
 * Eight houses, represented as cells, are arranged as a straight line.
 * Each day every cell competes with adjacent cells.
 * An integer value 1 represents an active cell and a value of 0 represents an inactive cell.
 * if the neighbors on both sides of the cell are both active or inactive, the cell becomes inactive the next day, otherwise the cell becomes active.
 * The two cells on each end have a single adjacent cell, so assume that the unoccupied space on the opposite side is an inactive cell.
 * Even after updating the cell state, consider its previous state when updating the state of other cells.
 * The state information of all cells should be updated simultaneously.
 * 
 * Write an algorithm to output the state of the cells after the given number of days.
 * 
 * Input: the input to the function/method consists of two arguments:
 *   states, a list of integers representing the current state of cells;
 *   days, an integer representing the number of days.
 * 
 * Output: return a list of integers representing the state of the cells after the given number of days.
 * 
 * Note: the elements of the list states contains 0s and 1s only.
 */
public class AmazonCellCompete {

	public static void main(String[] args) {
		int days = 1;
		int[] states = new int[8];
		
		states[0] = 1;
		states[1] = 0;
		states[2] = 1;
		states[3] = 0;
		states[4] = 1;
		states[5] = 1;
		states[6] = 1;
		states[7] = 0;
		
		int[] finalStates = solution01(states, days);
		System.out.println(Arrays.toString(finalStates) + " is the array after " + days + " days.");
	}

	/**
	 * Time complexity: O(n + (2 * (k * n)) = O(n + 2kn) = O(n + kn) = O(n).
	 * @param states
	 * @param days
	 * @return
	 */
	public static int[] solution01(int[] states, int days) {
		System.out.println("### Solution 01 ###");
		System.out.println(Arrays.toString(states) + " is the original array.");
		
		int left, right;
		int[] bkp = new int[states.length];
		
		for (int i = 0; i < bkp.length; i++) {
			bkp[i] = states[i];
		}
		
		for (int d = 0; d < days; d++) {
			for (int i = 0; i < states.length; i++) {
				if (i == 0) {
					left = 0;
					right = bkp[1];
				} else if (i == bkp.length - 1) { 
					left = bkp[bkp.length - 2];
					right = 0;
				} else {
					left = bkp[i - 1];
					right = bkp[i + 1];
				}
				
				if (left == right) {
					states[i] = 0;
				} else {
					states[i] = 1;
				}
			}
			
			for (int i = 0; i < bkp.length; i++) {
				bkp[i] = states[i];
			}
		}
		
		return states;
	}

}