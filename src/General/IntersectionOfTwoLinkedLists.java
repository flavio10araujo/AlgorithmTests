package General;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 * 
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. 
 * If the two linked lists have no intersection at all, return null.
 * For example, the following two linked lists begin to intersect at node c1:
 * 
 * A:         [a1] -> [a2] 
 *                         \
 *                          [c1] -> [c2] -> [c3]                 
 *                         /
 * B: [b1] -> [b2] -> [b3]
 * 
 * The test cases are generated such that there are no cycles anywhere in the entire linked structure.
 * Note that the linked lists must retain their original structure after the function returns.
 * 
 * Custom Judge:
 * 
 * The inputs to the judge are given as follows (your program is not given these inputs):
 * - intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
 * - listA - The first linked list.
 * - listB - The second linked list.
 * - skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
 * - skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
 * - The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program. If you correctly return the intersected node, then your solution will be accepted.
 * 
 * Example 1:
 * 
 * A:         [4] -> [1] 
 *                      \
 *                       [8] -> [4] -> [5]                 
 *                      /
 * B: [5] -> [6] -> [1]
 * 
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * Output: Intersected at '8'
 * Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
 * From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 * 
 * 
 */
public class IntersectionOfTwoLinkedLists {

	public static void main(String[] args) {
		ListNode headA;
		headA = new ListNode(4);
		headA.next = new ListNode(1);
		
		ListNode headB;
		headB = new ListNode(5);
		headB.next = new ListNode(6);
		headB.next.next = new ListNode(1);
		
		ListNode intersec = new ListNode(8);
		intersec.next = new ListNode(4);
		intersec.next.next = new ListNode(5);
		
		headA.next.next = intersec;
		headB.next.next.next = intersec;
		
		ListNode res = solution02(headA, headB);
		System.out.println(res.val);
	}

	/**
	 * Time complexity: O(n).
	 * Space complexity: O(n).
	 * @param headA
	 * @param headB
	 * @return
	 */
	public static ListNode solution01(ListNode headA, ListNode headB) {
		Set<ListNode> myset = new HashSet<>();

		ListNode nextA = headA;

		while (nextA != null) {
			myset.add(nextA);
			nextA = nextA.next;
		}

		ListNode nextB = headB;
		ListNode res = null;

		while(nextB != null) {
			if (myset.contains(nextB)) {
				res = nextB;
				break;
			}

			nextB = nextB.next;
		}

		return res;
	}
	
	/**
	 * Time complexity: O(n).
	 * Space complexity: O(1).
	 * @param headA
	 * @param headB
	 * @return
	 */
	public static ListNode solution02(ListNode headA, ListNode headB) {
		ListNode pointerA = headA;
		ListNode pointerB = headB;
		
		while(pointerA != pointerB) {
			if (pointerA == null) {
				pointerA = headB;
			} else {
				pointerA = pointerA.next;
			}
			
			if (pointerB == null) {
				pointerB = headA;
			} else {
				pointerB = pointerB.next;
			}
		}
		
		return pointerA;
	}

	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
}