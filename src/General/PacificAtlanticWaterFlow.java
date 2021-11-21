package General;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 * 
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. 
 * The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 * The island is partitioned into a grid of square cells. 
 * You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, 
 * and west if the neighboring cell's height is less than or equal to the current cell's height. 
 * Water can flow from any cell adjacent to an ocean into the ocean.
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) 
 * to both the Pacific and Atlantic oceans.
 * 
 * Example 01:
 * Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * 
 * Example 2:
 * Input: heights = [[2,1],[1,2]]
 * Output: [[0,0],[0,1],[1,0],[1,1]]
 * 
 * Constraints:
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 105
 */
public class PacificAtlanticWaterFlow {

	public static void main(String[] args) {
		int[][] heights = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
		//int[][] heights = {{2,1},{1,2}};
		//int[][] heights = {{99,99,99,99,18,99,99,99}, {95,94,93,99,19,98,98,99}, {95,94,93,98,13,13,13,12}, {99,99,99,99,99,99,99,99}}; 

		long startTime = System.nanoTime();
		List<List<Integer>> res = solution01(heights);
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
		
		for (List<Integer> lista : res) {
			System.out.print(lista + " ");
		}
		
		System.out.println("");System.out.println("");
		
		startTime = System.nanoTime();
		List<List<Integer>> resSol02 = solution02(heights);
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);

		for (List<Integer> lista : resSol02) {
			System.out.print(lista + " ");
		}
	}
	
	public static void printMatrixBoolean(boolean[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			
			System.out.println("");
		}
	}

	/**
	 * Approach: doing the DFS in every cell twice (once for Pacific and other - if Pacific is true - for Atlantic).
	 * Time complexity: O(n ^ 2).
	 * @param heights
	 * @return
	 */
	public static List<List<Integer>> solution01(int[][] heights) {
		if (heights.length == 0) {
			return new ArrayList<>();
		}

		List<List<Integer>> res = new ArrayList<>();

		int rows = heights.length;
		int cols = heights[0].length;

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				Set<String> visited = new HashSet<>();
				boolean found = search(heights, rows, cols, 0, visited, row, col, row, col, true);

				if (found) {
					visited = new HashSet<>();

					found = search(heights, rows, cols, 1, visited, row, col, row, col, true);

					if (found) {
						res.add(new ArrayList<>(Arrays.asList(row, col)));
					}
				}
			}
		}

		return res;
	}

	public static boolean search(int[][] heights, 
			int rows, 
			int cols, 
			int oceanType, 
			Set<String> visited,
			int rowBefore,
			int colBefore,
			int rowAfter, 
			int colAfter,
			boolean firstTime) {

		if (visited.contains(rowAfter+","+colAfter)) {
			return false;
		}

		// pacific
		if (oceanType == 0) {
			if (rowAfter <= 0 || colAfter <= 0) {
				if (heights[rowAfter][colAfter] <= heights[rowBefore][colBefore]) {
					return true;
				}
			}

			if (!firstTime && (rowAfter >= rows || colAfter >= cols)) {
				return false;
			}
		} 
		// atlantic
		else {
			if (rowAfter >= rows - 1 || colAfter >= cols - 1) {
				if (heights[rowAfter][colAfter] <= heights[rowBefore][colBefore]) {
					return true;
				}
			}

			if (rowAfter < 0 || colAfter < 0) {
				return false;
			}
		}

		if (heights[rowAfter][colAfter] > heights[rowBefore][colBefore]) {
			return false;
		}

		visited.add(rowAfter+","+colAfter);

		// left
		boolean found = search(heights, rows, cols, oceanType, visited, rowAfter, colAfter, rowAfter, colAfter - 1, false);

		if (!found) {
			// up
			found = search(heights, rows, cols, oceanType, visited, rowAfter, colAfter, rowAfter - 1, colAfter, false);

			if (!found) {
				// right
				found = search(heights, rows, cols, oceanType, visited, rowAfter, colAfter, rowAfter, colAfter + 1, false);

				if (!found) {
					// down
					found = search(heights, rows, cols, oceanType, visited, rowAfter, colAfter, rowAfter + 1, colAfter, false);
				}
			}
		}

		return found;
	}

	/**
	 * Time complexity: O(mn).
	 * Space complexity: O(mn).
	 * @param heights
	 * @return
	 */
	public static List<List<Integer>> solution02(int[][] heights) {
		if (heights.length == 0) {
			return new ArrayList<>();
		}

		List<List<Integer>> res = new ArrayList<>();
		
		int rows = heights.length;
		int cols = heights[0].length;
		
		boolean[][] seenP = new boolean[rows][cols];
		boolean[][] seenA = new boolean[rows][cols];
		
		for (int i = 0; i < rows; ++i) {
			solution02DFS(heights, i, 0, 0, seenP);
			solution02DFS(heights, i, cols - 1, 0, seenA);
		}
		
		printMatrixBoolean(seenP);
		System.out.println("-----");
		printMatrixBoolean(seenA);
		System.out.println("-----");

		for (int j = 0; j < cols; ++j) {
			solution02DFS(heights, 0, j, 0, seenP);
			solution02DFS(heights, rows - 1, j, 0, seenA);
		}
		
		printMatrixBoolean(seenP);
		System.out.println("-----");
		printMatrixBoolean(seenA);
		System.out.println("-----");

		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				if (seenP[i][j] && seenA[i][j]) {
					res.add(new ArrayList<>(Arrays.asList(i, j)));
				}
			}
		}

		return res;
	}

	private static void solution02DFS(int[][] matrix, int i, int j, int h, boolean[][] seen) {
		if (i < 0 || i == matrix.length || j < 0 || j == matrix[0].length) {
			return;
		}
		
		if (seen[i][j] || matrix[i][j] < h) {
			return;
		}

		seen[i][j] = true;
		
		solution02DFS(matrix, i + 1, j, matrix[i][j], seen);
		solution02DFS(matrix, i - 1, j, matrix[i][j], seen);
		solution02DFS(matrix, i, j + 1, matrix[i][j], seen);
		solution02DFS(matrix, i, j - 1, matrix[i][j], seen);
	}
}