package General.Graph.MatrixAsGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a 2-dimensional matrix of values with 0 and 1, count the number of islands of 1. 
 * An island consists of all value 1 cells and is surrounded by either an edge or all 0 cells. 
 * A cell can only be adjacent to each other horizontally or vertically, not diagonally.
 */
public class FindTheNumberOfIslands {

	public static class Coordinate {
        int r;
        int c;

        public Coordinate(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static List<Coordinate> getNeighbors(Coordinate cell, int numRows, int numCols) {
        List<Coordinate> neighbors = new ArrayList<>();
        int[] deltaRow = {-1, 0, 1, 0};
        int[] deltaCol = {0, 1, 0, -1};
        
        for (int i = 0; i < deltaRow.length; i++) {
            int neighborRow = cell.r + deltaRow[i];
            int neighborCol = cell.c + deltaCol[i];
            
            if (0 <= neighborRow && neighborRow < numRows && 0 <= neighborCol && neighborCol < numCols) {
                neighbors.add(new Coordinate(neighborRow, neighborCol));
            }
        }
        
        return neighbors;
    }

    private static void bfs(List<List<Integer>> grid, Coordinate root, int numRows, int numCols) {
        Deque<Coordinate> queue = new ArrayDeque<>();
        queue.add(root);
        
        grid.get(root.r).set(root.c, 0); // a different way of marking the item as visited
        
        while (queue.size() > 0) {
            Coordinate node = queue.pop();
            List<Coordinate> neighbors = getNeighbors(node, numRows, numCols);
            
            for (Coordinate neighbor : neighbors) {
                if (grid.get(neighbor.r).get(neighbor.c) == 0) {
                	continue;
                }
                
                queue.add(neighbor);
                grid.get(neighbor.r).set(neighbor.c, 0);
            }
        }
    }

    public static int countNumberOfIslands(List<List<Integer>> grid) {
        int numRows = grid.size();
        int numCols = grid.get(0).size();
        int count = 0;
        
        // bfs starting from each unvisited land cell
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                if (grid.get(r).get(c) == 0) {
                	continue;
                }
                
                bfs(grid, new Coordinate(r, c), numRows, numCols);
                // bfs would find 1 connected island, increment count
                count++;
            }
        }
        
        return count;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        List<List<Integer>> grid = new ArrayList<>();
        
        grid.add(splitWords("1 1 1 0 0 0").stream().map(Integer::parseInt).collect(Collectors.toList()));
        grid.add(splitWords("1 1 1 1 0 0").stream().map(Integer::parseInt).collect(Collectors.toList()));
        grid.add(splitWords("1 1 1 0 0 0").stream().map(Integer::parseInt).collect(Collectors.toList()));
        grid.add(splitWords("0 1 0 0 0 0").stream().map(Integer::parseInt).collect(Collectors.toList()));
        grid.add(splitWords("0 0 0 0 1 0").stream().map(Integer::parseInt).collect(Collectors.toList()));
        grid.add(splitWords("0 0 0 0 0 0").stream().map(Integer::parseInt).collect(Collectors.toList()));
        
        int res = countNumberOfIslands(grid);
        System.out.println(res);
    }
}