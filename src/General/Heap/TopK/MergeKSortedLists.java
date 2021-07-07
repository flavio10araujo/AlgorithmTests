package General.Heap.TopK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Given k sorted lists of numbers, merge them into one sorted list.
 * 
 * Input: [[1, 3, 5], [2, 4, 6], [7, 10]]
 * Output: [1, 2, 3, 4, 5, 6, 7, 10]
 */
public class MergeKSortedLists {

	private static class Element {
        private int val;
        private List<Integer> currentList;
        private int headIndex;

        public Element(int val, List<Integer> currentList, int headIndex) {
            this.val = val;
            this.currentList = currentList;
            this.headIndex = headIndex;
        }
    }

    public static List<Integer> mergeKSortedLists(List<List<Integer>> lists) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Element> heap = new PriorityQueue<>(lists.size(), Comparator.comparingInt(e -> e.val));
        
        // push first number of each list into the heap
        for (List<Integer> list : lists) {
            heap.add(new Element(list.get(0), list, 0));  // 1
        }
        
        while (!heap.isEmpty()) {
            Element e = heap.poll();
            res.add(e.val);
            int headIndex = e.headIndex + 1;
            // if there are more numbers in the list, push into the heap
            if (headIndex < e.currentList.size()) {
                heap.add(new Element(e.currentList.get(headIndex), e.currentList, headIndex));
            }
        }
        
        return res;
    }
    
    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    /**
     * 3
     * 1 3 5
     * 2 4 6
     * 7 10
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int listsLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> lists = new ArrayList<>();
        
        for (int i = 0; i < listsLength; i++) {
            lists.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        
        scanner.close();
        List<Integer> res = mergeKSortedLists(lists);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}