package SearchingAlgorithm.Graph;

import java.util.Iterator;
import java.util.LinkedList;

// This class represents a directed graph using adjacency list representation.
// https://www.tutorialspoint.com/data_structures_algorithms/breadth_first_traversal.htm

/**
 * Breadth First Search (or Traversal) for a graph is similar to BFS of a tree.
 * The only catch here is, unlike trees, graphs may contain cycles, so we may come to the same node again.
 * To avoid processing a node more than once, we use a boolean visited array.
 * For simplicity, it is assumed that all vertices are reachable from the starting vertex.
 * 
 * Time Complexity: O(V + E) where V is the number of vertices in the graph and E is the number of edges in the graph.
 */
class BreadthFirstSearch {
	private int V; // No. of vertices/nodes.
	private LinkedList<Integer> adj[]; // Adjacency Lists

	BreadthFirstSearch(int v) {
		V = v;
		adj = new LinkedList[v];
		
		for (int i = 0; i < v; ++i) {
			adj[i] = new LinkedList<Integer>();
		}
	}

	// Function to add an edge into the graph.
	void addEdge(int v, int w) {
		adj[v].add(w);
	}

	// Print BFS traversal from a given source s.
	void BFS(int s) {
		// Mark all the vertices as not visited (set as false by default in java).
		boolean visited[] = new boolean[V];

		// Create a queue for BFS.
		LinkedList<Integer> queue = new LinkedList<Integer>();

		// Mark the current node as visited and enqueue it.
		visited[s] = true;
		queue.add(s);

		while (queue.size() != 0) {
			// Dequeue a vertex from queue and print it.
			s = queue.poll();
			System.out.print(s + " ");

			// Get all adjacent vertices of the dequeued vertex s.
			// If an adjacent has not been visited, then mark it visited and enqueue it.
			Iterator<Integer> i = adj[s].listIterator();
			
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}
	}

	public static void main(String args[]) {
		BreadthFirstSearch g = new BreadthFirstSearch(4);
		
		//       0
		//     // \
		//    2 __ 1
		//   /
		//  3<->

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);

		System.out.println("Following is Breadth First Traversal (starting from vertex 2)");

		g.BFS(2);
	}
}