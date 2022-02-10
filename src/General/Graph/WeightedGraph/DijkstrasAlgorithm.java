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
 * The Dijkstra's Algorithm uses a priority queue to store nodes by the distance from our root node. 
 * When we pop a node and its distance, we know that the distance is the shortest distance from our source node to the node. 
 * And we update the distances of the neighbors of the node by decrease_priority 
 * to make sure the distances of other nodes in the priority queue is the shortest from source node. 
 * Each time we pop a node from the queue it takes time logarithmic to the size of the queue. 
 * Therefore, our final time complexity is O(n log(e)) as maintaining the edges in the queue takes logarithmic time. 
 */
public class DijkstrasAlgorithm {

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
        Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(1, (a, b) -> (Integer.compare(a.getKey(), b.getKey())));
        
        List<Integer> distances = new ArrayList<>();
        
        for (int i = 0; i < graph.size(); i++) {
            if (i == root) {
                distances.add(0);
                pq.add(new HashMap.SimpleEntry<>(0, i));
            } else {
                distances.add(Integer.MAX_VALUE);
                pq.add(new HashMap.SimpleEntry<>(Integer.MAX_VALUE, i));
            }
        }
        
        while (pq.size() > 0) {
            Map.Entry<Integer, Integer> t = pq.poll();
            int node = t.getValue();
            
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