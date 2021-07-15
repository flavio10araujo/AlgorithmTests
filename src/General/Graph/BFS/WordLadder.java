package General.Graph.BFS;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Word Ladder is "a puzzle begins with two words, and to solve the puzzle one must find a chain of other words to link the two, 
 * in which two adjacent words (that is, words in successive steps) differ by one letter."
 * 
 * For example: COLD → CORD → CARD → WARD → WARM
 * 
 * Given a start word, an end word and a list of dictionary words. 
 * Determine the minimum number of steps to go from the start word to the end word using only words from the dictionary.
 * 
 * Input:
 * start = "COLD"
 * end = "WARM"
 * word_list = ["COLD", "GOLD", "CORD", "SOLD", "CARD", "WARD", "WARM", "TARD"]
 * Output:
 * 4
 * Explanation: We can go from COLD to WARM by going through COLD → CORD → CARD → WARD → WARM
 */
public class WordLadder {

	public static final char[] ALPHABETS = new char[26];
    static {
        // ascii representation of english alphabets a - z are numbers 97 - 122
        for (int i = 0; i < 26; i++) {
            ALPHABETS[i] = (char) (i + 'a');
        }
    }

    public static int wordLadder(String begin, String end, List<String> wordList) {
        // make a set because existence query is O(1) vs O(N) for list
        Set<String> words = new HashSet<>(wordList);
        
        ArrayDeque<String> queue = new ArrayDeque<>();
        queue.add(begin);
        
        int distance = 0;
        
        while (queue.size() > 0) {
            int n = queue.size();
            distance++;
            
            for (int i = 0; i < n; i++) {
                String word = queue.pop();
                
                for (int j = 0; j < word.length(); j++) {
                    
                    for (char c : ALPHABETS) {
                        StringBuilder wordBuilder = new StringBuilder(word.length());
                        
                        wordBuilder.append(word.substring(0, j));
                        wordBuilder.append(c);
                        wordBuilder.append(word.substring(j + 1));
                        
                        String nextWord = wordBuilder.toString();
                        
                        if (!words.contains(nextWord)) continue;
                        
                        if (nextWord.equals(end)) return distance;
                        
                        queue.add(nextWord);
                        // removing from the set is equivalent as marking the word visited
                        words.remove(nextWord);
                    }
                }
            }
        }
        return 0;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }
    
    public static void main(String[] args) {
        String begin = "cold";
        String end = "warm";
        List<String> wordList = splitWords("cold gold cord card ward warm tard sold");
        int res = wordLadder(begin, end, wordList);
        System.out.println(res);
    }
}