package General.DFS;

public class FindMaxValueInBinaryTree {

	public static void main(String[] args) {
		Node root = new Node(5);
		root.left = new Node(1);
		root.left.left = new Node(8);
		root.left.right = new Node(11);
		//        5
		//       / 
		//      1
		//     / \
		//    8  11
		
		System.out.println(DFS(root));
	}

	public static int DFS(Node node) {
		if (node == null) {
			return Integer.MIN_VALUE;
		}
		
		int valueLeft = DFS(node.left);
		int valueRight = DFS(node.right);
		
		return getMax(node.data, valueLeft, valueRight);
	}
	
	public static int getMax(int data, int left, int right) {
		if (data >= left && data >= right) {
			return data;
		} else if (left >= data && left >= right) {
			return left;
		} else {
			return right;
		}
	}
}