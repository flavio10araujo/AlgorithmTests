package General.LinkedList.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a linked list with potentially a loop, determine whether the linked list from the first node contains a cycle in it. 
 * 
 * For bonus points, do this with constant space.
 * 
 * Parameters
 * nodes: The first node of a linked list with potentially a loop.
 * Result
 * Whether there is a loop contained in the linked list.
 * 
 * Example
 * Input:
 * 	0 -> 1 -> 2 -> 3 -> 4 -> 1
 * Output:
 * true
 * 
 * Example
 * Input:
 * 	0 -> 1 -> 2 -> 3 -> 4
 * Output:
 * false
 * 
 * Constraints
 * 1 <= len(nodes) <= 10^5
 * 
 * Solution:
 * If a linked list has no loop, then when we iterate through the list, we will eventually reach the end of the list, at which point we can simply return. 
 * However, the challenge is figuring out how to terminate the program if it finds a loop. Otherwise the program would go on forever.
 * A simple solution would be to use a set to store the information. 
 * We store all the nodes we have been through and check if we have been there each time we move. 
 * However, a set is not constant memory, and there might be issues with hashing and whatnot. Clearly there is a better way.
 * Introducing Floyd's Cycle Finding Algorithm, also known as the Tortoise and Hare Algorithm. 
 * The idea is to have two pointers, the fast pointer (or "hare") moves at double speed of the slow pointer (or "tortoise"). 
 * Each cycle, the tortoise moves once and the hare moves twice. 
 * The idea is that since the cycle must have integer size, when the tortoise and the hare are both in the cycle, 
 * their distance difference must also be an integer. 
 * Then, each cycle, because of their speed difference, the distance between them constantly reduces until they meet, 
 * in which case we know there is a cycle. 
 * However, if there is no cycle, they will never meet because the speed of the hare is always faster.
 * 
 * Time Complexity: O(n)
 * Technically O(n/2) but again we factor out the constant and we are left with linear time. 
 * Worst case we have, O(2n) as the small pointer moves around the entire array once. Either way we have O(n) time complexity.
 */
public class LinkedListCycle {

	public static class Node<T> {
        public T val;
        public Node<T> next;

        public Node(T val) {
            this(val, null);
        }

        public Node(T val, Node<T> next) {
            this.val = val;
            this.next = next;
        }
    }

    public static Node<Integer> nextNode(Node<Integer> node) {
        if (node.next == null) {
            return node;
        }
        
        return node.next;
    }

    public static boolean hasCycle(Node<Integer> nodes) {
        Node<Integer> tortoise = nextNode(nodes);
        Node<Integer> hare = nextNode(nextNode(nodes));
        
        while (tortoise != hare && hare.next != null) {
            tortoise = nextNode(tortoise);
            hare = nextNode(nextNode(hare));
        }
        
        return hare.next != null;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }
    
    public static void main(String[] args) {
        List<Integer> rawInput = splitWords("1 2 3 4 1").stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Node<Integer>> nodesList = new ArrayList<>();
        
        for (int i = 0; i < rawInput.size(); i++) {
            nodesList.add(new Node<Integer>(i));
        }
        
        for (int i = 0; i < rawInput.size(); i++) {
            if (rawInput.get(i) != -1) {
                nodesList.get(i).next = nodesList.get(rawInput.get(i));
            }
        }
        
        Node<Integer> nodes = nodesList.get(0);
        boolean res = hasCycle(nodes);
        System.out.println(res);
    }
}