package General.Graph.DirectedGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseScheduleII {

	public static void main(String[] args) {
		int numCourses = 4;
		int[][] prerequisites = {{3,0},{2,0},{1,3},{1,2}};
		
		int[] ans = findOrder(numCourses, prerequisites);
		
		for (int i = 0; i < ans.length; i++) {
			System.out.print(ans[i] + " ");
		}
	}
	
	public static int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = buildGraph(prerequisites);
        
        Deque<Integer> ans = new ArrayDeque<>();
        
        int[] visited = new int[numCourses]; // 0=not visited, 1=visiting; 2=visited
        
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, graph, visited, ans)) {
                return new int[0];
            }
        }
        
        return ans.stream().mapToInt(i -> i).toArray();
    }
    
    private static boolean dfs(int start, Map<Integer, List<Integer>> graph, int[] visited, Deque<Integer> ans) {
    	if (visited[start] == 2) {
    		return true;
    	}
    	
    	visited[start] = 1;
        
        if (graph.containsKey(start)) {
            for (Integer nextVertex : graph.get(start)) {
                if (visited[nextVertex] == 2) {
                    continue;
                }
                
                if (visited[nextVertex] == 1) {
                    return false;
                }
                
                if (!dfs(nextVertex, graph, visited, ans)) {
                    return false;
                }
            }
        }
        
        if (visited[start] != 2) {
            ans.add(start);
        }
        
        visited[start] = 2;
        
        return true;
    }
    
    private static Map<Integer, List<Integer>> buildGraph(int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        for (int i = 0; i < prerequisites.length; i++) {
            if (graph.containsKey(prerequisites[i][0])) {
                graph.get(prerequisites[i][0]).add(prerequisites[i][1]);
            } else {
                List<Integer> l = new ArrayList<>();
                l.add(prerequisites[i][1]);
                graph.put(prerequisites[i][0], l);
            }
        }
        
        return graph;
    }
}