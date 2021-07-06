package General.TwoPointers.SameDirection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an array of integers, move all the 0s to the back of the array while maintaining the relative order of the non-zero elements.
 * Do this in-place using constant auxiliary space.
 * 
 * Input: [1, 0, 2, 0, 0, 7]
 * Output: [1, 2, 7, 0, 0, 0]
 */
public class MoveZeros {

	public static void moveZeros(List<Integer> nums) {
        int slow = 0;
        
        for (int fast = 0; fast < nums.size(); fast++) {
            if (nums.get(fast) != 0) {
                int slowNum = nums.get(slow);
                nums.set(slow, nums.get(fast));
                nums.set(fast, slowNum);
                slow++;
            }
        }
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        List<Integer> nums = splitWords("1 0 2 0 0 7").stream().map(Integer::parseInt).collect(Collectors.toList());
        moveZeros(nums);
        System.out.println(nums.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}