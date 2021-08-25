package General.DivideAndConquer.Generalized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance.
 * Now suppose you are given the locations and heights of all the buildings, 
 * write a program to output the skyline formed by these buildings collectively.
 * 
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], 
 * where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. 
 * 
 * It is guaranteed that 0 <= Li, Ri <= INT_MAX, 0 < Hi <= INT_MAX, and Ri - Li > 0.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 * 
 * The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline.
 * 
 * A key point is the left endpoint of a horizontal line segment.
 * Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. 
 * Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
 * 
 * Example:
 * Input: [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]]
 * Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 * 
 * Solution:
 * 
 * Explanation
 * 
 * Intuition
 * 
 * This is a difficult problem at first glance. 
 * But we can use the divide and conquer strategy to make it a lot easier.
 * We can first see the problem of converting the entire set of building given into a skyline as the big problem. 
 * Now that we have that as the baseline, we can apply the divide and conquer strategy and separate the big problem into two smaller problems. 
 * Naturally, a very intuitive way to do this is to separate the data set into two halves, the first half contain the buildings from the beginning to the middle and the second the middle to the end.
 * Now that we have two smaller problems, we can apply the next step -- assume that each of the smaller problems have already been solved and do a merge. 
 * We can see that now the problem have turned into a much simpler problem -- merging two skylines into one skyline.
 * Now that the problem has changed into merging two skylines, how do we solve it? 
 * Well, this problem is very similar to merging two sorted arrays. 
 * It has some twists of course but the general idea is the same.
 * First, we need a pointer for each of the skyline provided to us. 
 * This is to keep track how much of each skyline we have already merged. 
 * We also need x and y coordinates for each of the skyline to know which skyline to advance.
 * After initialization, we can enter a loop to merge the skylines. 
 * Each iteration, we advance the pointer of the skyline that have the smaller x coordinates (meaning merging from left to right). 
 * Then we will need to update our new skyline to reflect the merges.
 * When do we add the current skyline and when do we just change the skyline's last y coordinate? 
 * We notice that we only need to add a new x and y coordinate to our skyline when both the x and y coordinates have changed. 
 * When x coordinate changes and y coordinate doesn't change -> we do nothing. 
 * When y coordinate change and x coordinate doesn't change -> we simply update skyline's last y coordinate (the case when two buildings of the different height begin at the same location). 
 * Note that this part of the logic is reflected in the update function.
 * After the loop terminates, one skyline must have been completely merged while the other might not have been. 
 * At this point, we simply need to iterate through the remaining skyline and merge it.
 * As we see above, the key of the problem is recognizing that the problem can be solved using divide and conquer and then simplifying the problem into a merging skyline problem. 
 * After that point, the problem became trivial.
 * Time Complexity: O(nlog(n))
 */
public class SkylineProblem {

	public static List<List<Integer>> result = new ArrayList<List<Integer>>();
	
    public static void update(int x, int y) {
        if (result.isEmpty() || (result.get(result.size() - 1).get(0) != x && result.get(result.size() - 1).get(1) != y)) {
            result.add(Arrays.asList(x, y));
        } else {
            result.get(result.size() - 1).set(1, y);
        }
    }
    
    public static List<List<Integer>> merge(List<List<Integer>> left, List<List<Integer>> right) {
        result.clear();
        
        int curx = 0;
        int cury = 0;
        int i1 = 0;
        int i2 = 0;
        int ly = 0;
        int ry = 0;
        
        while (i1 < left.size() && i2 < right.size()) {
            int x1 = left.get(i1).get(0);
            int y1 = left.get(i1).get(1);
            int x2 = right.get(i2).get(0);
            int y2 = right.get(i2).get(1);
            
            if (x1 < x2) {
                i1++;
                ly = y1;
            } else {
                i2++;
                ry = y2;
            }
            
            curx = Math.min(x1, x2);
            cury = Math.max(ly, ry);
            
            update(curx, cury);
        }
        
        while (i1 < left.size()) {
            int x1 = left.get(i1).get(0);
            int y1 = left.get(i1).get(1);
            i1++;
            update(x1, y1);
        }
        
        while (i2 < right.size()) {
            int x2 = right.get(i2).get(0);
            int y2 = right.get(i2).get(1);
            i2++;
            update(x2, y2);
        }
        
        List<List<Integer>> temp = new ArrayList<List<Integer>>(result);
        
        return temp;
    }
    
    public static List<List<Integer>> getSkyline(List<List<Integer>> buildings) {
        int l = buildings.size();
        
        if (l == 0) {
        	return Arrays.asList();
        }
        
        if (l == 1) {
        	return Arrays.asList(Arrays.asList(buildings.get(0).get(0), buildings.get(0).get(2)), Arrays.asList(buildings.get(0).get(1), 0));
        }
        
        List<List<Integer>> leftSplit = new ArrayList<List<Integer>>();
        List<List<Integer>> rightSplit = new ArrayList<List<Integer>>();
        
        for (int i = 0; i < l; i++) {
            if (i < l / 2) {
            	leftSplit.add(buildings.get(i));
            } else {
            	rightSplit.add(buildings.get(i));
            }
        }
        
        List<List<Integer>> leftBuildings = getSkyline(leftSplit);
        List<List<Integer>> rightBuildings = getSkyline(rightSplit);
        
        return merge(leftBuildings, rightBuildings);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 5
    // 2 9 10
    // 3 7 15
    // 5 12 12
    // 15 20 10
    // 19 24 8
    // Output: [[2, 10], [3, 15], [7, 12], [12, 0], [15, 10], [20, 8], [24, 0]]
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int buildingsLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> buildings = new ArrayList<>();
        
        for (int i = 0; i < buildingsLength; i++) {
            buildings.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        
        scanner.close();
        List<List<Integer>> res = getSkyline(buildings);
        
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}