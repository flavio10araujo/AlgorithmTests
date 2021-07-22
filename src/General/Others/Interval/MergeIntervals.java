package General.Others.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * Example: 1
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals 1,3 and 2,6 overlaps, merge them into 1,6.
 * 
 * Example: 2
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals 1,4 and 4,5 are considered overlapping.
 * 
 * Constraints:
 * intervals[i][0] <= intervals[i][1]
 * 
 * Solution:
 * 
 * To merge the intervals, we have to establish an order so that the intervals that 
 * are close to each other are ordered next to each other so we can merge them. 
 * Since an interval is represented by a start time and an end time, we really only have two ways to order them - by start time or by end time.
 * Here we sort them by start time and go through each interval. 
 * We keep a list of final intervals. 
 * For each interval in the sorted interval list, if it has an overlap with the last 
 * interval in the final interval list, we update the last interval's end position. 
 * Otherwise, we append the interval to the final interval list. 
 * 
 * Time Complexity: O(n*log(n))
 * 
 * This is because we have to sort the array.
 */
public class MergeIntervals {

	public static boolean overlap(List<Integer> a, List<Integer> b) {
        return !(a.get(1) < b.get(0) || a.get(0) > b.get(1));
    }

    public static List<List<Integer>> mergeIntervals(List<List<Integer>> intervals) {
        
    	intervals.sort((i1, i2) -> Integer.compare(i1.get(0), i2.get(0)));

        List<List<Integer>> res = new ArrayList<>();
        
        for (List<Integer> interval : intervals) {
            
        	if (res.isEmpty() || !overlap(res.get(res.size() - 1), interval)) {
                res.add(interval);
            } else {
                List<Integer> lastInterval = res.get(res.size() - 1);
                lastInterval.set(1, Math.max(lastInterval.get(1), interval.get(1)));
            }
        }

        return res;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 4
    // 1 3
    // 2 6
    // 8 10
    // 15 18
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int intervalsLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> intervals = new ArrayList<>();
        
        for (int i = 0; i < intervalsLength; i++) {
            intervals.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        
        scanner.close();
        List<List<Integer>> res = mergeIntervals(intervals);
        
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}