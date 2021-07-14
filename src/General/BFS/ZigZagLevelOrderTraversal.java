package General.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Given a binary tree, return its level order traversal but alternate left to right order.
 * 
 * For example:
 * Input:
 *     1
 *    / \
 *   2   3
 *  / \   \
 * 4   5   6
 *  \   \
 *   7   8
 * Output:
 * [[1], [3, 2], [4, 5, 6], [8, 7]]
 */
public class ZigZagLevelOrderTraversal {
	public static class Node<T> {
        public T val;
        public Node<T> left;
        public Node<T> right;

        public Node(T val) {
            this(val, null, null);
        }

        public Node(T val, Node<T> left, Node<T> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static List<List<Integer>> zigZagTraversal(Node<Integer> root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        if (root == null) {
            return res;
        }
        
        Deque<Node<Integer>> queue = new ArrayDeque<>();
        queue.add(root);
        
        boolean normalOrder = true;
        
        while (queue.size() > 0) {
            
            int n = queue.size();
            List<Integer> line = new ArrayList<Integer>();
            
            for (int i = 0; i < n; i++) {
                
                Node<Integer> node = queue.pop();
                line.add(node.val);
                
                if (node.left != null) {
                    queue.add(node.left);
                }
                
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            
            if (!normalOrder) {
                Collections.reverse(line);
            }
            
            res.add(line);
            
            normalOrder = !normalOrder;
        }
        
        return res;
    }

    public static <T> Node<T> buildTree(Iterator<String> iter, Function<String, T> f) {
        String val = iter.next();
        if (val.equals("x")) return null;
        Node<T> left = buildTree(iter, f);
        Node<T> right = buildTree(iter, f);
        return new Node<T>(f.apply(val), left, right);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }
    
    public static void main(String[] args) {
        Node<Integer> root = buildTree(splitWords("1 2 4 x 7 x x 5 x 8 x x 3 x 6 x x").iterator(), Integer::parseInt);
        List<List<Integer>> res = zigZagTraversal(root);
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}