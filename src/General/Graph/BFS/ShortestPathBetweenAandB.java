package General.Graph.BFS;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given an (unweighted) graph, return the length of the shortest path between two nodes A and B.
 * 
 * Input:
 * 	graph: {
 * 		0: [1, 2],
 * 		1: [0, 2, 3],
 * 		2: [0, 1],
 * 		3: [1]
 * 	}
 * A: 0
 * B: 3
 * Output: 2
 */
public class ShortestPathBetweenAandB {
	
	private static List<Integer> getNeighbours(Map<Integer, List<Integer>> graph, int node) {
        return graph.get(node);
    }

    public static int lengthOfShortestPath(Map<Integer, List<Integer>> graph, int a, int b) {
    	
    	Deque<Integer> queue = new ArrayDeque<Integer>();
    	queue.add(a);
    	
    	Set<Integer> visited = new HashSet<Integer>();
    	int count = 0;
    	
    	while (queue.size() > 0) {
    		
    		int n = queue.size();
    		
    		for (int i = 0; i < n; i++) {
    			
    			int node = queue.pop();
    			
    			if (node == b) {
    				return count;
    			}
    			
    			for (Integer neighbour : getNeighbours(graph, node)) {
    				if (visited.contains(neighbour)) {
    					continue;
    				}
    				
    				queue.add(neighbour);
    				visited.add(neighbour);
    			}
    		}
    		
    		count++;
    	}
    	
    	return count;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 4
    // 1 2
    // 0 2 3
    // 0 1
    // 1
    // 0
    // 3
    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        graph.put(0, splitWords("1 2").stream().map(Integer::parseInt).collect(Collectors.toList()));
        graph.put(1, splitWords("0 2 3").stream().map(Integer::parseInt).collect(Collectors.toList()));
        graph.put(2, splitWords("0 1").stream().map(Integer::parseInt).collect(Collectors.toList()));
        graph.put(3, splitWords("1").stream().map(Integer::parseInt).collect(Collectors.toList()));
        
        int a = Integer.parseInt("0");
        int b = Integer.parseInt("3");
        
        int res = lengthOfShortestPath(graph, a, b);
        System.out.println(res);
    }
}