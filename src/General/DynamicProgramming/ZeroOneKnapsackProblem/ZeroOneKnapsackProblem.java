package General.DynamicProgramming.ZeroOneKnapsackProblem;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * The problem we are interested in is given a series of objects with a weight and value and a knapsack that can carry a set amount of weight, 
 * what is the maximum object value we can put in our knapsack without exceeeding the weight.
 * 
 * Input
 * weights: an array of integers that denote the weights of objects
 * values: an array of integers that denote the values of objects
 * max_weight: the maximum weight in the knapsack
 * Output: the maximum value in the knapsack
 * 
 * Example 1:
 * Input:
 * weights = [3, 4, 7]
 * values = [4, 5, 8]
 * max_weight = 7
 * Output: 9
 * Explanation:
 * We have a knapsack of max limit 7 
 * with 3 objects of weight-value pairs of [3,4],[4,5],[7,8] 
 * then the maximal value we can achieve is using the first 2 objects to obtain value 4 + 5 = 9.
 */
public class ZeroOneKnapsackProblem {
	public static int knapsack(List<Integer> weights, List<Integer> values, int maxWeight) {
        // Make a dp array and fill the array with -1 making sure to set weight 0 to value 0.
        int [] dp = new int[maxWeight + 1];
        Arrays.fill(dp, -1);
        
        dp[0] = 0;
        
        // Loop through every object which is simply length of weights array, 
        // values array also works as they should be same length.
        for (int i = 0; i < weights.size(); i++) {
            // As discussed make sure to loop from highest value backwards to avoid reusing the same object.
            for (int j = maxWeight; j >= weights.get(i); j--) {
                // If we establish a particular weight is achievable we update out current weight maximum value.
                if (dp[j - weights.get(i)] != -1) {
                    dp[j] = Math.max(dp[j], dp[j - weights.get(i)] + values.get(i));
                }
            }
        }
        
        int maxValue = -1;
        
        for (int i = 0; i < maxWeight + 1; i++) {
            maxValue = Math.max(maxValue, dp[i]);
        }
        
        return maxValue;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 3 4 7
    // 4 5 8
    // 7
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> weights = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> values = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        int maxWeight = Integer.parseInt(scanner.nextLine());
        scanner.close();
        int res = knapsack(weights, values, maxWeight);
        System.out.println(res);
    }
}