package Recursion;

public class SumValuesInATree {

	public static void main(String[] args) {
		//      2
		//     / \
		//    3   4
		//   / \
		//  5  6
		
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		Node node6 = new Node(6);
		
		node2.left = node3;
		node2.right = node4;
		node3.left = node5;
		node3.right = node6;
		
		System.out.println(sumValues(node2));
	}
	
	public static int sumValues(Node root){
		if (root == null) {
			return 0;
		}
		
		return root.data + sumValues(root.left) + sumValues(root.right);
	}
}

class Node {
	int data;
	Node left;
	Node right;
	
	Node(int data) {
		this.data = data;
	}
}