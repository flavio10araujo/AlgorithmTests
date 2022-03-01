package General.BinarySearch;

/**
 * https://leetcode.com/problems/search-a-2d-matrix/
 * 
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
 * - Integers in each row are sorted from left to right.
 * - The first integer of each row is greater than the last integer of the previous row.
 * 
 * Example 1:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 * 
 * Example 2:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 * 
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -10 ^ 4 <= matrix[i][j], target <= 10 ^ 4
 */
public class SearchA2DMatrix {

	public static void main(String[] args) {
		/*int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
		int target = 13;*/

		int[][] matrix = {{1,3,5}};
		int target = 5;

		System.out.println(solution01(matrix, target));
	}

	public static boolean solution01(int[][] matrix, int target) {
		int h = matrix.length;
		int w = matrix[0].length;
		int iL = 0, jL = 0;
		int iR = h - 1, jR = w - 1;

		while (iL <= iR) {
			if (iL == iR && jL > jR) {
				break;
			}

			int indexL = (iL * w) + (jL);
			int indexR = (iR * w) + (jR);
			int indexM = (indexL + indexR) / 2;

			int iM = indexM / w;
			int jM = indexM % w;

			if (matrix[iM][jM] == target) {
				return true;
			}

			if (matrix[iM][jM] > target) {
				iR = iM;
				jR = jM - 1;

				if (jR < 0) {
					iR = iM - 1;
					jR = w - 1;
				}
			} else {
				iL = iM;
				jL = jM + 1;

				if (jL == w) {
					iL = iM + 1;
					jL = 0;
				}
			}

		}

		return false;
	}

	/**
	 * Time complexity: O(mn log mn).
	 * Space complexity: O(1).
	 * @param matrix
	 * @param target
	 * @return
	 */
	public static boolean solution02(int[][] matrix, int target) {
		final int m = matrix.length;
		final int n = matrix[0].length;
		int l = 0;
		int r = m * n;

		while (l < r) {
			final int mid = l + (r - l) / 2;
			final int i = mid / n;
			final int j = mid % n;
			
			if (matrix[i][j] == target)
				return true;
			
			if (matrix[i][j] < target)
				l = mid + 1;
			else
				r = mid;
		}

		return false;
	}
}