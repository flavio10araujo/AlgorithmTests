package SearchingAlgorithm.Tree;

public class InOrderBinaryTreeTraversal {

	Node root;
	 
	InOrderBinaryTreeTraversal() { 
		root = null; 
	}
	
	/**
	 * In-Order Traversal: Left, Root, Right.
	 * If the Tree is a Binary Search Tree, the In-Order Traversal will print the values in order.
	 * @param node
	 */
	void printInOrder(Node node) {
        if (node == null) {
            return;
        }
 
        // First recur on left child.
        printInOrder(node.left);
 
        // Then print the data of node.
        System.out.print(node.data + " ");
 
        // Now recur on right child.
        printInOrder(node.right);
    }
	
	public static void main(String[] args) {
		InOrderBinaryTreeTraversal tree = new InOrderBinaryTreeTraversal();
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
 
        System.out.println("In-Order Traversal of binary tree is ");
        tree.printInOrder(tree.root);
    }
}