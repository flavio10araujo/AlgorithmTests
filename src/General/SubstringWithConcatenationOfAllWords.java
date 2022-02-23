package General;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SubstringWithConcatenationOfAllWords {

	public static void main(String[] args) {
		//String s = "barfoofoobarthefoobarman";
		//String[] words = {"bar","foo","the"};

		String s = "wordgoodgoodgoodbestword";
		String[] words = {"word","good","best","good"};

		System.out.println(solution01(s, words));
	}

	public static List<Integer> solution01(String s, String[] words) {
		if (s.isEmpty() || words.length == 0) {
			return new ArrayList<>();
		}

		final int k = words.length;
		final int n = words[0].length();
		List<Integer> ans = new ArrayList<>();
		Map<String, Integer> count = new HashMap<>();

		for (final String word : words) {
			count.put(word, count.getOrDefault(word, 0) + 1);
		}

		for (int i = 0; i <= s.length() - k * n; ++i) {
			Map<String, Integer> seen = new HashMap<>();
			int j = 0;
			
			for (; j < k; ++j) {
				final String word = s.substring(i + j * n, i + j * n + n);
				seen.put(word, seen.getOrDefault(word, 0) + 1);
				
				if (seen.get(word) > count.getOrDefault(word, 0)) {
					break;
				}
			}
			
			if (j == k) {
				ans.add(i);
			}
		}

		return ans;
	}

	/**
	 * This solution doesn't work in leetcode with the error time limit exceeded.
	 * @param s
	 * @param words
	 * @return
	 */
	public static List<Integer> solution02(String s, String[] words) {
		Set<String> strs = new HashSet<>();
		generateStrings(words, strs, new StringBuilder(), new boolean[words.length]);

		List<Integer> ans = new ArrayList<>();
		int wordSize = words[0].length();
		int wordQtd = words.length;

		for (int i = 0; i <= (s.length() - wordSize * wordQtd); i++) {
			if (strs.contains(s.substring(i, i + (wordSize * wordQtd)))) {
				ans.add(i);
			}
		}

		return ans;
	}

	private static void generateStrings(String[] words, Set<String> strs, StringBuilder sb, boolean[] visited) {
		if (sb.length() == (words[0].length() * words.length)) {
			strs.add(new String(sb.toString()));
			return;
		}

		for (int i = 0; i < words.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				sb.append(words[i]);
				generateStrings(words, strs, sb, visited);
				sb.delete(sb.length() - words[i].length(), sb.length());
				visited[i] = false;
			}
		}
	}
}