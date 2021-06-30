package Tests;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
 *      "Expect the Elsa dolls to be very popular this year, Elsa",
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
		quotes.add("o brinquedo ELMO é o mais legal");
		quotes.add("comprei para meu filho um tablet");
		quotes.add("ainda tem muita gente na fila para comprar a nova versão do eLmO para baixinhos");
		quotes.add("o lançamento do novo tablet da apple vem em várias cores");
		quotes.add("elMO e elsa são os grandes vencedores");
		quotes.add("os mais velhos preferem os legos porque os legos mais novos são melhores que os legos antigos");
		quotes.add("o filme da elsa na neve venceu todas as categorias");

		// Results expected:
		// 1) elmo = 3
		// 2) elsa = 2
		// 3) tablet = 2

		int numToys = 6;
		int topToys = 3;
		int numQuotes = 7;

		List<String> retorno = q.popularNToys(numToys, topToys, toys, numQuotes, quotes);
		
		for (String s : retorno) {
			System.out.println(s);
		}
	}

	public List<String> popularNToys(int numToys, int topToys, List<String> toys, int numQuotes, List<String> quotes) {

		// Map for tracing all the occurrences for every toy in the list toys[].
		// The TreeMap was chosen because its elements are added in the right order (alphabetically).
		//Map<String, Integer> toysOccurrences = new TreeMap<String, Integer>();
		Map<String, Integer> toysOccurrences = new HashMap<String, Integer>();
		// The list of the n (being n = topToys) more quoted toys in quotes[].
		List<String> mostQuotedToys = new ArrayList<String>();

		for (int i = 0; i < quotes.size(); i++) {
			for (int j = 0; j < toys.size(); j++) {
				// Searching the toy from the list toys[] in each item of the array quotes[].
				// Ignoring the case sensitive with a toLowerCase in both of the contents.
				if (quotes.get(i).toLowerCase().contains(toys.get(j).toLowerCase())) {
					// If : the toy is already in the map toysOccurrences, so we add 1 for its value.
					// Else : We add the toy in the map toysOccurrences with value 1.
					if (toysOccurrences.get(toys.get(j)) != null) {
						toysOccurrences.put(toys.get(j), toysOccurrences.get(toys.get(j)) + 1);
					} else {
						toysOccurrences.put(toys.get(j), 1);
					}
				}
			}
		}

		// We need to sort the map by the numbers of occurrences of each toy.
		Map<String, Integer> sortedMap = sortByValues(toysOccurrences);
		Set set = sortedMap.entrySet();
	    Iterator i = set.iterator();
	 
	    // We need to get the n (being n = topToys) first toys in the list.
	    while(i.hasNext() && topToys > 0) {
	    	topToys--;
	    	Map.Entry me = (Map.Entry) i.next();
	    	mostQuotedToys.add(me.getKey().toString());
	    }

		return mostQuotedToys;
	}

	// Method for sorting the TreeMap descending based on values.
	// With Java 8.
	public static Map<String, Integer> sortByValues(final Map<String, Integer> map) {
		return map.entrySet().stream()
			    .sorted(Map.Entry.comparingByKey()) 			
			    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, TreeMap::new));
	}
	/*public static Map<String, Integer> sortByValues(final Map<String, Integer> map) {
		Comparator<String> valueComparator = new Comparator<String>() {
			public int compare(String k1, String k2) {
				int compare = map.get(k2).compareTo(map.get(k1));
				if (compare == 0)
					return 1;
				else
					return compare;
			}
		};
		
		Map<String, Integer> sortedByValues = new TreeMap<String, Integer>(valueComparator);
		sortedByValues.putAll(map);
		
		return sortedByValues;
	}*/
	// The same method but with generics:
	/*public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
		Comparator<K> valueComparator = new Comparator<K>() {
			public int compare(K k1, K k2) {
				int compare = map.get(k2).compareTo(map.get(k1));
				if (compare == 0)
					return 1;
				else
					return compare;
			}
		};

		Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
		sortedByValues.putAll(map);
		return sortedByValues;
	}*/
}