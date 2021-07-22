package General.AdvancedDataStructures.DisjointSetUnion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * You have recently been promoted as the director of infrastructure of Umbristan. Congratulations! 
 * Our Duke Stack Umbristan has recently ordered the destruction of all the roads of Umbristan so that new ones can be rebuilt. 
 * Our Duke has assigned you the director of infrastructure an important task. 
 * After each road in Umbristan is demolished he wants to know how many clusters of cities will still be connected after the road is destroyed. 
 * 
 * Two cities are considered connected if there exists a series of roads one can take to travel to reach one city from another. 
 * A cluster of cities being connected means that for any 2 cities in the cluster there exists a path between the 2 cities. 
 * 
 * The input will first consist of the number n which is the number of cities numbered from 1 to n. 
 * Then there will exist a list of roads that represent the road connecting 2 cities is now broken. 
 * Each road in this list is guaranteed to be a valid existing road. 
 * The final result will be guaranteed to have no roads (since all roads must be destroyed).
 * 
 * Examples
 * Input: n = 4, breaks = [[1, 2], [2, 3], [3, 4], [1, 4], [2, 4]]
 * Output: [1, 1, 2, 3, 4] // the number of separated sets after each operation.
 * 
 * Solution
 * 
 * The main idea is to work backwards from the broken graph and maintain a disjoint-set union data structure. 
 * It is a lot easier to connect edges than it is to break edges and so we remember all our answers 
 * while working backwards and return a reverse of that list. 
 * There does in fact exist a data structure that can maintain several disjoint-set union data structures called a Link Cut Tree. 
 * You do not need to know this data structure for interviews but it may be a subject of interest for further reading.
 * Time Complexity: O(nlog(n)).
 */
public class ReverseUnionFind {

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

    public static List<Integer> umbristan(int n, List<List<Integer>> breaks) {
        // Initialize data structures.
        UnionFind<Integer> dsu = new UnionFind<>();
        List<Integer> ret = new ArrayList<>();
        Collections.reverse(breaks);
        
        // Loop through the reverse list and merge the edges if they are not of the same list.
        for (List<Integer> edge : breaks) {
            ret.add(n);
            
            // Merging 2 connected components means total number of connected components decreases by 1.
            if (dsu.find(edge.get(0)) != dsu.find(edge.get(1))) {
                dsu.union(edge.get(0), edge.get(1));
                n--;
            }
        }
        
        // Remember that our answers are in reverse since we started from the end.
        Collections.reverse(ret);
        
        return ret;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 4
    // 5
    // 1 2
    // 2 3
    // 3 4
    // 1 4
    // 2 4    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int breaksLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> breaks = new ArrayList<>();
        
        for (int i = 0; i < breaksLength; i++) {
            breaks.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        
        scanner.close();
        List<Integer> res = umbristan(n, breaks);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}