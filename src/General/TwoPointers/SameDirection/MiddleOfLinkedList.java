package General.TwoPointers.SameDirection;

public class MiddleOfLinkedList {

	public static ListNode middleOfLinkedList(ListNode head) {
		ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        return slow;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        
        for (String val : "0 1 2 3 4".split(" ")) {
            ListNode node = new ListNode(Integer.parseInt(val));
            cur.next = node;
            cur = node;
        }
        
        System.out.println(middleOfLinkedList(dummy.next).val);
    }
}