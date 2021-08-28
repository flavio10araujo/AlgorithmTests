package General.DynamicProgramming.Grid;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Our favourite plumber is on his way to save the princess located in the castle. 
 * The castle is represented by a 2-D grid that contains obstacles (denoted by a 'x') and coins (denoted by a 'c'). 
 * Empty squares in the castle will be denote by a '.'. 
 * Our plumber will always start at position (0, 0) in the grid and the princess will always be at position (r - 1, c - 1) in the grid 
 * where r and c denote the number of rows and columns in the grid respectively. 
 * Whilst on his way to save the princess what is the maximal number of coins that can be obtained?
 * 
 * An additional restriction is that our plumber can only move left, right and up but never down.
 * Additionally, the plumber cannot cross through the obstacles.
 * It is guaranteed that the starting locations of the plumber and the princess will not contain coins nor obstacles.
 * 
 * NOTE: the input will be a series of strings that will represent the grid.
 * 
 * Input: grid: Grid containing the castle layout
 * Output: Integer representing the maximal number of coins that can be obtained.
 * 
 * Example 1:
 * Input: grid = ["..", "cc"]
 * Output: 2
 * Explanation: Both of the coins can be collected on the way to the princess.
 * 
 * Example 2:
 * Input: grid = ["..c", "..x", "..."]
 * Output: 0
 * Explanation: Getting the coin means you are trapped as you cannot go down because of the obstacle and cannot go left.
 * 
 * Constraints
 * 1 <= rows * columns <= 2000
 * 
 * Solution:
 * 
 * We use dynamic programming to solve this question. 
 * In particular, let dp[i][j] represent the maximal amount of coins that can be obtained whilst ending up on square dp[i][j]. 
 * Therefore, dp[i][j] = max(dp[i][j], max(dp[i - 1][j], dp[i + 1][j], dp[i][j - 1]) + (1 if dp[i][j] has coin)). 
 */
public class Plumber {

	public static int plumber(List<String> grid) {
		
        int [][] dp = new int [grid.size() + 4][grid.get(0).length() + 4];
        int [][] temp = new int [grid.size() + 4][grid.get(0).length() + 4];
        
        for (int i = 0; i < grid.size(); i++) {
            
        	for (int j = 0; j < grid.get(0).length(); j++) {
                if (grid.get(i).charAt(j) != 'x') check(grid, dp, i, j);
            }
            
        	for (int j = grid.get(0).length() - 1; j >= 0; j--) {
                if (grid.get(i).charAt(j) != 'x') check(grid, temp, i, j);
            }
            
        	for (int j = grid.get(0).length() - 1; j >= 0; j--) {
                dp[i][j] = Math.max(dp[i][j], temp[i][j]);
                temp[i][j] = dp[i][j];
            }
        }
        
        return dp[grid.size() - 1][grid.get(0).length() - 1];
    }
	
    public static void check(List<String> grid, int [][] dp, int i, int j) {
        if (i - 1 != -1 && grid.get(i - 1).charAt(j) != 'x')  dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + (grid.get(i).charAt(j) == 'c' ? 1:0));
        if (j - 1 != -1 && grid.get(i).charAt(j - 1) != 'x')  dp[i][j] = Math.max(dp[i][j], dp[i][j - 1] + (grid.get(i).charAt(j) == 'c' ? 1:0));
        if (j + 1 != grid.get(0).length() && grid.get(i).charAt(j + 1) != 'x')  dp[i][j] = Math.max(dp[i][j], dp[i][j + 1] + (grid.get(i).charAt(j) == 'c' ? 1:0));
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // .. cc
    // output: 2
    
    // ..c ..x ...
    // output: 1
    
    // ..c. .c.. cx..
    // output: 2
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> grid = splitWords(scanner.nextLine());
        scanner.close();
        int res = plumber(grid);
        System.out.println(res);
    }
}