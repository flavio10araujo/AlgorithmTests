package General.DynamicProgramming.DynamicNumberOfSubproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * You have a list of envelopes, who each have an integer width and height. 
 * An envelope can fit into another envelope if and only if the first envelope's width and height 
 * is smaller than the other envelope's width and height (you cannot rotate either envelopes).
 * Given a list of envelopes, find the maximum number of envelopes that you can fit inside one another like a Russian Doll.
 * 
 * Input
 * envelopes: a list of integer pairings representing the envelopes. 
 * 			  For each pair, the first integer represents the width, and the second integer represents the height.
 * Output: The number representing the max envelope layers.
 * 
 * Example 1:
 * Input: envelopes = [[5, 4], [6, 4], [6, 7], [2, 3]]
 * Output: 3
 * Explanation: [2, 3] goes inside [5, 4], which goes inside [6, 7].
 * 
 * Constraints
 * 1 <= len(envelopes) <= 5000
 * 1 <= width[i], height[i] <= 10^4
 * 
 * Solution:
 * 
 * For this problem, note that if an envelope can fit inside another envelope, 
 * the sum of its weight and height must be smaller than the other one's sum. 
 * Note the converse is not necessarily true.
 * In that case, what we can do is sort the entries by the sum of their edges and use a bottom-up DP. 
 * For each envelope, we calculate the maximum number of envelopes that can fit in it (including itself). 
 * It should equal to the maximum number of envelops that can fit into an envelope smaller than it plus 1. 
 * Since the smaller envelope has a smaller edge length sum, we only need to check the envelops whose edge is smaller than the current envelope.
 * The time complexity is O(n^2), where n is the number of envelopes.
 */
public class RussianDollEnvelopes {

	public static int maxLayers(List<List<Integer>> envelopes) {
        
		Collections.sort(envelopes, (a, b) -> Integer.compare(a.get(0) + a.get(1), b.get(0) + b.get(1)));
        
		List<Integer> maxLayersUntilEnvelope = new ArrayList<>();
        
        for (int i = 0; i < envelopes.size(); i++) {
            int maxPreviousLayers = 0;
            
            for (int j = 0; j < i; j++) {
                if (envelopes.get(j).get(0) < envelopes.get(i).get(0) && envelopes.get(j).get(1) < envelopes.get(i).get(1)) {
                    maxPreviousLayers = Math.max(maxPreviousLayers, maxLayersUntilEnvelope.get(j));
                }
            }
            
            maxLayersUntilEnvelope.add(maxPreviousLayers + 1);
        }
        
        return Collections.max(maxLayersUntilEnvelope);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 4
    // 5 4
    // 6 4
    // 6 7
    // 2 3
    // Output: 3
    
    // 3
    // 1 3
    // 3 1
    // 3 3
    // output: 1
    
    // 8
    // 1 2
    // 5 4
    // 3 3
    // 10 10
    // 4 5
    // 6 5
    // 6 7
    // 8 7
    // output: 6
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int envelopesLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> envelopes = new ArrayList<>();
        
        for (int i = 0; i < envelopesLength; i++) {
            envelopes.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        
        scanner.close();
        int res = maxLayers(envelopes);
        System.out.println(res);
    }
}