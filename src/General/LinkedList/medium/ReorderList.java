package General.LinkedList.medium;

/**
 * https://leetcode.com/problems/reorder-list/
 * 
 * You are given the head of a singly linked-list. The list can be represented as:
 * L0 -> L1 -> … -> Ln - 1 -> Ln
 * 
 * Reorder the list to be on the following form:
 * L0 -> Ln -> L1 -> Ln - 1 -> L2 -> Ln - 2 -> …
 * 
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 *
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [1,4,2,3]
 * 
 * Example 2:
 * Input: head = [1,2,3,4,5]
 * Output: [1,5,2,4,3]
 * 
 * Constraints:
 * The number of nodes in the list is in the range [1, 5 * 104].
 * 1 <= Node.val <= 1000
 */
public class ReorderList {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		
		printLinkedList(head);
		
		solution01(head);
		
		printLinkedList(head);
	}
	
	public static void printLinkedList(ListNode node) {
		while(node != null) {
			System.out.print(node.val + " ");
			node = node.next;
		}
		
		System.out.println("");
	}

	public static void solution01(ListNode head) {
		if (head == null || head.next == null) {
			return;
		}

		ListNode mid = findMid(head);
		ListNode reversed = reverse(mid);
		merge(head, reversed);
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

	private static ListNode reverse(ListNode head) {
		ListNode prev = null;
		ListNode curr = head;
		ListNode next = null;

		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}

		return prev;
	}

	private static void merge(ListNode l1, ListNode l2) {
		while (l2 != null) {
			ListNode next = l1.next;
			l1.next = l2;
			l1 = l2;
			l2 = next;
		}
	}

	public static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
}