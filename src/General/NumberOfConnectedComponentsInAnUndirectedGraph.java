package General;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Leetcode 323
 * 
 * You have a graph of n nodes.
 * You are given an integer n and an array edges where edges[i] = [ai, bi]
 * indicates that there is an edge between ai and bi in the graph.
 * Return the number of connected components in the graph.
 *
 * Example 01:
 * Input: n=5; edges = [[0,1],[1,2][3,4]]
 * Output: 2
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph {

	public static void main(String[] args) {
		int n = 5;
		int[][] edges = {{0,1}, {1,2}, {3,4}};

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
	 * Approach: DFS.
	 * Time complexity: O(V + E).
	 * Space complexity: O(V + E).
	 * @param n
	 * @param edges
	 * @return
	 */
	public static int solution01(int n, int[][] edges) {
		int ans = 0;
		List<Integer>[] graph = new List[n];
		Set<Integer> seen = new HashSet<>();

		for (int i = 0; i < graph.length; ++i) {
			graph[i] = new ArrayList<>();
		}

		for (int[] edge : edges) {
			graph[edge[0]].add(edge[1]);
			graph[edge[1]].add(edge[0]);
		}

		for (int i = 0; i < n; ++i) {
			if (!seen.contains(i)) {
				dfs(graph, i, seen);
				++ans;
			}
		}

		return ans;
	}

	private static void dfs(List<Integer>[] graph, int u, Set<Integer> seen) {
		for (final int v : graph[u]) {
			if (seen.add(v)) {
				dfs(graph, v, seen);
			}
		}
	}

	/**
	 * Approach: Union Find.
	 * Time complexity: O(V + E).
	 * Space complexity: O(V + E).
	 * @param n
	 * @param edges
	 * @return
	 */
	public static int solution02(int n, int[][] edges) {
		UF uf = new UF(n);

		for (int[] edge : edges)
			uf.union(edge[0], edge[1]);

		return uf.getSize();
	}

	static class UF {
		private int size;
		private int[] parent;
		
		public UF(int n) {
			size = n;
			parent = new int[n];

			for (int i = 0; i < n; ++i) {
				parent[i] = i;
			}
		}

		public void union(int u, int v) {
			final int pu = find(u);
			final int pv = find(v);
			
			if (pu == pv) {
				return;
			}

			parent[pu] = pv;
			--size;
		}

		public int getSize() {
			return size;
		}

		private int find(int u) {
			if (u != parent[u]) {
				parent[u] = find(parent[u]);
			}
			
			return parent[u];
		}
	}
}