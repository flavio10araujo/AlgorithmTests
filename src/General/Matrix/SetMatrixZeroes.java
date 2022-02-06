package General.Matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/set-matrix-zeroes/
 * 
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's, 
 * and return the matrix.
 * You must do it in place.
 * 
 * Example 1:
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 * 
 * Example 2:
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * 
 * Constraints:
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -2 ^ 31 <= matrix[i][j] <= 2 ^ 31 - 1
 * 
 * Follow up:
 * A straightforward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */
public class SetMatrixZeroes {

	public static void main(String[] args) {
		int[][] matrix = {{1,1,1}, {1,0,1}, {1,1,1}};
		//int[][] matrix = {{0,1,2,0}, {3,4,5,2}, {1,3,1,5}};
		
		print(matrix);
		long startTime = System.nanoTime();
		System.out.println("Solution 01:");
		solution01(matrix);
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
		print(matrix);
		
		int[][] matrix2 = {{1,1,1}, {1,0,1}, {1,1,1}};
		//int[][] matrix2 = {{0,1,2,0}, {3,4,5,2}, {1,3,1,5}};
		print(matrix2);
		startTime = System.nanoTime();
		System.out.println("Solution 02:");
		solution02(matrix2);
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
		print(matrix2);
		
		//int[][] matrix3 = {{1,2,3}, {4,0,5}, {6,7,8}};
		//int[][] matrix3 = {{1,1,0}, {1,0,1}, {1,1,1}};
		int[][] matrix3 = {{0,1,2,0}, {3,4,5,2}, {1,3,1,5}};
		print(matrix3);
		startTime = System.nanoTime();
		System.out.println("Solution 03:");
		solution03(matrix3);
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
		print(matrix3);
	}

	public static void print(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			
			System.out.println("");
		}
	}
	
	/**
	 * Time complexity: O(m * n).
	 * Space complexity: O(m * n).
	 * @param matrix
	 */
	public static void solution01(int[][] matrix) {
		List<Coordinate> coords = new ArrayList<>();
		
		// Time: O(m * n).
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					coords.add(new Coordinate(i, j));
				}
			}
		}
		
		for (int i = 0; i < coords.size(); i++) {
			int x = coords.get(i).getX();
			int y = coords.get(i).getY();
			
			// left
			while (y >= 0) {
				matrix[x][y] = 0;
				y--;
			}
			
			y = coords.get(i).getY();
			
			// up
			while (x >= 0) {
				matrix[x][y] = 0;
				x--;
			}
			
			x = coords.get(i).getX();
			
			// right
			while (y < matrix[0].length) {
				matrix[x][y] = 0;
				y++;
			}
			
			y = coords.get(i).getY();
			
			// down
			while (x < matrix.length) {
				matrix[x][y] = 0;
				x++;
			}
		}
    }
	
	static class Coordinate {
		private int x;
		private int y;
		
		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return this.x;
		}
		
		public int getY() {
			return this.y;
		}
	} 

	/**
	 * Time complexity: O(m * n).
	 * Space complexity: O(m + n).
	 * @param matrix
	 */
	public static void solution02(int[][] matrix) {
		boolean[] lines = new boolean[matrix.length];
        boolean[] cols = new boolean[matrix[0].length];
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    lines[i] = true;
                    cols[j] = true;
                }
            }
        }
        
        for (int i = 0; i < lines.length; i++) {
            if (lines[i]) {
                for (int j = 0; j < cols.length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        for (int i = 0; i < cols.length; i++) {
            if (cols[i]) {
                for (int j = 0; j < lines.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
	}
	
	/**
	 * Time complexity: O(m * n).
	 * Space complexity: O(1).
	 * @param matrix
	 */
	public static void solution03(int[][] matrix) {
		boolean firstLine = false;
		int height = matrix.length;
		int width = matrix[0].length;
		
		int n = Math.max(height, width);
		
		for (int i = 0; i < n; i++) {
			// Lines
			if (i < height) {
				for (int col = 0; col < width; col++) {
					if (matrix[i][col] == 0) {
						if (i == 0) {
							firstLine = true;
						} else {
							matrix[i][0] = 0;
						}
					}
				}
			}
			
			// Cols
			if (i < width) {
				for (int line = 0; line < height; line++) {
					if (matrix[line][i] == 0) {
						matrix[0][i] = 0;
					}
				}
			}
		}
		
		for (int i = 1; i < height; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < width; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        for (int i = 0; i < width; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 0; j < height; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        
        if (firstLine) {
        	for (int i = 0; i < width; i++) {
        		matrix[0][i] = 0;
        	}
        }
	}
}