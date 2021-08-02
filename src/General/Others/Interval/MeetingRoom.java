package General.Others.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...](si< ei), 
 * determine if a person could attend all meetings.
 * 
 * Example: 1
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 * 
 * Example: 2
 * Input: intervals = [[7,10],[2,4]]
 * Output: true
 * 
 * Solution:
 * 
 * This problem basically asks if there's any overlap between adjacent intervals. 
 * We sort the intervals by start time and use the technique from the intro section to check overlap.
 * Time Complexity: O(n*log(n))
 * Let n denote the size of the intervals array.
 */
public class MeetingRoom {

	public static boolean meetingRooms(List<List<Integer>> intervals) {
		
        intervals.sort(Comparator.comparingInt(p -> p.get(0)));

        for (int i = 0; i < intervals.size() - 1; i++) {
            if (intervals.get(i).get(1) > intervals.get(i + 1).get(0)) {
                return false;
            }
        }

        return true;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 3
    // 0 30
    // 5 10
    // 15 20
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int intervalsLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> intervals = new ArrayList<>();
        
        for (int i = 0; i < intervalsLength; i++) {
            intervals.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        
        scanner.close();
        boolean res = meetingRooms(intervals);
        System.out.println(res);
    }
}