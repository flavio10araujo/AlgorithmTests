package General.DynamicProgramming.Grid;

/**
 * Number of Unique Paths to Go from Top Left to Bottom Right.
 * 
 * A robot is located at the top-left corner of a m x n grid.
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid.
 * How many possible unique paths are there?
 * 
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 	Right -> Right -> Down
 * 	Right -> Down -> Right
 * 	Down -> Right -> Right
 * 
 * Input: m = 5, n = 3
 * Output: 15
 * 
 * Solution:
 * 
 * This is a "Grid DP" problem. 
 * It's an extension of the "Sequence DP" where dp[i] normally represents the max/min/best value for sequence ending at index i.
 * The key is that the robot can move to the right or down only.
 * This translates to "the robot could only reach a cell from top or left".
 * Hence the number of paths to reach a cell = number of paths to reach the cell to the left + number of paths to reach the cell at the top.
 * Let dp[r][c] represent the number of unique paths to reach cell (r, c). (r stands for row, and c stands for column. I found it more intuitive than i, j).
 * 
 * dp[r][c] = dp[r - 1][c] + dp[r][c - 1]
 * 
 * where (r - 1, c) is the cell on the left and (r, c - 1) is the cell at the top.
 */
public class NumberOfRobotPaths {
	
	public static int uniquePaths(int m, int n) {
        int[][] dp = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                    System.out.println("zero i="+i+" j="+j);
                }
                else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                    System.out.println("i="+i+" j="+j);
                }
            }
        }
        
        return dp[n - 1][m - 1];
    }

    public static void main(String[] args) {
        int m = Integer.parseInt("5");
        int n = Integer.parseInt("3");
        int res = uniquePaths(m, n);
        System.out.println(res);
    }
}