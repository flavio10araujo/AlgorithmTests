package General;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * 
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * 
 * Example 2:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * 
 * Example 2:
 * Input: head = [1], n = 1
 * Output: []
 * 
 * Example 3:
 * Input: head = [1,2], n = 1
 * Output: [1]
 * 
 * Constraints:
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * 
 * Follow up: Could you do this in one pass?
 */
public class RemoveNthNodeFromEndOfList {

	public static void main(String[] args) {
		int n = 2;
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);

		ListNode res = solution02(head, n);
		
		while (res != null) {
			System.out.println(res.val);
			res = res.next;
		}
	}

	public static ListNode solution01(ListNode head, int n) {
		if (head.next == null) {
			return null;
		}

		Deque<ListNode> stack = new ArrayDeque<>();
		ListNode current = head;

		while (current != null) {
			stack.push(current);
			current = current.next;
		}

		ListNode before = null;
		current = head;
		ListNode next = head.next;

		while (n >= 0) {
			if (n == 0) {
				if (!stack.isEmpty()) {
					before = stack.pop();
					before.next = next;
				} else {
					return next;
				}

			} else {
				current = stack.pop();
				next = current.next;
			}

			n--;
		}

		return head;
	}
	
	public static ListNode solution02(ListNode head, int n) {
        if (head.next == null) {
            return null;
        }
        
        ListNode current = head;
        ListNode target = head;
        ListNode before = null;
        int count = 0;
        
        while (current != null) {
            count++;
            
            if (count > n) {
                before = target;
                target = target.next;
            }
            
            current = current.next;
        }
        
        if (count == n) {
            return head.next;
        } 
        
        before.next = target.next;
        
        return head;
    }

	public static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
}