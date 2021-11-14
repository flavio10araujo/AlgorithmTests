package General;

/**
 * https://leetcode.com/problems/container-with-most-water/
 * 
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). 
 * n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). 
 * Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.
 * Notice that you may not slant the container.
 * 
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. 
 * In this case, the max area of water (blue section) the container can contain is 49.
 * 
 * Example 2:
 * Input: height = [1,1]
 * Output: 1
 * 
 * Example 3:
 * Input: height = [4,3,2,1,4]
 * Output: 16
 * 
 * Example 4:
 * Input: height = [1,2,1]
 * Output: 2
 * 
 * Constraints:
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 */
public class ContainerWithMostWater {

	public static void main(String[] args) {
		int[] height = {1,8,6,2,5,4,8,3,7};
		System.out.println(maxArea(height));
	}
	
	/**
	 * Time complexity: O(n).
	 * Space complexity: O(1).
	 * @param height
	 * @return
	 */
	public static int maxArea(int[] height) {
        int L = 0;
        int R = height.length - 1;
        
        int max = 0;
        
        while (L < R) {
            int area = (R - L) * Math.min(height[L], height[R]);
            max = Math.max(max, area);
            
            if (height[L] < height[R]) {
                L++;
            } else {
                R--;
            }
        }
        
        return max;
    }
}