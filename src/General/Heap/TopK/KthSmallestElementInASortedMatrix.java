package General.Heap.TopK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * 
 * Input
 * matrix = 
 * [
 *  [1,  5,  9],
 *  [10, 11, 13],
 *  [12, 13, 15]
 * ],
 * k = 8,
 * Output: 13
 * 
 * Note: You may assume k is always valid, 1 ≤ k ≤ n^2. You may also assume that 1 <= n <= 1000.
 */
public class KthSmallestElementInASortedMatrix {
	public static int kthSmallest(List<List<Integer>> matrix, int k) {
		int n = matrix.size();
        // Keeps track of row and column numbers of items in the heap
        // The smallest item represented by the row and column number is added to the top
        Queue<int[]> heap = new PriorityQueue<>(
            (a, b) -> Integer.compare(matrix.get(a[0]).get(a[1]), matrix.get(b[0]).get(b[1]))
            );
        heap.offer(new int[]{0, 0});
        // Keeps track of the top of each row that is not processed
        int[] columnTop = new int[n];
        // Keeps track of the first number each row not processed
        int[] rowFirst = new int[n];
        // Repeat the process k - 1 times.
        
        while (k > 1) {
            k--;
            int[] coords = heap.poll();
            int row = coords[0], column = coords[1];
            rowFirst[row] = column + 1;
            // Add the item on the right to the heap if everything above it is processed
            if (column + 1 < n && columnTop[column + 1] == row) {
                heap.offer(new int[]{row, column + 1});
            }
            columnTop[column] = row + 1;
            // Add the item below it to the heap if everything before it is processed
            if (row + 1 < n && rowFirst[row + 1] == column) {
                heap.offer(new int[]{row + 1, column});
            }
        }
        int[] resCoords = heap.poll();
        return matrix.get(resCoords[0]).get(resCoords[1]);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int matrixLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> matrix = new ArrayList<>();
        
        for (int i = 0; i < matrixLength; i++) {
            matrix.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        
        int k = Integer.parseInt(scanner.nextLine());
        scanner.close();
        int res = kthSmallest(matrix, k);
        System.out.println(res);
    }
}