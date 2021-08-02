package General.BinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Given a binary tree, invert it and return the new value. You may invert it in-place.
 * To "invert" a binary tree, switch the left subtree and the right subtree, and invert them both. 
 * Inverting an empty tree does nothing.
 * 
 * Input
 * 		tree: a binary tree that needs to be inverted.
 * Output
 * 		The inverted binary tree.
 * 
 * Input:
 * //        1
 * //       / \
 * //      2   3
 * //     / \   \
 * //    4   5   6
 * //     \
 * //      7
 * 
 * Output:
 * //        1
 * //       / \
 * //      3   2
 * //     / \   \
 * //    6   5   4
 * //            /
 * //           7
 */
public class InvertBinaryTree {

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

    public static Node<Integer> invertBinaryTree(Node<Integer> tree) {
        if (tree == null) {
            return null;
        }
        
        return new Node<>(tree.val, invertBinaryTree(tree.right), invertBinaryTree(tree.left));
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

    public static <T> void formatTree(Node<T> node, List<String> out) {
        if (node == null) {
            out.add("x");
            return;
        }
        out.add(node.val.toString());
        formatTree(node.left, out);
        formatTree(node.right, out);
    }

    // input: 1 2 4 x x 5 6 x x x 3 x x
    // output: 1 3 x x 2 5 x 6 x x 4 x x
    
    // input: 1 2 5 x x x 3 4 6 x x 7 x x x
    // output: 1 3 x 4 7 x x 6 x x 2 x 5 x x
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node<Integer> tree = buildTree(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
        scanner.close();
        Node<Integer> res = invertBinaryTree(tree);
        ArrayList<String> resArr = new ArrayList<>();
        formatTree(res, resArr);
        System.out.println(String.join(" ", resArr));
    }
}