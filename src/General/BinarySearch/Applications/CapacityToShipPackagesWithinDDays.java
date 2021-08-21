package General.BinarySearch.Applications;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * There are n packages that needs to be transported from one city to another, and you need to transport them there within d days. 
 * For the ith package, the weight of the package is weights[i]. 
 * You are required to deliver them in order, and in order to minimize the cost, you want to deliver the packages as light as possible. 
 * What is the minimum total weight of the packages delivered on the day where you deliver the maximum total weight?
 * 
 * Parameters
 * weights: A list of packages and their weights.
 * d: The number of days to deliver all packages.
 * Result
 * The minimum maximum total weight delivered.
 * 
 * Example
 * Input: weights = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], d = 5
 * Output: 15
 * Explanation:
 * The 1st day we deliver the first 5 package. 
 * The 2nd day we deliver the next 2, and for each following days, we deliver 1. 
 * The maximum weight delivered on each day is 15, and this value is the minimum.
 * 
 * Constraints
 * 1 <= len(weights) <= 5 * 10^4
 * 1 <= d <= len(weights)
 * 1 <= weights[i] <= 500
 */
public class CapacityToShipPackagesWithinDDays {
	
	public static int calcDays(List<Integer> weights, int maxWeight) {
        int reqDays = 1;
        int capacity = maxWeight;
        int i = 0, n = weights.size();
        
        while (i < n) {
            if (weights.get(i) <= capacity) {
                capacity -= weights.get(i);
                i++;
            } else {
                reqDays++;
                capacity = maxWeight;
            }
        }
        
        System.out.println("maxWeight="+maxWeight+" reqDays="+reqDays);
        
        return reqDays;
    }

    public static int minMaxWeight(List<Integer> weights, int d) {
        int L = Collections.max(weights);
        int R = 0;
        
        for (int weight : weights)
            R += weight;
        
        int boundaryIndex = R;
        
        while (L <= R) {
            int M = (L + R) / 2;
            int reqDays = calcDays(weights, M);
            
            if (reqDays > d) {
                L = M + 1;
            } else {
                boundaryIndex = M;
                R = M - 1;
            }
        }
        
        return boundaryIndex;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        List<Integer> weights = splitWords("1 2 3 4 5 6 7 8 9 10").stream().map(Integer::parseInt).collect(Collectors.toList());
        int d = Integer.parseInt("5");
        int res = minMaxWeight(weights, d);
        System.out.println(res);
    }
}