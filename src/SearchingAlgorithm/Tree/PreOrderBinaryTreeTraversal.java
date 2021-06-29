package SearchingAlgorithm.Tree;

public class PreOrderBinaryTreeTraversal {

	Node root;
	 
	PreOrderBinaryTreeTraversal() { 
		root = null; 
	}
	
	/**
	 * Pre-Order Traversal: Root, Left, Right.
	 * It's the same order than Depth First Search.
	 * Pre-Order is used to create a copy of the tree.
	 * @param node
	 */
	void printPreOrder(Node node) {
		if (node == null) {
            return;
		}
 
        // First print data of node.
        System.out.print(node.data + " ");
 
        // Then recur on left subtree.
        printPreOrder(node.left);
 
        // Now recur on right subtree.
        printPreOrder(node.right);
    }
	
	public static void main(String[] args) {
		PreOrderBinaryTreeTraversal tree = new PreOrderBinaryTreeTraversal();
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
 
        System.out.println("Pre-Order Traversal of binary tree is ");
        tree.printPreOrder(tree.root);
    }
}