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
 * Input: [3 2 1 2 2 3 2] Output: 5
 * 
 * X . . . . X
 * X X . X X X X
 * X X X X X X X
 * 
 * Input: [1 0 2 1 0 1 3 2 1 2 1] Output: 6
 * 
 *             X
 *     X . . . X X . X
 * X . X X . X X X X X X
 */
public class TrappingRainWater {

	public static void main(String[] args) {
		//List<Integer> elevations = splitWords("0 1 0 2 1 0 1 3 2 1 2 1").stream().map(Integer::parseInt).collect(Collectors.toList());
		//List<Integer> elevations = splitWords("1 8 6 2 5 4 8 3 7").stream().map(Integer::parseInt).collect(Collectors.toList());
		List<Integer> elevations = splitWords("4 2 0 6 2 3 1").stream().map(Integer::parseInt).collect(Collectors.toList());
		System.out.println(solution01(elevations));
		System.out.println(solution02(elevations));
	}

	public static List<String> splitWords(String s) {
		return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
	}

	/**
	 * Time complexity: O(n).
	 * Space complexity: O(n).
	 * @param elevations
	 * @return
	 */
	public static int solution01(List<Integer> elevations) {

		int n = elevations.size();
		int[] leftWalls = new int[n];
		int[] rightWalls = new int[n];
		int maxLeftWall = 0, maxRightWall = 0;

		// Registrando o muro mais alto da esquerda para cada item do array elevations. 
		for (int i = 0; i < n; i++) {
			leftWalls[i] = maxLeftWall;
			maxLeftWall = Math.max(elevations.get(i), maxLeftWall);
		}

		// Registrando o muro mais alto da direita para cada item do array elevations.
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

	/**
	 * Time complexity: O(n).
	 * Space complexity: O(1).
	 * @param elevations
	 * @return
	 */
	public static int solution02(List<Integer> height) {
		if (height.size() == 0)
			return 0;

		int ans = 0;
		int l = 0;
		int r = height.size() - 1;
		int maxL = height.get(l);
		int maxR = height.get(r);

		while (l < r)
			if (maxL < maxR) {
				ans += maxL - height.get(l);
				maxL = Math.max(maxL, height.get(++l));
			} else {
				ans += maxR - height.get(r);
				maxR = Math.max(maxR, height.get(--r));
			}

		return ans;
	}
}