package General.LinkedList.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/odd-even-linked-list/
 * 
 * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, 
 * and return the reordered list.
 * The first node is considered odd, and the second node is even, and so on.
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 * 
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [1,3,5,2,4]
 * 
 * Example 2:
 * Input: head = [2,1,3,5,6,4,7]
 * Output: [2,3,6,7,1,5,4]
 */
public class OddEvenLinkedList {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);

		printLinkedList(head);

		ListNode ans = solution02(head);
		printLinkedList(ans);
	}

	public static ListNode solution01(ListNode head) {
		List<Integer> arr = new ArrayList<>();

		ListNode pequeno = head;

		while (head != null) {
			arr.add(head.val);
			head = head.next;
		}

		if (arr.size() <= 1) {
			return pequeno;
		}

		ListNode result = new ListNode(arr.get(0));
		ListNode dummy = new ListNode(0);
		dummy.next = result;

		int index = 2;

		while (index < arr.size()) {
			result.next = new ListNode(arr.get(index));
			index = index + 2;
			result = result.next;
		}

		result.next = new ListNode(arr.get(1));
		result = result.next;
		index = 3;

		while (index < arr.size()) {
			result.next = new ListNode(arr.get(index));
			index = index + 2;
			result = result.next;
		}

		return dummy.next;
	}

	public static ListNode solution02(ListNode head) {
		int linkedlistSize = 0;
		ListNode temp = head;
		
		while(temp != null) {
			linkedlistSize++;
			temp = temp.next;
		}
		
		// If the number of nodes is 0, 1 or 2 we return the same head.
		if (linkedlistSize <= 2) {
			return head;
		}
		
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		
		ListNode pointerSlow = head.next;
		ListNode pointerFast = head.next.next;
		
		int count = 2;
		
		while(count <= linkedlistSize && pointerFast != null) {
			
			int bkp = pointerSlow.val;
			pointerSlow.val = pointerFast.val;
			pointerFast.val = bkp;
			
			pointerSlow = pointerSlow.next;
			
			pointerFast = pointerFast.next;
			
			if (pointerFast != null) {
				pointerFast = pointerFast.next;
			}
			
			count = count + 2;
		}
		
		return dummy.next;
	}
	
	public static void printLinkedList(ListNode node) {
		while(node != null) {
			System.out.print(node.val + " ");
			node = node.next;
		}

		System.out.println("");
	}

	static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
}