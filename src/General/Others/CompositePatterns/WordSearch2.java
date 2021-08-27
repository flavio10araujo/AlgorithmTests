package General.Others.CompositePatterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Word Search II | Depth First Search + Trie
 * 
 * For this question we ask you to determine if it is possible to make certain words given a 2-d matrix of characters. 
 * What we mean is that you will be given a 2-d grid of characters as well as a list of words. 
 * You may start at any point on the grid and move from cell to cell without looping back to a cell already travelled through 
 * and see if it is possible to make every word in the list of words. 
 * Output all possible words that can be created in the EXACT order that they appear in the list. 
 * The input for the matrix will appear as a list of words to represent the matrix.
 * 
 * Input
 * matrix: list of strings representing the 2-d matrix of characters
 * words: list of words to check if they can be made.
 * Output: list of all possible words that can be made.
 * 
 * Example 1:
 * Input:
 * 		matrix = [aab, aaa]
 * 		words = [bb, aa, abaa]
 * Output: [aa, abaa]
 * Explanation: We can easily make aa by starting at any of the cells containing a in the matrix then moving to another cell containing a. 
 * We cannot make bb as once we start at the 1 b position there is no other b to move onto to make bb.
 * 
 * Constraints
 * 1 <= (row of matrix) * (column of matrix) <= 100
 * Each character will be a lower case english letter
 * 1 <= words.length <= 30001
 * 1 <= words[i].length <= 10
 * 
 * Solution
 * 
 * For the question we need to use two of the patterns we have learned - DFS and Trie.
 * Firstly, we want to know whether or not we have correctly made a word. 
 * For this we first insert all the possible words into a trie.
 * As we dfs through the grid to make the possibilities we move down the trie at the same time. 
 * Once we reach a point where the next character does not exist in that particular node's child in the trie 
 * then we know no word in our list of words matches that sequence of characters. 
 * We therefore do not search any more cells in the grid as we know that no children contain that particular word. 
 * We also make sure to carefully mark which nodes in the trie we have reached.
 * We then repeat this process for every cell in the grid to check if it is possible to make a word. 
 * By the end we query every word in our trie to figure out which words we can make. 
 * Another minor implementation detail is that we need to make sure not to go back on cells we have visited. 
 * Therefore, we need to maintain a visited array that keeps marks cells that we reached and we unmark cells that we have not visited. 
 * It can also be noted that the character a has an ascii code of 97.
 */
public class WordSearch2 {

	// Note that when setting the arrays we only need to pick sufficiently high values.
    // Size of the matrix is given but trie is a bit hard to predict.
    // Sometimes a good technique can be to arbitrarily increase the sizes of the array until we pass the tests!
    
	public static int sz = 1;
    
	// Here we show an alternative implementation of a trie using an array or list.
    public static int [][] trie = new int[26][300001];
    public static boolean [] reached = new boolean[300001];
    public static boolean [][] vis = new boolean[101][101];
    
    public static void insert(int triePos, String str, int strPos) {
        // The array implementation is a 2-d matrix where every column is a new trie node.
        // We can therefore create our trie by pointing to the next node in the trie in the appropriate column.
        
    	if (strPos == str.length()) {
        	return;
        }
        
        int trieChar = (int)(str.charAt(strPos) - 'a');
        
        if (trie[trieChar][triePos] == 0) {
        	trie[trieChar][triePos] = sz++;
        }
        
        insert(trie[trieChar][triePos], str, strPos + 1);
    }
    
    public static boolean query(int triePos, String str, int strPos) {
        // Make sure to check we have moved through the entire word as well as the fact that we have gone through this path.
        if (!reached[triePos]) {
        	return false;
        }
        
        if (strPos == str.length()) {
        	return true;
        }
        
        int trieChar = (int)(str.charAt(strPos) - 'a');
        
        return query(trie[trieChar][triePos], str, strPos + 1);
    }
    
    public static void dfs(int i, int j, int idx, List<String> matrix) {
        
    	int n = matrix.size(), m = matrix.get(0).length();
        
    	// Marks our trie to say we can make this particular prefix in our trie.
        reached[idx] = true;
        
        // The vis array keeps track of the cells we have visited we mark it once we move through and unmark when we leave.
        // This is because we don't want our current path to loop back on itself but once we move up the recursive stack 
        // we have no longer visited this cell and we need to unmark it for other paths moving through this cell.
        vis[i][j] = true;
        
        if (i + 1 < n && !vis[i + 1][j] && trie[(int)(matrix.get(i + 1).charAt(j) - 'a')][idx] != 0) {
            dfs(i + 1, j, trie[(int)(matrix.get(i + 1).charAt(j) - 'a')][idx], matrix);
        }
        
        if (i - 1 >= 0 && !vis[i - 1][j] && trie[(int)(matrix.get(i - 1).charAt(j) - 'a')][idx]!= 0) {
            dfs(i - 1, j, trie[(int)(matrix.get(i - 1).charAt(j) - 'a')][idx], matrix);
        }
        
        if (j + 1 < m  && !vis[i][j + 1] && trie[(int)(matrix.get(i).charAt(j + 1) - 'a')][idx] != 0) {
            dfs(i, j + 1, trie[(int)(matrix.get(i).charAt(j + 1) - 'a')][idx], matrix);
        }
        
        if (j - 1 >= 0 && !vis[i][j - 1] && trie[(int)(matrix.get(i).charAt(j - 1) - 'a')][idx] != 0) {
            dfs(i, j - 1, trie[(int)(matrix.get(i).charAt(j - 1) - 'a')][idx], matrix);
        }
        
        vis[i][j] = false;
    }

    public static List<String> wordSearchIi(List<String> matrix, List<String> words) {
        // Insert all words into our trie.
        for (int i = 0; i < words.size(); i++) {
            insert(0, words.get(i), 0);
        }
        
        // We loop through our matrix and choose every cell as a potential starting point for our path.
        for (int i = 0; i < matrix.size(); i++) {
            
        	for (int j = 0; j < matrix.get(0).length(); j++) {
                
        		int trieIdx = trie[(int)(matrix.get(i).charAt(j) - 'a')][0];
                
        		if (trieIdx > 0) {
        			dfs(i, j, trieIdx, matrix);
        		}
            }
        }
        
        reached[0] = true;
        
        List<String> ans = new ArrayList<String>();
        
        for (int i = 0; i < words.size(); i++) {
            if (query(0, words.get(i), 0)) {
            	ans.add(words.get(i));
            }
        }
        
        return ans;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // aab aaa
    // bb aa
    // output: aa
    
    // a
    // a
    // output: a
    
    // abcd dcba abra
    // bcbr bcbe a ca ac aad bcba
    // output: bcbr a aad bcba
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> matrix = splitWords(scanner.nextLine());
        List<String> words = splitWords(scanner.nextLine());
        scanner.close();
        List<String> res = wordSearchIi(matrix, words);
        System.out.println(String.join(" ", res));
    }
}