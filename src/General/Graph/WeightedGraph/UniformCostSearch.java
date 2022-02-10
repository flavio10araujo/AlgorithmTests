package General.Graph.WeightedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * In Dijkstra's Algorithm, we add all vertices to the priority queue at the beginning. 
 * However, this is not possible when we have a large graph that does not fit in memory, or even infinite graph. 
 * The Uniform Cost Search algorithm is a variant of Dijkstra's Algorithm. 
 * We start with the priority queue containing only the root node, and add new vertices as we checking the neighbors.
 */
public class UniformCostSearch {

	// 4
	// 1 1 2 1
	// 0 1 2 1 3 1
	// 0 1 1 1
	// 1 1
	// 0
	// 3
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
        Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(1, (a, b) -> (Integer.compare(a.getKey(), b.getKey())));
        pq.add(new HashMap.SimpleEntry<>(0, root));
        
        List<Integer> distances = new ArrayList<>();
        
        for (int i = 0; i < graph.size(); i++) {
            distances.add(Integer.MAX_VALUE);
        }
        
        distances.set(root, 0);
        
        while (pq.size() > 0) {
            Map.Entry<Integer, Integer> t = pq.poll();
            int distance = t.getKey();
            int node = t.getValue();
            
            if (node == target) {
                return distance;
            }
            
            if (distance > distances.get(node)) {
                continue;
            }
            
            for (Map.Entry<Integer, Integer> neighbour : getNeighbors(graph, node)) {
                int id = neighbour.getKey();
                int weight = neighbour.getValue();
                int d = distances.get(node) + weight;
                
                if (distances.get(id) <= d) {
                    continue;
                }
                
                pq.add(new HashMap.SimpleEntry<>(d, id));
                distances.set(id, d);
            }
        }
        
        return distances.get(target) == Integer.MAX_VALUE ? -1 : distances.get(target);
    }
	
	private static List<Map.Entry<Integer, Integer>> getNeighbors(List<List<Map.Entry<Integer, Integer>>> graph, int node) {
        return graph.get(node);
    }
}