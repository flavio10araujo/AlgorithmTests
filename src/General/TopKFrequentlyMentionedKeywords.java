package General;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/top-k-frequent-words/
 * 
 * Given an array of strings words and an integer k, return the k most frequent strings.
 * Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.
 * 
 * Example 1:4
 * Input: words = ["i","love","leetcode","i","love","coding"], k = 2
 * Output: ["i","love"]
 * Explanation: "i" and "love" are the two most frequent words. Note that "i" comes before "love" due to a lower alphabetical order.
 *
 * Example 2:
 * Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
 * Output: ["the","is","sunny","day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
 */
public class TopKFrequentlyMentionedKeywords {

	public static List<String> topKFrequent(String[] words, int k) {	
        Map<String, Integer> mapWords = new HashMap<>();
        
        // O(n)
        for (String word : words) {
        	mapWords.put(word, mapWords.getOrDefault(word, 0) + 1);
        }
        
        // Creating an heap.
        Queue<String> heap = new PriorityQueue<>(new Comparator<String>() {
        	@Override
        	public int compare(String word1, String word2) {
        		int frequency1 = mapWords.get(word1);
        		int frequency2 = mapWords.get(word2);
        		
        		if (frequency1 == frequency2) {
        			return word2.compareTo(word1);
        		}
        		
        		return frequency1 - frequency2;
        	}
        });
        
        // O(n).
        for (Map.Entry<String, Integer> entry : mapWords.entrySet()) {
        	heap.add(entry.getKey());
        	
        	if (heap.size() > k) {
        		heap.poll();
        	}
        }
        
        List<String> res = new ArrayList<String>();
        
        while (!heap.isEmpty()) {
        	res.add(heap.poll());
        }
        
        Collections.reverse(res);
        
        return res;
    }
	
	public static void main(String[] args) {
    	//String[] words = {"i","love","leetcode","i","love","coding"};
    	//int k = 2;
    	
    	String[] words = {"the","day","is","sunny","the","the","the","sunny","is","is"};
    	int k = 4;
    	
        List<String> res = topKFrequent(words, k);
        System.out.println(String.join(" ", res));
    }
}