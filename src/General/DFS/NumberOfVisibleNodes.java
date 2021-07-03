package General.DFS;

/**
 * In a binary tree, a node is considered "visible" if no node on the root-to-itself path has a greater value. 
 * The root is always "visible" since there are no other nodes between the root and itself. 
 * 
 * Given a binary tree, count the number of "visible" nodes.
 * 
 * Input:
 * //        5
 * //       / \
 * //      4   6
 * //     / \
 * //    3   8
 * 
 * Output: 3
 * Explanation: 5, 6 and 8.
 */
public class NumberOfVisibleNodes {

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
	
	public static Node<Integer> buildTree() {
        Node<Integer> node = new Node<Integer>(5);
        node.left = new Node<Integer>(4);
        node.left.left = new Node<Integer>(3);
        node.left.right = new Node<Integer>(8);
        node.right = new Node<Integer>(6);
    	
    	/*Node<Integer> node = new Node<Integer>(-100);
        node.right = new Node<Integer>(-500);
        node.right.right = new Node<Integer>(-50);*/
        
        return node;
    }

    /**
     * 
     * @param root
     * @param bigNumber
     * @return
     */
	public static int dfs(Node<Integer> root, int bigNumber) {
        
    	int count = 0;
		
		if (root == null) {
    		return 0;
    	}
    	
		System.out.println("root.val="+root.val+" bigNumber="+bigNumber);
		
		if (root.val >= bigNumber) {
    		count++;
    		bigNumber = root.val;
    		System.out.println("novo bigNumber="+bigNumber);
    	}
    	
    	return count + dfs(root.left, bigNumber) + dfs(root.right, bigNumber);
    }

    public static void main(String[] args) {
		Node<Integer> root = buildTree();
		System.out.println(dfs(root, Integer.MIN_VALUE));
	}
}