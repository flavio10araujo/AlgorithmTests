package General.BFS;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * Given a binary tree, find the depth of the shallowest leaf node.
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
 * 
 * Output: 6
 */
public class MinDepth {

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

    public static int binaryTreeMinDepth(Node<Integer> root) {
        
        if (root == null) {
            return 0;
        }
        
        int minDepth = -1;
        
        Deque<Node<Integer>> queue = new ArrayDeque<>();
        
        queue.add(root);
        
        while (queue.size() > 0) {
            
        	minDepth++;
            int n = queue.size();
            
            for (int i = 0; i < n; i++) {
                Node<Integer> node = queue.pop();

                if (node.left == null && node.right == null) {
                    queue.clear();
                    i = n;
                } else {
                    if (node.left != null) {
                        queue.add(node.left);
                    }

                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
        }
        
        return minDepth;
    
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
        Node<Integer> root = buildTree(splitWords("1 2 4 x 7 x x 5 x x 3 x 6 x x").iterator(), Integer::parseInt);
        int res = binaryTreeMinDepth(root);
        System.out.println(res);
    }
}