package General;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Leetcode 261.
 * 
 * You have a graph of n nodes labeled from 0 to n - 1.
 * You are given an integer n and a list of edges where edges[i] = [ai, bi] 
 * indicates that there is an undirected edge between nodes ai and bi in the graph.
 * Return true if the edges of the given graph make up a valid tree, and false otherwise.
 * 
 * Example 01:
 * Input: n = 5, edges = [[0,1], [0,2], [0,3], 1,4]].
 * Output: true
 * 
 * Example 02:
 * Input: n = 5, edges = [[0,1], [1,2], [2,3], [1,3], [1,4]].
 * Output: false.
 */ 
public class GraphValidTree {

	public static void main(String[] args) {
		//int n = 5; int[][] edges = {{0,1}, {0,2}, {0,3}, {1,4}};
		int n = 5; int[][] edges = {{0,1}, {1,2}, {2,3}, {1,3}, {1,4}};
		
		long startTime = System.nanoTime();
		System.out.println(solution01(n, edges));
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
		
		startTime = System.nanoTime();
		System.out.println(solution02(n, edges));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	}

	/**
	 * Approach: finding a cycle in the graph.
	 * If there is a cycle so it is not a tree.
	 * @param n
	 * @param edges
	 * @return
	 */
	public static boolean solution01(int n, int[][] edges) {
		Map<Integer, List<Integer>> graph = new HashMap<>();
		
		for (int i = 0; i < edges.length; i++) {
			if (graph.containsKey(edges[i][0])) {
				graph.get(edges[i][0]).add(edges[i][1]);
			} else {
				List<Integer> l = new ArrayList<>();
				l.add(edges[i][1]);
				graph.put(edges[i][0], l);
			}
		}
		
		for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
			Set<Integer> visited = new HashSet<>();
			
			if (!searchCycle(graph, visited, entry.getKey())) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean searchCycle(Map<Integer, List<Integer>> graph, Set<Integer> visited, int node) {
		if (visited.contains(node)) {
			return false;
		} else {
			visited.add(node);
		}
		
		if (!graph.containsKey(node)) {
			return true;
		}
		
		List<Integer> l = graph.get(node);
		
		for (Integer i : l) {
			if (!searchCycle(graph, visited, i)) {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Approach: using a union find.
	 * 
	 * @param n
	 * @param edges
	 * @return
	 */
	public static boolean solution02(int n, int[][] edges) {
		// Initialize n isolated islands.
		int[] fathers = new int[n];
		Arrays.fill(fathers, -1);
		
		// Perform union find.
		for (int i = 0; i < edges.length; i++) {
			int x = find(fathers, edges[i][0]);
			int y = find(fathers, edges[i][1]);
			
			// If two vertices happen to be in the same set then there is a cycle.
			if (x == y) {
				return false;
			}
			
			// Union.
			fathers[y] = x;
		}
		
		return edges.length == n -1;
	}
	
	public static int find(int fathers[], int i) {
		if (fathers[i] ==  -1) {
			return i;
		}
		
		return find(fathers, fathers[i]);
	} 
}