package General;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * 
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. 
 * A node can only appear in the sequence at most once. 
 * Note that the path does not need to pass through the root.
 * The path sum of a path is the sum of the node's values in the path.
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 * 
 * Example 1:
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * 
 * Example 2:
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 */
public class BinaryTreeMaximumPathSum {

	public static void main(String[] args) {
		/*TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);*/
		
		/*TreeNode root = new TreeNode(-10);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);*/
		
		/*TreeNode root = new TreeNode(1);
		root.left = new TreeNode(-2);
		root.left.left = new TreeNode(1);
		root.left.left.left = new TreeNode(-1);
		root.left.right = new TreeNode(3);
		root.right = new TreeNode(-3);
		root.right.left = new TreeNode(-2);*/
		
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right = new TreeNode(8);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.right.right.right = new TreeNode(1);
		
		System.out.println(maxPathSum(root));
	}
	
	static int max = Integer.MIN_VALUE;
    
    public static int maxPathSum(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        }
        
        dfs(root);
        
        return max;
    }
    
    private static int dfs(TreeNode root) {
        if (root == null) {
            return -1001;
        }
        
        int leftSum = dfs(root.left);
        int rightSum = dfs(root.right);
        
        int leftPlusRoot = leftSum + root.val;
        int rightPlusRoot = rightSum + root.val;        
        int kidsPlusRoot = leftSum + root.val + rightSum;
        
        int maxLocal = Math.max(root.val, Math.max(rightPlusRoot, leftPlusRoot));        
        
        max = Math.max(max, Math.max(kidsPlusRoot, maxLocal));
        
        return maxLocal;
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