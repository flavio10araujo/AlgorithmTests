package General.BinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Given a binary tree, return a linked list that is a "flattened" version of the tree.
 * The linked list still uses the same nodes as a normal binary tree, 
 * only the left subtree is always empty, and the right subtree always points to the next element in the linked list (or the empty tree).
 * The flattened tree represents the pre-order traversal of the tree.
 * 
 * Input
 * 		tree: the binary tree to be flattened.
 * Output
 * 		A tree representing the flattened binary tree.
 * 
 * Input:
 * //        1
 * //       / \
 * //      2   3
 * //     / \   
 * //    4   5
 * Output:
 * 1 -> 2 -> 3 -> 4 -> 5
 * 
 * Solution
 * 
 * 1. Decide on the return value
 * Since we are building the list backwards to form a pre-order traversal, 
 * the return value is the linked list representing the pre-order traversal of the current subtree, followed by the existing linked list.
 * 2. Identify states
 * To build up the linked list backwards, we add the flattened right subtree before the existing linked linked list, 
 * then the flattened left tree, and finally the current node.
 */
public class FlattenBinaryTreeToLinkedList {

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

    public static Node<Integer> flattenTreeToStub(Node<Integer> tree, Node<Integer> existingList) {
        if (tree == null) {
            return existingList;
        }
        
        existingList = flattenTreeToStub(tree.right, existingList);
        existingList = flattenTreeToStub(tree.left, existingList);
        
        return new Node<Integer>(tree.val, null, existingList);
    }

    public static Node<Integer> flattenTree(Node<Integer> tree) {
        return flattenTreeToStub(tree, null);
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

    // 1 2 4 x x 5 x x 3 x x
    // output: 1 x 2 x 4 x 5 x 3 x x
    
    // 1 2 4 x x 5 6 x x x 3 x x
    // output: 1 x 2 x 4 x 5 x 6 x 3 x x
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node<Integer> tree = buildTree(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
        scanner.close();
        Node<Integer> res = flattenTree(tree);
        ArrayList<String> resArr = new ArrayList<>();
        formatTree(res, resArr);
        System.out.println(String.join(" ", resArr));
    }
}