package General.DynamicProgramming.Bitmask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Given a directed acyclic graph(DAG) compute the longest path that the graph contains. 
 * 
 * For the purposes of this question, a path is defined by the edges and the input graph is in the form of a list of lists 
 * where the list at index i in the array represents each of the connections formed by node i. 
 * 
 * The number of nodes is guaranteed to not exceed 100000.
 * 
 * Example:
 * Input: [(1 -> 3), (2 -> 3), (3 -> 4)]
 * Output: 2
 * 
 * Solution:
 * 
 * The basic idea for this problem is to use dynamic programming to record the longest path every particular node might have. 
 * That is let F(i) denote the longest possible path that extends from node i and to store this in a dp array. 
 * We also note that we do not have to do dfs from every node and instead only start at nodes with no parents 
 * as in the case we dfs from a node with a parent we always know there exists a longer path if we started from the parent instead.
 * 
 * Time Complexity: O(n+m)
 * 
 * We traverse through the graph once, so therefore n+m operations where n is the number of nodes and m is the number of edges.
 */
public class LongestPath {

	public static int longestPath(List<List<Integer>> adj) {
        // Variable to store number of nodes.
        int n = adj.size();
        
        // Boolean array to store which nodes are parents.
        boolean[] parents = new boolean[100001];
        
        // Distance array.
        int[] dis = new int[100001];
        
        int max = -1;
        
        // Set every distance to -1 initially.
        for(int i = 0; i < n; i++) {
            dis[i] = -1;
        }
        
        // Loop through marking every node which is a parent node.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                parents[adj.get(i).get(j)] = true;
            }
        }
        
        // Only do dfs on nodes which does not have parents.
        for (int i = 0; i < n; i++) {
            if (!parents[i]) {
                // Update maximal value based on distance returned.
                max = Math.max(max, dfs(i, adj, dis));
            }
        }
        
        return max;
    }
	
    public static int dfs (int cur, List<List<Integer>> adj, int dis[]) {
        // Check if its a leaf node, if it is return 0.
        if (cur >= adj.size()) {
            return dis[cur] = 0;
        }
        
        // Check the neighbours of the node, if dis array is -1 we have not visited so do dfs, otherwise return largest path value.
        for (int i = 0; i < adj.get(cur).size(); i++) {
            if (dis[adj.get(cur).get(i)] == -1) {
                dis[cur] = Math.max(dis[cur], dfs(adj.get(cur).get(i), adj, dis) + 1);
            } else {
                dis[cur] = Math.max(dis[cur], dis[adj.get(cur).get(i)] + 1);
            }
        }
        
        return dis[cur];
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }
    
    // 3
    // 2
    // 2
    // 3
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int adjLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> adj = new ArrayList<>();
        
        for (int i = 0; i < adjLength; i++) {
            adj.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        
        scanner.close();
        int res = longestPath(adj);
        System.out.println(res);
    }
}