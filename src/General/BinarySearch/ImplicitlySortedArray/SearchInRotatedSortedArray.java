package General.BinarySearch.ImplicitlySortedArray;

/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) 
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). 
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 * You must write an algorithm with O(log n) runtime complexity.
 * 
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * 
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * 
 * Example 3:
 * Input: nums = [4,5,6,7,0,1,2], target = 6
 * Output: 2
 * 
 * Example 4:
 * Input: nums = [1], target = 0
 * Output: -1
 * 
 * Example 5:
 * Input: nums = [1,2,3,4,5,6], target = 4
 * Output: 3
 * 
 * Example 6:
 * Input: nums = [5,1,2,3,4], target = 1
 * Output: 1
 * 
 * Example 7:
 * Input: nums = [5,6,2,3,4], target = 6
 * Output: 1
 * 
 * Constraints:
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * All values of nums are unique.
 * nums is an ascending array that is possibly rotated.
 * -104 <= target <= 104
 */
public class SearchInRotatedSortedArray {

	public static void main(String[] args) {
		
		//int[] nums = {4,5,6,7,0,1,2};
		//int target = 0;
		
		int[] nums = {4,5,6,7,0,1,2};
		int target = 3;
		
		System.out.println(search(nums, target));
	}
	
	public static int search(int[] nums, int target) {
        int L = 0;
        int R = nums.length - 1;
        
        int index = -1;
        
        while(L <= R) {
            int M = (L + R) / 2;
            
            if (nums[M] == target) {
                index = M;
                break;
            }
            
            if (nums[L] == target) {
                index = L;
                break;
            }
            
            if (nums[R] == target) {
                index = R;
                break;
            }
            
            if (nums[M] > nums[L]) {
                if (target > nums[L] && target < nums[M]) {
                    R = M - 1;
                } else {
                    L = M + 1;
                }
            } else if (target < nums[M]) {
                R = M - 1;
            } else if (target < nums[R]) {
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
        
        return index;
    }
}