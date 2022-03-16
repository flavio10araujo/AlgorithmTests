package General;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 * 
 * Example 1:
 * Input: nums = [1,1,1,2,2,3]
 * Output: 5, nums = [1,1,2,2,3,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 */
public class RemoveDuplicatesFromSortedArrayII {

	public static void main(String[] args) {
		int[] nums = {1,1,1,2,2,3};
		System.out.println(solution01(nums));

		int[] nums2 = {1,1,1,2,2,3};
		System.out.println(solution02(nums2));
	}

	public static int solution01(int[] nums) {
		int i = 0;

		for (final int num : nums)
			if (i < 2 || num > nums[i - 2])
				nums[i++] = num;

		return i;
	}

	public static int solution02(int[] nums) {
		if (nums.length < 3) {
			return nums.length;
		}

		int in = 2;

		for (int i = 2; i < nums.length; i++) {
			if (nums[i] != nums[in - 2]) {
				nums[in] = nums[i];
				in++;
			}
		}

		return in;
	}
}