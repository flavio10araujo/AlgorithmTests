package General;

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
		int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
		
		printMatrix(matrix);
		rotate(matrix);
		printMatrix(matrix);
	}

	public static void rotate(int[][] matrix) {
		for (int i = 0, j = matrix.length - 1; i < j; i++, j--) {
			System.out.println("Primeiro for: i="+i+" j="+j);
			
			int[] temp = matrix[i];
			matrix[i] = matrix[j];
			matrix[j] = temp;
			
			printMatrix(matrix);
		}

		for (int i = 0; i < matrix.length; ++i) {
			for (int j = i + 1; j < matrix.length; ++j) {
				System.out.println("Segundo for: i="+i+" j="+j);
				
				final int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
				
				printMatrix(matrix);
			}
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