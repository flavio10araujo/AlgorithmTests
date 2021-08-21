package Tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Testando {
	
	public static void main(String[] args) {
		System.out.println(
				topToys(6, 
						2, 
						new String[]{"elmo", "elsa", "legos", "drone", "tablet", "warcraft"}, 
						6,
						new String[]{
								"Elmo is the hottest of the season! Elmo will be on every kid's wishlist!",
								"The new Elmo dolls are super high quality",
								"Expect the Elsa dolls to be very popular this year, Elsa!",
								"Elsa and Elmo are the toys I'll be buying for my kids, Elsa is good",
								"For parents of older kids, look into buying them a drone",
								"Warcraft is slowly rising in popularity ahead of the holiday season"
				}));
	}

	static class Toy {
		String name;
		int freq;
		int sentenceCnt;
		public Toy(String name, int freq, int sentenceCnt) {
			this.name = name;
			this.freq = freq;
			this.sentenceCnt = sentenceCnt;
		}
	}

	public static List<String> topToys(int numToys, int topToys, String[] toys, int numQuotes, String[] quotes) {
		
		Set<String> set = new HashSet<>(Arrays. asList(toys));
		Map<String, Integer> freqMap = new HashMap<>();
		Map<String, Set<Integer>> sentenceMap = new HashMap<>();
		
		for (String t : toys) {
			freqMap.put(t, 0);
			sentenceMap.put(t, new HashSet<>());
		}

		for (int i = 0; i < quotes.length; i++) {    
			String[] words = quotes[i].toLowerCase().split("\\W+");
			
			for (String word : words) {
				if (!set.contains(word)) {
					continue;
				}
				freqMap.put(word, freqMap.get(word) + 1);
				sentenceMap.get(word).add(i);
			}
		}
		
		PriorityQueue<Toy> pq = new PriorityQueue<>((p1, p2) -> {
			if (p1.freq != p2.freq) {
				return p2.freq - p1.freq;
			} else if (p2.sentenceCnt != p1.sentenceCnt) {
				return p2.sentenceCnt - p1.sentenceCnt;
			} else {
				return p1.name.compareTo(p2.name);
			}
		});
		
		for (String t : toys) {
			pq.add(new Toy(t, freqMap.get(t), sentenceMap.get(t).size()));
		}
		
		List<String> res = new ArrayList<>();
		int cnt = topToys;
		while (cnt > 0 && !pq.isEmpty()) {
			res.add(pq.poll().name);
			cnt--;
		}
		
		return res;
	}
}