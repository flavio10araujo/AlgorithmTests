package General.AdvancedDataStructures.Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This questions was asked by Amazon.
 * 
 * Input:
 * words = a list of words
 * search = the word that we are looking for
 * Output:
 * wordsAutocomplete = a list of words that starts with the word "search".
 * 
 * Example:
 * words = "flavio", "flavia", "franco", "galera", "flaviano", "grude", "flaviana"
 * search = "flavi"
 * wordsAutocomplete = flavio, flavia, flaviano, flaviana
 */
public class AutocompleteReturnWords {

	public static void main(String[] args) {
		List<String> words = List.of("flavio", "flavia", "franco", "galera", "flaviano", "grude", "flaviana");
		String search = "flavi";
		
		System.out.println(autocomplete(words, search));
	}
	
	public static List<String> autocomplete(List<String> words, String search) {
		Node trie = new Node('$');
		
		for (String word : words) {
			trie.insert(word, 0);
		}
		
		System.out.println("In the list " + words + " there are " + trie.countPrefix(search, 0) + " words with the prefix " + search + ".");
		
		return trie.getWords(search, 0);
	}
	
	static class Node {
		char value;
		Map<Character, Node> children = new HashMap<>();
		
		int freq;
		List<String> words = new ArrayList<>();
		
		public Node(char value) {
			this.value = value;
		}
		
		public void insert(String word, int index) {
			 freq++;
			 words.add(word);
			 
			 if (index == word.length()) {
				 return;
			 }
			 
			 if (children.containsKey(word.charAt(index))) {
				 children.get(word.charAt(index)).insert(word, index + 1);
			 } else {
				 children.put(word.charAt(index), new Node(word.charAt(index)));
				 children.get(word.charAt(index)).insert(word, index + 1);
			 }
		}
		
		public int countPrefix(String search, int index) {
			if (index == search.length()) {
				return freq;
			}
			
			if (children.containsKey(search.charAt(index))) {
				return children.get(search.charAt(index)).countPrefix(search, index + 1);
			} else {
				return 0;
			}
		}
		
		public List<String> getWords(String search, int index) {
			if (index == search.length()) {
				return words;
			}
			
			if (children.containsKey(search.charAt(index))) {
				return children.get(search.charAt(index)).getWords(search, index + 1);
			} else {
				return words;
			}
		}
	}
}