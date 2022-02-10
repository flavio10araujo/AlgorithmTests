package General.Graph.WeightedGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The Shortest-Path Faster Algorithm abbreviated to SPFA can be thought of as a BFS variant. 
 * Instead of checking whether or not the neighbor node has been visited 
 * we instead see if we can improve our distance by checking the neighbor nodes 
 * and if it possible to update to a smaller distance and push the node into our queue.
 * 
 * This algorithm runs in worst case O(n * m) where n is the number of nodes in our graph and m is thg number of edges.
 * 
 * This algorithm works for smaller graphs and is easy to implement, so if you are in a rush and can afford to have a less efficient algorithm, you may want to use SPFA.
 */
public class ShortestPathFasterAlgorithm {

	// 4
	// 1 1 2 1
	// 0 1 2 1 3 1
	// 0 1 1 1
	// 1 1
	// 0
	// 3
	// output: 2
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int graphLength = Integer.parseInt(scanner.nextLine());
        List<List<Map.Entry<Integer, Integer>>> graph = new ArrayList<>();
        
        for (int i = 0; i < graphLength; i++) {
            String s = scanner.nextLine();
            List<String> l = s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
            List<Map.Entry<Integer, Integer>> lm = new ArrayList<>();
            
            for (int j = 0; j < l.size(); j += 2) {
                lm.add(new HashMap.SimpleEntry<Integer, Integer>(Integer.parseInt(l.get(j)),
                        Integer.parseInt(l.get(j + 1))));
            }
            
            graph.add(lm);
        }
        
        int a = Integer.parseInt(scanner.nextLine());
        int b = Integer.parseInt(scanner.nextLine());
        
        scanner.close();
        
        System.out.println(shortestPath(graph, a, b));
    }
	
	public static int shortestPath(List<List<Map.Entry<Integer, Integer>>> graph, int a, int b) {
        return bfs(graph, a, b);
    }
	
	private static int bfs(List<List<Map.Entry<Integer, Integer>>> graph, int root, int target) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(root);
        
        List<Integer> distance = new ArrayList<>();
        
        for (int i = 0; i < graph.size(); i++) {
            distance.add(Integer.MAX_VALUE);
        }
        
        distance.set(root, 0);
        
        while (queue.size() > 0) {
            int node = queue.poll();
            
            for (Map.Entry<Integer, Integer> neighbour : getNeighbors(graph, node)) {
                int id = neighbour.getKey();
                int weight = neighbour.getValue();
                
                if (distance.get(id) <= distance.get(node) + weight) {
                    continue;
                }
                
                queue.add(id);
                distance.set(id, distance.get(node) + weight);
            }
        }
        
        return distance.get(target) == Integer.MAX_VALUE ? -1 : distance.get(target);
    }

	private static List<Map.Entry<Integer, Integer>> getNeighbors(List<List<Map.Entry<Integer, Integer>>> graph, int node) {
        return graph.get(node);
    }
}