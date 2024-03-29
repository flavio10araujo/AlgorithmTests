package General.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 * 
 * Input: nums = [1,2,3]
 * Output:
 * [
 * 	[3],
 * 	[1],
 * 	[2],
 * 	[1,2,3],
 * 	[1,3],
 * 	[2,3],
 * 	[1,2],
 * 	[]
 * ]
 */
public class Subsets {
	
	public static List<List<Integer>> subsets(List<Integer> nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        getSubsets(nums, res, new ArrayList<Integer>(), 0);
        
        return res;
    }
    
    /**
     * Time complexity: O(n ^ 2).
     * 
     * @param res
     * @param nums
     * @param path
     * @param start
     * @return
     */
	public static void getSubsets(List<Integer> nums, List<List<Integer>> res, List<Integer> path, int start) {
	    
        if (start <= nums.size()) {
            res.add(new ArrayList<Integer>(path));
        }
        
        if (start > nums.size()) {
            return;
        }
        
        for (int i = start; i < nums.size(); i++) {
            path.add(nums.get(i));
            
            getSubsets(nums, res, path, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        List<Integer> nums = splitWords("1 2 3").stream().map(Integer::parseInt).collect(Collectors.toList());
        List<List<Integer>> res = subsets(nums);
        
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}