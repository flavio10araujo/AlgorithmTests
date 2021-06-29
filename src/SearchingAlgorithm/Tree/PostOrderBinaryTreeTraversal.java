package SearchingAlgorithm.Tree;

public class PostOrderBinaryTreeTraversal {

	Node root;
	 
	PostOrderBinaryTreeTraversal() { 
		root = null; 
	}
	
	/**
	 * Post-Order Traversal: Left, Right, Root.
	 * Post-Order is used to delete the tree.
	 * @param node
	 */
	void printPostOrder(Node node) {
		if (node == null) {
            return;
		}
 
        // First recur on left subtree.
        printPostOrder(node.left);
 
        // Then recur on right subtree.
        printPostOrder(node.right);
 
        // Now deal with the node.
        System.out.print(node.data + " ");
    }
	
	public static void main(String[] args) {
		PostOrderBinaryTreeTraversal tree = new PostOrderBinaryTreeTraversal();
		// An example of Binary Search Tree:
		//       4
		//      / \
		//     2   5
		//    / \
		//   1   3
        tree.root = new Node(4);
        tree.root.left = new Node(2);
        tree.root.right = new Node(5);
        tree.root.left.left = new Node(1);
        tree.root.left.right = new Node(3);
 
        System.out.println("Post-Order Traversal of binary tree is ");
        tree.printPostOrder(tree.root);
    }
}