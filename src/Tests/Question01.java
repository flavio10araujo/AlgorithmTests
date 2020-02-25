package Tests;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Question01 {

	public static void main(String[] args) {
		Question01 q = new Question01();

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
		int topToys = 2;
		int numQuotes = 7;

		ArrayList<String> retorno = q.popularNToys(numToys, topToys, toys, numQuotes, quotes);
		
		for (String s : retorno) {
			System.out.println(s);
		}
	}

	public static ArrayList<String> popularNToys(int numToys, int topToys, List<String> toys, int numQuotes, List<String> quotes) {

		// Map for tracing all the occurrences for every toy in the list toys[].
		// The TreeMap was chosen because its elements are added in the right order (alphabetically).
		Map<String, Integer> toysOccurrences = new TreeMap<String, Integer>();
		// The list of the n (being n = topToys) more quoted toys in quotes[].
		ArrayList<String> mostQuotedToys = new ArrayList<String>();

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
		Map sortedMap = sortByValues(toysOccurrences);
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
	public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
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
	}
}