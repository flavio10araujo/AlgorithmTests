package Tests;

/**
 * Input: 2, 4, 3, 8, 6, 1
 * Output: 24
 * Explanation: (2+1) + (4+6) + (3+8) = 24.
 * Constraint: constant space complexity. 
 *
 */
public class AmazonSumNodes {

	public static void main(String[] args) {
		Node node = new Node(2);
		node.next = new Node(4);
		node.next.next = new Node(3);
		node.next.next.next = new Node(8);
		node.next.next.next.next = new Node(6);
		node.next.next.next.next.next = new Node(1);
		
		printNode(node);
		
		System.out.println(sumNodes(node));
	}
	
	public static int sumNodes(Node node) {
		
		if (node == null) {
			return 0;
		}
		
		int sum = 0;
		int count = countNodes(node);
		
		Node invertedNode = invertNode(node, count);
		
		printNode(invertedNode);
		
		Node right = step(invertedNode, count);
		
		while(right != null) {
			if (invertedNode != right) {
				sum += invertedNode.val + right.val;
			} else {
				sum += right.val;
			}
			
			invertedNode = invertedNode.next;
			right = right.next;
		}
		
		return sum;
	}
	
	public static Node step(Node node, int n) {
        Node ans = node;
        
        if (n % 2 == 0) {
			n = n / 2;
		} else {
			n = (n + 1) / 2; 
		}
        
        while(n > 0 && ans != null) {
            ans = ans.next;
            n--;
        }
        
        return ans;
    }
	
	public static Node invertNode(Node node, int n) {
		if (n < 3) {
			return node;
		}
		
		if (n % 2 == 0) {
			n = n / 2;
		} else {
			n = (n + 1) / 2; 
		}
		
		Node dummy = new Node(0);
		dummy.next = node;
		Node left = node;
		
		Node prev = null;
		Node current = null;
		Node next = null;
		
		n--;
		
		while(n > 0) {
			left = left.next;
			n--;
		}
		
		current = left.next;
		
		while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
		
		left.next = prev;
        
        return dummy.next;
	}
	
	public static int countNodes(Node node) {
		int count = 0;
		
		while(node != null) {
			count++;
			node = node.next;
		}
		
		return count;
	} 

	public static void printNode(Node node) {
		while(node != null) {
			System.out.print(node.val + " ");
			node = node.next;
		}
		
		System.out.println(" ");
	}
	
	static class Node {
		int val;
		Node next;
		
		public Node(int val) {
			this.val = val;
		}
	}
}