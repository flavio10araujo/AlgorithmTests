package General.Graph.MinimumSpanningTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * The Umbristan Department of Forestry(UDF) is tackling a rather difficult problem and the Umbristan government 
 * has detached you one of its best workers to go resolve the issue. 
 * When you arrive you are quickly briefed on the problem at hand. 
 * Inside the Umbristan National Park there exists an area that has been closed off as fencing needs to be erected in the area. 
 * The department for whatever reason needs to set up some very expensive fencing between the trees. 
 * The department has also set up some rules for the fence:
 * 1 - The fence needs to be set up such that every tree in the area is connected to the fence.
 * 2 - The department is on a very strict operating budget and so needs to minimize the metres of fencing required.
 * 3 - The department has counted the number of trees in the area 
 * 	   as well as determined all possible tree pairs where a fence can be built between the pair.
 * 
 * Can you help them figure out the smallest amount of fencing required?
 * It is possible that not all the nodes will be connected to one another depending on the tree pairs. 
 * Input will consist of trees the number of trees in the area labelled from 1 to n as well as pairs, 
 * a list consisting of the tuple [a, b, d] which denotes that a fence can be built between the trees a and b that will be d metres in length.
 * 
 * Constraints: 1 <= trees <= 10^5
 * 
 * Input: trees = 5, pairs = [[1, 2, 1], [2, 4, 2], [3, 5, 3], [4, 4, 4]]
 * Output: 6
 * Explanation: We can erect fencing between trees for the following pairs, 1 and 2, 2 and 4, 3 and 5. 
 * 			    With this every tree is connected by a fence and we have used 6 metres of fencing.
 * 
 * Solution:
 * 
 * The main idea is to run Kruskal's algorithm with some tweaked aspects. 
 * Instead of terminating when n - 1 nodes have been merged we are notified that it may not be possible to merge every node. 
 * Therefore, we simply just let the algorithm run until every edge is checked. 
 * One can also run prim's but make sure that every single node is calculated. 
 * Every connected component needs to have prim's algorithm run on it.
 */
public class Forests {

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

    public static int mstForest(int trees, List<List<Integer>> pairs) {
         // Sort list, make sure to define custom comparator class cmp to sort edge based on weight from lowest to highest.
         Collections.sort(pairs, new Comparator<List<Integer>>() {
        	 public int compare(List<Integer> a, List<Integer> b) {
        		 return a.get(2).compareTo(b.get(2));
        	 }
         });
         
         UnionFind<Integer> dsu = new UnionFind<Integer>();
         int ret = 0;
         
         for (int i = 0; i < pairs.size(); i++) {
             // Check if pairs belong to same set before merging and adding edge to mst (minimum spanning tree).
             int a = pairs.get(i).get(0);
             int b = pairs.get(i).get(1);
             int d = pairs.get(i).get(2);
             
             if (dsu.find(a) != dsu.find(b)) {
                 dsu.union(a, b);
                 ret += d;
             }
         }
         
         return ret;
     }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    /*
     5
	 4
	 1 2 1
	 2 4 2
	 3 5 3
	 4 4 4
	 
	 Output: 6
     */
    /*
     10
	 13
	 1 2 3
	 2 3 4
	 10 1 2
	 5 6 2
	 1 4 5
	 7 5 1
	 9 2 10
	 7 10 1
	 8 5 2
	 5 8 1
	 6 5 1
	 4 9 2
	 7 8 2
	 
	 Output: 20
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int trees = Integer.parseInt(scanner.nextLine());
        int pairsLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < pairsLength; i++) {
            pairs.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        int res = mstForest(trees, pairs);
        System.out.println(res);
    }
}