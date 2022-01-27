package General.LinkedList.medium;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * 
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * 
 * Input: head = [-1,5,3,4,0]]
 * Output: [-1,0,3,4,5]
 * 
 * Input: head = []
 * Output: []
 */
public class SortList {

	public static void main(String[] args) {
		ListNode node = new ListNode(4);
		node.next = new ListNode(2);
		node.next.next = new ListNode(1);
		node.next.next.next = new ListNode(3);
		
		printLinkedList(node);
		
		ListNode sorted = sortList02(node);
		
		printLinkedList(sorted);
	}

	public static ListNode sortList01(ListNode head) {
		// Approach: iterate n times always looking for the next min value.
		// time: O(n ^ 2)
		// space: O(n)
		
		// Approach: heap, iterate linked list and add in a min-heap. Pop the elements to create a sorted linked list.
		// time: O(n) + O(log n) + O(1) + O(n)
		// space: O(n)
		
		Queue<Integer> heap = new PriorityQueue<>();
		
		while(head != null) {
			heap.add(head.val);
			head = head.next;
		}
		
		ListNode curr = new ListNode(0);
		ListNode dummy = curr;
		
		while(!heap.isEmpty()) {
			curr.next = new ListNode(heap.poll());
			curr = curr.next;
		}
		
		return dummy.next;
	}
	
	public static ListNode sortList02(ListNode head) { 
		// Approach: merge sort, break the linked list in the middle and repeat the process
		// until we have just two element. Sort the two elements. Merge the sorted sub-list with the others sorted sub-lists. 
		
		if (head == null) {
			return null;
		}
		
		if (head.next == null) {
			return head;
		}
		
		// If we have only two elements in the list, we sort it.
		if (head.next.next == null) {
			return sortTwoElementsLinkedList(head);
		}
		
		ListNode middle = findMiddle(head);
		
		ListNode pLeft = sortList02(head);
		ListNode pRight = sortList02(middle);
		
		return mergeSortedLinkedLists(pLeft, pRight);
	}
	
	public static ListNode mergeSortedLinkedLists(ListNode l1, ListNode l2) {
		ListNode sorted = new ListNode(0);
		ListNode dummy = sorted;
		
		while(l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				sorted.next = new ListNode(l1.val);
				sorted = sorted.next;
				l1 = l1.next;
			} else {
				sorted.next = new ListNode(l2.val);
				sorted = sorted.next;
				l2 = l2.next;
			}
		}
		
		while(l1 != null) {
			sorted.next = new ListNode(l1.val);
			sorted = sorted.next;
			l1 = l1.next;
		}
		
		while(l2 != null) {
			sorted.next = new ListNode(l2.val);
			sorted = sorted.next;
			l2 = l2.next;
		}
		
		return dummy.next;
	}
	
	public static ListNode sortTwoElementsLinkedList(ListNode head) {
		if (head.val > head.next.val) {
			Integer temp = head.next.val;
			head.next = null;
			return new ListNode(temp, head);
		}
		
		return head;
	}
	
	public static ListNode findMiddle(ListNode head) {
		ListNode prev = head;
		ListNode slow = head;
		ListNode fast = head;
		
		while(fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		
		prev.next = null;
		
		return slow;
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

	static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
}