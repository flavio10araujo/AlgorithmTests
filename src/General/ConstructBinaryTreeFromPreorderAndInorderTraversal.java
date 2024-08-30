package General;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * <p>
 * Given two integer arrays preorder and inorder
 * where preorder is the preorder traversal of a binary tree
 * and inorder is the inorder traversal of the same tree,
 * construct and return the binary tree.
 * <p>
 * Example 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * <p>
 * Example 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static void main(String[] args) {
        int[] preorder = { 3, 9, 20, 15, 7 };
        int[] inorder = { 9, 3, 15, 20, 7 };
        // output: 3,9,20,null,null,15,7

        TreeNode res = buildTree(preorder, inorder);
        printPreOrder(res);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderToIndex = new HashMap<>();

        for (int i = 0; i < inorder.length; ++i) {
            inorderToIndex.put(inorder[i], i);
        }

        return helper(preorder, inorderToIndex, 0, preorder.length - 1, 0);
    }

    private static TreeNode helper(int[] preorder, Map<Integer, Integer> inorderToIndex,
            int preorderStart,
            int preorderEnd,
            int inorderStart) {

        if (preorderStart > preorderEnd) {
            return null;
        }

        int root = preorder[preorderStart];
        final int inorderIndex = inorderToIndex.get(root);
        
        TreeNode curr = new TreeNode(root);
        curr.left = helper(preorder, inorderToIndex,
                preorderStart + 1,
                preorderStart + inorderIndex - inorderStart,
                inorderStart);

        curr.right = helper(preorder, inorderToIndex,
                preorderStart + inorderIndex - inorderStart + 1,
                preorderEnd,
                inorderIndex + 1);

        return curr;
    }

    private static void printPreOrder(TreeNode res) {
        Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.add(res);

        while (!queue.isEmpty()) {
            int n = queue.size();

            for (int i = 0; i < n; i++) {
                TreeNode node = queue.pop();

                System.out.print(" " + node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
