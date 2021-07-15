package General.Graph.MatrixAsGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * On an infinitely large chessboard, a knight is located on [0, 0].
 * A knight can move in eight directions.
 * 
 * Given a destination coordinate [x, y], determine the minimum number of moves from [0, 0] to [x, y].
 */
public class KnightMinimumMoves {

	public static class Coordinate {
        int r;
        int c;

        public Coordinate(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (this == obj) return true;
            if ((obj instanceof Coordinate) && ((Coordinate) obj).r == r && ((Coordinate) obj).c == c) {
                return true;
            }
            return false;
        }
    }
	
	private static List<Coordinate> getNeighbours(Coordinate cell) {
        List<Coordinate> neighbors = new ArrayList<>();
        int[] deltaRow = {-1, -2, -2, -1, 1, 2, 1, 2};
        int[] deltaCol = {-2, -1 ,1, 2, -2, -1, 2, 1};
        
        for (int i = 0; i < deltaRow.length; i++) {
            int neighborRow = cell.r + deltaRow[i];
            int neighborCol = cell.c + deltaCol[i];
            
            //if (0 <= neighborRow && 0 <= neighborCol) {
                neighbors.add(new Coordinate(neighborRow, neighborCol));
            //}
        }
        
        return neighbors;
    }
	
	public static int getKnightShortestPath(int x, int y) {
        
		Deque<Coordinate> queue = new ArrayDeque<Coordinate>();
		Set<Coordinate> visited = new HashSet<Coordinate>();
		Coordinate coordinate = new Coordinate(0, 0);
		
		queue.add(coordinate);
		visited.add(coordinate);
		
		int count = 0;
		
		while (queue.size() > 0) {
			
			int r = queue.size();
			
			for (int i = 0; i < r; i++) {
				Coordinate coord = queue.pop();
				
				if (coord.c == x && coord.r == y) {
					return count;
				}
				
				for (Coordinate neighbour : getNeighbours(coord)) {
					
					if (visited.contains(neighbour)) {
						continue;
					}
					
					queue.add(neighbour);
					visited.add(neighbour);
				}
			}
			
			count++;
		}
		
        return count;
    }

    public static void main(String[] args) {
        int x = Integer.parseInt("1");
        int y = Integer.parseInt("1");
        int res = getKnightShortestPath(x, y);
        System.out.println(res);
    }
}