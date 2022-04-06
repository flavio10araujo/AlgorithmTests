package Tests;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindNthBiggestSalaryInABST {

	static int biggest = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		/*Node node = new Node(100);
		node.left = new Node(90);*/
		
		/*Node node = new Node(100);
		node.right = new Node(110);*/
		
		/*Node node = new Node(100);
		node.right = new Node(110);
		node.right.left = new Node(105);
		node.right.right = new Node(120);*/
		
		Node node = new Node(100);
		node.left = new Node(90);
		node.left.left = new Node(80);
		node.left.right = new Node(95);
		node.left.left.left = new Node(70);
		node.left.left.right = new Node(85);
		node.right = new Node(110);
		node.right.left = new Node(105);
		node.right.left.left = new Node(103);
		node.right.left.right = new Node(107);
		node.right.right = new Node(120);
		node.right.right.left = new Node(117);
		node.right.right.right = new Node(123);
		
		int n = 2, nthBiggest = 0;
		Queue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
		
		//findBiggest(node);
		//System.out.println(biggest);
		
		/*findNthBiggest(node, heap);
		
		while(n > 0) {
			n--;
			nthBiggest = heap.poll();
		}
		
		System.out.println(nthBiggest);*/
		
		findSecondBiggest(node);
		
		System.out.println(middle);
	}
	
	static int middle = 0;
	
	private static void findSecondBiggest(Node node) {
		if (node == null) {
			return;
		}
		
		// If node doesn't have any kid.
		if (node.left == null && node.right == null) {
			return;
		}
		
		// If there is a right side.
		if (node.right != null) {
			middle = node.value;
			findSecondBiggest(node.right);
		}
		// If there is not a right side.
		else {
			middle = node.left.value;
			findSecondBiggest(node.left);
		}
		
	}
	
	private static void findNthBiggest(Node node, Queue<Integer> heap) {
		if (node == null) {
			return;
		}
		
		heap.add(node.value);
		
		findNthBiggest(node.left, heap);
		findNthBiggest(node.right, heap);
	}
	
	private static void findBiggest(Node node) {
		if (node == null) {
			return;
		}
		
		biggest = Math.max(biggest, node.value);
		
		findBiggest(node.right);
	}
	
	static class Node {
		int value;
		Node left;
		Node right;
		
		public Node(int value) {
			this.value = value;
		}
		
		public void setLeft(Node left) {
			this.left = left;
		}
		
		public void setRight(Node right) {
			this.right = right;
		}
	}
}