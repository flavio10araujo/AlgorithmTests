package General;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/balance-a-binary-search-tree/
 * 
 * Given the root of a binary search tree, return a balanced binary search tree with the same node values. 
 * If there is more than one answer, return any of them.
 * A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.
 * 
 * Example 1:
 * Input: root = [1,null,2,null,3,null,4,null,null]
 * Output: [2,1,3,null,null,null,4]
 * Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.
 * 
 * Example 2:
 * Input: root = [2,1,3]
 * Output: [2,1,3]
 * 
 * Constraints:
 * The number of nodes in the tree is in the range [1, 104].
 * 1 <= Node.val <= 105
 */
public class BalanceABinarySearchTree {

	public static void main(String[] args) {
		TreeNode bst = new TreeNode(1);
		bst.right = new TreeNode(2);
		bst.right.right = new TreeNode(3);
		bst.right.right.right = new TreeNode(4);
		bst.right.right.right.right = new TreeNode(5);
		bst.right.right.right.right.right = new TreeNode(6);
		bst.right.right.right.right.right.right = new TreeNode(7);
		
		balanceBST(bst);
	}

	static List<Integer> nodes = new ArrayList<>();

	public static TreeNode balanceBST(TreeNode root) {
		traverseBST(root);
		Collections.sort(nodes);
		return balance(0, nodes.size() - 1);
	}

	public static void traverseBST(TreeNode node) {
		if (node == null) {
			return;
		}

		nodes.add(node.val);

		traverseBST(node.left);
		traverseBST(node.right);
	}

	public static TreeNode balance(int from, int to) {
		if (from > to) {
			return null;
		}
		
		if (from >= nodes.size() || to <= -1) {
			return null;
		}

		if (to >= nodes.size() || from <= -1) {
			return null;
		}

		int middle = (from + to) / 2;

		return new TreeNode(nodes.get(middle),
				balance(from, middle - 1),
				balance(middle + 1, to));
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