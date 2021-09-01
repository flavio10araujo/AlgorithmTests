package General;

import java.util.Stack;

/**
 * Given pointer to the head node of a linked list, the task is to reverse the linked list. 
 * We need to reverse the list by changing the links between nodes.
 * 
 * Example:
 * Input: Head of following linked list 
 * 		  1->2->3->4->NULL 
 * Output: Linked list should be changed to, 
 * 		  4->3->2->1->NULL
 * 
 * Solution 01 (with pointers):
 * 
 * Initialize three pointers: 
 * 		prev as NULL, 
 * 		curr as head, 
 * 		next as NULL.
 * 
 * Iterate through the linked list. 
 * In loop, do following. 
 * 		Before changing next of current, store next node 
 * 			next = curr->next
 * 		Now change next of current 
 * 		This is where actual reversing happens 
 * 			curr->next = prev 
 * 		Move prev and curr one step forward 
 * 			prev = curr 
 * 			curr = next
 * 
 * Solution 02 (with a stack):
 * 
 * - Store the nodes (values and address) in the stack until all the values are entered.
 * - Once all entries are done, update the Head pointer to the last location (i.e the last value).
 * - Start popping the nodes (value and address) and store them in the same order until the stack is empty.
 * - Update the next pointer of last Node in the stack by NULL.
 * 
 * https://www.geeksforgeeks.org/reverse-a-linked-list/
 * 
 */
public class ReverseALinkedList {
	static Node head;
	 
	public static void main(String[] args) {
    	ReverseALinkedList list = new ReverseALinkedList();
        
    	list.head = new Node(1);
        list.head.next = new Node(2);
        list.head.next.next = new Node(3);
        list.head.next.next.next = new Node(4);
 
        System.out.println("Given Linked list:");
        
        list.printList(head);
        
        head = list.reverseWithPointers();
        //head = list.reverseWithStack();
        
        System.out.println("");
        System.out.println("Reversed linked list:");
        
        list.printList(head);
    }
    
    public void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }
	
	public static class Node {
        int data;
        Node next;
 
        Node(int d) {
            data = d;
            next = null;
        }
    }
 
    // ### Solution 01 - using pointers - begin ###
	public Node reverseWithPointers() {
        Node prev = null;
        Node current = head;
        Node next = null;
        
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        return prev;
    }
	// ### Solution 01 - end ###
    
    // ### Solution 02 - using a stack - begin ###
    public Node reverseWithStack() {
        // Create a stack "s" of Node type.
        Stack<Node> s = new Stack<>();
        Node temp = head;
        
        while (temp.next != null) {
            // Push all the nodes in to stack.
            s.add(temp);
            temp = temp.next;
        }
        
        head = temp;
       
        while (!s.isEmpty()) {
            // Store the top value of stack in list.
            temp.next = s.peek();

            // Pop the value from stack.
            s.pop();

            // Update the next pointer in the list.
            temp = temp.next;
        }
        
        temp.next = null;
        
        return head;
    }
    // ### Solution 02 - end ###
}