package General.DynamicProgramming.Partition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * The problem is to find the minimum path sum from top to bottom if given a triangle.
 * Each step you may move to adjacent numbers on the row below.
 * 
 * Input:
 *  [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 *  ]
 * Output: 11
 * Explanation: The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11.
 * 
 * Solution
 * 
 * This is a classic problem for top-down dynamic programming or DFS + memoization. 
 * The key is to draw the state-space tree.
 * Then we traverse the tree depth-first and find the sum when we reach a leaf node.
 * Time Complexity: O(r*c)
 * Technically the size of the tree which is O(r*c/2) if you recall area of a triangle but we factor out constants and discard it.
 */
public class MinimumPathInATriangle {

	public static int minimumTotal(List<List<Integer>> triangle) {
        int[] total = new int[triangle.size()];
        int l = triangle.size() - 1;

        for (int i = 0; i < triangle.get(l).size(); i++) {
            total[i] = triangle.get(l).get(i);
        }

        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                total[j] = triangle.get(i).get(j) + (int) Math.min(total[j], total[j + 1]);
            }
        }

        return total[0];
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }
    
    // 4
    // 2
    // 3 4
    // 6 5 7
    // 4 1 8 3
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int triangleLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> triangle = new ArrayList<>();
        
        for (int i = 0; i < triangleLength; i++) {
            triangle.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        
        scanner.close();
        int res = minimumTotal(triangle);
        System.out.println(res);
    }
}