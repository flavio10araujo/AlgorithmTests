package General;

/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 * 
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
 * 
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * 
 * Example 1:
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * Output: true
 * 
 * Example 2:
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * Output: false
 * 
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -10 ^ 9 <= matrix[i][j] <= 10 ^ 9
 * All the integers in each row are sorted in ascending order.
 * All the integers in each column are sorted in ascending order.
 * -10 ^ 9 <= target <= 10 ^ 9
 */
public class SearchA2DMatrixII {

	public static void main(String[] args) {
		//int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
		//int target = 5; // true
		
		//int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
		//int target = 20; // false
		
		//int[][] matrix = {{-5}};
		//int target = -2; // false
		
		//int[][] matrix = {{-1, 3}};
		//int target = 3; // true
		
		//int[][] matrix = {{-1}, {-1}};
		//int target = -1; // true
		
		//int[][] matrix = {{5,6,10,14},{6,10,13,18},{10,13,18,19}};
		//int target = 14;
		
		int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
		int target = 20;
		
		System.out.println(searchMatrix(matrix, target));
	}

	public static boolean searchMatrix(int[][] matrix, int target) {
		int r = 0;
	    int c = matrix[0].length - 1;

	    while (r < matrix.length && c >= 0) {
	      if (matrix[r][c] == target)
	        return true;
	      if (matrix[r][c] > target)
	        --c;
	      else
	        ++r;
	    }

	    return false;
    }
}