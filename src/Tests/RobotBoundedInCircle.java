package Tests;

import java.util.Scanner;

/**
 * On an infinite plane, a robot initially stands at (0, 0) and faces north.
 * The robot can receive one of three instructions:
 * 
 * "G": go straight 1 unit;
 * "L": turn 90 degrees to the left;
 * "R": turn 90 degrees to the right.
 * 
 * The robot performs the instructions given in order, and repeats them forever.
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 * 
 * Example 1:
 * Input: instructions = "GGLLGG"
 * Output: true
 * Explanation: The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
 * When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
 * 
 * Example 2:
 * Input: instructions = "GG"
 * Output: false
 * Explanation: The robot moves north indefinitely.
 * 
 * Example 3:
 * Input: instructions = "GL"
 * Output: true
 * Explanation: The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
 * 
 * Solution:
 * 
 * This problem is more about math proof than coding.
 * Consider the location of the robot after one iteration. 
 * There are two cases:
 * 1. The robot is back to the origin. 
 * 	  In this case, it's obvious that the robot will stay at the origin after any number of runs.
 * 2. The robot is not at the origin.
 * 
 * For case 2, let's consider where the robot is facing after one iteration. 
 * The robot starts facing north, and after one iteration it could face:
 * 1. North. 
 * 		Since it's facing the same direction after one iteration, it'll get further and further away from origin in the next iteration. 
 * 		And therefore its movement will not be bounded by a circle.
 * 2. South. 
 * 		The robot reverses its direction. 
 * 		The distance it traveled in the current movement will be cancelled by the next movement since they are of opposite directions. 
 * 		Therefore, the robot goes back to the origin every two iterations.
 * 3. East. 
 * 		If the robot ends up facing east after the iteration 1, it will be facing south after iteration 2, west after iteration 3 and north again after iteration 4. 
 * 		The distance it traveled in north direction cancels that of south, and distance it traveled in the east direction cancels that of west. 
 * 		So the robot is back to origin after 4 iterations.
 * 4. West. 
 * 		It's the opposite situation as east but the result is the same - the robot goes back to the origin.
 * 
 * Therefore, we can simulate the robot movement after one iteration and return true if the robot's coordinate is back to origin or it faces a direction that is not north.
 */
public class RobotBoundedInCircle {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String movements = scanner.nextLine();
        scanner.close();
        boolean res = isRobotBounded(movements);
        System.out.println(res);
    }
	
	public static boolean isRobotBounded(String instructions) {
        
		int x = 0, y = 0;
		// 0 = North; 1 = East; 2 = South; 3 = West.
		int dir = 0;
		
		int[][] directions = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };
		
		for (int i = 0; i < instructions.length(); i++) {
			char ch = instructions.charAt(i);
			
			if (ch == 'G') {
				x = x + directions[dir][0];
				y = y + directions[dir][1];
			} else if (ch == 'L') {
				dir = (dir == 0) ? 3 : dir - 1;
			} else if (ch == 'R') {
				dir = (dir + 1) % 4;
			}
		}
		
		// If returned to the same position.
		if (x == 0 && y == 0) {
			return true;
		}
		
		// If it is finished pointing to north.
		if (dir == 0) {
			return false;
		}
		
		return true;
    }
}