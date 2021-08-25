package General;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public static List<String> topMentioned(int k, List<String> keywords, List<String> reviews) {
		
        Pattern patt = Pattern.compile("\\b(:?" + String.join("|", keywords) + ")\\b", Pattern.CASE_INSENSITIVE);
        Map<String, Integer> counts = new HashMap<>();
        
        for (String review : reviews) {
            Matcher m = patt.matcher(review);
            Set<String> words = new HashSet<>();
            
            while (m.find())
                words.add(m.group(0).toLowerCase());
            
            for (String word : words)
                counts.merge(word, 1, Integer::sum);
        }
        
        Queue<Map.Entry<Integer, String>> queue = new PriorityQueue<>((a, b) -> {
            if (a.getKey() != b.getKey())
                return Integer.compare(a.getKey(), b.getKey());
            return -a.getValue().compareTo(b.getValue());
        });
        
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            queue.offer(Map.entry(entry.getValue(), entry.getKey()));
            
            if (queue.size() > k)
                queue.poll();
        }
        
        List<String> res = new ArrayList<>();
        
        while (!queue.isEmpty())
            res.add(queue.poll().getValue());
        
        Collections.reverse(res);
        
        return res;
    }
	
	// 2
	// 6
	// i
	// love
	// leetcode
	// i
	// love
	// coding
	// 
    public static void main(String[] args) {
    	long startTime = System.nanoTime();
    	
    	Scanner scanner = new Scanner(System.in);
        int k = Integer.parseInt(scanner.nextLine());
        int keywordsLength = Integer.parseInt(scanner.nextLine());
        List<String> keywords = new ArrayList<>();
        
        for (int i = 0; i < keywordsLength; i++) {
            keywords.add(scanner.nextLine());
        }
        
        int reviewsLength = Integer.parseInt(scanner.nextLine());
        List<String> reviews = new ArrayList<>();
        
        for (int i = 0; i < reviewsLength; i++) {
            reviews.add(scanner.nextLine());
        }
        
        scanner.close();
        List<String> res = topMentioned(k, keywords, reviews);
        System.out.println(String.join(" ", res));
        
        long endTime = System.nanoTime();
	    long timeElapsed = endTime - startTime;
	    System.out.println("Execution time in nanoseconds: " + timeElapsed);
	    System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }
}