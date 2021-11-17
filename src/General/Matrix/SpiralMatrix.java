package General.Matrix;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/spiral-matrix/
 * 
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * 
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 *
 * Example 2:
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 * 
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class SpiralMatrix {

	public static void main(String[] args) {
		//int[][] matrix =  {{1,2,3},{4,5,6},{7,8,9}};
		int[][] matrix =  {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
		
		System.out.println(solution01(matrix));
		System.out.println(solution02(matrix));
	}

	public static List<Integer> solution01(int[][] matrix) {
        Set<String> visited = new HashSet<>();
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int x = 0;
        int y = -1;
        
        List<Integer> res = new ArrayList<>();
        
        int[][] delta = new int[4][2];
        delta[0][0] = 0; // x
        delta[0][1] = 1; // y
        
        delta[1][0] = 1; // x
        delta[1][1] = 0; // y
        
        delta[2][0] = 0;
        delta[2][1] = -1;
        
        delta[3][0] = -1;
        delta[3][1] = 0;
        
        int deltaIndex = 0;
        
        while (visited.size() != (m * n)) {
            
            int xFuture = x + delta[deltaIndex][0];
            int yFuture = y + delta[deltaIndex][1];
            
            while ((xFuture >= m) || (xFuture < 0) 
                   || (yFuture >= n) || (yFuture < 0) 
                   || visited.contains(xFuture+","+yFuture)) {
                deltaIndex++;
                
                if (deltaIndex > 3) {
                    deltaIndex = 0;
                }
                
                xFuture = x + delta[deltaIndex][0];
                yFuture = y + delta[deltaIndex][1];
            }
            
            x = xFuture;
            y = yFuture;
            
            res.add(matrix[x][y]);
            visited.add(x+","+y);
        }
        
        return res;
    }
	
	public static List<Integer> solution02(int[][] matrix) {
        int m = (matrix.length) - 1;
        int n = matrix[0].length;
        
        int x = 0;
        int y = -1;
        
        List<Integer> res = new ArrayList<>();
        
        int[][] delta = new int[4][2];
        delta[0][0] = 0; // x
        delta[0][1] = 1; // y
        
        delta[1][0] = 1; // x
        delta[1][1] = 0; // y
        
        delta[2][0] = 0;
        delta[2][1] = -1;
        
        delta[3][0] = -1;
        delta[3][1] = 0;
        
        int deltaIndex = 0; // 0 = right; 1 = down; 2 = left; 3 = up
        boolean rightLeft = true;
        
        int countHorizontal = 0;
        int countVertical = 0;
        boolean changeDirection = false;
        
        while ((rightLeft && n > 0) || (!rightLeft && m > 0)) {
            
            x = x + delta[deltaIndex][0];
            y = y + delta[deltaIndex][1];
            
            res.add(matrix[x][y]);
            
            if (rightLeft) {
            	countHorizontal++;
            	
            	if (countHorizontal == n) {
            		countHorizontal = 0;
            		n--;
            		rightLeft = !rightLeft;
            		changeDirection = true;
            	}
            } else {
            	countVertical++;
            	
            	if (countVertical == m) {
            		countVertical = 0;
            		m--;
            		rightLeft = !rightLeft;
            		changeDirection = true;
            	}
            }
            
            if (changeDirection) {
            	deltaIndex++;
                
                if (deltaIndex > 3) {
                	deltaIndex = 0;
                }
                
                changeDirection = false;
            }
        }
        
        return res;
    }
}
