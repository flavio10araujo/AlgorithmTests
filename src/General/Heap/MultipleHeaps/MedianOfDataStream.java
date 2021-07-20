package General.Heap.MultipleHeaps;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Given a stream of numbers, find the median number at any given time (accurate to 1 decimal place). 
 * Do this in O(1) time complexity.
 * 
 * Example:
 * add_number(1)
 * add_number(2)
 * add_number(3)
 * get_median() == 2.0
 * add_number(4)
 * get_median() == 2.5
 * 
 * Explanation
 * 
 * Intuition
 * Brute force way is to sort the entire list every time we get a new number. 
 * This would be O(Nlog(N)) for each add_number operation.
 * One step up is to notice that the list is sorted before we add any new number to it. 
 * So every time we add a new number to the existing list we just have to find the spot to add it.
 *  We can find the insert position using binary search in O(log(N)). 
 *  However, since inserting into a position also means shifting after the insert position by 1, the overall run time is O(N).
 *  Upon re-examining the conditions and unknowns of the problem, we noticed we only need to find the median and don't need the rest of the list to be sorted. 
 *  But how are we gonna find the median without having a sorted list?
 *  It's useful to use the first principle and go back to the definition of median:
 *  Half the numbers are smaller than the median, the other half are larger than the median.
 *  Let's assume the total number of elements is even, and we can divide the numbers into two piles of equal size based on their values, 
 *  a smaller half small pile, and the bigger half big pile. 
 *  The median of both piles is the average of the largest number in the small pile and the smallest number of the big pile. 
 *  When we add a new number, two things could happen:
 *  - The new number is smaller than the largest of the small pile. 
 *    In this case, we put it into the small pile, and the size of the small pile is now 1 greater than big pile. 
 *    The median of both piles is the largest number of the small pile.
 *  - The new number is bigger than the largest of the small pile. 
 *    In this case, the number belongs to the big pile. 
 *    And the median of both piles is the smallest number of the big pile.
 *  Now the problem boils down to how to keep a small pile where we can find max value easily and a big pile where we can find min value easily. 
 *  And min heap and max heap fits these requirements perfectly.
 *  
 *  Time Complexity: O(qlog(q))
 *  Here we let q denote the number of queries so worst case its log(q) insertion for a given number.
 */
class Solution {

	private Queue<Double> minHeap = new PriorityQueue<>();
	private Queue<Double> maxHeap = new PriorityQueue<>();

	public void addNum(int num) {
		// Push into max heap if.
		if (minHeap.size() == 0 || num < minHeap.peek()) {
			maxHeap.add((double) -num); // Note the - sign since it's max heap.
		} else {
			minHeap.add((double) num);
		}
		
		_balance();
	}

	public double getMedian() {
		if (maxHeap.size() == minHeap.size()) {
			return (-maxHeap.peek() + minHeap.peek()) / 2.0;
		} else {
			return -maxHeap.peek();
		}
	}

	private void _balance() {
		if (maxHeap.size() < minHeap.size()) {
			maxHeap.add(-minHeap.poll());
		}
		
		if (maxHeap.size() > minHeap.size() + 1) {
			minHeap.add(-maxHeap.poll());
		}
	}
}

class MedianOfDataStream {
    
	// 6
	// 1
	// 2
	// 3
	// get
	// 4
	// get
	public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int numInputs = Integer.parseInt(scanner.nextLine());
        Solution medianOfStream = new Solution();
        
        for (int i = 0; i < numInputs; ++i) {
            String input = scanner.nextLine();
            if (input.equals("get")) {
                System.out.println(medianOfStream.getMedian());
            } else {
                medianOfStream.addNum(Integer.parseInt(input));
            }
        }
        
        scanner.close();
    }
}