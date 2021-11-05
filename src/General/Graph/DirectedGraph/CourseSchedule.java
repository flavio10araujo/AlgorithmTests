package General.Graph.DirectedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/course-schedule/
 * 
 * There are a total of n courses a student has to take, numbered from 0 to n-1.
 * A course may have prerequisites. 
 * The "depends on" relationship is expressed as a pair of numbers. 
 * For example, [0, 1] means you need to take course 1 before taking course 0. 
 * Given n and the list of prerequisites, decide if it is possible to take all the courses.
 * 
 * Input: n = 2, prerequisites = [[0, 1]]
 * Output: true
 * Explanation: we can take 1 first and then 0.
 * 
 * Input: n = 2, prerequisites = [[0, 1], [1, 0]]
 * Output: false
 * Explanation: the two courses depend on each other, there is no way to take them.
 */
public class CourseSchedule {
	private enum State {
        TO_VISIT, VISITING, VISITED
    }

    private static Map<Integer, List<Integer>> buildGraph(List<List<Integer>> prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>(prerequisites.size());
        
        for (List<Integer> dependency : prerequisites) {
            graph.computeIfAbsent(dependency.get(0), k -> new ArrayList<>()).add(dependency.get(1));
        }
        
        return graph;
    }

    private static boolean dfs(int start, State[] states, Map<Integer, List<Integer>> graph) {
        // Mark self as visiting.
        states[start] = State.VISITING;

        if (graph.get(start) != null) {
            for (Integer nextVertex : graph.get(start)) {
                // Ignore visited nodes.
                if (states[nextVertex] == State.VISITED) {
                	continue;
                }
                
                // Revisiting a visiting node, CYCLE!
                if (states[nextVertex] == State.VISITING) {
                	return false;
                }
                
                // Recursively visit neighbours.
                // If a neighbour found a cycle, we return false right away.
                if (!dfs(nextVertex, states, graph)) {
                	return false;
                }
            }
        }

        // Mark self as visited.
        states[start] = State.VISITED;
        
        // If we have gotten this far, our neighbours haven't found any cycle, return true.
        return true;
    }

    public static boolean isValidCourseSchedule(int n, List<List<Integer>> prerequisites) {
        Map<Integer, List<Integer>> graph = buildGraph(prerequisites);
        State[] states = new State[n];
        Arrays.fill(states, State.TO_VISIT);
        
        // DFS on each node.
        for (int i = 0; i < n; i++) {
            if (!dfs(i, states, graph)) {
            	return false;
            }
        }
        
        return true;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt("2");
        List<List<Integer>> prerequisites = new ArrayList<>();
        prerequisites.add(splitWords("0 1").stream().map(Integer::parseInt).collect(Collectors.toList()));
        boolean res = isValidCourseSchedule(n, prerequisites);
        System.out.println(res);
    }
}