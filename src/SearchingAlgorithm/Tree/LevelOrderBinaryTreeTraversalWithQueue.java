package SearchingAlgorithm.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Level order traversal of a tree is the BFS for a tree.
 * Time Complexity: O(n) where n is the number of nodes in the binary tree.
 * Space Complexity: O(n) where n is the number of nodes in the binary tree.
 */
public class LevelOrderBinaryTreeTraversalWithQueue {
	// Root of the Binary Tree.
	Node root;
	
	public static void main(String args[]) {
        LevelOrderBinaryTreeTraversalWithQueue tree = new LevelOrderBinaryTreeTraversalWithQueue();
        //      1
     	//    / \
     	//   2   3
     	//  / \
     	// 4   5
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
 
        System.out.println("Level order traversal of binary tree is ");
        tree.printLevelOrder();
    }
	 
    /**
     * Given a binary tree, print its nodes in level order using array for implementing queue.
     */
    void printLevelOrder() {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            // poll() removes the present head.
            Node tempNode = queue.poll();
            System.out.print(tempNode.data + " ");
 
            // Enqueue left child.
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }
 
            // Enqueue right child.
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }
}