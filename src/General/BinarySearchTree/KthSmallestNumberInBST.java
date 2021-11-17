package General.BinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * 
 * Given the root node of a valid BST and a number k, return the kth smallest number in this BST (1-indexed).
 * 
 * Input
 * 		bst: a binary tree representing the existing BST.
 * 		k: an integer.
 * Output
 * 		The kth smallest number in bst.
 * 
 * Input:
 * number k = 4
 * Tree:
 * //        8
 * //       / \
 * //      5   10
 * //     / \   \
 * //    2   6   14
 * //     \
 * //      3
 * 
 * Output: 6
 * 
 * Constraints
 * 		1 <= k <= n <= 10^5, where n is the size of bst.
 * 
 * Solution
 * 
 * To find the kth smallest element in a BST is simple. 
 * Consider the root node of a BST: because everything in the left subtree is smaller and everything in the right tree is larger, 
 * the root node is the m + 1th largest value in the BST, where m is the size of the left subtree.
 * In that case, we can compare this number to k. 
 * If they are equal, then the root is the value we want. 
 * If it is smaller, we look in the right subtree and find the k - m - 1th smallest value there (as everything there is larger than the root and the left subtree). 
 * If it's larger, we look in the left subtree and find the kth smallest value there.
 * Note that because the implementation of binary trees does not include the size, the calculation of the size is linked to each recursion cycle. 
 * The time complexity of calculating size of a tree without prior knowledge about the tree size is O(n), where n is the size of the tree. 
 * In addition, the code terminates when it finds the kth largest element, so the overall time complexity is O(k).
 */
public class KthSmallestNumberInBST {

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

	public static int val = -1;

	// 8 4 2 1 x x 3 x x 6 x x 12 10 x x 14 x 15 x x

	// 37 19 2 x x 28 23 x x 35 x x 44 x 58 52 x x 67 x x
	// 8
	// output: 52
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Node<Integer> bst = buildTree(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
		int k = Integer.parseInt(scanner.nextLine());
		scanner.close();

		long startTime = System.nanoTime();
		System.out.println(solution01(bst, k));
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);

		startTime = System.nanoTime();
		System.out.println(solution02(bst, k));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);

		startTime = System.nanoTime();
		System.out.println(solution03(bst, k));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	}

	/**
	 * Approach: Binary Search
	 * Time complexity: O(n ^ 2).
	 * Space complexity: O(h).
	 * @param bst
	 * @param k
	 * @return
	 */
	public static int solution01(Node<Integer> bst, int k) {
		final int leftCount = countNodes(bst.left);

		if (leftCount == k - 1)
			return bst.val;

		if (leftCount >= k)
			return solution01(bst.left, k);

		return solution01(bst.right, k - 1 - leftCount); // leftCount < k
	}

	private static int countNodes(Node<Integer> root) {
		if (root == null)
			return 0;

		return 1 + countNodes(root.left) + countNodes(root.right);
	}

	/**
	 * In Order Traversal.
	 * Time complexity: O(n).
	 * Space complexity: O(n).
	 * @param bst
	 * @param k
	 * @return
	 */
	public static int solution02(Node<Integer> bst, int k) {
		List<Integer> nums = new ArrayList<>();

		inorder(bst, nums, k);

		return nums.get(k - 1);
	}

	public static void inorder(Node<Integer> node, List<Integer> nums, int k) {
		if (node == null || nums.size() == k) {
			return;
		}

		inorder(node.left, nums, k);
		nums.add(node.val);
		inorder(node.right, nums, k);
	}

	/**
	 * Approach with a stack.
	 * Time complexity: O(n).
	 * Space complexity: O(k).
	 * @param bst
	 * @param k
	 * @return
	 */
	public static int solution03(Node<Integer> bst, int k) {
		Stack<Node<Integer>> stack = new Stack<>();

		while (bst != null) {
			stack.push(bst);
			bst = bst.left;
		}

		for (int i = 0; i < k - 1; ++i) {
			bst = stack.pop();
			bst = bst.right;

			while (bst != null) {
				stack.push(bst);
				bst = bst.left;
			}
		}

		return stack.peek().val;
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
}