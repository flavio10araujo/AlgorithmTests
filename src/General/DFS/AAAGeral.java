package General.DFS;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.function.Function;

/**
 * Um resumo sobre algumas funções úteis sobre DFS:
 * 
 * 		- dfsCountNodes
 * 		- dfsVerifyMaxDepth
 * 		- dfsVerifyMinDepth
 * 		- dfsIsBalanced
 * 		- dfsSerialize
 * 		- dfsDeserialize
 * 		- dfsIsBinarySearchTree
 * 		- dsfInsertIntoBST
 * 		- dfsInvertBinaryTree
 */
class AAAGeral {

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

    public static void testingDFSFunctions(Node<Integer> tree) {
        System.out.println("The number of nodes in this tree is: " + dfsCountNodes(tree));
        System.out.println("The max depth in this tree is: " + dfsVerifyMaxDepth(tree));
        System.out.println("The min depth in this tree is: " + dfsVerifyMinDepth(tree));
        System.out.println("Is this binary tree balanced? " + (dfsIsBalanced(tree) == -1 ? false : true));
        
        // Converting the tree into a String.
        StringJoiner s = new StringJoiner(" ");
        dfsSerialize(tree, s);
        System.out.println(s.toString());
        
        // Converting one String into one tree.
        Node<Integer> newTree = dfsDeserialize(Arrays.stream(s.toString().split(" ")).iterator());
        
        boolean isBinarySearchTree = dfsIsBinarySearchTree(newTree, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("Is this tree a Binary Search Tree? " + isBinarySearchTree);
        
        Node<Integer> treeWithNewElement = null;
        
        if (isBinarySearchTree) {
        	// If it is a BST, insert the value 9 in the tree.
        	treeWithNewElement = dsfInsertIntoBST(newTree, 9);
        	s = new StringJoiner(" ");
            dfsSerialize(treeWithNewElement, s);
            System.out.println(s.toString());
        }
        
        // Inverting the binary tree.
        Node<Integer> invertedTree = dfsInvertBinaryTree(isBinarySearchTree ? treeWithNewElement : newTree);
        s = new StringJoiner(" ");
        dfsSerialize(invertedTree, s);
        System.out.println("Inverted tree:");
        System.out.println(s.toString());
    }
    
    /**
     * Count the number of nodes of a binary tree.
     * 
     * @param node
     * @return
     */
    public static int dfsCountNodes(Node<Integer> node) {
        if (node == null) {
            return 0;
        }
        
        int count = 1;
        
        count += dfsCountNodes(node.left);
        count += dfsCountNodes(node.right);
        
        return count;
    }
    
    /**
     * Verify the max depth in all the subtrees.
     * 
     * @param node
     * @return
     */
    public static int dfsVerifyMaxDepth(Node<Integer> node) {
    	if (node == null) {
    		return 0;
    	}
    	
    	return Math.max(dfsVerifyMaxDepth(node.left), dfsVerifyMaxDepth(node.right)) + 1;
    }
    
    /**
     * Verify the min depth in all the subtrees.
     * 
     * @param node
     * @return
     */
    public static int dfsVerifyMinDepth(Node<Integer> node) {
    	if (node == null) {
    		return 0;
    	}
    	
    	return Math.min(dfsVerifyMinDepth(node.left), dfsVerifyMinDepth(node.right)) + 1;
    }
    
    /**
     * Verify is a binary tree is balanced.
     * 
     * Obs.: 
     * It is basically the same logic than dfsVerifyMaxDepth.
     * The difference is that we validate the size difference between the left subtree height and the right subtree height.
     * If this difference is greater than 1, this means that the binary tree is not balanced.
     * If the binary tree is not balanced, we return -1.
     * 
     * The part "if (leftHeight == -1 || rightHeight == -1) return -1" is going pass -1 until the root.
     * 
     * @param node
     * @return
     */
    public static int dfsIsBalanced(Node<Integer> node) {
        if (node == null) {
            return 0;
        }
        
        int leftHeight = dfsIsBalanced(node.left);
        int rightHeight = dfsIsBalanced(node.right);
        
        // With this if we guarantee that the -1 will be passed until the root.
        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }
        
        // Math.abs will get the absolute value of the number.
        // Ex.: -5 will return 5; 8 will return 8.
        // If the difference between the size of the two subtree is greater than 1, than the tree is not balanced.
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    /**
     * Get a tree and show it in a String format.
     * 
     * @param node
     * @param s
     */
    public static void dfsSerialize(Node<Integer> node, StringJoiner s) {
    	if (node == null) {
    		s.add("x");
    		return;
    	}
    	
    	s.add(Integer.toString(node.val));
    	
    	dfsSerialize(node.left, s);
    	dfsSerialize(node.right, s);
    }
    
    /**
     * Get a String like "1 2 4 x 7 x x 5 x x 3 x 6 x x" and convert into a binary tree.
     * 
     * @param nodes
     * @return
     */
    public static Node<Integer> dfsDeserialize(Iterator<String> nodes) {
    	String val = nodes.next();
    	
    	if (val.equals("x")) {
    		return null;
    	}
    	
    	Node<Integer> node = new Node<Integer>(Integer.parseInt(val));
    	node.left = dfsDeserialize(nodes);
    	node.right = dfsDeserialize(nodes);
    	
    	return node;
    }
    
    /**
     * Verify if the tree is a binary search tree.
     * 
     * @param node
     * @param min
     * @param max
     * @return
     */
    public static boolean dfsIsBinarySearchTree(Node<Integer> node, Integer min, Integer max) {
    	// Empty nodes are always BST.
    	if (node == null) {
    		return true;
    	}
    	
    	if (!(node.val >= min && node.val <= max)) {
            return false;
        }
        
        return dfsIsBinarySearchTree(node.left, min, node.val) && dfsIsBinarySearchTree(node.right, node.val, max);
    }
    
    /**
     * Inserting a new node into a BST.
     * 
     * @param node
     * @param newNode
     * @return
     */
    public static Node<Integer> dsfInsertIntoBST(Node<Integer> node, Integer newNode) {
    	if (node == null) {
    		return new Node<Integer>(newNode);
    	}
    	
    	int compare = node.val.compareTo(newNode);
    	
    	// If newNode is bigger than node.val.
    	if (compare < 0) {
    		node.right = dsfInsertIntoBST(node.right, newNode);
    	}
    	// If newNode is smaller than node.val.
    	else if (compare > 0) {
    		node.left = dsfInsertIntoBST(node.left, newNode);
    	}
    	
    	return node;
    }
    
    /**
     * Invert the nodes.
     * 
     * From:
     *         6
     *        / \
     *       4   8
     *      / \   \
     *     3   5   9
     * 
     * To:
	 *         6
	 *        / \
	 *       8   4
	 *      / 	/ \
	 *     9   5   3
	 * 
     * @param node
     * @return
     */
    public static Node<Integer> dfsInvertBinaryTree(Node<Integer> node) {
    	if (node == null) {
    		return null;
    	}
    	
    	return new Node<Integer>(node.val, dfsInvertBinaryTree(node.right), dfsInvertBinaryTree(node.left));
    }
    
    

    public static <T> Node<T> buildTree(Iterator<String> iter, Function<String, T> f) {
        String val = iter.next();
        if (val.equals("x")) return null;
        Node<T> left = buildTree(iter, f);
        Node<T> right = buildTree(iter, f);
        return new Node<T>(f.apply(val), left, right);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 1 2 4 x 7 x x 5 x x 3 x 6 x x (balanced)
    
    // 1 2 4 x 7 x x 5 x x 3 x 6 8 x x x (not balanced)
    
    // 6 4 3 x x 5 x x 8 x x (BST)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node<Integer> tree = buildTree(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
        scanner.close();
        testingDFSFunctions(tree);
    }
}