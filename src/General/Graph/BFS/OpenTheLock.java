package General.Graph.BFS;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Leetode 752
 * 
 * You commissioned a locksmith to craft a special combination lock for you, 
 * because you are a lich and you want to hide your phylactery in somewhere secure. 
 * It looks like a regular 4-digit combination lock, with each digit can be one of the digits from 0~9. 
 * Each digit can be turned forwards by 1 and backwards by 1, 
 * and the first and the last digit is connected (9 goes to 0 when moving forward, and 0 goes to 9 when moving backward). 
 * The lock starts at 0000.
 * 
 * However, because you don't want people to find your phylactery, 
 * you have trapped certain combinations of this combination lock. 
 * If someone were to set the combination to a trapped combination, 
 * bad things happen to them, which this question would not elaborate upon.
 * 
 * You know the combination, and you know the trapped combinations. 
 * You wonder if the lock can be opened without triggering the traps, 
 * and if so, how many digit changes must be made to the lock to unlock it.
 * 
 * Input
 * combo: a string representing the four digit combination to open the lock.
 * trapped_combos: a list of strings representing the trapped combinations.
 * Output
 * An integer representing the number of steps it takes to open the lock, or -1 if you can't open it without triggering the trap.
 * 
 * Example 1:
 * Input:
 * combo = "0202"
 * trapped_combos = ["0201","0101","0102","1212","2002"]
 * Output: 6
 * Explanation:
 * 0000 -> 1000 -> 1100 -> 1200 -> 1201 -> 1202 -> 0202, a total of 6 steps.
 * 
 * Constraints
 * The starting combination (0000) and the final combination is not trapped because that defeats the purpose of the lock.
 * 
 * Solution
 * 
 * This question is a standard BFS problem, except we consider two combinations who has one digit differ by one "adjacent". 
 * The implementation comes easily after we defined what "adjacent" means for this question.
 * The time complexity is O(n), where n is the number of possible combinations (which is 10^4 == 10000 in this case).
 */
public class OpenTheLock {

	public static Map<Character, Character> nextDigit = Map.of(
	        '0', '1',
	        '1', '2',
	        '2', '3',
	        '3', '4',
	        '4', '5',
	        '5', '6',
	        '6', '7',
	        '7', '8',
	        '8', '9',
	        '9', '0'
	    );

	    public static Map<Character, Character> prevDigit = nextDigit.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

	    public static int numSteps(String combo, List<String> trappedCombos) {
	        if (combo.equals("0000")) {
	            return 0;
	        }
	        
	        Set<String> trappedComboSet = new HashSet<>(trappedCombos);
	        Map<String, Integer> bfsMap = new HashMap<>();
	        
	        bfsMap.put("0000", 0);
	        
	        Deque<String> bfsQueue = new ArrayDeque<>();
	        bfsQueue.offer("0000");
	        
	        while (!bfsQueue.isEmpty()) {
	            
	        	String top = bfsQueue.poll();
	            
	        	for (int i = 0; i < 4; i++) {
	                String newCombo = top.substring(0, i).concat(String.valueOf(nextDigit.get(top.charAt(i)))).concat(top.substring(i + 1));
	                
	                if (!trappedComboSet.contains(newCombo) && !bfsMap.containsKey(newCombo)) {
	                    bfsQueue.offer(newCombo);
	                    bfsMap.put(newCombo, bfsMap.get(top) + 1);
	                    if (newCombo.equals(combo)) {
	                        return bfsMap.get(newCombo);
	                    }
	                }
	                
	                newCombo = top.substring(0, i).concat(String.valueOf(prevDigit.get(top.charAt(i)))).concat(top.substring(i + 1));
	                
	                if (!trappedComboSet.contains(newCombo) && !bfsMap.containsKey(newCombo)) {
	                    bfsQueue.offer(newCombo);
	                    bfsMap.put(newCombo, bfsMap.get(top) + 1);
	                    if (newCombo.equals(combo)) {
	                        return bfsMap.get(newCombo);
	                    }
	                }
	            }
	        }
	        
	        return -1;
	    }

	    public static List<String> splitWords(String s) {
	        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
	    }

	    // 0202
	    // 0201 0101 0102 1212 2002
	    // output: 6
	    
	    // 9999
	    // 0009 0090 0900 9000
	    // 6
	    
	    // 1111
	    // 0111 2111 1011 1211 1101 1121 1110 1112
	    // -1
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        String combo = scanner.nextLine();
	        List<String> trappedCombos = splitWords(scanner.nextLine());
	        scanner.close();
	        int res = numSteps(combo, trappedCombos);
	        System.out.println(res);
	    }
}