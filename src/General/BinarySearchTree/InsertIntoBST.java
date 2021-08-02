package General.BinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Given the root node of a valid BST and a value to insert into the tree, 
 * return a new root node representing the valid BST with the addition of the new item. 
 * If the new item already exists in the binary search tree, do not insert anything.
 * 
 * You must expand on the original BST by adding a leaf node. Do not change the structure of the original BST.
 * 
 * Input
 * 		bst: a binary tree representing the existing BST.
 * 		val: an integer representing the value to be inserted.
 * Output
 * 		A valid BST with the inserted number, or the same BST if the number already exists.
 * 
 * Input:
 * Number: 7
 * Tree:
 * //        8
 * //       / \
 * //      5   10
 * //     / \   \
 * //    2   6   14
 * //     \
 * //      3
 * 
 * Output:
 * //        8
 * //       / \
 * //      5   10
 * //     / \   \
 * //    2   6   14
 * //     \   \
 * //      3   7
 */
public class InsertIntoBST {

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

    public static Node<Integer> insertBst(Node<Integer> bst, int val) {
        if (bst == null) {
            return new Node<>(val);
        }
        
        int compareVal = bst.val.compareTo(val);
        
        if (compareVal < 0) {
            bst.right = insertBst(bst.right, val);
        } else if (compareVal > 0) {
            bst.left = insertBst(bst.left, val);
        }
        
        return bst;
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

    // 8 4 2 1 x x 3 x x 6 x x 12 10 x x 14 x 15 x x
    // 7
    // output: 8 4 2 1 x x 3 x x 6 x 7 x x 12 10 x x 14 x 15 x x
    
    // 37 19 2 x x 28 23 x x 35 x x 44 x 58 52 x x 67 x x
    // 42
    // output: 37 19 2 x x 28 23 x x 35 x x 44 42 x x 58 52 x x 67 x x 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node<Integer> bst = buildTree(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
        int val = Integer.parseInt(scanner.nextLine());
        scanner.close();
        Node<Integer> res = insertBst(bst, val);
        ArrayList<String> resArr = new ArrayList<>();
        formatTree(res, resArr);
        System.out.println(String.join(" ", resArr));
    }
}