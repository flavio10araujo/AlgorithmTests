package Tests;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-units-on-a-truck/
 * 
 * You are assigned to put some amount of boxes onto one truck. 
 * 
 * You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
 * - numberOfBoxesi is the number of boxes of type i.
 * - numberOfUnitsPerBoxi is the number of units in each box of the type i.
 * 
 * You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck. 
 * You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.
 * 
 * Return the maximum total number of units that can be put on the truck.
 * 
 * Example 1:
 * Input: boxTypes = { {2, 2}, {1, 3}, {3, 1} }, truckSize = 4
 * Output: 8
 * Explanation: There are:
 * 	- 1 box of the first type that contains 3 units.
 * 	- 2 boxes of the second type that contain 2 units each.
 *  - 1 box of the third type that contain 1 unit each.
 *  You can take all the boxes of the first and second types, and one box of the third type.
 *  The total number of units will be = (1 * 3) + (2 * 2) + (1 * 1) = 8.
 *  
 *  Example 2:
 *  Input: boxTypes = {{5,10},{2,5},{4,7},{3,9}}, truckSize = 10
 *  Output: 91
 *  
 *  Constraints:
 *  1 <= boxTypes.length <= 1000
 *  1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
 *  1 <= truckSize <= 106
 *  
 *  Solution:
 *  
 *  Greedy Algorithm.
 *  The problem is fairly straightforward. 
 *  We simply sort the boxes by the number of units a box can contain and fill the truck until it's full.
 */
public class MaximumUnitsOnATruck {

	public static void main(String[] args) {
		int[][] boxTypes = { {2, 2}, {1, 3}, {3, 1} };
		int truckSize = 4;
		
		System.out.println(getMaximumUnitsOnATruck(boxTypes, truckSize));
	}
	
	public static int getMaximumUnitsOnATruck(int[][] boxTypes, int truckSize) {
		
		/*for(int i = 0; i < boxTypes.length; i++) {
			for(int j = 0; j < 2; j++) {
				System.out.print(boxTypes[i][j]);
			}
			System.out.println("");
		}*/
		
		// First thing is to order the list by unit. Biggest units first.
		Arrays.sort(boxTypes, (a, b) -> -Integer.compare(a[1], b[1]));
		
		/*for(int i = 0; i < boxTypes.length; i++) {
			for(int j = 0; j < 2; j++) {
				System.out.print(boxTypes[i][j]);
			}
			System.out.println("");
		}*/
		
		int maximumUnitsOnATruck = 0;
		int countBoxes = 0;
		
		label : {
			for (int i = 0; i < boxTypes.length; i++) {
				for (int j = 1; j <= boxTypes[i][0]; j++) {
					
					maximumUnitsOnATruck += boxTypes[i][1];
					
					countBoxes++;
					
					if (countBoxes >= truckSize) {
						break label;
					}
				}
			}
		}
		
		return maximumUnitsOnATruck;
	}
}