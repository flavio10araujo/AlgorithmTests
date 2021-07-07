package General.Heap.TopK;

import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Given a list of points on a 2D plane. 
 * Find k closest points to origin (0, 0).
 * 
 * Input: [(1, 1), (2, 2), (3, 3)], 1
 * Output: [(1, 1)]
 */
public class KClosestPoints {

	public static Point[] kClosestPoints(Point[] points, int k) {
		Queue<Point> heap = new PriorityQueue<>(points.length, Comparator.comparingInt(Point::distanceToOrigin));
        
		for (Point point : points) {
            heap.add(point);
        }
        
		Point[] res = new Point[k];
        
		for (int i = 0; i < k; i++) {
            res[i] = heap.poll();
        }
        
		return res;
    }

    public static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public int distanceToOrigin() {
            return (int) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        }
    }

    /**
     * 3
     * 1 1
     * 2 2
     * 3 3
     * 1
     */
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Point[] points = new Point[n];
        
        for (int i = 0; i < n; i++) {
            String[] coords = scanner.nextLine().split(" ");
            points[i] = new Point(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
        }
        
        int k = Integer.parseInt(scanner.nextLine());
        scanner.close();
        Point[] res = kClosestPoints(points, k);
        
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i].x + " " + res[i].y);
        }
    }

}