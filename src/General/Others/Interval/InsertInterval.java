package General.Others.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 * 
 * Example: 1
 * Input: 
 * intervals = [[1,3],[6,9]]
 * newInterval = [2,5]
 * Output: [[1,5], [6,9]]
 * 
 * Example: 2
 * Input:
 * intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]]
 * newInterval = [4,8]
 * Output: [[1, 2], [3, 10], [12, 16]]
 * 
 * Example: 3
 * Input:
 * intervals = []
 * newInterval = [5,7]
 * Output: [[5,7]]
 * 
 * Example: 4
 * Input:
 * intervals = [[1,5]]
 * newInterval = [2,3]
 * Output: [[1,5]]
 * 
 * Example: 5
 * Input:
 * intervals = [[1,5]]
 * newInterval = [2,7]
 * Output: [[1,7]]
 * 
 * Solution:
 * 
 * The easiest way to solve this problem is to insert the interval to be inserted to the end, and now the problem has become a Merge Intervals problem :).
 * Literally the only thing we need to do is to add the new interval to the end and copy paste the code we had in the merge interval problem.
 * 
 * O(n) solution
 * 
 * The solution above is O(nlog(n)) because of the re-sorting of the entire array. 
 * Since log(n) grows so slowly, most of the time with small ns it can considered a constant. 
 * But there is also an O(n) solution to the problem.
 * For intervals whose end time is before the start time of the new interval, add them directly to the final result.
 * For intervals that overlap with the new interval, expand the new interval's start and end time.
 * Add the new interval to the final results.
 * For intervals whose start time is after the end of the new interval, add them directly to the final result.
 */
public class InsertInterval {

	public static List<List<Integer>> insertInterval(List<List<Integer>> intervals, List<Integer> newInterval) {
        
		List<List<Integer>> r = new ArrayList<>();

        int i = 0;
        int n = intervals.size();
        
        // For intervals whose end time is before the start time of the new interval, add them directly to the final result.
        while (i < n && intervals.get(i).get(1) < newInterval.get(0)) {
            r.add(intervals.get(i));
            i++;
        }

        // For intervals that overlap with the new interval, expand the new interval's start and end time.
        while (i < n && intervals.get(i).get(0) <= newInterval.get(1)) {
            newInterval.set(0, Math.min(newInterval.get(0), intervals.get(i).get(0)));
            newInterval.set(1, Math.max(newInterval.get(1), intervals.get(i).get(1)));
            i++;
        }
        
        // Add the new interval to the final results.
        r.add(newInterval);

        // For intervals whose start time is after the end of the new interval, add them directly to the final result.
        while (i < n) {
            r.add(intervals.get(i));
            i++;
        }

        return r;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 2
    // 1 3
    // 6 9
    // 2 5
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int intervalsLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> intervals = new ArrayList<>();
        
        for (int i = 0; i < intervalsLength; i++) {
            intervals.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        
        List<Integer> newInterval = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        scanner.close();
        List<List<Integer>> res = insertInterval(intervals, newInterval);
        
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}