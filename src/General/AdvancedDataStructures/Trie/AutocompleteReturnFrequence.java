package General.AdvancedDataStructures.Trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * For this question, we first give you a series of n words. 
 * For every word, we first add it to our dictionary and then we type out the word using the minimum number of strokes to then autocomplete the word. 
 * What is the minimum total number of strokes needed to type out all the words.
 * 
 * Constraints
 * 1 <= n <= 100001
 * Sum of the lengths of all the strings will not exceed 100001.
 * 
 * Words will only have lowercase letters.
 * 
 * Example 1:
 * Input: words = ["hi", "hello", "bojack", "hills", "hill"]
 * Output: 11
 * Explanation:
 * We put "hi" in our dictionary and then we only need to type out "h", which is 1 stroke, before it autocompletes to "hi". 
 * We put "hello" in our dictionary and then we only need to type out "he", which is 2 strokes, before it autocompletes to "hello". 
 * We put "bojack" in our dictionary and then we only need to type out "b" which is 1 stroke, before it autocompletes to "bojack". 
 * We put "hills" in our dictionary and then we only need to type out "hil"(since we have "hi") which is 3 strokes, before it autompletes to "hills". 
 * Lastly, we put "hill" in our dictionary and we need to type out "hill" which is 4 strokes. 
 * Adding these strokes up we have 1 + 2 + 1 + 3 + 4 = 11 which we then output.
 * 
 * Solution
 * 
 * The solution is to use a trie and maintain the frequency of prefixes that occur. 
 * Once the frequency becomes 1 we know that there is only 1 word left in this branch of the trie so we can stop searching. 
 * An implementation detail is to make sure when we have reached the end of the word 
 * so we can properly terminate and ensure we are counting the correct number of strokes.
 */
public class AutocompleteReturnFrequence {

	public static class Node {
        char value; // The value of the node.
        int freq; // The number of words that uses this char in the trie.
        Map<Character, Node> children = new HashMap<Character, Node>();
        
        public Node(char value) {
            this.value = value;
        }
        
        void insert(String s, int idx) {
            freq++;
            
            // Base case to stop the recursion.
            if (s.length() == idx) {
                return;
            }
            
            System.out.println("INSERT: s = " + s + " idx = " + idx + " s.charAt(idx) = " + s.charAt(idx) + " freq = "+ freq);
            
            if (children.containsKey(s.charAt(idx))) {
                children.get(s.charAt(idx)).insert(s, idx + 1);
            } else {
                children.put(s.charAt(idx), new Node(s.charAt(idx)));
                children.get(s.charAt(idx)).insert(s, idx + 1);
            }
        }

        int query(String s, int idx) {
        	// Base case to stop the recursion.
            if (s.length() == idx || freq == 1) {
                return 0;
            }
            
            return children.get(s.charAt(idx)).query(s, idx + 1) + 1;
        }
    }
	
    public static int autocomplete(List<String> words) {
        Node trie = new Node('$');
        int total = 0;
        
        for (String word : words) {
            trie.insert(word, 0);
            total += trie.query(word, 0);
        }
        
        return total + 1; // 
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // hi hello bojack hills hill
    // output: 11
    
    // a aa aaa aaaa aaaaa
    // output: 15
    
    // to be or not two bee
    // output: 9
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> words = splitWords(scanner.nextLine());
        scanner.close();
        int res = autocomplete(words);
        System.out.println(res);
    }
}