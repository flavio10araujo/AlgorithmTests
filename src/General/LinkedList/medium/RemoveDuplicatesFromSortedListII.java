package General.LinkedList.medium;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 * 
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. 
 * Return the linked list sorted as well.
 * 
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 * 
 * Input: head = [1,1,1,2,3]
 * Output: [2,3]
 */
public class RemoveDuplicatesFromSortedListII {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(1);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(2);
		head.next.next.next.next = new ListNode(3);
		head.next.next.next.next.next = new ListNode(4);
		head.next.next.next.next.next.next = new ListNode(4);
		head.next.next.next.next.next.next.next = new ListNode(5);

		printLinkedList(head);

		ListNode newNode = deleteDuplicates02(head);

		printLinkedList(newNode);
		
		head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(2);

		printLinkedList(head);

		newNode = deleteDuplicates02(head);

		printLinkedList(newNode);
	}

	public static void printLinkedList(ListNode node) {
		while(node != null) {
			System.out.print(node.val + " ");
			node = node.next;
		}

		System.out.println("");
	}

	public static ListNode deleteDuplicates01(ListNode head) {

		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode curr = head;
		ListNode next = head;

		while(next != null) {
			while(next != null && curr.val == next.val) {
				next = next.next;
			}

			curr.next = next;
			curr = curr.next;
		}

		return dummy.next;
	}

	public static ListNode deleteDuplicates02(ListNode head) {
		ListNode dummy = new ListNode(0);
		ListNode prev = dummy;

		ListNode curr = head;

		int count = 0;

		while (head != null) {
			while (head != null && curr.val == head.val) {
				head = head.next;
				count++;
			}

			if (count > 1) {
				curr = head;
				prev.next = null;
			} else {
				prev.next = curr;
				prev = prev.next;

				curr = head;
			}

			count = 0;
		}

		return dummy.next;
	}

	public static ListNode deleteDuplicates(ListNode head) {
		ListNode dummy = new ListNode(0, head);
		ListNode prev = dummy;

		while (head != null) {
			while (head.next != null && head.val == head.next.val)
				head = head.next;

			if (prev.next == head)
				prev = prev.next;
			else
				prev.next = head.next;
			
			head = head.next;
		}

		return dummy.next;
	}

	static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
}