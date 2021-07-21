package General.DFS;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * A balanced binary tree is defined as a tree such that either it is an empty tree, 
 * or both its subtree are balanced and has a height difference of at most 1.
 * 
 * In that case, given a binary tree, determine if it's balanced.
 * 
 * Parameter
 * tree: A binary tree.
 * Result: A boolean representing whether the tree given is balanced.
 * 
 * Example 1
 * Input:
 * 				    1
 *                 / \
 *                2   3
 *               / \   \
 *              4   5   6
 *              \
 *               7
 * 
 * Output: true
 * 
 * Example 2
 * Input:
 * 				    1
 *                 / \
 *                2   3
 *               / \   \
 *              4   5   6
 *              \      /
 *               7    8
 * 
 * Output: false
 * Explanation: The subtrees of the node labelled 3 has a height difference of 2, so it is not balanced.
 * 
 * Solution
 * 
 * This question can be solved using a post-order traversal on the tree. 
 * To find whether a tree is balanced, and to find out about its height, we look at the two subtrees 
 * and see whether they are balanced, and if so, their height. 
 * If one of the subtree is unbalanced, this tree is unbalanced. 
 * Otherwise, if the height difference between the trees is greater than 1, the tree is unbalanced. 
 * Otherwise, the tree is balanced by definition, and the height is the max height between the two subtrees plus 1.
 * We have established the recursion logic. 
 * For the base case, we assume the empty subtree has a height of 0.
 * 
 * Implementation
 * 
 * Now let's think like a node and apply our two-step formula:
 * 1. Return value
 * 		We want to return the height of the current tree to the parents so that current node's parent 
 * 		can decide whether its subtrees' height difference is no more than 1.
 * 2. Identify states(s)
 * 		To decide whether current node's subtree is balanced we do not need any information other 
 * 		than the height for each subtree returned from each child's recursive call. 
 * 		Therefore no additional state is needed.
 * 
 * The time complexity is O(n), where n is the number of nodes in this tree.
 */
public class BalancedBinaryTree {

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

    // Returns -1 if is not a binary tree.
	// The height if it is.
    public static int treeHeight(Node<Integer> tree) {
        if (tree == null) {
            return 0;
        }
        
        int leftHeight = treeHeight(tree.left);
        int rightHeight = treeHeight(tree.right);
        
        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }
        
        if (Math.abs(leftHeight - rightHeight) > 1 ) {
            return -1;
        }
        
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static boolean isBalanced(Node<Integer> tree) {
        return treeHeight(tree) != -1;
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
        Node<Integer> tree = buildTree(splitWords("1 2 4 x 7 x x 5 x x 3 x 6 x x").iterator(), Integer::parseInt);
        boolean res = isBalanced(tree);
        System.out.println(res);
    }
}