package General.AdvancedDataStructures.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * For this question, we ask you to compute the number of strings that conform to a given prefix. 
 * That is you will first be given a dictionary of words. 
 * Then you will be given a series of prefixes of words and you will be 
 * given a series of queries to return the number of words in the dictionary that contains that prefix. 
 * Each query will simply be a string which is asking for the number of words that contain the string.
 * Only lower-case English letters will be used.
 * 
 * Constraints
 * 1 <= words.length <= 100001
 * 1 <= words[i] <= 10
 * 
 * Example 1:
 * Input: word = ["forgot", "for", "algomonster", "while"], prefix = ["fo", "forg", "algo"]
 * Output: ans = [2, 1, 1]
 * Explanation: "forgot" and "for" have the prefix "fo", only "forgot" has "forg" and lastly only "algomonster" has the prefix "algo".
 * 
 * Solution
 * 
 * The solution is to use a trie to record the frequency of prefixes. 
 * We simply add a variable to each node to record the number of times a word uses this prefix as well as a method in our class to query our trie.
 * Alternatively one can precompute the answer through using a map for example to store the prefixes. 
 * Then simply answer every query by referencing the map. 
 * The solution used here will employ a trie to demonstrate how to use the data structure.
 */
public class PrefixCount {

	public static class Node {
        // Mostly the same as the provided trie data structure in the introductory article, add freq to keep track of frequency we get this prefix.
        char value;
        int freq;
        Map<Character, Node> children = new HashMap<Character, Node>();
        
        public Node(char value) {
            this.value = value;
        }
        
        void insert(String s, int idx) {
            freq++;
            
            if (s.length() == idx) {
                return;
            }
            
            if (children.containsKey(s.charAt(idx))) {
                children.get(s.charAt(idx)).insert(s, idx + 1);
            } else {
                children.put(s.charAt(idx), new Node(s.charAt(idx)));
                children.get(s.charAt(idx)).insert(s, idx + 1);
            }
        }
        
        // Function to check frequency we get a prefix.
        int prefixCheck(String s, int idx) {
            // We have reached end of prefix, terminate by returning the value.
            if (s.length() == idx) {
                return freq;
            }
            
            // Go to the children if it exists.
            if (children.containsKey(s.charAt(idx))) {
                return children.get(s.charAt(idx)).prefixCheck(s, idx + 1);
            }
            // If character not in children then our dictionary does not have prefix and return 0.
            else {
                return 0;
            }
        }
    }
	
    public static List<Integer> prefixCount(List<String> words, List<String> prefixes) {
        // Initialize data structure, relatively trivial to insert and query the data structure at this point.
        Node trie = new Node('$');
        
        for (String word : words) {
            trie.insert(word, 0);
        }
        
        Set<Map.Entry<Character, Node>> entries = trie.children.entrySet();
        entries.forEach((Map.Entry<Character, Node> entry) -> {
        	Character key = entry.getKey();
        	Node value = entry.getValue();
			System.out.println("key=" + key + ", value=" + value);
		});
        
        List<Integer> ans = new ArrayList<Integer>();
        
        for (String prefix : prefixes) {
          ans.add(trie.prefixCheck(prefix, 0));
        }
        
        return ans;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        List<String> words = splitWords("forgot for algomonster while");
        List<String> prefixes = splitWords("fo forg algo");
        List<Integer> res = prefixCount(words, prefixes);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}