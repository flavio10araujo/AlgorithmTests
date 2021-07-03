package General.DFS;

/**
 * Input:
 * //        5
 * //       / \
 * //      4   6
 * //     / \
 * //    3   8
 * 
 * Output: 3
 * Explanation: 4, 6 and 8.
 */
public class NumerOfPairsNumbers {

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
     */
	public static int dfs(Node<Integer> root) {
        
    	int count = 0;
    	
    	if (root == null) {
    		return 0;
    	}
    	
    	if (root.val % 2 == 0) {
			count++;
		}
    	
    	return count + dfs(root.left) + dfs(root.right);
    }

    public static void main(String[] args) {
		Node<Integer> root = buildTree();
		System.out.println(dfs(root));
	}
}