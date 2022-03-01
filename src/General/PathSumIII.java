package General;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/path-sum-iii/
 * 
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 * 
 * Example 1:
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 * Explanation: The paths that sum to 8 are shown.
 * 
 * Example 2:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: 3
 * 
 * Constraints:
 * The number of nodes in the tree is in the range [0, 1000].
 * -10 ^ 9 <= Node.val <= 10 ^ 9
 * -1000 <= targetSum <= 1000
 */
public class PathSumIII {

	public static void main(String[] args) {
		//[5,4,8,11,null,13,4,7,2,null,null,5,1]
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right = new TreeNode(8);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.right.right.left = new TreeNode(5);
		root.right.right.right = new TreeNode(1);
		int targetSum = 22;

		System.out.println(solution01(root, targetSum));
		
		System.out.println(solution02(root, targetSum));
	}

	/**
	 * 
	 * @param root
	 * @param targetSum
	 * @return
	 */
	public static int solution01(TreeNode root, int targetSum) {
		if (root == null) {
			return 0;
		}

		// Approach: traverse the tree O(n)
		// keep all the paths in a list
		// iterate the list O(number of paths)
		// sum the elements in the list O(path size)
		// ex.: 10, 5, 3, 3 becomes 10, 15, 18, 21
		// two pointers, opposiste ways to verify if target exists O(path size)
		// space complexity: O(n)


		// Approach: same idea but analysing the list inside the dfs
		List<Integer> listSums = new ArrayList<>();
		return dfs(root, targetSum, listSums);
	}

	private static int dfs(TreeNode node, int targetSum, List<Integer> listSums) {
		if (node == null) {
			return 0;
		}

		if (listSums.size() > 0) {
			listSums.add(node.val + listSums.get(listSums.size() - 1));
		} else {
			listSums.add(node.val);
		}

		int counter = 0;
		int L = 0;
		int R = listSums.size() - 1;

		if (listSums.get(R) == targetSum) {
			counter++;
		}

		while(L < R) {
			if (listSums.get(R) - listSums.get(L) == targetSum) {
				counter++;
			}

			L++;
		}

		counter += dfs(node.left, targetSum, listSums);
		counter += dfs(node.right, targetSum, listSums);

		listSums.remove(listSums.size() - 1);

		return counter;
	}

	/**
	 * 
	 * @param root
	 * @param sum
	 * @return
	 */
	public static int solution02(TreeNode root, int sum) {
		if (root == null)
			return 0;
		
		return dfs(root, sum) + solution02(root.left, sum) + solution02(root.right, sum);
	}

	private static int dfs(TreeNode root, int sum) {
		if (root == null)
			return 0;
		
		return (sum == root.val ? 1 : 0) +
				dfs(root.left, sum - root.val) +
				dfs(root.right, sum - root.val);
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