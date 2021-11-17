package General.Matrix;

/**
 * https://leetcode.com/problems/rotate-image/
 * 
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. 
 * DO NOT allocate another 2D matrix and do the rotation.
 * 
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 * 
 * Example 2:
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * 
 * Example 3:
 * Input: matrix = [[1]]
 * Output: [[1]]
 * 
 * Example 4:
 * Input: matrix = [[1,2],[3,4]]
 * Output: [[3,1],[4,2]]
 * 
 * Constraints:
 * matrix.length == n
 * matrix[i].length == n
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 */
public class RotateImage {

	public static void main(String[] args) {
		//int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
		
		long startTime = System.nanoTime();
		System.out.println("Solution 01:");
		int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
		printMatrix(matrix);
		solution01(matrix);
		printMatrix(matrix);
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
		
		startTime = System.nanoTime();
		System.out.println("Solution 02:");
		int[][] matrix2 = {{1,2,3},{4,5,6},{7,8,9}};
		printMatrix(matrix2);
		solution02(matrix2);
		printMatrix(matrix2);
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
		
		startTime = System.nanoTime();
		System.out.println("Solution 03:");
		int[][] matrix3 = {{1,2,3},{4,5,6},{7,8,9}};
		printMatrix(matrix3);
		solution03(matrix3);
		printMatrix(matrix3);
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	}

	/**
	 * Approach: reverse.
	 * Time complexity: O(n ^ 2).
	 * Space complexity: O(1). 
	 * @param matrix
	 */
	public static void solution01(int[][] matrix) {
		for (int i = 0, j = matrix.length - 1; i < j; i++, j--) {
			//System.out.println("Primeiro for: i="+i+" j="+j);

			int[] temp = matrix[i];
			matrix[i] = matrix[j];
			matrix[j] = temp;

			//printMatrix(matrix);
		}

		for (int i = 0; i < matrix.length; ++i) {
			for (int j = i + 1; j < matrix.length; ++j) {
				//System.out.println("Segundo for: i="+i+" j="+j);

				final int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;

				//printMatrix(matrix);
			}
		}
	}

	/**
	 * Approach: indexing.
	 * Time complexity: O(n ^ 2).
	 * Space complexity: O(1).
	 * @param matrix
	 */
	public static void solution02(int[][] matrix) {
		for (int min = 0; min < matrix.length / 2; ++min) {
			final int max = matrix.length - min - 1;
			
			for (int i = min; i < max; ++i) {
				final int offset = i - min;
				final int top = matrix[min][i];
				
				matrix[min][i] = matrix[max - offset][min];
				matrix[max - offset][min] = matrix[max][max - offset];
				matrix[max][max - offset] = matrix[i][max];
				matrix[i][max] = top;
			}
		}
	}

	public static void solution03(int[][] matrix) {
		int L = 0;
		int R = matrix.length - 1;
		
		while (L < R) {
			for (int i = 0; i < R; i++) {
				int top = L;
				int bottom = R;
				
				// Save the topLeft.
				int topLeft = matrix[top][L + i];
				
				// Move bottom left into top left.
				matrix[top][L + i] = matrix[bottom - i][L];
				
				// Move bottom right into bottom left.
				matrix[bottom - i][L] = matrix[bottom][R - i];
				
				// Move top right into bottom right.
				matrix[bottom][R - i] = matrix[top + i][R];
				
				// Move top left into top right.
				matrix[top + i][R] = topLeft;
				
				//printMatrix(matrix);
			}
			
			L++;
			R--;
		}
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(" " + matrix[i][j]);
			}

			System.out.println(" ");
		}

		System.out.println("-----");
	}
}