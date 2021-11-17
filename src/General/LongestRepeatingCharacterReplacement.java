package General;

/**
 * https://leetcode.com/problems/longest-repeating-character-replacement/
 * 
 * You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. 
 * You can perform this operation at most k times.
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 * 
 * Example 1:
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * 
 * Example 2:
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * 
 * Constraints:
 * 1 <= s.length <= 105
 * s consists of only uppercase English letters.
 * 0 <= k <= s.length
 */
public class LongestRepeatingCharacterReplacement {

	public static void main(String[] args) {
		// String s = "BAAAB"; // output=5
		// int k = 2;

		//String s = "ABBB"; // output=4
		//int k = 2;

		String s = "JSDSSMESSTRQWERPIOUQWERPOIUQWERPOUIPOIUPOIUPOIUPOIU"; // output=6
		int k = 2;

		long startTime = System.nanoTime();
		System.out.println(solution01(s, k));
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);

		startTime = System.nanoTime();
		System.out.println(solution02(s, k));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
		
		startTime = System.nanoTime();
		System.out.println(solution03(s, k));
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	}

	public static int solution01(String s, int k) {
		if (s.length() == 1) {
			return 1;
		}

		int slow = 0;
		int fast = 0;
		int max = 0;
		int count = 0;
		int lives = k;

		while(fast < s.length()) {
			if (s.charAt(slow) == s.charAt(fast)) {
				count++;
				fast++;
			} else {
				if (lives > 0) {
					lives--;
					count++;
					fast++;
				} else {
					/*if (k > 0) {
                        count = 0;
                        fast = slow + 1;
                        slow = fast;
                        lives = k;
                    } else {
                        count = 0;
                        //fast--;
                        slow = fast;
                        lives = k;
                    }*/
					count = 0;
					slow++;
					fast = slow;
					lives = k;
				}
			}

			max = Math.max(max, count);
		}

		if (lives > 0 && count < s.length()) {
			count = count + lives;
			if (count > s.length()) {
				count = s.length();
			}
			max = Math.max(max, count);
		}

		return max;
	}

	public static int solution02(String s, int k) {
		int n = s.length();
		int[] charsCounts = new int[26]; // 26 letters in the alphabet

		int windowStart = 0;
		int maxLength = 0;
		int maxCount = 0;

		for (int windowEnd = 0; windowEnd < n; windowEnd++) {
			charsCounts[s.charAt(windowEnd) - 'A']++;
			int currentCharCount = charsCounts[s.charAt(windowEnd) - 'A'];
			maxCount = Math.max(maxCount, currentCharCount);

			while (windowEnd - windowStart - maxCount + 1 > k) {
				charsCounts[s.charAt(windowStart) - 'A']--;
				windowStart++;
			}

			maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
		}

		return maxLength;
	}

	public static int solution03(String s, int k) {
		int ans = 0;
		int maxCount = 0;
		int[] count = new int[128];

		for (int l = 0, r = 0; r < s.length(); ++r) {
			maxCount = Math.max(maxCount, ++count[s.charAt(r)]);
			
			while (maxCount + k < r - l + 1)
				--count[s.charAt(l++)];
			
			ans = Math.max(ans, r - l + 1);
		}

		return ans;
	}
}