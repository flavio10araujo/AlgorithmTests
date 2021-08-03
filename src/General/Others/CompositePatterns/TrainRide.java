package General.Others.CompositePatterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Recently you've moved to the new city Umbristan working in the government sector. 
 * You haven't been able to get your driver's license since moving to the city. 
 * Therefore, you have to take the subway. 
 * The subway can be denoted by n stations numbered from 1 to n. 
 * There are a series of lines in the city which consist of train tracks running between 2 different stations. 
 * The lines are denoted by a number from 1 to k where 1 <= k <= n. 
 * The Umbristan subway system has a strange fare system. 
 * When you buy a ticket you can pay any price between 1 to k. 
 * Then after purchasing the ticket you can only ride lines between 1 and the price you paid for the ticket (inclusive). 
 * You having recently moved to the city lack funds and wanting to save as much money as possible, 
 * you are interested in the minimum price you have to spend in order to make it to your workplace.
 * You live at station 1 and your workplace is located at station n.
 * The first input is the number n denoting the number of stations.
 * The second input is the number k denoting the number of lines.
 * The third input is a list of triplets where the first 2 values denotes the 2 stations connected and the 3rd value is the line it belongs to.
 * If a path does not exist between your workplace and home, output -1, 
 * otherwise output the minimum ticket price needed for you to travel to your workplace.
 * 
 * Example 1:
 * Input:
 * n = 5, 
 * k = 2, 
 * connections = [[1, 2, 1], [2, 3, 1], [3, 4, 1], [4, 5, 1], [2, 4, 2], [1, 5, 2]]
 * Output: 1
 * Explanation: You only need connections on line 1 in order to go from station 1 to station n. 
 *              Therefore, you only need to buy a ticket with price 1.
 * 
 * Solution:
 * 
 * Naive Solution:
 * The naive solution is to try every value of k and see if there exists a path between your home and workplace 
 * until you find a viable path before you stop using a graph traversal algorithm such as BFS. 
 * Let e denote the number of edges in the graph.
 * Time Complexity: O((n+e)k))
 * 
 * Optimized Solution:
 * A more optimized solution is to use a disjoint set data structure. 
 * We are only interested in cases where our workplace and home are connected in the same graph for the lowest price. 
 * We can organize the edges by their line value and loop upwards. 
 * For every line we go through each edge merging the sets that the edge belongs to. 
 * Then we check if our home and workplace are in the same set at which point we can terminate.
 * Time Complexity: O(e)
 */
public class TrainRide {

	public static class UnionFind<T> {
        private Map<T, T> f = new HashMap<>();

        public T find(T x) {
            T y = f.getOrDefault(x, x);
            
            if (y != x) {
                y = find(y);
                f.put(x, y);
            }
            
            return y;
        }

        public void union(T x, T y) {
            f.put(find(x), find(y));
        }
    }

    public static int trainRide (int n, int k, List<List<Integer>> connections) {
        // Initialize disjoint set data structure.
        UnionFind<Integer> dsu = new UnionFind<Integer>();
        
        // List of the edges for a particular line.
        List<List<Integer>> [] lines = new ArrayList[k + 1];
        
        // Make sure to initialize each list.
        for (int i = 0; i <= k; i++) {
            lines[i] = new ArrayList<List<Integer>>();
        }
        
        // Loop through the connections and add.
        for (List<Integer> connection : connections) {
            lines[connection.get(2)].add(Arrays.asList(connection.get(0), connection.get(1)));
        }
        
        // Loop through the lines, we stop once we have connected our home station to station n.
        int minimumPrice = 1;
        
        for (; minimumPrice <= k && dsu.find(1) != dsu.find(n); minimumPrice++) {
            for (List<Integer> connection : lines[minimumPrice]) {
                // Merge the edges for each connection for a line.
                if (dsu.find(connection.get(0)) != dsu.find(connection.get(1))) {
                    dsu.union(connection.get(0), connection.get(1));
                }
            }
        }
        
        return (dsu.find(1) != dsu.find(n) ? -1 : minimumPrice - 1);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    /*
     5
	 2
	 6
	 1 2 1
	 2 3 1
	 3 4 1
	 4 5 1
	 2 4 2
	 1 5 2
	 Output: 1
     */
    /*
     10
	 3
	 14
	 1 2 1
	 2 3 1
	 3 4 1
	 4 5 2
	 5 6 2
	 6 7 2
	 7 8 3
	 8 9 3
	 9 10 3
	 1 4 1
	 1 9 1
	 5 9 1
	 2 9 2
	 2 9 1
	 Output: 3
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());
        int connectionsLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> connections = new ArrayList<>();
        for (int i = 0; i < connectionsLength; i++) {
            connections.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        int res = trainRide(n, k, connections);
        System.out.println(res);
    }
}