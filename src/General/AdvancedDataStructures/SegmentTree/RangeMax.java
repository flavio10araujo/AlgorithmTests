package General.AdvancedDataStructures.SegmentTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * For this question we will give you an array and a series of queries and updates. 
 * Each update can change 1 particular value in the array and each query will give you 
 * an interval where you have to return the maximum value on the interval. 
 * Each query or update will be a list of 3 elements. 
 * The first element is a number denoting a query or update operation, 1 will denote a query and 2 an update operation. 
 * If the number is a 1 the next 2 numbers will denote the interval that is to be queried in the 0-indexed array. 
 * If the number is a 2 the next 2 numbers will denote the index i and value v in that order which means that index i in the array should be updated to v.
 * 
 * Input
 * arr: original array of numbers
 * operations: list of queries and updates on the array
 * 
 * Output: list containing the answer to all the queries
 * 
 * Example 1:
 * Input:
 * arr = [1,2,3,4,5]
 * operations = [[1,0,4], [2,4,7], [1,1,4]]
 * Output: [5,7]
 * Explanation: We are given a 1 query operation which means we first query from interval 0 - 4 the largest number which is 5. 
 * We then are given a 2 update operation which means we update the array at index 4 to the value of 7. 
 * Therefore, our new array is [1,2,3,4,7]. 
 * Lastly, we query one more time between 1 and 4 where we get a largest value of 7.
 * 
 * Constraints
 * 1 <= arr.length <= 10000
 * 1 <= operations.length <= 10000
 * Each value of arr will be in the range [1, 30000]
 * 
 * Solution
 * 
 * We simply use the Segment Tree data structure that we have learned. 
 * We need only modify the addition to a built-in maximum function.
 * Time Complexity: O(operations.length * log(arr.length))
 * 
 */
public class RangeMax {

	public static class SegmentTree {
        int [] tree;
        
        // Make sure to modify this part to account for a list instead of an array as input.
        public SegmentTree(List<Integer> arr) {
            tree = new int [4 * arr.size()];
            
            for (int i = 0; i < arr.size(); i++) {
                update(1, 0, arr.size() - 1, i, arr.get(i));
            }
        }
        
        void update(int cur, int curLeft, int curRight, int idx, int val) {
            // Make sure we reach leaf node when the left interval equals right interval and return the value located in the tree.
            if (curLeft == curRight && curLeft == idx) {
                tree[cur] = val;
            } else {
                // Compute value of the midpoint where we cut the segment in half.
                int curMid = (curLeft + curRight) / 2;
                
                // Remember n * 2 is left child node and n * 2 + 1 is the right child node.
                if (idx <= curMid) {
                	update(cur * 2, curLeft, curMid, idx, val);
                } else {
                	update(cur * 2 + 1, curMid + 1, curRight, idx, val);
                }
                
                // After updating the values, compute the new value for the node.
                tree[cur] = Math.max(tree[cur * 2], tree[cur * 2 + 1]);
            }
        }
        
        int query(int cur, int curLeft, int curRight, int queryLeft, int queryRight) {
            // If our current left interval is greater than the queried right interval it means we are out of range.
            // Similarly, if the current right interval is less than the queried left interval we are out of range and in both cases return 0.
            if (curLeft > queryRight || curRight < queryLeft) {
                return 0;
            }
            // Check if we are in range, if we are return the current interval.
            else if (queryLeft <= curLeft && curRight <= queryRight) {
                return tree[cur];
            }
            
            // This means part of our interval is in range but part of our interval is not in range, we must therefore query both children.
            int curMid = (curLeft + curRight) / 2;
            
            return Math.max(query(cur * 2, curLeft, curMid, queryLeft, queryRight), query(cur * 2 + 1, curMid + 1, curRight, queryLeft, queryRight));
        }
    }
    public static List<Integer> rangeMax(List<Integer> arr, List<List<Integer>> operations) {
        SegmentTree tree = new SegmentTree(arr);
        List<Integer> ans = new ArrayList<Integer>();
        
        for (int i = 0; i < operations.size(); i++) {
            List<Integer> op = operations.get(i);
            
            if (op.get(0) == 1) {
            	ans.add(tree.query(1, 0, arr.size() - 1, op.get(1), op.get(2)));
            } else {
            	tree.update(1, 0, arr.size() - 1, op.get(1), op.get(2));
            }
        }
        
        return ans;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 1 2 3 4 5
    // 3
    // 1 0 4
    // 2 4 7
    // 1 1 4
    // Output: 5 7
    
    // 1 1 1 1 1 1 1 1 1 1 1 1
    // 6
    // 1 0 4
    // 2 0 2
    // 1 0 0
    // 1 0 7
    // 2 0 1
    // 1 0 0
    // Output: 1 2 2 1
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> arr = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        int operationsLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> operations = new ArrayList<>();
        
        for (int i = 0; i < operationsLength; i++) {
            operations.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        
        scanner.close();
        List<Integer> res = rangeMax(arr, operations);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}