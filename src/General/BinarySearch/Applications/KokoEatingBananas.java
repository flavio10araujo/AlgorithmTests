package General.BinarySearch.Applications;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Annual Banana Festival is here in Umbristan. 
 * The Annual Banana Festival is one of the most renowned cultural celebration in Umbristan, 
 * and Koko is very excited because she loves eating bananas and banana foods. 
 * Banana smoothie, banana boat, you name it, she loves it.
 * 
 * Out of the n at the fair stands selling bananas, each stand sells a unique banana dish, Koko wants to try all of them. 
 * In fact, for the ith stand, Koko wants to have at least min_cals[i] Calories of food 
 * (Did you know that Calories and calories are different? 1 Cal = 1 kcal = 1000 cal). 
 * 
 * During each hour while the fair is open to the public, Koko decides that she can eat up to k Calories of food from one stand.
 * Problem is, the fair is open to the public for a limited time - h hours to be exact - yet she would like to eat 
 * as much as she wants before the fair ends. 
 * However, she would like k to be as small as possible, as she is on a diet. 
 * You understand her struggle, so you would like to help.
 * 
 * Find the smallest integer k that allows her to eat what she wants before the fair closes for the public.
 * Parameters
 * min_cals: A list of Calories Koko wants to each from the food of each stand.
 * h: The number of hours the fair opens for.
 * Result: The minimum integer k, as described above.
 * 
 * Example
 * Input: min_cals = [3, 6, 7, 11], h = 8
 * Output: 4
 * Explanation:
 * When k = 4, Koko can eat from each stand for 1, 2, 2, 3 hours, respectively. 
 * Any smaller k will cause her to spend more time to eat, exceeding the time limit.
 * 
 * Constraints
 * 1 <= len(min_cals) <= 10^4
 * len(min_cals) <= h <= 10^9
 * 1 <= min_cals[i] <= 10^9
 */
public class KokoEatingBananas {

	public static int minK(List<Integer> minCals, int h) {
        
		int L = 1;
		int R = Collections.max(minCals);
		int M = -1;
		int K = 0;
		
		while (L <= R) {
			M = (L + R) / 2;
			
			if (calcStandHours(M, minCals) <= h) {
				R = M - 1;
				K = M;
			} else {
				L = L + 1;
			}
		}
		
        return K;
    }
	
	public static int calcStandHours(int k, List<Integer> minCals) {
		int sum = 0;
		
		for (int i = 0; i < minCals.size(); i++) {
			if (minCals.get(i) <= k) {
				sum++;
			} else {
				sum = sum + (minCals.get(i) / k);
				
				if (minCals.get(i) % k != 0) {
					sum++;
				}
			}
		}
		
		return sum;
	}

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        List<Integer> minCals = splitWords("30 11 23 4 20").stream().map(Integer::parseInt).collect(Collectors.toList());
        int h = Integer.parseInt("5");
        int res = minK(minCals, h);
        System.out.println(res);
    }
}