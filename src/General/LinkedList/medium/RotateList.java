package General.LinkedList.medium;

/**
 * https://leetcode.com/problems/rotate-list/
 * 
 * Given the head of a linked list, rotate the list to the right by k places.
 * 
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 * 
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 */
public class RotateList {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		int k = 2;
		
		printLinkedList(head);
		
		ListNode ans = rotateRight(head, k);
		
		printLinkedList(ans);
	}
	
	public static void printLinkedList(ListNode node) {
		while(node != null) {
			System.out.print(node.val + " ");
			node = node.next;
		}
		
		System.out.println("");
	}

	public static ListNode rotateRight(ListNode head, int k) {
		if (head == null || k == 0) {
			return head;
		}

		int count = countNodes(head);

		if (k % count == 0) {
			return head;
		}

		return rotate(head, k % count);
	}

	public static ListNode rotate(ListNode head, int n) {
		ListNode leftPointer = head;
		ListNode rightPointer = head;

		while(n > 0) {
			n--;
			rightPointer = rightPointer.next;
		}

		while(rightPointer.next != null) {
			leftPointer = leftPointer.next;
			rightPointer = rightPointer.next;    
		}

		rightPointer = leftPointer.next;
		leftPointer.next = null;

		ListNode dummy = new ListNode(0);
		dummy.next = rightPointer;

		while(rightPointer.next != null) {
			rightPointer = rightPointer.next;
		}

		rightPointer.next = head;

		return dummy.next;
	}

	public static int countNodes(ListNode node) {
		int count = 0;

		while(node != null) {
			count++;
			node = node.next;
		}

		return count;
	}

	static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
}