package General.BinarySearchTree;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/**
 * A binary search tree is a binary tree with the property that any of its node's value is greater than or equal to any node in its left subtree 
 * and less than or equal to any node's value in its right subtree.
 * 
 * Given a binary tree, determine whether it is a binary search tree.
 * 
 * Input:
 * //        6
 * //       / \
 * //      4   8
 * //     / \
 * //    3   5
 * Output: True.
 * 
 * Input:
 * //        6
 * //       / \
 * //      4   8
 * //     / \
 * //    3   8
 * Output: False.
 */
public class ValidBinarySearchTree {

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

    private static boolean dfs(Node<Integer> root, int min, int max) {
        // Empty nodes are always valid.
        if (root == null) {
        	return true;
        }
        
        if (!(min <= root.val && root.val <= max)) {
            return false;
        }
        
        return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
    }

    public static boolean validBst(Node<Integer> root) {
        // Root is always valid.
        return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
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

    // 6 4 3 x x 5 x x 8 x x = true
    // 6 4 3 x x 8 x x 8 x x = false
    // 1 2 x x 3 x x = false
    // 7 7 7 x x x 7 x 7 x x = true
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node<Integer> root = buildTree(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
        scanner.close();
        boolean res = validBst(root);
        System.out.println(res);
    }
}