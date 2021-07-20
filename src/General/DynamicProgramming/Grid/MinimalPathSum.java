package General.DynamicProgramming.Grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Suppose we have a m x n matrix filled with non-negative integers, find a path from top left corner to bottom right corner 
 * which minimizes the sum of all numbers along its path.
 * 
 * Note: Movements can only be either down or right at any point in time.
 * 
 * Example:
 * Input:
 * [
 * 	[1,3,1],
 * 	[1,5,1],
 * 	[4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 * 
 * Solution
 * 
 * This problem is similar to the Robot Unique Paths. 
 * We can only to right or bottom from a cell. 
 * The difference is the cells now have weights and we want to choose the path with the minimal weight. 
 * The keyword "minimal" and the grid is a good sign of dynamic programming.
 * The equivalent of "only able to move to the right and bottom" is "can only reach a cell from top or left". 
 * Since top and left are the only two ways to reach a cell, the minimal path sum to reach a cell is the minimum sum of top and left + value of the current cell.
 * Let dp[r][c] represent the minimal path sum to reach cell (r, c).
 * dp[r][c] = min(dp[r - 1][c], dp[r][c - 1]) + grid[r][c]
 * where dp[r - 1][c] is the minimal path sum to reach the cell at the top of the current cell and dp[r][c - 1] is the minimal path sum to reach 
 * the cell to the left of the current cell
 * We fill the dp matrix row by row from left to right.
 * 
 * Time Complexity: O(m*n)
 */
public class MinimalPathSum {

	public static int minPathSum(List<List<Integer>> grid) {
        int m = grid.size();
        int n = grid.get(0).size();

        int[][] dp = new int[m][n];
        dp[0][0] = grid.get(0).get(0);

        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid.get(0).get(i);
        }

        for (int j = 1; j < m; j++) {
            dp[j][0] = dp[j - 1][0] + grid.get(j).get(0);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    dp[i][j] = dp[i][j - 1] + grid.get(i).get(j);
                } else {
                    dp[i][j] = dp[i - 1][j] + grid.get(i).get(j);
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }
    
    // 3
    // 1 3 1
    // 1 5 1
    // 4 2 1
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int gridLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> grid = new ArrayList<>();
        
        for (int i = 0; i < gridLength; i++) {
            grid.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        
        scanner.close();
        int res = minPathSum(grid);
        System.out.println(res);
    }
}