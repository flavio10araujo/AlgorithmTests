package General.Others.CompositePatterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * You have carefully planned out the minimum ticket price you have to pay but you forgot one important fact, are you going to make it to work on time? 
 * After considering this, you hurry to change your ticket plans as now you need to not only buy the cheapest ticket 
 * but also make sure you get to work on time. 
 * You quickly whip out your map and now mark each connection in the subway with a time, the time it takes to ride between those 2 stations.
 * 
 * The new input format is as follows,
 * 		The first input still denotes n the number of stations.
 * 		The second input still denotes k the number of lines.
 * 		The third input now denotes 't' the time that you need to be at station n by starting at station 1 in minutes.
 * 		The fourth linput now has the connections except now there are four values.
 * 			First 2 values contains the connection, 
 * 			the 3rd value is the line number it belongs, 
 * 			the 4th value is the time needed to traverse this connection in minutes.
 * 
 * Output the lowest ticket cost such that you can make it to work on time.
 * Output -1 if no such ticket can be bought to get you to work on time.
 * 
 * Example 1:
 * Input:
 * n = 5, 
 * k = 2, 
 * t = 6, 
 * connections = [[1, 2, 1, 1], [2, 3, 1, 2], [3, 4, 1, 3], [4, 5, 1, 4], [1, 5, 2, 6]]
 * Output: 2
 * Explanation: If you only use the connections on line 1 there exists a path to your workplace but you will be late to work. 
 * 			    The total time would be 1 + 2 + 3 + 4 = 10 minutes but you only have 6 minutes. 
 * 				By buying a ticket with price 2 you can take the connection [1, 5, 2, 6] 
 * 				which will take only 6 minutes and therefore allow you to arrive at work on time.
 * 
 * Solution:
 * 
 * Naive Solution:
 * The naive solution is to try every value of k and see if there exists a path between your home and workplace 
 * until you find a viable path before you stop using a graph traversal algorithm such as Dijkstra's algorithm. 
 * Let e denote the number of edges in the graph.
 * Time Complexity: O(log(e)k))
 * 
 * Optimized Solution:
 * A more optimized solution is to use binary search on the lowest price k whilst using Dijkstra to check. 
 * We know for a fact that by buying more expensive tickets our time will go down. 
 * We can imagine each ticket price containing its own graph and increasing 
 * the price preserves the previous connections while adding more connections. 
 * There will eventually come a ticket price where every price thereafter will be guaranteed to get you to work on time. 
 * From here we can observer a monotonic property on the ticket prices from which we can binary search on. 
 * We can then use Dijkstra's algorithm to check if the given ticket price will get us to work on time. 
 * If it does not we increase our ticket price otherwise, we check a lower price.
 * Time Complexity: O(nlog(e)log(k))
 */
public class TrainRide2 {

	// Class to hold the values for an edge, we want to store destination node, the line it belongs to and the time to traverse the edge.
    public static class Edge {
        int dest;
        int line;
        int time;
        
        public Edge (int dest, int line, int time) {
            this.dest = dest;
            this.line = line;
            this.time = time;
        }
    }

    // custom comparator class for our Edge class, we want our priority queue to be in ascending order so lowest is front of the queue
    public static Comparator<Edge> cmp = (a, b) -> b.time - a.time;

    // Function to check if a valid path exists with the given price and time restriction.
    public static boolean check(List<Edge> graph[], int price, int t) {
        // Use dijkstra's algorithm by making the priority queue, we can reuse our edge class.
        Queue<Edge> pq = new PriorityQueue<Edge>(1001, cmp);
        
        // Make a distance array and initialize to -1.
        int [] dis = new int[graph.length];
        
        Arrays.fill(dis, -1);
        dis[1] = 0;
        pq.add(new Edge(1, 0, 0));
        
        int n = graph.length - 1;

        // Apply dijkstra's algorithm.
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            
            // If we have reached destination node we can stop.
            if (cur.dest == n) break;
            
            // If our current distance is greater than established distance then we should stop.
            if (cur.time > dis[cur.dest]) continue;
            
            // Loop through adjacent connections.
            for (Edge connection : graph[cur.dest]) {
                // check if the connection is allowed for out price restriction
                if(connection.line > price) continue;
                if(dis[connection.dest] == -1 || dis[connection.dest] > dis[cur.dest] + connection.time) {
                    // update distance array and priority queue
                    dis[connection.dest] = dis[cur.dest] + connection.time;
                    pq.add(new Edge(connection.dest, 0, dis[connection.dest]));
                }
            }
        }
        
        return dis[n] <= t && dis[n] != -1;
    }

    public static int trainRide2 (int n, int k, int t, List<List<Integer>> connections) {
        // Initialize the graph.
        List<Edge> [] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++){
            graph[i] = new ArrayList<Edge>();
        }

        for (List<Integer> connection : connections) {
            graph[connection.get(0)].add(new Edge(connection.get(1), connection.get(2), connection.get(3)));
            graph[connection.get(1)].add(new Edge(connection.get(0), connection.get(2), connection.get(3)));
        }

        // Apply binary search on the k value.
        int low = 1;
        int high = k;
        int ans = -1;
        
        while (low <= high) {
            int mid = (low + high) / 2;
            
            // Check if our current ticket price can match the time limit, if it can try a lower ticket price, otherwise increase price.
            if (check(graph, mid, t)) {
                high = mid - 1;
                ans = mid;
            } else {
                low = mid + 1;
            }
        }
        
        return ans;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    /*
     5
	 2
	 6
	 5
	 1 2 1 1
	 2 3 1 2
	 3 4 1 3
	 4 5 1 4
	 1 5 2 6
	 Output: 2
     */
    /*
     6
	 4
	 15
	 10
	 1 2 1 1
	 2 3 2 1
	 3 5 1 1
	 5 4 2 2
	 4 5 2 2
	 4 5 4 4
	 1 6 3 15
	 5 2 3 2
	 3 2 5 1
	 1 2 3 4
	 Output: 3
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());
        int t = Integer.parseInt(scanner.nextLine());
        int connectionsLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> connections = new ArrayList<>();
        for (int i = 0; i < connectionsLength; i++) {
            connections.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        int res = trainRide2(n, k, t, connections);
        System.out.println(res);
    }
}