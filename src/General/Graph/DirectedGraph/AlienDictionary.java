package General.Graph.DirectedGraph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * There is a new alien language which uses the latin alphabet. 
 * However, the order among letters are unknown to you.
 * You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. 
 * Derive the order of letters in this language.
 * 
 * Note:
 * You may assume all letters are in lowercase.
 * Every letter that appears in the input must also appear in the output, and your output cannot have characters not in the input.
 * If no ordering of letters makes the dictionary sorted lexicographically, return an empty string.
 * There may be multiple valid orders. If that's the case, return the smallest in normal lexicographical order.
 * 
 * Parameter:
 * 	words: A list of strings of size n, representing the dictionary words sorted lexicographically in the alien language.
 * Result:
 * 	A string representing the smallest possible lexicographical order, or an empty string if no valid order exists.
 * 
 * Example 1:
 * Input: ["z","x"]
 * Output: zx
 * Explanation: From z and x，we can get 'z < x'. So return zx.
 * 
 * Example 2:
 * Input: ["wrt","wrf","er","ett","rftt"]
 * Output: wertf
 * Explanation:
 * - wrt comes before wrf, so t comes before f.
 * - wrt comes before er, so w comes before e.
 * - er comes before ett, so r comes before t.
 * - ett comes before rftt, so e comes r. 
 * 
 * Constraints:
 * 	2 <= n <= 10000
 * 	1 <= words[i] <= 30
 */
public class AlienDictionary {

	public static String alienOrder(List<String> words) {
		
        Map<Character, Set<Character>> postReqs = new HashMap<>();
        Map<Character, Integer> preReqCount = new HashMap<>();
        
        // Iterate through each adjacent words.
        for (int i = 0; i < words.size() - 1; i++) {
            
        	String earlyWord = words.get(i);
        	String lateWord = words.get(i + 1);
            int lateLen = lateWord.length();
            
            // Comparing each character.
            for (int j = 0; j < earlyWord.length(); j++) {
                
            	// The second word is a prefix of the first, return empty string because it's not in lexicographical order.
                if (j >= lateLen) {
                    return "";
                }
                
                char earlyChar = earlyWord.charAt(j);
                char lateChar = lateWord.charAt(j);
                
                if (earlyChar != lateChar) {
                    // A difference in character, record the ordering of the characters.
                    if (!postReqs.containsKey(earlyChar)) {
                        postReqs.put(earlyChar, new HashSet<>());
                    }
                    
                    if (!postReqs.get(earlyChar).contains(lateChar)) {
                        postReqs.get(earlyChar).add(lateChar);
                        preReqCount.merge(lateChar, 1, Integer::sum);
                    }
                    
                    break;
                }
            }
        }
        
        // Count up all chars from all strings.
        Set<Character> allChars = new HashSet<>();
        
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                allChars.add(ch);
            }
        }
        
        // A heap to keep track of all free characters, with the normally smallest character on top.
        Queue<Character> freeHeap = new PriorityQueue<>();
        // ArrayList<Character> resultList = new ArrayList<>();
        char[] resultList = new char[allChars.size()];
        int pointer = 0;
        
        for (char ch : allChars) {
            if (preReqCount.getOrDefault(ch, 0) == 0) {
                freeHeap.offer(ch);
            }
        }
        
        while (!freeHeap.isEmpty()) {
            char validChar = freeHeap.poll();
            resultList[pointer] = validChar;
            pointer++;
            allChars.remove(validChar);
            
            if (!postReqs.containsKey(validChar)) {
                continue;
            }
            
            for (char postReq : postReqs.get(validChar)) {
                preReqCount.merge(postReq, -1, Integer::sum);
                
                if (preReqCount.get(postReq) == 0) {
                    freeHeap.offer(postReq);
                }
            }
        }
        
        if (allChars.isEmpty()) {
            return new String(resultList);
        }
        
        return "";
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        List<String> words = splitWords("wrt wrf er ett rftt");
        String res = alienOrder(words);
        System.out.println(res);
    }
}