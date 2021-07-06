package General.TwoPointers.OppositeDirection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a list of non-negative integers representing elevations of columns, 
 * and assuming each column is of equal width of 1, 
 * find how much water is trapped in the columns after a rain. 
 * Left and right boundaries outside of the columns have 0 elevations.
 * 
 * Input: [3, 2, 1, 2, 2, 3, 2] Output: 5
 * 
 * X . . . . X
 * X X . X X X X
 * X X X X X X X
 * 
 */
public class TrappingRainWater {
	public static int trappingRainWater(List<Integer> elevations) {
        
		int n = elevations.size();
        int[] leftWalls = new int[n];
        int[] rightWalls = new int[n];

        int maxLeftWall = 0;
        for (int i = 0; i < n; i++) {
            leftWalls[i] = maxLeftWall;
            maxLeftWall = Math.max(elevations.get(i), maxLeftWall);  // 1
        }

        int maxRightWall = 0;
        for (int i = n - 1; i >= 0; i--) {
            rightWalls[i] = maxRightWall;
            maxRightWall = Math.max(elevations.get(i), maxRightWall);  // 1
        }

        int totalWater = 0;
        for (int i = 0; i < n; i++) {
            int elevation = elevations.get(i);
            int lowestWall = Math.min(leftWalls[i], rightWalls[i]);
            if (lowestWall > elevation) {  // 2
                totalWater += lowestWall - elevation;
            }
        }

        return totalWater;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        List<Integer> elevations = splitWords("2 1 2 2 3 2").stream().map(Integer::parseInt).collect(Collectors.toList());
        int res = trappingRainWater(elevations);
        System.out.println(res);
    }
}