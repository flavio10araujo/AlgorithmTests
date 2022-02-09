package General.Graph.DirectedGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * For this problem, given a list of tasks and a list of requirements, 
 * compute a sequence of tasks that can be performed, 
 * such that we complete every task once while satisfying all the requirements.
 * 
 * Each requirement will be in the form of a list [a, b], where task a needs to be completed first before task b can be completed,
 * There is guaranteed to be a solution.
 * 
 * Example
 * Input:
 * 	tasks = ["a", "b", "c", "d"]
 * 	requirements = [["a", "b"], ["c", "b"], ["b", "d"]]
 * 	Output: ["a", "c", "b", "d"]
 * 
 * Solution:
 * 
 * Cycle detection
 * One way to detect cycles is to use DFS. 
 * For normal graph DFS, a node has two states, to be visited and visited. 
 * We traverse the graph and visit each to be visited node and mark them as visited.
 * In cycle detection, we need a third state: visiting.
 */
public class TaskScheduling {

	public static void main(String[] args) {
		List<String> tasks = splitWords("a b c d");
		List<List<String>> requirements = new ArrayList<>();
		requirements.add(splitWords("a b"));
		requirements.add(splitWords("c b"));
		requirements.add(splitWords("b d"));

		List<String> res = solution02(tasks, requirements);

		if (res.size() != tasks.size()) {
			System.out.println("output size " + res.size() + " does not match input size " + tasks.size());
			return;
		}

		Map<String, Integer> indices = new HashMap<>();

		for (int i = 0; i < res.size(); i++) {
			indices.put(res.get(i), i);
		}

		for (List<String> req : requirements) {
			for (String task : req) {
				if (!indices.containsKey(task)) {
					System.out.println("'" + task + "' is not in output");
					return;
				}
			}

			String a = req.get(0);
			String b = req.get(1);

			if (indices.get(a) >= indices.get(b)) {
				System.out.println("'" + a + "' is not before '" + b + "'");
				return;
			}
		}

		System.out.println("ok");
	}

	public static List<String> splitWords(String s) {
		return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
	}

	/**
	 * Approach: DFS
	 * 
	 * @param tasks
	 * @param requirements
	 * @return
	 */
	public static List<String> solution01(List<String> tasks, List<List<String>> requirements) {
		Map<String, List<String>> graph = new HashMap<>();

		for (String task : tasks) {
			graph.put(task, new ArrayList<>());
		}

		for (List<String> req : requirements) {
			graph.get(req.get(0)).add(req.get(1));
		}

		return topoSort(graph);
	}

	public static List<String> topoSort(Map<String, List<String>> graph) {
		// node -> true if visited, false if visiting
		Map<String, Boolean> visited = new HashMap<>();
		List<String> res = new ArrayList<>();

		for (String node : graph.keySet()) {
			topoSortDfs(node, graph, visited, res);
		}

		Collections.reverse(res);
		return res;
	}

	public static void topoSortDfs(String node, Map<String, List<String>> graph, Map<String, Boolean> visited, List<String> res) {

		if (visited.containsKey(node)) {
			if (visited.get(node)) {
				return;
			}

			throw new RuntimeException("not a DAG (Directed Acyclic Graph): cycle detected");
		}

		visited.put(node, false);

		for (String child : graph.get(node)) {
			topoSortDfs(child, graph, visited, res);
		}

		visited.put(node, true);
		res.add(node);
	}

	/**
	 * Approach: BFS (Kahn's Algorithm)
	 * 
	 * @param tasks
	 * @param requirements
	 * @return
	 */
	public static List<String> solution02(List<String> tasks, List<List<String>> requirements) {
		Map<String, List<String>> graph = new HashMap<>();

		for (String task : tasks) {
			graph.put(task, new ArrayList<>());
		}

		for (List<String> req : requirements) {
			graph.get(req.get(0)).add(req.get(1));
		}

		return kahnsAlgorithm(graph);
	}

	public static <T> List<T> kahnsAlgorithm(Map<T, List<T>> graph) {
		List<T> res = new ArrayList<>();
		Queue<T> q = new ArrayDeque<>();
		
		// Loop through all nodes and add all nodes that do not have any parent.
		Map<T, Integer> counts = countParents(graph);
		
		counts.entrySet().forEach(entry -> {
			if (entry.getValue() == 0) {
				q.add(entry.getKey());
			}
		});
		
		while (!q.isEmpty()) {
			T node = q.poll();
			// Add node to list to keep track of topological order.
			res.add(node);
			
			for (T child : graph.get(node)) {
				// Subtract one from every neighbor.
				counts.put(child, counts.get(child) - 1);
				
				// Once the number of parents reaches zero you add it to the queue.
				if (counts.get(child) == 0) {
					q.add(child);
				}
			}
		}
		
		return res;
	}

	// Count the number of parents that a particular node has by adding to the node, need to initialize this before.
	public static <T> Map<T, Integer> countParents(Map<T, List<T>> graph) {
		Map<T, Integer> counts = new HashMap<>();
		
		graph.keySet().forEach(node -> {
			counts.put(node, 0);
		});
		
		// Loop through every node and add to the child node 1 parent.
		graph.entrySet().forEach(entry -> {
			for (T node : entry.getValue()) {
				counts.put(node, counts.get(node) + 1);
			}
		});
		
		return counts;
	}
}