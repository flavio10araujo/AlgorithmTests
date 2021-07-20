package General.TwoPointers.OppositeDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given a list of integers, return a list containing all unique triplets in the list such that the sum of the triplet is zero. 
 * 
 * Each triplet must be sorted in ascending order, and the resulting list must be sorted lexicographically.
 * 
 * Parameter
 * nums: a list of integers
 * Result
 * A list of triplets containing all unique triplets that sums up to zero, sorted.
 * 
 * Example 1
 * Input: nums = [-1, 0, 1, 2, -1, -4]
 * Output: [[-1, -1, 2], [-1, 0, 1]]
 * 
 * Example 2
 * Input: nums = [1, -1, 2, -2, 3, -3, 4, -4]
 * Output: [[-4, 1, 3], [-3, -1, 4], [-3, 1, 2], [-2, -1, 3]]
 */
public class ThreeSum {
	
	public static List<List<Integer>> tripletsWithSum0(List<Integer> nums) {
        
		// This stores how many numbers of each kind is in the list.
        Map<Integer, Integer> numCount = new HashMap<>();
        for (int i : nums) {
            numCount.merge(i, 1, Integer::sum);
        }
        
        // This stores all unique numbers in "nums", sorted.
        List<Integer> uniqueNums = new ArrayList<>(numCount.keySet());
        Collections.sort(uniqueNums);
        
        // Stores a list of triplets that is the return value.
        List<List<Integer>> resList = new ArrayList<>();
        
        // "i" is the first, and slow pointer, pointing towards the first value of the triplets.
        for (int i = 0; i < uniqueNums.size(); i++) {
            
        	int firstNum = uniqueNums.get(i);
            
        	// Subtract the count by 1 so it can't be used by the second or third pointer.
            numCount.merge(firstNum, -1, Integer::sum);
            
            // "j" is the second pointer that moves forward, pointing towards the second value of the triplets.
            // With that, we can easily get the value of the third pointer.
            // Because we are using a map, we can easily get whether there is a number for the third pointer and whether it hasn't been used up yet.
            for (int j = i; j < uniqueNums.size(); j++) {
                
            	int secondNum = uniqueNums.get(j);
                int thirdNum = -firstNum - secondNum;
                
                // Inner loop ends if the third number is smaller than the second.
                if (thirdNum < secondNum) {
                    break;
                }
                
                // If the number is used up by the first one, we can't assign it to the second one, go to the next one.
                if (numCount.get(secondNum) <= 0) {
                    continue;
                }
                
                numCount.merge(secondNum, -1, Integer::sum);
                
                // If the number is used up by the previous ones, or if it doesn't exist, go to the next one and reset counter.
                if (numCount.getOrDefault(thirdNum, 0) <= 0) {
                    numCount.merge(secondNum, 1, Integer::sum);
                    continue;
                }
                
                // Otherwise, add the triplet to the result.
                List<Integer> newList = new ArrayList<>();
                newList.add(firstNum);
                newList.add(secondNum);
                newList.add(thirdNum);
                resList.add(newList);
                numCount.merge(secondNum, 1, Integer::sum);
            }
        }
        
        return resList;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        List<Integer> nums = splitWords("-1 0 1 2 -1 -4").stream().map(Integer::parseInt).collect(Collectors.toList());
        List<List<Integer>> res = tripletsWithSum0(nums);
        
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}