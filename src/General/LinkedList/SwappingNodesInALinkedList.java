package General.LinkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
 * 
 * You are given the head of a linked list, and an integer k.
 * Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [1,4,3,2,5]
 * 
 * Example 2:
 * Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
 * Output: [7,9,6,6,8,7,3,0,9,5]
 * 
 * Example 3:
 * Input: head = [1], k = 1
 * Output: [1]
 */
public class SwappingNodesInALinkedList {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		int k = 2;
		
		/*ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(4);
		head.next.next.next = new ListNode(5);
		int k = 2;*/
		
		/*ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		int k = 2;*/
		
		printNode(head);
		
		/*System.out.println("");
		ListNode ans = solution01(head, k);
		printNode(ans);*/
		
		System.out.println("");
		ListNode ans2 = solution02(head, k);
		printNode(ans2);
	}
	
	public static ListNode solution01(ListNode head, int k) {
		List<ListNode> mylist = transformListNodeInArray(head);
		
		ListNode front = mylist.get(k - 1);
		ListNode back = mylist.get(mylist.size() - k);
		
		mylist.set(k - 1, back);
		mylist.set(mylist.size() - k, front);
		
		ListNode newlist = mylist.get(0);
		
		ListNode dummy = new ListNode(0);
		dummy.next = newlist;
		
		for (int i = 1; i < mylist.size(); i++) {
			newlist.next = new ListNode(mylist.get(i).val);
			newlist = newlist.next;
		}
		
		return dummy.next;
	}
	
	public static List<ListNode> transformListNodeInArray(ListNode head) {
		List<ListNode> mylist = new ArrayList<>();
		
		while (head != null) {
			mylist.add(head);
			head = head.next;
		}
		
		return mylist;
	}
	
	public static ListNode solution02(ListNode head2, int k) {
		ListNode p1 = null;
		ListNode p2 = head2;
		ListNode result = head2;
		
		while (--k > 0) {
			head2 = head2.next;
		}
		
		p1 = head2;
		
		while (head2.next != null) {
			p2 = p2.next;
			head2 = head2.next;
		}
		
		int temp = p2.val;
		p2.val = p1.val;
		p1.val = temp;
		
		return result;
	}
	
	public static void printNode(ListNode head) {
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
	}
	
	static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
}
