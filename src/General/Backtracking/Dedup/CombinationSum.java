package General.Backtracking.Dedup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an array of distinct integers candidates and a target integer target, 
 * return a list of all unique combinations of candidates where the chosen numbers sum to target. 
 * You may return the combinations in any order.
 * The same number may be chosen from candidates an unlimited number of times. 
 * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 * It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 * 
 * Examples:
 * 
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation: 
 *   2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 *   7 is a candidate, and 7 = 7.
 *   These are the only two combinations.
 * 
 * Example 2:
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * 
 * Example 3:
 * Input: candidates = [2], target = 1
 * Output: []
 * 
 * Example 4:
 * Input: candidates = [1], target = 1
 * Output: [[1]]
 * 
 * Example 5:
 * Input: candidates = [1], target = 2
 * Output: [[1, 1]]
 * 
 * Constrains:
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * All elements of candidates are distinct.
 * 1 <= target <= 500
 */
public class CombinationSum {
	public static List<List<Integer>> combinationSum(List<Integer> candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        dfs(target, candidates, res, new ArrayList<Integer>(), 0);
        return res;
    }
    
    public static List<List<Integer>> dfs(int target, 
                                          List<Integer> candidates, 
                                          List<List<Integer>> res, 
                                          List<Integer> path,
                                         int start) {
        int sum = sumValuesInPath(path);
        
        if (sum >= target) {
            if (sum == target) {
                res.add(new ArrayList<Integer>(path));
            }
            
            return res;
        }
        
        for (int i = start; i < candidates.size(); i++) {
            path.add(candidates.get(i));
            dfs(target, candidates, res, path, i);
            path.remove(path.size() - 1);
        }
        
        return res;
    }
    
    public static int sumValuesInPath(List<Integer> path) {
        int r = 0;
        
        for (Integer i : path) {
            r += i;
        }
        
        return r;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        List<Integer> candidates = splitWords("2 3 5").stream().map(Integer::parseInt).collect(Collectors.toList());
        int target = Integer.parseInt("8");
        
        List<List<Integer>> res = combinationSum(candidates, target);
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}