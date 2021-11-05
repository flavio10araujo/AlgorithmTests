package General.Others.Interval;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/non-overlapping-intervals/
 * 
 * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * 
 * Example 1:
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 * 
 * Example 2:
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 * 
 * Example 3:
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 * 
 * Constraints:
 * 1 <= intervals.length <= 105
 * intervals[i].length == 2
 * -5 * 104 <= starti < endi <= 5 * 104
 */
public class NonOverlappingIntervals {

	public static void main(String[] args) {
		int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
		//int[][] intervals = {{1,2},{1,3},{2,3},{3,4}};

		System.out.println(solution02(intervals));
	}

	/**
	 * Sorting by the end of the intervals.
	 * @param intervals
	 * @return
	 */
	public static int solution01(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

		int currentEnd = intervals[0][1];
		int ans = 0;

		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i][0] >= currentEnd) {
				currentEnd = intervals[i][1];
			}
			else {
				ans++;
			}
		}

		return ans;
	}
	
	/**
	 * Sorting by the start of the intervals.
	 * @param intervals
	 * @return
	 */
	public static int solution02(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

		int currentEnd = intervals[0][1];
		int ans = 0;

		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i][0] >= currentEnd) {
				currentEnd = intervals[i][1];
			}
			else {
				currentEnd = Math.min(intervals[i][1], currentEnd);
				ans++;
			}
		}

		return ans;
	}
}