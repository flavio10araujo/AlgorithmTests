package General;

/**
 * https://leetcode.com/problems/game-of-life/
 */
public class GameOfLife {

	public static void main(String[] args) {
		int[][] board = {{0,1,0}, {0,0,1}, {1,1,1}, {0,0,0}};
		gameOfLife(board, 1);
		
		for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            
            System.out.println(" ");
        }
	}
	
	public static void gameOfLife(int[][] board, int n) {
        for (int times = 0; times < n; times++) {
            int[][] temp = copyMatrix(board);
            
            for (int i = 0; i < temp.length; i++) {
                for (int j = 0; j < temp[0].length; j++) {
                    int count = countNeighborsAlive(temp, i, j);
                    
                    if (board[i][j] == 1) {
                        if (count < 2 || count > 3) {
                            board[i][j] = 0;
                        }
                    } else if (count == 3) {
                        board[i][j] = 1;
                    }
                }
            }
        }
    }
    
    private static int[][] copyMatrix(int[][] matrix) {
        int[][] copy = new int[matrix.length][matrix[0].length];
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                copy[i][j] = matrix[i][j];
            }
        }
        
        return copy;
    }
    
    private static int countNeighborsAlive(int[][] matrix, int row, int col) {
        int count = 0;
        int h = matrix.length;
        int w = matrix[0].length;
        
        if (row - 1 >= 0) {
            if (col - 1 >= 0) {
                if (matrix[row - 1][col - 1] == 1) {
                    count++;
                }
            }
            
            if (matrix[row - 1][col] == 1) {
                count++;
            }
            
            if (col + 1 < w) {
                if (matrix[row - 1][col + 1] == 1) {
                    count++;
                }
            }
        }
        
        if (row + 1 < h) {
            if (col - 1 >= 0) {
                if (matrix[row + 1][col - 1] == 1) {
                    count++;
                }
            }
            
            if (matrix[row + 1][col] == 1) {
                count++;
            }
            
            if (col + 1 < w) {
                if (matrix[row + 1][col + 1] == 1) {
                    count++;
                }
            }
        }
        
        if (col - 1 >= 0) {
            if (matrix[row][col - 1] == 1) {
                count++;
            }
        }
        
        if (col + 1 < w) {
            if (matrix[row][col + 1] == 1) {
                count++;
            }
        }
        
        return count;
    }
}