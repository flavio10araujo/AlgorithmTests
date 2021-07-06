package General.TwoPointers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a sorted list of numbers, remove duplicates and return the new length.
 * You must do this in-place and without using extra memory.
 * 
 * Input: `0, 0, 1, 1, 1, 2, 2`
 * Your function should modify the list in place so the first 3 elements becomes `0, 1, 2`.
 */
public class RemoveDuplicates {

	public static int removeDuplicates(List<Integer> arr) {
		int slow = 0;
		
        for (int fast = 0; fast < arr.size(); fast++) {
            if (arr.get(fast) != arr.get(slow)) {
                slow++;
                arr.set(slow, arr.get(fast));
            }
        }
        
        return slow + 1;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        List<Integer> arr = splitWords("0 0 1 1 1 2 2").stream().map(Integer::parseInt).collect(Collectors.toList());
        int res = removeDuplicates(arr);
        System.out.println(arr.stream().limit(res).map(String::valueOf).collect(Collectors.joining(" ")));
    }
}