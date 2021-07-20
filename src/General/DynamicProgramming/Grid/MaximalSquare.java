package General.DynamicProgramming.Grid;

import java.io.IOException;
import java.util.Scanner;

/**
 * Given a binary matrix, find out the largest size square sub-matrix with all 1's and return its area.
 * 
 * Input:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 0
 * 1 0 0 1 0
 * Output: 4
 * 
 * Solution
 * 
 * Given the keyword "maximal" and the grid, dynamic programming is a good candidate.
 * Let dp[r][c] be the maximal square of all 1s whose bottom-right corner is at cell (r, c).
 * If the cell at (r, c) is 0 then there's no 1-filled square whose bottom-right corner ends in this cell. 
 * Otherwise, we have to figure out how to compute dp[r][c] from previous subproblems.
 * Notice that the problem asks for a square. 
 * This means width = height, and we have to take the minimal of dp[r - 1][c] and dp[r][c - 1] because the square 
 * has to be filled with 1s and the shorter side limits the square size. 
 * In addition, we have to consider the top-left corner to make sure the entire square is filled with 1s. 
 * The recurrence relation is:
 * dp[r][c] = 1 + min(dp[r - 1][c], dp[r][c - 1], dp[r -1][c -1])
 * Time Complexity: O(r*c)
 */
public class MaximalSquare {

	public static int maximalSquare(char[][] matrix) {
		
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int best = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            dp[i][0] = matrix[i][0]-'0';
            best = Math.max(best, dp[i][0]);
        }

        for (int j = 0; j < matrix[0].length; j++){
            dp[0][j] = matrix[0][j]-'0';
            best = Math.max(best, dp[0][j]);
        }

        for (int i = 1; i < matrix.length; i++) {
        	
            for (int j = 1; j < matrix[0].length; j++) {
            	
                if (matrix[i][j] == '1') {
                	
                    int min = Math.min(dp[i-1][j], dp[i][j-1]);
                    min = Math.min(min, dp[i-1][j-1]);
                    dp[i][j] = min+1;
                    best = Math.max(best, min + 1);
                }
                else {
                    dp[i][j] = 0;
                }
            }
        }

        return best * best;
    }
	
	// 4
	// 1 0 1 0 0
	// 1 0 1 1 1
	// 1 1 1 1 0
	// 1 0 0 0 1
	public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int rows = Integer.parseInt(scanner.nextLine());
        String[] line = scanner.nextLine().trim().split(" ");
        char [][] dataArray = new char[rows][line.length];
        
        while(scanner.hasNextLine()) {
           for (int i=0; i<dataArray.length; i++) {
              if(i != 0){
                line = scanner.nextLine().trim().split(" ");
              }
              for (int j=0; j<line.length; j++) {
                 dataArray[i][j] = line[j].charAt(0);
              }
           }
        }
        
        System.out.println(maximalSquare(dataArray));
    }
}