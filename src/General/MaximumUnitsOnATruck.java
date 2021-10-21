package General;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

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
		//int[][] boxTypes = { {2, 2}, {1, 3}, {3, 1} };
		//int truckSize = 4;
		
		//int[][] boxTypes = { {5,10},{2,5},{4,7},{3,9} };
		//int truckSize = 10;
		
		int[][] boxTypes = { {1,3},{5,5},{2,5},{4,2},{4,1},{3,1},{2,2},{1,3},{2,5},{3,2} };
		int truckSize = 35; // return 76
		
		System.out.println(solution01(boxTypes, truckSize));
		System.out.println(solution02(boxTypes, truckSize));
	}
	
	/**
	 * O(n log n).
	 * @param boxTypes
	 * @param truckSize
	 * @return
	 */
	public static int solution01(int[][] boxTypes, int truckSize) {
		
		// First thing is to order the list by unit. Biggest units first.
		Arrays.sort(boxTypes, (a, b) -> -Integer.compare(a[1], b[1]));
		
		int maximumUnits = 0;
		
		for (int i = 0; i < boxTypes.length; i++) {
			int numBoxes = boxTypes[i][0];
			
			if (numBoxes > truckSize) {
				numBoxes = truckSize;
			}
			
			maximumUnits = maximumUnits + (numBoxes * boxTypes[i][1]);
			
			truckSize = truckSize - numBoxes;
			
			if (truckSize == 0) {
				break;
			}
		}
		
		return maximumUnits;
	}
	
	/**
	 * O(log n).
	 * @param boxTypes
	 * @param truckSize
	 * @return
	 */
	public static int solution02(int[][] boxTypes, int truckSize) {
		Queue<Element> heap = new PriorityQueue<>(new Comparator<Element>() {
            public int compare(Element o1, Element o2) {
                return o2.getNumberOfUnitsPerBox() - o1.getNumberOfUnitsPerBox();
            }
        });
        
        for (int i = 0; i < boxTypes.length; i++) {
            heap.offer(new Element(boxTypes[i][0], boxTypes[i][1]));
        }
        
        int max = 0;
        
        while(truckSize > 0 && !heap.isEmpty()) {
            Element e = heap.poll();
            
            int numberOfBoxes = e.getNumberOfBoxes();
            
            if (numberOfBoxes > truckSize) {
                numberOfBoxes = truckSize;
            }
            
            max = max + (numberOfBoxes * e.getNumberOfUnitsPerBox());
            
            truckSize = truckSize - numberOfBoxes;
        }
        
        return max;
	}
	
	static class Element {
	    int numberOfBoxes;
	    int numberOfUnitsPerBox;
	    
	    public Element(int numberOfBoxes, int numberOfUnitsPerBox) {
	        this.numberOfBoxes = numberOfBoxes;
	        this.numberOfUnitsPerBox = numberOfUnitsPerBox;
	    }
	    
	    public int getNumberOfBoxes() {
	        return this.numberOfBoxes;
	    }
	    
	    public int getNumberOfUnitsPerBox() {
	        return this.numberOfUnitsPerBox;
	    }
	}
}