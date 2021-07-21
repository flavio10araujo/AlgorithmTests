package General.DynamicProgramming.Bitmask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Output the minimum cost to traverse every node in a directed weighted graph. 
 * 
 * The graph will be in the form of a 2D list where element [i, j] in the array denotes the weight of the directed edge from i to j. 
 * If the value is 0 then the edge doesn't exist. 
 * You do not have to end at the starting node. 
 * All edges are guaranteed to be in the range [0, 1000], there will not exceed 15 nodes in the graph. 
 * The starting node will always be at node 0. 
 * If a solution does not exist return -1.
 * 
 * Example:
 * Input: 
 * [
 * 	[0,0 = 0][0,1 = 100][0,2 = 100][0,3 = 1]
 *  [1,0 = 0][1,1 = 0  ][1,2 = 100][1,3 = 0]
 *  [2,0 = 0][2,1 = 1  ][2,2 = 0  ][2,3 = 0]
 *  [3,0 = 0][3,1 = 20 ][3,2 = 1  ][3,3 = 0]  
 * ]
 * Output: 3
 * Explanation: 0 -> 3 -> 2 -> 1.
 * 
 * Solution
 * 
 * Maintain a bitmask for the nodes that you visited. 
 * We can then perform dfs to check nodes we have not visited and record the minimum value and return that for our recursive function. 
 * For further reading, this problem is actually quite famous and known as the Travelling Salesman Problem.
 * 
 * Time Complexity: O(2^n)
 * 
 * Bitmask problems tend to reduce O(n!) time complexity or worse to O(2^n).
 */
public class MinimumCostToVisitEveryNodeInAGraph {

	public static int minCostToVisitEveryNode(List<List<Integer>> graph) {
        // dp array set it equal to 2 ^ (number of nodes).
        int[][] dp = new int[1 << graph.size()][graph.size()];
        
        // Call the function make sure to set node 0 as visited and start at node 0.
        int ans = f(1, 0, graph, dp);
        
        return ans == 0x3f3f3f3f ? -1 : ans;
    }
	
    // Recursive function to compute DP
    public static int f(int bitmask, int cur, List<List<Integer>> graph, int dp[][]) {
        // Check if we have visited every node
        if (bitmask == (1 << graph.size()) - 1) {
            return 0;
        }
        
        if (dp[bitmask][cur] != 0) {
            return dp[bitmask][cur];
        }
        
        // Set to arbitrary large value, edges are only 1000 and 15 nodes so total can never reach 0x3f3f3f3f.
        int ans = 0x3f3f3f3f;
        
        // Loop through all the neighbours for this particular node.
        for (int i = 0; i < graph.get(cur).size(); i++) {
            // Check if we have visited this node before.
            if ((bitmask & (1 << i)) == 0 && graph.get(cur).get(i) != 0) {
                // If we have not call the recursive function and see if we get a new minimum.
                ans = Math.min(ans, graph.get(cur).get(i) + f((bitmask | (1 << i)), i, graph, dp));
            }
        }
        
        return dp[bitmask][cur] = ans;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();
        
        graph.add(splitWords("0 100 100 1").stream().map(Integer::parseInt).collect(Collectors.toList()));
        graph.add(splitWords("0 0 100 0").stream().map(Integer::parseInt).collect(Collectors.toList()));
        graph.add(splitWords("0 1 0 0").stream().map(Integer::parseInt).collect(Collectors.toList()));
        graph.add(splitWords("0 20 1 0").stream().map(Integer::parseInt).collect(Collectors.toList()));
        
        int res = minCostToVisitEveryNode(graph);
        System.out.println(res);
    }
}