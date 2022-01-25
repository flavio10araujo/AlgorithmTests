package General.LinkedList.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This question is the same as Linked List Cycle, except in addition to checking whether a linked list has a loop, 
 * we also find the entry point to the loop, if possible.
 * 
 * Parameters
 * nodes: The first node of a linked list with potentially a loop.
 * Result
 * An integer representing the value contained by the entry point to the loop, if there is one. If there is no loop, output -1.
 * 
 * Example 1
 * Input: 0 -> 1 -> 2 -> 3 -> 4 -> 1
 * Output: 1
 * 
 * Example 2
 * Input: 0 -> 1 -> 2 -> 3 -> 4
 * Output: -1
 * 
 * Solution:
 * 
 * The basic idea is the same as in Linked List Cycle, except we need to do some more special steps to get there. After they meet up,
 *  reset one pointer to the starting location, and move the pointer each cycle until they meet up, except they both move at the same speed this time. 
 *  Their meeting point is the starting location of the loop.
 *  
 *  How does this work? It involves a bit of math.
 *  Suppose the starting point of the cycle is s, and suppose the tortoise travelled k nodes. 
 *  Then the hare travelled 2 * k nodes. 
 *  Then suppose that the size of the loop is r, then the difference between the distances that the two pointers travelled must be a multiple of r. 
 *  That is, k % r == 0. In that case, after the two pointers both moved s steps, they must both be inside the cycle as k + s >= s 
 *  (which by definition is the entry point of the cycle). 
 *  Furthermore, note that (k + s) % r == s % r, meaning that they must both meet at the same point inside the circle. 
 *  Therefore, s must be the smallest number of steps for both pointer to meet again 
 *  (any smaller will mean that one pointer is outside of the cycle while the other one is inside), so to find s, we only need to move the pointers 
 *  at equal speed until they meet.
 *  
 *  Time Complexity: O(n)
 */
public class LinkedListCycleIII {

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

    public static int cycleEntryPoint(Node<Integer> nodes) {
        Node<Integer> tortoise = nextNode(nodes);
        Node<Integer> hare = nextNode(nextNode(nodes));
        
        while (tortoise != hare && hare.next != null) {
            tortoise = nextNode(tortoise);
            hare = nextNode(nextNode(hare));
        }
        
        if (hare.next == null) {
            return -1;
        }
        
        tortoise = nodes;
        
        while (tortoise != hare) {
            tortoise = nextNode(tortoise);
            hare = nextNode(hare);
        }
        
        return tortoise.val;
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
        int res = cycleEntryPoint(nodes);
        System.out.println(res);
    }
}