package General.Others.CompositePatterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * It's been a month since you started working in Umbristan and all is going well. 
 * Like any developed nation Umbristan has been dabbling in new technologies and recently unveiled its newest invention the teleporter! 
 * The company heading the technology has also announced the locations where the teleporters will be placed. 
 * Some places will be at existing train stations, some at entirely new locations perhaps not serviceable by train. 
 * The company is charging a flat fare for use of any teleporter within one day so after the fee is paid 
 * you are free to use the teleporters as much as you want. 
 * The only disadvantage is that the teleporters only allow you to move to other teleporters. 
 * The teleporter company has also worked with the train company to place the teleporters such that 
 * it takes no time to move between trains and teleporters for the teleporters located at train stations. 
 * Another problem is that teleporters also have their own train station "lines". 
 * That is teleporters are given a numerical ID such that they can only teleport to other teleporters with the same ID. 
 * The teleporter company is still a start-up and trying to save money so each location has a maximum of 1 teleporter. 
 * The benefit is that this new technology may be able to improve your daily commute. 
 * You go back to the trusty map you drew up for Train Ride 2 and get to work planning your new transit route. 
 * Can you once again figure out the minimum price needed to get to your workplace on time?
 * 
 * The new input format is as follows,
 * 		The first line still denotes n the total number of teleporter locations and train stations. 
 * 			A teleporter can be located at a train station and is treated as 1 location.
 * 		The second line still denotes k the number of lines.
 * 		The third line still denotes 't' the time that you need to be at station n by starting at station 1 in minutes.
 * 		The fourth line now contains p the fare needed to use the teleporter for the day.
 * 		The fifth line now has the connections except now there are four values. 
 * 			First 2 values contains the connection, 
 * 			the 3rd value if the line number it belongs, 
 * 			the 4th value is the time needed to traverse this connection in minutes.
 * 		The sixth line now has a list of pairs that contains the teleporters. 
 * 			Each teleporter is denoted by the first value which is 
 * 				the location it is location at, 
 * 				and the second value which is its numerical ID indicating which teleporters it is connected to.
 * 
 * Output the minimum amount of money you need to spend such that you can make it to work on time.
 * Output -1 if no amount of money spend can get you to work on time.
 * 
 * Example 1:
 * Input:
 * n = 5, 
 * k = 2, 
 * t = 6, 
 * p = 2, 
 * connections = [[1, 2, 1, 1], [2, 3, 1, 2], [3, 4, 1, 3], [4, 5, 1, 4], [1, 5, 2, 7]], 
 * teleporters = [[1, 1], [5, 1], [2, 2]]
 * Output: 2
 * Explanation: Now there doesn't exist a way to use the trains to get to work on time 
 * 				but if we pay a fare of 2 we gain access to the teleporters and can get to work instantly.
 * 
 * Solution:
 * 
 * This problem throws an extra wrinkle into Train Ride 2 where now we have to account for teleporters. 
 * In fact, we can build off of our Train Ride 2 solution which you may want to go back and quickly reference. 
 * The basic idea remains the same which is to do binary search while checking with Dijkstra's for a particular price. 
 * The question we have left is to account for the teleporters. 
 * The main idea is to build 2 different graphs, one with the teleporter and one without the teleporter. 
 * Since transportation between the teleporter locations is instant we want to merge all the teleporters that belong to one numerical ID. 
 * How do we merge the nodes? 
 * We use a disjoint set data structure to merge the nodes and treat the Set ID node as the only node 
 * in the teleporter graph for all teleporters under 1 numerical ID. 
 * Now when we perform binary search we check 2 conditions. 
 * We check if we can reach our workplace with the teleporter and we check without the teleporter and see which one yields a better result. 
 * Remember that if the price of the teleporter is less than our current price then we can use the remaining money to buy a train ticket. 
 * For example, if our binary search is checking the price of 9 and the teleporter fare is 5 then we can buy a train ticket with price 4. 
 * We do not need to worry about merging 2 different teleporter sets as each location has a maximum of 1. 
 * An alternative solution is to make dummy nodes and connect all teleporters with the same numerical ID with a dummy node with edges with time 0. 
 * This effectively has the same result as we have effectively merged all the nodes by having them all be connected 
 * to a node such that it takes 0 time.
 * Time Complexity: O(nlog(e)log(k))
 */
public class TrainRide3 {

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

    // class to hold the values for an edge, we want to store destination node, the line it belongs to and the time to traverse the edge
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

    // function to check if a valid path exists with the given price and time restriction
    public static boolean check (List<Edge> graph[], int price, int t, boolean teleporterCheck, UnionFind<Integer> dsu, int n) {
        // check if we are on teleporter graph, if we are and price is less than equal to 0 simply return
        if (teleporterCheck && price < 0) return false;
        // use dijkstra's algorithm by making the priority queue, we can reuse our edge class
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(1001, cmp);
        // make a distance array and initialize to -1
        int [] dis = new int[graph.length];
        Arrays.fill(dis, -1);

        // if we are on teleporter graph make sure to add SetID node to queue
        if (!teleporterCheck) {
            pq.add(new Edge(1, 0, 0));
            dis[1] = 0;
        }
        else {
            pq.add(new Edge(dsu.find(1), 0 , 0));
            dis[dsu.find(1)] = 0;
        }

        // apply dijkstra's algorithm
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            // if we have reached destination node we can stop
            if ((!teleporterCheck && cur.dest == n) || (teleporterCheck && cur.dest == dsu.find(n))) break;
            // if our current distance is greater than established distance then we should stop
            if (cur.time > dis[cur.dest]) continue;
            // loop through adjacent connections
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
        if (!teleporterCheck) return dis[n] <= t && dis[n] != -1;
        else return dis[dsu.find(n)] <= t && dis[dsu.find(n)] != -1;
    }

    public static int trainRide3 (int n, int k, int t, int p, List<List<Integer>> connections, List<List<Integer>> teleporters) {
        // initialize the graph
        List<Edge> [] noTeleporterGraph = new ArrayList[n + 1];
        List<Edge> [] teleporterGraph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++){
            noTeleporterGraph[i] = new ArrayList<Edge>();
            teleporterGraph[i] = new ArrayList<Edge>();
        }

        // merge the nodes in the graph, that belong in under 1 teleporter ID
        UnionFind<Integer> dsu = new UnionFind<Integer>();
        int [] teleporterID = new int[n+1];
        // loop through teleporter, making sure to merge all teleporters under 1 numerical ID
        for(List<Integer> teleporter : teleporters) {
            // first time we encounter a node in a particular teleporter ID we remember it for later
            if (teleporterID[teleporter.get(1)] == 0) {
                teleporterID[teleporter.get(1)] = teleporter.get(0);
            }
            else {
                dsu.union(teleporterID[teleporter.get(1)], teleporter.get(0));
            }
        }

        // revise graph to initialize both teleporter and non-teleporter graph
        for (List<Integer> connection : connections) {
            noTeleporterGraph[connection.get(0)].add(new Edge(connection.get(1), connection.get(2), connection.get(3)));
            noTeleporterGraph[connection.get(1)].add(new Edge(connection.get(0), connection.get(2), connection.get(3)));
            teleporterGraph[dsu.find(connection.get(0))].add(new Edge(dsu.find(connection.get(1)), connection.get(2), connection.get(3)));
            teleporterGraph[dsu.find(connection.get(1))].add(new Edge(dsu.find(connection.get(0)), connection.get(2), connection.get(3)));
        }

        // apply binary search on the k value
        int low = 1;
        int high = k;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            // check if our current ticket price can match the time limit, if it can try a lower ticket price, otherwise increase price
            if (check(noTeleporterGraph, mid, t, false, dsu, n) || check(teleporterGraph, mid - p, t, true, dsu, n)) {
                high = mid - 1;
                ans = mid;
            }
            else {
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
	 2
	 5
	 1 2 1 1
	 2 3 1 2
	 3 4 1 3
	 4 5 1 4
	 1 5 2 7
	 3
	 1 1
	 5 1
	 2 2
	 Output: 2
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());
        int t = Integer.parseInt(scanner.nextLine());
        int p = Integer.parseInt(scanner.nextLine());
        int connectionsLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> connections = new ArrayList<>();
        
        for (int i = 0; i < connectionsLength; i++) {
            connections.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        
        int teleporterLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> teleporter = new ArrayList<>();
        
        for (int i = 0; i < teleporterLength; i++) {
            teleporter.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        
        scanner.close();
        int res = trainRide3(n, k, t, p, connections, teleporter);
        System.out.println(res);
    }
}