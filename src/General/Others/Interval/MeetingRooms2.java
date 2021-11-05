package General.Others.Interval;

import java.util.Arrays;

/**
 * Leetcode 253.
 * 
 * Given an array of meeting time intervals where intervals[i] = [starti, endi],
 * return the minimum number of conference rooms required.
 * 
 * Example 1:
 * Input: intervals = {{0, 30}, {5, 10}, {15, 20}}
 * Output: 2 
 *
 * Example 2:
 * Input: Intervals = {{7, 10}, {2, 4}}
 * Output: 1
 */
public class MeetingRooms2 {

	public static void main(String[] args) {
		//int[][] intervals = {{0,30}, {5, 10}, {15, 20}};
		int[][] intervals = {{7, 10}, {2, 4}};
		
		System.out.println(solution01(intervals));
		System.out.println(solution02(intervals));
	}

	public static int solution01(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

		int end = intervals[0][1];
		int numberOfRooms = 1;

		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i][0] < end) {
				numberOfRooms++;
				end = Math.min(end, intervals[i][1]);
			}
		}

		return numberOfRooms;
	}

	public static int solution02(int[][] intervals) {
		final int n = intervals.length;

		int ans = 0;
		int[] starts = new int[n];
		int[] ends = new int[n];

		for (int i = 0; i < n; ++i) {
			starts[i] = intervals[i][0];
			ends[i] = intervals[i][1];
		}

		Arrays.sort(starts);
		Arrays.sort(ends);

		// j points to ends
		for (int i = 0, j = 0; i < n; ++i)
			if (starts[i] < ends[j])
				++ans;
			else
				++j;

		return ans;
	}
}