package General.Graph.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * You happen upon a puzzle box that unlocks... something. 
 * You aren't quite sure what just yet. 
 * The puzzle is a 2 x 3 box, and there are 5 square sliding bricks labelled conveniently with 1 to 5. 
 * It looks something like this:
 *  _____
 * |4 1 3|
 * |2   5|
 *
 * It can be represented as a 2 x 3 matrix containing numbers from 0 to 5, where 0 represents an empty space. 
 * For example, the pattern above can be represented by [[4, 1, 3], [2, 0, 5]].
 * 
 * The tiles are interlocked with each other, so you cannot take the tiles out. 
 * However, the tiles can move freely horizontally and vertically, 
 * so each turn, you can move a tile to an adjacent empty space. 
 * You have a feeling that when you move the tiles to the position [[1, 2, 3], [4, 5, 0]],
 * the puzzle will be solved and unlock the "something". 
 * Like this:
 * 
 * |1 2 3|
 * |4 5  |
 * 
 * You need to be quick about solving this puzzle, though, 
 * as you might attract the attention of someone who doesn't like you poking around in their dungeon. 
 * You wonder whether it is possible to solve this puzzle, and if so, 
 * how many steps you need to move in order to do so.
 * 
 * Input
 * 		init_pos: an integer matrix representing the initial position of the puzzle.
 * Output
 * 		The number of steps required to solve this puzzle, or -1 if the puzzle is impossible to solve.
 * 
 * Example 1:
 * Input: init_pos = [[4, 1, 3], [2, 0, 5]]
 * Output: 5
 * 
 * Constraints
 * The input must be a 2 x 3 integer matrix containing exactly one of each from 0 to 5
 * 
 * Solution
 * 
 * This is a straightforward BFS problem. 
 * However, the biggest difficulty in implementing a solution is finding the state adjacent to the current state 
 * and being able to store the different states in a hash table for lookup of items. 
 * The core idea is another state is adjacent to the current state when the entry with the 0 is swapped with one of the entries adjacent to it, 
 * which is very helpful if we use a mutable structure (like lists). 
 * On the other hand, we need to store the swapped value in a hash table and in a queue, 
 * which is very helpful if we use a hashable structure (like tuples), which are usually immutable. 
 * Other than that, it's just a standard BFS solution.
 * Which side a line comes out of represent which way the puzzle can move, and what happens when you move the puzzle that way. 
 * Note this graph is two-directional, as you can always move back to return to the original position.
 * The time complexity is O(n!), where n is the size of the matrix in question. 
 * We usually would never reach the worst case scenario though.
 */
public class SlidingPuzzle {

	public static List<Integer> rowDirections = List.of(1, 0, -1, 0);
    public static List<Integer> colDirections = List.of(0, 1, 0, -1);

    // The serialized value of [[1, 2, 3], [4, 5, 0]].
    public static int targetState = 123450;

    /**
     * This method gets a List of List<Integer> and transform in a number.
     * Ex.: [[1, 2, 3], [4, 5, 0]] becomes 123450.
     * 
     * @param position
     * @return
     */
    public static int serialize(List<List<Integer>> position) {
        int total = 0;
        
        for (List<Integer> line : position) {
            for (int entry : line) {
                total *= 10;
                total += entry;
            }
        }
        
        return total;
    }

    /**
     * This method gets a number and transform in a List of List<Integer>.
     * Ex.: 123450 becomes [[1, 2, 3], [4, 5, 0]].
     * 
     * @param state
     * @return
     */
    public static List<List<Integer>> deserialize(int state) {
        List<List<Integer>> result = new ArrayList<>();
        
        result.add(new ArrayList<>());
        result.add(new ArrayList<>());
        
        for (int i = 1; i >= 0; i--) {
            for (int j = 2; j >= 0; j--) {
                int exponent = i * 3 + j;
                int digit = state / (int) (Math.round(Math.pow(10, exponent))) % 10;
                result.get(1 - i).add(digit);
            }
        }
        
        return result;
    }

    public static int numSteps(List<List<Integer>> initPos) {
        int initState = serialize(initPos);
        
        if (initState == targetState) {
            return 0;
        }
        
        Map<Integer, Integer> movesMap = new HashMap<>();
        Deque<Integer> movesDeque = new ArrayDeque<>();
        
        movesMap.put(initState, 0);
        movesDeque.offer(initState);
        
        while (!movesDeque.isEmpty()) {
            
        	int topState = movesDeque.poll();
            int row = 0, col = 0;
            
            List<List<Integer>> topPosition = deserialize(topState);
            
            // Looking for the zero means looking for the empty place in the puzzle.
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    if (topPosition.get(i).get(j) == 0) {
                        row = i;
                        col = j;
                    }
                }
            }
            
            // Using delta col and row.
            for (int i = 0; i < 4; i++) {
                
            	int deltaRow = rowDirections.get(i);
            	int deltaCol = colDirections.get(i);
                int newRow = row + deltaRow;
                int newCol = col + deltaCol;
                
                if (newRow >= 0 && newRow < 2 && newCol >= 0 && newCol < 3) {
                    
                	List<List<Integer>> newPosition = deserialize(topState);
                    
                	newPosition.get(row).set(col, topPosition.get(newRow).get(newCol));
                    newPosition.get(newRow).set(newCol, topPosition.get(row).get(col));
                    
                    int newState = serialize(newPosition);
                    
                    if (!movesMap.containsKey(newState)) {
                        movesMap.put(newState, movesMap.get(topState) + 1);
                        movesDeque.offer(newState);
                        
                        if (newState == targetState) {
                            return movesMap.get(newState);
                        }
                    }
                }
            }
        }
        
        return -1;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 2
    // 4 1 3
    // 2 0 5
    // output: 5
    
    // 2
    // 3 2 4
    // 1 5 0
    // output: 14
    
    // 2
    // 1 2 3
    // 4 5 0
    // output: 0
    
    // 2
    // 1 2 3
    // 5 4 0
    // -1
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int initPosLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> initPos = new ArrayList<>();
        
        for (int i = 0; i < initPosLength; i++) {
            initPos.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        
        scanner.close();
        int res = numSteps(initPos);
        System.out.println(res);
    }
}