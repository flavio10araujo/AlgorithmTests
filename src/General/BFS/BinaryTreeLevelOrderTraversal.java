package General.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Given a binary tree, return its level order traversal. 
 * Input is the root node of the tree. 
 * The output should be a list of lists of integers, with the ith list containing the values of nodes on level i, from left to right.
 * 
 * For example:
 * Input:
 *     1
 *    / \
 *   2   3
 *  / \   \
 * 4   5   6
 *  \
 *   7
 * Output:
 * [[1], [2, 3], [4, 5, 6], [7]]
 */
public class BinaryTreeLevelOrderTraversal {

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

    public static List<List<Integer>> levelOrderTraversal(Node<Integer> root) {
    	List<List<Integer>> res = new ArrayList<>();
    	if (root == null) return res;
    	
    	Deque<Node<Integer>> queue = new ArrayDeque<>();
    	queue.add(root);  // at least one element in the queue to kick start bfs
    	
    	while (queue.size() > 0) {  // as long as there is element in the queue
    		int n = queue.size();
    		List<Integer> newLevel = new ArrayList<>();
    		
    		// dequeue each node in the current level
            for (int i = 0; i < n; i++) {
            	Node<Integer> node = queue.pop();
            	newLevel.add(node.val);
            	// enqueue non-null children
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            
            res.add(newLevel);
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
        Node<Integer> root = buildTree(splitWords("1 2 4 7 x x x 5 x x 3 6 x x x").iterator(), Integer::parseInt);
        List<List<Integer>> res = levelOrderTraversal(root);
        
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}