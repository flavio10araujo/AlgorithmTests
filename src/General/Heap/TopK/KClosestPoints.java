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
 * 
 * Solution:
 * 
 * Intuition
 * First off, some elementary geometry if you don't remember. 
 * Distance between two points (x1, y1) and (x2, y2) is sqrt((x1 - x2)^2, (y1 - y2)^2). 
 * For distance to origin, (x2, y2) is (0, 0) so distance becomes sqrt(x1^2, y1^2).
 * Having just learned the "art of the heap", our first reaction when we see "Closest k" is to use a heap. 
 * The key for node comparison is a node's distance to origin. 
 * We then pop the top 3 smallest off. 
 * So simple we don't even have to draw a figure this time.
 * Time Complexity: O(n*log(n))
 * We add every element into the heap. Heap insertion is O(log(n)) which we do n times.
 * 
 * Alternative solution using max heap
 * It might be counter-intuitive to think that a max heap can solve a problem that asks for minimum distances. 
 * Here's how to think about it:
 * If we had the k closest points already and we have to decide whether a new point belongs to top k. 
 * The criterion is whether the new point is closer than the furthermost point within the current k points. 
 * If it is, we want to kick the current furthermost point out and add the new point.
 * We can use a max heap to accomplish this. 
 * The root of the max heap is the point with max distance to the origin. 
 * If the new point has a smaller distance, then we pop the root of the max heap and push the new point in.
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