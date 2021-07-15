package General.Graph.MatrixAsGraph;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * In computer graphics, an uncompressed raster image is presented as a matrix of numbers.
 * Each entry of the matrix represents the color of a pixel.
 * A flood fill algorithm takes a coordinate r, c, a replacement color and replaces all pixels connected to r, c 
 * that has the same color as r, c with replacement color. (e.g. MS-Paint's paint bucket tool).
 * 
 * Input:
 * Coordinates: 2 2
 * 
 */
public class FloodFill {
	
	public static void floodFill(List<List<Integer>> image, Coordinate start, int replacementColor) {
        int numRows = image.size();
        int numCols = image.get(0).size();
        
        bfs(image, start, replacementColor, numRows, numCols);
    }

    private static List<Coordinate> getNeighbors(List<List<Integer>> image, Coordinate node, int rootColor, int numRows, int numCols) {
        List<Coordinate> neighbors = new ArrayList<>();
        int[] deltaRow = {-1, 0, 1, 0};
        int[] deltaCol = {0, 1, 0, -1};
        
        for (int i = 0; i < deltaRow.length; i++) {
            int neighborRow = node.r + deltaRow[i];
            int neighborCol = node.c + deltaCol[i];
            
            if (0 <= neighborRow && neighborRow < numRows && 0 <= neighborCol && neighborCol < numCols) {
                if (image.get(neighborRow).get(neighborCol) == rootColor) {
                    neighbors.add(new Coordinate(neighborRow, neighborCol));
                }
            }
        }
        
        return neighbors;
    }

    private static void bfs(List<List<Integer>> image, Coordinate root, int replacementColor, int numRows, int numCols) {
        ArrayDeque<Coordinate> queue = new ArrayDeque<>();
        queue.add(root);
        
        boolean[][] visited = new boolean[numRows][numCols];
        
        int rootColor = image.get(root.r).get(root.c);
        
        image.get(root.r).set(root.c, replacementColor);  // replace root color
        visited[root.r][root.c] = true;
        
        while (queue.size() > 0) {
            Coordinate node = queue.pop();
            List<Coordinate> neighbors = getNeighbors(image, node, rootColor, numRows, numCols);
            
            for (Coordinate neighbor : neighbors) {
                if (visited[neighbor.r][neighbor.c]) {
                	continue;
                }
                
                image.get(neighbor.r).set(neighbor.c, replacementColor);  // replace color
                
                queue.add(neighbor);
                visited[neighbor.r][neighbor.c] = true;
            }
        }
    }

    /** Driver code, do not change **/
    public static class Coordinate {
        int r;
        int c;

        public Coordinate(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    //2 2
    //9
    //0 1 3 4 1
    //3 8 8 3 3
    //6 7 8 8 3
    //12 2 8 9 1
    //12 3 1 3 2
    public static void main(String[] args) throws IOException {
        String[] coord = "2 2".split(" ");
        Coordinate start = new Coordinate(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
        int color = Integer.parseInt("9");
        List<List<Integer>> image = new ArrayList<>();
        
        image.add(Arrays.stream("0 1 3 4 1".split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
        image.add(Arrays.stream("3 8 8 3 3".split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
        image.add(Arrays.stream("6 7 8 8 3".split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
        image.add(Arrays.stream("1 2 8 9 1".split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
        image.add(Arrays.stream("1 3 1 3 2".split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
        
        floodFill(image, start, color);
        
        for (List<Integer> row : image) {
            System.out.println(row.stream().map(pixel -> Integer.toString(pixel)).collect(Collectors.joining(" ")));
        }
    }
}