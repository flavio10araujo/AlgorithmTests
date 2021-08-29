package General.DynamicProgramming.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * For this question we ask you to compute the length of the longest possible cycle on a tree 
 * after adding an edge to a graph connecting any 2 nodes in a graph. 
 * As a reminder, a tree is a special subset of graphs that have n nodes, n - 1 edges and possess no cycles. 
 * The input will have n the number of nodes in the tree where the nodes will be numbered from 1 to n. 
 * t Then, a list of size n -1 called edges denoting the edges in the graph. 
 * It should be noted that adding any 1 edge to a tree will make at least 1 cycle in the graph.
 * 
 * Constraints
 * 1 <= n <= 100000
 * 
 * Example 1:
 * Input 1: n = 4, edges = [[1, 2], [2, 3], [2, 4]]
 * Output 1: 3
 * Explanation: We connect the edge between nodes 1 and 3 thus creating a cycle of size 3 which is the longest possible cycle.
 * 
 * Solution:
 * 
 * What is the main idea behind this question?
 * We firstly realize that looking for the longest cycle is no different from finding the longest path in the tree then adding an edge to make the path a cycle. 
 * Then the problem simply becomes looking for the longest path in the tree. 
 * On inspection, we can do a DFS or BFS search from every node in the tree 
 * and check every distance which nets us a time of O(n^2) since traversing a tree once we have established is O(n).
 * Can we improve this?
 * If we use DP and remember the longest path from a node to a leaf node then at every node we check if we have a new longest path. 
 * This can be checked by checking the 2 largest paths from the current node to the leaf node and seeing if this achieves a new maximum. 
 * This can all be done in a DFS through the tree.
 * An alternative solution is to DFS or BFS through a tree twice to find the longest path in a tree. 
 * The solution provided makes use of DP.
 * Time Complexity: O(n)
 * One-time DFS is sufficient.
 */
public class LongestCycle {

	public static int dfs(List<Integer> graph[], int current, int parent, int [] longestPathTotal, int [] longestPathFromLeafNode) {
        longestPathFromLeafNode[current] = 0;
        longestPathTotal[current] = 0;
        int longestPath = 0;
        int secondLongestPath = 0;
        for (int neighbor : graph[current]) {
            if (neighbor != parent) {
                longestPathTotal[current] = Math.max(longestPathTotal[current], dfs(graph, neighbor, current, longestPathTotal, longestPathFromLeafNode));
                longestPathFromLeafNode[current] = Math.max(longestPathFromLeafNode[current], longestPathFromLeafNode[neighbor] + 1);
                if (longestPathFromLeafNode[current] > longestPath) {
                    secondLongestPath = longestPath;
                    longestPath = longestPathFromLeafNode[current];
                }
                else if (longestPathFromLeafNode[current] > secondLongestPath) {
                    secondLongestPath = longestPathFromLeafNode[current];
                }
            }
        }
        longestPathTotal[current] = Math.max(longestPathTotal[current], longestPath + secondLongestPath);
        return longestPathTotal[current];
    }
	
    public static int longestCycle(int n, List<List<Integer>> edges){
        List<Integer> [] graph = new ArrayList[n + 1];
        int [] longestPathTotal = new int [n + 1];
        int [] longestPathFromLeafNode = new int [n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for (List<Integer> edge : edges) {
            graph[edge.get(0)].add(edge.get(1));
            graph[edge.get(1)].add(edge.get(0));
        }
        return dfs(graph, 1, 1, longestPathTotal, longestPathFromLeafNode) + 1;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 4
    // 3
    // 1 2
    // 2 3
    // 2 4
    // output: 3
    
    // 9
    // 8
    // 1 2
    // 2 3
    // 3 4
    // 4 5
    // 5 6
    // 6 7
    // 7 8
    // 8 9
    // output: 9
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int edgesLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < edgesLength; i++) {
            edges.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        int res = longestCycle(n, edges);
        System.out.println(res);
    }
}