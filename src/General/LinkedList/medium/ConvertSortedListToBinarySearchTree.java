package General.LinkedList.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/ 
 * 
 * Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which 
 * the depth of the two subtrees of every node never differ by more than 1.
 */
public class ConvertSortedListToBinarySearchTree {

	public static void main(String[] args) {
		ListNode node = null;
		/*printLinkedList(node);
		TreeNode tree = sortedListToBST(node);
		printBinaryTree(tree);*/

		/*node = new ListNode(1);
		printLinkedList(node);
		tree = sortedListToBST(node);
		printBinaryTree(tree);*/

		/*node = new ListNode(1);
		node.next = new ListNode(2);
		printLinkedList(node);
		tree = sortedListToBST(node);
		printBinaryTree(tree);*/

		node = new ListNode(1);
		node.next = new ListNode(2);
		node.next.next = new ListNode(3);
		node.next.next.next = new ListNode(4);
		node.next.next.next.next = new ListNode(5);
		printLinkedList(node);
		TreeNode tree = sortedListToBSTSolution02(node);
		printBinaryTree(tree);
	}

	public static void printLinkedList(ListNode node) {
		if (node == null) {
			System.out.print("The linked list is null.");
		}

		while(node != null) {
			System.out.print(node.val + " ");
			node = node.next;
		}

		System.out.println(" ");
	}

	public static void printBinaryTree(TreeNode tree) {
		if (tree == null) {
			System.out.print("X ");
			return;
		}

		System.out.print(tree.val + " ");

		printBinaryTree(tree.left);
		printBinaryTree(tree.right);
	}

	public static TreeNode sortedListToBSTSolution01(ListNode head) {
		if (head == null) {
			return null;
		}

		List<Integer> items = new ArrayList<>();

		while(head != null) {
			items.add(head.val);
			head = head.next;
		}

		return createTreeByList(items, 0, items.size() - 1);
	}

	public static TreeNode createTreeByList(List<Integer> items, int from, int to) {
		if (from > to) {
			return null;
		}

		int middle = (from + to) / 2;

		TreeNode tree = new TreeNode(items.get(middle));

		tree.left = createTreeByList(items, from, middle - 1);
		tree.right = createTreeByList(items, middle + 1, to);

		return tree;
	}

	public static TreeNode sortedListToBSTSolution02(ListNode head) {
		if (head == null)
			return null;
		
		if (head.next == null)
			return new TreeNode(head.val);

		ListNode mid = findMid(head);
		TreeNode root = new TreeNode(mid.val);
		root.left = sortedListToBSTSolution02(head);
		root.right = sortedListToBSTSolution02(mid.next);

		return root;
	}

	private static ListNode findMid(ListNode head) {
		ListNode prev = null;
		ListNode slow = head;
		ListNode fast = head;

		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		
		prev.next = null;

		return slow;
	}

	static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}