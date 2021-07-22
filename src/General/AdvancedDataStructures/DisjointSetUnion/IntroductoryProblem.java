package General.AdvancedDataStructures.DisjointSetUnion;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Now we will start with an introductory problem to get you familiar with the data structure. 
 * 
 * Complete the class below to support the following two operations:
 * 1 - merge(x, y) merges the sets that the x and y belong to.
 * 2 - is_same(x, y) determines if x and y belong to the same set. If so return true, otherwise false.
 * 
 * Example:
 * merge(1, 2)
 * merge(2, 3)
 * is_same(1, 3) => true
 * is_same(2, 4) => false
 * 
 * Explanation:
 * We merge elements 1 and 2 then we merge the set of 1 and 2 with the element 3, so we should have now have 2 sets, [1, 2, 3] and [4]. 
 * Therefore 1 and 3 are in the same set, while 2 and 4 are in different sets.
 * 
 * Solution
 * 
 * The solution here is relatively straightforward using what we discussed. 
 * The union operation has already been given to you and should be relatively straightforward to apply. 
 * We can check if two values belong to the same set by querying for the set id using the find operation, 
 * and they are in the same set if and only if the set ids are the same.
 * Time Complexity: O(nlog(n))
 * Where n is the number of nodes in our graph. 
 * Remember this can be improved using Union by rank to O(alpha(n)).
 */
public class IntroductoryProblem {

	public static class UnionFind {
		private Map<Integer, Integer> f = new HashMap<>();

        public Integer find(Integer x) {
            Integer y = f.getOrDefault(x, x);
            
            System.out.println("x="+x+" y="+y);
            
            if (y != x) {
                y = find(y);
                f.put(x, y);
            }
            
            return y;
        }

        public void union(Integer x, Integer y) {
            f.put(find(x), find(y));
        }
    }

    public static class SameSet {
        private UnionFind dsu = new UnionFind();

        public void merge(int x, int y) {
            dsu.union(x, y);
        }

        public boolean isSame(int x, int y) {
            return dsu.find(x) == dsu.find(y);
        }
    }

    // 4
    // union 1 2
    // union 2 3
    // is_same 1 3
    // is_same 1 4
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        SameSet sol = new SameSet();
        int queryLength = Integer.parseInt(scanner.nextLine());
        
        for (int i = 0; i < queryLength; i++) {
            String[] segs = scanner.nextLine().split(" ");
            String op = segs[0];
            int x = Integer.parseInt(segs[1]);
            int y = Integer.parseInt(segs[2]);
            
            if (op.equals("union")) {
                sol.merge(x, y);
            } else if (op.equals("is_same")) {
                boolean res = sol.isSame(x, y);
                System.out.println(res);
            }
        }
        
        scanner.close();
    }
}