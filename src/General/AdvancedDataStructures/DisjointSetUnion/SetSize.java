package General.AdvancedDataStructures.DisjointSetUnion;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * For this problem we now ask you to do something similar to the introductory problem, 
 * but this time instead of checking whether or not 2 values belong to the same set, 
 * we want to know the size of the set that a value belongs to. 
 * 
 * Therefore, we now support a different set of 2 operations:
 * 1. merge(x, y) merge the sets that x and y belong to.
 * 2. count(x) returns the size of the set that x belongs to.
 * 
 * Example:
 * merge(1, 2)
 * merge(2, 3)
 * count(3) => 3
 * count(4) => 1
 * Explanation:
 * We merge elements 1 and 2 then we merge the set of 1 and 2 with the element 3, so we should have now have 2 sets, [1, 2, 3] and [4]. 
 * Therefore the set that 3 belongs to contains 3 elements, while the set that 4 belongs to contains 1 element.
 * 
 * Solution
 * 
 * This is similar to the introductory problem. 
 * The key observation is that we can keep track of the size of each set by its set id, 
 * and when we merge two sets we update the size to be the sum of the set sizes of the two values.
 * Time Complexity: O(nlog(n))
 */
public class SetSize {

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

    public static class SetCounter {
        private UnionFind<Integer> dsu = new UnionFind<>();
        private Map<Integer, Integer> sizes = new HashMap<>();

        public void merge(int x, int y) {
            int newSize = count(x) + count(y);
            dsu.union(x, y);
            sizes.put(dsu.find(x), newSize);
        }

        public int count(int x) {
            return sizes.getOrDefault(dsu.find(x), 1);
        }
    }

    // 4
    // union 1 2
    // union 2 3
    // count 3
    // count 4
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SetCounter sol = new SetCounter();
        int queryLength = Integer.parseInt(scanner.nextLine());
        
        for (int i = 0; i < queryLength; i++) {
            String[] segs = scanner.nextLine().split(" ");
            String op = segs[0];
            int x = Integer.parseInt(segs[1]);
            
            if (op.equals("union")) {
                int y = Integer.parseInt(segs[2]);
                sol.merge(x, y);
            } else if (op.equals("count")) {
                int res = sol.count(x);
                System.out.println(res);
            }
        }
        
        scanner.close();
    }
}