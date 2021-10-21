package Tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You work on a team whose job is to understand the most sought after toys for the holiday season. 
 * A teammate of yours has built a web crawler that extracts a list of quotes about toys from different articles. 
 * You need to take these quotes and identify which toys are mentioned most frequently. 
 * Write an algorithm that identifies the top N toys out of a list of quotes and a list of toys.
 * Your algorithm should output the top N toys mentioned most frequently in the quotes.
 *
 * Input
 * The input consists of five arguments:
 * 	numToys: an integer representing the number of toys.
 * 	topToys: an integer representing the number of top toys your algorithm needs to return.
 * 	toys: a list of strings representing the toys.
 * 	numQuotes: an integer representing the number of quotes about toys.
 * 	quotes: a list of strings that consists of space-separated words representing articles about toys.
 * 
 * Output
 * Return a list of strings of the most popular N toys in order of most to least frequently mentioned.
 * 
 * Note
 * The comparison of strings is case-insensitive. 
 * If the value of topToys is more than the number of toys, return the names of only the toys mentioned in the quotes. 
 * If toys are mentioned an equal number of times in quotes, sort by the count of quotes.
 * 
 * Example:
 * Input:
 *   numToys = 6; topToys = 2; toys = ["elmo", "elsa", "legos", "drone", "tablet", "warcraft"]; numQuotes = 6
 *   quotes = [
 *   	"Elmo is the hottest of the season! Elmo will be on every kid's wishlist!",
 *      "The new Elmo dolls are super high quality",
 *      "Expect the Elsa dolls to be very popular this year, Elsa!",
 *      "Elsa and Elmo are the toys I'll be buying for my kids, Elsa is good",
 *      "For parents of older kids, look into buying them a drone",
 *      "Warcraft is slowly rising in popularity ahead of the holiday season"
 *   ]
 * Output: ["elmo", "elsa"]
 * 
 * Explanation:
 * elmo - 4; elsa - 4
 * "elmo" should be placed before "elsa" in the result because "elmo" appears in 3 different quotes and "elsa" appears in 2 different quotes.
 */

// Explanation here:
// https://molchevskyi.medium.com/top-k-frequently-mentioned-keywords-c8a5378822e

public class AmazonTopKBuzzwords {

	public static void main(String[] args) {
		AmazonTopKBuzzwords q = new AmazonTopKBuzzwords();

		List<String> toys = new ArrayList<String>();
		toys.add("elmo");
		toys.add("elsa");
		toys.add("legos");
		toys.add("drone");
		toys.add("tablet");
		toys.add("wacraft");

		List<String> quotes = new ArrayList<String>();
		quotes.add("Elmo is the hottest of the season! Elmo will be on every kid's wishlist!");
		quotes.add("The new Elmo dolls are super high quality");
		quotes.add("Expect the Elsa dolls to be very popular this year, Elsa!");
		quotes.add("Elsa and Elmo are the toys I'll be buying for my kids, Elsa is good");
		quotes.add("For parents of older kids, look into buying them a drone");
		quotes.add("Warcraft is slowly rising in popularity ahead of the holiday season");

		int numToys = 6;
		int topToys = 2;
		int numQuotes = 6;

		List<String> retorno = q.popularNToys(numToys, topToys, toys, numQuotes, quotes);
		
		for (String s : retorno) {
			System.out.println(s);
		}
	}

	public List<String> popularNToys(int numToys, int topToys, List<String> toys, int numQuotes, List<String> quotes) {

		quotes = preProcessReviews(quotes);
		
		Map<String,Integer> countToyMentions = new HashMap<>();
        Map<String, Integer> countToyMentionsDistinctReviews = new HashMap<>();
        
        for (String toy : toys) {
            countToyMentions.put(toy, 0);
            countToyMentionsDistinctReviews.put(toy, 0);
        }
        
        for (int i = 0; i < quotes.size(); i++) {
        	for (int j = 0; j < toys.size(); j++) {
        		if (quotes.get(i).toLowerCase().contains(toys.get(j).toLowerCase())) {
                    countToyMentionsDistinctReviews.put(toys.get(j), countToyMentionsDistinctReviews.get(toys.get(j)) + 1);
                }
            }
        }
        
        //System.out.println(countToyMentionsDistinctReviews);
        
        for (int i = 0; i < quotes.size(); i++) {
            for (String reviewWord : quotes.get(i).toLowerCase().split(" ")) {
                if (countToyMentions.containsKey(reviewWord)) {
                    countToyMentions.put(reviewWord, countToyMentions.get(reviewWord) + 1);
                }
            }
        }
        
        //System.out.println(countToyMentions);
        
        Queue<String> heap = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String t1, String t2) {
                if (countToyMentions.get(t1) != countToyMentions.get(t2)) {
                    return countToyMentions.get(t1) - countToyMentions.get(t2);
                } else if(countToyMentionsDistinctReviews.get(t1) != countToyMentionsDistinctReviews.get(t2)) {
                    return countToyMentionsDistinctReviews.get(t1) - countToyMentionsDistinctReviews.get(t2);
                } else {
                    return t2.compareTo(t1);
                }
            }
        });
        
        for (String toy : toys) {
            heap.offer(toy);
            
            if (heap.size() > topToys) {
                heap.poll();
            }
        }
        
        //System.out.println(heap);
        
        List<String> topKToys = new ArrayList<>(topToys);
        
        while (!heap.isEmpty()) {
            topKToys.add(heap.poll());
        }
        
        Collections.reverse(topKToys);
        
        return topKToys;
	}
	
	private static List<String> preProcessReviews(List<String> quotes) {
        for (int i = 0; i < quotes.size(); i++) {
            quotes.set(i, quotes.get(i).replaceAll("[^ a-zA-Z]", "").toLowerCase());
        }
        
        return quotes;
    }
}