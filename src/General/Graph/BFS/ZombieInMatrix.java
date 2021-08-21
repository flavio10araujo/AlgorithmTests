package General.Graph.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/walls-and-gates/
 * 
 * You are given an m by n grid of integers representing a map of a dungeon. 
 * In this map:
 * 		-1 represents a wall or an obstacle of some kind.
 * 		0 represents a gate out of the dungeon.
 * 		INF represents empty space.
 * 			For this question, let INF be 2^31 - 1 == 2147483647, which is the max value of the integer type in many programming languages.
 * 
 * Venturing into the dungeon is very dangerous, so you would like to know how close you are at each point in the dungeon to the exit. 
 * Given the map of the dungeon, return the same map, but for each empty space, 
 * that space is replaced by the number of steps it takes to reach any exit. 
 * If a space cannot reach the exit, that space remains INF.
 * 
 * Note that each step, you can move horizontally or vertically, but you cannot move pass a wall or an obstacle.
 * 
 * Input
 * 		dungeon_map: a matrix of integer representing the dungeon map.
 * Output
 * 		A matrix of integer representing the dungeon map with the addition of distance to an exit for each empty space.
 * 
 * Input:
 * dungeon_map = [
 * 		[INF,  -1,   0, INF],
 * 		[INF, INF, INF,  -1],
 * 		[INF,  -1, INF,  -1],
 * 		[  0,  -1, INF, INF],
 * ]
 * Output: [ 
 * 		[3  ,  -1,   0,   1], 
 * 		[2  ,   2,   1,  -1], 
 * 		[1  ,  -1,   2,  -1], 
 * 		[0  ,  -1,   3,   4], 
 * ]
 * 
 * Constraints: 1 <= n, m <= 500
 * 
 * Solution:
 * 
 * This is a classical breadth-first search problem, as it asks for the distance from each point to some location. 
 * Simply initialize the queue with a list of gate locations, and each cycle, when we process the location at the front of the queue, 
 * we add all the adjacent locations into the queue if their value is INF (meaning it is empty and unprocessed) 
 * and mark the distances on the cells by adding the value of the current cell by 1. 
 * This way each empty space is only in the queue once.
 * The time complexity is O(n * m).
 */
public class ZombieInMatrix {

	public static class Coords {
        public int row;
        public int col;

        public Coords(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Coords add(Coords other) {
            return new Coords(row + other.row, col + other.col);
        }
    }

    public static List<Coords> directions = List.of(new Coords(0, 1), new Coords(1, 0), new Coords(0, -1), new Coords(-1, 0));

    public static List<List<Integer>> mapGateDistances(List<List<Integer>> dungeonMap) {
        Deque<Coords> deque = new ArrayDeque<>();
        int n = dungeonMap.size(), m = dungeonMap.get(0).size();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dungeonMap.get(i).get(j) == 0) {
                    deque.offer(new Coords(i, j));
                }
            }
        }
        
        while (!deque.isEmpty()) {
            Coords currentPos = deque.poll();
            
            for (Coords delta : directions) {
                Coords newPos = currentPos.add(delta);
                
                if (newPos.row >= 0 && newPos.row < n && newPos.col >= 0 && newPos.col < m) {
                    if (dungeonMap.get(newPos.row).get(newPos.col) == Integer.MAX_VALUE) {
                        dungeonMap.get(newPos.row).set(newPos.col, 1 + dungeonMap.get(currentPos.row).get(currentPos.col));
                        deque.add(newPos);
                    }
                }
            }
        }
        
        return dungeonMap;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    /*
     4
	 2147483647 -1 0 2147483647
	 2147483647 2147483647 2147483647 -1
	 2147483647 -1 2147483647 -1
	 0 -1 2147483647 2147483647
	 
	 Output:
	 3 -1 0 1
	 2 2 1 -1
	 1 -1 2 -1
	 0 -1 3 4
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dungeonMapLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> dungeonMap = new ArrayList<>();
        for (int i = 0; i < dungeonMapLength; i++) {
            dungeonMap.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        List<List<Integer>> res = mapGateDistances(dungeonMap);
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}