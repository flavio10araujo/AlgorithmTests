package General.Heap.MovingBest;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a string s, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 * If possible, output any possible result. If not possible, return the empty string.
 * 
 * Example 1:
 * Input:s = "aab"
 * Output: aba
 * 
 * Example 2:
 * Input:s = "aaab"
 * Output:
 * 
 * Note:
 * s will consist of lowercase letters and have length in range [1, 500].
 * 
 * Solution
 * We divide the indices of the string into two: odds and evens. 
 * In that case, elements with odd indices can only be adjacent to elements with even indices, and vice versa. 
 * Note that because the index starts at zero, there will always be more or equal evens than odds. 
 * If there are more of one character than the number of evens, it is impossible to rearrange the string so no adjacent characters are the same. 
 * Otherwise, we can start from the character that occurs the most and fill out spots with even indices. 
 * Once we run out of spots to fill, we fill out the spots with odd indices. 
 * In this case, the result will guarantee to have no same adjacent characters.
 * For the implementation, we use a heap to guarantee the character that is repeated the most is processed first, 
 * and because the character repeated the most changes as we process more characters.
 */
public class ReorganizeString {

	public static String reorganizeString(String s) {
		
        int n = s.length();
        Map<Character, Integer> charCount = new HashMap<>();
        
        // Count up the characters that appear in the string.
        for (char c : s.toCharArray()) {
            charCount.merge(c, 1, Integer::sum);
        }
        
        // Use a max heap to compare the multiplicity of each character
        Queue<Map.Entry<Character, Integer>> heap = new PriorityQueue<>((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            heap.offer(entry);
        }
        
        // Return the empty string if there are too many of one character.
        if (heap.peek().getValue() > (n + 1) / 2) {
            return "";
        }
        
        // Stores the resulting char array to be converted to string.
        char[] res = new char[n];
        
        // Pointer to the next item to be inserted
        // Increment by 2 until it reaches the end to fill out even positions,
        // then it is reset to 1 to fill out odd positions
        int pointer = 0;
        
        // Insert characters into the char array by their multiplicity
        while (!heap.isEmpty()) {
            
        	Map.Entry<Character, Integer> pairing = heap.poll();
            
        	for (int i = 0; i < pairing.getValue(); i++) {
                res[pointer] = pairing.getKey();
                pointer += 2;
                if (pointer >= n) {
                    pointer = 1;
                }
            }
        }
        
        return new String(res);
    }
	
	public static void main(String[] args) {
        String s = "aab";
        String res = reorganizeString(s);
        
        if (res.isEmpty()) {
            System.out.println("Impossible");
            return;
        }
        
        Map<Character, Integer> sCount = new HashMap<>(), resCount = new HashMap<>();
        
        for (char e : s.toCharArray()) {
            sCount.merge(e, 1, Integer::sum);
        }
        
        for (char e : res.toCharArray()) {
            resCount.merge(e, 1, Integer::sum);
        }
        
        if (!sCount.equals(resCount)) {
            System.out.println("Not rearrangement");
            return;
        }
        
        for (int i = 0; i < res.length() - 1; i++) {
            if (res.charAt(i) == res.charAt(i + 1)) {
                System.out.printf("Same character at index %d and %d\n", i, i + 1);
                return;
            }
        }
        
        System.out.println("Valid");
    }
}