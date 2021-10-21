package General.TwoPointers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
 *    /
 *   4
 *   
 * Output: 3 + 4 = 7
 */
public class HouseRobber3 {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(3);
		root.right.right = new TreeNode(1);
		
		System.out.println(rob(root));
	}
	
	public static int rob(TreeNode root) {
        
		Deque<TreeNode> queue = new ArrayDeque<>();
		queue.add(root);
		
		int index = -1;
		List<Integer> dp = new ArrayList<>();
		
		while(!queue.isEmpty()) {
			int n = queue.size();
			index++;
			int countValues = 0;
			
			for (int i = 0; i < n; i++) {
				TreeNode node = queue.poll();
				
				countValues += node.val;
				
				if (node.left != null) {
					queue.add(node.left);
				}
				
				if (node.right != null) {
					queue.add(node.right);
				}
			}
			
			if (dp.isEmpty()) {
				dp.add(countValues);
			} else if (dp.size() == 1) {
				dp.add(Math.max(dp.get(0), countValues));
			} else {
				dp.add(Math.max(dp.get(index - 2) + countValues, dp.get(index - 1)));
			}
		}
		
		if (dp.isEmpty()) {
			return 0;
		}
		
		return dp.get(dp.size() - 1);
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