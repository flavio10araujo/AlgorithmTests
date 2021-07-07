package General.Heap.TopK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Given a list of numbers, return top 3 smallest numbers.
 */
public class ThreeSmallestNumbers {
	public static List<Integer> heapTop3(List<Integer> arr) {
        
		Queue<Integer> queue = new PriorityQueue<Integer>(arr);
		
		System.out.println("Priority queue: " + queue);
		
		List<Integer> lista = new ArrayList<Integer>();
		
		lista.add(queue.poll());
		lista.add(queue.poll());
		lista.add(queue.poll());
		
        return lista;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        List<Integer> arr = splitWords("50 40 60 30 120 130 31 62").stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> res = heapTop3(arr);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}