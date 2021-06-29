package SearchingAlgorithm.Tree;

/**
 * Level order traversal of a tree is the BFS for a tree.
 * Time Complexity: O(n ^ 2) in worst case.
 * Space Complexity: O(n) in worst case.
 */
public class LevelOrderBinaryTreeTraversal {
	// Root of the Binary Tree.
    Node root;
 
    public LevelOrderBinaryTreeTraversal() {
        root = null;
    }
    
    public static void main(String args[]) {
    	LevelOrderBinaryTreeTraversal tree = new LevelOrderBinaryTreeTraversal();
        //     1
    	//    / \
    	//   2   3
    	//  / \
    	// 4   5
    	tree.root= new Node(1);
        tree.root.left= new Node(2);
        tree.root.right= new Node(3);
        tree.root.left.left= new Node(4);
        tree.root.left.right= new Node(5);
         
        System.out.println("Level order traversal of binary tree is ");
        tree.printLevelOrder();
     }

    /**
     * Function to print level order traversal of tree.
     */
    void printLevelOrder() {
        int h = height(root);
        int i;

        for (i = 1; i <= h; i++)
        	printCurrentLevel(root, i);
    }

    /**
     * Compute the "height" of a tree: the number of nodes along the longest path from the root node down to the farthest leaf node.
     * @param root
     * @return
     */
    int height(Node root) {
        if (root == null) {
           return 0;
        }
        else {
            // Compute height of each subtree.
            int lheight = height(root.left);
            int rheight = height(root.right);

            // Use the larger one.
            if (lheight > rheight) {
                return (lheight + 1);
            } else {
            	return (rheight + 1);
            }
        }
    }
 
    /**
     * Print nodes at the level.
     * @param root
     * @param level
     */
    void printCurrentLevel (Node root, int level) {
        if (root == null)
            return;

        if (level == 1) {
            System.out.print(root.data + " ");
        } else if (level > 1) {
            printCurrentLevel(root.left, level - 1);
            printCurrentLevel(root.right, level - 1);
        }
    }
}