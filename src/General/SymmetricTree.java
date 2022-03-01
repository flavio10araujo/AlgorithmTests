package General;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * https://leetcode.com/problems/symmetric-tree/
 * 
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 * 
 * Example 1:
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 * 
 * Example 2:
 * Input: root = [1,2,2,null,3,null,3]
 * Output: false
 * 
 * Constraints:
 * The number of nodes in the tree is in the range [1, 1000].
 * -100 <= Node.val <= 100
 */
public class SymmetricTree {

	public static void main(String[] args) {
		TreeNode t1 = new TreeNode(1);
		t1.left = new TreeNode(2);
		t1.right = new TreeNode(2);
		t1.left.left = new TreeNode(3);
		t1.left.right = new TreeNode(4);
		t1.right.left = new TreeNode(4);
		t1.right.right = new TreeNode(3);

		System.out.println(solution01(t1));
		System.out.println(solution02(t1));

		TreeNode t2 = new TreeNode(1);
		t2.left = new TreeNode(2);
		t2.right = new TreeNode(2);
		t2.left.right = new TreeNode(3);
		t2.right.right = new TreeNode(3);

		System.out.println(solution01(t2));
		System.out.println(solution02(t2));
	}

	/**
	 * Approach: recursive.
	 * Time: O(n).
	 * Space: O(h).
	 * @param root
	 * @return
	 */
	public static boolean solution01(TreeNode root) {
		return solution01(root, root);
	}

	private static boolean solution01(TreeNode p, TreeNode q) {
		if (p == null || q == null)
			return p == q;

		return p.val == q.val && solution01(p.left, q.right) && solution01(p.right, q.left);
	}

	/**
	 * Approach: BFS.
	 * 
	 * @param root
	 * @return
	 */
	public static boolean solution02(TreeNode root) {
		Deque<TreeNode> queue = new ArrayDeque<>();

		queue.add(root);

		while(!queue.isEmpty()) {
			int n = queue.size();
			List<Integer> pal = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				TreeNode node = queue.pop();

				if (node.left != null) {
					queue.add(node.left);
					pal.add(node.left.val);
				} else {
					pal.add(-101);
				}

				if (node.right != null) {
					queue.add(node.right);
					pal.add(node.right.val);
				} else {
					pal.add(-101);
				}
			}

			int L = 0;
			int R = pal.size() - 1;

			while(L < R) {
				if (pal.get(L) != pal.get(R)) {
					return false;
				}

				L++;
				R--;
			}
		}

		return true;
	}

	public static class TreeNode {
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