package General.TwoPointers.SlidingWindow;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * 
 * Find the length of the longest substring of a given string without repeating characters.
 * Input: abccabcabcc
 * Output: 3
 * Explanation: longest substrings are abc, cab, both of length 3.
 * 
 * Input: aaaabaaa
 * Output: 2
 * Explanation: ab is the longest substring, length 2
 */
public class LongestSubstringWithoutRepeatingCharacters {

	public static void main(String[] args) {
		String s = "pwwkew";

		long startTime = System.nanoTime();
		System.out.println(solution01(s));
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);

		startTime = System.nanoTime();
		System.out.println(solution02(s));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);

		startTime = System.nanoTime();
		System.out.println(solution03(s));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
		
		startTime = System.nanoTime();
		System.out.println(solution04(s));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	}

	public static int solution01(String s) {

		if (s == null || s.length() == 0) {
			return 0;
		} else if (s.length() == 1) {
			return 1;
		}

		Set<Character> chars = new HashSet<>();

		int slow = 0;
		int fast = 1;
		int count = 1;

		chars.add(s.charAt(0));

		while (fast < s.length()) {
			if (!chars.contains(s.charAt(fast))) {
				chars.add(s.charAt(fast));
				fast++;

				if (chars.size() > count) {
					count = chars.size();
				}
			} else {
				chars.remove(s.charAt(slow));
				slow++;
			}
		}

		return count;
	}

	public static int solution02(String s) {    
		int n = s.length();
		int longest = 0, l = 0, r = 0;
		Set<Character> window = new HashSet<>();

		while (r < n) {
			if (!window.contains(s.charAt(r))) {
				window.add(s.charAt(r));
				r++;
			} else {
				window.remove(s.charAt(l));
				l++;
			}

			longest = Math.max(longest, r - l);
		}

		return longest;
	}

	public static int solution03(String s) {

		if (s == null || s.length() == 0) {
			return 0;
		}

		int total = 1;
		int n = 1;
		Deque<Character> queue = new ArrayDeque<>();
		queue.add(s.charAt(0));

		for (int i = 1; i < s.length(); i++) {
			n++;

			if (queue.contains(s.charAt(i))) {
				queue.add(s.charAt(i));

				while(true) {
					n--;
					Character c = queue.pop();

					if (c == s.charAt(i)) {
						break;
					}
				}
			} else {
				queue.add(s.charAt(i));
			}

			if (n > total) {
				total = n;
			}
		}

		return total;
	}

	/**
	 * Time complexity: O(n).
	 * Space complexity: O(128) = O(1).
	 * @param s
	 * @return
	 */
	public static int solution04(String s) {
		int ans = 0;
		int[] count = new int[128];

		for (int l = 0, r = 0; r < s.length(); ++r) {
			++count[s.charAt(r)];
			
			while (count[s.charAt(r)] > 1)
				--count[s.charAt(l++)];
			
			ans = Math.max(ans, r - l + 1);
		}

		return ans;
	}
}