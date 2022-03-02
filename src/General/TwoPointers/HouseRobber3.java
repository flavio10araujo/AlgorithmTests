package General.TwoPointers;

/**
 * https://leetcode.com/problems/house-robber-iii/
 * 
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
 * Besides the root, each house has one and only one parent house. 
 * After a tour, the smart thief realized that all houses in this place form a binary tree. 
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
 * 
 * Example 1:
 *        3
 *       / \
 *      2   3
 *     /     \
 *    3       1
 * 
 * Output: 3 + 3 + 1 = 7.
 * 
 * Example 2
 *        3
 *       / \
 *      4   5
 *     / \   \
 *    1   3   1
 *    
 * Output: 4 + 5 = 9
 * 
 * Example 3
 *       2
 *      / \
 *     1   3
 *      \
 *       4
 *   
 * Output: 3 + 4 = 7
 */
public class HouseRobber3 {

	public static void main(String[] args) {
		/*TreeNode root = new TreeNode(3);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(3);
		root.right.right = new TreeNode(1);*/

		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(1);
		root.left.right = new TreeNode(4);
		root.right = new TreeNode(3);
		
		System.out.println(rob(root));
	}

	public static int rob(TreeNode root) {
		T t = robOrNotRob(root);
		return Math.max(t.robRoot, t.notRobRoot);
	}
	
	private static T robOrNotRob(TreeNode root) {
	    if (root == null)
	      return new T(0, 0);

	    T l = robOrNotRob(root.left);
	    T r = robOrNotRob(root.right);

	    return new T(root.val + l.notRobRoot + r.notRobRoot,
	                 Math.max(l.robRoot, l.notRobRoot) + Math.max(r.robRoot, r.notRobRoot));
	  }

	static class T {
		public int robRoot;
		public int notRobRoot;

		public T(int robRoot, int notRobRoot) {
			this.robRoot = robRoot;
			this.notRobRoot = notRobRoot;
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