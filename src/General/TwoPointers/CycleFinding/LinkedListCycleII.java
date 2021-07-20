package General.TwoPointers.CycleFinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This question is the same as Linked List Cycle, except in addition to checking whether a linked list has a loop, we also find the size of the loop, 
 * if applicable.
 * 
 * Parameters
 * nodes: The first node of a linked list with potentially a loop.
 * Result
 * An integer representing the size of the loop, if there is one. If there is no loop, output -1.
 * 
 * Example 1
 * Input: 0 -> 1 -> 2 -> 3 -> 4 -> 1
 * Output: 4
 * 
 * Example 2
 * Input: 0 -> 1 -> 2 -> 3 -> 4
 * Output: -1
 * 
 * Constraints: 1 <= len(nodes) <= 10^5
 */
public class LinkedListCycleII {

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

    public static int cycleSize(Node<Integer> nodes) {
        
        Node<Integer> tortoise = nextNode(nodes);
        Node<Integer> hare = nextNode(nextNode(nodes));
        
        while (tortoise != hare && hare.next != null) {
            tortoise = nextNode(tortoise);
            hare = nextNode(nextNode(hare));
        }
        
        if (hare.next == null) {
            return -1;
        }
        
        int count = 0;
        
        do {
            count++;
            tortoise = nextNode(tortoise);
            hare = nextNode(nextNode(hare));
        } while (tortoise != hare);
        
        return count;
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
        int res = cycleSize(nodes);
        System.out.println(res);
    }
}