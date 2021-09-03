package General;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/reorder-data-in-log-files/
 * 
 * You are given an array of logs. 
 * Each log is a space-delimited string of words, where the first word is the identifier.
 * 
 * There are two types of logs:
 * - Letter-logs: All words (except the identifier) consist of lowercase English letters.
 * - Digit-logs: All words (except the identifier) consist of digits.
 * 
 * Reorder these logs so that:
 * - The letter-logs come before all digit-logs.
 * - The letter-logs are sorted lexicographically by their contents. 
 *   If their contents are the same, then sort them lexicographically by their identifiers.
 * - The digit-logs maintain their relative ordering.
 * 
 * Return the final order of the logs.
 * 
 * Example 1:
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 * Explanation:
 * 		The letter-log contents are all different, so their ordering is "art can", "art zero", "own kit dig".
 * 		The digit-logs have a relative order of "dig1 8 1 5 1", "dig2 3 6".
 * 
 * Example 2:
 * Input: logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
 * Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
 * 
 * Constraints:
 * 1 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * All the tokens of logs[i] are separated by a single space.
 * logs[i] is guaranteed to have an identifier and at least one word after the identifier.
 */
public class ReorderDataInLogFiles {

	public static void main(String[] args) {
		//String[] logs = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
		String[] logs = {"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo","a2 act car"};
		//String[] logs = {"dig1 8 1 5 1","let1 art zero can","dig2 3 6","let2 own kit dig","let3 art zero"};
		
		/*String[] res = reorderLogFiles(logs);
		System.out.println(Arrays.toString(res));*/
		
		String[] res2 = solution02(logs);
		System.out.println(Arrays.toString(res2));
	}

	/**
	 * Time complexity: O(n log n)
	 * Space complexity: O(log n)
	 * @param logs
	 * @return
	 */
	public static String[] reorderLogFiles(String[] logs) {
        
		Arrays.sort(logs, (log1, log2) -> {
			// log1 < log2 -> -1
			// log1 == log2 -> 0
			// log1 > log2 -> 1
			
			//int sBodyBegin = s.indexOf(" ");
			//String prefix = s.substring(0, sBodyBegin);
			//String sBody = s.substring(sBodyBegin + 1);
			
			int sBodyBegin1 = log1.indexOf(" ");
			String prefix1 = log1.substring(0, sBodyBegin1);
			String sBody1 = log1.substring(sBodyBegin1 + 1);
			
			int sBodyBegin2 = log2.indexOf(" ");
			String prefix2 = log2.substring(0, sBodyBegin2);
			String sBody2 = log2.substring(sBodyBegin2 + 1);
			
			boolean isDigit1 = Character.isDigit(sBody1.charAt(0));
			boolean isDigit2 = Character.isDigit(sBody2.charAt(0));
			
			if (!isDigit1 && !isDigit2) {
				int value = sBody1.compareTo(sBody2);
				
				if (value == 0) {
					return prefix1.compareTo(prefix2);
				}
				
				return value;
			}
			
			return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
		});
		
		return logs;
    }

	public static String[] solution02(String[] logs) {
		List<String> nums = new ArrayList<>();
		Map<String, Integer> letters = new TreeMap<>();
		String[] res = new String[logs.length];
		
		for (int i = 0; i < logs.length; i++) {
			String s = logs[i];
			int sBodyBegin = s.indexOf(" ");
			String prefix = s.substring(0, sBodyBegin);
			String sBody = s.substring(sBodyBegin + 1);
			
			if (Character.isDigit(sBody.charAt(0))) {
				nums.add(logs[i]);
			} else {
				if (letters.containsKey(sBody)) {
					Integer iDuplicate = letters.get(sBody);
					String sDuplicate = logs[iDuplicate];
					int sBodyBeginDuplicate = sDuplicate.indexOf(" ");
					String prefixDuplicate = sDuplicate.substring(0, sBodyBeginDuplicate);
					
					letters.remove(sBody);
					letters.put(sBody + " " + prefixDuplicate, iDuplicate);
					
					letters.put(sBody + " " + prefix, i);
				} else {
					letters.put(sBody, i);
				}
				
				System.out.println(letters);
			}
		}
		
		int i = 0;
		
		for (Map.Entry<String, Integer> entry : letters.entrySet()) {
			res[i] = logs[entry.getValue()];
			i++;
		}
		
		for (String s : nums) {
			res[i] = s;
			i++;
		}
		
		return res;
	}
}