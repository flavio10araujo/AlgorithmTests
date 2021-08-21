package Tests;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Testando04 {

	public static void main(String[] args) {

		String s = "aab";
		
		if (s == null || "".equals(s) || s.length() <= 1) {
			System.out.println("Não rola.");
		} else if (s.length() == 2) {
			if (s.charAt(0) == s.charAt(1)) {
				System.out.println("Não rola.");
			} else {
				System.out.println(s);
			}
		} else {		
			Map<Character, Integer> countChars = new HashMap<>();
			int maxOccurence = 0;
			
			for (char c : s.toCharArray()) {
				maxOccurence = Math.max(maxOccurence, countChars.getOrDefault(c, 0) + 1);
				countChars.put(c, countChars.getOrDefault(c, 0) + 1);
			}
			
			System.out.println(countChars);
			
			System.out.println("Max occurence: " + maxOccurence);
			
			if ((s.length() + 1) / 2 < maxOccurence) {
				System.out.println("Não rola.");
			} else {
				Queue<Map.Entry<Character, Integer>> heap = new PriorityQueue<>((a, b) -> Integer.compare(b.getValue(), a.getValue()));
				
				for (Map.Entry<Character, Integer> entry : countChars.entrySet()) {
					heap.offer(entry);
				}
				
				System.out.println(heap);
				
				int n = s.length();
				// Stores the resulting char array to be converted to string.
		        char[] res = new char[n];
		        
		        // Pointer to the next item to be inserted.
		        // Increment by 2 until it reaches the end to fill out even positions, then it is reset to 1 to fill out odd positions.
		        int pointer = 0;
		        
		        // Insert characters into the char array by their multiplicity.
		        while (!heap.isEmpty()) {
		            
		        	Map.Entry<Character, Integer> pairing = heap.poll();
		        	System.out.println("pairing: " + pairing);
		            
		        	for (int i = 0; i < pairing.getValue(); i++) {
		        		
		        		System.out.println(" res["+i+"] = " + res[i]);
		        		res[pointer] = pairing.getKey();
		        		System.out.println(" res["+i+"] = " + res[i]);
		        		
		        		
		                pointer += 2;
		                
		                if (pointer >= n) {
		                    pointer = 1;
		                }
		            }
		        }
			}
		}
	}
}