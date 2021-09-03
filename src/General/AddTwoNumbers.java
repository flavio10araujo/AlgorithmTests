package General;

/**
 * https://leetcode.com/problems/add-two-numbers/
 * 
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order, and each of their nodes contains a single digit. 
 * Add the two numbers and return the sum as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * 
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * 
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * 
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 * 
 * Solution:
 * 
 * 1 - Set two pointers, one for each input list. Create head of new list.
 * 2 - Add values that our pointers are at.
 * 3 - Check if there is a carry, add 1.
 * 4 - Check if sum >= 10, if it is, mod it and set carry flag = true.
 * 5 - Move pointers forward.
 * 6 - After the loop, check if carry is true. If is is, add additional node to result list.
 * 7 - Return result list.
 */
public class AddTwoNumbers {

	public static void main(String[] args) {
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);
		
		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);
		
		ListNode res = addTwoNumbers(l1, l2);
		
		while (res != null) {
			System.out.println(res.val);
			res = res.next;
		}
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummyHead = new ListNode(0);
		ListNode l3 = dummyHead;
		int carry = 0;
		
		while(l1 != null && l2 != null) {
			// Creating two pointers.
			int l1Val = (l1 != null) ? l1.val : 0; // Pointer for the first list.
			int l2Val = (l2 != null) ? l2.val : 0; // Pointer for the second list.
			
			int currentSum = l1Val + l2Val + carry;
			
			carry = currentSum / 10;
			
			int lastDigit = currentSum % 10;
			
			System.out.println("l1Val="+l1Val+" l2Val="+l2Val+" currentSum="+currentSum+" carry="+carry+" lastDigit="+lastDigit);
			
			ListNode newNode = new ListNode(lastDigit);
			
			l3.next = newNode;
			
			if (l1 != null) {
				l1 = l1.next;
			}
			
			if (l2 != null) {
				l2 = l2.next;
			}
			
			l3 = l3.next;
		}
		
		if (carry > 0) {
			ListNode newNode = new ListNode(carry);
			l3.next = newNode;
			l3 = l3.next;
		}
		
		return dummyHead.next;
	}

	/**
	 * Definition for singly-linked list.
	 */
	public static class ListNode {
		int val;
		ListNode next;
		
		ListNode() {}
		
		ListNode(int val) { 
			this.val = val; 
		}
		
		ListNode(int val, ListNode next) { 
			this.val = val; this.next = next; 
		}
	}
}