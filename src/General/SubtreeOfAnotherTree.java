package General;

import General.SameTree.TreeNode;

/**
 * https://leetcode.com/problems/subtree-of-another-tree/
 * 
 * Given the roots of two binary trees root and subRoot, 
 * return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
 * A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. 
 * The tree tree could also be considered as a subtree of itself.
 * 
 * Example 01:
 * Input: root = [3,4,5,1,2], subRoot = [4,1,2]
 * Output: true
 * 
 * Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * Output: false
 */
public class SubtreeOfAnotherTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(4);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(2);
		root.right = new TreeNode(5);
		
		TreeNode subRoot = new TreeNode(4);
		subRoot.left = new TreeNode(1);
		subRoot.right = new TreeNode(2);
		
		System.out.println(isSubtree(root, subRoot));
	}

	public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
		if (root == null) {
			return false;
		} else if (isSameTree(root, subRoot)) {
			return true;
		} else {
			return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
		}
	}

	public static boolean isSameTree(TreeNode s, TreeNode t) {
		if (s == null || t == null) {
			return s == null && t == null;
		} else if (s.val == t.val) {
			return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
		} else {
			return false;
		}
	}

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
}