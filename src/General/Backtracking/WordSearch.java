package General.Backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-search/
 * 
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cells, where "adjacent" cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * 
 * Input: 
 * 	board = [
 * 				["A","B","C","E"],
 * 				["S","F","C","S"],
 * 				["A","D","E","E"]
 * 			], 
 * 			word = "ABCCED"
 * Output: true
 * 
 * Input: 
 * 	board = [
 * 				["A","B","C","E"],
 * 				["S","F","C","S"],
 * 				["A","D","E","E"]
 *			], 
 *			word = "SEE"
 * Output: true
 * 
 * Input: 
 * 	board = [
 * 				["A","A","B","B"]
 *			], 
 * 			word = "ABCB"
 * Output: false
 */
public class WordSearch {
	
	public static void main(String[] args) {
		//char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
		//String word = "ABCCED";
		
		char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
		String word = "ABCB";
		
    	System.out.println(solution01(board, word));
    	
    	System.out.println(solution02(board, word));
    }
	
	static int[][] delta = new int[4][2];
    
    /**
     * 
     * @param board
     * @param word
     * @return
     */
	public static boolean solution01(char[][] board, String word) {
        
        char firstLetter = word.charAt(0);
        
        int height = board.length;
        int width = board[0].length;
        
        initializeDelta();
        
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (board[row][col] == firstLetter) {
                    Set<Coordinate> coords = new HashSet<>();
                    coords.add(new Coordinate(row, col));
                    
                    List<Character> chars = new ArrayList<>();
                    chars.add(word.charAt(0));
                    
                    if (wordFinder(board, word, row, col, coords, 0, chars)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public static void initializeDelta() {
        // left
        delta[0][0] = 0;
        delta[0][1] = -1;
        // up
        delta[1][0] = -1;
        delta[1][1] = 0;
        // right
        delta[2][0] = 0;
        delta[2][1] = 1;
        // down
        delta[3][0] = 1;
        delta[3][1] = 0;
    }
    
    public static boolean wordFinder(char[][] board, String word, int row, int col, Set<Coordinate> coords, int indexWord, List<Character> chars) {
        
        if (chars.size() == word.length()) {
            return true;
        }
        
        int indexDelta, rowFuture, colFuture;
        
        indexWord++;
        
        // left
        indexDelta = 0;
        rowFuture = row + delta[indexDelta][0];
        colFuture = col + delta[indexDelta][1];
        if (isValidMove(board, word, rowFuture, colFuture, coords, indexWord, indexDelta)) {
            
        	Coordinate c = new Coordinate(rowFuture, colFuture);
            coords.add(c);
            chars.add(word.charAt(indexWord));
            
            if (wordFinder(board, word, rowFuture, colFuture, coords, indexWord, chars)) {
                return true;
            }
            
            chars.remove(chars.size() - 1);
            coords.remove(c);
        }
        
        // up
        indexDelta = 1;
        rowFuture = row + delta[indexDelta][0];
        colFuture = col + delta[indexDelta][1];
        if (isValidMove(board, word, rowFuture, colFuture, coords, indexWord, indexDelta)) {
            
        	Coordinate c = new Coordinate(rowFuture, colFuture);
            coords.add(c);
            chars.add(word.charAt(indexWord));
            
            if (wordFinder(board, word, rowFuture, colFuture, coords, indexWord, chars)) {
                return true;
            }
            
            chars.remove(chars.size() - 1);
            coords.remove(c);
        }
        
        // right
        indexDelta = 2;
        rowFuture = row + delta[indexDelta][0];
        colFuture = col + delta[indexDelta][1];
        if (isValidMove(board, word, rowFuture, colFuture, coords, indexWord, indexDelta)) {
            
        	Coordinate c = new Coordinate(rowFuture, colFuture);
            coords.add(c);
            chars.add(word.charAt(indexWord));
            
            if (wordFinder(board, word, rowFuture, colFuture, coords, indexWord, chars)) {
                return true;
            }
            
            chars.remove(chars.size() - 1);
            coords.remove(c);
        }
        
        // down
        indexDelta = 3;
        rowFuture = row + delta[indexDelta][0];
        colFuture = col + delta[indexDelta][1];
        if (isValidMove(board, word, rowFuture, colFuture, coords, indexWord, indexDelta)) {
            
        	Coordinate c = new Coordinate(rowFuture, colFuture);
            coords.add(c);
            chars.add(word.charAt(indexWord));
            
            if (wordFinder(board, word, rowFuture, colFuture, coords, indexWord, chars)) {
                return true;
            }
            
            chars.remove(chars.size() - 1);
            coords.remove(c);
        }
        
        return false;
    }
    
    public static boolean isValidMove(char[][] board, String word, int rowFuture, int colFuture, Set<Coordinate> coords, int indexWord, int indexDelta) {
        
        if (rowFuture < 0 || colFuture < 0 || rowFuture >= board.length || colFuture >= board[0].length) {
            return false;
        }
        
        if (board[rowFuture][colFuture] != word.charAt(indexWord)) {
        	return false;
        }
        
        if (coords.contains(new Coordinate(rowFuture, colFuture))) {
        	return false;
        }
        
        return true;
    }
    
    static class Coordinate {
        private int row;
        private int col;
        
        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }
        
        public int getRow() {
            return this.row;
        }
        
        public int getCol() {
            return this.col;
        }
        
        public boolean equals(Object obj) {
            if (!(obj instanceof Coordinate))
    			return false;	
    		if (obj == this)
    			return true;
    		
    		if (this.row == ((Coordinate) obj).row 
    				&& this.col == ((Coordinate) obj).col) {
                return true;
            } else {
                return false;
            }
        }
        
        public int hashCode() {
        	if (this.row == 0) {
        		return this.col;
        	}
        	
        	return (this.row * 10) + this.col;
        }
    }

    /**
     * 
     * @param board
     * @param word
     * @return
     */
    public static boolean solution02(char[][] board, String word) {
    	for (int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board[i].length; j++) {
    			if (board[i][j] == word.charAt(0) && dfs(board, i, j, 0, word)) {
    				return true;
    			}
    		}
    	}
    	
    	return false;
    }
    
    public static boolean dfs(char[][] board, int i, int j, int count, String word) {
    	if (count == word.length()) {
    		return true;
    	}
    	
    	if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] != word.charAt(count)) {
    		return false;
    	}
    	
    	char temp = board[i][j];
    	board[i][j] = ' ';
    	
    	boolean found = dfs(board, i + 1, j, count + 1, word)
    			|| dfs(board, i - 1, j, count + 1, word)
    			|| dfs(board, i, j + 1, count + 1, word)
    			|| dfs(board, i, j - 1, count + 1, word);
    	
    	board[i][j] = temp;
    	return found;
    }
}