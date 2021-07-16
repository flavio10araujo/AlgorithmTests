package General.Graph.DirectedGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This problem is similar to Task Scheduling. 
 * The primary difference is now we assign times to tasks and we ask for the minimum amount of time to complete all tasks. 
 * There will be an extra times array such that times[i] indicates the time required to complete task[i]. 
 * You have also invited all your friends to help complete your tasks so you can work on any amount of tasks at a given time. 
 * Be sure to remember that task a must be completed before task b so despite your unlimited manpower your friends must 
 * still wait for task a to complete before starting task b.
 * There is guaranteed to be a solution.
 * 
 * Example:
 * 	Input:
 *    tasks = ["a", "b", "c", "d"]
 *    times = [1, 1, 2, 1]
 *    requirements = [["a", "b"], ["c", "b"], ["b", "d"]]
 * Output: 4
 */
public class TaskScheduling2 {

	public static int taskScheduling2(List<String> tasks, List<Integer> times, List<List<String>> requirements) {
		
        // Initialize our data structures, we have a adjacencylist to represent the graph, parents stores number of parent nodes
        // of current node, and taskTimes stores time for a fiven task.
        Map<String, List<String>> graph = new HashMap<String, List<String>>();
        Map<String, Integer> parents = new HashMap<String, Integer>();
        Map<String, Integer> taskTimes = new HashMap<String, Integer>();

        // Loop through the tasks and initialize the graph and put in the relevant times into the times HashMap.
        for (int i = 0; i < tasks.size(); i++) {
            graph.put(tasks.get(i), new ArrayList<>());
            taskTimes.put(tasks.get(i), times.get(i));
        }

        // Loop through the requirements and add it to our graph, and number of parent nodes.
        for (List<String> req : requirements) {
            graph.get(req.get(0)).add(req.get(1));
            
            // Check if we already have the key in our map.
            if (!parents.containsKey(req.get(1))) {
                parents.put(req.get(1), 1);
            } else {
                parents.put(req.get(1), parents.get(req.get(1)) + 1);
            }
        }

        // Make deque that we loop through as well as distance HashMap.
        Deque<String> queue = new ArrayDeque<String>();
        Map<String, Integer> dis = new HashMap<String, Integer>();
        
        for (int i = 0; i < tasks.size(); i++) {
            // Add the relevant elements to our queue as well as intialize the distance values.
            if (!parents.containsKey(tasks.get(i))) {
                queue.addLast(tasks.get(i));
                dis.put(tasks.get(i), times.get(i));
            }
        }

        // Standard topological sort calculation.
        int ans = 0;
        
        while (!queue.isEmpty()) {
            String cur = queue.pollFirst();
            // Make sure to check if current task time is maximal.
            ans = Math.max(ans, dis.get(cur));
            
            for (String task : graph.get(cur)) {
                // If distance is not contained in the HashMap add it to the map otherwise update the value.
                if (!dis.containsKey(task)) {
                    dis.put(task, dis.get(cur) + taskTimes.get(task));
                } else {
                    dis.put(task, Math.max(dis.get(task), dis.get(cur) + taskTimes.get(task)));
                }
                
                // Check if node has no other parents, if not push it into the queue, remember we always want to visit all parents for
                // a topological sort.
                parents.put(task, parents.get(task) - 1);
                if (parents.get(task) == 0) {
                    queue.addLast(task);
                }
            }
        }
        
        return ans;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        List<String> tasks = splitWords("a b c d");
        List<Integer> times = splitWords("1 1 2 1").stream().map(Integer::parseInt).collect(Collectors.toList());
        List<List<String>> requirements = new ArrayList<>();
        
        requirements.add(splitWords("a b"));
        requirements.add(splitWords("c b"));
        requirements.add(splitWords("b d"));
        
        int res = taskScheduling2(tasks, times, requirements);
        System.out.println(res);
    }
}