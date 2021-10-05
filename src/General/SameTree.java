package General;

public class SameTree {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(1);
		tree1.left = new TreeNode(2);
		tree1.right = new TreeNode(3);
		
		TreeNode tree2 = new TreeNode(1);
		tree2.left = new TreeNode(2);
		tree2.right = new TreeNode(3);
		
		System.out.println(isSameTree(tree1, tree2));
	}

	public static boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}

		if ((p == null && q != null) || (p != null && q == null)) {
			return false;
		}

		return DFS(p, q);
	}

	public static boolean DFS(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		} else if ((p == null && q != null) || (p != null && q == null)) {
			return false;
		} else if (p.val != q.val) {
			return false;
		}

		if (!DFS(p.left, q.left)) {
			return false;
		} else if (!DFS(p.right, q.right)) {
			return false;
		}

		return true;
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