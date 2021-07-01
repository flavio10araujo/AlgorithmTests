package General.DFS;

public class MaxDepthOfATree {

	public static void main(String[] args) {
		Node root = new Node(5);
		root.left = new Node(1);
		root.left.left = new Node(8);
		root.left.left.left = new Node(9);
		root.left.right = new Node(11);
		root.left.right.left = new Node(17);
		root.right = new Node(70);
		root.right.left = new Node(69);
		root.right.left.left = new Node(68);
		root.right.left.left.left = new Node(67);
		//        5
		//       / 
		//      1
		//     / \
		//    8  11
		
		System.out.println(DFS(root));
		
		System.out.println(DFSPower(root));
	}
	
	/**
	 * Solution 01.
	 * 
	 * @param node
	 * @return
	 */
	public static int DFS(Node node) {
		if (node == null) {
			return 0;
		}
		
		int countLeft = 1 + DFS(node.left);
		int countRight = 1 + DFS(node.right);
		return getMax(0, countLeft, countRight);
	}
	
	public static int getMax(int data, int left, int right) {
		
		//System.out.println("data="+data+" left="+left+" right="+right);
		
		if (data >= left && data >= right) {
			return data;
		} else if (left >= data && left >= right) {
			return left;
		} else {
			return right;
		}
	}
	
	/**
	 * Solution 02.
	 * 
	 * @param root
	 * @return
	 */
	public static int DFSPower(Node root) {
        if (root == null) return 0;
        // Depth of current node's subtree = max depth of the two subtrees + 1 provided by current node
        return Math.max(DFSPower(root.left), DFSPower(root.right)) + 1;
    }
}