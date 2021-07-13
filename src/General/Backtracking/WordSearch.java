package General.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cells, where "adjacent" cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * 
 * Input: 
 * 	board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 * 
 * Input: 
 * 	board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 * 
 * Input: 
 * 	board = "aabb", word = "ABCB"
 * Output: false
 */
public class WordSearch {
	
	public static boolean exist(List<List<String>> board, String word) {    
        int height = board.size();
        int width = board.get(0).size();
        Character firstLetter = word.charAt(0);
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (board.get(y).get(x).equalsIgnoreCase(firstLetter.toString())) {
                    
                	Set<String> mySet = new HashSet<String>();
                	mySet.add(x+","+y);
                	
                	if (wordFinder(board, word, x, y, List.of(firstLetter.toString()), mySet)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
	public static boolean compareListStringAndString(List<String> path, String word) {
		String str = new String("");
		
		for (String s : path) {
			str += s;
		}
		
		if (str.equalsIgnoreCase(word)) {
			return true;
		} 
		
		return false;
	} 

    public static boolean wordFinder(List<List<String>> board, String word, int x, int y, List<String> path, Set<String> mySet) {
        
    	System.out.println("path="+path);
    	System.out.println("mySet"+mySet);
    	
    	if (path.size() == word.length()) {
    		if (compareListStringAndString(path, word)) {
                return true;
            }
            
            return false;
        }
    	
    	// going right
        if (x < board.get(0).size() - 1 && !mySet.contains((x+1)+","+y)) {
        	x = x + 1;
            
            path = new ArrayList<String>(path);
            path.add(board.get(y).get(x));
            mySet.add(x+","+y);
            
            if (wordFinder(board, word, x, y, path, mySet)) {
            	return true;
            }
            
            path.remove(path.size() - 1);
            mySet.remove(x+","+y);
            
            x = x - 1;
        }
        
        // going left
        if (x > 0 && !mySet.contains((x-1)+","+y)) {
        	x = x - 1;
        	
            path = new ArrayList<String>(path);
            path.add(board.get(y).get(x));
            mySet.add(x+","+y);

            if (wordFinder(board, word, x, y, path, mySet)) {
            	return true;
            }
            
            path.remove(path.size() - 1);
            mySet.remove(x+","+y);
            
            x = x + 1;
        }
        
        // going down
        if (y < board.size() - 1 && !mySet.contains(x+","+(y+1))) {
            y = y + 1;
            
            path = new ArrayList<String>(path);
            path.add(board.get(y).get(x));
            mySet.add(x+","+y);
            
            if (wordFinder(board, word, x, y, path, mySet)) {
            	return true;
            }
            
            path.remove(path.size() - 1);
            mySet.remove(x+","+y);
            
            y = y - 1;
        }
        
        // going up
        if (y > 0 && !mySet.contains(x+","+(y-1))) {
        	y = y - 1;
            
        	path = new ArrayList<String>(path);
            path.add(board.get(y).get(x));
            mySet.add(x+","+y);
            
            if (wordFinder(board, word, x, y, path, mySet)) {
            	return true;
            }
            
            path.remove(path.size() - 1);
            mySet.remove(x+","+y);
            
            y = y + 1;
        }
        
        return false;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        List<List<String>> board = new ArrayList<>();
        List<String> l1 = List.of("A", "B", "C", "E");
        List<String> l2 = List.of("S", "F", "C", "S"); // S F C S
        List<String> l3 = List.of("A", "D", "E", "E"); // A D E E
        board.add(l1);
        board.add(l2);
        board.add(l3);
        String word = "EESCFDA";
        
        boolean res = exist(board, word);
        System.out.println(res);
    }
}