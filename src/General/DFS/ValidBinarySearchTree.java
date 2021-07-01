package General.DFS;

/**
 * A binary search tree is a binary tree with the property that any of its node's value is greater than or equal to any node in its left subtree 
 * and less than or equal to any node's value in its right subtree.
 * 
 * Given a binary tree, determine whether it is a binary search tree.
 * Input:
 * //        6
 * //       / \
 * //      4   8
 * //     / \
 * //    3   5
 * Output: True.
 * 
 * Input:
 * //        6
 * //       / \
 * //      4   8
 * //     / \
 * //    3   8
 * Output: False.
 */
public class ValidBinarySearchTree {
	public static class Node<T> {
        public T val;
        public Node<T> left;
        public Node<T> right;

        public Node(T val) {
            this(val, null, null);
        }

        public Node(T val, Node<T> left, Node<T> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

	public static boolean validBst(Node<Integer> root) {
        // root is always valid
        return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
	private static boolean dfs(Node<Integer> root, int min, int max) {
        if (root == null) {
        	return true;
        }
        
        System.out.println("root.val="+root.val+" min="+min+" max="+max);
        
        if (!(min <= root.val && root.val <= max)) {
            return false;
        }
        
        return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
    }

    public static Node<Integer> buildTree() {
        /*Node<Integer> node = new Node<Integer>(6);
        node.left = new Node<Integer>(4);
        node.left.left = new Node<Integer>(3);
        node.left.right = new Node<Integer>(5);
        node.right = new Node<Integer>(8);*/
    	
    	/*Node<Integer> node = new Node<Integer>(6);
        node.left = new Node<Integer>(4);
        node.left.left = new Node<Integer>(3);
        node.left.right = new Node<Integer>(8);
        node.right = new Node<Integer>(8);*/
    	
    	Node<Integer> node = new Node<Integer>(88);
        node.left = new Node<Integer>(60);
        node.right = new Node<Integer>(90);
        
        node.left.left = new Node<Integer>(40);
        node.left.right = new Node<Integer>(80);
        
        node.left.left.left = new Node<Integer>(30);
        node.left.left.right = new Node<Integer>(80);
        
        
        return node;
    }
    
    public static void main(String[] args) {
        Node<Integer> root = buildTree();
        System.out.println(validBst(root));
    }
}
