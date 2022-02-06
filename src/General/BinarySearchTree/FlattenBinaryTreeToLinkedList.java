package General.BinarySearchTree;

import java.util.Stack;

/**
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 * 
 * Given the root of a binary tree, flatten the tree into a "linked list":
 * - The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
 * - The "linked list" should be in the same order as a pre-order traversal of the binary tree.
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
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 * 
 * Input: root = []
 * Output: []
 * 
 * Input: root = [0]
 * Output: [0]
 */
public class FlattenBinaryTreeToLinkedList {

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);

		printTree(root);

		System.out.println("");

		solution03(root);

		printTree(root);
	}

	public static void printTree(TreeNode root) {
		if (root == null) {
			System.out.print("X ");
			return;
		}

		System.out.print(root.val + " ");
		printTree(root.left);
		printTree(root.right);
	}

	/**
	 * Approach 1: Recursive.
	 * Time: O(n).
	 * Space: O(h).
	 */
	public static void solution01(TreeNode root) {
		if (root == null) {
			return;
		}

		solution01(root.left);
		solution01(root.right);

		TreeNode left = root.left;   // flattened left
		TreeNode right = root.right; // flattened right

		root.left = null;
		root.right = left;

		// connect the original right subtree
		// to the end of new right subtree
		TreeNode rightmost = root;

		while (rightmost.right != null) {
			rightmost = rightmost.right;
		}

		rightmost.right = right;
	}

	/**
	 * Approach 2: Iterative (stack).
	 * Time: O(n).
	 * Space: O(h).
	 */
	public static void solution02(TreeNode root) {
		if (root == null) {
			return;
		}

		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			root = stack.pop();

			if (root.right != null) {
				stack.push(root.right);
			}

			if (root.left != null) {
				stack.push(root.left);
			}

			if (!stack.isEmpty()) {
				root.right = stack.peek();
			}

			root.left = null;
		}
	}

	/**
	 * Approach 3: Morris-like.
	 * Time: O(n).
	 * Space: O(1).
	 */
	public static void solution03(TreeNode root) {
		if (root == null) {
			return;
		}

		while (root != null) {
			if (root.left != null) {
				// find the rightmost root
				TreeNode rightmost = root.left;
				
				while (rightmost.right != null)
					rightmost = rightmost.right;
				
				// rewire the connections
				rightmost.right = root.right;
				root.right = root.left;
				root.left = null;
			}
			
			// move on to the right side of the tree
			root = root.right;
		}
	}
}