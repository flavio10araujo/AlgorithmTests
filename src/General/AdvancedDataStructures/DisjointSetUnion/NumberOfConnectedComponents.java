package General.AdvancedDataStructures.DisjointSetUnion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * For this question we start with n nodes in a graph that are all independent of each other. 
 * We will then begin connecting nodes in the graph. 
 * After each connection connecting two different nodes we ask you to compute the number of connected components in a graph. 
 * A connected component is a series of node such that there exists a path between any two nodes in the graph. 
 * Nodes will be 0-indexed and will be integers from 0 to n - 1.
 * 
 * Example
 * Input: n = 5, connections = [[1, 2], [2, 3], [1, 3], [0, 4], [0, 4]]
 * Output: [4, 3, 3, 2, 2] // the number of separated sets after each operation.
 * 
 * Solution
 * 
 * The basic idea is that we use a disjoint-union set data structure.
 * We realize that if we merge two sets that don't have the same parent that the number of connected components decreases by 1.
 * Time Complexity: O(nlog(n))
 */
public class NumberOfConnectedComponents {

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

    public static List<Integer> numberOfConnectedComponents(int n, List<List<Integer>> connections) {
        // DSU initialization, this should look familiar.
        List<Integer> ret = new ArrayList<Integer>();
        int connectedComponents = n;
        UnionFind<Integer> dsu = new UnionFind<>();
        
        for (List<Integer> connection : connections) {
            // Check if they are part of the same set if not merge and decrease connected components by 1.
            if (dsu.find(connection.get(0)) != dsu.find(connection.get(1))) {
                dsu.union(connection.get(0), connection.get(1));
                connectedComponents--;
            }
            
            ret.add(connectedComponents);
        }
        
        return ret;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 5
    // 5
    // 1 2
    // 2 3
    // 1 3
    // 0 4
    // 0 4 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int connectionsLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> connections = new ArrayList<>();
        
        for (int i = 0; i < connectionsLength; i++) {
            connections.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        
        scanner.close();
        List<Integer> res = numberOfConnectedComponents(n, connections);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}