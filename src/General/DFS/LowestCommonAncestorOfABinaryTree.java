package General.DFS;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Lowest common ancestor (LCA) of two nodes v and w in a tree is the lowest (i.e. deepest) node that has both v and w as descendants.
 * We also define each node to be a descendant of itself (so if v has a direct connection from w, w is the lowest common ancestor).
 * 
 * Given two nodes of a binary tree, find their lowest common ancestor.
 * 
 * Input:
 * v = 3; w = 8;
 * //        5
 * //       / \
 * //      4   6
 * //     / \
 * //    3   8
 * Output: 4.
 * Explanation: 4 is the deepest common ancestor of 3 and 8.
 * 
 *  Input:
 *  v = 3; w = 6
 *  Output: 5.
 */
public class LowestCommonAncestorOfABinaryTree {
	public static Node lca(Node root, Node node1, Node node2) {
        if (root == null) {
        	return null;
        }

        // Case 2 : Current node is LCA : Current node is one of the target and the other node is in a subtree.
        if (root.equals(node1) || root.equals(node2)) {
        	return root;
        }
        
        Node left = lca(root.left, node1, node2);
        Node right = lca(root.right, node1, node2);

        // Case 1 : Current node is LCA : One target node is on the left subtree, the other target node on the right subtree, so the current node itself is the LCA.
        if (left != null && right != null) {
        	return root;
        }

        // At this point, left and right can't be both non-null
        // Case 4 : Current node is not LCA : Current node is in the path between LCA and target node in case 2.
        // Case 5 : Current node is not LCA : LCA is in the subtree of current node.
        // report target node or LCA back to parent
        if (left != null) {
        	return left;
        }
        
        if (right != null) {
        	return right;
        }

        // Case 4, not found return null
        return null;
    }

    /** Driver class, do not change **/
    static class Node {
        int val;
        Node left, right;

        public Node(int val) {
            this.val = val;
        }

        public static Node buildTree(Iterator<String> iter) {
            String nxt = iter.next();
            if (nxt.equals("x")) return null;
            Node node = new Node(Integer.parseInt(nxt));
            node.left = buildTree(iter);
            node.right = buildTree(iter);
            return node;
        }

        public static Node findNode(Node root, int target) {
            if (root == null) return null;
            if (root.val == target) return root;
            Node leftSearch = findNode(root.left, target);
            if (leftSearch != null) {
                return leftSearch;
            }
            return findNode(root.right, target);
        }
    }

    public static void main(String[] args) {
        Node root = Node.buildTree(Arrays.stream("5 4 3 x x 8 x x 6 x x".split(" ")).iterator());
    	Node node1 = Node.findNode(root, 3);
        Node node2 = Node.findNode(root, 8);
    	
        Node ans = lca(root, node1, node2);
        System.out.println(ans == null ? "null" : ans.val);
    }
}