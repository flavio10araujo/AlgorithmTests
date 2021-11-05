package General;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode.com/problems/clone-graph/
 * 
 * Given a reference of a node in a connected undirected graph.
 * Return a deep copy (clone) of the graph.
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 * 
 * class Node {
 * 	 public int val;
 * 	 public List<Node> neighbors;
 * }
 * 
 * Test case format:
 * 
 * For simplicity, each node's value is the same as the node's index (1-indexed). 
 * For example, the first node with val == 1, the second node with val == 2, and so on. 
 * The graph is represented in the test case using an adjacency list.
 * An adjacency list is a collection of unordered lists used to represent a finite graph. 
 * Each list describes the set of neighbors of a node in the graph.
 * The given node will always be the first node with val = 1. 
 * You must return the copy of the given node as a reference to the cloned graph.
 * 
 * Example 01:
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 
 * Example 02:
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
 * 
 * Example 03:
 * Input: adjList = []
 * Output: []
 * Explanation: This an empty graph, it does not have any nodes.
 * 
 * Example 04:
 * Input: adjList = [[2],[1]]
 * Output: [[2],[1]]
 * 
 * Constraints:
 * The number of nodes in the graph is in the range [0, 100].
 * 1 <= Node.val <= 100
 * Node.val is unique for each node.
 * There are no repeated edges and no self-loops in the graph.
 * The Graph is connected and all nodes can be visited starting from the given node.
 */
public class CloneGraph {

	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		
		n1.neighbors.add(n2);
		n1.neighbors.add(n4);
		
		n2.neighbors.add(n1);
		n2.neighbors.add(n3);
		
		n3.neighbors.add(n2);
		n3.neighbors.add(n4);
		
		n4.neighbors.add(n1);
		n4.neighbors.add(n3);
		
		Node res = solution01(n1);
		
		 
	}

	static class Node {
		public int val;
		public List<Node> neighbors;
		
		public Node() {
			val = 0;
			neighbors = new ArrayList<Node>();
		}
		
		public Node(int _val) {
			val = _val;
			neighbors = new ArrayList<Node>();
		}
		
		public Node(int _val, ArrayList<Node> _neighbors) {
			val = _val;
			neighbors = _neighbors;
		}
	}

	/**
	 * Approach: BFS.
	 * @param node
	 * @return
	 */
	public static Node solution01(Node node) {
		if (node == null) {
			return null;
		}

		Queue<Node> q = new LinkedList<>(Arrays.asList(node));
		
		Map<Node, Node> map = new HashMap<>();
		map.put(node, new Node(node.val));

		while (!q.isEmpty()) {
			Node n = q.poll();
			
			for (Node neighbor : n.neighbors) {
				if (!map.containsKey(neighbor)) {
					map.put(neighbor, new Node(neighbor.val));
					q.offer(neighbor);
				}
				
				map.get(n).neighbors.add(map.get(neighbor));
			}
		}

		return map.get(node);
	}

	/**
	 * Approach: BFS.
	 * @param node
	 * @return
	 */
	public static Node solution02(Node node) {
		if (node == null)
			return null;
		if (map.containsKey(node))
			return map.get(node);

		Node newNode = new Node(node.val);
		map.put(node, newNode);

		for (Node neighbor : node.neighbors)
			newNode.neighbors.add(solution02(neighbor));

		return newNode;
	}

	static private Map<Node, Node> map = new HashMap<>();
}